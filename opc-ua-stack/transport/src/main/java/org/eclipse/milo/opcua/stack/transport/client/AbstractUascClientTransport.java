/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.transport.client;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import io.netty.channel.Channel;
import io.netty.util.Timeout;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.UaRequestMessageType;
import org.eclipse.milo.opcua.stack.core.types.UaResponseMessageType;
import org.eclipse.milo.opcua.stack.core.types.structured.RequestHeader;
import org.eclipse.milo.opcua.stack.transport.client.uasc.UascRequest;
import org.eclipse.milo.opcua.stack.transport.client.uasc.UascResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractUascClientTransport implements OpcClientTransport, UascResponseHandler {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected final AtomicLong requestId = new AtomicLong(0L);

    protected final Map<Long, CompletableFuture<UaResponseMessageType>> pendingRequests = new ConcurrentHashMap<>();
    protected final Map<Long, Timeout> pendingTimeouts = new ConcurrentHashMap<>();

    protected final OpcClientTransportConfig config;

    public AbstractUascClientTransport(OpcClientTransportConfig config) {
        this.config = config;
    }

    protected abstract CompletableFuture<Channel> getChannel();

    @Override
    public CompletableFuture<UaResponseMessageType> sendRequestMessage(UaRequestMessageType requestMessage) {
        return getChannel().thenCompose(ch -> sendRequestMessage(requestMessage, ch));
    }

    protected CompletableFuture<UaResponseMessageType> sendRequestMessage(
        UaRequestMessageType requestMessage,
        Channel channel
    ) {

        var request = new UascRequest(requestId.getAndIncrement(), requestMessage);
        var responseFuture = new CompletableFuture<UaResponseMessageType>();

        pendingRequests.put(request.getRequestId(), responseFuture);
        scheduleRequestTimeout(request);

        channel.writeAndFlush(request).addListener(f -> {
            if (!f.isSuccess()) {
                pendingRequests.remove(request.getRequestId());
                cancelRequestTimeout(request.getRequestId());

                responseFuture.completeExceptionally(f.cause());

                logger.debug(
                    "Write failed, request={}, requestHandle={}",
                    requestMessage.getClass().getSimpleName(), request.getRequestId()
                );
            } else {
                if (logger.isTraceEnabled()) {
                    logger.trace(
                        "Write succeeded, request={}, requestId={}",
                        requestMessage.getClass().getSimpleName(), request.getRequestId()
                    );
                }
            }
        });

        return responseFuture;
    }

    private void scheduleRequestTimeout(UascRequest request) {
        RequestHeader requestHeader = request.getRequestMessage().getRequestHeader();

        long timeoutHint = requestHeader.getTimeoutHint() != null ?
            requestHeader.getTimeoutHint().longValue() : 0L;

        if (timeoutHint > 0) {
            Timeout timeout = config.getWheelTimer().newTimeout(
                t -> {
                    if (!t.isCancelled()) {
                        CompletableFuture<UaResponseMessageType> future =
                            pendingRequests.remove(request.getRequestId());

                        if (future != null) {
                            UaException exception = new UaException(
                                StatusCodes.Bad_Timeout,
                                String.format(
                                    "requestId=%s timed out after %sms",
                                    request.getRequestId(), timeoutHint)
                            );

                            future.completeExceptionally(exception);
                        }
                    }
                },
                timeoutHint,
                TimeUnit.MILLISECONDS
            );

            pendingTimeouts.put(request.getRequestId(), timeout);
        }
    }

    protected void cancelRequestTimeout(long requestId) {
        Timeout timeout = pendingTimeouts.remove(requestId);
        if (timeout != null) timeout.cancel();
    }

    @Override
    public void handleResponse(long requestId, UaResponseMessageType responseMessage) {
        CompletableFuture<UaResponseMessageType> responseFuture = pendingRequests.remove(requestId);

        if (responseFuture != null) {
            cancelRequestTimeout(requestId);

            config.getExecutor().execute(() -> responseFuture.complete(responseMessage));
        } else {
            logger.warn("Received response for unknown request, requestId={}", requestId);
        }
    }

    @Override
    public void handleSendFailure(long requestId, UaException exception) {
        CompletableFuture<UaResponseMessageType> responseFuture = pendingRequests.remove(requestId);

        if (responseFuture != null) {
            cancelRequestTimeout(requestId);

            config.getExecutor().execute(() -> responseFuture.completeExceptionally(exception));
        } else {
            logger.warn("Send failed for unknown request, requestId={}", requestId);
        }
    }

    @Override
    public void handleReceiveFailure(long requestId, UaException exception) {
        CompletableFuture<UaResponseMessageType> responseFuture = pendingRequests.remove(requestId);

        if (responseFuture != null) {
            cancelRequestTimeout(requestId);

            config.getExecutor().execute(() -> responseFuture.completeExceptionally(exception));
        } else {
            logger.warn("Receive failed for unknown request, requestId={}", requestId);
        }
    }

    @Override
    public void handleChannelInactive() {
        var exception = new UaException(
            StatusCodes.Bad_ConnectionClosed,
            "connection closed"
        );

        pendingRequests.values().forEach(
            f ->
                config.getExecutor().execute(() -> f.completeExceptionally(exception))
        );
        pendingRequests.clear();

        pendingTimeouts.values().forEach(Timeout::cancel);
        pendingTimeouts.clear();
    }

}

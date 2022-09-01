package org.eclipse.milo.opcua.sdk.client.model.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.UaException;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.11/#9.1.11.11">https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.11/#9.1.11.11</a>
 */
public interface PubSubDiagnosticsDataSetWriterType extends PubSubDiagnosticsType {
    /**
     * Get the Counters {@link BaseObjectType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the Counters {@link BaseObjectType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseObjectType getCountersNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getCountersNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * BaseObjectType Node or completes exceptionally if an error occurs creating or
     * getting the Node.
     */
    CompletableFuture<? extends BaseObjectType> getCountersNodeAsync();

    /**
     * Get the LiveValues {@link BaseObjectType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the LiveValues {@link BaseObjectType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    BaseObjectType getLiveValuesNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getLiveValuesNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * BaseObjectType Node or completes exceptionally if an error occurs creating or
     * getting the Node.
     */
    CompletableFuture<? extends BaseObjectType> getLiveValuesNodeAsync();
}
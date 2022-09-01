package org.eclipse.milo.opcua.sdk.client.model.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.13/#9.1.13.3">https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.13/#9.1.13.3</a>
 */
public interface PubSubCommunicationFailureEventType extends PubSubStatusEventType {
    QualifiedProperty<StatusCode> ERROR = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Error",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=19"),
        -1,
        StatusCode.class
    );

    /**
     * Get the local value of the Error Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the Error Node.
     * @throws UaException if an error occurs creating or getting the Error Node.
     */
    StatusCode getError() throws UaException;

    /**
     * Set the local value of the Error Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param value the local value to set for the Error Node.
     * @throws UaException if an error occurs creating or getting the Error Node.
     */
    void setError(StatusCode value) throws UaException;

    /**
     * Read the value of the Error Node from the server and update the local value if
     * the operation succeeds.
     *
     * @return the {@link StatusCode} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    StatusCode readError() throws UaException;

    /**
     * Write a new value for the Error Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param value the {@link StatusCode} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeError(StatusCode value) throws UaException;

    /**
     * An asynchronous implementation of {@link #readError}.
     *
     * @return a CompletableFuture that completes successfully with the value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends StatusCode> readErrorAsync();

    /**
     * An asynchronous implementation of {@link #writeError}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeErrorAsync(StatusCode value);

    /**
     * Get the Error {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the Error {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getErrorNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getErrorNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * PropertyType Node or completes exceptionally if an error occurs creating or
     * getting the Node.
     */
    CompletableFuture<? extends PropertyType> getErrorNodeAsync();
}
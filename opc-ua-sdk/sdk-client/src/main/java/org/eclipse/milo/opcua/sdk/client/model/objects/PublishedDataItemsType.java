package org.eclipse.milo.opcua.sdk.client.model.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.PublishedVariableDataType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.4/#9.1.4.3.1">https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.4/#9.1.4.3.1</a>
 */
public interface PublishedDataItemsType extends PublishedDataSetType {
    QualifiedProperty<PublishedVariableDataType[]> PUBLISHED_DATA = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "PublishedData",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=14273"),
        1,
        PublishedVariableDataType[].class
    );

    /**
     * Get the local value of the PublishedData Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the PublishedData Node.
     * @throws UaException if an error occurs creating or getting the PublishedData Node.
     */
    PublishedVariableDataType[] getPublishedData() throws UaException;

    /**
     * Set the local value of the PublishedData Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param value the local value to set for the PublishedData Node.
     * @throws UaException if an error occurs creating or getting the PublishedData Node.
     */
    void setPublishedData(PublishedVariableDataType[] value) throws UaException;

    /**
     * Read the value of the PublishedData Node from the server and update the local value if
     * the operation succeeds.
     *
     * @return the {@link PublishedVariableDataType[]} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    PublishedVariableDataType[] readPublishedData() throws UaException;

    /**
     * Write a new value for the PublishedData Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param value the {@link PublishedVariableDataType[]} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writePublishedData(PublishedVariableDataType[] value) throws UaException;

    /**
     * An asynchronous implementation of {@link #readPublishedData}.
     *
     * @return a CompletableFuture that completes successfully with the value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends PublishedVariableDataType[]> readPublishedDataAsync();

    /**
     * An asynchronous implementation of {@link #writePublishedData}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writePublishedDataAsync(PublishedVariableDataType[] value);

    /**
     * Get the PublishedData {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the PublishedData {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getPublishedDataNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getPublishedDataNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * PropertyType Node or completes exceptionally if an error occurs creating or
     * getting the Node.
     */
    CompletableFuture<? extends PropertyType> getPublishedDataNodeAsync();
}

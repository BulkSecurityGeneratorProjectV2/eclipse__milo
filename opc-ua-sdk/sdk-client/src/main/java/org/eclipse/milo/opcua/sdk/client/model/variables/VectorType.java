package org.eclipse.milo.opcua.sdk.client.model.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.EUInformation;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/7.21">https://reference.opcfoundation.org/v105/Core/docs/Part5/7.21</a>
 */
public interface VectorType extends BaseDataVariableType {
    QualifiedProperty<EUInformation> VECTOR_UNIT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "VectorUnit",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=887"),
        -1,
        EUInformation.class
    );

    /**
     * Get the local value of the VectorUnit Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the VectorUnit Node.
     * @throws UaException if an error occurs creating or getting the VectorUnit Node.
     */
    EUInformation getVectorUnit() throws UaException;

    /**
     * Set the local value of the VectorUnit Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param value the local value to set for the VectorUnit Node.
     * @throws UaException if an error occurs creating or getting the VectorUnit Node.
     */
    void setVectorUnit(EUInformation value) throws UaException;

    /**
     * Read the value of the VectorUnit Node from the server and update the local value if
     * the operation succeeds.
     *
     * @return the {@link EUInformation} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    EUInformation readVectorUnit() throws UaException;

    /**
     * Write a new value for the VectorUnit Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param value the {@link EUInformation} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeVectorUnit(EUInformation value) throws UaException;

    /**
     * An asynchronous implementation of {@link #readVectorUnit}.
     *
     * @return a CompletableFuture that completes successfully with the value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends EUInformation> readVectorUnitAsync();

    /**
     * An asynchronous implementation of {@link #writeVectorUnit}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeVectorUnitAsync(EUInformation value);

    /**
     * Get the VectorUnit {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the VectorUnit {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getVectorUnitNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getVectorUnitNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * PropertyType Node or completes exceptionally if an error occurs creating or
     * getting the Node.
     */
    CompletableFuture<? extends PropertyType> getVectorUnitNodeAsync();
}

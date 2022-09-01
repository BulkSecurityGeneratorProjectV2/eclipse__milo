package org.eclipse.milo.opcua.sdk.client.model.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.structured.AccessRestrictionType;
import org.eclipse.milo.opcua.stack.core.types.structured.RolePermissionType;
import org.eclipse.milo.opcua.stack.core.types.structured.SemanticChangeStructureDataType;

public class SemanticChangeEventTypeNode extends BaseEventTypeNode implements SemanticChangeEventType {
    public SemanticChangeEventTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                       QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                       UInteger writeMask, UInteger userWriteMask, RolePermissionType[] rolePermissions,
                                       RolePermissionType[] userRolePermissions, AccessRestrictionType accessRestrictions,
                                       UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, rolePermissions, userRolePermissions, accessRestrictions, eventNotifier);
    }

    @Override
    public SemanticChangeStructureDataType[] getChanges() throws UaException {
        PropertyTypeNode node = getChangesNode();
        return cast(node.getValue().getValue().getValue(), SemanticChangeStructureDataType[].class);
    }

    @Override
    public void setChanges(SemanticChangeStructureDataType[] value) throws UaException {
        PropertyTypeNode node = getChangesNode();
        ExtensionObject[] encoded = ExtensionObject.encodeArray(client.getStaticSerializationContext(), value);
        node.setValue(new Variant(encoded));
    }

    @Override
    public SemanticChangeStructureDataType[] readChanges() throws UaException {
        try {
            return readChangesAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeChanges(SemanticChangeStructureDataType[] value) throws UaException {
        try {
            writeChangesAsync(value).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends SemanticChangeStructureDataType[]> readChangesAsync() {
        return getChangesNodeAsync()
            .thenCompose(node -> node.readAttributeAsync(AttributeId.Value))
            .thenApply(v -> cast(v.getValue().getValue(), SemanticChangeStructureDataType[].class));
    }

    @Override
    public CompletableFuture<StatusCode> writeChangesAsync(
        SemanticChangeStructureDataType[] changes) {
        ExtensionObject[] encoded = ExtensionObject.encodeArray(client.getStaticSerializationContext(), changes);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getChangesNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getChangesNode() throws UaException {
        try {
            return getChangesNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getChangesNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync(
            "http://opcfoundation.org/UA/",
            "Changes",
            ExpandedNodeId.parse("ns=0;i=46"),
            false
        );
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
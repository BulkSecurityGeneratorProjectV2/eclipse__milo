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
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.enumerated.PerformUpdateType;
import org.eclipse.milo.opcua.stack.core.types.structured.AccessRestrictionType;
import org.eclipse.milo.opcua.stack.core.types.structured.RolePermissionType;

public class AuditHistoryAnnotationUpdateEventTypeNode extends AuditHistoryUpdateEventTypeNode implements AuditHistoryAnnotationUpdateEventType {
    public AuditHistoryAnnotationUpdateEventTypeNode(OpcUaClient client, NodeId nodeId,
                                                     NodeClass nodeClass, QualifiedName browseName, LocalizedText displayName,
                                                     LocalizedText description, UInteger writeMask, UInteger userWriteMask,
                                                     RolePermissionType[] rolePermissions, RolePermissionType[] userRolePermissions,
                                                     AccessRestrictionType accessRestrictions, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, rolePermissions, userRolePermissions, accessRestrictions, eventNotifier);
    }

    @Override
    public PerformUpdateType getPerformInsertReplace() throws UaException {
        PropertyTypeNode node = getPerformInsertReplaceNode();
        Object value = node.getValue().getValue().getValue();

        if (value instanceof Integer) {
            return PerformUpdateType.from((Integer) value);
        } else if (value instanceof PerformUpdateType) {
            return (PerformUpdateType) value;
        } else {
            return null;
        }
    }

    @Override
    public void setPerformInsertReplace(PerformUpdateType value) throws UaException {
        PropertyTypeNode node = getPerformInsertReplaceNode();
        node.setValue(new Variant(value));
    }

    @Override
    public PerformUpdateType readPerformInsertReplace() throws UaException {
        try {
            return readPerformInsertReplaceAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writePerformInsertReplace(PerformUpdateType value) throws UaException {
        try {
            writePerformInsertReplaceAsync(value).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PerformUpdateType> readPerformInsertReplaceAsync() {
        return getPerformInsertReplaceNodeAsync()
            .thenCompose(node -> node.readAttributeAsync(AttributeId.Value))
            .thenApply(v -> {
                Object value = v.getValue().getValue();
                if (value instanceof Integer) {
                    return PerformUpdateType.from((Integer) value);
                } else {
                    return null;
                }
            });
    }

    @Override
    public CompletableFuture<StatusCode> writePerformInsertReplaceAsync(
        PerformUpdateType performInsertReplace) {
        DataValue value = DataValue.valueOnly(new Variant(performInsertReplace));
        return getPerformInsertReplaceNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getPerformInsertReplaceNode() throws UaException {
        try {
            return getPerformInsertReplaceNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getPerformInsertReplaceNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync(
            "http://opcfoundation.org/UA/",
            "PerformInsertReplace",
            ExpandedNodeId.parse("ns=0;i=46"),
            false
        );
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public DataValue[] getNewValues() throws UaException {
        PropertyTypeNode node = getNewValuesNode();
        return (DataValue[]) node.getValue().getValue().getValue();
    }

    @Override
    public void setNewValues(DataValue[] value) throws UaException {
        PropertyTypeNode node = getNewValuesNode();
        node.setValue(new Variant(value));
    }

    @Override
    public DataValue[] readNewValues() throws UaException {
        try {
            return readNewValuesAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeNewValues(DataValue[] value) throws UaException {
        try {
            writeNewValuesAsync(value).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends DataValue[]> readNewValuesAsync() {
        return getNewValuesNodeAsync()
            .thenCompose(node -> node.readAttributeAsync(AttributeId.Value))
            .thenApply(v -> (DataValue[]) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeNewValuesAsync(DataValue[] newValues) {
        DataValue value = DataValue.valueOnly(new Variant(newValues));
        return getNewValuesNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getNewValuesNode() throws UaException {
        try {
            return getNewValuesNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getNewValuesNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync(
            "http://opcfoundation.org/UA/",
            "NewValues",
            ExpandedNodeId.parse("ns=0;i=46"),
            false
        );
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public DataValue[] getOldValues() throws UaException {
        PropertyTypeNode node = getOldValuesNode();
        return (DataValue[]) node.getValue().getValue().getValue();
    }

    @Override
    public void setOldValues(DataValue[] value) throws UaException {
        PropertyTypeNode node = getOldValuesNode();
        node.setValue(new Variant(value));
    }

    @Override
    public DataValue[] readOldValues() throws UaException {
        try {
            return readOldValuesAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeOldValues(DataValue[] value) throws UaException {
        try {
            writeOldValuesAsync(value).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends DataValue[]> readOldValuesAsync() {
        return getOldValuesNodeAsync()
            .thenCompose(node -> node.readAttributeAsync(AttributeId.Value))
            .thenApply(v -> (DataValue[]) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeOldValuesAsync(DataValue[] oldValues) {
        DataValue value = DataValue.valueOnly(new Variant(oldValues));
        return getOldValuesNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getOldValuesNode() throws UaException {
        try {
            return getOldValuesNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getOldValuesNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync(
            "http://opcfoundation.org/UA/",
            "OldValues",
            ExpandedNodeId.parse("ns=0;i=46"),
            false
        );
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
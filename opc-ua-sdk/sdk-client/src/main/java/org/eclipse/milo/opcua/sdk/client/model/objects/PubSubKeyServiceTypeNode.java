package org.eclipse.milo.opcua.sdk.client.model.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.structured.AccessRestrictionType;
import org.eclipse.milo.opcua.stack.core.types.structured.RolePermissionType;

public class PubSubKeyServiceTypeNode extends BaseObjectTypeNode implements PubSubKeyServiceType {
    public PubSubKeyServiceTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                    QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                    UInteger writeMask, UInteger userWriteMask, RolePermissionType[] rolePermissions,
                                    RolePermissionType[] userRolePermissions, AccessRestrictionType accessRestrictions,
                                    UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, rolePermissions, userRolePermissions, accessRestrictions, eventNotifier);
    }

    @Override
    public SecurityGroupFolderTypeNode getSecurityGroupsNode() throws UaException {
        try {
            return getSecurityGroupsNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError));
        }
    }

    @Override
    public CompletableFuture<? extends SecurityGroupFolderTypeNode> getSecurityGroupsNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync(
            "http://opcfoundation.org/UA/",
            "SecurityGroups",
            ExpandedNodeId.parse("ns=0;i=47"),
            false
        );
        return future.thenApply(node -> (SecurityGroupFolderTypeNode) node);
    }

    @Override
    public PubSubKeyPushTargetFolderTypeNode getKeyPushTargetsNode() throws UaException {
        try {
            return getKeyPushTargetsNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError));
        }
    }

    @Override
    public CompletableFuture<? extends PubSubKeyPushTargetFolderTypeNode> getKeyPushTargetsNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync(
            "http://opcfoundation.org/UA/",
            "KeyPushTargets",
            ExpandedNodeId.parse("ns=0;i=47"),
            false
        );
        return future.thenApply(node -> (PubSubKeyPushTargetFolderTypeNode) node);
    }
}
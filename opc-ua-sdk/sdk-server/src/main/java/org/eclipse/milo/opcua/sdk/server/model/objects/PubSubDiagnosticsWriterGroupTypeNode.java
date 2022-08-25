package org.eclipse.milo.opcua.sdk.server.model.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.core.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.AccessRestrictionType;
import org.eclipse.milo.opcua.stack.core.types.structured.RolePermissionType;

public class PubSubDiagnosticsWriterGroupTypeNode extends PubSubDiagnosticsTypeNode implements PubSubDiagnosticsWriterGroupType {
    public PubSubDiagnosticsWriterGroupTypeNode(UaNodeContext context, NodeId nodeId,
                                                QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                                UInteger writeMask, UInteger userWriteMask, RolePermissionType[] rolePermissions,
                                                RolePermissionType[] userRolePermissions, AccessRestrictionType accessRestrictions,
                                                UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, rolePermissions, userRolePermissions, accessRestrictions, eventNotifier);
    }

    public PubSubDiagnosticsWriterGroupTypeNode(UaNodeContext context, NodeId nodeId,
                                                QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                                UInteger writeMask, UInteger userWriteMask, RolePermissionType[] rolePermissions,
                                                RolePermissionType[] userRolePermissions, AccessRestrictionType accessRestrictions) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, rolePermissions, userRolePermissions, accessRestrictions);
    }

    @Override
    public BaseObjectTypeNode getCountersNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "Counters");
        return (BaseObjectTypeNode) component.orElse(null);
    }

    @Override
    public BaseObjectTypeNode getLiveValuesNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "LiveValues");
        return (BaseObjectTypeNode) component.orElse(null);
    }
}
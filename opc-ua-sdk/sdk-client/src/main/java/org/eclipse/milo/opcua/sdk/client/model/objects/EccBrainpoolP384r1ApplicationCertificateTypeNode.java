package org.eclipse.milo.opcua.sdk.client.model.objects;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.structured.AccessRestrictionType;
import org.eclipse.milo.opcua.stack.core.types.structured.RolePermissionType;

public class EccBrainpoolP384r1ApplicationCertificateTypeNode extends EccApplicationCertificateTypeNode implements EccBrainpoolP384r1ApplicationCertificateType {
    public EccBrainpoolP384r1ApplicationCertificateTypeNode(OpcUaClient client, NodeId nodeId,
                                                            NodeClass nodeClass, QualifiedName browseName, LocalizedText displayName,
                                                            LocalizedText description, UInteger writeMask, UInteger userWriteMask,
                                                            RolePermissionType[] rolePermissions, RolePermissionType[] userRolePermissions,
                                                            AccessRestrictionType accessRestrictions, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, rolePermissions, userRolePermissions, accessRestrictions, eventNotifier);
    }
}

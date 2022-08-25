package org.eclipse.milo.opcua.sdk.server.model.variables;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.core.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.AccessLevelExType;
import org.eclipse.milo.opcua.stack.core.types.structured.AccessRestrictionType;
import org.eclipse.milo.opcua.stack.core.types.structured.EUInformation;
import org.eclipse.milo.opcua.stack.core.types.structured.RolePermissionType;

public class CartesianCoordinatesTypeNode extends BaseDataVariableTypeNode implements CartesianCoordinatesType {
    public CartesianCoordinatesTypeNode(UaNodeContext context, NodeId nodeId,
                                        QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                        UInteger writeMask, UInteger userWriteMask, RolePermissionType[] rolePermissions,
                                        RolePermissionType[] userRolePermissions, AccessRestrictionType accessRestrictions,
                                        DataValue value, NodeId dataType, Integer valueRank, UInteger[] arrayDimensions,
                                        UByte accessLevel, UByte userAccessLevel, Double minimumSamplingInterval, boolean historizing,
                                        AccessLevelExType accessLevelEx) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, rolePermissions, userRolePermissions, accessRestrictions, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing, accessLevelEx);
    }

    public CartesianCoordinatesTypeNode(UaNodeContext context, NodeId nodeId,
                                        QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                        UInteger writeMask, UInteger userWriteMask, RolePermissionType[] rolePermissions,
                                        RolePermissionType[] userRolePermissions, AccessRestrictionType accessRestrictions,
                                        DataValue value, NodeId dataType, Integer valueRank, UInteger[] arrayDimensions) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, rolePermissions, userRolePermissions, accessRestrictions, value, dataType, valueRank, arrayDimensions);
    }

    @Override
    public PropertyTypeNode getLengthUnitNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(CartesianCoordinatesType.LENGTH_UNIT);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public EUInformation getLengthUnit() {
        return getProperty(CartesianCoordinatesType.LENGTH_UNIT).orElse(null);
    }

    @Override
    public void setLengthUnit(EUInformation value) {
        setProperty(CartesianCoordinatesType.LENGTH_UNIT, value);
    }
}
package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/8.5.1">https://reference.opcfoundation.org/v105/Core/docs/Part14/8.5.1</a>
 */
public interface SecurityGroupFolderType extends FolderType {
    QualifiedProperty<String[]> SUPPORTED_SECURITY_POLICY_URIS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SupportedSecurityPolicyUris",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        1,
        String[].class
    );

    String[] getSupportedSecurityPolicyUris();

    void setSupportedSecurityPolicyUris(String[] value);

    PropertyType getSupportedSecurityPolicyUrisNode();

    MethodNode getAddSecurityGroupMethodNode();

    MethodNode getRemoveSecurityGroupMethodNode();

    MethodNode getAddSecurityGroupFolderMethodNode();

    MethodNode getRemoveSecurityGroupFolderMethodNode();
}
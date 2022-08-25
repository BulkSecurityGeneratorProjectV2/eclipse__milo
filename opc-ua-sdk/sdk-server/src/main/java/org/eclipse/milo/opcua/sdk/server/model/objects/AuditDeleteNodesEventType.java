package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteNodesItem;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/6.4.21">https://reference.opcfoundation.org/v105/Core/docs/Part5/6.4.21</a>
 */
public interface AuditDeleteNodesEventType extends AuditNodeManagementEventType {
    QualifiedProperty<DeleteNodesItem[]> NODES_TO_DELETE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "NodesToDelete",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=382"),
        1,
        DeleteNodesItem[].class
    );

    DeleteNodesItem[] getNodesToDelete();

    void setNodesToDelete(DeleteNodesItem[] value);

    PropertyType getNodesToDeleteNode();
}
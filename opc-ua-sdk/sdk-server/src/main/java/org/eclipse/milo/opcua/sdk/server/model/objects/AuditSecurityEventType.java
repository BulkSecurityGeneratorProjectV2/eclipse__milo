package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/6.4.4">https://reference.opcfoundation.org/v105/Core/docs/Part5/6.4.4</a>
 */
public interface AuditSecurityEventType extends AuditEventType {
    QualifiedProperty<StatusCode> STATUS_CODE_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "StatusCodeId",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=19"),
        -1,
        StatusCode.class
    );

    StatusCode getStatusCodeId();

    void setStatusCodeId(StatusCode value);

    PropertyType getStatusCodeIdNode();
}
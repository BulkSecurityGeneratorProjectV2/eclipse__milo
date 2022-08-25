package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ServerState;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/6.4.30">https://reference.opcfoundation.org/v105/Core/docs/Part5/6.4.30</a>
 */
public interface SystemStatusChangeEventType extends SystemEventType {
    QualifiedProperty<ServerState> SYSTEM_STATE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SystemState",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=852"),
        -1,
        ServerState.class
    );

    ServerState getSystemState();

    void setSystemState(ServerState value);

    PropertyType getSystemStateNode();
}
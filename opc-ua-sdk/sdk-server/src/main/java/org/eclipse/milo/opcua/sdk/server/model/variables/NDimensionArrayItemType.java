package org.eclipse.milo.opcua.sdk.server.model.variables;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.AxisInformation;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part8/5.3.4/#5.3.4.6">https://reference.opcfoundation.org/v105/Core/docs/Part8/5.3.4/#5.3.4.6</a>
 */
public interface NDimensionArrayItemType extends ArrayItemType {
    QualifiedProperty<AxisInformation[]> AXIS_DEFINITION = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "AxisDefinition",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12079"),
        1,
        AxisInformation[].class
    );

    AxisInformation[] getAxisDefinition();

    void setAxisDefinition(AxisInformation[] value);

    PropertyType getAxisDefinitionNode();
}
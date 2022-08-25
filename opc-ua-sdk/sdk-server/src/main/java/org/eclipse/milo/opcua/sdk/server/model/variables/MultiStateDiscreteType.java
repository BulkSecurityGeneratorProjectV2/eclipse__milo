package org.eclipse.milo.opcua.sdk.server.model.variables;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part8/5.3.3/#5.3.3.3">https://reference.opcfoundation.org/v105/Core/docs/Part8/5.3.3/#5.3.3.3</a>
 */
public interface MultiStateDiscreteType extends DiscreteItemType {
    QualifiedProperty<LocalizedText[]> ENUM_STRINGS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EnumStrings",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=21"),
        1,
        LocalizedText[].class
    );

    LocalizedText[] getEnumStrings();

    void setEnumStrings(LocalizedText[] value);

    PropertyType getEnumStringsNode();
}
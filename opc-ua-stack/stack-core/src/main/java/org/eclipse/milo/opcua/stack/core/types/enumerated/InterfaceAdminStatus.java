package org.eclipse.milo.opcua.stack.core.types.enumerated;

import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEnumeration;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.structured.EnumDefinition;
import org.eclipse.milo.opcua.stack.core.types.structured.EnumField;
import org.jetbrains.annotations.Nullable;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part22/5.3.1/#5.3.1.2">https://reference.opcfoundation.org/v105/Core/docs/Part22/5.3.1/#5.3.1.2</a>
 */
public enum InterfaceAdminStatus implements UaEnumeration {
    /**
     * Ready to pass packets.
     */
    Up(0),

    /**
     * Not ready to pass packets and not in some test mode.
     */
    Down(1),

    /**
     * In some test mode.
     */
    Testing(2);

    private final int value;

    InterfaceAdminStatus(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("ns=0;i=24212");
    }

    public static @Nullable InterfaceAdminStatus from(int value) {
        switch (value) {
            case 0:
                return Up;
            case 1:
                return Down;
            case 2:
                return Testing;
            default:
                return null;
        }
    }

    public static EnumDefinition definition() {
        return new EnumDefinition(new EnumField[]{
            new EnumField(0L, LocalizedText.NULL_VALUE, new LocalizedText("", "Ready to pass packets."), "Up"),
            new EnumField(1L, LocalizedText.NULL_VALUE, new LocalizedText("", "Not ready to pass packets and not in some test mode."), "Down"),
            new EnumField(2L, LocalizedText.NULL_VALUE, new LocalizedText("", "In some test mode."), "Testing")
        });
    }

    public static final class Codec extends GenericDataTypeCodec<InterfaceAdminStatus> {
        @Override
        public Class<InterfaceAdminStatus> getType() {
            return InterfaceAdminStatus.class;
        }

        @Override
        public InterfaceAdminStatus decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, InterfaceAdminStatus.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           InterfaceAdminStatus value) {
            encoder.writeEnum(null, value);
        }
    }
}

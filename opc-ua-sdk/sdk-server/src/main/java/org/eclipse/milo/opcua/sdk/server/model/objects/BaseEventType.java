package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.structured.TimeZoneDataType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/6.4.2">https://reference.opcfoundation.org/v105/Core/docs/Part5/6.4.2</a>
 */
public interface BaseEventType extends BaseObjectType {
    QualifiedProperty<ByteString> EVENT_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EventId",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15"),
        -1,
        ByteString.class
    );

    QualifiedProperty<NodeId> EVENT_TYPE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EventType",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17"),
        -1,
        NodeId.class
    );

    QualifiedProperty<NodeId> SOURCE_NODE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SourceNode",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17"),
        -1,
        NodeId.class
    );

    QualifiedProperty<String> SOURCE_NAME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SourceName",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        -1,
        String.class
    );

    QualifiedProperty<DateTime> TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Time",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=294"),
        -1,
        DateTime.class
    );

    QualifiedProperty<DateTime> RECEIVE_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ReceiveTime",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=294"),
        -1,
        DateTime.class
    );

    QualifiedProperty<TimeZoneDataType> LOCAL_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "LocalTime",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=8912"),
        -1,
        TimeZoneDataType.class
    );

    QualifiedProperty<LocalizedText> MESSAGE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Message",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=21"),
        -1,
        LocalizedText.class
    );

    QualifiedProperty<UShort> SEVERITY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Severity",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=5"),
        -1,
        UShort.class
    );

    ByteString getEventId();

    void setEventId(ByteString value);

    PropertyType getEventIdNode();

    NodeId getEventType();

    void setEventType(NodeId value);

    PropertyType getEventTypeNode();

    NodeId getSourceNode();

    void setSourceNode(NodeId value);

    PropertyType getSourceNodeNode();

    String getSourceName();

    void setSourceName(String value);

    PropertyType getSourceNameNode();

    DateTime getTime();

    void setTime(DateTime value);

    PropertyType getTimeNode();

    DateTime getReceiveTime();

    void setReceiveTime(DateTime value);

    PropertyType getReceiveTimeNode();

    TimeZoneDataType getLocalTime();

    void setLocalTime(TimeZoneDataType value);

    PropertyType getLocalTimeNode();

    LocalizedText getMessage();

    void setMessage(LocalizedText value);

    PropertyType getMessageNode();

    UShort getSeverity();

    void setSeverity(UShort value);

    PropertyType getSeverityNode();
}
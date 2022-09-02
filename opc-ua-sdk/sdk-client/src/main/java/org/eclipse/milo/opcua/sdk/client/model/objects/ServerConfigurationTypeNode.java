/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.structured.AccessRestrictionType;
import org.eclipse.milo.opcua.stack.core.types.structured.RolePermissionType;

public class ServerConfigurationTypeNode extends BaseObjectTypeNode implements ServerConfigurationType {
    public ServerConfigurationTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                       QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                       UInteger writeMask, UInteger userWriteMask, RolePermissionType[] rolePermissions,
                                       RolePermissionType[] userRolePermissions, AccessRestrictionType accessRestrictions,
                                       UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, rolePermissions, userRolePermissions, accessRestrictions, eventNotifier);
    }

    @Override
    public String[] getServerCapabilities() throws UaException {
        PropertyTypeNode node = getServerCapabilitiesNode();
        return (String[]) node.getValue().getValue().getValue();
    }

    @Override
    public void setServerCapabilities(String[] value) throws UaException {
        PropertyTypeNode node = getServerCapabilitiesNode();
        node.setValue(new Variant(value));
    }

    @Override
    public String[] readServerCapabilities() throws UaException {
        try {
            return readServerCapabilitiesAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeServerCapabilities(String[] value) throws UaException {
        try {
            writeServerCapabilitiesAsync(value).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String[]> readServerCapabilitiesAsync() {
        return getServerCapabilitiesNodeAsync()
            .thenCompose(node -> node.readAttributeAsync(AttributeId.Value))
            .thenApply(v -> (String[]) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeServerCapabilitiesAsync(String[] serverCapabilities) {
        DataValue value = DataValue.valueOnly(new Variant(serverCapabilities));
        return getServerCapabilitiesNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getServerCapabilitiesNode() throws UaException {
        try {
            return getServerCapabilitiesNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getServerCapabilitiesNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync(
            "http://opcfoundation.org/UA/",
            "ServerCapabilities",
            ExpandedNodeId.parse("ns=0;i=46"),
            false
        );
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public String[] getSupportedPrivateKeyFormats() throws UaException {
        PropertyTypeNode node = getSupportedPrivateKeyFormatsNode();
        return (String[]) node.getValue().getValue().getValue();
    }

    @Override
    public void setSupportedPrivateKeyFormats(String[] value) throws UaException {
        PropertyTypeNode node = getSupportedPrivateKeyFormatsNode();
        node.setValue(new Variant(value));
    }

    @Override
    public String[] readSupportedPrivateKeyFormats() throws UaException {
        try {
            return readSupportedPrivateKeyFormatsAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSupportedPrivateKeyFormats(String[] value) throws UaException {
        try {
            writeSupportedPrivateKeyFormatsAsync(value).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String[]> readSupportedPrivateKeyFormatsAsync() {
        return getSupportedPrivateKeyFormatsNodeAsync()
            .thenCompose(node -> node.readAttributeAsync(AttributeId.Value))
            .thenApply(v -> (String[]) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeSupportedPrivateKeyFormatsAsync(
        String[] supportedPrivateKeyFormats) {
        DataValue value = DataValue.valueOnly(new Variant(supportedPrivateKeyFormats));
        return getSupportedPrivateKeyFormatsNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getSupportedPrivateKeyFormatsNode() throws UaException {
        try {
            return getSupportedPrivateKeyFormatsNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getSupportedPrivateKeyFormatsNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync(
            "http://opcfoundation.org/UA/",
            "SupportedPrivateKeyFormats",
            ExpandedNodeId.parse("ns=0;i=46"),
            false
        );
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public UInteger getMaxTrustListSize() throws UaException {
        PropertyTypeNode node = getMaxTrustListSizeNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setMaxTrustListSize(UInteger value) throws UaException {
        PropertyTypeNode node = getMaxTrustListSizeNode();
        node.setValue(new Variant(value));
    }

    @Override
    public UInteger readMaxTrustListSize() throws UaException {
        try {
            return readMaxTrustListSizeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMaxTrustListSize(UInteger value) throws UaException {
        try {
            writeMaxTrustListSizeAsync(value).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readMaxTrustListSizeAsync() {
        return getMaxTrustListSizeNodeAsync()
            .thenCompose(node -> node.readAttributeAsync(AttributeId.Value))
            .thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeMaxTrustListSizeAsync(UInteger maxTrustListSize) {
        DataValue value = DataValue.valueOnly(new Variant(maxTrustListSize));
        return getMaxTrustListSizeNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getMaxTrustListSizeNode() throws UaException {
        try {
            return getMaxTrustListSizeNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getMaxTrustListSizeNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync(
            "http://opcfoundation.org/UA/",
            "MaxTrustListSize",
            ExpandedNodeId.parse("ns=0;i=46"),
            false
        );
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Boolean getMulticastDnsEnabled() throws UaException {
        PropertyTypeNode node = getMulticastDnsEnabledNode();
        return (Boolean) node.getValue().getValue().getValue();
    }

    @Override
    public void setMulticastDnsEnabled(Boolean value) throws UaException {
        PropertyTypeNode node = getMulticastDnsEnabledNode();
        node.setValue(new Variant(value));
    }

    @Override
    public Boolean readMulticastDnsEnabled() throws UaException {
        try {
            return readMulticastDnsEnabledAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMulticastDnsEnabled(Boolean value) throws UaException {
        try {
            writeMulticastDnsEnabledAsync(value).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Boolean> readMulticastDnsEnabledAsync() {
        return getMulticastDnsEnabledNodeAsync()
            .thenCompose(node -> node.readAttributeAsync(AttributeId.Value))
            .thenApply(v -> (Boolean) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeMulticastDnsEnabledAsync(Boolean multicastDnsEnabled) {
        DataValue value = DataValue.valueOnly(new Variant(multicastDnsEnabled));
        return getMulticastDnsEnabledNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getMulticastDnsEnabledNode() throws UaException {
        try {
            return getMulticastDnsEnabledNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getMulticastDnsEnabledNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync(
            "http://opcfoundation.org/UA/",
            "MulticastDnsEnabled",
            ExpandedNodeId.parse("ns=0;i=46"),
            false
        );
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public CertificateGroupFolderTypeNode getCertificateGroupsNode() throws UaException {
        try {
            return getCertificateGroupsNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError));
        }
    }

    @Override
    public CompletableFuture<? extends CertificateGroupFolderTypeNode> getCertificateGroupsNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync(
            "http://opcfoundation.org/UA/",
            "CertificateGroups",
            ExpandedNodeId.parse("ns=0;i=47"),
            false
        );
        return future.thenApply(node -> (CertificateGroupFolderTypeNode) node);
    }
}

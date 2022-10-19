/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.objects;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part12/7.8.3/#7.8.3.3">https://reference.opcfoundation.org/v105/Core/docs/Part12/7.8.3/#7.8.3.3</a>
 */
public interface CertificateGroupFolderType extends FolderType {
    CertificateGroupType getDefaultApplicationGroupNode();

    CertificateGroupType getDefaultHttpsGroupNode();

    CertificateGroupType getDefaultUserTokenGroupNode();
}

/*
 * The contents of this file are subject to the terms of the Common Development and
 * Distribution License (the License). You may not use this file except in compliance with the
 * License.
 *
 * You can obtain a copy of the License at legal/CDDLv1.0.txt. See the License for the
 * specific language governing permission and limitations under the License.
 *
 * When distributing Covered Software, include this CDDL Header Notice in each file and include
 * the License file at legal/CDDLv1.0.txt. If applicable, add the following below the CDDL
 * Header, with the fields enclosed by brackets [] replaced by your own identifying
 * information: "Portions copyright [year] [name of copyright owner]".
 *
 * Copyright 2015 ForgeRock AS.
 */

package org.forgerock.selfservice.core.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.forgerock.selfservice.core.StorageType;
import org.forgerock.selfservice.core.snapshot.SnapshotTokenConfig;
import org.forgerock.util.Reject;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the configuration for an instance of the anonymous process service.
 *
 * @param <V>
 *         type that describes the stage config visitor
 *
 * @since 0.1.0
 */
public final class ProcessInstanceConfig<V extends StageConfigVisitor> {

    private List<StageConfig<? super V>> stageConfigs;
    @JsonProperty("snapshotToken")
    private SnapshotTokenConfig snapshotTokenConfig;
    @JsonProperty("storage")
    private StorageType storageType;

    /**
     * Sets the list of stage configs. The order of the list
     * is the order in which the stages will be processed.
     *
     * @param stageConfigs
     *         the list of stage configs
     *
     * @return this config
     */
    public ProcessInstanceConfig<V> setStageConfigs(List<StageConfig<? super V>> stageConfigs) {
        Reject.ifNull(stageConfigs);
        this.stageConfigs = new ArrayList<>(stageConfigs);
        return this;
    }

    /**
     * Gets the list of stage configs.
     *
     * @return the list of stage configs
     */
    public List<StageConfig<? super V>> getStageConfigs() {
        return stageConfigs;
    }

    /**
     * Sets the snapshot token type to use.
     *
     * @param snapshotTokenConfig
     *         the snapshot token config
     *
     * @return this config
     */
    public ProcessInstanceConfig<V> setSnapshotTokenConfig(SnapshotTokenConfig snapshotTokenConfig) {
        Reject.ifNull(snapshotTokenConfig);
        this.snapshotTokenConfig = snapshotTokenConfig;
        return this;
    }

    /**
     * Gets the snapshot token type to use.
     *
     * @return the snapshot token type
     */
    public SnapshotTokenConfig getSnapshotTokenConfig() {
        return snapshotTokenConfig;
    }

    /**
     * Sets the storage type to use. See {@link org.forgerock.selfservice.core.StorageType}.
     *
     * @param storageType
     *         the storage type
     *
     * @return this config
     */
    public ProcessInstanceConfig<V> setStorageType(StorageType storageType) {
        Reject.ifNull(storageType);
        this.storageType = storageType;
        return this;
    }

    /**
     * Gets the storage type to use.
     *
     * @return the storage type
     */
    public StorageType getStorageType() {
        return storageType;
    }

}

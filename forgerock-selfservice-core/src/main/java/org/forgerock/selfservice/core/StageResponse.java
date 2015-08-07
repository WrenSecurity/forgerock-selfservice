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

package org.forgerock.selfservice.core;

import static org.forgerock.selfservice.core.ServiceUtils.EMPTY_TAG;
import static org.forgerock.selfservice.core.ServiceUtils.emptyJson;

import org.forgerock.json.JsonValue;
import org.forgerock.util.Reject;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Stage response represents a response from having invoked a progress stage.
 *
 * @since 0.1.0
 */
public final class StageResponse {

    private final String stageTag;
    private final JsonValue requirements;
    private final Map<String, String> state;

    private StageResponse(Builder builder) {
        stageTag = builder.stageTag;
        requirements = builder.requirements;
        state = builder.state;
    }

    String getStageTag() {
        return stageTag;
    }

    boolean hasRequirements() {
        return requirements.size() > 0;
    }

    JsonValue getRequirements() {
        return requirements;
    }

    Map<String, String> getState() {
        return Collections.unmodifiableMap(state);
    }

    /**
     * Builder assists with the creation of {@link StageResponse} instances.
     */
    public static final class Builder {

        private String stageTag;
        private JsonValue requirements;
        private final Map<String, String> state;

        private Builder() {
            stageTag = EMPTY_TAG;
            requirements = emptyJson();
            state = new HashMap<>();
        }

        /**
         * Sets the stage tag.
         *
         * @param stageTag
         *         the stage tag
         *
         * @return this builder
         */
        public Builder setStageTag(String stageTag) {
            Reject.ifNull(stageTag);
            this.stageTag = stageTag;
            return this;
        }

        /**
         * Sets the json requirements.
         *
         * @param requirements
         *         the json requirements
         *
         * @return this builder
         */
        public Builder setRequirements(JsonValue requirements) {
            Reject.ifNull(requirements);
            this.requirements = requirements;
            return this;
        }

        /**
         * Adds state to be preserved throughout the flow.
         *
         * @param key
         *         state key
         * @param value
         *         corresponding state value
         *
         * @return this builder
         */
        public Builder addState(String key, String value) {
            Reject.ifNull(key, value);
            state.put(key, value);
            return this;
        }

        /**
         * Builds a stage response instance.
         *
         * @return a stage response instance
         */
        public StageResponse build() {
            return new StageResponse(this);
        }

    }

    /**
     * New builder to help construct a stage response.
     *
     * @return new builder
     */
    public static Builder newBuilder() {
        return new Builder();
    }

}
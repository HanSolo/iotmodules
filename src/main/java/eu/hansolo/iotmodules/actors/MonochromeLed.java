/*
 * Copyright (c) 2021 by Gerrit Grunwald
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.hansolo.iotmodules.actors;

import eu.hansolo.iotmodules.tools.Constants.ActorType;
import eu.hansolo.iotmodules.tools.Helper;
import eu.hansolo.properties.IntegerProperty;

import static eu.hansolo.iotmodules.tools.Constants.*;


public class MonochromeLed implements Actor {
    private final String          id;
    private       IntegerProperty intensity;


    // ******************** Constructors **************************************
    public MonochromeLed(final String id) {
        if (null == id || id.isEmpty()) { throw new IllegalArgumentException("Actor ID cannot be null or empty"); }
        this.id        = getActorType().getTypeId() + "_" + id;
        this.intensity = new IntegerProperty(0);
    }


    // ******************** Methods *******************************************
    public String getId() { return id; }

    public ActorType getActorType() { return ActorType.MONOCHROME_LED; }

    public int getIntensity() { return intensity.get(); }
    public void setIntensity(final int intensity) { this.intensity.set(Helper.clamp(0, 255, intensity)); }
    public IntegerProperty intensity() { return intensity; }

    @Override public void dispose() {
        intensity.removeAllListeners();
    }

    @Override public String toJsonString() {
        StringBuilder msgBuilder = new StringBuilder();
        msgBuilder.append(CURLY_BRACKET_OPEN)
                  .append(QUOTES).append(FIELD_ID).append(QUOTES).append(COLON).append(QUOTES).append(getId()).append(QUOTES).append(COMMA)
                  .append(QUOTES).append(FIELD_TYPE).append(QUOTES).append(COLON).append(QUOTES).append(getActorType().getTypeId()).append(QUOTES).append(COMMA)
                  .append(QUOTES).append(FIELD_INTENSITY).append(QUOTES).append(COLON).append(getIntensity())
                  .append(CURLY_BRACKET_CLOSE);
        return msgBuilder.toString();
    }

    @Override public String toString() { return toJsonString(); }
}

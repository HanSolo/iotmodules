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

import eu.hansolo.iotmodules.tools.Helper;
import eu.hansolo.properties.IntegerProperty;

import static eu.hansolo.iotmodules.tools.Constants.*;


public class RgbLed implements Actor {
    private final String          id;
    private       IntegerProperty red;
    private       IntegerProperty green;
    private       IntegerProperty blue;


    // ******************** Constructors **************************************
    public RgbLed(final String id) {
        if (null == id || id.isEmpty()) { throw new IllegalArgumentException("Actor ID cannot be null or empty"); }
        this.id    = getActorType().getTypeId() + "_" + id;
        this.red   = new IntegerProperty(0);
        this.green = new IntegerProperty(0);
        this.blue  = new IntegerProperty(0);
    }


    // ******************** Methods *******************************************
    public String getId() { return id; }

    public ActorType getActorType() { return ActorType.RGB_LED; }

    public int getRed() { return red.get(); }
    public void setRed(final int red) { this.red.set(Helper.clamp(0, 255, red)); }
    public IntegerProperty red() { return red; }

    public int getGreen() { return green.get(); }
    public void setGreen(final int green) { this.green.set(Helper.clamp(0, 255, green)); }
    public IntegerProperty green() { return green; }

    public int getBlue() { return blue.get(); }
    public void setblue(final int blue) { this.blue.set(Helper.clamp(0, 255, blue)); }
    public IntegerProperty blue() { return blue; }

    public void set(final int red, final int green, final int blue) {
        this.red.set(Helper.clamp(0, 255, red));
        this.green.set(Helper.clamp(0, 255, green));
        this.blue.set(Helper.clamp(0, 255, blue));
    }


    @Override public void dispose() {
        red.removeAllListeners();
        green.removeAllListeners();
        blue.removeAllListeners();
    }

    @Override public String toJsonString() {
        StringBuilder msgBuilder = new StringBuilder();
        msgBuilder.append(CURLY_BRACKET_OPEN)
                  .append(QUOTES).append(FIELD_ID).append(QUOTES).append(COLON).append(QUOTES).append(getId()).append(QUOTES).append(COMMA)
                  .append(QUOTES).append(FIELD_TYPE).append(QUOTES).append(COLON).append(QUOTES).append(getActorType().getTypeId()).append(QUOTES).append(COMMA)
                  .append(QUOTES).append(FIELD_RED).append(QUOTES).append(COLON).append(getRed()).append(COMMA)
                  .append(QUOTES).append(FIELD_GREEN).append(QUOTES).append(COLON).append(getGreen()).append(COMMA)
                  .append(QUOTES).append(FIELD_BLUE).append(QUOTES).append(COLON).append(getBlue())
                  .append(CURLY_BRACKET_CLOSE);
        return msgBuilder.toString();
    }

    @Override public String toString() { return toJsonString(); }
}

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


public class Led implements Actor {
    public enum Type { RGB, MONOCHROME }

    private final String          id;
    private final Type            type;
    private       IntegerProperty red;
    private       IntegerProperty green;
    private       IntegerProperty blue;
    private       IntegerProperty intensity;


    // ******************** Constructors **************************************
    public Led(final String id, final Type type) {
        if (null == id || id.isEmpty()) { throw new IllegalArgumentException("Actor ID cannot be null or empty"); }
        if (null == type) { throw new IllegalArgumentException("Type cannot be null"); }
        this.id        = id;
        this.type      = type;
        this.red       = new IntegerProperty(0);
        this.green     = new IntegerProperty(0);
        this.blue      = new IntegerProperty(0);
        this.intensity = new IntegerProperty(0);
    }


    // ******************** Methods *******************************************
    public String getId() { return id; }

    public Type getType() { return type; }

    public int getRed() { return red.get(); }
    public void setRed(final int red) { set(red, getGreen(), getBlue()); }
    public IntegerProperty red() { return red; }

    public int getGreen() { return green.get(); }
    public void setGreen(final int green) { set(getRed(), green, getBlue()); }
    public IntegerProperty green() { return green; }

    public int getBlue() { return blue.get(); }
    public void setblue(final int blue) { set(getRed(), getGreen(), blue); }
    public IntegerProperty blue() { return blue; }

    public void set(final int red, final int green, final int blue) {
        final int r = Helper.clamp(0, 255, red);
        final int g = Helper.clamp(0, 255, green);
        final int b = Helper.clamp(0, 255, blue);
        if (Type.RGB == type) {
            this.red.set(r);
            this.green.set(g);
            this.blue.set(b);
        }
        final int i = Helper.clamp(0, 255, (r + g + b) / 3);
        this.intensity.set(i);
    }

    public int getIntensity() { return intensity.get(); }
    public void set(final int intensity) {
        final int i = Helper.clamp(0, 255, intensity);
        this.intensity.set(i);
    }
    public IntegerProperty intensityProperty() { return intensity; }

    @Override public void dispose() {
        red.removeAllListeners();
        green.removeAllListeners();
        blue.removeAllListeners();
        intensity.removeAllListeners();
    }

    @Override public String toJsonString() {
        StringBuilder msgBuilder = new StringBuilder();
        msgBuilder.append(CURLY_BRACKET_OPEN)
                  .append(INDENTED_QUOTES).append(FIELD_ID).append(QUOTES).append(COLON).append(QUOTES).append(getId()).append(QUOTES).append(COMMA_NEW_LINE)
                  .append(INDENTED_QUOTES).append(FIELD_TYPE).append(QUOTES).append(COLON).append(QUOTES).append(type.name()).append(QUOTES).append(COMMA_NEW_LINE)
                  .append(INDENTED_QUOTES).append(FIELD_INTENSITY).append(QUOTES).append(COLON).append(getIntensity()).append(COMMA_NEW_LINE)
                  .append(INDENTED_QUOTES).append(FIELD_RED).append(QUOTES).append(COLON).append(getRed()).append(COMMA_NEW_LINE)
                  .append(INDENTED_QUOTES).append(FIELD_GREEN).append(QUOTES).append(COLON).append(getGreen()).append(COMMA_NEW_LINE)
                  .append(INDENTED_QUOTES).append(FIELD_BLUE).append(QUOTES).append(COLON).append(getBlue()).append(NEW_LINE)
                  .append(CURLY_BRACKET_CLOSE);
        return msgBuilder.toString();
    }

    @Override public String toString() { return toJsonString(); }
}

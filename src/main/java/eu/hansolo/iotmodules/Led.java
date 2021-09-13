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

package eu.hansolo.iotmodules;

import eu.hansolo.iotmodules.tools.Helper;
import eu.hansolo.properties.IntegerProperty;


public class Led implements Actor {
    public enum Type { RGB, MONOCHROME }

    private final Type            type;
    private       IntegerProperty red;
    private       IntegerProperty green;
    private       IntegerProperty blue;
    private       IntegerProperty intensity;


    // ******************** Constructors **************************************
    public Led(final Type type) {
        this.type      = type;
        this.red       = new IntegerProperty(0);
        this.green     = new IntegerProperty(0);
        this.blue      = new IntegerProperty(0);
        this.intensity = new IntegerProperty(0);
    }


    // ******************** Methods *******************************************
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
    public void set(final int intensity) {
        final int i = Helper.clamp(0, 255, intensity);
        this.intensity.set(i);
    }

    @Override public void dispose() {
        red.removeAllListeners();
        green.removeAllListeners();
        blue.removeAllListeners();
        intensity.removeAllListeners();
    }
}

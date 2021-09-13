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

import eu.hansolo.properties.BooleanProperty;


public class Switch implements Actor {
    public enum Type { PUSH_BUTTON, TOGGLE_BUTTON }

    private final Type            type;
    private       BooleanProperty on;


    // ******************** Constructors **************************************
    public Switch(final Type type) {
        this.type = type;
        on = new BooleanProperty(false) {
            @Override protected void willChange(final Boolean oldValue, final Boolean newValue) {}
            @Override protected void didChange(final Boolean oldValue, final Boolean newValue) {}
        };
    }


    // ******************** Methods *******************************************
    public BooleanProperty onProperty() { return on; }

    @Override public void dispose() {
        on.removeAllListeners();
    }
}

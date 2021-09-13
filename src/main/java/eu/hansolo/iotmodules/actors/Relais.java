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

import eu.hansolo.properties.ObjectProperty;

import static eu.hansolo.iotmodules.tools.Constants.*;


public class Relais implements Actor {
    public enum State { OPEN, CLOSED }

    private final String                id;
    private       ObjectProperty<State> state;


    // ******************** Constructors **************************************
    public Relais(final String id) {
        this(id, State.OPEN);
    }
    public Relais(final String id, final State state) {
        if (null == id || id.isEmpty()) { throw new IllegalArgumentException("Actor ID cannot be null or empty"); }
        if (null == state) { throw new IllegalArgumentException("State cannot be null"); }
        this.id    = id;
        this.state = new ObjectProperty<>(state);
    }


    // ******************** Methods *******************************************
    public String getId() { return id; }

    public State getState() { return state.get(); }
    public void setState(final State state) {
        if (null == state) { throw new IllegalArgumentException("State cannot be null"); }
        this.state.set(state);
    }
    public ObjectProperty<State> stateProperty() { return state; }


    @Override public void dispose() {
        state.removeAllListeners();
    }

    @Override public String toJsonString() {
        StringBuilder msgBuilder = new StringBuilder();
        msgBuilder.append(CURLY_BRACKET_OPEN)
                  .append(INDENTED_QUOTES).append(FIELD_ID).append(QUOTES).append(COLON).append(QUOTES).append(getId()).append(QUOTES).append(COMMA_NEW_LINE)
                  .append(INDENTED_QUOTES).append(FIELD_STATE).append(QUOTES).append(COLON).append(QUOTES).append(state.getName()).append(QUOTES).append(NEW_LINE)
                  .append(CURLY_BRACKET_CLOSE);
        return msgBuilder.toString();
    }

    @Override public String toString() { return toJsonString(); }
}

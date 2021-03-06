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

import eu.hansolo.properties.BooleanProperty;

import static eu.hansolo.iotmodules.tools.Constants.*;


public class Relais implements Actor {
    private final String          id;
    private       BooleanProperty closed;


    // ******************** Constructors **************************************
    public Relais(final String id) {
        this(id, false);
    }
    public Relais(final String id, final boolean closed) {
        if (null == id || id.isEmpty()) { throw new IllegalArgumentException("Actor ID cannot be null or empty"); }
        this.id     = getActorType().getTypeId() + "_" + id;
        this.closed = new BooleanProperty(closed);
    }


    // ******************** Methods *******************************************
    public String getId() { return id; }

    public ActorType getActorType() { return ActorType.RELAIS; }

    public boolean isClosed() { return closed.get(); }
    public void setClosed(final boolean closed) { this.closed.set(closed); }
    public BooleanProperty closedProperty() { return closed; }


    @Override public void dispose() {
        closed.removeAllListeners();
    }

    @Override public String toJsonString() {
        StringBuilder msgBuilder = new StringBuilder();
        msgBuilder.append(CURLY_BRACKET_OPEN)
                  .append(QUOTES).append(FIELD_ID).append(QUOTES).append(COLON).append(QUOTES).append(getId()).append(QUOTES).append(COMMA)
                  .append(QUOTES).append(FIELD_TYPE).append(QUOTES).append(COLON).append(QUOTES).append(getActorType().getTypeId()).append(QUOTES).append(COMMA)
                  .append(QUOTES).append(FIELD_VALUE).append(QUOTES).append(COLON).append(isClosed())
                  .append(CURLY_BRACKET_CLOSE);
        return msgBuilder.toString();
    }

    @Override public String toString() { return toJsonString(); }
}

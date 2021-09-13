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

import eu.hansolo.iotmodules.actors.Actor;
import eu.hansolo.iotmodules.sensors.Sensor;

import java.util.List;
import java.util.stream.Collectors;

import static eu.hansolo.iotmodules.tools.Constants.*;

public abstract class IotModule {

    public abstract void init();

    public abstract void registerListeners();

    public abstract String getId();

    public abstract List<Sensor> getSensors();

    public abstract List<Actor> getActors();

    @Override public String toString() {
        StringBuilder msgBuilder = new StringBuilder();
        msgBuilder.append(CURLY_BRACKET_OPEN).append(NEW_LINE)
                  .append(INDENTED_QUOTES).append(FIELD_MODULE_ID).append(QUOTES).append(COLON).append(QUOTES).append(getId()).append(QUOTES).append(COMMA_NEW_LINE)
                  .append(INDENTED_QUOTES).append(FIELD_SENSORS).append(QUOTES).append(COLON)
                  .append(getSensors().parallelStream().map(sensor -> sensor.toJsonString()).collect(Collectors.joining(COMMA_NEW_LINE, SQUARE_BRACKET_OPEN, SQUARE_BRACKET_CLOSE)))
                  .append(COMMA_NEW_LINE)
                  .append(INDENTED_QUOTES).append(FIELD_ACTORS).append(QUOTES).append(COLON)
                  .append(getActors().parallelStream().map(actor -> actor.toJsonString()).collect(Collectors.joining(COMMA_NEW_LINE, SQUARE_BRACKET_OPEN, SQUARE_BRACKET_CLOSE)))
                  .append(NEW_LINE)
                  .append(CURLY_BRACKET_CLOSE);
        return msgBuilder.toString();
    }
}

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
import eu.hansolo.iotmodules.mqtt.MqttManager;
import eu.hansolo.iotmodules.sensors.Sensor;
import eu.hansolo.iotmodules.tools.Helper;
import eu.hansolo.properties.BooleanProperty;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import static eu.hansolo.iotmodules.tools.Constants.*;

public abstract class IotModule {
    protected final String          id;
    protected final List<Sensor>    sensors;
    protected final List<Actor>     actors;
    protected final BooleanProperty connected;
    protected       MqttManager     mqttManager;


    // ******************** Constructors **************************************
    public IotModule() {
        this.id        = Helper.createId();
        this.sensors   = new CopyOnWriteArrayList<>();
        this.actors    = new CopyOnWriteArrayList<>();
        this.connected = new BooleanProperty(false);
    }


    // ******************** Initialization ************************************
    public abstract void init();

    public abstract void registerListeners();


    // ******************** Methods *******************************************
    public final String getId() { return id; };

    public boolean isConnected() { return connected.get(); }
    public void setConnected(final boolean connected) { this.connected.set(connected); }
    public BooleanProperty connectedProperty() { return connected; }

    public final List<Sensor> getSensors() { return sensors; }

    public final List<Actor> getActors() { return actors; }

    public void reboot() {}

    public void shutdown() {};

    public void dispose() {
        connected.removeAllListeners();
    }

    public String toJsonString() {
        StringBuilder msgBuilder = new StringBuilder();
        msgBuilder.append(CURLY_BRACKET_OPEN)
                  .append(QUOTES).append(FIELD_MODULE_ID).append(QUOTES).append(COLON).append(QUOTES).append(getId()).append(QUOTES).append(COMMA)
                  .append(QUOTES).append(FIELD_TIMESTAMP).append(QUOTES).append(COLON).append(Instant.now().getEpochSecond()).append(COMMA)
                  .append(QUOTES).append(FIELD_CONNECTED).append(QUOTES).append(COLON).append(isConnected()).append(COMMA)
                  .append(QUOTES).append(FIELD_SENSORS).append(QUOTES).append(COLON)
                  .append(getSensors().stream().map(sensor -> sensor.toJsonString()).collect(Collectors.joining(COMMA, SQUARE_BRACKET_OPEN, SQUARE_BRACKET_CLOSE)))
                  .append(COMMA)
                  .append(QUOTES).append(FIELD_ACTORS).append(QUOTES).append(COLON)
                  .append(getActors().stream().map(actor -> actor.toJsonString()).collect(Collectors.joining(COMMA, SQUARE_BRACKET_OPEN, SQUARE_BRACKET_CLOSE)))
                  .append(CURLY_BRACKET_CLOSE);
        return msgBuilder.toString();
    }

    @Override public String toString() {
        return toJsonString();
    }
}

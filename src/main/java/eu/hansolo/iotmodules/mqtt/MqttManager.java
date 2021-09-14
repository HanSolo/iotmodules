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

package eu.hansolo.iotmodules.mqtt;

import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.MqttClientState;
import com.hivemq.client.mqtt.datatypes.MqttQos;
import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient;
import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5PublishResult;
import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5WillPublish;
import com.hivemq.client.mqtt.mqtt5.message.subscribe.suback.Mqtt5SubAck;
import com.hivemq.client.mqtt.mqtt5.message.unsubscribe.Mqtt5Unsubscribe;
import eu.hansolo.iotmodules.PropertyManager;
import eu.hansolo.iotmodules.event.MqttEvt;
import eu.hansolo.iotmodules.event.MqttEvtObserver;
import eu.hansolo.iotmodules.tools.Constants;
import eu.hansolo.properties.BooleanProperty;
import eu.hansolo.properties.ReadOnlyBooleanProperty;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.hivemq.client.mqtt.MqttGlobalPublishFilter.ALL;
import static java.nio.charset.StandardCharsets.UTF_8;


public class MqttManager {
    private static final String LAST_WILL_TOPIC = new StringBuilder().append(PropertyManager.INSTANCE.getString(Constants.MQTT_TOPIC_PRESENCE))
                                                                     .append(Constants.MQTT_TOPIC_SEPARATOR)
                                                                     .append(PropertyManager.INSTANCE.getString(Constants.CLIENT_ID))
                                                                     .toString();
    private Mqtt5AsyncClient      asyncClient;
    private BooleanProperty       connected;
    private List<MqttEvtObserver> observers;
    private String                lastWillPayload;


    public MqttManager(final String lastWillPayload) {
        if (null == lastWillPayload || lastWillPayload.isBlank()) { throw new IllegalArgumentException("lastWillPayload cannot be empty or null"); }
        this.connected       = new BooleanProperty(MqttManager.this, "connected", false);
        this.observers       = new CopyOnWriteArrayList<>();
        this.lastWillPayload = lastWillPayload;
        asyncClient          = MqttClient.builder()
                                         .useMqttVersion5()
                                         .serverHost(PropertyManager.INSTANCE.getString(Constants.MQTT_HOST))
                                         .serverPort(PropertyManager.INSTANCE.getInt(Constants.MQTT_PORT))
                                         .sslWithDefaultConfig()
                                         .buildAsync();

        init();
    }


    private void init() {
        connect(true);
    }


    public boolean isConnected() { return connected.get(); }
    public ReadOnlyBooleanProperty connectedProperty() { return connected; }

    public CompletableFuture<Mqtt5PublishResult> publish(final String topic, final MqttQos qos, final boolean retain, final String msg) {
        if (null == asyncClient || !asyncClient.getState().isConnected()) { connect(false); }
        return asyncClient.publishWith()
                          .topic(topic)
                          .qos(qos)
                          .retain(retain)
                          .payload(UTF_8.encode(msg))
                          .send();
    }

    public CompletableFuture<Mqtt5SubAck> subscribe(final String topic, final MqttQos qos) {
        if (null == asyncClient || !asyncClient.getState().isConnected()) { connect(false); }
        return asyncClient.subscribeWith()
                          .topicFilter(topic)
                          .qos(qos)
                          .send();
    }

    public void unsubscribe(final String topic) {
        if (null == asyncClient || !asyncClient.getState().isConnected()) { connect(false); }
        asyncClient.unsubscribe(Mqtt5Unsubscribe.builder().topicFilter(topic).build());
    }


    private void connect(final boolean cleanStart) {
        if (null == asyncClient) {
            asyncClient = MqttClient.builder()
                                    .useMqttVersion5()
                                    .serverHost(PropertyManager.INSTANCE.getString(Constants.MQTT_HOST))
                                    .serverPort(PropertyManager.INSTANCE.getInt(Constants.MQTT_PORT))
                                    .sslWithDefaultConfig()
                                    .buildAsync();
        }

        if (!asyncClient.getState().isConnected() && MqttClientState.CONNECTING != asyncClient.getState()) {
            asyncClient.connectWith()
                       .cleanStart(cleanStart)
                       .noSessionExpiry()
                       .keepAlive(60)
                       .simpleAuth()
                       .username(PropertyManager.INSTANCE.getString(Constants.MQTT_USER))
                       .password(UTF_8.encode(PropertyManager.INSTANCE.getString(Constants.MQTT_PW)))
                       .applySimpleAuth()
                       .willPublish(Mqtt5WillPublish.builder()
                                                    .topic(LAST_WILL_TOPIC)
                                                    .qos(MqttQos.EXACTLY_ONCE)
                                                    .payload(lastWillPayload.getBytes(UTF_8))
                                                    .build())
                       .send()
                       .whenComplete((connAck, throwable) -> connected.set(null == throwable));
        }
        asyncClient.toAsync().publishes(ALL, publish -> {
            fireMqttEvent(new MqttEvt(publish.getTopic().toString(), UTF_8.decode(publish.getPayload().get()).toString()));

            //disconnect the client after a message was received
            //client.disconnect();
        });
    }


    public void addMqttObserver(final MqttEvtObserver observer) {
        if (observers.contains(observer)) { return; }
        observers.add(observer);
    }
    public void removeMqttObserver(final MqttEvtObserver observer) {
        if (observers.contains(observer)) { observers.remove(observer); }
    }
    private void fireMqttEvent(final MqttEvt evt) {
        observers.forEach(observer -> observer.handleEvt(evt));
    }
}

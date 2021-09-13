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

package eu.hansolo.iotmodules.sensors;

import eu.hansolo.evt.Evt;
import eu.hansolo.evt.EvtObserver;
import eu.hansolo.evt.EvtPriority;
import eu.hansolo.evt.EvtType;
import eu.hansolo.evt.example.MyEvt;
import eu.hansolo.iotmodules.event.SensorEvt;
import eu.hansolo.iotmodules.event.TemperatureSensorEvt;
import eu.hansolo.properties.DoubleProperty;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static eu.hansolo.iotmodules.tools.Constants.*;


public class TemperatureSensor implements Sensor {
    private final Map<String, List<EvtObserver>> observers = new ConcurrentHashMap<>();
    private final String                         id;
    private       double                         _temperature;
    private       DoubleProperty                 temperature;


    // ******************** Constructors **************************************
    public TemperatureSensor(final String id) {
        if (null == id || id.isEmpty()) { throw new IllegalArgumentException("Sensor ID cannot be null or empty"); }
        this.id           = id;
        this._temperature = 0;
    }


    // ******************** Methods *******************************************
    public String getId() { return id; }

    public double getTemperature() { return null == temperature ? _temperature : temperature.get(); }
    public void setTemperature(final double value) {
        if (null == temperature) {
            _temperature = value;
            fireEvt(new TemperatureSensorEvt(TemperatureSensor.this, TemperatureSensorEvt.TEMPERATURE, value, EvtPriority.NORMAL));
        } else {
            temperature.set(value);
        }
    }
    public DoubleProperty temperatureProperty() {
        if (null == temperature) {
            temperature = new DoubleProperty() {
                @Override protected void willChange(final Double oldValue, final Double newValue) {}
                @Override protected void didChange(final Double oldValue, final Double newValue) {
                    fireEvt(new TemperatureSensorEvt(TemperatureSensor.this, TemperatureSensorEvt.TEMPERATURE, newValue, EvtPriority.NORMAL));
                }
            };
        }
        return temperature;
    }

    public void triggerMeasurement() {

    }


    // ******************** EventHandling *************************************
    public void addOnEvt(final EvtType<? extends TemperatureSensorEvt> type, final EvtObserver observer) {
        if (!observers.keySet().contains(type.getName())) { observers.put(type.getName(), new CopyOnWriteArrayList<>()); }
        if (!observers.get(type.getName()).contains(observer)) { observers.get(type.getName()).add(observer); }
    }
    public void removeOnEvt(final EvtType<? extends TemperatureSensorEvt> type, final EvtObserver observer) {
        if (!observers.keySet().contains(type.getName())) { return; }
        if (observers.get(type.getName()).contains(observer)) { observers.get(type.getName()).remove(observer); }
    }
    public void removeAllObservers() { observers.entrySet().forEach(entry -> entry.getValue().clear()); }

    @Override public void fireEvt(final SensorEvt evt) {
        final EvtType<? extends Evt> type = evt.getEvtType();
        if (observers.containsKey(type.getName())) {
            observers.get(type.getName()).forEach(observer -> observer.handle(evt));
        }
        if (observers.keySet().contains(MyEvt.ANY.getName())) {
            observers.get(MyEvt.ANY.getName()).forEach(observer -> observer.handle(evt));
        }
    }

    @Override public void dispose() {
        removeAllObservers();
        if (null != temperature) { temperature.removeAllListeners(); }
    }

    @Override public String toJsonString() {
        StringBuilder msgBuilder = new StringBuilder();
        msgBuilder.append(CURLY_BRACKET_OPEN)
                  .append(INDENTED_QUOTES).append(FIELD_ID).append(QUOTES).append(COLON).append(QUOTES).append(getId()).append(QUOTES).append(COMMA_NEW_LINE)
                  .append(INDENTED_QUOTES).append(FIELD_TYPE).append(QUOTES).append(COLON).append(QUOTES).append(TYPE_TEMPERATURE).append(QUOTES).append(COMMA_NEW_LINE)
                  .append(INDENTED_QUOTES).append(FIELD_TEMPERATURE).append(QUOTES).append(COLON).append(getTemperature()).append(NEW_LINE)
                  .append(CURLY_BRACKET_CLOSE);
        return msgBuilder.toString();
    }

    @Override public String toString() { return toJsonString(); }
}

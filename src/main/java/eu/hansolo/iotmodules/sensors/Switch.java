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
import eu.hansolo.evt.EvtType;
import eu.hansolo.evt.example.MyEvt;
import eu.hansolo.iotmodules.event.SensorEvt;
import eu.hansolo.iotmodules.event.SwitchEvt;
import eu.hansolo.properties.BooleanProperty;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static eu.hansolo.iotmodules.tools.Constants.*;


public class Switch implements Sensor {
    private final Map<String, List<EvtObserver>> observers = new ConcurrentHashMap<>();
    private final String                         id;
    private       boolean                        _on;
    private       BooleanProperty                on;


    // ******************** Constructors **************************************
    public Switch(final String id) {
        if (null == id || id.isEmpty()) { throw new IllegalArgumentException("Sensor ID cannot be null or empty"); }
        this.id   = getSensorType().getTypeId() + "_" + id;
        this._on  = false;
    }


    // ******************** Methods *******************************************
    public String getId() { return id; }

    public SensorType getSensorType() { return SensorType.SWITCH; }

    public boolean isOn() { return null == on ? _on : on.get(); }
    public void setOn(final boolean ON) {
        if (null == on) {
            _on = ON;
            fireEvt(new SwitchEvt(Switch.this, ON ? SwitchEvt.ON : SwitchEvt.OFF, ON));
        } else {
            on.set(ON);
        }
    }
    public BooleanProperty onProperty() {
        if (null == on) {
            on = new BooleanProperty(false) {
                @Override protected void willChange(final Boolean oldValue, final Boolean newValue) {}
                @Override protected void didChange(final Boolean oldValue, final Boolean newValue) {
                    fireEvt(new SwitchEvt(Switch.this, newValue ? SwitchEvt.ON : SwitchEvt.OFF, newValue));
                }
            };
        }
        return on;
    }

    public void triggerMeasurement() {

    }

    // ******************** EventHandling *************************************
    public void addOnEvt(final EvtType<? extends SwitchEvt> type, final EvtObserver observer) {
        if (!observers.keySet().contains(type.getName())) { observers.put(type.getName(), new CopyOnWriteArrayList<>()); }
        if (!observers.get(type.getName()).contains(observer)) { observers.get(type.getName()).add(observer); }
    }
    public void removeOnEvt(final EvtType<? extends SwitchEvt> type, final EvtObserver observer) {
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
        on.removeAllListeners();
    }

    @Override public String toJsonString() {
        StringBuilder msgBuilder = new StringBuilder();
        msgBuilder.append(CURLY_BRACKET_OPEN)
                  .append(QUOTES).append(FIELD_ID).append(QUOTES).append(COLON).append(QUOTES).append(getId()).append(QUOTES).append(COMMA)
                  .append(QUOTES).append(FIELD_TYPE).append(QUOTES).append(COLON).append(QUOTES).append(getSensorType().getTypeId()).append(QUOTES).append(COMMA)
                  .append(QUOTES).append(FIELD_VALUE).append(QUOTES).append(COLON).append(isOn())
                  .append(CURLY_BRACKET_CLOSE);
        return msgBuilder.toString();
    }

    @Override public String toString() { return toJsonString(); }
}

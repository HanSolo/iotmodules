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

package eu.hansolo.iotmodules.event;

import eu.hansolo.evt.EvtPriority;
import eu.hansolo.evt.EvtType;


public class TemperatureSensorEvt extends SensorEvt {
    public static final EvtType<TemperatureSensorEvt> ANY                 = new EvtType<>(SensorEvt.ANY, "ANY_TEMPERATURE");
    public static final EvtType<TemperatureSensorEvt> TEMPERATURE         = new EvtType<>(TemperatureSensorEvt.ANY, "TEMPERATURE");
    public static final EvtType<TemperatureSensorEvt> OUTSIDE_TEMPERATURE = new EvtType<>(TemperatureSensorEvt.ANY, "OUTSIDE_TEMPERATURE");
    public static final EvtType<TemperatureSensorEvt> INSIDE_TEMPERATURE  = new EvtType<>(TemperatureSensorEvt.ANY, "INSIDE_TEMPERATURE");


    // ******************** Constructors **************************************
    public TemperatureSensorEvt(final Object source, final EvtType<? extends SensorEvt> evtType, final Double temperature) {
        super(source, evtType, temperature);
    }
    public TemperatureSensorEvt(final Object source, final EvtType<? extends SensorEvt> evtType, final Double temperature, final EvtPriority priority) {
        super(source, evtType, temperature, priority);
    }


    // ******************** Methods *******************************************
    public Double getTemperature() { return (Double) super.getData(); }
}

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


public class PressureSensorEvt extends SensorEvt {
    public static final EvtType<PressureSensorEvt> ANY              = new EvtType<>(SensorEvt.ANY, "ANY_PRESSURE");
    public static final EvtType<PressureSensorEvt> PRESSURE         = new EvtType<>(PressureSensorEvt.ANY, "PRESSURE");
    public static final EvtType<PressureSensorEvt> OUTSIDE_PRESSURE = new EvtType<>(PressureSensorEvt.ANY, "OUTSIDE_PRESSURE");
    public static final EvtType<PressureSensorEvt> INSIDE_PRESSURE  = new EvtType<>(PressureSensorEvt.ANY, "INSIDE_PRESSURE");


    // ******************** Constructors **************************************
    public PressureSensorEvt(final Object source, final EvtType<? extends SensorEvt> evtType, final Double pressure) {
        super(source, evtType, pressure);
    }
    public PressureSensorEvt(final Object source, final EvtType<? extends SensorEvt> evtType, final Double pressure, final EvtPriority priority) {
        super(source, evtType, pressure, priority);
    }


    // ******************** Methods *******************************************
    public Double getPressure() { return (Double) super.getData(); }
}

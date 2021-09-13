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


public class VoltageSensorEvt extends SensorEvt {
    public static final EvtType<VoltageSensorEvt> ANY     = new EvtType<>(SensorEvt.ANY, "ANY_VOLTAGE");
    public static final EvtType<VoltageSensorEvt> VOLTAGE = new EvtType<>(VoltageSensorEvt.ANY, "VOLTAGE");


    // ******************** Constructors **************************************
    public VoltageSensorEvt(final Object source, final EvtType<? extends SensorEvt> evtType, final Double voltage) {
        super(source, evtType, voltage);
    }
    public VoltageSensorEvt(final Object source, final EvtType<? extends SensorEvt> evtType, final Double voltage, final EvtPriority priority) {
        super(source, evtType, voltage, priority);
    }


    // ******************** Methods *******************************************
    public Double getHumidity() { return (Double) super.getData(); }
}

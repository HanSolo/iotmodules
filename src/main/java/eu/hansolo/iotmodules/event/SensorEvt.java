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


public class SensorEvt extends IotModuleEvt {
    public static final EvtType<SensorEvt> ANY = new EvtType<>(IotModuleEvt.ANY, "SENSOR");

    private Object data;

    // ******************** Constructors **************************************
    public SensorEvt(final Object source, final EvtType<? extends SensorEvt> evtType, final Object data) { this(source, evtType, data, EvtPriority.NORMAL); }
    public SensorEvt(final Object source, final EvtType<? extends SensorEvt> evtType, final Object data, final EvtPriority priority) {
        super(source, evtType, priority);
        this.data = data;
    }


    // ******************** Methods *******************************************
    public EvtType<? extends SensorEvt> getEvtType() { return (EvtType<? extends SensorEvt>) super.getEvtType(); }

    public Object getData() { return data; }
}

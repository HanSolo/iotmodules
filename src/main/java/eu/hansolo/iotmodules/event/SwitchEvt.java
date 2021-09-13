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


public class SwitchEvt extends SensorEvt {
    public static final EvtType<SwitchEvt> ANY = new EvtType<>(SensorEvt.ANY, "ANY_STATE");
    public static final EvtType<SwitchEvt> ON  = new EvtType<>(SwitchEvt.ANY, "ON");
    public static final EvtType<SwitchEvt> OFF = new EvtType<>(SwitchEvt.ANY, "OFF");


    // ******************** Constructors **************************************
    public SwitchEvt(final Object source, final EvtType<? extends SensorEvt> evtType, final Boolean state) {
        super(source, evtType, state);
    }
    public SwitchEvt(final Object source, final EvtType<? extends SensorEvt> evtType, final Boolean state, final EvtPriority priority) {
        super(source, evtType, state, priority);
    }


    // ******************** Methods *******************************************
    public Boolean isOn() { return (boolean) super.getData(); }
}

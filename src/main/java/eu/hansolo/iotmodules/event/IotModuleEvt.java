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

import eu.hansolo.evt.Evt;
import eu.hansolo.evt.EvtPriority;
import eu.hansolo.evt.EvtType;


public class IotModuleEvt extends Evt {
    public static final EvtType<IotModuleEvt> ANY = new EvtType<IotModuleEvt>(Evt.ANY, "IOT_MODULE_EVENT");


    // ******************** Constructors **************************************
    public IotModuleEvt(final EvtType<? extends IotModuleEvt> evtType) {
        super(evtType);
    }
    public IotModuleEvt(final Object source, final EvtType<? extends IotModuleEvt> evtType) {
        super(source, evtType);
    }
    public IotModuleEvt(final Object source, final EvtType<? extends IotModuleEvt> evtType, final EvtPriority priority) {
        super(source, evtType, priority);
    }


    // ******************** Methods *******************************************
    @Override public EvtType<? extends IotModuleEvt> getEvtType() {
        return (EvtType<? extends IotModuleEvt>) super.getEvtType();
    }
}

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


public class AirQualitySensorEvt extends SensorEvt {
    public static final EvtType<AirQualitySensorEvt> ANY                 = new EvtType<>(SensorEvt.ANY, "ANY_AIR_QUALITY");
    public static final EvtType<AirQualitySensorEvt> AIR_QUALITY         = new EvtType<>(AirQualitySensorEvt.ANY, "AIR_QUALITY");
    public static final EvtType<AirQualitySensorEvt> OUTSIDE_AIR_QUALITY = new EvtType<>(AirQualitySensorEvt.ANY, "OUTSIDE_AIR_QUALITY");
    public static final EvtType<AirQualitySensorEvt> INSIDE_AIR_QUALITY  = new EvtType<>(AirQualitySensorEvt.ANY, "INSIDE_AIR_QUALITY");


    // ******************** Constructors **************************************
    public AirQualitySensorEvt(final Object source, final EvtType<? extends SensorEvt> evtType, final Double airQuality) {
        super(source, evtType, airQuality);
    }
    public AirQualitySensorEvt(final Object source, final EvtType<? extends SensorEvt> evtType, final Double airQuality, final EvtPriority priority) {
        super(source, evtType, airQuality, priority);
    }


    // ******************** Methods *******************************************
    public Double getAirQuality() { return (Double) super.getData(); }
}

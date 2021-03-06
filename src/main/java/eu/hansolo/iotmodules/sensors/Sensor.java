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

import eu.hansolo.iotmodules.event.SensorEvt;
import eu.hansolo.iotmodules.tools.Constants.SensorType;


public interface Sensor {

    String getId();

    SensorType getSensorType();

    void triggerMeasurement();

    void fireEvt(final SensorEvt evt);

    void dispose();

    String toJsonString();
}

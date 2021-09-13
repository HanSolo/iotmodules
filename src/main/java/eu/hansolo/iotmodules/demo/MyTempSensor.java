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

package eu.hansolo.iotmodules.demo;

import eu.hansolo.iotmodules.sensors.TemperatureSensor;


public class MyTempSensor extends TemperatureSensor {

    public MyTempSensor(final String id) {
        super(id);
    }

    @Override public void triggerMeasurement() { setTemperature(Math.random() * 100.0); }
}

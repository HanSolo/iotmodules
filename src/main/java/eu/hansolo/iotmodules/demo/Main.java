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

import eu.hansolo.evt.EvtObserver;
import eu.hansolo.iotmodules.event.TemperatureSensorEvt;
import eu.hansolo.iotmodules.sensors.TemperatureSensor;
import eu.hansolo.iotmodules.tools.Converter;
import eu.hansolo.iotmodules.tools.Converter.UnitDefinition;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

import static eu.hansolo.iotmodules.tools.Converter.Category.TEMPERATURE;


public class Main {

    public Main() {
        Converter temperatureConverter = new Converter(TEMPERATURE, UnitDefinition.CELSIUS);

        TemperatureSensor temperatureSensor = new TemperatureSensor("temp_" + UUID.randomUUID());
        EvtObserver<TemperatureSensorEvt> temperatureObserver = evt -> {
            System.out.println("Celsius   : " + evt.getTemperature() + UnitDefinition.CELSIUS.UNIT.getUnitShort());
            System.out.println("Kelvin    : " + temperatureConverter.convertToString(evt.getTemperature(), UnitDefinition.KELVIN));
            System.out.println("Fahrenheit: " + temperatureConverter.convertToString(evt.getTemperature(), UnitDefinition.FAHRENHEIT));
        };
        temperatureSensor.addOnEvt(TemperatureSensorEvt.TEMPERATURE, temperatureObserver);
        temperatureSensor.temperatureProperty().addOnChange(evt -> System.out.println("Temperature changed from " + evt.getOldValue() + " to " + evt.getValue()));

        temperatureSensor.setTemperature(15);

        try {
            System.out.println(InetAddress.getLocalHost().getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        Pizw1 room = new Pizw1();
        System.out.println(room.toString());
    }


    public static void main(String[] args) {
        new Main();
    }
}

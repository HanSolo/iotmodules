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
import eu.hansolo.evt.EvtType;
import eu.hansolo.iotmodules.IotModule;
import eu.hansolo.iotmodules.actors.Actor;
import eu.hansolo.iotmodules.event.SensorEvt;
import eu.hansolo.iotmodules.event.TemperatureSensorEvt;
import eu.hansolo.iotmodules.sensors.Sensor;
import eu.hansolo.iotmodules.sensors.TemperatureSensor;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Room extends IotModule implements EvtObserver<TemperatureSensorEvt> {
    private final String                   id = "room_" + UUID.randomUUID();
    private       ScheduledExecutorService service;
    private       List<Sensor>             sensors;
    private       List<Actor>              actors;
    private       MyTempSensor             innerTemperature;
    private       MyTempSensor             outerTemperature;


    public Room() {
        service          = Executors.newScheduledThreadPool(1);
        sensors          = new CopyOnWriteArrayList<>();
        actors           = new CopyOnWriteArrayList<>();
        innerTemperature = new MyTempSensor("inner_temp_" + UUID.randomUUID());
        outerTemperature = new MyTempSensor("outer_temp_" + UUID.randomUUID());

        init();
        registerListeners();
    }

    public void init() {
        sensors.add(innerTemperature);
        sensors.add(outerTemperature);

        service.scheduleAtFixedRate(() -> innerTemperature.triggerMeasurement(), 5, 5, TimeUnit.SECONDS);
        service.scheduleAtFixedRate(() -> outerTemperature.triggerMeasurement(), 5, 5, TimeUnit.SECONDS);
    }

    public void registerListeners() {
        innerTemperature.addOnEvt(TemperatureSensorEvt.TEMPERATURE, this);
        outerTemperature.addOnEvt(TemperatureSensorEvt.TEMPERATURE, this);
    }


    @Override public String getId() { return id; }

    @Override public List<Sensor> getSensors() { return sensors; }

    @Override public List<Actor> getActors() { return actors; }

    @Override public void handle(final TemperatureSensorEvt event) {
        EvtType<? extends SensorEvt> type = event.getEvtType();
        if (type.equals(TemperatureSensorEvt.TEMPERATURE)) {
            TemperatureSensor sensor = (TemperatureSensor) event.getSource();
            System.out.println("temperature: " + sensor.getId() + ": " + event.getTemperature());
        }
    }
}

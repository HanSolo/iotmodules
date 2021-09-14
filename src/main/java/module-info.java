module eu.hansolo.iotmodules {
    // Java
    requires java.base;

    // 3rd Party
    requires eu.hansolo.evt;
    requires eu.hansolo.properties;
    requires com.hivemq.client.mqtt;
    requires jdk.unsupported;

    exports eu.hansolo.iotmodules;
    exports eu.hansolo.iotmodules.tools;
    exports eu.hansolo.iotmodules.event;
    exports eu.hansolo.iotmodules.sensors;
    exports eu.hansolo.iotmodules.actors;
    exports eu.hansolo.iotmodules.demo;
}
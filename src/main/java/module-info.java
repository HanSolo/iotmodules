module eu.hansolo.iotmodules {
    // Java
    requires java.base;

    // 3rd Party
    requires transitive eu.hansolo.evt;
    requires transitive eu.hansolo.properties;
    requires transitive com.hivemq.client.mqtt;
    requires transitive jdk.unsupported;
    requires transitive com.google.gson;

    exports eu.hansolo.iotmodules;
    exports eu.hansolo.iotmodules.tools;
    exports eu.hansolo.iotmodules.event;
    exports eu.hansolo.iotmodules.sensors;
    exports eu.hansolo.iotmodules.actors;
    exports eu.hansolo.iotmodules.mqtt;
    exports eu.hansolo.iotmodules.demo;
}
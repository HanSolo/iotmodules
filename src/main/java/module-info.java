module eu.hansolo.iotmodules {
    // Java
    requires java.base;

    // Java-FX
    requires eu.hansolo.evt;
    requires eu.hansolo.properties;

    exports eu.hansolo.iotmodules;
    exports eu.hansolo.iotmodules.tools;
    exports eu.hansolo.iotmodules.event;
    exports eu.hansolo.iotmodules.sensors;
    exports eu.hansolo.iotmodules.actors;
}
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

package eu.hansolo.iotmodules.tools;

public class Constants {
    public static final String PROPERTIES_FILE_NAME            = "iotmodules.properties";

    public static final String PROPERTY_CLIENT_ID              = "CLIENT_ID";
    public static final String PROPERTY_MQTT_HOST              = "MQTT_HOST";
    public static final String PROPERTY_MQTT_PORT              = "MQTT_PORT";
    public static final String PROPERTY_MQTT_USER              = "MQTT_USER";
    public static final String PROPERTY_MQTT_PW                = "MQTT_PW";
    public static final String PROPERTY_MQTT_TOPIC_PRESENCE    = "TOPIC_PRESENCE";
    public static final String PROPERTY_MQTT_TOPIC_IOT_MODULES = "TOPIC_IOT_MODULES";
    public static final String MQTT_TOPIC_SEPARATOR            = "/";
    public static final String MQTT_PLUS                       = "+";
    public static final String MQTT_IOT_MODULES_TOPIC          = "iotmodules";
    public static final String MQTT_SENSORS_TOPIC              = "sensors";
    public static final String MQTT_ACTORS_TOPIC               = "actors";

    public static final String SQUARE_BRACKET_OPEN             = "[";
    public static final String SQUARE_BRACKET_CLOSE            = "]";
    public static final String CURLY_BRACKET_OPEN              = "{";
    public static final String CURLY_BRACKET_CLOSE             = "}";
    public static final String INDENTED_QUOTES                 = "  \"";
    public static final String QUOTES                          = "\"";
    public static final String COLON                           = ":";
    public static final String COMMA                           = ",";
    public static final String SLASH                           = "/";
    public static final String NEW_LINE                        = "\n";
    public static final String COMMA_NEW_LINE                  = ",\n";
    public static final String INDENT                          = "  ";

    public static final String FIELD_MODULE_ID                 = "module_id";
    public static final String FIELD_SENSOR_ID                 = "sensor_id";
    public static final String FIELD_ACTOR_ID                  = "actor_id";
    public static final String FIELD_CONNECTED                 = "connected";
    public static final String FIELD_TIMESTAMP                 = "timestamp";
    public static final String FIELD_SENSORS                   = "sensors";
    public static final String FIELD_ACTORS                    = "actors";
    public static final String FIELD_ID                        = "id";
    public static final String FIELD_TYPE                      = "type";
    public static final String FIELD_VALUE                     = "value";
    public static final String FIELD_INTENSITY                 = "intensity";
    public static final String FIELD_RED                       = "red";
    public static final String FIELD_GREEN                     = "green";
    public static final String FIELD_BLUE                      = "blue";


    public enum SensorType {
        TEMPERATURE("temperature"),
        INSIDE_TEMPERATURE("inner_temperature"),
        OUTSIDE_TEMPERATURE("outer_temperature"),
        HUMIDITY("humidity"),
        INSIDE_HUMIDITY("inner_humidity"),
        OUTSIDE_HUMIDITY("outer_humidity"),
        PRESSURE("pressure"),
        INSIDE_PRESSURE("inner_pressure"),
        OUTSIDE_PRESSURE("outer_pressure"),
        VOLTAGE("voltage"),
        CURRENT("current"),
        SWITCH("switch");

        private final String typeId;

        SensorType(final String typeId) {
            this.typeId = typeId;
        }


        public String getTypeId() { return typeId; }
    }

    public enum ActorType {
        MONOCHROME_LED("monochrome_led"),
        RGB_LED("rgb_led"),
        RELAIS("relais");

        private final String typeId;

        ActorType(final String typeId) {
            this.typeId = typeId;
        }


        public String getTypeId() { return typeId; }
    }
}

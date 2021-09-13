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
    public static final String PROPERTIES_FILE_NAME   = "iotmodules.properties";

    public static final String CLIENT_ID              = "CLIENT_ID";
    public static final String MQTT_HOST              = "MQTT_HOST";
    public static final String MQTT_PORT              = "MQTT_PORT";
    public static final String MQTT_USER              = "MQTT_USER";
    public static final String MQTT_PW                = "MQTT_PW";
    public static final String MQTT_TOPIC_MESSAGES    = "TOPIC_MESSAGES";
    public static final String MQTT_TOPIC_PRESENCE    = "TOPIC_PRESENCE";
    public static final String MQTT_TOPIC_ALL_MODULES = "TOPIC_ALL_DEVICES";
    public static final String MQTT_TOPIC_SEPARATOR   = "/";
    public static final String MQTT_PLUS              = "+";

    public static final String SQUARE_BRACKET_OPEN    = "[";
    public static final String SQUARE_BRACKET_CLOSE   = "]";
    public static final String CURLY_BRACKET_OPEN     = "{";
    public static final String CURLY_BRACKET_CLOSE    = "}";
    public static final String INDENTED_QUOTES        = "  \"";
    public static final String QUOTES                 = "\"";
    public static final String COLON                  = ":";
    public static final String COMMA                  = ",";
    public static final String SLASH                  = "/";
    public static final String NEW_LINE               = "\n";
    public static final String COMMA_NEW_LINE         = ",\n";
    public static final String INDENT                 = "  ";

    public static final String FIELD_MODULE_ID        = "module_id";
    public static final String FIELD_SENSORS          = "sensors";
    public static final String FIELD_ACTORS           = "actors";
    public static final String FIELD_ID               = "id";
    public static final String FIELD_TYPE             = "type";
    public static final String FIELD_STATE            = "state";
    public static final String FIELD_INTENSITY        = "intensity";
    public static final String FIELD_RED              = "red";
    public static final String FIELD_GREEN            = "green";
    public static final String FIELD_BLUE             = "blue";
    public static final String FIELD_TEMPERATURE      = "temperature";
    public static final String FIELD_HUMIDITY         = "humidity";
    public static final String FIELD_PRESSURE         = "pressure";
    public static final String FIELD_VOLTAGE          = "voltage";
    public static final String FIELD_CURRENT          = "current";
    public static final String TYPE_TEMPERATURE       = "type_temperature";
    public static final String TYPE_HUMIDITY          = "type_humidity";
    public static final String TYPE_PRESSURE          = "type_pressure";
    public static final String TYPE_VOLTAGE           = "type_voltage";
    public static final String TYPE_CURRENT           = "type_current";
}

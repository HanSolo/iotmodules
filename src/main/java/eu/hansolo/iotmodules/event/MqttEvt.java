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

public class MqttEvt {
    private String topic;
    private String msg;


    // ******************** Constructors **************************************
    public MqttEvt(final String topic, final String msg) {
        this.topic = topic;
        this.msg   = msg;
    }


    // ******************** Messages ******************************************
    public String getTopic() { return topic; }

    public String getMsg() { return msg; }
}

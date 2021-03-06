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

import java.util.Arrays;
import java.util.List;


public enum OperatingSystem {
    LINUX("Linux", "linux"),
    MACOS("Mac OS", "macos"),
    WINDOWS("Windows", "windows"),
    NONE("-", ""),
    NOT_FOUND("", "");

    private final String uiString;
    private final String apiString;


    OperatingSystem(final String uiString, final String apiString) {
        this.uiString  = uiString;
        this.apiString = apiString;
    }


    public String getUiString() { return uiString; }

    public String getApiString() { return apiString; }

    public OperatingSystem getDefault() { return OperatingSystem.NONE; }

    public OperatingSystem getNotFound() { return OperatingSystem.NOT_FOUND; }

    public OperatingSystem[] getAll() { return values(); }

    public static OperatingSystem fromText(final String text) {
        if (null == text) { return NOT_FOUND; }
        switch (text) {
            case "-linux":
            case "linux":
            case "Linux":
            case "LINUX":
                return LINUX;
            case "darwin":
            case "-darwin":
            case "-macosx":
            case "-MACOSX":
            case "MacOS":
            case "Mac OS":
            case "mac_os":
            case "Mac_OS":
            case "mac-os":
            case "Mac-OS":
            case "mac":
            case "MAC":
            case "macos":
            case "MACOS":
            case "osx":
            case "OSX":
            case "macosx":
            case "MACOSX":
            case "Mac OSX":
            case "mac osx":
                return MACOS;
            case "-win":
            case "windows":
            case "Windows":
            case "WINDOWS":
            case "win":
            case "Win":
            case "WIN":
                return WINDOWS;
            default:
                return NOT_FOUND;
        }
    }

    public static List<OperatingSystem> getAsList() { return Arrays.asList(values()); }
}

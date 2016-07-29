/*
 * Copyright 2016 Nebula Bay.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tascape.demo.android.driver;

import com.android.uiautomator.stub.IUiObject;
import com.android.uiautomator.stub.UiSelector;
import com.tascape.reactor.android.driver.App;
import com.tascape.reactor.android.driver.UiAutomatorDevice;
import com.tascape.reactor.exception.EntityDriverException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author linsong wang
 */
public class Settings {

    private final UiAutomatorDevice device;

    private final IUiObject uiObject;

    public static final Map<String, String> UPDATE_INTERVALS = new HashMap<String, String>() {
        {
            put("Never", "No automatic app list updates");
            put("Hourly", "Hourly");
            put("Every 4 hours", "Every 4 hours");
            put("Every 12 hours", "Every 12 hours");
            put("Daily", "Daily");
            put("Weekly", "Weekly");
            put("Every 2 weeks", "Every 2 weeks");
        }
    };

    public Settings(App droid) {
        this.device = droid.getDevice();
        this.uiObject = device.getUiObject();
    }

    public void setAutoUpdateInterval(String interval) throws IOException {
        device.clickByText("Automatic update interval");
        device.takeDeviceScreenshot();
        if (device.waitForText(interval)) {
            device.clickByText(interval);
        } else {
            throw new EntityDriverException("Cannot find " + interval);
        }
    }

    public String getAutoUpdateInterval() {
        this.uiObject.useUiObjectSelector(new UiSelector().resourceId("android:id/list"));
        this.uiObject.selectChild(new UiSelector().index(1));
        this.uiObject.selectChild(new UiSelector().index(0));
        this.uiObject.selectChild(new UiSelector().index(1));
        return this.uiObject.getText();
    }
}

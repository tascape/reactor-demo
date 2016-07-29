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
package com.tascape.demo.android.task;

import com.tascape.demo.android.driver.FDroidApp;
import com.tascape.demo.android.driver.Settings;
import org.junit.Test;
import com.tascape.reactor.android.driver.UiAutomatorDevice;
import com.tascape.reactor.android.task.UiAutomatorTask;
import com.tascape.reactor.driver.CaseDriver;
import com.tascape.reactor.task.AbstractCase;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.rules.Timeout;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.tascape.demo.android.driver.Settings.UPDATE_INTERVALS;

/**
 *
 * @author linsong wang
 */
public class FDroidSettingsCases extends AbstractCase implements UiAutomatorTask {
    private static final Logger LOG = LoggerFactory.getLogger(FDroidSettingsCases.class);

    public static final CaseDriver MOBILE_DEVICE = new CaseDriver(FDroidSettingsCases.class, UiAutomatorDevice.class);

    public static final CaseDriver MOBILE_APP = new CaseDriver(FDroidSettingsCases.class, FDroidApp.class);

    private final UiAutomatorDevice device;

    private final FDroidApp app;

    private Settings settings;

    @Rule
    public TestWatcher watcher = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            try {
                device.loadWindowHierarchy();
            } catch (Exception ex) {
                LOG.warn("Cannot dump view hierarchy", ex);
            }
        }
    };

    public FDroidSettingsCases() {
        this.globalTimeout = new Timeout(5, TimeUnit.MINUTES);
        this.device = super.getEntityDriver(MOBILE_DEVICE);
        this.app = super.getEntityDriver(MOBILE_APP);
    }

    @Before
    public void setup() throws Exception {
        device.backToHome();
        app.launch();
        device.takeDeviceScreenshot();
        this.settings = app.openSettings();
    }

    @After
    public void cleanup() throws Exception {
        device.takeDeviceScreenshot();
    }

    @Test
    public void testAutoUpdateInterval() throws Exception {
        for (Map.Entry<String, String> intervalEntry : UPDATE_INTERVALS.entrySet()) {
            String interval = intervalEntry.getKey();
            LOG.info("set auto update interval to '{}'", interval);
            settings.setAutoUpdateInterval(interval);
            device.takeDeviceScreenshot();
            Assert.assertEquals("Update interval is not updated,", intervalEntry.getValue(),
                settings.getAutoUpdateInterval());
        }
    }

    @Override
    public String getApplicationUnderTask() {
        return app.getName();
    }
}

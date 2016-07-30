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
package com.tascape.demo.android.suite;

import com.tascape.demo.android.driver.FDroidApp;
import com.tascape.demo.android.task.FDroidSettingsCases;
import com.tascape.reactor.android.driver.App;
import com.tascape.reactor.android.driver.UiAutomatorDevice;
import com.tascape.reactor.suite.AbstractSuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;
import com.tascape.reactor.android.suite.UiAutomatorSuite;

/**
 *
 * @author linsong wang
 */
public class FDroidSuite extends AbstractSuite implements UiAutomatorSuite {
    private static final Logger LOG = LoggerFactory.getLogger(FDroidSuite.class);

    private final FDroidApp droid = new FDroidApp();

    private UiAutomatorDevice device;

    @Override
    public void setUpCaseClasses() {
        this.addCaseClass(FDroidSettingsCases.class);
    }

    @Override
    protected void setUpEnvironment() throws Exception {
        device = this.getAvailableDevice();
        String apk = SYSCONFIG.getProperty(App.SYSPROP_APK_PATH);
        if (StringUtils.isNotEmpty(apk)) {
            device.uninstall(droid.getPackageName());
            device.install(apk);
        }
        device.install(droid);
        device.takeDeviceScreenshot();

        this.putCaseDirver(FDroidSettingsCases.MOBILE_DEVICE, device);
        this.putCaseDirver(FDroidSettingsCases.MOBILE_APP, droid);
    }

    @Override
    protected void tearDownEnvironment() {
        if (device != null) {
            device.stop();
        }
    }

    @Override
    public String getProductUnderTask() {
        return droid.getName();
    }

    @Override
    public String getProjectName() {
        return "thx-android-demo";
    }

    @Override
    public int getNumberOfEnvs() {
        return this.getNumberOfDevices();
    }
}

/*
 * Copyright (c) 2015 - present Nebula Bay.
 * All rights reserved.
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
package com.tascape.demo.suite;

import com.tascape.demo.task.PageLoading;
import com.tascape.reactor.suite.AbstractSuite;
import com.tascape.reactor.webui.comm.Firefox;
import com.tascape.reactor.webui.comm.WebBrowser;
import com.tascape.reactor.webui.driver.DefaultWebApp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author linsong wang
 */
public class PageLoadingSuite extends AbstractSuite {
    private static final Logger LOG = LoggerFactory.getLogger(PageLoadingSuite.class);

    private Firefox firefox;

    private final DefaultWebApp app = new DefaultWebApp();

    @Override
    public void setUpCaseClasses() {
        super.addCaseClass(PageLoading.class);
    }

    @Override
    public String getProductUnderTask() {
        return app.getName();
    }

    @Override
    protected void setUpEnvironment() throws Exception {
        firefox = WebBrowser.newFirefox(true);
        app.setWebBrowser(firefox);
        app.launch(DefaultWebApp.URL);

        super.putCaseDirver(PageLoading.DRIVER_WEB_APP, app);
    }

    @Override
    protected void tearDownEnvironment() {
        if (firefox != null) {
            firefox.close();
        }
    }
}

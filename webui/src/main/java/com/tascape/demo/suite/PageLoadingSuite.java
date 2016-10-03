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

import com.tascape.demo.driver.GoogleDotCom;
import com.tascape.demo.task.PageLoading;
import com.tascape.demo.task.PartialPageLoading;
import com.tascape.reactor.webui.comm.WebBrowser;
import com.tascape.reactor.webui.driver.DefaultWebApp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author linsong wang
 */
public class PageLoadingSuite extends WebUiDemoSuite {
    private static final Logger LOG = LoggerFactory.getLogger(PageLoadingSuite.class);

    @Override
    public void setUpCaseClasses() {
        super.addCaseClass(PageLoading.class);
        super.addCaseClass(PartialPageLoading.class);
    }

    @Override
    public String getProductUnderTask() {
        return "PageLoading";
    }

    @Override
    protected void setUpEnvironment() throws Exception {
        webBrowser = WebBrowser.newFirefox(true);

        DefaultWebApp app = new DefaultWebApp();
        app.setWebBrowser(webBrowser);
        super.putCaseDirver(PageLoading.DRIVER_WEB_APP, app);

        GoogleDotCom google = new GoogleDotCom();
        google.setWebBrowser(webBrowser);
        super.putCaseDirver(PartialPageLoading.DRIVER_GOOGLE, google);

        app.launch();
    }
}

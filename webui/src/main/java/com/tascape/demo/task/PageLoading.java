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
package com.tascape.demo.task;

import com.tascape.reactor.driver.CaseDriver;
import com.tascape.reactor.task.AbstractCase;
import com.tascape.reactor.webui.driver.DefaultWebApp;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author linsong wang
 */
public class PageLoading extends AbstractCase {
    private static final Logger LOG = LoggerFactory.getLogger(PageLoading.class);

    public static final CaseDriver DRIVER_WEB_APP = new CaseDriver(PageLoading.class,
        DefaultWebApp.class);

    private final DefaultWebApp app;

    public PageLoading() throws InterruptedException {
        this.app = super.getEntityDriver(DRIVER_WEB_APP);
        app.getWebBrowser().setDefaultTimeouts();
    }

    @Override
    public String getApplicationUnderTask() {
        return app.getName();
    }

    @Before
    public void setup() throws Exception {
    }

    @After
    public void cleanup() throws Exception {
        app.takeBrowerScreenshot();
    }

    @Test
    public void searchGoogle() throws Exception {
        int time = app.getPageLoadTimeMillis("https://www.google.com/#newwindow=1&q=tascape");
        this.putResultMetric("page-loading", "google", time);
        Assert.assertTrue("Fail to load in 5 seconds", time < 5000);
    }

    @Test
    public void loadJsonEditorOnline() throws Exception {
        int time = app.getPageLoadTimeMillis("http://www.jsoneditoronline.org/");
        this.putResultMetric("page-loading", "jsoneditoronline", time);
        Assert.assertTrue("Fail to load in 5 seconds", time < 5000);
    }

    @Test
    public void loadNetflix() throws Exception {
        int time = app.getPageLoadTimeMillis("https://www.netflix.com/");
        this.putResultMetric("page-loading", "netflix", time);
        Assert.assertTrue("Fail to load in 5 seconds", time < 5000);
    }
}

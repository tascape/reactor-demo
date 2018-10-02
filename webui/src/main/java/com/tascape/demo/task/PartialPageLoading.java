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

import com.tascape.demo.driver.GoogleDotCom;
import com.tascape.demo.driver.PageGoogleMaps;
import com.tascape.reactor.data.CaseDataProvider;
import com.tascape.reactor.data.CaseIterationData;
import com.tascape.reactor.driver.CaseDriver;
import com.tascape.reactor.task.AbstractCase;
import com.tascape.reactor.webui.comm.WebBrowser;
import com.tascape.reactor.webui.comm.WebBrowser.Ajax;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author linsong wang
 */
public class PartialPageLoading extends AbstractCase {
    private static final Logger LOG = LoggerFactory.getLogger(PartialPageLoading.class);

    public static final CaseDriver DRIVER_GOOGLE = new CaseDriver(PartialPageLoading.class,
        GoogleDotCom.class);

    private final GoogleDotCom google;

    public PartialPageLoading() throws InterruptedException {
        this.google = super.getEntityDriver(DRIVER_GOOGLE);
        google.getWebBrowser().setDefaultTimeouts();
        google.launch();
    }

    @Override
    public String getApplicationUnderTask() {
        return google.getName();
    }

    @Before
    public void setup() throws Exception {
    }

    @After
    public void cleanup() throws Exception {
        google.takeBrowserScreenshot();
    }

    @Test
    @CaseDataProvider(klass = CaseIterationData.class, parameter = "8")
    public void searchGoogleMaps() throws Exception {
        PageGoogleMaps maps = google.open(PageGoogleMaps.class);
        maps.search("food");
        long start = System.currentTimeMillis();
        Ajax click = new WebBrowser.AbstractAjax() {
            @Override
            public long doRequest() {
                WebElement f = google.getWebBrowser().findElements(By.className("section-result")).get(1);
                long start = System.currentTimeMillis();
                f.click();
                return start;
            }
        };
        int time = google.getWebBrowser().getAjaxLoadTimeMillis(click);
        long stop = System.currentTimeMillis();
        LOG.debug("{} - {} = {}", stop, start, stop - start);
        this.putResultMetric("page-loading", "partial", time);
        Assert.assertTrue("Fail to load second search result in 20 seconds", time < 20000);
    }
}

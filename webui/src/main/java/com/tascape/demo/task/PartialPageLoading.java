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
import com.tascape.reactor.driver.CaseDriver;
import com.tascape.reactor.task.AbstractCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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
        google.takeBrowerScreenshot();
    }

    @Test
    public void searchGoogleNews() throws Exception {
        LOG.warn("TODO");
    }
}

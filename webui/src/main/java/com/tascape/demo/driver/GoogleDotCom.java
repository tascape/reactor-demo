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
package com.tascape.demo.driver;

import com.tascape.reactor.webui.driver.WebApp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author linsong wang
 */
public class GoogleDotCom extends WebApp {
    private static final Logger LOG = LoggerFactory.getLogger(GoogleDotCom.class);

    public static final String URL = "https://www.google.com";

    @Override
    public String getName() {
        return URL;
    }

    @Override
    public String getVersion() {
        return "NA";
    }

    @Override
    public void reset() throws Exception {
        LOG.debug("nothing");
    }

    @Override
    public int getLaunchDelayMillis() {
        return 2000;
    }

    @Override
    public String getBaseUrl() {
        return URL;
    }
}

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

import com.tascape.reactor.suite.AbstractSuite;
import com.tascape.reactor.webui.comm.WebBrowser;

/**
 *
 * @author linsong wang
 */
public abstract class WebUiDemoSuite extends AbstractSuite {
    protected WebBrowser webBrowser;

    @Override
    public String getProjectName() {
        return "reactor-demo-webui";
    }

    @Override
    protected void tearDownEnvironment() {
        if (webBrowser != null) {
            webBrowser.close();
        }
    }
}

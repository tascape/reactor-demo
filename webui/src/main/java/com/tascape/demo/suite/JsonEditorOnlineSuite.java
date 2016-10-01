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

import com.tascape.demo.driver.JsonEditorOnline;
import com.tascape.demo.task.JsonEditorOnlinePageLoading;
import com.tascape.reactor.suite.AbstractSuite;
import com.tascape.reactor.webui.comm.Firefox;
import com.tascape.reactor.webui.comm.WebBrowser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author linsong wang
 */
public class JsonEditorOnlineSuite extends AbstractSuite {
    private static final Logger LOG = LoggerFactory.getLogger(JsonEditorOnlineSuite.class);

    private Firefox firefox;

    private JsonEditorOnline editor;

    @Override
    public void setUpCaseClasses() {
        super.addCaseClass(JsonEditorOnlinePageLoading.class);
    }

    @Override
    public String getProductUnderTask() {
        return editor.getName();
    }

    @Override
    protected void setUpEnvironment() throws Exception {
        firefox = WebBrowser.newFirefox(false);
        firefox.get(JsonEditorOnline.URL);

        editor = new JsonEditorOnline();
        editor.setWebBrowser(firefox);

        super.putCaseDirver(JsonEditorOnlinePageLoading.DRIVER_JSON_EDITOR, editor);
    }

    @Override
    protected void tearDownEnvironment() {
        if (firefox != null) {
            firefox.close();
        }
    }
}

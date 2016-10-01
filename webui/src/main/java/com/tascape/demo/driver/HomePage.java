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

import com.tascape.reactor.webui.driver.WebPage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author linsong wang
 */
public class HomePage extends WebPage {
    private static final Logger LOG = LoggerFactory.getLogger(HomePage.class);

    @FindBy(xpath = "//textarea[@class='ace_text-input']")
    private WebElement editor;

    @FindBy(id = "toTree")
    private WebElement parse;

    public static final String XPATH_ERROR = "//pre[@class='error']";

    public void parseJson(String json) throws IOException {
        webBrowser.clear(editor);
        webBrowser.takeBrowerScreenshot();
        editor.sendKeys(json);
        webBrowser.takeBrowerScreenshot();
        parse.click();
        webBrowser.takeBrowerScreenshot();
    }

    @Override
    public String getPath() {
        return "/";
    }

    @Override
    protected void isLoaded() throws Error {
        Assert.assertNotNull("Cannot find editor on page, assuming page is not loaded", editor);
    }

    public boolean hasNoError() {
        webBrowser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        try {
            return this.webBrowser.findElements(By.xpath(XPATH_ERROR)).isEmpty();
        } finally {
            webBrowser.setDefaultTimeouts();
        }
    }
}

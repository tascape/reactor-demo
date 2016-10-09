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
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author linsong wang
 */
public class PageGoogleMaps extends WebPage {
    private static final Logger LOG = LoggerFactory.getLogger(PageGoogleMaps.class);

    @FindBy(xpath = "//input[@name='q']")
    private WebElement searchBox;

    @Override
    public String getPath() {
        return "/maps";
    }

    @Override
    protected void isLoaded() throws Error {
        Assert.assertNotNull("Cannot find search box on page", searchBox);
    }

    public void search(String term) {
        searchBox.sendKeys(term);
        searchBox.sendKeys(Keys.RETURN);
    }
}

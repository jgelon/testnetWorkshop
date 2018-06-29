package com.testnet.ourWebdriver;

import cucumber.api.java.After;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public class TearDown {

    private final WebDriver browser;

    public TearDown() throws MalformedURLException {
        browser = BrowserFactory.getWebDriver();
    }

    @After
    public void afterScenario() {
        browser.quit();
    }
}

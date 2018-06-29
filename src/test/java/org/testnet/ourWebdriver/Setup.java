package org.testnet.ourWebdriver;

import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public class Setup {

    @Before
    public void beforeScenario() throws MalformedURLException {
        final WebDriver browser = BrowserFactory.getWebDriver();
        browser.manage().window().maximize();
    }
}

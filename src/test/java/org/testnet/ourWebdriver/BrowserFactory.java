package org.testnet.ourWebdriver;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.testnet.utils.FileHelper;

import java.io.*;
import java.net.MalformedURLException;
import java.util.Properties;

public class BrowserFactory {

    private static OurWebDriver browser;

    public static OurFirefoxDriver createFfDriver() throws MalformedURLException {
        return OurFirefoxDriver.getBrowser();
    }

    public static OurChromeDriver createChromeDriver() throws MalformedURLException {
        return OurChromeDriver.getBrowser();
    }

    public static OurIEDriver createIEDriver() throws MalformedURLException {
        return OurIEDriver.getBrowser();
    }

    public static OurWebDriver getWebDriver() throws MalformedURLException {
        final String browserType = getBrowserType();
        return getBrowserOfType(browserType);
    }

    static String getDriverFile(String browserName) {
        FileFilter fileFilter = new WildcardFileFilter("*"+browserName+"*");
        File[] dir = new File(FileHelper.getRootPath() + File.separator + "drivers" + File.separator).listFiles(fileFilter);
        String driverPath = "";
        try {
            driverPath = dir[0].getAbsolutePath();
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("No driver found for '"+browserName+"' in the folder drivers.\nStopping execution.");
            System.exit(1);
        }
        return driverPath;
    }

    private static OurWebDriver getBrowserOfType(final String browserType) {
        if (browserType == null) {
            browser = OurFirefoxDriver.getBrowser();
        } else if (browserType.equals("chrome")) {
            browser = OurChromeDriver.getBrowser();
        } else if (browserType.equals("ie")) {
            browser = OurIEDriver.getBrowser();
        } else {
            browser = OurFirefoxDriver.getBrowser();
        }
        return browser;
    }

    private static String getBrowserType() {
        final Properties prop = new Properties();
        final InputStream input;
        String browserType = null;

        try {
            input = new FileInputStream(FileHelper.getRootPath() + "\\browser.properties");
            prop.load(input);
            browserType = prop.getProperty("browser.type");
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return browserType;
    }
}
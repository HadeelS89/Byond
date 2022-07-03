package com.beyond.common;


import com.beyond.helpers.ReadWriteHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

@Listeners(com.beyond.reporting.Listeners.class)
public class Base {

    public static WebDriver driver;
    public static String environment = "";
    public static String downloadDir = System.getProperty("user.dir") + "\\src\\main\\resources\\DataProvider";
public static boolean isWeb= false;

    @Parameters({"browserType", "url"})
    @BeforeClass(enabled = true)

    public void setUpBrowser(@Optional("optional")
                                   String browserType, @Optional("tst") String url) {

            if (!browserType.equals("optional")) {
                initiateDriver(OsValidator.getDeviceOs(), browserType);
                // initiateDriver();
            } else {
                initiateDriver(OsValidator.getDeviceOs(), ReadWriteHelper.ReadData("browser"));
            }
            // this to determine the url needed
            // if it not created as param on xml file then use the else
            if (url.equalsIgnoreCase("tst")) {
                driver.navigate().to(ReadWriteHelper.ReadData("eSISClient"));

                System.out.println("URL for test");
                environment = "tst";
                ReadWriteHelper.writeEnvironment(environment);
                System.out.println("environment = " + environment);
            }
            // created as param on xml suite for staging
            else if (url.equalsIgnoreCase("stg")) {
                driver.navigate().to(ReadWriteHelper.ReadData("eSISStaging"));
                System.out.println("URL for staging");
                environment = "stg";
                ReadWriteHelper.writeEnvironment(environment);
            } else {
// created as param on xml suite for staging  for pre prod
                driver.navigate().to(ReadWriteHelper.ReadData("eSISPreProd"));
                System.out.println("URL for pre prod");
                environment = "pre_prod";
                ReadWriteHelper.writeEnvironment(environment);
            }


    }

    public WebDriver initiateDriver()
    {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        return driver;
    }

    public WebDriver initiateDriver(String deviceOsType, String driverType) {
        String browser = driverType.toLowerCase();

        switch (browser) {
            case "firefox":
                try {

                    setFireFoxBrowser(deviceOsType);
                    FirefoxOptions options = new FirefoxOptions();
                    options.setAcceptInsecureCerts(true);
                    if (ReadWriteHelper.ReadData("headless").equalsIgnoreCase("true")) {
                        options.addArguments("--headless");

                    }
                    driver = new FirefoxDriver(options);
                } catch (Throwable e) {
                    e.printStackTrace(System.out);
                    Assert.fail("Please check Browser is exist Browser Unable to start");
                }
                break;
            case "chrome":

            case "Edge":
                try {
                   // WebDriverManager.chromedriver().setup();
                    DesiredCapabilities handlSSLErr = DesiredCapabilities.chrome();
                    handlSSLErr.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                    setChromeBrowser(deviceOsType);
                    Map<String, Object> prefs = new HashMap<>();
                    //Put this into prefs map to switch off browser notification
                    prefs.put("profile.default_content_setting_values.notifications", 2);

                    prefs.put("download.default_directory", downloadDir);
                    //Create chrome options to set this prefs
                    ChromeOptions options = new ChromeOptions();
                    options.setExperimentalOption("prefs", prefs);
                    options.addArguments("--disable-web-security");
                    options.addArguments("--allow-running-insecure-content");
                    options.setAcceptInsecureCerts(true);
                    options.addArguments("--no-sandbox");
                    if (ReadWriteHelper.ReadData("headless").equalsIgnoreCase("true")) {
                        options.addArguments("--headless");
                        options.addArguments("window-size=1920x1080");
                    }
                   // driver = new ChromeDriver(options);

                     driver = new ChromeDriver(options);

                    if (ReadWriteHelper.ReadData("headless").equalsIgnoreCase("true")) {
                        Dimension targetSize = new Dimension(1440, 1080); //your screen resolution here
                        driver.manage().window().setSize(targetSize);
                    }


                } catch (Throwable e) {
                    e.printStackTrace(System.out);
                    Assert.fail("Please check Browser is exist Browser Unable to start");
                }
                break;
            case "ie": {
                try {
                    System.setProperty(ReadWriteHelper.ReadData("IEDriverPath"),
                            ReadWriteHelper.ReadData("IEBrowserPathWindows"));
                    driver = new InternetExplorerDriver();
                } catch (Throwable e) {
                    e.printStackTrace(System.out);
                    Assert.fail("Please check Browser is exist Browser Unable to start");
                }
                break;
            }
            case "safari": {
                try {
                    System.setProperty(ReadWriteHelper.ReadData("SafariDriverPath"),
                            ReadWriteHelper.ReadData("SafariBrowserPath"));

                    driver = new SafariDriver();
                } catch (Throwable e) {
                    e.printStackTrace(System.out);
                    Assert.fail("Please check Browser is exist Browser Unable to start");
                }
            }
        }

        driver.manage().window().maximize();
        return driver;
    }

    public DriverType getBrowser() {
        String browserName = ReadWriteHelper.ReadData("browser");

        if (browserName == null || browserName.equalsIgnoreCase("chrome"))
            return DriverType.CHROME;
        else if (browserName.equalsIgnoreCase("firefox"))
            return DriverType.FIREFOX;
        else if (browserName.equals("iexplorer") || browserName.equalsIgnoreCase("internetExplorer") ||
                browserName.equalsIgnoreCase("ie"))
            return DriverType.INTERNETEXPLORER;
        else if (browserName.equalsIgnoreCase("safari")) return DriverType.Safari;
        else
            throw new RuntimeException("Browser Name Key value in Configuration.properties is not matched : " +
                    browserName);
    }


    private void setChromeBrowser(String deviceOsType) {
        if (deviceOsType.equalsIgnoreCase("mac")) {

            WebDriverManager.chromedriver().setup();
           // System.setProperty(ReadWriteHelper.ReadData("ChromeDriverPath"),
             //       ReadWriteHelper.ReadData("ChromeDriverLinkMac"));
        } else if (deviceOsType.equalsIgnoreCase("windows")) {
            WebDriverManager.chromedriver().setup();
        } else if (deviceOsType.equalsIgnoreCase("Linux")) {
            System.setProperty(ReadWriteHelper.ReadData("ChromeDriverPath"),
                    ReadWriteHelper.ReadData("chromeDriverLinkLinux"));
        }
    }

    private void setFireFoxBrowser(String deviceOsType) {
        if (deviceOsType.equalsIgnoreCase("mac")) {
            System.setProperty(ReadWriteHelper.ReadData("FireFoxDriverPath"),
                    ReadWriteHelper.ReadData("FireFoxBrowserPathMac"));
        } else if (deviceOsType.equalsIgnoreCase("windows")) {
            System.setProperty(ReadWriteHelper.ReadData("FireFoxDriverPath"),
                    ReadWriteHelper.ReadData("FireFoxBrowserPathWindows"));
        }
    }


   //  @AfterClass(enabled = true)
    public void stopDriver() {
        try {
            driver.quit();
        } catch (Throwable e) {
            e.getStackTrace();
        }
    }

}

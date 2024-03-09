package base;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aeonbits.owner.ConfigCache;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import utils.AppConstants;
import utils.ConfigProps;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

@Slf4j
public class BaseClass {
    @Getter
    public WebDriver driver;

    @Getter
    public String testUrl;

    public ConfigProps config;

    public void setUp() {
        if (driver == null) {
            String browserName = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browserName");
            String remoteFlag = System.getProperty("remote", "false");

            try {
                config = ConfigCache.getOrCreate(ConfigProps.class);
                String osName = config.osName();
                driver = createBrowserInstance(osName, browserName, remoteFlag);
                driver.manage().window().maximize();
                driver.get(config.url());
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            } catch (Exception e) {
                log.error("Error occurred while creating driver instance : " + e);
                throw new RuntimeException(e);
            }
        }
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    //get driver instance
    private WebDriver createBrowserInstance(String osName, String browserName, String remoteFlag) {
        WebDriver wd = null;
        //select browser
        if ((osName.contains(AppConstants.WINDOWS) || osName.contains(AppConstants.LINUX))
                && browserName.equals(AppConstants.SAFARI)) {
            log.info("Safari is not supported on " + osName);
            throw new RuntimeException("Safari not supported on Operating System : " +osName);
        } else{
            wd = createDriver(browserName, remoteFlag);
        }
        return wd;
    }

    private WebDriver createDriver(String browserName, String remoteFlag) {
        WebDriver wd = null;
        if (browserName.equals(AppConstants.CHROME)) {
            ChromeOptions options = new ChromeOptions();
            if (remoteFlag.equalsIgnoreCase("true")) {
                try {
                    wd = new RemoteWebDriver(new URL(config.seleniumGridUrl()), options);
                } catch (MalformedURLException e) {
                    log.info("Issue with selenium grid url");
                    throw new RuntimeException(e);
                }
            } else {
               wd = new ChromeDriver(options);
            }
        }
        return wd;
    }
}
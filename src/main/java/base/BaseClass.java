package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.SkipException;

public class BaseClass{
    public WebDriver driver;
    public WebDriverManager wdm;
    public Properties config = new Properties();
    public String projPath = System.getProperty("user.dir");
    public String fileSeparator = File.separator;
    public FileInputStream fis;
    public String osName = System.getProperty("os.name").toLowerCase();

    public WebDriver getDriver() {
        return driver;
    }


    public void setUp() throws IOException {
        if (driver == null) {
            fis = new FileInputStream(projPath + fileSeparator + "src" + fileSeparator + "main" + fileSeparator + "resources" + fileSeparator + "properties" + fileSeparator + "config.properties");
            config.load(fis);
            String browserName = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browserName");
            String remoteFlag = System.getProperty("remote", "false");

            //select browser
            if (osName.contains("mac")) {
                if (browserName.equals("chrome")) {
                    if(remoteFlag.equalsIgnoreCase("true")){
                        wdm = WebDriverManager.chromedriver().browserInDocker()
                                .enableVnc().enableRecording();
                        driver = wdm.create();
                    }else{
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions options = new ChromeOptions();
                        driver = new ChromeDriver(options);
                    }
                    driver.manage().window().maximize();
                    driver.get(config.getProperty("testSiteUrl"));
                }
            } else if (osName.contains("windows")) {
                if (browserName.equals("chrome")) {
                    if(remoteFlag.equalsIgnoreCase("true")){
                        wdm = WebDriverManager.chromedriver().browserInDocker()
                                .enableVnc().enableRecording();
                        driver = wdm.create();
                    }else{
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions options = new ChromeOptions();
                        driver = new ChromeDriver(options);
                    }
                    driver.manage().window().maximize();
                    driver.get(config.getProperty("testSiteUrl"));
                }
            } else if (osName.contains("linux")) {
                if (browserName.equals("chrome")) {
                    if(remoteFlag.equalsIgnoreCase("true")){
                        wdm = WebDriverManager.chromedriver().browserInDocker()
                                .enableVnc().enableRecording();
                        driver = wdm.create();
                    }else{
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions options = new ChromeOptions();
                        driver = new ChromeDriver(options);
                    }
                    driver.manage().window().maximize();
                    driver.get(config.getProperty("testSiteUrl"));
                }
            }

        }
    }

    public void tearDown() throws IOException {
        String remoteFlag = System.getProperty("remote", "false");
        if(remoteFlag.equalsIgnoreCase("true")){
            wdm.quit();
            return;
        }
        if (driver != null) {
            driver.quit();
        }
    }
}
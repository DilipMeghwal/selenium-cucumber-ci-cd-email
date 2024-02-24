package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class BaseClass{
    public WebDriver driver;
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
                        ChromeOptions options = new ChromeOptions();
                        driver = new RemoteWebDriver(new URL("http://localhost:4444"),options);
                    }else{
                        ChromeOptions options = new ChromeOptions();
                        driver = new ChromeDriver(options);
                    }
                    driver.manage().window().maximize();
                    driver.get(config.getProperty("testSiteUrl"));
                }
            } else if (osName.contains("windows")) {
                if (browserName.equals("chrome")) {
                    if(remoteFlag.equalsIgnoreCase("true")){
                        ChromeOptions options = new ChromeOptions();
                        driver = new RemoteWebDriver(new URL("http://127.0.0.1:60837/"),options);
                    }else{
                        ChromeOptions options = new ChromeOptions();
                        driver = new ChromeDriver(options);
                    }
                    driver.manage().window().maximize();
                    driver.get(config.getProperty("testSiteUrl"));
                }
            } else if (osName.contains("linux")) {
                if (browserName.equals("chrome")) {
                    if(remoteFlag.equalsIgnoreCase("true")){
                        ChromeOptions options = new ChromeOptions();
                        driver = new RemoteWebDriver(new URL("http://127.0.0.1:60837/"),options);
                    }else{
                        ChromeOptions options = new ChromeOptions();
                        driver = new ChromeDriver(options);
                    }
                    driver.manage().window().maximize();
                    driver.get(config.getProperty("testSiteUrl"));
                }
            }

        }
    }

    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }
}
package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.SkipException;

public class BaseClass {
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
			fis = new FileInputStream(projPath + fileSeparator + "src" + fileSeparator + "test" + fileSeparator + "resources" + fileSeparator + "properties" + fileSeparator + "config.properties");
			config.load(fis);
			String browserName = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browserName");

			//select browser
			if (osName.contains("mac")) {
				if (browserName.equals("chrome")) {
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
					driver.manage().window().maximize();
					driver.get(config.getProperty("testSiteUrl"));
				} else if (browserName.equals("safari")) {
					driver = new SafariDriver();
					driver.manage().window().maximize();
					driver.get(config.getProperty("testSiteUrl"));
				}
			} else if (osName.contains("windows")) {
				if (browserName.equals("chrome")) {
					WebDriverManager.chromedriver().setup();
					ChromeOptions options = new ChromeOptions();
					driver = new ChromeDriver(options);
					driver.manage().window().maximize();
					driver.get(config.getProperty("testSiteUrl"));
				} else if (browserName.equals("safari")) {
					throw new SkipException("Skipping the run on safari on windows as its not supported");
				}
			} else if (osName.contains("linux")) {
				if (browserName.equals("chrome")) {
					WebDriverManager.chromedriver().setup();
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--headless");
					driver = new ChromeDriver(options);
					driver.manage().window().maximize();
					driver.get(config.getProperty("testSiteUrl"));
				} else if (browserName.equals("safari")) {
					throw new SkipException("Skipping the run on safari on linux as its not supported");
				}
			}

		}
	}

	public void tearDown() throws IOException {
		if (driver != null) {
			driver.quit();
		}
	}
	
	// to check element is present or not
		public <T> boolean isElementPresent(T element){
			try {
				if (element.getClass().getName().contains("By")) {
					driver.findElement((By) element).isDisplayed();
					return true;
				} else {
					((WebElement) element).isDisplayed();
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}

		// Set Test to inutBox
		public <T> void setInputBoxText(T element, String text){
			try {
				if (element.getClass().getName().contains("By")) {
					driver.findElement((By) element).sendKeys(text);
				} else {
					((WebElement) element).sendKeys(text);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// click On element
		public <T> void clickOnElement(T element) {
			try {
				if (element.getClass().getName().contains("By")) {
					driver.findElement((By) element).click();
				} else {
					((WebElement) element).click();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Hover over element
		public <T> void mouseOverOnElement(T element) {
			try {
				Actions action = new Actions(driver);
				if (element.getClass().getName().contains("By")) {
					action.moveToElement(driver.findElement((By) element)).build().perform();
				} else {
					action.moveToElement((WebElement) element).build().perform();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// ExplicitWait
		public <T> void applyExplicitWait(T element) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt((String) config.get("explicitWait")));
				if (element.getClass().getName().contains("By")) {
					wait.until(ExpectedConditions.elementToBeClickable(driver.findElement((By) element)));
				} else {
					wait.until(ExpectedConditions.elementToBeClickable((WebElement) element));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// click using JAvascript exec
		public <T> void clickOnElementUsingJS(T element) {
			try {
				JavascriptExecutor jsExe = (JavascriptExecutor) driver;
				if (element.getClass().getName().contains("By")) {
					jsExe.executeScript("arguments[0].click();", driver.findElement((By) element));
				} else {
					jsExe.executeScript("arguments[0].click();", (WebElement) element);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// get Text
		public <T> String getTextOfElement(T element) {
			String strText = null;
			try {
				if (element.getClass().getName().contains("By")) {
					if (!driver.findElement((By) element).getText().equals("")) {
						strText = driver.findElement((By) element).getText();
					} else if (!driver.findElement((By) element).getAttribute("textContent").equals("")) {
						strText = driver.findElement((By) element).getAttribute("textContent");
					} else if (!driver.findElement((By) element).getAttribute("innerText").equals("")) {
						strText = driver.findElement((By) element).getAttribute("innerText");
					} else if (!driver.findElement((By) element).getAttribute("outerText").equals("")) {
						strText = driver.findElement((By) element).getAttribute("outerText");
					} else if (!driver.findElement((By) element).getAttribute("innerHTML").equals("")) {
						strText = driver.findElement((By) element).getAttribute("innerHTML");
					}
				} else {
					if (!((WebElement) element).getText().equals("")) {
						strText = ((WebElement) element).getText();
					} else if (!((WebElement) element).getAttribute("textContent").equals("")) {
						strText = ((WebElement) element).getAttribute("textContent");
					} else if (!((WebElement) element).getAttribute("innerText").equals("")) {
						strText = ((WebElement) element).getAttribute("innerText");
					} else if (!((WebElement) element).getAttribute("outerText").equals("")) {
						strText = ((WebElement) element).getAttribute("outerText");
					} else if (!((WebElement) element).getAttribute("innerHTML").equals("")) {
						strText = ((WebElement) element).getAttribute("innerHTML");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return strText;
		}
	}
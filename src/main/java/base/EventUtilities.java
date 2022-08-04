package base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EventUtilities {
    // to check element is present or not
    public static boolean isElementPresent(WebDriver driver, By element) {
        try {
            driver.findElement(element).isDisplayed();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Set Test to inutBox
    public static void setInputBoxText(WebDriver driver, By element, String text) {
        try {
            driver.findElement(element).sendKeys(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // click On element
    public static void clickOnElement(WebDriver driver, By element) {
        try {
            driver.findElement(element).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // click On element having Text
    public static void clickOnWithVisibleText(WebDriver driver, String xpath) {
        try {
            driver.findElement(By.xpath(xpath)).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Hover over element
    public static void mouseOverOnElement(WebDriver driver, By element) {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(driver.findElement(element)).build().perform();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ExplicitWait
    public static void applyExplicitWait(WebDriver driver, By element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(element)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void applyExplicitWaitForVisibility(WebDriver driver, By element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void applyExplicitWaitVisibility(WebDriver driver, By element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(element)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void applyExplicitWaitBy(WebDriver driver, By element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(element)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // scroll element into view
    public static void scrollElementIntoView(WebDriver driver, String xpath) {
        try {
            JavascriptExecutor jsExe = (JavascriptExecutor) driver;
            jsExe.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(xpath)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //scroll by
    public static void scrollBy(WebDriver driver, int x, int y) {
        try {
            JavascriptExecutor jsExe = (JavascriptExecutor) driver;
            jsExe.executeScript("window.scrollBy(" + x + "," + y + ")", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // click using Javascript exec
    public static void clickOnElementUsingJS(WebDriver driver, By element) {
        try {
            JavascriptExecutor jsExe = (JavascriptExecutor) driver;
            jsExe.executeScript("arguments[0].click();", driver.findElement(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // get Text
    public static String getTextOfElement(WebDriver driver, By element) {
        String strText = null;
        try {
            if (!driver.findElement(element).getText().equals("")) {
                strText = driver.findElement(element).getText();
            } else if (!driver.findElement(element).getAttribute("textContent").equals("")) {
                strText = driver.findElement(element).getAttribute("textContent");
            } else if (!driver.findElement(element).getAttribute("innerText").equals("")) {
                strText = driver.findElement(element).getAttribute("innerText");
            } else if (!driver.findElement(element).getAttribute("outerText").equals("")) {
                strText = driver.findElement(element).getAttribute("outerText");
            } else if (!driver.findElement(element).getAttribute("innerHTML").equals("")) {
                strText = driver.findElement(element).getAttribute("innerHTML");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strText;
    }
}

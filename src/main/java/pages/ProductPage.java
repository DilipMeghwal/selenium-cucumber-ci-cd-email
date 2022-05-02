package pages;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.Iterator;
import java.util.Set;

public class ProductPage extends BaseClass {
    private WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    By DIV_ABOUT_PRODUCT = By.cssSelector("div[id=\"feature-bullets\"]");

    public String verifyAboutContext() {
        String productContent = "";
        String currentWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> it = windows.iterator();
        while (it.hasNext()) {
            String childWindow = it.next();
            if (!currentWindow.equalsIgnoreCase(childWindow)) {
                driver.switchTo().window(childWindow);
                applyExplicitWait(driver, DIV_ABOUT_PRODUCT);
                Assert.assertTrue(driver.findElements(DIV_ABOUT_PRODUCT).size() > 0, "Verify that “About this item” section is present.");
                productContent = getTextOfElement(driver, DIV_ABOUT_PRODUCT);
                driver.close();
                driver.switchTo().window(currentWindow);
                break;
            }
        }
        return productContent;
    }
}

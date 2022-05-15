package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.BaseClass;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class HomePage extends BaseClass {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    By ICON_HAMBURGER_MENU = By.cssSelector("div[id='nav-main'] div[class='nav-left']");
    By DIV_LEFT_PANEL = By.cssSelector("div[id=\"hmenu-content\"]");
    By A_BACK_TO_MAIN_MENU = By.cssSelector("a[aria-label=\"Back to main menu\"]");
    By SPAN_SORT = By.cssSelector("span[class=\"a-dropdown-container\"]");
    By SPAN_SORT_TEXT = By.cssSelector("span[class=\"a-dropdown-prompt\"]");
    String xpath = "//a[@class='hmenu-item' and contains(., '%s')]";


    public void checkHomePageOpened() {
        applyExplicitWaitBy(driver, ICON_HAMBURGER_MENU);
        Assert.assertTrue(isElementPresent(driver, ICON_HAMBURGER_MENU), "Verify Home page opened successfully.");
    }

    public void clickOnHamburgerMenu() {
        applyExplicitWaitBy(driver, ICON_HAMBURGER_MENU);
        clickOnElement(driver, ICON_HAMBURGER_MENU);
        applyExplicitWaitBy(driver, DIV_LEFT_PANEL);
        Assert.assertTrue(isElementPresent(driver, DIV_LEFT_PANEL), "Verify user can see departments.");
    }

    public void clickOnShopWithDepartment(String department) {
        String.format(xpath, department);
        applyExplicitWait(driver, By.xpath(xpath));
        scrollElementIntoView(driver, xpath);
        clickOnWithVisibleText(driver, xpath);
        Assert.assertTrue(isElementPresent(driver, A_BACK_TO_MAIN_MENU), "Verify department is selected");
    }

    public void clickOnProductType(String productType) {
        String xpath = "//a[@class='hmenu-item' and contains(., '" + productType + "')]";
        applyExplicitWait(driver, By.xpath(xpath));
        scrollElementIntoView(driver, xpath);
        clickOnWithVisibleText(driver, xpath);
        Assert.assertTrue(driver.findElements(A_BACK_TO_MAIN_MENU).size() < 1, "Verify product type is selected");
    }

    public void clickOnBrand(String brand) {
        String xpath = "//li[@class='a-spacing-micro']//a[contains(.,'" + brand + "')]";
        int counter = 0;
        while(counter < 5){
            if(!(driver.findElements(By.xpath(xpath)).size() > 0)){
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                scrollBy(driver, 0, 500);
                counter++;
            }else{
                scrollElementIntoView(driver, xpath);
                break;
            }
        }
        clickOnWithVisibleText(driver, xpath);
        String checkboxXpath = "//li[@class='a-spacing-micro']//a[contains(.,'" + brand + "')]//parent::div//input";
        Assert.assertTrue(driver.findElement(By.xpath(checkboxXpath)).isSelected(), "Verify brand is selected");
    }

    public void selectSortingMethod(String sorting) {
        applyExplicitWait(driver, SPAN_SORT);
        clickOnElement(driver, SPAN_SORT);
        String xpath = "//li[contains(., '" + sorting + "')]";
        applyExplicitWait(driver, By.xpath(xpath));
        clickOnWithVisibleText(driver, xpath);
        Assert.assertTrue(driver.findElement(SPAN_SORT_TEXT).getText().equals(sorting), "Verify product is sorted as " + sorting);
    }

    public void selectProduct(String productIndex) {
        String cssSelector = "div[data-cel-widget='search_result_" + productIndex + "'][data-component-type='s-search-result']";
        applyExplicitWait(driver, By.cssSelector(cssSelector));
        clickOnElement(driver, By.cssSelector(cssSelector));
    }

}

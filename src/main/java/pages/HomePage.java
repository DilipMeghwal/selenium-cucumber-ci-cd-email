package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.BaseClass;

public class HomePage extends BaseClass {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    By ICON_HAMBURGER_MENU = By.cssSelector("div[id='nav-main'] div[class='nav-left']");
    //By LINK_TV_APPLIANCES_ELECTRONICS = By.xpath("//a[@class='hmenu-item' and contains(., 'TV, Appliances, Electronics')]");
    //By LINK_TELEVISION = By.xpath("//a[@class='hmenu-item' and contains(., 'Televisions')]");
    //By LINK_BRAND = By.xpath("//li[@class='a-spacing-micro']//a[contains(.,'Samsung')]");
    By SELECT_SORT = By.cssSelector("select[id=\"s-result-sort-select\"]");
    //By LI_HIGH_TO_LOW = By.xpath("//li[contains(., 'Price: High to Low')]");
    //By DIV_PRODUCT = By.cssSelector("div[data-cel-widget*='search_result_'][data-component-type='s-search-result']");


    public void checkHomePageOpened() {
        applyExplicitWait(ICON_HAMBURGER_MENU);
        Assert.assertTrue(isElementPresent(ICON_HAMBURGER_MENU), "Verify Home page opened successfully.");
    }

    public void clickOnHamburgerMenu() {
        applyExplicitWait(ICON_HAMBURGER_MENU);
        clickOnElement(ICON_HAMBURGER_MENU);
    }

    public void clickOnShopWithDepartment(String department) {
        String xpath = "//a[@class='hmenu-item' and contains(., '" + department + "')]";
        applyExplicitWait(driver.findElement(By.xpath(xpath)));
        clickOnWithVisibleText(xpath);
    }

    public void clickOnProductType(String productType) {
        String xpath = "//a[@class='hmenu-item' and contains(., '" + productType + "')]";
        applyExplicitWait(driver.findElement(By.xpath(xpath)));
        clickOnWithVisibleText(xpath);
    }

    public void clickOnBrand(String brand) {
        String xpath = "//li[@class='a-spacing-micro']//a[contains(.,'" + brand + "')]";
        scrollElementIntoView(xpath);
        applyExplicitWait(driver.findElement(By.xpath(xpath)));
        clickOnWithVisibleText(xpath);
    }

    public void selectSortingMethod(String sorting) {
        clickOnElement(SELECT_SORT);
        String xpath = "//li[contains(., '" + sorting + "')]";
        applyExplicitWait(driver.findElement(By.xpath(xpath)));
        clickOnWithVisibleText(xpath);
    }

    public void selectProduct(String productIndex) {
        String xpath = "div[data-cel-widget*='search_result_" + productIndex + "'][data-component-type='s-search-result']";
        applyExplicitWait(driver.findElement(By.xpath(xpath)));
        clickOnWithVisibleText(xpath);
    }
}

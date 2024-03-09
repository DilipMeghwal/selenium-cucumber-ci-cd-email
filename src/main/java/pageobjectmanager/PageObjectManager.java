package pageobjectmanager;

import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.ProductPage;

import java.util.Objects;

public class PageObjectManager {
    WebDriver driver;
    private HomePage homePage = null;
    private ProductPage productPage = null;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage(WebDriver driver) {
        return Objects.requireNonNullElseGet(homePage, () -> homePage = new HomePage(driver));
    }

    public ProductPage getProductPage(WebDriver driver) {
        return Objects.requireNonNullElseGet(productPage, () -> productPage = new ProductPage(driver));
    }
}

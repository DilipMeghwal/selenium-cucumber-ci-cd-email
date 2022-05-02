package pageObjectManager;

import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.ProductPage;

public class PageObjectManager {
    private HomePage homePage = null;
    private ProductPage productPage = null;

    public HomePage getHomePage(WebDriver driver) {
        return (homePage == null) ? homePage = new HomePage(driver) : homePage;
    }

    public ProductPage getProductPage(WebDriver driver) {
        return (productPage == null) ? productPage = new ProductPage(driver) : productPage;
    }
}

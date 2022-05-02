package pageObjectManager;

import org.openqa.selenium.WebDriver;
import pages.HomePage;

public class PageObjectManager {
    private HomePage homePage = null;

    public HomePage getHomePage(WebDriver driver) {
        return (homePage == null) ? homePage = new HomePage(driver) : homePage;
    }
}

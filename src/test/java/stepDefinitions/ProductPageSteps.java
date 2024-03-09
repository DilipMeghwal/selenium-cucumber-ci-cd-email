package stepDefinitions;

import base.BaseClass;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pageobjectmanager.PageObjectManager;

public class ProductPageSteps extends BaseClass {

    public WebDriver driver;

    public BaseClass baseClass;

    private final PageObjectManager pom;

    public ProductPageSteps(BaseClass baseClass) {
        this.baseClass = baseClass;
        this.driver = baseClass.getDriver();
        pom = baseClass.getPom();
    }

    @Then("Verify that “About this item” section is present and log this section text to console or report.")
    public void verify_that_about_this_item_section_is_present_and_log_this_section_text_to_console_or_report() {
        String productContent = pom.getProductPage(driver).verifyAboutContext();
        System.out.println(productContent);
    }
}

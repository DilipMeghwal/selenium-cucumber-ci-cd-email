package stepDefinitions;

import base.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pageObjectManager.PageObjectManager;

public class ProductPageSteps extends BaseClass {

    public WebDriver driver;

    public BaseClass baseClass;

    public ProductPageSteps(BaseClass baseClass) {
        this.baseClass = baseClass;
        this.driver = baseClass.driver;
    }

    PageObjectManager POM = new PageObjectManager();

    @Then("Verify that “About this item” section is present and log this section text to console or report.")
    public void verify_that_about_this_item_section_is_present_and_log_this_section_text_to_console_or_report() {
        String productContent = POM.getProductPage(driver).verifyAboutContext();
        System.out.println(productContent);
    }
}

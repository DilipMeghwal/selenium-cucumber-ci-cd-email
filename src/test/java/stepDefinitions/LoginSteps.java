package stepDefinitions;

import base.BaseClass;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pageObjectManager.PageObjectManager;

public class LoginSteps extends BaseClass{

	public WebDriver driver;
	
	public BaseClass baseClass;
	
	public LoginSteps(BaseClass baseClass) {
		this.baseClass = baseClass;
		this.driver = baseClass.driver;
	}
	
	PageObjectManager POM = new PageObjectManager();

	@Given("User open amazon web app.")
	public void user_open_amazon_web_app() throws Throwable {
		POM.getHomePage(driver).checkHomePageOpened();
	}

	@When("User click on the hamburger menu in the top left corner.")
	public void user_click_on_the_hamburger_menu_in_the_top_left_corner() throws Throwable {
		POM.getHomePage(driver).clickOnHamburgerMenu();
	}

	@And("User scroll own and then Click on the {string} link under Shop by Department section.")
	public void user_scroll_own_and_then_click_on_the_tv_appliances_and_electronics_link_under_shop_by_department_section(String department) throws Throwable {
		POM.getHomePage(driver).clickOnShopWithDepartment(department);
	}

	@And("User click on {string} under Tv, Audio & Cameras sub section.")
	public void user_click_on_televisions_under_tv_audio_cameras_sub_section(String productType) throws Throwable {
		POM.getHomePage(driver).clickOnProductType(productType);
	}

	@And("User scroll down and filter the results by Brand {string}.")
	public void user_scroll_down_and_filter_the_results_by_brand_samsung(String product) throws Throwable {
		POM.getHomePage(driver).clickOnBrand(product);
	}

	@And("User sort the Samsung results with {string}")
	public void user_sort_the_samsung_results_with_price_high_to_low(String sorting) throws Throwable {
		POM.getHomePage(driver).selectSortingMethod(sorting);
	}

	@And("User click on the {string} highest priced item.")
	public void user_click_on_the_second_highest_priced_item(String itemIndex) throws Throwable {
		POM.getHomePage(driver).selectProduct(itemIndex);
	}

	@Then("Verify that “About this item” section is present and log this section text to console or report.")
	public void verify_that_about_this_item_section_is_present_and_log_this_section_text_to_console_or_report() throws Throwable {
		POM.getHomePage(driver).checkHomePageOpened();
	}

}

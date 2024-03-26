package stepDefinitions;

import base.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pageobjectmanager.PageObjectManager;

public class HomePageSteps {

    public WebDriver driver;

    public BaseClass baseClass;

    private final PageObjectManager pom;

    public HomePageSteps(BaseClass baseClass) {
        this.baseClass = baseClass;
        this.driver = baseClass.getDriver();
        pom = baseClass.getPom();
    }

    @Given("User open amazon web app.")
    public void user_open_amazon_web_app() {
        pom.getHomePage(driver).checkHomePageOpened();
    }

    @When("User click on the hamburger menu in the top left corner.")
    public void user_click_on_the_hamburger_menu_in_the_top_left_corner() {
        pom.getHomePage(driver).clickOnHamburgerMenu();
    }

    @When("User scroll own and then Click on the {string} link under Shop by Department section.")
    public void user_scroll_own_and_then_click_on_the_link_under_shop_by_department_section(String department) {
        pom.getHomePage(driver).clickOnShopWithDepartment(department);
    }

    @When("User click on {string} under Tv, Audio & Cameras sub section.")
    public void user_click_on_under_tv_audio_cameras_sub_section(String productType) {
        pom.getHomePage(driver).clickOnProductType(productType);
    }

    @When("User scroll down and filter the results by Brand {string}.")
    public void user_scroll_down_and_filter_the_results_by_brand(String product) {
        pom.getHomePage(driver).clickOnBrand(product);
    }

    @When("User sort the {string} results with {string}.")
    public void user_sort_the_results_with(String brand, String sorting) {
        pom.getHomePage(driver).selectSortingMethod(sorting);
    }

    @When("User click on the {string} highest priced item.")
    public void user_click_on_the_highest_priced_item(String itemIndex) {
        pom.getHomePage(driver).selectProduct(itemIndex);
    }
}

package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageObjects.LandingsPage;
import utils.TestContextSetup;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductSearchStepDefinitions {
    public TestContextSetup testContextSetup;
    public LandingsPage landingsPage;
    public ProductSearchStepDefinitions(TestContextSetup testContextSetup){
        this.testContextSetup=testContextSetup;
        this.landingsPage=testContextSetup.pageObjectManager.getLandingPage();
    }

    @Given("User is on the GreenKart Landing page")
    public void userIsOnTheGreenKartLandingPage() {
        assertTrue(landingsPage.getTitle().contains("GreenKart"));
    }

    @When("^Search the product with name (.+) and extract actual name of the product$")
    public void searchTheProductWithNameAndExtractActualNameOfTheProduct(String productName) {
        landingsPage.searchItem(productName);
        testContextSetup.homePageProductName=landingsPage.getProductName(productName);

    }


    @And("Added {string} items of selected item product to cart")
    public void addedItemsOfSelectedItemProductToCart(String quantity) {
    landingsPage.incrementQuantity(Integer.parseInt(quantity));
    landingsPage.addToCart();
            }
}

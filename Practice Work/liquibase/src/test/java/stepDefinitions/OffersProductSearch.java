package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageObjects.LandingsPage;
import pageObjects.OffersPage;
import utils.TestContextSetup;



import static org.junit.jupiter.api.Assertions.assertEquals;

public class OffersProductSearch {

    public String offerPageProductName;

    public TestContextSetup testContextSetup;
    public OffersProductSearch(TestContextSetup testContextSetup){
        this.testContextSetup=testContextSetup;
    }

    @Then("^search with the same product name (.+) in offers page$")
    public void searchWithTheSameProductNameInOffersPage(String pageProduct) {
        switchToOffersPage();
        OffersPage offersPage=testContextSetup.pageObjectManager.getOfferPage();
        offersPage.searchItem(pageProduct);
        offerPageProductName= offersPage.getProductName(pageProduct.toLowerCase());
        System.out.println("Offer Page product name is here : "+ offerPageProductName);
    }

    public void switchToOffersPage(){
        LandingsPage landingsPage=testContextSetup.pageObjectManager.getLandingPage();
        landingsPage.selectTopDeals();
        testContextSetup.genericUtils.switchToChildHandler();
    }

    @And("Validate both the product name")
    public void validateBothTheProductName() {
        System.out.println("Product page : "+ testContextSetup.homePageProductName+ " and offer page product name : "+ offerPageProductName);
        assertEquals(testContextSetup.homePageProductName,offerPageProductName);
    }
}

package stepDefinitions;

import io.cucumber.java.en.Then;
import pageObjects.CheckoutPage;
import utils.TestContextSetup;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutStepDefinitions {
    public TestContextSetup testContextSetup;
    public CheckoutPage checkoutPage;
    public CheckoutStepDefinitions(TestContextSetup testContextSetup){
        this.testContextSetup=testContextSetup;
        this.checkoutPage=testContextSetup.pageObjectManager.getCheckoutPage();
    }


    @Then("User has ability to enter promo code and place the order")
    public void userHasAbilityToEnterPromoCodeAndPlaceTheOrder() {
       assertTrue(checkoutPage.verifyPromoBtn());
       assertTrue(checkoutPage.verifyPlaceOrder());
    }

    @Then("^User proceeds to checkout and validate the (.+) items in checkout page$")
    public void userProceedsToCheckoutAndValidateTheNameItemsInCheckoutPage(String checkoutProductName) throws InterruptedException {
        checkoutPage.checkOutItem();

    }
}

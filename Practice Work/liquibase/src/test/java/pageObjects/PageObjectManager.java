package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {
    private final WebDriver driver;

    public PageObjectManager(WebDriver driver){
        this.driver=driver;
    }
    public LandingsPage getLandingPage(){
        return new LandingsPage(driver);
    }
    public OffersPage getOfferPage(){
        return new OffersPage(driver);
    }
    public CheckoutPage getCheckoutPage(){
        return new CheckoutPage(driver);
    }
}

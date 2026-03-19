package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CheckoutPage {
    private final WebDriver driver;
    By cartBag=By.cssSelector("[alt='Cart']");
    By  checkoutButton=By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]");
    By promoButton=By.className("promoBtn");
    By  placeOrder=By.xpath("//button[contains(text(),'Place Order')]");

    public CheckoutPage(WebDriver driver){
        this.driver=driver;
    }
    public void checkOutItem(){
        driver.findElement(cartBag).click();
        driver.findElement(checkoutButton).click();
    }

    public Boolean verifyPromoBtn(){
        return driver.findElement(promoButton).isDisplayed();
    }
    public Boolean verifyPlaceOrder(){
        return driver.findElement(placeOrder).isDisplayed();
    }
    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }
}

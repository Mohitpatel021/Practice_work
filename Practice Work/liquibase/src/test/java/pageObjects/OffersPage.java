package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class OffersPage {
    private final WebDriver driver;
    private final By search= By.xpath("//input[@type='search']");
    private final By productName=By.cssSelector("tr td:nth-child(1)");

    public OffersPage(WebDriver driver){
        this.driver=driver;
    }

    public void searchItem(String name){
        this.driver.findElement(search).sendKeys(name);
    }

    public void getSearchText(){
        this.driver.findElement(search).getText();
    }

    public String getProductName(String name){
        List<WebElement> products= this.driver.findElements(productName);
        for(WebElement web:products) {
            if(!web.getText().isEmpty() && web.getText().toLowerCase().contains(name.toLowerCase())){
                return web.getText();
            }
        }
        return "";
    }


}

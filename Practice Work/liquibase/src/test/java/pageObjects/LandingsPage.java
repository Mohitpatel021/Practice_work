package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class LandingsPage {
    private final WebDriver driver;
    private final By search= By.className("search-keyword");
    private final By productName=By.cssSelector("h4.product-name");
    private final By topDeals=By.linkText("Top Deals");
    private final By increment=By.cssSelector("a.increment");
    private final By addToCart=By.cssSelector(".product-action button");
    public LandingsPage(WebDriver driver){
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
        for(WebElement element:products) {
            String p = element.getText().split("-")[0].trim();
            if (!p.isEmpty() && p.toLowerCase().contains(name.toLowerCase())) {
                return p;
            }
        }
        return "";
    }

    public void selectTopDeals(){
        this.driver.findElement(topDeals).click();
    }

    public WebElement visibilityCondition(WebDriverWait wait){
       return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h4.product-name")));
    }

    public String getTitle(){
        return driver.getTitle();
    }
    public void incrementQuantity(int quantity){
        int i=quantity-1;
        while(i>0){
            driver.findElement(increment).click();
            i--;
        }
    }
    public void addToCart(){
        driver.findElement(addToCart).click();
    }
}

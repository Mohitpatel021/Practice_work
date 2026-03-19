package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.Set;

public class GenericUtils {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public GenericUtils(WebDriver driver,WebDriverWait wait){
        this.driver=driver;
        this.wait=wait;
    }

    public void switchToChildHandler(){
        Set<String> h1=this.driver.getWindowHandles();
        Iterator<String> i=h1.iterator();
        String parentHandler=i.next();
        String childHandler=i.next();
        this.driver.switchTo().window(childHandler);
    }

}

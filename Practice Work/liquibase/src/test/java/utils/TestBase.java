package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class TestBase {
    private WebDriver driver;
    public WebDriver webDriverManager() throws IOException {
        if(driver==null) {
            FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+ "//src//test//resources//global.properties");
            Properties prop=new Properties();
            prop.load(fis);
            if(prop.getProperty("browser").equalsIgnoreCase("chrome")){
                this.driver=new ChromeDriver();
            }
            this.driver.get(prop.getProperty("url"));
            this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        return driver;
    }




}

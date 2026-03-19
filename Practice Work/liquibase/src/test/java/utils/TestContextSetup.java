package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.PageObjectManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

public class TestContextSetup {
    public WebDriver driver;
    public String homePageProductName;
    public WebDriverWait wait;
    public PageObjectManager pageObjectManager;
    public TestBase testBase;
    public GenericUtils genericUtils;
    public TestContextSetup() throws IOException {
        this.testBase=new TestBase();
        pageObjectManager=new PageObjectManager(testBase.webDriverManager());
        this.wait=new WebDriverWait(testBase.webDriverManager(), Duration.ofSeconds(10));
        this.genericUtils=new GenericUtils(testBase.webDriverManager(),wait);
    }
}

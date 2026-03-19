package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.TestContextSetup;

import java.io.File;
import java.io.IOException;

public class Hooks {

    private final TestContextSetup testContextSetup;
    public Hooks(TestContextSetup testContextSetup){
        this.testContextSetup=testContextSetup;
    }

    @After
    public void closeBrowser() throws IOException {
        testContextSetup.testBase.webDriverManager().quit();
    }

    @AfterStep
    public void takeScreenShots(Scenario scenario) throws IOException {
        WebDriver driver=testContextSetup.testBase.webDriverManager();
        if(scenario.isFailed()){
            File screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            byte[] bytes = FileUtils.readFileToByteArray(screenshotAs);
            System.out.println("File name after taken screenshot: "+screenshotAs.getName());
            scenario.attach(bytes,"image/png",screenshotAs.getName());
        }
    }
}

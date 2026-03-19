package cucumberOptions;//package cucumberOptions;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/feature",glue = "stepDefinitions",monochrome = true ,plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","rerun:target/failed_test.txt"})
public class JunitTestRunnerTest {

}

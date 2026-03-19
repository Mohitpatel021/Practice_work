//package cucumberOptions;
//
//import io.cucumber.testng.AbstractTestNGCucumberTests;
//import io.cucumber.testng.CucumberOptions;
//import org.testng.annotations.DataProvider;
//
//
//@CucumberOptions(features = "@target/failed_test.txt",glue = "stepDefinitions",monochrome = true ,plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","rerun:target/failed_test.txt"})
//public class FailedNGTestRunner extends AbstractTestNGCucumberTests {
//
//
//    @Override
//    @DataProvider(parallel = true)
//    public Object[][] scenarios(){
//        return super.scenarios();
//    }
//}

//package cucumberOptions;
//
//import io.cucumber.testng.AbstractTestNGCucumberTests;
//import io.cucumber.testng.CucumberOptions;
//import org.testng.annotations.DataProvider;
//
//
//@CucumberOptions(features = "src/test/java/feature",glue = "stepDefinitions",monochrome = true ,plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","rerun:target/failed_test.txt"})
//public class ProductNGTestRunner extends AbstractTestNGCucumberTests {
//
//    @Override
//    @DataProvider(parallel = true)
//    public Object[][] scenarios(){
//        return super.scenarios();
//    }
//}

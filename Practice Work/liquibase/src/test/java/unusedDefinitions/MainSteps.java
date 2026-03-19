package unusedDefinitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Map;

public class MainSteps {

    @Given("User is on landing page")
    public void user_is_on_landing_page(){
        System.out.println("user is on landing page");
    }

    @Then("Home page is displaying")
    public void home_page_is_displaying(){
        System.out.println("user is currently displaying the home page");
    }
    @Then("Cards should be display")
    public void cards_should_be_display(){
        System.out.println("cards information are displaying properly");
    }

    @When("User is login to the application with {string} and password {string}")
    public void userIsLoginToTheApplicationWithAndPassword(String username, String password) {
        System.out.println("User is login to the application with username : "+username+ " "+ " and password : "+password);
    }

    @When("^User is login with (.+) and password (.+)$")
    public void userIsLoginWithAndPassword(int username, String password) {
        System.out.println("User is login to the application with username with int: "+username+ " "+ " and password : "+username);

    }

    @Then("Home page should display")
    public void homePageShouldDisplay() {
        System.out.println("User is login to the application with username");

    }

    @And("Card details should display")
    public void cardDetailsShouldDisplay() {
        System.out.println("User is login to the application with username : ");

    }

    @When("User is login with <username> and password {string}")
    public void userIsLoginWithUsernameAndPassword(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("User is login to the application")
    public void userIsLoginToTheApplication(Map<String,String> data) {
        System.out.println(data);

    }

    @Given("Setup the entries in the db")
    public void setupTheEntriesInTheDb() {
        System.out.println("************");
        System.out.println("Entries are setup perfectly");
    }

    @When("launch the browser using env variables")
    public void launchTheBrowserUsingEnvVariables() {
        System.out.println("Launching the browser using env variables");
    }

    @And("hit the url to landup on login page")
    public void hitTheUrlToLandupOnLoginPage() {

        System.out.println("Hiting the url to landed up on login page");
    }
}

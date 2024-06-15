package stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pageObj.LoginPage;
import utility.InitialHelper;
import utility.WaitHelper;

public class LoginStep {

    WebDriver driver;
    LoginPage loginPage;
    //    WebDriverWait driverWait;
    Logger logger;
    WaitHelper waitHelper;

    @Given("User opens the browser")
    public void userOpensTheBrowser() {
        driver = InitialHelper.iniitializeChromeBrowser ( );
        loginPage = new LoginPage (driver);
//        driverWait = new WebDriverWait (driver, Duration.ofSeconds (8));
        logger = LogManager.getLogger (LoginStep.class);
        System.out.println("Logger initialized: " + logger);
        logger.info ("Logger initialized in this Class");
        waitHelper = new WaitHelper (driver);
    }

    @When("^User opens the URL \"([^\"]*)\"$")
    public void userOpensTheURL(String providerURL) {
        logger.info ("User enters the URL");
        driver.get (providerURL);
    }

    @Then("^User clicks on sign-in$")
    public void userClicksOnSignIn() {
        logger.info ("User clicked on sign-in");
        loginPage.clickSignIn ( );
    }

    @And("^User enters email \"([^\"]*)\" and password \"([^\"]*)\" and clicks login$")
    public void userEntersEmailAndPasswordAndClicksLogin(String userName, String password) {
        try {
            logger.info ("Try to login with these creds: \n Username: " + userName + "\n Paswword: " + password);
            // driverWait.until (ExpectedConditions.visibilityOf (loginPage.getUserName ()));
            waitHelper.WaitForElement (loginPage.getUserName ( ), 10);
            loginPage.setUserName (userName);
            loginPage.setPassWord (password);
            loginPage.clickLoginButton ( );
        } catch (Exception e) {
            throw new RuntimeException (e);
        }
    }

    @Then("^User lands on provider homepage$")
    public void userLandsOnProviderHomepage() {
        try {
//            driverWait.until (ExpectedConditions.visibilityOf (loginPage.getHomePage ()));
            Thread.sleep (5000);
            loginPage.verifyHomePage ( );
        } catch (Exception e) {
            throw new RuntimeException (e);
        }
    }

    @When("^User clicks on logout$")
    public void userClicksOnLogout() {
        //driverWait.withTimeout (Duration.ofSeconds (5000));
        waitHelper.WaitForElement (loginPage.getHomePage ( ), 5);
        loginPage.userLogsOut ( );

    }

    @Then("User closes browser")
    public void userClosesBrowser() throws InterruptedException {
        driver.quit ( );
    }

    @And("User is shown error message")
    public void userIsShownErrorMessage() {
        loginPage.verifyErrorMsg ( );
    }
}

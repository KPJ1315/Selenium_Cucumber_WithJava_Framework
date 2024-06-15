package stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObj.LoginPage;
import utility.WaitHelper;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LoginStep {

    WebDriver driver;
    LoginPage loginPage;
//    WebDriverWait driverWait;
    Logger logger;
    WaitHelper waitHelper;



    @Given("User opens the browser")
    public void userOpensTheBrowser() {
        System.setProperty ("webdriver.chrome.driver", "D:\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions ( );
        options.setBinary ("D:\\chrome-win64\\chrome.exe");
        options.addArguments ("--disable-notifications");
        driver = new ChromeDriver (options);
        driver.manage ( ).window ( ).maximize ( );
        driver.manage ( ).timeouts ( ).implicitlyWait (8, TimeUnit.SECONDS);
        loginPage = new LoginPage (driver);
//        driverWait = new WebDriverWait (driver, Duration.ofSeconds (8));
        logger = LogManager.getLogger (this.getClass ());
        waitHelper = new WaitHelper (driver);
    }

    @When("^User opens the URL \"([^\"]*)\"$")
    public void userOpensTheURL(String providerURL) {
        driver.get (providerURL);
    }

    @Then("^User clicks on sign-in$")
    public void userClicksOnSignIn() {
        loginPage.clickSignIn ( );
    }

    @And("^User enters email \"([^\"]*)\" and password \"([^\"]*)\" and clicks login$")
    public void userEntersEmailAndPasswordAndClicksLogin(String userName, String password) {
        try {
            logger.info ("Try to login with these creds: \n Username: "+userName+"\n Paswword: "+password);
         // driverWait.until (ExpectedConditions.visibilityOf (loginPage.getUserName ()));
            waitHelper.WaitForElement (loginPage.getUserName (),10 );
          loginPage.setUserName (userName);
          loginPage.setPassWord (password);
          loginPage.clickLoginButton( );
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
        waitHelper.WaitForElement (loginPage.getHomePage (),5);
        loginPage.userLogsOut ( );

    }

    @Then("User closes browser")
    public void userClosesBrowser() throws InterruptedException {
        driver.quit ( );
    }

    @And("User is shown error message")
    public void userIsShownErrorMessage() {
        loginPage.verifyErrorMsg();
    }
}

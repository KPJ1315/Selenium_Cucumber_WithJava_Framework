package stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObj.LoginPage;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LoginStep {

    WebDriver driver;
    LoginPage loginPage;
    WebDriverWait driverWait;


    @Given("User opens the browser")
    public void userOpensTheBrowser() {
        System.setProperty ("webdriver.chrome.driver", "D:\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions ( );
        options.setBinary ("D:\\chrome-win64\\chrome.exe");
        options.addArguments ("--disable-notifications");
        driver = new ChromeDriver (options);
        driver.manage ( ).window ( ).maximize ( );
        driver.manage ( ).timeouts ( ).implicitlyWait (10, TimeUnit.SECONDS);
        loginPage = new LoginPage (driver);
        driverWait = new WebDriverWait (driver, Duration.ofSeconds (10));
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
          driverWait.until (ExpectedConditions.visibilityOf (loginPage.getUserName ()));
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
            loginPage.verifyHomePage ( );
        } catch (Exception e) {
            throw new RuntimeException (e);
        }
    }

    @When("^User clicks on logout$")
    public void userClicksOnLogout() {
        driverWait.withTimeout (Duration.ofSeconds (5000));
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

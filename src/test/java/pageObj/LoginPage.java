package pageObj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements (driver, this);
    }

    @FindBy(id = "15044")
    @CacheLookup
    WebElement signIn;

    @FindBy(xpath = "//input[@id='emailid']")
    @CacheLookup
    WebElement userName;

    @FindBy(xpath = "//input[@id='pword']")
    @CacheLookup
    WebElement passWord;

    @FindBy(id = "signin")
    @CacheLookup
    WebElement loginBtn;

    @FindBy(className = "bannerimage")
    @CacheLookup
    WebElement homePageBanner;

    @FindBy(id = "dropdown-list")
    @CacheLookup
    WebElement logoutDropDown;

    @FindBy(id = "logimg")
    @CacheLookup
    WebElement logOutImg;

    public static final String err1 = "//div[contains(text(),'Invalid Username or Password')]";
    @FindBy(xpath = err1)
    WebElement lgnErrorMsg1;

    public static final String err2 ="//div[contains(text(),\"Couldn't find your Account\")]";
    @FindBy(xpath = err2 )
    WebElement lgnErrorMsg2;

    public static final String usrErrReq = "//div[contains(text(),'Required')]";
    @FindBy(xpath = usrErrReq)
    WebElement usrNameErrorMsg;

    public static final String usrErrInvalid ="//div[contains(text(), 'Invalid email')]";
    @FindBy(xpath = usrErrInvalid)
    WebElement usrNameInvalidErrorMsg;

    public static final String psdErr="//div[contains(text(),'Required')]";
    @FindBy(xpath = psdErr)
    WebElement pswdErrorMsg;

    Logger logger;

    public WebElement getUserName() {
        return userName;
    }

    private WebElement getPassWord() {
        return passWord;
    }

    private WebElement getLoginBtn() {
        return loginBtn;
    }

    public WebElement getHomePage() {
        return homePageBanner;
    }

    public void clickSignIn() {
        signIn.click ( );
        logger = LogManager.getLogger(this.getClass());
    }

    public void setUserName(String usrName) {
        userName.clear ( );
        userName.click ( );
        userName.sendKeys (usrName);
        new WebDriverWait (driver, Duration.ofSeconds (2));
    }

    public void setPassWord(String pswrd) {
        passWord.clear ( );
        passWord.click ( );
        passWord.sendKeys (pswrd);
        new WebDriverWait (driver, Duration.ofSeconds (2));
    }

    public void clickLoginButton() {
        loginBtn.click ( );
        new WebDriverWait (driver, Duration.ofSeconds (2));
    }

    public void verifyHomePage() {
        if (homePageBanner.isDisplayed ( )) {
            logger.info ("Home Page verified");
            Assert.assertTrue (true);
            return;
        }
        Assert.assertFalse (false);
        logger.info ("Home Page not verified");
    }

    public void userLogsOut() {
        logoutDropDown.click ( );
        logOutImg.click ( );
    }


    public void verifyErrorMsg() {
        boolean isAnyErrorDisplayed = false;

        try {
            if (usrNameInvalidErrorMsg.isDisplayed ( )) {
                isAnyErrorDisplayed = true;
            }
        } catch (NoSuchElementException | NullPointerException e) {
            // Handle exception if the element is not found or not initialized
        }

        try {
            if (lgnErrorMsg1.isDisplayed ( )) {
                isAnyErrorDisplayed = true;
            }
        } catch (NoSuchElementException | NullPointerException e) {
            // Handle exception if the element is not found or not initialized
        }

        try {
            if (lgnErrorMsg2.isDisplayed ( )) {
                isAnyErrorDisplayed = true;
            }
        } catch (NoSuchElementException | NullPointerException e) {
            // Handle exception if the element is not found or not initialized
        }

        try {
            if (usrNameErrorMsg.isDisplayed ( )) {
                isAnyErrorDisplayed = true;
            }
        } catch (NoSuchElementException | NullPointerException e) {
            // Handle exception if the element is not found or not initialized
        }

        try {
            if (pswdErrorMsg.isDisplayed ( )) {
                isAnyErrorDisplayed = true;
            }
        } catch (NoSuchElementException | NullPointerException e) {
            // Handle exception if the element is not found or not initialized
        }

        Assert.assertTrue ("No error message is displayed when one was expected.", isAnyErrorDisplayed);
    }
}
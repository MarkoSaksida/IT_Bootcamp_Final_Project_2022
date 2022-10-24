package automationpractice.com.tests;

import automationpractice.com.helpers.DataProviders;
import automationpractice.com.pages.AccountCreateOrLogInPage;
import automationpractice.com.pages.ForgotYourPasswordPage;
import automationpractice.com.pages.HomePage;
import automationpractice.com.pages.MyAccountPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginLogoutTests {


    private WebDriver webDriver;
    private HomePage homePage;
    private AccountCreateOrLogInPage accountCreateOrLogInPage;
    private MyAccountPage myAccountPage;
    private ForgotYourPasswordPage forgotYourPasswordPage;

    @BeforeMethod
    public void configure() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        homePage = new HomePage(webDriver);
        accountCreateOrLogInPage = new AccountCreateOrLogInPage(webDriver);
        myAccountPage = new MyAccountPage(webDriver);
        forgotYourPasswordPage = new ForgotYourPasswordPage(webDriver);
        webDriver.get("http://automationpractice.com/index.php");
        webDriver.manage().window().maximize();

    }

    @AfterMethod
    public void quitWebDriver() {
        webDriver.quit();
    }

    @Test(dataProvider = "incompleteCredentialsForSignIn", dataProviderClass = DataProviders.class)
    public void signIntoAccountMissingTheValueInEmailField_expectAuthenticationFail (String email, String password) {
        homePage.clickSignInBtn();
        accountCreateOrLogInPage.setSignInEmailAddressField(email);
        accountCreateOrLogInPage.setSignInPasswordField(password);
        accountCreateOrLogInPage.clickSignInButton();
        Assert.assertEquals(accountCreateOrLogInPage.getInvalidSignInAlert(),
                "There is 1 error\n" +
                        "An email address required.");

    }

    @Test
    public void signIntoAccountWithInvalidDataInEmailField_expectAuthenticationFail() {
        homePage.clickSignInBtn();
        accountCreateOrLogInPage.setSignInEmailAddressField("bootcamp");
        accountCreateOrLogInPage.setSignInPasswordField("123456");
        accountCreateOrLogInPage.clickSignInButton();
        Assert.assertEquals(accountCreateOrLogInPage.getInvalidSignInAlert(),
                "There is 1 error\n" +
                        "Invalid email address.");

    }

    @Test
    public void signIntoAccountWithValidEmailInvalidPassword_expectAuthenticationFail() {
        homePage.clickSignInBtn();
        accountCreateOrLogInPage.setSignInEmailAddressField("t@t.com");
        accountCreateOrLogInPage.setSignInPasswordField("123456");
        accountCreateOrLogInPage.clickSignInButton();
        Assert.assertEquals(accountCreateOrLogInPage.getInvalidSignInAlert(),
                "There is 1 error\n" +
                        "Authentication failed.");

    }

    @Test
    public void signInAndSignOutWithValidCredentials_expectSuccessfulAuthenticationAndSignOut() {
        homePage.clickSignInBtn();
        accountCreateOrLogInPage.setSignInEmailAddressField("tester@tester.rs");
        accountCreateOrLogInPage.setSignInPasswordField("test123456");
        accountCreateOrLogInPage.clickSignInButton();
        Assert.assertEquals(webDriver.getTitle(), "My account - My Store");
        myAccountPage.clickSignOutButton();
        Assert.assertEquals(webDriver.getTitle(), "Login - My Store");
    }

    @Test
    public void retrieveForgottenPassword_expectSuccessfullySentConfirmationEmail() {
        homePage.clickSignInBtn();
        accountCreateOrLogInPage.clickOnForgottenPasswordBtn();
        Assert.assertEquals(webDriver.getTitle(), "Forgot your password - My Store");
        forgotYourPasswordPage.inputEmailToRetrievePassword("t@t.com");
        forgotYourPasswordPage.clickRetrievePasswordBtn();
        Assert.assertEquals(forgotYourPasswordPage.getConfirmationMailSentAlert(),
                "A confirmation email has been sent to your address: t@t.com");
    }


}

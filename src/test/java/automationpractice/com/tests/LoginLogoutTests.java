package automationpractice.com.tests;

import automationpractice.com.base.BaseTest;
import automationpractice.com.helpers.DataProviders;
import automationpractice.com.pages.AccountCreateOrLogInPage;
import automationpractice.com.pages.ForgotYourPasswordPage;
import automationpractice.com.pages.HomePage;
import automationpractice.com.pages.AccountPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginLogoutTests extends BaseTest {
    private HomePage homePage;
    private AccountCreateOrLogInPage accountCreateOrLogInPage;
    private AccountPage accountPage;
    private ForgotYourPasswordPage forgotYourPasswordPage;

    @BeforeMethod
    public void configure() {
        homePage = new HomePage();
        accountCreateOrLogInPage = new AccountCreateOrLogInPage();
        accountPage = new AccountPage();
        forgotYourPasswordPage = new ForgotYourPasswordPage();

    }

    @AfterMethod
    public void pageCleanup() {
        webDriver.manage().deleteAllCookies();
    }

    @Test(priority = 10, dataProvider = "incompleteCredentialsForSignIn", dataProviderClass = DataProviders.class)
    public void signIntoAccountMissingTheValueInEmailField_expectAuthenticationFail (String email, String password) {
        homePage.clickSignInBtn();
        accountCreateOrLogInPage.setSignInEmailAddressField(email);
        accountCreateOrLogInPage.setSignInPasswordField(password);
        accountCreateOrLogInPage.clickSignInButton();
        Assert.assertEquals(accountCreateOrLogInPage.getInvalidSignInAlert(),
                "There is 1 error\n" +
                        "An email address required.");

    }

    @Test (priority = 20)
    public void signIntoAccountWithInvalidDataInEmailField_expectAuthenticationFail() {
        homePage.clickSignInBtn();
        accountCreateOrLogInPage.setSignInEmailAddressField("bootcamp");
        accountCreateOrLogInPage.setSignInPasswordField("123456");
        accountCreateOrLogInPage.clickSignInButton();
        Assert.assertEquals(accountCreateOrLogInPage.getInvalidSignInAlert(),
                "There is 1 error\n" +
                        "Invalid email address.");
    }

    @Test (priority = 30)
    public void signIntoAccountWithValidEmailInvalidPassword_expectAuthenticationFail() {
        homePage.clickSignInBtn();
        accountCreateOrLogInPage.setSignInEmailAddressField("t@t.com");
        accountCreateOrLogInPage.setSignInPasswordField("123456");
        accountCreateOrLogInPage.clickSignInButton();
        Assert.assertEquals(accountCreateOrLogInPage.getInvalidSignInAlert(),
                "There is 1 error\n" +
                        "Authentication failed.");
    }

    @Test (priority = 30)
    public void signInAndSignOutWithValidCredentials_expectSuccessfulAuthenticationAndSignOut() {
        homePage.clickSignInBtn();
        accountCreateOrLogInPage.setSignInEmailAddressField("tester@tester.rs");
        accountCreateOrLogInPage.setSignInPasswordField("test123456");
        accountCreateOrLogInPage.clickSignInButton();
        Assert.assertEquals(webDriver.getTitle(), "My account - My Store");
        accountPage.clickSignOutButton();
        Assert.assertEquals(webDriver.getTitle(), "Login - My Store");
    }

    @Test (priority = 40)
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

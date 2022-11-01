package automationpractice.com.tests;

import automationpractice.com.base.BaseTest;
import automationpractice.com.helpers.DataProviders;
import automationpractice.com.pages.AccountCreateOrLogInPage;
import automationpractice.com.pages.CreateAccountPage;
import automationpractice.com.pages.HomePage;
import automationpractice.com.pages.AccountPage;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateAccountTests extends BaseTest {

    private HomePage homePage;
    private AccountCreateOrLogInPage accountCreateOrLogInPage;
    private CreateAccountPage createAccountPage;
    private AccountPage accountPage;


    @BeforeMethod
    public void pageSetup() {
        homePage = new HomePage();
        accountCreateOrLogInPage = new AccountCreateOrLogInPage();
        createAccountPage = new CreateAccountPage();
        accountPage = new AccountPage();
    }

    @AfterMethod
    public void pageCleanup() {
        webDriver.manage().deleteAllCookies();
    }

    @Test(priority = 10, dataProvider = "invalidEmailDataForCreateAccountEmailField", dataProviderClass = DataProviders.class)
    public void createAccountWithInvalidDataInCreateAccountEmailField_expectFailToStartCreatingAccount(String email) {
        homePage.clickSignInBtn();
        accountCreateOrLogInPage.setSignUpEmailField(email);
        accountCreateOrLogInPage.clickCreateAnAccountBtn();
        Assert.assertEquals(accountCreateOrLogInPage.getInvalidEmailAddressMessage(),
                "Invalid email address.");

    }

    @Test (priority = 20)
    public void createAccountWithEmailAlreadyRegistered_expectFailToStartCreatingAccount() {
        homePage.clickSignInBtn();
        accountCreateOrLogInPage.setSignUpEmailField("t@t.com");
        accountCreateOrLogInPage.clickCreateAnAccountBtn();
        Assert.assertEquals(accountCreateOrLogInPage.getInvalidEmailAddressMessage(),
                "An account using this email address has already been registered. Please enter a valid password or request a new one.");

    }

    @Test (priority = 30, dataProvider = "validDataForAccountCreation", dataProviderClass = DataProviders.class)
    public void createAccountWithValidData_expectCreatingValidAccount(String title) {
        homePage.clickSignInBtn();
        accountCreateOrLogInPage.setSignUpEmailField(Faker.instance().bothify("?????###@mail.de"));
        accountCreateOrLogInPage.clickCreateAnAccountBtn();
        createAccountPage.getAccountCreationForm();
        createAccountPage.clickTitleRadioBtn(title);
        createAccountPage.setFirstNameField(Faker.instance().name().firstName());
        createAccountPage.setLastNameField(Faker.instance().name().lastName());
        createAccountPage.setPasswordField(Faker.instance().bothify("??????"));
        createAccountPage.setDayOfBirthDropdown(1);
        createAccountPage.setMonthOfBirthDropdown(1);
        createAccountPage.setYearOfBirthDropdown(2000);
        createAccountPage.checkSubscribeToNewsletterCheckbox();
        createAccountPage.checkSubscribeToSpecialOffersCheckbox();
        createAccountPage.setCompanyField("Bootcamp");
        createAccountPage.setAddressField(Faker.instance().address().streetAddress());
        createAccountPage.setAddressLine2Field(Faker.instance().address().secondaryAddress());
        createAccountPage.setCityField("San Francisco");
        createAccountPage.setStateDropdown(1);
        createAccountPage.setPostalCodeField(11111);
        createAccountPage.setCountryDropdown(21);
        createAccountPage.setAdditionalInformationField("leave the package with the neighbor in appartment 1");
        createAccountPage.setHomePhoneField("123-456-789");
        createAccountPage.setMobilePhoneField("123-456-789");
        createAccountPage.setAddressAliasField("home");
        createAccountPage.clickRegisterButton();
        Assert.assertEquals(accountPage.getSuccessfulSignupAlert(), "Welcome to your account. Here you can manage all of your personal information and orders.");
        accountPage.clickSignOutButton();
    }

}
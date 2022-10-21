package automationpractice.com.tests;

import automationpractice.com.pages.AccountCreateOrLogInPage;
import automationpractice.com.pages.CreateAccountPage;
import automationpractice.com.pages.HomePage;
import automationpractice.com.pages.MyAccountPage;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class CreateAccountTests {


    private WebDriver webDriver;
    private HomePage homePage;
    private AccountCreateOrLogInPage accountCreateOrLogInPage;
    private CreateAccountPage createAccountPage;
    private MyAccountPage myAccountPage;


    @BeforeMethod
    public void configure() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        homePage = new HomePage(webDriver);
        accountCreateOrLogInPage = new AccountCreateOrLogInPage(webDriver);
        createAccountPage = new CreateAccountPage(webDriver);
        myAccountPage = new MyAccountPage(webDriver);
        webDriver.get("http://automationpractice.com/index.php");
        webDriver.manage().window().maximize();

    }

    @AfterMethod
    public void quitWebDriver() {
        webDriver.quit();
    }


    @DataProvider(name = "invalidEmailDataForCreateAccountEmailField")
    public Object[][] invalidEmailDataForCreateAccountEmailField() {
        return new Object[][]{
                {" "},
                {"@mail.de"},
                {"mail@mail"},
        };
    }

    @Test(dataProvider = "invalidEmailDataForCreateAccountEmailField")
    public void createAccountWithInvalidDataInCreateAccountEmailField_expectFailToStartCreatingAccount(String email) {
        homePage.clickSignInBtn();
        accountCreateOrLogInPage.setSignUpEmailField(email);
        accountCreateOrLogInPage.clickCreateAnAccountBtn();
        Assert.assertEquals(accountCreateOrLogInPage.getInvalidEmailAddressMessage(),
                "Invalid email address.");

    }

    @Test
    public void signIntoAccountWithInvalidDataInEmailField_expectAuthenticationFail() {
        homePage.clickSignInBtn();
        accountCreateOrLogInPage.setSignUpEmailField("t@t.com");
        accountCreateOrLogInPage.clickCreateAnAccountBtn();
        Assert.assertEquals(accountCreateOrLogInPage.getInvalidEmailAddressMessage(),
                "An account using this email address has already been registered. Please enter a valid password or request a new one.");

    }

    @DataProvider(name = "validDataForAccountCreation")
    public Object[][] validDataForAccountCreation() {
        return new Object[][]{
                {"mr"},
                {"ms"},
        };
    }

    @Test(dataProvider = "validDataForAccountCreation")
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
        Assert.assertEquals(myAccountPage.getSuccessfulSignupAlert(), "Welcome to your account. Here you can manage all of your personal information and orders.");
    }

}

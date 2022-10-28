package automationpractice.com.pages;

import automationpractice.com.base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateAccountPage extends BaseTest {

    public CreateAccountPage() {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(className = "account_creation")
    private WebElement accountCreationForm;

    @FindBy(id = "id_gender1")
    private WebElement customerTitleMr;

    @FindBy(id = "id_gender2")
    private WebElement customerTitleMrs;

    @FindBy(id = "customer_firstname")
    private WebElement customerFirstName;

    @FindBy(id = "customer_lastname")
    private WebElement customerLastName;

    @FindBy(id = "newsletter")
    private WebElement newsletterCheckbox;

    @FindBy(id = "optin")
    private WebElement specialOffersCheckbox;

    @FindBy(id = "email")
    private WebElement customerEmail;

    @FindBy(id = "passwd")
    private WebElement customerPassword;

    @FindBy(id = "days")
    private WebElement customerDayOfBirth;

    @FindBy(id = "months")
    private WebElement customerMonthOfBirth;

    @FindBy(id = "years")
    private WebElement customerYearOfBirth;

    @FindBy(id = "firstname")
    private WebElement customerFirstNameAddress;

    @FindBy(id = "lastname")
    private WebElement customerLastNameAddress;

    @FindBy(id = "company")
    private WebElement customerCompany;

    @FindBy(id = "address1")
    private WebElement customerAddress;

    @FindBy(id = "address2")
    private WebElement customerAddressLine2;

    @FindBy(id = "city")
    private WebElement customerCity;

    @FindBy(id = "id_state")
    private WebElement customerState;

    @FindBy(id = "postcode")
    private WebElement customerPostalCode;

    @FindBy(id = "id_country")
    private WebElement customerCountry;

    @FindBy(id = "other")
    private WebElement customerAdditionalInfo;

    @FindBy(id = "phone")
    private WebElement customerHomePhone;

    @FindBy(id = "phone_mobile")
    private WebElement customerMobilePhone;

    @FindBy(id = "alias")
    private WebElement customerAddressAlias;

    @FindBy(id = "submitAccount")
    private WebElement registerBtn;

    public void getAccountCreationForm() {
        WebDriverWait waitForAccountCreationForm = new WebDriverWait(webDriver,Duration.ofSeconds(10));
        waitForAccountCreationForm.until(ExpectedConditions.visibilityOf(accountCreationForm));
    }


    public void clickTitleRadioBtn(String title) {
        if (title.equalsIgnoreCase("mr")) {
            this.customerTitleMr.click();
        } else if (title.equalsIgnoreCase("ms")) {
            this.customerTitleMrs.click();
        }
    }

    public void setFirstNameField(String firstName) {
        this.customerFirstName.clear();
        this.customerFirstName.sendKeys(firstName);
    }

    public void setLastNameField(String lastName) {
        this.customerLastName.clear();
        this.customerLastName.sendKeys(lastName);
    }

    public String getEmailField() {
        return this.customerEmail.getText();
    }

    public void setPasswordField(String password) {
        this.customerPassword.clear();
        this.customerPassword.sendKeys(password);
    }

    public void setDayOfBirthDropdown(int dayOfBirth) {
        Select dropdown = new Select(this.customerDayOfBirth);
        dropdown.selectByValue(String.valueOf(dayOfBirth));
    }

    public void setMonthOfBirthDropdown(int monthOfBirth) {
        Select dropdown = new Select(this.customerMonthOfBirth);
        dropdown.selectByValue(String.valueOf(monthOfBirth));
    }

    public void setYearOfBirthDropdown(int yearOfBirth) {
        Select dropdown = new Select(this.customerYearOfBirth);
        dropdown.selectByValue(String.valueOf(yearOfBirth));
    }

    public void checkSubscribeToNewsletterCheckbox() {
        this.newsletterCheckbox.click();
    }

    public void checkSubscribeToSpecialOffersCheckbox() {
        this.specialOffersCheckbox.click();
    }

    public void setCompanyField(String company) {
        this.customerCompany.clear();
        this.customerCompany.sendKeys(company);
    }

    public void setAddressField(String address) {
        this.customerAddress.clear();
        this.customerAddress.sendKeys(address);
    }

    public void setAddressLine2Field(String addressLine2) {
        this.customerAddressLine2.clear();
        this.customerAddressLine2.sendKeys(addressLine2);
    }

    public void setCityField(String city) {
        this.customerCity.clear();
        this.customerCity.sendKeys(city);
    }

    public void setStateDropdown(int state) {
        Select dropdown = new Select(this.customerState);
        dropdown.selectByValue(String.valueOf(state));
    }

    public void setPostalCodeField(int postalCode) {
        this.customerPostalCode.clear();
        this.customerPostalCode.sendKeys(String.valueOf(postalCode));
    }

    public void setCountryDropdown(int country) {
        Select dropdown = new Select(this.customerCountry);
        dropdown.selectByValue(String.valueOf(country));
    }

    public void setAdditionalInformationField(String additionalInfo) {
        this.customerAdditionalInfo.clear();
        this.customerAdditionalInfo.sendKeys(additionalInfo);
    }

    public void setHomePhoneField(String homePhone) {
        this.customerHomePhone.clear();
        this.customerHomePhone.sendKeys(homePhone);
    }

    public void setMobilePhoneField(String mobilePhone) {
        this.customerMobilePhone.clear();
        this.customerMobilePhone.sendKeys(mobilePhone);
    }

    public void setAddressAliasField(String addressAlias) {
        this.customerAddressAlias.clear();
        this.customerAddressAlias.sendKeys(addressAlias);
    }

    public void clickRegisterButton() {
        this.registerBtn.click();
    }
}

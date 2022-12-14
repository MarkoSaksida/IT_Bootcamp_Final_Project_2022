package automationpractice.com.pages;

import automationpractice.com.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountCreateOrLogInPage extends BaseTest {
    public AccountCreateOrLogInPage() {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id = "email_create")
    private WebElement signUpEmailField;

    @FindBy(css = ".btn.btn-default.button.button-medium.exclusive")
    private WebElement createAnAccountBtn;

    @FindBy(id = "email")
    private WebElement signInEmail;

    @FindBy(id = "passwd")
    private WebElement signInPassword;

    @FindBy(id = "SubmitLogin")
    private WebElement signInBtn;

    @FindBys({@FindBy (css = ".lost_password.form-group"), @FindBy(tagName = "a")})
    private WebElement forgottenPasswordBtn;

    public void setSignUpEmailField(String email) {
        this.signUpEmailField.clear();
        this.signUpEmailField.sendKeys(email);
    }

    public void clickCreateAnAccountBtn() {
        this.createAnAccountBtn.click();
    }

    public String getInvalidEmailAddressMessage() {
        WebElement invalidEmailAddress = new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("create_account_error")));
        return invalidEmailAddress.getText();
    }

    public void setSignInEmailAddressField(String email) {
        this.signInEmail.clear();
        this.signInEmail.sendKeys(email);
    }

    public void setSignInPasswordField(String password) {
        this.signInPassword.clear();
        this.signInPassword.sendKeys(password);
    }

    public void clickSignInButton() {
        this.signInBtn.click();
    }

    public String getInvalidSignInAlert() {
        WebElement invSignIn = new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-danger")));
        return invSignIn.getText();
    }

    public void clickOnForgottenPasswordBtn() {
        this.forgottenPasswordBtn.click();
    }
}

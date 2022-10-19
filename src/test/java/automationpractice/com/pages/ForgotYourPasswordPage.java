package automationpractice.com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class ForgotYourPasswordPage {

    public WebDriver webDriver;

    public ForgotYourPasswordPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
    }

    @FindBy(id = "email")
    private WebElement inputEmailField;

    @FindBys ({@FindBy(className = "submit"), @FindBy(css = ".btn.btn-default.button.button-medium")})
    private WebElement retrievePasswordBtn;

    @FindBy(css = ".alert.alert-success")
    private WebElement confirmationMailAlert;

    public void inputEmailToRetrievePassword(String email) {
        this.inputEmailField.sendKeys(email);
    }

    public void clickRetrievePasswordBtn() {
        this.retrievePasswordBtn.click();
    }

    public String getConfirmationMailSentAlert() {
        return confirmationMailAlert.getText();
    }

}

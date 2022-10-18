package automationpractice.com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotYourPasswordPage {

    public WebDriver webDriver;

    public ForgotYourPasswordPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
    }

    @FindBy(id = "email")
    private WebElement inputEmailField;

    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/form[1]/fieldset[1]/p[1]/button[1]/span[1]")
    private WebElement retrievePasswordBtn;

    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/p[1]")
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

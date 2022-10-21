package automationpractice.com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ContactUsPage {

    private WebDriver webDriver;

    public ContactUsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
    }

    @FindBy(id = "id_contact")
    private WebElement subjectHeadingDropdown;

    @FindBy (id = "email")
    private WebElement emailAddress;

    @FindBy (id = "id_order")
    private WebElement orderId;

    @FindBy (id = "message")
    private WebElement message;

    @FindBy (id = "submitMessage")
    private WebElement sendBtn;

    @FindBy(css = ".alert.alert-success")
    private WebElement messageSentSuccessfully;

    public void setSubjectHeadingDropdown(String heading) {
        Select dropdown = new Select(this.subjectHeadingDropdown);
        if (heading.equalsIgnoreCase("Customer service")) {dropdown.selectByValue(String.valueOf(2));}
        else if (heading.equalsIgnoreCase("Webmaster")) {dropdown.selectByValue(String.valueOf(1));}
    }

    public void setEmailAddress(String email) {
        this.emailAddress.sendKeys(email);
    }

    public void setOrderReference(String order) {
        this.orderId.sendKeys(order);
    }

    public void setMessageField(String message) {
        this.message.sendKeys(message);
    }

    public void clickSendButton() {
        this.sendBtn.click();
    }

    public String getMessageSentSuccessfullyAlert() {
        return messageSentSuccessfully.getText();
    }
}

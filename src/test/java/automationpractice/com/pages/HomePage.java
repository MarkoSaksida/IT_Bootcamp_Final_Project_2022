package automationpractice.com.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

public class HomePage {

    private WebDriver webDriver;

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
    }

    @FindBy(xpath = "//a[contains(text(), \"Sign in\")]")
    private WebElement signInBtn;

    @FindBy(id = "header_logo")
    private WebElement yourLogoHomeButton;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/header[1]/div[3]/div[1]/div[1]/div[6]/ul[1]/li[3]/a[1]")
    private WebElement tShirtsBtn;

    @FindBy (id = "search_query_top")
    private WebElement searchQueryField;

    @FindBy (xpath = "/html[1]/body[1]/div[1]/div[1]/header[1]/div[3]/div[1]/div[1]/div[2]/form[1]/button[1]")
    private WebElement searchQueryBtn;

    @FindBy (xpath = "//div[@id='contact-link']//a[contains(text(),'Contact us')]")
    private WebElement contactUsField;

    @FindBy (xpath = "//li[@class='facebook']")
    private WebElement facebookBtn;

    @FindBy (xpath = "//li[@class='twitter']")
    private WebElement twitterBtn;

    @FindBy (xpath = "//li[@class='youtube']")
    private WebElement youtubeBtn;

    @FindBy (xpath = "//li[@class='google-plus']")
    private WebElement googlePlusBtn;

    @FindBy (id = "newsletter-input")
    private WebElement newsletterInputField;

    @FindBy (xpath = "//p[@class='alert alert-success']")
    private WebElement newsletterSubscriptionSuccessAlert;

    @FindBy(id = "contact-link")
    private WebElement contactUsButton;

    public void clickSignInBtn() {
        this.signInBtn.click();
    }

    public void clickYourLogoHomeButton() {
        this.yourLogoHomeButton.click();
    }

    public void clickTShirtsBtn() {
        this.tShirtsBtn.click();
    }

    public void setSearchQueryField(String item) {
        this.searchQueryField.sendKeys(item);
    }

    public void clickSearchQueryBtn() {
        this.searchQueryBtn.click();
    }

    public void clickSocialMediaButton(String socialMedia) {
        if(socialMedia.equalsIgnoreCase("facebook")) {
            this.facebookBtn.click();
        }
        if(socialMedia.equalsIgnoreCase("twitter")) {
            this.twitterBtn.click();
        }
        if(socialMedia.equalsIgnoreCase("youtube")) {
            this.youtubeBtn.click();
        }
        String oldTab = webDriver.getWindowHandle();
        ArrayList<String> newTab = new ArrayList<String>(webDriver.getWindowHandles());
        newTab.remove(oldTab);
        webDriver.switchTo().window(newTab.get(0));
    }

    public void setNewsletterField(String email) {
        this.newsletterInputField.sendKeys(email);
        this.newsletterInputField.sendKeys(Keys.ENTER);
    }

    public String getNewsletterSubscriptionSuccessField() {
        return newsletterSubscriptionSuccessAlert.getText();
    }

    public void clickContactUsButton() {
        this.contactUsButton.click();
    }

}

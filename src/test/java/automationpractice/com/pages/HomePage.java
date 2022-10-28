package automationpractice.com.pages;

import automationpractice.com.base.BaseTest;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BaseTest {

    public HomePage() {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(className = "login")
    private WebElement signInBtn;

    @FindBy(id = "header_logo")
    private WebElement yourLogoHomeButton;

    @FindBy(tagName = "li")
    private List<WebElement> navigationButtons;

    @FindBy (id = "search_query_top")
    private WebElement searchQueryField;

    @FindBy (name = "submit_search")
    private WebElement searchQueryButton;

    @FindBy (className = "facebook")
    private WebElement facebookBtn;

    @FindBy (className = "twitter")
    private WebElement twitterBtn;

    @FindBy (className = "youtube")
    private WebElement youtubeBtn;

    @FindBy (id = "newsletter-input")
    private WebElement newsletterInputField;

    @FindBy (css = ".alert.alert-success")
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
        for (int i = 0; i < navigationButtons.size(); i++) {
            if (navigationButtons.get(i).getText().equalsIgnoreCase("T-shirts")) {
                navigationButtons.get(i).click();
            }
        }
    }

    public void setSearchQueryField(String item) {
        this.searchQueryField.sendKeys(item);
    }

    public void clickSearchQueryBtn() {
        this.searchQueryButton.click();
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
        this.newsletterInputField.clear();
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

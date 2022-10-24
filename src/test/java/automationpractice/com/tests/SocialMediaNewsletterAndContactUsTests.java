package automationpractice.com.tests;

import automationpractice.com.helpers.DataProviders;
import automationpractice.com.pages.ContactUsPage;
import automationpractice.com.pages.HomePage;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SocialMediaNewsletterAndContactUsTests {

    private WebDriver webDriver;
    private HomePage homePage;
    private ContactUsPage contactUsPage;


    @BeforeMethod
    public void configure() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        homePage = new HomePage(webDriver);
        contactUsPage = new ContactUsPage(webDriver);
        webDriver.get("http://automationpractice.com/index.php");
        webDriver.manage().window().maximize();
    }

    @AfterMethod
    public void quitWebDriver() {
        webDriver.quit();
    }

    @Test(dataProvider = "socialMediaLinks", dataProviderClass = DataProviders.class)
    public void socialMediaLinks_expectedAllLinksLeadToTheirSocialMediaPages(String socialMedia, String expectedUrl) {
        homePage.clickSocialMediaButton(socialMedia);
        Assert.assertTrue(webDriver.getCurrentUrl().contains(expectedUrl));
    }

    @Test
    public void subscribingToNewsLetter_expectedSuccessfulSubscriptionToNewsletter() {
        homePage.setNewsletterField(Faker.instance().bothify("?????###@mail.de"));
        Assert.assertEquals(homePage.getNewsletterSubscriptionSuccessField(),
                "Newsletter : You have successfully subscribed to this newsletter.");
    }

    @Test (dataProvider = "dataForContactUsForm", dataProviderClass = DataProviders.class)
    public void fillingOutContactUsForm_expectedMessageSuccessfullySent(String heading, String message) {
        homePage.clickContactUsButton();
        contactUsPage.setEmailAddress(Faker.instance().bothify("?????###@mail.de"));
        contactUsPage.setSubjectHeadingDropdown(heading);
        contactUsPage.setOrderReference(Faker.instance().numerify("??######"));
        contactUsPage.setMessageField(message);
        contactUsPage.clickSendButton();
        Assert.assertEquals(contactUsPage.getMessageSentSuccessfullyAlert(),
                "Your message has been successfully sent to our team.");
    }


}

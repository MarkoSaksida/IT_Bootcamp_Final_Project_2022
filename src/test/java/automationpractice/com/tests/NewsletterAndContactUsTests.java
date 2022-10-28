package automationpractice.com.tests;

import automationpractice.com.base.BaseTest;
import automationpractice.com.helpers.DataProviders;
import automationpractice.com.pages.ContactUsPage;
import automationpractice.com.pages.HomePage;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.*;


public class NewsletterAndContactUsTests extends BaseTest {
    private HomePage homePage;
    private ContactUsPage contactUsPage;

    @BeforeMethod
    public void configure(){
        homePage = new HomePage();
        contactUsPage = new ContactUsPage();
    }

    @AfterMethod
    public void pageCleanup() {
        webDriver.manage().deleteAllCookies();
    }

    @Test (priority = 10)
    public void subscribingToNewsLetter_expectedSuccessfulSubscriptionToNewsletter() {
        homePage.setNewsletterField(Faker.instance().bothify("?????###@mail.de"));
        Assert.assertEquals(homePage.getNewsletterSubscriptionSuccessField(),
                "Newsletter : You have successfully subscribed to this newsletter.");
    }


    @Test (priority = 20, dataProvider = "dataForContactUsForm", dataProviderClass = DataProviders.class)
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
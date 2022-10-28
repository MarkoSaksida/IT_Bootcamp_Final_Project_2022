package automationpractice.com.tests;

import automationpractice.com.base.BaseTest;
import automationpractice.com.helpers.DataProviders;
import automationpractice.com.pages.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SocialMediaLinksTests extends BaseTest {
    private HomePage homePage;

    @BeforeMethod
    public void setUp()
    {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        webDriver.manage().window().maximize();
        webDriver.get("http://automationpractice.com/index.php");
        homePage = new HomePage();
    }


    @AfterMethod
    public void tearDown() {
        webDriver.manage().deleteAllCookies();
        webDriver.close();
        webDriver.quit();
    }

    @Test(priority = 10, dataProvider = "socialMediaLinks", dataProviderClass = DataProviders.class)
    public void socialMediaLinks_expectedAllLinksLeadToTheirSocialMediaPages(String socialMedia, String expectedUrl) throws InterruptedException {
        homePage.clickSocialMediaButton(socialMedia);
        Assert.assertTrue(webDriver.getCurrentUrl().contains(expectedUrl));
    }
}
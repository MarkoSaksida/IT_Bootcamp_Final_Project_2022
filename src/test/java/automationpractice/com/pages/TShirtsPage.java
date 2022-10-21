package automationpractice.com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TShirtsPage {

    private WebDriver webDriver;

    public TShirtsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
    }

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[3]/div[2]/ul[1]/li[1]/div[1]/div[2]/h5[1]/a[1]")
    private WebElement fadedShortSleeveTShirt;

    public void clickOnFadedShortSleeveTShirt() {
        this.fadedShortSleeveTShirt.click();
    }
}

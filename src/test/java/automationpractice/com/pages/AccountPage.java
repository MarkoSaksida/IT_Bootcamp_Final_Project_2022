package automationpractice.com.pages;

import automationpractice.com.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage extends BaseTest {

    public AccountPage() {
        PageFactory.initElements(webDriver, this);
    }

    public String getSuccessfulSignupAlert() {
        WebElement successfulSignupAlert = new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='info-account']")));
        return successfulSignupAlert.getText();
    }
    @FindBy(className = "logout")
    private WebElement signOutBtn;

    public void clickSignOutButton() {
        this.signOutBtn.click();
    }
}

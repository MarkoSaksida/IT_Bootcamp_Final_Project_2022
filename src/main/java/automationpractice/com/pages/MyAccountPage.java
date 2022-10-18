package automationpractice.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyAccountPage {

    private WebDriver webDriver;

    public MyAccountPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
    }

    public String getSuccessfulSignupAlert() {
        WebElement successfulSignupAlert = new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='info-account']")));
        return successfulSignupAlert.getText();
    }
    @FindBy(xpath = "//a[@title=\"Log me out\"]")
    private WebElement signOutBtn;

    public void clickSignOutButton() {
        this.signOutBtn.click();
    }
}

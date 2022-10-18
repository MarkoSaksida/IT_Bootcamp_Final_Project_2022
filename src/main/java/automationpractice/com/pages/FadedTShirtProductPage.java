package automationpractice.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FadedTShirtProductPage {

    public FadedTShirtProductPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
    }

    private WebDriver webDriver;

    @FindBy(id = "quantity_wanted")
    private WebElement selectQuantityField;

    @FindBy(id = "group_1")
    private WebElement selectSizeDropdown;

    @FindBy(id = "color_13")
    private WebElement orangeFadedTShirtBtn;

    @FindBy(id = "color_14")
    private WebElement blueFadedTShirtBtn;

    @FindBy(xpath = "//span[contains(text(),'Add to cart')]")
    private WebElement addFadedTShirtToCart;

    @FindBy(xpath = "//*[@id=\"center_column\"]/p[2]/a[1]/span")
    private WebElement proceedToCheckoutBtn;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/header[1]/div[3]/div[1]/div[1]/div[4]/div[1]/div[2]/div[4]/span[1]/span[1]")
    private WebElement continueShopping;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/header[1]/div[3]/div[1]/div[1]/div[3]/div[1]/a[1]")
    private WebElement shopingCartBtn;

    public void setQuantityField(String quantity) {
        this.selectQuantityField.clear();
        this.selectQuantityField.sendKeys(quantity);
    }

    public void setSizeDropdown(int size) {
        Select dropdown = new Select(this.selectSizeDropdown);
        dropdown.selectByValue(String.valueOf(size));
    }

    public void clickOrangeOrBlueColorButton(String color) {
        if (color.equalsIgnoreCase("orange")) {
            this.orangeFadedTShirtBtn.click();
        } else if (color.equalsIgnoreCase("blue")) {
            this.blueFadedTShirtBtn.click();
        }
    }

    public void clickAddFadedTShirtToCart() {
        this.addFadedTShirtToCart.click();
    }

    public void clickContinueShopping() {
        WebElement continueShopping = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/header[1]/div[3]/div[1]/div[1]/div[4]/div[1]/div[2]/div[4]/span[1]/span[1]")));
        continueShopping.click();
    }

    public void clickProceedToCheckoutBtn() {
        WebElement proceedToCheckout = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[1]/header[1]/div[3]/div[1]/div[1]/div[4]/div[1]/div[2]/div[4]/a[1]/span[1]")));
        proceedToCheckout.click();
    }

    public void clickOnCartButton() {
        this.shopingCartBtn.click();
    }
}

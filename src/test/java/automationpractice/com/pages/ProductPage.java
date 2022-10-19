package automationpractice.com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {

    public ProductPage(WebDriver webDriver) {
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

    @FindBys({@FindBy(id = "add_to_cart"), @FindBy(className = "exclusive")})
    private WebElement addFadedTShirtToCart;

    @FindBy(css = ".btn.btn-default.button.button-medium")
    private WebElement proceedToCheckoutButton;

    @FindBy(css = ".continue.btn.btn-default.button.exclusive-medium")
    private WebElement continueShoppingButton;

    @FindBys({@FindBy(className = "shopping_cart"), @FindBy(tagName = "a")})
    private WebElement shoppingCartBtn;

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
        WebElement continueShopping = new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(this.continueShoppingButton));
        continueShoppingButton.click();
    }

    public void clickProceedToCheckoutBtn() {
        WebElement proceedToCheckout = new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(this.proceedToCheckoutButton));
        proceedToCheckoutButton.click();
    }

    public void clickOnCartButton() {
        this.shoppingCartBtn.click();
    }
}

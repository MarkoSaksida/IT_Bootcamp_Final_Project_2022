package automationpractice.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Cart {

    private WebDriver webDriver;

    public Cart(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);

    }

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[1]/a[1]/img[1]")
    private WebElement pictureBlueTShirt;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[2]/table[1]/tbody[1]/tr[2]/td[1]/a[1]/img[1]")
    private WebElement pictureOrangeTShirt;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[5]/input[2]")
    private WebElement quantityField;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[7]/div[1]/a[1]/i[1]")
    private WebElement emptyCartBtn;

    @FindBy(xpath = "//*[@id=\"center_column\"]/p[2]/a[1]/span")
    private WebElement proceedToCheckoutBtn;

    @FindBy(xpath = "//button[@type=\"submit\"]/span[contains(text(), \"Proceed to checkout\")]")
    private WebElement proceedToCheckoutBtn2;

    @FindBy(id = "cgv")
    private WebElement agreeToTermsCheckbox;

    @FindBy(xpath = "//a[@title=\"Pay by bank wire\"]")
    private WebElement payByBankWire;

    @FindBy(xpath = "//a[@title=\"Pay by check.\"]")
    private WebElement payByCheck;

    @FindBy(xpath = "//button[@type=\"submit\"]/span[contains(text(), \"I confirm my order\")]")
    private WebElement iConfirmMyOrder;

    public String getSrcAttributeOrangeTShirt() {
        return pictureBlueTShirt.getAttribute("src");
    }

    public String getSrcAttributeBlueTShirt() {
        return pictureOrangeTShirt.getAttribute("src");
    }

    public void changeQuantityOfItem(String quantity) {
        this.quantityField.clear();
        this.quantityField.sendKeys(quantity);
    }

    public void clickProceedToCheckout() {
        this.proceedToCheckoutBtn.click();
    }

    public void clickProceedToCheckoutBtn2() {
        this.proceedToCheckoutBtn2.click();
    }

    public void checkAgreeToTermsCheckbox() {
        this.agreeToTermsCheckbox.click();
    }

    public void clickPayBy(String paymentMethod) {
        if (paymentMethod.equalsIgnoreCase("bank wire")) {
            this.payByBankWire.click();
        }
        else if (paymentMethod.equalsIgnoreCase("check")) {
            this.payByCheck.click();
        }
    }

    public void clickIConfirmMyOrder() {
        this.iConfirmMyOrder.click();
    }

    public void clickRemoveAllFromCart() {
        this.emptyCartBtn.click();
    }

    public String getShoppingCartIsEmptyAlert() {
        WebElement shoppingCartEmptyAlert = new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[3]/div[1]/p[1]")));
        return shoppingCartEmptyAlert.getText();
    }

}

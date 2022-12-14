package automationpractice.com.pages;

import automationpractice.com.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Cart extends BaseTest {



    public Cart() {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[1]/a[1]/img[1]")
    private WebElement pictureBlueTShirt;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[2]/table[1]/tbody[1]/tr[2]/td[1]/a[1]/img[1]")
    private WebElement pictureOrangeTShirt;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[5]/input[2]")
    private WebElement quantityField;

    @FindBy(className = "icon-trash")
    private WebElement emptyCartBtn;


    @FindBy(css = ".button.btn.btn-default.standard-checkout.button-medium")
    private WebElement proceedToCheckoutBtn;

    @FindBy(name = "processAddress")
    private WebElement proceedToCheckoutBtn2;

    @FindBy(name="processCarrier")
    private WebElement proceedToCheckoutBtn3;

    @FindBy(id = "cgv")
    private WebElement agreeToTermsCheckbox;

    @FindBy(className = "bankwire")
    private WebElement payByBankWire;

    @FindBy(className = "cheque")
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

    public void clickProceedToCheckoutBtn3() {
        this.proceedToCheckoutBtn3.click();
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
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-warning")));
        return shoppingCartEmptyAlert.getText();
    }

}

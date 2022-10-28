package automationpractice.com.pages;

import automationpractice.com.base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage extends BaseTest {

    public SearchResultsPage() {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[3]/div[2]/ul[1]/li[1]/div[1]/div[2]/h5[1]/a[1]")
    private WebElement foundItem;


    public String getSearchReturnedItem() {
        return foundItem.getText();
    }
}

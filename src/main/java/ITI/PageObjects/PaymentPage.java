package ITI.PageObjects;

import ITI.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class PaymentPage extends AbstractComponent
{
    public WebDriver driver;

    public PaymentPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver ;
    }

    @FindBy(css = "button[title='Place Order']")
    WebElement placeOrderButton ;

    public void goToConfirmationPage()
    {
        //removePopupAds();
        //waitUntilElementIsVisiable(placeOrderButton);
        //waitUntilElementIsClickable(placeOrderButton);
        waitForMethodLoadToFinish(By.cssSelector("checkout-index-index page-layout-checkout ajax-loading"));
        placeOrderButton.click();
        waitForMethodLoadToFinish(By.cssSelector("div.loading-mask[data-role='loader']") );
    }

}

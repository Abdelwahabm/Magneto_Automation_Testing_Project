package ITI.PageObjects;

import ITI.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ShippingPage extends AbstractComponent
{
    WebDriver driver ;
    public ShippingPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver ;
    }

    @FindBy(css = "button[class='button action continue primary']")
    WebElement nextButton ;

    @FindBy(id = "B4AL846")
    WebElement streetAddressField ;

    @FindBy(id = "AJ7EX31")
    WebElement cityField ;

    @FindBy(id = "MU7XURC")
    WebElement countrySelection ;

    @FindBy(id = "BFGHJ4A")
    WebElement postalCode ;

    @FindBy(id = "PKIMYOJ")
    WebElement mobile ;

    public void fillShippingAddress()
    {
        streetAddressField.sendKeys("Cairo-Madinat Nasr");
        cityField.sendKeys("Cairo");
        postalCode.sendKeys("1234");
        mobile.sendKeys("1234567891");
        Select selection = new Select(countrySelection);
        selection.selectByVisibleText("Egypt");
    }



    public void goToPaymentPage()
    {
        waitForMethodLoadToFinish(By.cssSelector("#checkout-shipping-method-load .loading-mask"));
        scrollToElement(nextButton);
        //waitUntilElementIsVisiable(nextButton);
        waitUntilElementIsClickable(nextButton);
        nextButton.click();
       // removePopupAds();
    }
}

package ITI.PageObjects;

import ITI.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfirmationPage extends AbstractComponent
{
    WebDriver driver ;
    public ConfirmationPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver ;
    }

    @FindBy(css = "div span[class='base']")
    WebElement confirmationMsg ;


    public boolean verifyConfirmationMsg()
    {

        boolean confirmation ;
        waitUntilElementIsVisiable(confirmationMsg);
        confirmation = confirmationMsg.getText().equalsIgnoreCase("Thank you for your purchase!") ;
        return  confirmation ;
    }


}

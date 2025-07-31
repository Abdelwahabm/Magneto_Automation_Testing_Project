package ITI.PageObjects;

import ITI.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends AbstractComponent
{
    WebDriver driver ;
    public HomePage (WebDriver driver)
    {
        super(driver);
        this.driver = driver ;

    }


    public void goToHomePage()
    {
        driver.get("https://magento.softwaretestingboard.com/");
    }





}

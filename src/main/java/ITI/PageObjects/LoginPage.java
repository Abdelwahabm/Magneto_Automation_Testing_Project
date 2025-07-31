package ITI.PageObjects;

import ITI.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractComponent
{
    WebDriver driver ;
    public  LoginPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver ;

    }

    @FindBy(id = "email")
    WebElement email ;

    @FindBy(id = "pass")
    WebElement pass ;

    @FindBy(id = "send2")
    WebElement signInButton ;

    @FindBy(id = "email-error")
    WebElement emailErrorMsg ;

    @FindBy(css = "div[data-ui-id='message-error']")
    WebElement inCorrectSignInMsg ;

    public void doLoginIn (String username , String password)
    {
        goToSignInPage();
        email.sendKeys(username) ;
        pass.sendKeys(password);
        signInButton.click();

    }

    public String getActualEmailErrorMsg ()
    {
        return "Please enter a valid email address (Ex: johndoe@domain.com)." ;
    }


    public String getEmailErrorMsg ()
    {
        waitUntilElementIsVisiable(emailErrorMsg);
        return emailErrorMsg.getText();
    }

    public String getActualIncorrectSignInMsg ()
    {
        return "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later." ;
    }

    public String getInCorrectSignInMsg()
    {
        waitUntilElementIsVisiable(inCorrectSignInMsg);
        return inCorrectSignInMsg.getText() ;
    }



}

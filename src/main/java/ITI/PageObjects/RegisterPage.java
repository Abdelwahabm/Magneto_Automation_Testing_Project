package ITI.PageObjects;

import ITI.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends AbstractComponent
{
    WebDriver driver ;
    public RegisterPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver ;
    }

    @FindBy(id = "page-title-wrapper")
    WebElement createAccountPageTitle ;

    @FindBy(id = "firstname")
    WebElement firstNameField ;

    @FindBy(id = "lastname")
    WebElement lastNameField ;

    @FindBy(id = "email_address")
    WebElement emailField ;

    @FindBy(id = "password")
    WebElement passField ;

    @FindBy(id = "password-confirmation")
    WebElement passConfirmationField ;

    @FindBy(css = "button[title='Create an Account']")
    WebElement createAccountButton ;

    @FindBy(xpath = "(//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)'])[1]")
    WebElement createAccountSuccessMsg ;

    @FindBy(id = "firstname-error")
    WebElement firstNameErrorMsg ;

    @FindBy(id = "lastname-error")
    WebElement lastNameErrorMsg ;

    @FindBy (id = "email_address-error")
    WebElement emailAddErrorMsg ;

    @FindBy (id = "password-error")
    WebElement passErrorMsg ;

    @FindBy (id = "password-confirmation-error")
    WebElement passConfirmErrorMsg ;

    public void createAccount(String firstName , String lastName , String email , String password , String confirmPassword)
    {

        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        emailField.sendKeys(email);
        passField.sendKeys(password);
        passConfirmationField.sendKeys(confirmPassword);
        createAccountButton.click();
    }

    public String getCreateAccountSuccessfulMsg()
    {
        waitUntilElementIsVisiable(createAccountSuccessMsg);
        return createAccountSuccessMsg.getText() ;
    }

    public String getFirstNameErrorMsg()
    {
        return firstNameErrorMsg.getText() ;
    }

    public String getLastNameErrorMsg()
    {
        return lastNameErrorMsg.getText() ;
    }

    public String getEmailErrorMsg()
    {
        return emailAddErrorMsg.getText() ;
    }

    public String getPasswordErrorMsg()
    {
        return passErrorMsg.getText() ;
    }

    public String getConfirmPasswordErrorMsg()
    {
        return passConfirmErrorMsg.getText() ;
    }

    public String expectedCreateAccountSuccessfulMsg()
    {
        return "Thank you for registering with Main Website Store." ;
    }

    public String expectedBlankErrorMsg()
    {
        return "This is a required field." ;
    }

    public String expectedInvalidEmailFormatErrorMsg()
    {
        return "Please enter a valid email address (Ex: johndoe@domain.com)." ;
    }

    public String expectedPasswordLengthErrorMsg()
    {
        return "Minimum length of this field must be equal or greater than 8 symbols. Leading and trailing spaces will be ignored.";
    }

    public String expectedPasswordRulesErrorMsg()
    {
        return "Minimum of different classes of characters in password is 3. Classes of characters: Lower Case, Upper Case, Digits, Special Characters." ;
    }

    public String expectedConfirmPasswordErrorMsg()
    {
        return "Please enter the same value again." ;
    }



}

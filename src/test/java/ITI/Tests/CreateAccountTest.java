package ITI.Tests;

import ITI.PageObjects.RegisterPage;
import ITI.TestComponents.BaseTest;
import net.bytebuddy.build.Plugin;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateAccountTest extends BaseTest
{
    String blankErrorMsg = "This is a required field." ;

    @Test
    public void createAccountSuccessfulTest() throws InterruptedException {
        RegisterPage registerPage = new RegisterPage(driver) ;
        registerPage.goToCreateAccount();
        registerPage.createAccount("Hossam","Amr","hossam6amr@gmail.com","Hosssam@123","Hosssam@123");
        Assert.assertEquals( registerPage.getCreateAccountSuccessfulMsg() , registerPage.expectedCreateAccountSuccessfulMsg() );
    }


    @Test
    public void firstNameBlankTest() throws InterruptedException {
        RegisterPage registerPage = new RegisterPage(driver) ;
        registerPage.goToCreateAccount();
        registerPage.createAccount("","hamo","dwad@gmail.com","Moha@123","Moha@123");
        Assert.assertEquals(registerPage.getFirstNameErrorMsg(),registerPage.expectedBlankErrorMsg());

    }

    @Test
    public void lastNameBlankTest() throws InterruptedException {
        RegisterPage registerPage = new RegisterPage(driver) ;
        registerPage.goToCreateAccount();
        registerPage.createAccount("mohamed","","moha@gma.com","Mohame@123","Mohame@123");
        Assert.assertEquals(registerPage.getLastNameErrorMsg(),registerPage.expectedBlankErrorMsg());
    }

    @Test
    public void emailBlankTest() throws InterruptedException {
        RegisterPage registerPage = new RegisterPage(driver) ;
        registerPage.goToCreateAccount();
        registerPage.createAccount("mohamed","ahmed","","Mohamasd@123","Mohamed@123");
        Assert.assertEquals(registerPage.getEmailErrorMsg(),registerPage.expectedBlankErrorMsg());
    }

    @Test
    public void emailInvalidFormatTest() throws InterruptedException {
        RegisterPage registerPage = new RegisterPage(driver) ;
        registerPage.goToCreateAccount();
        registerPage.createAccount("mohamed","ahmed","dwadw","Mohamasd@123","Mohamed@123");
        Assert.assertEquals(registerPage.getEmailErrorMsg() , registerPage.expectedInvalidEmailFormatErrorMsg());
    }

    @Test
    public void passwordBlankTest() throws InterruptedException {
        RegisterPage registerPage = new RegisterPage(driver) ;
        registerPage.goToCreateAccount();
        registerPage.createAccount("mohamed","ahmed","dwwad@gmail.com","","Mohamed@123");
        Assert.assertEquals(registerPage.getPasswordErrorMsg() , registerPage.expectedBlankErrorMsg());
    }

    @Test
    public void passwordLengthTest() throws InterruptedException {
        // password must contain at least 8 characters
        RegisterPage registerPage = new RegisterPage(driver) ;
        registerPage.goToCreateAccount();
        registerPage.createAccount("mohamed","ahmed","dwwad@gmail.com","Mohamed","Mohamed");
        Assert.assertEquals(registerPage.getPasswordErrorMsg() , registerPage.expectedPasswordLengthErrorMsg());
    }

    @Test
    public void passwordConditionsTest() throws InterruptedException {
        RegisterPage registerPage = new RegisterPage(driver) ;
        registerPage.goToCreateAccount();
        registerPage.createAccount("mohamed","ahmed","dwwad@gmail.com","mohamedahmed","mohamedahmed");
        Assert.assertEquals(registerPage.getPasswordErrorMsg() , registerPage.expectedPasswordRulesErrorMsg());
    }

    @Test
    public void confirmPasswordBlankTest() throws InterruptedException {
        RegisterPage registerPage = new RegisterPage(driver) ;
        registerPage.goToCreateAccount();
        registerPage.createAccount("mohamed","ahmed","dwwad@gmail.com","Mohamed@123","");
        Assert.assertEquals(registerPage.getConfirmPasswordErrorMsg() , registerPage.expectedBlankErrorMsg());
    }

    @Test
    public void confirmPasswordMatchesPasswordTest() throws InterruptedException {
        RegisterPage registerPage = new RegisterPage(driver) ;
        registerPage.goToCreateAccount();
        registerPage.createAccount("mohamed","ahmed","dwwad@gmail.com","Mohamed@123","Mohamed@124");
        Assert.assertEquals(registerPage.getConfirmPasswordErrorMsg() , registerPage.expectedConfirmPasswordErrorMsg());
    }

}

package ITI.Tests;

import ITI.PageObjects.LoginPage;
import ITI.TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInErrorTest extends BaseTest
{
    @Test
    public void inValidEmailTest ()
    {
        LoginPage loginPage = new LoginPage(driver) ;
        loginPage.goToSignInPage();
        loginPage.doLoginIn("dwad","1234568");
        Assert.assertEquals(loginPage.getEmailErrorMsg(),loginPage.getActualEmailErrorMsg());
    }

    @Test
    public void inCorrectSignInTest()
    {
        LoginPage loginPage = new LoginPage(driver) ;
        loginPage.goToSignInPage();
        loginPage.doLoginIn("dwad@gmail.com","1234568");
        Assert.assertEquals(loginPage.getInCorrectSignInMsg(),loginPage.getActualIncorrectSignInMsg());
    }

}

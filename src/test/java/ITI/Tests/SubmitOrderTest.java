package ITI.Tests;

import ITI.PageObjects.*;
import ITI.TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest
{
    @Test(dataProvider = "getData")
    public void submitOrder (HashMap<String,String> data) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver) ;
        loginPage.doLoginIn(data.get("username"),data.get("password"));
        loginPage.goToMenProductsHoodies();
        HoodiesPage hoodiesPage = new HoodiesPage(driver) ;
        hoodiesPage.addHoodiesToCart(data.get("hoodie"),data.get("size"),data.get("color"));
        hoodiesPage.goToCheckoutPage();
        ShippingPage shippingPage = new ShippingPage(driver) ;
        shippingPage.goToPaymentPage();
        PaymentPage paymentPage = new PaymentPage(driver) ;
        paymentPage.goToConfirmationPage();
        ConfirmationPage confirmationPage = new ConfirmationPage(driver) ;

        Assert.assertTrue(confirmationPage.verifyConfirmationMsg());

    }


    @DataProvider
    public Object[][] getData () throws IOException {
        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//ITI//data//UserOrder.json") ;
        return  new Object[][] { {data.get(0)} } ;
    }
        /*
    String username = "losapix412@binafex.com" ;
    String password = "Mohamed@123" ;
    String hoodie = "Grayson Crewneck Sweatshirt" ;
    String size = "m" ;
    String color = "red" ;
    String confirmMessage = "Thank you for your purchase!" ;
*/


}

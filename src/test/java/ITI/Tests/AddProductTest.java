package ITI.Tests;

import ITI.PageObjects.ProductInfoPage;
import ITI.TestComponents.BaseTest;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class AddProductTest extends BaseTest
{

    @Test
    public void verifyAddProduct()
    {
        ProductInfoPage productInfoPage = new ProductInfoPage(driver) ;
        productInfoPage.goToProductPage();
        productInfoPage.selectMSize();
        productInfoPage.selectRedColor();
        productInfoPage.setQuantity("2");
        productInfoPage.pressAddToCart();
        Assert.assertEquals(productInfoPage.getAddToCartSuccessMsg() , productInfoPage.expectedAddToCartSuccessMsg());
    }

    @Test
    public void verifyAddToCompare()
    {
        ProductInfoPage productInfoPage = new ProductInfoPage(driver) ;
        productInfoPage.goToProductPage();
        productInfoPage.pressAddToCompare();
        Assert.assertEquals( productInfoPage.getAddToCompareSuccessMsg() , productInfoPage.expectedAddToCompareSuccessMsg() );
    }

    @Test
    public void verifySizeIsRequired()
    {
        ProductInfoPage productInfoPage = new ProductInfoPage(driver) ;
        productInfoPage.goToProductPage();
        productInfoPage.selectRedColor();
        productInfoPage.setQuantity("5");
        productInfoPage.pressAddToCart();
        Assert.assertEquals( productInfoPage.getSizeErrorMsg() , productInfoPage.expectedBlankMsg() );
    }

    @Test
    public void verifyColorIsRequired()
    {
        ProductInfoPage productInfoPage = new ProductInfoPage(driver) ;
        productInfoPage.goToProductPage();
        productInfoPage.selectMSize();
        productInfoPage.setQuantity("5");
        productInfoPage.pressAddToCart();
        Assert.assertEquals( productInfoPage.getColorErrorMsg() , productInfoPage.expectedBlankMsg() );
    }

    @Test
    public void verifyQuantityIsRequired()
    {
        ProductInfoPage productInfoPage = new ProductInfoPage(driver) ;
        productInfoPage.goToProductPage();
        productInfoPage.selectMSize();
        productInfoPage.selectMSize();
        productInfoPage.setQuantity("");
        productInfoPage.pressAddToCart();
        Assert.assertEquals(productInfoPage.getQuantityErrorMsg() , productInfoPage.expectedQuantityBlankErrorMsg() );
    }

    @Test
    public void verifyQuantityIsGreaterThanZero()
    {
        ProductInfoPage productInfoPage = new ProductInfoPage(driver) ;
        productInfoPage.goToProductPage();
        productInfoPage.selectMSize();
        productInfoPage.selectMSize();
        productInfoPage.setQuantity("0");
        productInfoPage.pressAddToCart();
        Assert.assertEquals(productInfoPage.getQuantityErrorMsg() , productInfoPage.expectedQuantityZeroErrorMsg() );
    }

    @Test
    public void verifyAllRequiredFields()
    {
        ProductInfoPage productInfoPage = new ProductInfoPage(driver) ;
        productInfoPage.goToProductPage();
        productInfoPage.pressAddToCart();
        Assert.assertEquals( productInfoPage.getSizeErrorMsg() , productInfoPage.expectedBlankMsg() );
        Assert.assertEquals( productInfoPage.getColorErrorMsg() , productInfoPage.expectedBlankMsg() );

    }

}

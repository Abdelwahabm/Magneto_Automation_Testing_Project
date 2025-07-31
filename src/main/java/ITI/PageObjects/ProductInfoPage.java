package ITI.PageObjects;

import ITI.AbstractComponents.AbstractComponent;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.BeforeGroups;

public class ProductInfoPage extends AbstractComponent
{
    WebDriver driver ;
    public ProductInfoPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver ;

    }

    @FindBy(id = "option-label-size-143-item-166")
    WebElement xsSize ;

    @FindBy(id = "option-label-size-143-item-167")
    WebElement sSize ;

    @FindBy(id = "option-label-size-143-item-168")
    WebElement mSize ;

    @FindBy(id = "option-label-size-143-item-169")
    WebElement lSize ;

    @FindBy(id = "option-label-size-143-item-170")
    WebElement xlSize ;

    @FindBy(id = "option-label-color-93-item-58")
    WebElement redColor ;

    @FindBy(id = "option-label-color-93-item-50")
    WebElement blueColor ;

    @FindBy(id = "option-label-color-93-item-49")
    WebElement blackColor ;

    @FindBy(id = "option-label-color-93-item-53")
    WebElement greenColor ;

    @FindBy(id = "qty")
    WebElement quantityField ;

    @FindBy(id = "product-addtocart-button")
    WebElement addToCartButton ;

    @FindBy(css = "div[class='product-addto-links'] a[class='action tocompare']")
    WebElement addToCompareButton ;

    @FindBy(id = "super_attribute[143]-error")
    WebElement sizeErrorMsg ;

    @FindBy(id = "super_attribute[93]-error")
    WebElement colorErrorMsg ;

    @FindBy(id = "qty-error")
    WebElement quantityErrorMsg ;

    ////div[@data-bind="scope: 'messages'"]//div[1]//div[1]//div[1]
    @FindBy(xpath = "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")
    WebElement addToCompareMsg ;

    @FindBy(xpath = "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")
    WebElement addToCardSuccessfulMsg ;

    public void goToProductPage()
    {
        driver.get("https://magento.softwaretestingboard.com/grayson-crewneck-sweatshirt.html");
    }


    public void selectXSSize()
    {
        scrollToElement(xsSize);
        xsSize.click();
    }

    public void selectSSize()
    {
        scrollToElement(sSize);
        sSize.click();
    }
    public void selectMSize()
    {
        scrollToElement(mSize);
        mSize.click();
    }
    public void selectLSize()
    {
        scrollToElement(lSize);
        lSize.click();
    }
    public void selectXLSize()
    {
        scrollToElement(xlSize);
        xlSize.click();
    }

    public void selectRedColor()
    {
        scrollToElement(redColor);
        redColor.click();
    }
    public void selectBlueColor()
    {

        blueColor.click();
    }
    public void selectBlackColor()
    {
        blackColor.click();
    }
    public void selectGreenColor()
    {
        greenColor.click();
    }

    public void setQuantity(String q)
    {
        scrollToElement(quantityField);
        quantityField.sendKeys(Keys.ARROW_RIGHT);
        quantityField.sendKeys(Keys.BACK_SPACE);
        quantityField.sendKeys(q);
    }

    public void pressAddToCart()
    {
        scrollToElement(addToCartButton);
        addToCartButton.click();
    }

    public void pressAddToCompare()
    {
        scrollToElement(addToCompareButton);
        addToCompareButton.click();
    }

    public String getSizeErrorMsg()
    {
        return sizeErrorMsg.getText() ;
    }

    public String getColorErrorMsg()
    {
        return colorErrorMsg.getText() ;
    }

    public String getQuantityErrorMsg()
    {
        return quantityErrorMsg.getText() ;
    }

    public String getAddToCartSuccessMsg()
    {
        waitUntilElementIsVisiable(addToCardSuccessfulMsg);
        scrollToElement(addToCardSuccessfulMsg);
        return addToCardSuccessfulMsg.getText() ;
    }

    public String getAddToCompareSuccessMsg()
    {
        scrollToElement(addToCompareMsg);
        return addToCompareMsg.getText() ;
    }


    public String expectedBlankMsg()
    {
        return "This is a required field." ;
    }

    public String expectedQuantityBlankErrorMsg()
    {
        return "Please enter a valid number in this field." ;
    }

    public String expectedQuantityZeroErrorMsg()
    {
        return "Please enter a quantity greater than 0." ;
    }

    public String expectedAddToCartSuccessMsg()
    {
        return "You added Grayson Crewneck Sweatshirt to your shopping cart." ;
    }

    public String expectedAddToCompareSuccessMsg()
    {
        return "You added product  Grayson Crewneck Sweatshirt to the comparison list." ;
    }



}

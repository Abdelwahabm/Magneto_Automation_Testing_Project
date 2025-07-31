package ITI.PageObjects;

import ITI.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

public class HoodiesPage extends AbstractComponent
{
    WebDriver driver ;
    public HoodiesPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver ;
    }

    @FindBy(css = "div[class='product details product-item-details']")
    List <WebElement> hoodies ;


    //product Size
    By xsSize = By.cssSelector("div[aria-label='XS']");
    By sSize  = By.cssSelector("div[aria-label='S']");
    By mSize  = By.cssSelector("div[aria-label='M']");
    By lSize  = By.cssSelector("div[aria-label='L']");
    By xlSize = By.cssSelector("div[aria-label='XL']");

    // product color
    By blue   = By.cssSelector("div[aria-label='Blue']");
    By green  = By.cssSelector("div[aria-label='Green']");
    By red    = By.cssSelector("div[aria-label='Red']");
    By black  = By.cssSelector("div[aria-label='Black']");


    public void addHoodiesToCart(String productName , String size , String color) throws InterruptedException {
        waitUntilElementIsVisiable(hoodiePageTitle);
        size = size.toLowerCase();
        color = color.toLowerCase();
        String hoodieName ;
        for (WebElement hoodie : hoodies )
        {
            hoodieName = hoodie.findElement(By.cssSelector("div[class='product details product-item-details'] strong a")).getText() ;
            scrollToElement(hoodie);
            if (hoodieName.equalsIgnoreCase(productName))
             {
                 switch (size) {
                     case "xs": hoodie.findElement(xsSize).click(); break;
                     case "s":  hoodie.findElement(sSize).click(); break;
                     case "m":  hoodie.findElement(mSize).click(); break;
                     case "l":  hoodie.findElement(lSize).click(); break;
                     case "xl": hoodie.findElement(xlSize).click(); break;
                 }

                 switch (color) {
                     case "blue":  hoodie.findElement(blue).click(); break;
                     case "green": hoodie.findElement(green).click(); break;
                     case "red":   hoodie.findElement(red).click(); break;
                     case "black": hoodie.findElement(black).click(); break;
                 }
                 hoodie.findElement(By.cssSelector("button[title='Add to Cart']")).click();//add to cart
                //Thread.sleep(Duration.ofSeconds(4)); //wait until product is added
                 break;
             }
        }
    }


}

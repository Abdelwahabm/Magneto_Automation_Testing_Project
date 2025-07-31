package ITI.AbstractComponents;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class AbstractComponent
{
    public WebDriver driver ;
    public AbstractComponent(WebDriver driver)
    {
        this.driver = driver ;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "div[class='panel header'] li[data-label='or'] a")
    WebElement goToSignIn ;

    @FindBy(xpath ="//a[@class='action showcart']")
    WebElement showBag ;

    @FindBy(css = "a[id='ui-id-5']")
    WebElement menProductsMenu ;

    @FindBy(css = "a[id='ui-id-17'] span:nth-child(2)")
    WebElement menTopsMenu ;

    @FindBy(css = "a[id='ui-id-20'] span")
    WebElement clickHoodiesSweatShirts ;

    @FindBy(id = "top-cart-btn-checkout")
    WebElement proceedToCheckout ;

    @FindBy(xpath = "//div[@class='page-title-wrapper']")
   public WebElement hoodiePageTitle ;

    @FindBy(xpath = "(//a[normalize-space()='Create an Account'])[1]")
    WebElement createAccountButton ;

    public void scrollToElement(WebElement element)
    {
        waitUntilElementIsVisiable(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void goToSignInPage()
    {
        goToSignIn.click();
    }

    public void goToCreateAccount() throws InterruptedException {
        createAccountButton.click();
        Thread.sleep(500);
        //closeAdIfPresent();
        removePopupAds();
        createAccountButton.click();
    }

   /* public void goToMenProductsHoodies () throws InterruptedException {

        Actions actions = new Actions(driver);
        actions.moveToElement(menProductsMenu)
                .pause(Duration.ofMillis(500))
                .moveToElement(menTopsMenu)
                .pause(Duration.ofMillis(500))
                .moveToElement(clickHoodiesSweatShirts)
                .click()
                .build()
                .perform();
        Thread.sleep(200);
        removePopupAds();

    }*/
   public void goToMenProductsHoodies() {
       Actions actions = new Actions(driver);

       // Try initial hover & click
       navigateToHoodies();

       // Remove popups
       boolean popupRemoved = removePopupAds();

       // Wait for the new page to load (adjust selector as needed)
       boolean onCorrectPage = waitForHoodiesPage();

       // Retry only if popup was removed AND page didn't open
       if (popupRemoved && !onCorrectPage) {
           navigateToHoodies();
       }
   }
    private void navigateToHoodies() {
        new Actions(driver)
                .moveToElement(menProductsMenu)
                .pause(Duration.ofMillis(500))
                .moveToElement(menTopsMenu)
                .pause(Duration.ofMillis(500))
                .moveToElement(clickHoodiesSweatShirts)
                .click()
                .build()
                .perform();
    }

    private boolean waitForHoodiesPage() {
        try {
            // Use a unique element that exists only on the hoodies page
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='product details product-item-details']")));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }


    public void goToCheckoutPage() throws InterruptedException {
        scrollToElement(showBag);
        waitForMethodLoadToFinish(By.cssSelector("span[class='counter qty _block-content-loading']"));
        showBag.click();
          // After opening cart
        //Thread.sleep(3000);
        proceedToCheckout.click();
      // After clicking checkout
    }

    public  void waitUntilElementIsClickable(WebElement element)
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(6)) ;
        wait.until(ExpectedConditions.elementToBeClickable(element)) ;
    }

    public  void waitUntilElementIsVisiable(WebElement element)
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3)) ;
        wait.until(ExpectedConditions.visibilityOf(element)) ;
    }

    public void waitUntilElementIsInvisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    public void waitForMethodLoadToFinish(By locator) {
        // Wait for the spinner inside the shipping method block to disappear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator)) ;
    }

    private static boolean adAlreadyHandled = false;

    public void closeAdIfPresent() {
        if (adAlreadyHandled) return;

        try {
            // Quick scan for visible iframes
            List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
            if (!iframes.isEmpty()) {
                for (WebElement iframe : iframes) {
                    if (iframe.isDisplayed()) {
                        try {
                            driver.switchTo().frame(iframe);

                            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
                            WebElement closeBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                                    By.xpath("//*[contains(@id,'dismiss') or contains(@class,'dismiss') or contains(@id,'close') or contains(@class,'close')]")
                            ));

                            if (closeBtn.isDisplayed()) {
                                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeBtn);
                                adAlreadyHandled = true;
                                driver.switchTo().defaultContent();
                                return;
                            }

                            driver.switchTo().defaultContent();
                        } catch (NoSuchElementException | TimeoutException ignored) {
                            driver.switchTo().defaultContent(); // Always switch back
                        }
                    }
                }
            }
        } catch (Exception e) {
            // Optional: log the error or ignore silently
        }

        // Always switch back to default content at the end
        try {
            driver.switchTo().defaultContent();
        } catch (Exception ignored) {}
    }


/*
    public void removePopupAds() {
        try {
            ((JavascriptExecutor) driver).executeScript("""
            document.querySelectorAll(
              '#dismiss-button, .popup-overlay, .adsbygoogle, .modal, .overlay, .interstitial, .vignette'
            ).forEach(e => e.remove());
        """);
        } catch (Exception ignored) {}
    }*/

    public boolean removePopupAds() {
        try {
            Long before = (Long) ((JavascriptExecutor) driver).executeScript("return document.body.innerHTML.length");
            ((JavascriptExecutor) driver).executeScript("""
            document.querySelectorAll(
              '#dismiss-button, .popup-overlay, .adsbygoogle, .modal, .overlay, .interstitial, .vignette'
            ).forEach(e => e.remove());
        """);
            Thread.sleep(300);  // Give time for UI change to reflect
            Long after = (Long) ((JavascriptExecutor) driver).executeScript("return document.body.innerHTML.length");
            return !before.equals(after);  // If DOM changed, ad was removed
        } catch (Exception ignored) {
            return false;
        }
    }








/* second worked
    public void closeAdIfPresent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        boolean adClosed = false;

        try {
            List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
            System.out.println("Found " + iframes.size() + " iframes.");

            for (WebElement iframe : iframes) {
                try {
                    driver.switchTo().frame(iframe);

                    // Try multiple dismiss buttons
                    List<WebElement> closeBtns = driver.findElements(By.xpath(
                            "//*[@id='dismiss-button' or contains(@class,'close') or contains(@id,'close') or contains(@class,'dismiss')]"
                    ));

                    for (WebElement btn : closeBtns) {
                        if (btn.isDisplayed()) {
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
                            System.out.println("Ad closed inside iframe.");
                            adClosed = true;
                            break;
                        }
                    }

                    if (adClosed) break;

                } catch (Exception e) {
                    System.out.println("Error inside iframe: " + e.getMessage());
                } finally {
                    driver.switchTo().defaultContent(); // Always return
                }
            }

            // Check main page fallback
            if (!adClosed) {
                List<WebElement> closeBtns = driver.findElements(By.xpath(
                        "//*[@id='dismiss-button' or contains(@class,'close') or contains(@id,'close') or contains(@class,'dismiss')]"
                ));
                for (WebElement btn : closeBtns) {
                    if (btn.isDisplayed()) {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
                        System.out.println("Ad closed on main page.");
                        adClosed = true;
                        break;
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error closing ad: " + e.getMessage());
        } finally {
            driver.switchTo().defaultContent();
            if (!adClosed) {
                System.out.println("No ad found to close.");
            }
        }
    }

    public void removePopupAds() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Remove known ad classes
            String removeScripts =
                    "document.querySelectorAll(" +
                            "'.popup-overlay, .sticky-ad, .ns-show, .adsbygoogle, .modal-backdrop, " +
                            ".overlay, .lightbox, .tp-modal, .interstitial, .vignette, .cookie-consent, .gdpr-banner'" +
                            ").forEach(el => el.remove());";

            js.executeScript(removeScripts);

            // Try hiding fixed position ads
            js.executeScript(
                    "document.querySelectorAll('*')" +
                            ".forEach(el => { if (getComputedStyle(el).position === 'fixed' && el.offsetHeight > 50) el.remove(); });"
            );

            System.out.println("Overlay/popups removed.");

        } catch (Exception e) {
            System.out.println("No ads to remove or JavaScript error: " + e.getMessage());
        }
    }


second worked                   */

/*
    public void removePopupAds() {
        try {
            // Attempt to click visible popup close buttons
            List<WebElement> closeButtons = driver.findElements(By.cssSelector(
                    ".needsclick .ns-close, .popup-close, .ad-close, #dismiss-button"
            ));

            for (WebElement btn : closeButtons) {
                if (btn.isDisplayed()) {
                    btn.click();
                    System.out.println("Popup closed.");
                    Thread.sleep(500); // Give time for fade-out
                }
            }

            // Remove any leftover overlays via JS (backup)
            ((JavascriptExecutor) driver).executeScript(
                    "document.querySelectorAll('.popup-overlay, .sticky-ad, .ns-show, .adsbygoogle').forEach(el => el.remove());"
            );
        } catch (Exception e) {
            System.out.println("No ads removed or error occurred: " + e.getMessage());
        }
    }
/*
    public void closeAdIfPresent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        boolean adClosed = false;

        try {
            // 1. Check inside all iframes
            List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
            System.out.println("Found " + iframes.size() + " iframes.");

            for (WebElement iframe : iframes) {
                try {
                    driver.switchTo().frame(iframe);
                    List<WebElement> closeBtns = driver.findElements(By.id("dismiss-button"));

                    if (!closeBtns.isEmpty()) {
                        WebElement closeAd = closeBtns.get(0);
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeAd);
                        System.out.println("Ad closed inside iframe.");
                        adClosed = true;
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Error inside iframe: " + e.getMessage());
                } finally {
                    driver.switchTo().defaultContent(); // Always switch back
                }
            }

            // 2. If not found in iframe, check on main page
            if (!adClosed) {
                List<WebElement> mainAd = driver.findElements(By.id("dismiss-button"));
                if (!mainAd.isEmpty()) {
                    WebElement closeAd = mainAd.get(0);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeAd);
                    System.out.println("Ad closed on main page.");
                    adClosed = true;
                }
            }

        } catch (Exception e) {
            System.out.println("Error closing ad: " + e.getMessage());
        } finally {
            driver.switchTo().defaultContent();
            if (!adClosed) {
                System.out.println("No ad found to close.");
            }
        }
    }

*/
    /*
    public void removePopupAds() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(
                    "document.querySelectorAll('.adsbygoogle.adsbygoogle-noablate')" +
                            ".forEach(el => el.remove());"
            );
        } catch (Exception e) {
            System.out.println("No ads to remove or JavaScript error.");
        }
    }

     */



}

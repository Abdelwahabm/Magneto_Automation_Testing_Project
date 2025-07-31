package ITI.TestComponents;

import ITI.PageObjects.HomePage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest
{
    public WebDriver driver ;

    public WebDriver initializeDriver () throws IOException {
        Properties prop = new Properties() ;
        FileInputStream fil = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\ITI\\resources\\GlobalData.properties") ;
        prop.load(fil);

        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser") ;

        if (browserName.contains("chrome"))
        {
            ChromeOptions options = new ChromeOptions();
            //options.addExtensions(new File(System.getProperty("user.dir") + "/browsertools/uBlockOriginLite.crx") );
            //options.addArguments("load-extension=" + System.getProperty("user.dir") + "/browsertools/uBlock0.chromium");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-notifications");
            options.addArguments("–disable-features=SameSiteByDefaultCookies");
            driver = new ChromeDriver(options)  ;

        }
        else if (browserName.contains("firefox"))
        {
            driver = new FirefoxDriver() ;
        } else if (browserName.contains("edge"))
        {
            driver = new EdgeDriver() ;
        }

        driver.manage().window().setSize(new Dimension(1440,900));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)) ;
        return driver ;
    }

    // reading data from json
    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        // read json to string
        String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8) ;

        //jsonString to hashmap jackson databind
        ObjectMapper mapper = new ObjectMapper() ;
        List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        }) ;
        return data ;
    }

    public String getScreenShot (String testCaseName , WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver ;
        File source = ts.getScreenshotAs(OutputType.FILE) ;
        File file = new File(System.getProperty("user.dir")+"//reports//"+ testCaseName + ".png");
        FileUtils.copyFile(source,file);
        return System.getProperty("user+dir")+"//reports//"+ testCaseName + ".png" ;
    }

    @BeforeMethod(alwaysRun = true)
    public void launchApplication() throws IOException {
        driver= initializeDriver();
        HomePage homePage = new HomePage(driver) ;
        homePage.goToHomePage();

    }

    @AfterMethod(alwaysRun = true)
    public void teardown()
    {
        driver.close();
    }



}

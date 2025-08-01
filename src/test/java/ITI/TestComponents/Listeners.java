package ITI.TestComponents;

import ITI.resources.ExtentReportsNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener
{
    ExtentReports extent = ExtentReportsNG.getReportObject();
    ExtentTest test ;
    ThreadLocal <ExtentTest> extentTestThreadLocal = new ThreadLocal<>() ;
    @Override
    public void onTestStart(ITestResult result) {
       test = extent.createTest(result.getMethod().getMethodName()) ;
        extentTestThreadLocal.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTestThreadLocal.get().log(Status.PASS,"Test passed") ;
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTestThreadLocal.get().fail(result.getThrowable());
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        String filePath = null ;
        try {
            filePath = getScreenShot(result.getMethod().getMethodName(),driver) ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        extentTestThreadLocal.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName()) ;
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}

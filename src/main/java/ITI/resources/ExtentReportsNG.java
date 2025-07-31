package ITI.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG
{
    public static ExtentReports getReportObject()
    {
        //ExtentReporter , ExtentSparkReporter -> it is responsible of creating report and put it in the path you want
        String path = System.getProperty("user.dir")+"\\reports\\index.html" ;
        ExtentSparkReporter reporter = new ExtentSparkReporter(path) ;
        reporter.config().setReportName("Web Automation Testing Results for Dema Website");
        reporter.config().setDocumentTitle("Test Results");

        ExtentReports extent = new ExtentReports() ;
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester","Mohamed Abdelwahab");
        return extent ;
    }
}

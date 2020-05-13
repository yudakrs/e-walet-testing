package G2.Project.Listener;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter {
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	public void onStart(ITestContext testContext)
	{
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/"+testContext.getName()+".html");
		
		htmlReporter.config().setDocumentTitle("Automation Report - e-Walet");
		htmlReporter.config().setReportName("e-Walet API Testing Report");
		htmlReporter.config().setTheme(Theme.STANDARD);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User", "Yuda");
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test = extent.createTest(result.getName());
		
		test.log(Status.PASS, "Test Case PASSED is " + result.getTestClass());
		test.log(Status.PASS, "Test Case PASSED is "+ result.getName());
		test.log(Status.PASS, "Test Case PASSED duration " + (result.getEndMillis()-result.getStartMillis()) +" ms");
	}
	
	public void onTestFailure(ITestResult result)
	{
		test = extent.createTest(result.getName());
		
		test.log(Status.FAIL, "Test Case FAILED is "+result.getTestClass());
		test.log(Status.FAIL, "Test Case FAILED is "+result.getName());
		test.log(Status.FAIL, "Test Case ERROR is "+result.getThrowable());	
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test = extent.createTest(result.getName());
		
		test.log(Status.SKIP, "Test Case SKIPPED is "+result.getTestClass());
		test.log(Status.SKIP, "Test Case SKIPPED is "+result.getName());
		test.log(Status.SKIP, "Test Case SKIPPED is "+result.getThrowable());
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
}

package testResultListeners;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;

import Parentutility.Baseclass;
import reportresources.ExtentReporterNG;

public class Listeners extends Baseclass implements ITestListener{
	ExtentTest test;
	ExtentReports extent=ExtentReporterNG.getReportObject();
	
	@Override
	public void onTestStart(ITestResult result)
	{
		test=extent.createTest(result.getMethod().getMethodName());
	}
	
	@Override
	public void onTestSuccess(ITestResult result)
	{
		test.log(Status.PASS, "PASSED");
	}
	@Override
	public void onTestFailure(ITestResult result)
	{
		
		test.fail(result.getThrowable());
		try {
		driver= (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		}catch (Exception e1)
		{
			e1.printStackTrace();
		}
		String filepath =null;
		 try {
			filepath =getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 test.addScreenCaptureFromPath(filepath,result.getMethod().getMethodName());
		
	}
	
}

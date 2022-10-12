package com.rahulshettyacademy.testcomponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.rahulshettyacademy.resources.ExtentReporterNG;

//ITestListener - interface provided by TestNG
//Listeners class will implement the inherited abstract methods of ITestListener interface
public class Listeners extends BaseTest implements ITestListener{
	
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	//Before running any TestNG test, this method will be executed
	//result object holds the information about the test that is going to execute
	@Override
	public void onTestStart(ITestResult result) {
		//before executing the test, create entry for that test in the ExtentReport
		//this is where entry got created in the extent report for the test under execution 
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}
	
	//------------------------------------------------------------------------------------------------------------
	
	
	
	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Woo hoo!! Test Passed");
	}
	
	//-------------------------------------------------------------------------------------------------------------
	
	@Override
	public void onTestFailure(ITestResult result) {
		
		extentTest.get().fail(result.getThrowable());
		
		try {
			  driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			String filepath=null;
			try {
				filepath = getScreenshot(result.getMethod().getMethodName(),driver);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
        
			extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
		
	}
	
	//--------------------------------------------------------------------------------
	
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
		
	}
	

}


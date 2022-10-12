package com.rahulshettyacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReportObject() {

		//Responsible for creating a html file and setting up some basic configurations
		String path = System.getProperty("user.dir")+"/reports/";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
				
		//this is the main class, responsible for reporting all your test execution results   
		ExtentReports extent = new ExtentReports();
		//attach the html file created by reporter object to the ExtentReports class object
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Rahul Shetty");
		return extent;
	}
}



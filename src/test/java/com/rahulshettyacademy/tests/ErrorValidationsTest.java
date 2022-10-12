package com.rahulshettyacademy.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import java.io.IOException;
import com.rahulshettyacademy.testcomponents.BaseTest;
import com.rahulshettyacademy.testcomponents.Retry;


public class ErrorValidationsTest extends BaseTest {

	@Test(retryAnalyzer=Retry.class)
	public void endToEnd() throws InterruptedException, IOException {
		
		
		landingPage.loginToApplication("cjasraj988@gmail.com", "Myacademy@007");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());	
		System.out.println("Test Passed!!!");
		
	}

}



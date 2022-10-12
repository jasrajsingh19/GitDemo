package com.rahulshettyacademy.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import com.rahulshettyacademy.pageobjects.CartPage;
import com.rahulshettyacademy.pageobjects.CheckoutPage;
import com.rahulshettyacademy.pageobjects.ConfirmationPage;
import com.rahulshettyacademy.pageobjects.OrderPage;
import com.rahulshettyacademy.pageobjects.ProductCatalogue;
import com.rahulshettyacademy.testcomponents.BaseTest;

public class StandAloneTest extends BaseTest{
	
	String productName = "ZARA COAT 3";

	@Test(dataProvider="getData")
	public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException {
		
		
		String countryName = "India";
		
		//login to application
		ProductCatalogue productCatalogue = landingPage.loginToApplication(input.get("email"), input.get("password"));
		
		//----------------------------------------------------------------------------
		
		//add product to cart
		productCatalogue.addProductToCart(input.get("productName"));
		
		//-----------------------------------------------------------------------------
		
		
		//Clicking on Cart button
		CartPage cartPage = productCatalogue.goToCartPage();
		
		//----------------------------------------------------------------------------

		//verify item added on My Cart Page and click on checkout botton
        Boolean match = cartPage.isitemAdded(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckOut();
		
		//----------------------------------------------------------------------------
		
		//After landing on checkout page, select country and click on submit order
		checkoutPage.selectCountry(countryName);
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		
		//-------------------------------------------------------------------------------
		
		//On confirmationPage, verify confirmation message displayed on screen
		String confirmMessage = confirmationPage.getConfirmationMessage();
		AssertJUnit.assertEquals(confirmMessage,"THANKYOU FOR THE ORDER.");
			
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest() {
		
		ProductCatalogue productCatalogue = landingPage.loginToApplication("cjasraj98@gmail.com", "Myacademy@007");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.isOrderAdded(productName));	
	}
	
	@DataProvider
	public Object[][] getData() throws IOException{

		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"/src/test/java/com/rahulshettyacademy/data/Purchase.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
}


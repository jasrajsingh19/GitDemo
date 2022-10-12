package com.rahulshettyacademy.stepDefinations;

import java.io.IOException;

import org.testng.Assert;
import org.testng.AssertJUnit;

import com.rahulshettyacademy.pageobjects.CartPage;
import com.rahulshettyacademy.pageobjects.CheckoutPage;
import com.rahulshettyacademy.pageobjects.ConfirmationPage;
import com.rahulshettyacademy.pageobjects.LandingPage;
import com.rahulshettyacademy.pageobjects.ProductCatalogue;
import com.rahulshettyacademy.testcomponents.BaseTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefination extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException {
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) throws IOException {
		productCatalogue = landingPage.loginToApplication(username, password);
	}
	
	
	@When("^I add product (.+) to Cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException {
		productCatalogue.addProductToCart(productName);
	}
	
	@When("^Checkout <productName> and submit the order$")
	public void checkout_submit_order(String productName) {
		
		//Clicking on Cart button
		CartPage cartPage = productCatalogue.goToCartPage();

		//verify item added on My Cart Page and click on checkout botton
		 Boolean match = cartPage.isitemAdded(productName);
		 Assert.assertTrue(match);
		 CheckoutPage checkoutPage = cartPage.goToCheckOut();
		 
		//After landing on checkout page, select country and click on submit order
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
		
	}
	
	@Then("{string} message is displayed on Confirmation page")
	public void message_displayed_confirmationPage(String string) {
		
		//On confirmationPage, verify confirmation message displayed on screen
		String confirmMessage = confirmationPage.getConfirmationMessage();
		AssertJUnit.assertEquals(confirmMessage,string);

	}

}

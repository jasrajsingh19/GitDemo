package com.rahulshettyacademy.pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.rahulshettyacademy.reusablecomponents.ReusableComponents;

public class CheckoutPage extends ReusableComponents{
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement inputCountryField;
	
	@FindBy(css=".ta-item:nth-of-type(2)")
	WebElement selectCountryOption;
	
	@FindBy(css=".action__submit")
	WebElement submitButton;
	
	By results = By.cssSelector(".ta-results");
	
	//Selecting country
	// 1. input country in dropdown
	// 2. wait for results to appear
	// 3. select 2nd option from the results list
	
	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(inputCountryField, countryName).build().perform();
		waitForElementToAppear(results);
		selectCountryOption.click();
	}
	
	public ConfirmationPage submitOrder() {
		submitButton.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
		
	}
	
}



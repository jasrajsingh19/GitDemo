package com.rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage {
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
	
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr.ng-star-inserted td:nth-of-type(2)")
	List<WebElement> orderedProductsTitle;
	
	
	
	public boolean isOrderAdded(String productName) {
		Boolean match = orderedProductsTitle.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
}



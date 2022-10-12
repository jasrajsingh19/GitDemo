package com.rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage{

WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		//initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);	
	}
	
	@FindBy(css=".hero-primary")
	WebElement confirmationMessage;
	
    public String getConfirmationMessage() {
    	return confirmationMessage.getText();
	
    }
}



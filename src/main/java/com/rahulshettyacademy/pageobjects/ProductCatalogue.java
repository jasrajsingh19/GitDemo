package com.rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.rahulshettyacademy.reusablecomponents.ReusableComponents;

public class ProductCatalogue extends ReusableComponents {
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	    @FindBy(css=".mb-3")
	    List<WebElement> products;
	
	    @FindBy(css=".ng-animating")
	    WebElement spinner;
	
		By productsBy = By.cssSelector(".mb-3");
	    By productTitle = By.cssSelector("b");
	    By addToCart = By.cssSelector("button:last-of-type");
	    By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		//getting product with title as ZARA COAT 3 from the list
		WebElement prod = getProductList().stream().filter(product-> product.findElement(productTitle).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
	}
	
	
	
	
}


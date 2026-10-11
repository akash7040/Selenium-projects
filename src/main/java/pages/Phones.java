package pages;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Phones {
	
	public static void phone(WebDriver driver) {
		
		//open Url
		driver.get("https://www.demoblaze.com/index.html#");
		
		//verify url
		System.out.println(driver.getTitle());
		
		//Phones category
	WebElement Phone = 	driver.findElement(By.xpath("//a[contains(normalize-space(.),'Phones')]"));
	Phone.click();
	
	//Find all products in phones category
	
	List<WebElement> phones = driver.findElements(By.xpath("//div[@class=\"card-block\"]"));
	
	boolean foundProduct = false;
	//product for adding to cart
	
	String product = "Nexus 6";
	for(WebElement AllPhones:phones) {
		String ph = AllPhones.getText();
		
		
	
	if (ph.contains(product)) {
		
		System.out.println("Product found "+product);
		
		driver.findElement(By.xpath("//a[contains(normalize-space(.),'"+product+"')]")).click();
		driver.findElement(By.xpath("//a[contains(normalize-space(.),\"Add to cart\")]")).click();
		
		try {
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = 
			driver.switchTo().alert();
			
			String msg = alert.getText();
			
			System.out.println(msg);
			
			alert.accept();
		}
			
		 catch(Exception e) {
			
			System.err.println(e);
		
		
	}
		break;
	}
	
	}
	
	WebElement cart = driver.findElement(By.id("cartur"));
	cart.click();
	
	//check if product is  available in car or not
	try {
	WebElement Cart_product = driver.findElement(By.xpath("//td[contains(normalize-space(.),'"+product+"')]"));
	
	
	if(Cart_product.isDisplayed()) {
		
		System.out.println("Product present in cart is "+ product);
	}
	
	
	//Price of the product
	String priceXpath = "//td[contains(normalize-space(.), '" + product + "')]/following-sibling::td[1]";
    WebElement priceElement = driver.findElement(By.xpath(priceXpath));
    
    
    String productPrice = priceElement.getText();
    
    // show price on console
    System.out.println("The price for " + product + " in the cart is: $" + productPrice);
    
	}catch(NoSuchElementException e) {
		
		System.out.println("no such element exception");
	}
	}
}

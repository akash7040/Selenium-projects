package pages;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PlaceOrder {
	
	public static void order(WebDriver driver) {
		
		try {
			
			List<WebElement> products = driver.findElements(By.xpath("//tr[@class ='success']"));
			
			if(!products.isEmpty()) {
				
				driver.findElement(By.xpath("//button[contains(text() ,'Place Order')]")).click();
				
				//fill detaila to place order
				
				driver.findElement(By.id("name")).sendKeys("Joseph");
				driver.findElement(By.id("country")).sendKeys("India");
				driver.findElement(By.id("city")).sendKeys("Mumbai");
				driver.findElement(By.id("card")).sendKeys("1234567890");
				driver.findElement(By.id("month")).sendKeys("June");
				driver.findElement(By.id("year")).sendKeys("202X");
				//driver.findElement(By.id("country")).sendKeys("India");
				
				//place order
				
				driver.findElement(By.xpath("//button[@onclick='purchaseOrder()']")).click();
				
				
				
				//Verify if order is placed
				
				String orderplaced = "Thank you for your purchase!";
				
				WebElement order = driver.findElement(By.xpath("//h2[contains(normalize-space(.),'Thank you for your purchase!')]"));
			   	
				
				if(order.getText().equals(orderplaced)) {
					
					System.out.println("Order placed successful");
					
					//all order details
					
					 // 1. Get the entire text block from the popup description
				    String rawDetails = driver.findElement(By.xpath("//p[contains(@class, 'lead')]")).getText();
				    
				    // 2. Short Split: Split by "Id:" and take the first word of the remaining text
				    String orderId = rawDetails.split("Id:")[1].trim().split("\n")[0].trim();
				    
				    System.out.println("Order ID: " + orderId);
				    
				    // 3. Click the OK button to close the popup
				    //driver.findElement(By.xpath("//button[text()='OK']")).click();
				}
					
					
				}
			
		}catch(NoSuchElementException e) {
			
			System.out.println("Cart is empty");
			
		}
		
		
		
	}

}

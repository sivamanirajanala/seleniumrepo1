package tests;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.service.DriverCommandExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.*;

public class AlertsAndIframes extends BaseTest{
	
	@Test(priority = 1)
	public void testingAlerst() {
		
		openurl("https://the-internet.herokuapp.com/javascript_alerts");
		
		//handling js alert
		driver.findElement(By.xpath("//li/button[contains(text(),'Click for JS Alert')]")).click();
		
		Alert alert = driver.switchTo().alert();
		
		String alertTextString=alert.getText();
		System.out.println("alert text"+alertTextString);
		
		Assert.assertEquals(alertTextString, "I am a JS Alert","Alert text is incorrect");
		alert.accept();
		
		//js confirms
		driver.findElement(By.xpath("//li/button[contains(text(),'Click for JS Confirm')]")).click();
		alert=driver.switchTo().alert();
		
		String confirmMsg=alert.getText();
		System.out.println("confirm text"+confirmMsg);
		
		alert.dismiss();
		
		System.out.println("confirm dismissed");
		
		WebElement confirmText=driver.findElement(By.id("result"));
		
		Assert.assertEquals(confirmText.getText(),"You clicked: Cancel","Confirm dismiss action failed");
		
		
		//handling promts
		
		driver.findElement(By.xpath("//li/button[text()='Click for JS Prompt']")).click();
		
		alert=driver.switchTo().alert();
		
		alert.sendKeys("yes im prompt");
		alert.accept();
		
		WebElement promtresElement=driver.findElement(By.id("result"));
		
		Assert.assertEquals(promtresElement.getText(), "You entered: yes im prompt");
		
		System.out.println("prompt completed");
		
		
		
	}

	@Test(priority = 0)
	public void testIframes() {
		openurl("https://iframetest.com/?url=https://bing.com");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		WebElement myFramElement=driver.findElement(By.tagName("iframe"));
		
		driver.switchTo().frame(myFramElement);
		
		System.out.println("swited to iframe");
		
		String frameTitle=driver.getTitle();
		
		System.out.println(frameTitle);
		
	
		
	
		driver.switchTo().defaultContent();
		
		 System.out.println("Switched back to main page");
		 
		 String mainMageTitleString=driver.getTitle();
		 
		 System.out.println("main page title"+mainMageTitleString);
		
		
		
		
	}
}

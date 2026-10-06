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
		
		logger.info("opening the url for alerts");
		openurl("https://the-internet.herokuapp.com/javascript_alerts");
		
		
		logger.info("found the alert elemement and clicked on the button");
		//handling js alert
		driver.findElement(By.xpath("//li/button[contains(text(),'Click for JS Alert')]")).click();
		
		
		Alert alert = driver.switchTo().alert();
		
		logger.info("asserted the alert text");
		String alertTextString=alert.getText();
		System.out.println("alert text"+alertTextString);
		
		
		Assert.assertEquals(alertTextString, "I am a JS Alert","Alert text is incorrect");
		
		logger.info("acceped the alert");
		alert.accept();
		
		//js confirms
		logger.info("found the confirm alert elemement and clicked on the button");
		driver.findElement(By.xpath("//li/button[contains(text(),'Click for JS Confirm')]")).click();
		alert=driver.switchTo().alert();
	
		String confirmMsg=alert.getText();
		System.out.println("confirm text"+confirmMsg);
		logger.info("dismissed the confirm alert");
		alert.dismiss();
		
		System.out.println("confirm dismissed");
		
		WebElement confirmText=driver.findElement(By.id("result"));
		logger.info("asserted the alert text");
		Assert.assertEquals(confirmText.getText(),"You clicked: Cancel","Confirm dismiss action failed");
		
		
		//handling promts
		logger.info("found the alert elemement and clicked on the button");
		driver.findElement(By.xpath("//li/button[text()='Click for JS Prompt']")).click();
		
		alert=driver.switchTo().alert();
		
		logger.info("sending the text for promt");
		alert.sendKeys("yes im prompt");
		alert.accept();
		
		WebElement promtresElement=driver.findElement(By.id("result"));
		
		logger.info("asserted the promt text");
		Assert.assertEquals(promtresElement.getText(), "You entered: yes im prompt");
		
		System.out.println("prompt completed");
			
		
	}

	@Test(priority = 0)
	public void testIframes() {
		
		logger.info("opening the url for iframes");
		openurl("https://iframetest.com/?url=https://bing.com");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		WebElement myFramElement=driver.findElement(By.tagName("iframe"));
		
		
		driver.switchTo().frame(myFramElement);
		
		logger.info("switced to the child frame");
		System.out.println("swited to iframe");
		
		String frameTitle=driver.getTitle();
		
		logger.info("printed the title of the page"+frameTitle);
		System.out.println(frameTitle);
		
	
		driver.switchTo().defaultContent();
		
		logger.info("switced back to the main page");
		 System.out.println("Switched back to main page");
		 
		 String mainMageTitleString=driver.getTitle();
		 
		 logger.info("printed the main page title"+mainMageTitleString);
		 System.out.println("main page title"+mainMageTitleString);
	
	}
}

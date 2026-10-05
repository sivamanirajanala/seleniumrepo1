package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.*;

public class CrossBrowserTests extends newBaseTestClass{
	
	@Test
	public void loginTest() {
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		
	    driver.findElement(By.id("login2")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername"))).sendKeys(properties.getProperty("username"));
		
		driver.findElement(By.id("loginpassword")).sendKeys(properties.getProperty("password"));
		driver.findElement(By.xpath("//button[text()='Log in']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout2")));
		
		Assert.assertTrue(driver.findElement(By.id("logout2")).isDisplayed(),"ele is not visible");
		
		System.out.println("login success ful");
		
		
		
	}

}

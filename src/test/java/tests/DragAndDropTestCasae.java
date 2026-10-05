package tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import base.*;
public class DragAndDropTestCasae extends BaseTest{
	
	@Test
	public void testDragAndDrop() throws IOException {
		
		openurl("https://jqueryui.com/droppable/");
		driver.switchTo().frame(0);
		
		Actions actions=new Actions(driver);
		
		//before draganddrop
		
		takesScreenshot("beforeDarg");
		WebElement draggableElement=driver.findElement(By.id("draggable"));
		WebElement droppableElement=driver.findElement(By.id("droppable"));
		
		actions.dragAndDrop(draggableElement, droppableElement).perform();
		
		//after dropping
		
		takesScreenshot("afterDrop");
		
		 String actualText = droppableElement.getText();

	        if (actualText.equals("Dropped!")) {
	            System.out.println("Test Passed: Drag and drop successful");
	        } else {
	            System.out.println("Test Failed: Drag and drop unsuccessful");
	        }

	        
	        driver.switchTo().defaultContent();
		
		
		
	}
	
	public void takesScreenshot(String fileName) throws IOException {
		
		TakesScreenshot screenshot=(TakesScreenshot) driver;
		
		File source=screenshot.getScreenshotAs(OutputType.FILE);
		
		File targeFile=new File("./screenshots/"+fileName+".png");
		
	    FileUtils.copyFile(source, targeFile);
	    
	    System.out.println( "Screenshot saved: " + targeFile.getAbsolutePath() );
	
	}
	
}
    
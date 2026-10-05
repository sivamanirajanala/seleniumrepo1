package tests;

import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class DynamicTables extends BaseTest{
	
	@Test()
	public void testingTables() {
		
		
		openurl("https://the-internet.herokuapp.com/tables");
		
		
		WebElement el=driver.findElement(By.id("table1"));
		
		List<WebElement> rows=el.findElements(By.xpath(".//tbody/tr"));
		String expectedName="Bach";
		
		for(WebElement row:rows) {
			
			List<WebElement> cells=row.findElements(By.tagName("td"));
			String lastName=cells.get(0).getText();
			if(lastName.contains(expectedName)) {
				System.out.println("specified row found");
				for(WebElement cell:cells) {
					System.out.print(cell.getText()+" - ");
				}
				System.out.println();
				break;
             }
			
		}
		
		System.out.println("print all data of table");
		
		for(WebElement row:rows) {
			
			List<WebElement> cells=row.findElements(By.tagName("td"));
			for(WebElement cell:cells) {
				System.out.println(cell.getText()+" - ");
			}
			System.out.println();
		}
		
		
		System.out.println("sorting the tables column");
		
		WebElement lastnameRow=driver.findElement(By.xpath("//table[@id='table1']//th[1]"));
		
		lastnameRow.click();
		
		List<WebElement> sortedRows=el.findElements(By.xpath(".//tbody/tr"));
		List<String> actualNames=new ArrayList<>();
		
		for(WebElement row:sortedRows) {
			String lastName=row.findElements(By.tagName("td")).get(0).getText();
			actualNames.add(lastName);
		}
		
		List<String> expectedNames=new ArrayList<>(actualNames);
		
		Collections.sort(expectedNames);
		System.out.println("Actual order after sorting:");
        System.out.println(actualNames);

        System.out.println("Expected order:");
        System.out.println(expectedNames);
        
        Assert.assertEquals(actualNames, expectedNames,"Last Name column is not sorted correctly");

        System.out.println("Sorting validation passed.");
	
		
	}

}

package tests;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class DownloadFilesTest extends BaseTest{
	

	@Test()
	public void testDownload() throws InterruptedException, IOException {
		
		openurl("https://the-internet.herokuapp.com/download");
		
		File downloadFolder=new File(downloadPath);
		
		if(!downloadFolder.exists()) {
			downloadFolder.mkdir();
		}
		
		WebElement file= driver.findElement(By.xpath("//a[contains(@href,'.txt')]"));
		
		file.click();
		
		System.out.println("Download started");

        Thread.sleep(3000);
		
		String fileName=file.getText();
		
		System.out.println("this is file name: "+fileName);
		
		
		File downloadedFile=new File(downloadPath+File.separator+fileName);
		
		Assert.assertTrue(downloadedFile.exists(),"flie not downloaded");
		
		
		//asserting the content in the file
		BufferedReader reader =
		        new BufferedReader(
		                new FileReader(downloadedFile)
		        );

		StringBuilder content = new StringBuilder();

		String line;

		while ((line = reader.readLine()) != null) {
		    content.append(line).append("\n");
		}

		reader.close();

		String fileContent = content.toString();

		System.out.println("File content:");
		System.out.println(fileContent);

		
		Assert.assertFalse(
		        fileContent.trim().isEmpty(),
		        "Downloaded file is empty"
		);

		
		//the text in the file is asc
		
		Assert.assertTrue(
		        fileContent.contains("asc"),
		        "Expected text was not found in downloaded file"
		);

		System.out.println("File content validation passed");

	
	}

}

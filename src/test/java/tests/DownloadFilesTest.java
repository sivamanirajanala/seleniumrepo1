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

	    logger.info("download started");

	    logger.info("opening the URL");

	    openurl("https://the-internet.herokuapp.com/download");

	    takeScreenshot("download01");

	    File downloadFolder = new File(downloadPath);

	    if (!downloadFolder.exists()) {
	        downloadFolder.mkdir();
	        logger.info("download folder created: " + downloadPath);
	    }

	    WebElement file =
	            driver.findElement(By.xpath("//a[contains(@href,'.txt')]"));

	    String fileName = file.getText();

	    logger.info("file selected for download: " + fileName);

	    file.click();

	    logger.info("download started...");

	    Thread.sleep(3000);

	    System.out.println("This is file name: " + fileName);

	    File downloadedFile =
	            new File(downloadPath + File.separator + fileName);

	    logger.info("checking whether file exists...");

	    Assert.assertTrue(
	            downloadedFile.exists(),
	            "file not downloaded"
	    );

	    logger.info("file downloaded successfully: "
	            + downloadedFile.getAbsolutePath());

	    takeScreenshot("filedownload02");

	    logger.info("reading downloaded file content...");

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

	    logger.info("validating file content...");

	    Assert.assertFalse(
	            fileContent.trim().isEmpty(),
	            "downloaded file is empty"
	    );

	    Assert.assertTrue(
	            fileContent.contains("asc"),
	            "expected text was not found in downloaded file"
	    );

	    takeScreenshot("validatedcontent03");

	    System.out.println("file content validation passed");

	    logger.info("file content validation passed");

	    logger.info("download test passed");
	}

}

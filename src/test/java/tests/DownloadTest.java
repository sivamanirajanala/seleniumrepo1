package tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class DownloadTest {
	
	String downloadPath=System.getProperty("user.dir")+"\\DownloadFiles";
	String expectedFileName="sample-clouds-400x300.jpg";
	
	@BeforeSuite
	public void cleanup() throws IOException {
		File foleder=new File(downloadPath);
		FileUtils.cleanDirectory(foleder);
	}
	
	@Test
	public void verifyDownload() {
		
		ChromeOptions options=new ChromeOptions();
		
		Map<String,Object> prefs=new HashMap<String,Object>();
		prefs.put("download.default_directory", downloadPath);
		options.setExperimentalOption("prefs", prefs);
		
		WebDriver driver=new ChromeDriver(options);
		
		driver.manage().window().maximize();
		
		driver.get("https://samplelib.com/sample-jpeg.html");
		
		driver.findElement(By.cssSelector(".btn.btn-outline-primary.btn-sm")).click();
		
	}

}

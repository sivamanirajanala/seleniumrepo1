package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class BaseTest {

    protected WebDriver driver;

    protected String downloadPath = System.getProperty("user.dir")+ File.separator + "downloads";
    public Logger logger;

    protected Properties properties;
    @BeforeMethod
    public void setup() throws IOException {
    	
    	properties = new Properties();

        FileInputStream file =
                new FileInputStream(
                        "src/test/resources/config.properties"
                );

        properties.load(file);
        file.close();
    	
    	logger=LogManager.getLogger(this.getClass());

        File downloadFolder = new File(downloadPath);

        if (!downloadFolder.exists()) {
            downloadFolder.mkdirs();
        }

        
        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait( Duration.ofSeconds(10));

    
        Map<String, Object> params = new HashMap<>();

        params.put("behavior", "allow");
        params.put("downloadPath", downloadPath);

        ((ChromeDriver) driver).executeCdpCommand("Browser.setDownloadBehavior", params);
    
        
    }
    public void takeScreenshot(String fileName) throws IOException {

        TakesScreenshot ts = (TakesScreenshot) driver;

        File source = ts.getScreenshotAs(OutputType.FILE);

        File destination = new File(
                System.getProperty("user.dir")
                + File.separator
                + "screenshots"
                + File.separator
                + fileName + ".png"
        );

        File screenshotFolder = destination.getParentFile();

        if (!screenshotFolder.exists()) {
            screenshotFolder.mkdirs();
        }

        Files.copy(
                source.toPath(),
                destination.toPath(),
                StandardCopyOption.REPLACE_EXISTING
        );

        logger.info("Screenshot saved: " + destination.getAbsolutePath());
    }
    
    public void openurl(String url) {
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    	driver.get(url);
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}
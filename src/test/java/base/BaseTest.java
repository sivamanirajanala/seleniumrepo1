package base;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    protected String downloadPath = System.getProperty("user.dir")+ File.separator + "downloads";
    public Logger logger;

    @BeforeMethod
    public void setup() {
    	
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
    
    public void openurl(String url) {
    	driver.get(url);
    }
//
//    @AfterMethod
//    public void tearDown() {
//
//        if (driver != null) {
//            driver.quit();
//        }
//    }
}
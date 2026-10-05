package base;


	import java.io.FileInputStream;
import java.io.IOException;
	import java.time.Duration;
	import java.util.Properties;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Parameters;


	public class newBaseTestClass {
		
	    protected WebDriver driver;
	    protected Properties properties;

	    @BeforeMethod
	    @Parameters("browser")
	    public void setup(String browser) throws IOException {


	        properties = new Properties();

	        FileInputStream file =
	                new FileInputStream(
	                        "src/test/resources/config.properties"
	                );

	        properties.load(file);
	        file.close();

	        System.out.println("Browser: " + browser);


	        if (browser.equalsIgnoreCase("chrome")) {

	            driver = new ChromeDriver();

	        } else if (browser.equalsIgnoreCase("firefox")) {

	            driver = new FirefoxDriver();

	        } else {

	            throw new RuntimeException(
	                    "Invalid browser: " + browser
	            );
	        }

	        
	        driver.manage().window().maximize();

	        driver.manage().timeouts().implicitlyWait(
	                Duration.ofSeconds(10)
	        );


	        driver.get(
	                properties.getProperty("url")
	        );
	    }

	    @AfterMethod
	    public void tearDown() {

	        if (driver != null) {
	            driver.quit();
	        }
	    }
	
		
		
}

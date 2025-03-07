package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger;
	public Properties prop;
	
	@BeforeClass(groups = {"Smoke","Regression", "Master"})
	@Parameters ({"os","browser"})
	public void setup(String os, String br) throws IOException
	{
		//Loading config.properties file
		FileReader file = new FileReader("./src/test/resources/config.properties");
		       prop= new Properties();
		       prop.load(file);
		
		
	  logger = LogManager.getLogger(this.getClass());
	  
	  if(prop.getProperty("execution_env").equalsIgnoreCase("remote")) 
	  {
		  DesiredCapabilities capabilites = new DesiredCapabilities();

		  //os
		  if(os.equalsIgnoreCase("windows")) 
		  {
			  capabilites.setPlatform(Platform.WIN11);
		  }
		  else if(os.equalsIgnoreCase("mac")) 
		  {
			  capabilites.setPlatform(Platform.MAC);
		  }
		  else if(os.equalsIgnoreCase("linux")) 
		  {
			  capabilites.setPlatform(Platform.LINUX);
		  }
		  else 
		  {
			System.out.println("No Matching OS");
			return;
		  }
		  
		  //Browser
		  switch (br.toLowerCase()) 
		  {
		   	case "chrome": capabilites.setBrowserName("chrome");break;
		   	case "edge": capabilites.setBrowserName("edge");break;
		   	case "firefox": capabilites.setBrowserName("firefox");break;
		   	default: System.out.println("No Matching Browser"); return;
			
		  }
		  
		   driver = new RemoteWebDriver(new URL("http://localhost:4444/"), capabilites);
	  	  
	  }
	  
	  if(prop.getProperty("execution_env").equalsIgnoreCase("local")) 
	  {
		  switch (br.toLowerCase()) 
		  {
		     case "chrome": driver = new ChromeDriver();break;
		     case "edge": driver = new EdgeDriver();break;
		     case "firefox": driver = new FirefoxDriver();break;
	         default: System.out.println(" Invalid browser "); return;
			
		  } 
	  }
	  
	    
	  driver.manage().deleteAllCookies();
	  driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	  driver.get(prop.getProperty("appURL"));       // Reading url̥ from properties file
	  driver.manage().window().maximize();
	  
	  
	}
    @AfterClass(groups = {"Smoke","Regression", "Master"})
    public void tearDown() {
    	driver.quit();
    }
    
    public String randomString(){
    	String genarateString= RandomStringUtils.randomAlphabetic(6);
		return genarateString; 
	}
    
    public String randomNumber() {
    	String genarateNumber = RandomStringUtils.randomNumeric(10);
    	return genarateNumber;
		
	}
    
    public String randomAlphaNumric() {
    	String genarateString= RandomStringUtils.randomAlphabetic(4);
    	String genarateNumber = RandomStringUtils.randomNumeric(5);
    	return (genarateString+"@"+genarateNumber);
		
	}
    public String captureSreen(String tname) throws IOException{
		
    	String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
    	
    	TakesScreenshot ts = (TakesScreenshot)driver;
    	File sourceFile = ts.getScreenshotAs(OutputType.FILE);
    	
    	String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp+".png";
    	File targetFile = new File(targetFilePath);	
    	
    	sourceFile.renameTo(targetFile);
    	
    	return targetFilePath; 
	}
    
}

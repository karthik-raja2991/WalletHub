package Reources;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

public class PropertyManager {
	private static PropertyManager instance;
	private static final Object lock=new Object(); 
	private static String chrome_path;
	private static String firefox_path;
	private static String browsertoTest;
	private static WebDriver driver=null;
	public boolean browserAlreadyOpen=false;
	private static String userName;
	private static String password;
	
	
	// Create singleton instance
	public static PropertyManager getInstance() 
	{
		if(instance==null)
		{
			synchronized(lock) 
			{
				instance=new PropertyManager();
				instance.loadData();
			}
		}
		return instance;
	}
	
	public void loadData() 
	{
		Properties prop=new Properties();
		
		try 
		{
			prop.load(new FileInputStream(System.getProperty("user.dir")+"/src/config.properteis"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		chrome_path=prop.getProperty("chrome_path");
		firefox_path=prop.getProperty("firefox_path");
		browsertoTest=prop.getProperty("browsertoTest");
		userName=prop.getProperty("username");
		password=prop.getProperty("password");
		
	}
	
	public String getFirefoxPath() 
	{
		return firefox_path;
	}
	
	public String getChromePath()
	{
		return chrome_path;
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public WebDriver initBrowser()
	{
		if(!browserAlreadyOpen)
		{
			if(browsertoTest.equals("ChromeBrowser"))
			{
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
				System.setProperty("webdriver.chrome.driver",(System.getProperty("user.dir")+chrome_path));
				driver=new ChromeDriver(options);
			}
			else if(browsertoTest.equals("FFBrowser"))
			{
				FirefoxProfile profile=new FirefoxProfile();
				profile.setPreference("dom.webnotifications.enabled", false);
				DesiredCapabilities capabilities=new DesiredCapabilities();
				capabilities.setCapability(FirefoxDriver.PROFILE, profile);
				FirefoxOptions options=new FirefoxOptions();
				options.merge(capabilities);
				driver=new FirefoxDriver(options);
			}
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			browserAlreadyOpen=true;
		}
		return driver;
	}
	
	public void closeBrowser()
	{
		driver.quit();
		browserAlreadyOpen=false; 
	}
}

package Assignment1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import Pages.HomePage;
import Pages.LoginPage;
import Reources.PropertyManager;

public class FaceBookLogin {

	public static void main(String[] args) throws InterruptedException {
		
		String chromeDriverPath=PropertyManager.getInstance().getChromePath();
		String systemPath=System.getProperty("user.dir");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver",systemPath+chromeDriverPath);
		WebDriver driver=new ChromeDriver(options);
		
		LoginPage lp = new LoginPage(driver);
		
		//Navigating to Firefox Driver
		driver.get("https://www.facebook.com");
		
		// Maximixing the window
		driver.manage().window().maximize();
		
		// wait to user name feild to be displayed
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// Login to facebook with valid credentials
		lp.setUserName("karthik2991@gmail.com");
		
		lp.setPassword("2991@Raja");
		
		HomePage hp=lp.clickLoginButton();
		
		hp.clickOnStatustextBox();
		
		hp.postStatusMessage();
		
		hp.clickPostButton();
		
		Thread.sleep(2000);
		
		hp.logOut();
	}

}

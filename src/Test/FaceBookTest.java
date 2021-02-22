package Test;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;

import Pages.HomePage;
import Pages.LoginPage;
import Reources.PropertyManager;

public class FaceBookTest extends PropertyManager {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		// Initialise the driver based on input given in config.properteis files
		
		//Getinstance is the singleton method in PropertyManager class present in the Resource package
		WebDriver driver=getInstance().initBrowser();
		
		Logger log=Logger.getLogger("FaceBookTest");
		
		//Navigate to Facebook.com
		driver.get("https://www.facebook.com");
		log.info("Facebook website is launched");
		
		// Create the object of Pages class to use in the Test class
		LoginPage loginpage=new LoginPage(driver);
		HomePage homepage=new HomePage(driver);
		
		// get the user name and password from property manager class
		log.info("Getting user name and password from configuration files");
		String userName=getInstance().getUserName().toString();
		String password=getInstance().getPassword().toString();
		
		// Login facebook using valid credentials
		loginpage.setUserName(userName);
		loginpage.setPassword(password);
		
		// Click on Sign in button
		homepage=loginpage.clickLoginButton();
		
		log.info("User name and password feild is provided");
		
		// using homepage class enter the status
		homepage.clickOnStatustextBox();
		homepage.postStatusMessage();
		homepage.clickPostButton();
		
		log.info("Hello world status is posted in facebook website");
		
		// wait for some time. Though implicit wait and explixit waits are defined in page classes, its taking some addtioal time to post the message
		Thread.sleep(2000);
		
		// CLick on logout button
		homepage.logOut();	
		
		log.info("Facebook is logged out");
	}

}

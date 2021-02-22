package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	WebDriver driver;
	
	By userName=By.id("email");
	By password=By.id("pass");
	By loginButton=By.name("login");
	
	
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	// Set User Name feild
	public void setUserName(String username)
	{
		driver.findElement(userName).sendKeys(username);
	}
	
	// Set password feild
	public void setPassword(String passWord)
	{
		driver.findElement(password).sendKeys(passWord);
	}
	
	//CLick on Login button
	public HomePage clickLoginButton()
	{
		driver.findElement(loginButton).click();
		return new HomePage(driver);
	}

}

package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
WebDriver driver;
	
	By statusText=By.xpath("//span[contains(text(),'on your mind')]");
	By postMessage=By.xpath("//div[contains(@aria-describedby,'placeholder')]");
	By postButton=By.xpath("//div[@aria-label='Post' and @role='button']");
	By logOutMenu=By.xpath("//div[@aria-label='Account Controls and Settings']//div[@aria-label='Account']");
	By logoutButton=By.xpath("//span[contains(text(),'Log Out')]");
	
	public HomePage(WebDriver driver) 
	{
		this.driver=driver;
	}
	
	// CLick on status icon
	public void clickOnStatustextBox()
	{
		(new WebDriverWait(driver,25)).until(ExpectedConditions.elementToBeClickable(statusText)).click();
	}
	
	public void postStatusMessage()
	{
		(new WebDriverWait(driver,25)).until(ExpectedConditions.elementToBeClickable(postMessage)).sendKeys("hello WOrld");
	}
	
	public void clickPostButton() throws InterruptedException
	{
		driver.findElement(postButton).click();
		JavascriptExecutor j = (JavascriptExecutor)driver;
		if(!j.executeScript("return document.readyState").toString().equals("complete"))
		{
			new WebDriverWait(driver,25).until(ExpectedConditions.elementToBeClickable(postMessage));
			Thread.sleep(3000);
		}
	}
	
	public LoginPage logOut()
	{
		WebElement logOutmenu=(new WebDriverWait(driver,10)).until(ExpectedConditions.elementToBeClickable(logOutMenu));
		logOutmenu.click();
		WebElement logOutButton=(new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(logoutButton));
		logOutButton.click();
		return new LoginPage(driver);
	}
}

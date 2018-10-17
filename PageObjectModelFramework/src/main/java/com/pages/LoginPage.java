package com.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

public class LoginPage extends LoadableComponent<LoginPage> {
		
	WebDriver driver;
	
	@FindBy(name="email")
	private WebElement emailTxtBox;
	
	@FindBy(name="password")
	private WebElement pasword;
	
	@FindBy(xpath="//button[@type='submit']")
	private WebElement loginBtn;
	
	@FindBy(xpath="//div[@class='alert alert-danger loading wow fadeIn animated animated']")
	private WebElement errorMsg;
	
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void loginAsAdmin(String email,String password) {
		
		emailTxtBox.clear();;
		emailTxtBox.sendKeys(email);
		pasword.sendKeys(password);
		loginBtn.click();
	}
	public String getErrorMessage() {
		return errorMsg.getText();
	}
	public String getPageTitle() {
		return driver.getTitle();
	}

	@Override
	protected void load() {
		
		driver.get("https://www.phptravels.net/admin");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Override
	protected void isLoaded() throws Error {
		
		String url=driver.getCurrentUrl();
		Assert.assertTrue(url.startsWith("https://www.phptravels.net"), "Some Error!! not onthe correct page");
		
	}
	
	
}

package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Dashboardpage {
		
	WebDriver driver;
	
	@FindBy(xpath="//a[contains(text(),'Log Out')]")
	private WebElement logOut;
	
	
	
	public Dashboardpage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void logOutAdmin() {
		
		logOut.click();
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
}

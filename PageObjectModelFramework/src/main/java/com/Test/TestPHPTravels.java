package com.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.pages.Dashboardpage;
import com.pages.LoginPage;
import com.utility.DriverUtilityClass;
import com.utility.ExcelUtility;

public class TestPHPTravels {
	
	WebDriver driver;
	LoginPage login;
	Dashboardpage dp;
	
	@BeforeTest
	public void openApp() {
	  
		driver=DriverUtilityClass.initializeDriver("chrome");
		driver.get("https://www.phptravels.net/admin");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		login=PageFactory.initElements(driver, LoginPage.class);
		dp=PageFactory.initElements(driver, Dashboardpage.class);
		
	}
	@Test(priority=0)
	public void validAdminTest() throws InterruptedException
	{
		//login.get();
		
		
		String excelPath = "C:\\Users\\A07208trng_b4a.03.28\\Documents\\shoaib\\Admin.xlsx";
		
		ExcelUtility exu = new ExcelUtility(excelPath);
		String sheetName = "Admin";
		int rowCount = exu.getNumOfRows(sheetName);
		int colCount = exu.getNumofCols(sheetName);
		String email = null;
		String pwd = null;

		for (int i = 1; i < rowCount; i++) {
			email = exu.getCellData(sheetName, i, 0);
			pwd = exu.getCellData(sheetName, i, 1);
			
			login.loginAsAdmin(email,pwd);
			
			Thread.sleep(6000);
			
			if (dp.getPageTitle().equals("Dashboard")) {
				exu.setCellData(sheetName, i, 2, "PASS");
				
				dp.logOutAdmin();
				
				Thread.sleep(6000);
			} else {
				exu.setCellData(sheetName, i, 2, "FAIL");
				
			}
		}
		
	}
	
	/*@Test(priority = 0 )
	public void validAdminTest() throws InterruptedException
	{
	lp.get();
	lp.loginAsAdmin("admin@phptravels.com ", "demoadmin");
	Thread.sleep(10000);
	Assert.assertEquals(dp.getPageTitle(), "Dashboard");
	dp.logOutAsAdmin();
	Thread.sleep(10000);
	Assert.assertEquals(lp.getPageTitle(), "Administator Login");
	}
	*/
	@Test(priority=1)
	public void invalidAdminErromessagetest() throws InterruptedException
	{
		String expectedError ="Invalid Login Credentials";
		login.loginAsAdmin("adn@phptravels.com", "demoadmin");
		Thread.sleep(8000);
		Assert.assertEquals(login.getErrorMessage(), expectedError);
		}
	
	@Test(priority=2)
	public void invalidAdminEmail() throws InterruptedException
	{
		String expectedError ="The Email field must contain a valid email address.";
		login.loginAsAdmin("adnels", "demoadmin");
		Thread.sleep(8000);
		Assert.assertEquals(login.getErrorMessage(), expectedError);
		}
	
	
	@AfterTest
	public void closeApp() {
		
		driver.quit();
		login=null;
		dp=null;
	}
}

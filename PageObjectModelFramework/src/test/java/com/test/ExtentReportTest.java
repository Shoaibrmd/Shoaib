package com.test;

import java.io.File;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportTest {
	
	ExtentReports extent;
	ExtentTest logger;
	
	@BeforeTest
	public void startReport() {
		extent= new ExtentReports(System.getProperty("user.dir")+
				"/target/surfire-reports/ExtentReportResults.html", true);
		extent.addSystemInfo("HostName", "Selenium Test")
						.addSystemInfo("Environment","Automation Testing")
								.addSystemInfo("User Name","Shoaib R");
		extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
		
	}
	@AfterMethod
	public void generateResults(ITestResult result) {
		
		if(result.getStatus()==ITestResult.FAILURE) {
			logger.log(LogStatus.FAIL,"test CAse failed is: "+result.getName());
			logger.log(LogStatus.FAIL,"test CAse failed is: "+result.getThrowable());
		}
		else if(result.getStatus()==ITestResult.SKIP) {
			logger.log(LogStatus.SKIP,"test CAse skipped is: "+result.getName());
			logger.log(LogStatus.SKIP,"test CAse skipped is: "+result.getThrowable());
		}
		extent.endTest(logger);
	}
   @Test(priority=1)
   public  void Register() {
	   
	   logger=extent.startTest("register");
	   Assert.assertTrue(true);
	   logger.log(LogStatus.PASS,"Test case passed ");
   }
   @Test(priority=2)
   public  void login() {
	   
	   logger=extent.startTest("login");
	   Assert.assertTrue(false);
	   logger.log(LogStatus.PASS,"Test case passed ");
   }
   @Test(priority=3)
   public  void mobileRecharge() {
	   
	   logger=extent.startTest("mobile Recharge");
	  throw new SkipException("This is skipped, not  ready for testing");
   }

	@AfterTest
	public void close() {
		extent.flush();
		extent.close();
		
	}
}
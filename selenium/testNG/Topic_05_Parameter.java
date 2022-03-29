package testNG;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



public class Topic_05_Parameter {
	WebDriver driver;

	String projectPath = System.getProperty("user.dir");
	@Parameters({"brower"})
 @BeforeClass(alwaysRun =true)
 public void initBrower(String browerName) {
	 System.err.println("Mo brower");
	 System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
 }
 
	@Test(groups= {"user"})
	public void TC_01_Creat_User() {
		
	}
	@Test(groups= {"user"})
	public void TC_02_View_User() {
		
	}
	@Test(groups= {"user","admin"})
	public void TC_03_Edit_User() {
		
	}
	@Test(groups= {"user","admin"})
	public void TC_04_Delete_User() {
		
	}
 
	@AfterClass(alwaysRun =true)
	 public void closeBrower() {
		 System.err.println("dong brower");
		 driver.quit();
	 }
	 
}

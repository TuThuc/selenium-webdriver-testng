package testNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Group_User {
 @BeforeClass
 public void initBrower() {
	 System.err.println("Mo brower");
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
 
	@AfterClass
	 public void closeBrower() {
		 System.err.println("dong brower");
	 }
	 
}

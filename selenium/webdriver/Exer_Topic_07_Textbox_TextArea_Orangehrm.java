package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Exer_Topic_07_Textbox_TextArea_Orangehrm {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, employeeID, editFristName, editLastName;
	By firstNameTextboxBy = By.id("firstName");
	By lastNameTextboxBy = By.id("lastName");
	By personalFirstNameTextboxBy = By.id("personal_txtEmpFirstName");
	By personalLastNameTextboxBy = By.id("personal_txtEmpLastName");
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		firstName="Tony";
		lastName = "Adam";
		 editFristName="Steven";
		 editLastName="Gerrard";
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    driver.get("https://opensource-demo.orangehrmlive.com/");
	}

	@Test
	public void TC_01_Login() {
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");	
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");	
	    driver.findElement(By.id("btnLogin")).click();
	    sleepInSecond(5);
	    
	}

	@Test
	public void TC_02_Add_Employee() {
		driver.findElement(By.id("menu_pim_viewPimModule"))	.click();
		driver.findElement(By.id("menu_pim_addEmployee")).click();
		// add new employee
		driver.findElement(firstNameTextboxBy).sendKeys(firstName);
	    driver.findElement(lastNameTextboxBy).sendKeys(lastName);
	    employeeID = driver.findElement(By.id("employeeId")).getAttribute("value");
	    driver.findElement(By.id("btnSave")).click();
	    sleepInSecond(3);
	    // Verify truong da nhap
	    Assert.assertEquals(driver.findElement(personalFirstNameTextboxBy).getAttribute("value"), firstName);
	    Assert.assertEquals(driver.findElement(personalLastNameTextboxBy).getAttribute("value"), lastName);
	    //Verify textbox bị disabled
	    Assert.assertFalse(driver.findElement(personalFirstNameTextboxBy).isEnabled());
	    Assert.assertFalse(driver.findElement(personalLastNameTextboxBy).isEnabled());
	    Assert.assertFalse(driver.findElement(By.id("personal_txtEmployeeId")).isEnabled());
	    // click button Edit
	    driver.findElement(By.id("btnSave")).click();
	    //update firstname va lastname
	    driver.findElement(personalFirstNameTextboxBy).clear();
	    driver.findElement(personalFirstNameTextboxBy).sendKeys(editFristName);
	    driver.findElement(personalLastNameTextboxBy).clear();
	    driver.findElement(personalLastNameTextboxBy).sendKeys(editLastName);
	    //Verify firstname, lastname is enabled
	    Assert.assertTrue(driver.findElement(personalFirstNameTextboxBy).isEnabled());
	    Assert.assertTrue(driver.findElement(personalLastNameTextboxBy).isEnabled());
	    //Click Save
	    driver.findElement(By.id("btnSave")).click();
	    //Verify firstname/lastname/employeeID value matching with input
	    Assert.assertEquals(driver.findElement(personalFirstNameTextboxBy).getAttribute("value"), editFristName);
	    Assert.assertEquals(driver.findElement(personalLastNameTextboxBy).getAttribute("value"), editLastName);
	   
	  //Verify firstname, lastname is disabled
	    Assert.assertFalse(driver.findElement(personalFirstNameTextboxBy).isEnabled());
	    Assert.assertFalse(driver.findElement(personalLastNameTextboxBy).isEnabled());
	    
	    // click to 'imigration' tab
	    driver.findElement(By.xpath("//a[text()='Immigration']")).click();
	    //click add
	    driver.findElement(By.id("btnAdd")).click();
	    driver.findElement(By.id("immigration_type_flag_2")).click();
	    driver.findElement(By.id("immigration_number")).sendKeys("232344223");
	    driver.findElement(By.id("immigration_comments")).sendKeys("test\nautomation\n24");
	    driver.findElement(By.id("btnSave")).click();
	    sleepInSecond(3);
	    driver.findElement(By.xpath("//a[text()='Visa']")).click();
	    //Verify data input
	    Assert.assertEquals(driver.findElement(By.id("immigration_number")).getAttribute("value"), "232344223");
	    Assert.assertEquals(driver.findElement(By.id("immigration_comments")).getAttribute("value"), "test\nautomation\n24");
	    
	    
	    
	    
	    
	    
	    
	    
		
	}
	@Test
	public void TC_03_Edit_Employee() {
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second*1000);
		}
		catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		}
	}

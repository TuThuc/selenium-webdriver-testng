package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Exer_Topic04_05_Login {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firtsName, lastName, fullName, emailAddress, password, nonExistedEmail;
	By emailTextboxBy = By.id("email");
	By passwordTextboxBy = By.id("pass");
	By buttonLoginBy = By.id("send2");
	By messErrorEmailEmpty = By.id("advice-required-entry-email");
	By messErrorEmailInvalid = By.id("advice-validate-email-email");
	By messErrorPassEmpty = By.id("advice-required-entry-pass");
	By messErrorPassInvalid = By.id("advice-validate-password-pass"); 

	@BeforeClass
	// chạy trước cho testcase đầu tiên
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		firtsName= "Steve";
		lastName= "Job"; 
		fullName= firtsName+ " " +lastName;
		emailAddress= "stevejob" + getRandomNumber() +"@hotmail.net"; 
		nonExistedEmail= "stevejob" + getRandomNumber() +"@livemail.net"; 
		password="123456";
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	@BeforeMethod
	//Chạy trước cho từng testcase
	public void beforeMethod() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	
	}

	@Test
	public void TC_01_Login_WithEmailPass_Empty() {
		driver.findElement(emailTextboxBy).clear();
		driver.findElement(passwordTextboxBy).clear();
		driver.findElement(buttonLoginBy).click();
		// Verify message error
		Assert.assertEquals(driver.findElement(messErrorEmailEmpty).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(messErrorPassEmpty).getText(), "This is a required field.");	
	}

	@Test
	public void TC_02_Login_WithEmail_Invalid() {
		driver.findElement(emailTextboxBy).sendKeys("213@123");
		driver.findElement(passwordTextboxBy).sendKeys("123456");
		driver.findElement(buttonLoginBy).click();
		// Verify message email invalid
		Assert.assertEquals(driver.findElement(messErrorEmailInvalid).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
		
	}
	@Test
	public void TC_03_Login_WithPassword_Invalid() {
		driver.findElement(emailTextboxBy).sendKeys("automationfc@gmail.com");
		driver.findElement(passwordTextboxBy).sendKeys("123");
		driver.findElement(buttonLoginBy).click();
		// Verify message password invalid
		Assert.assertEquals(driver.findElement(messErrorPassInvalid).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	}
	@Test
	public void TC_04_Creat_New_Account_Success() {
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		//Existed Email
		driver.findElement(By.id("firstname")).sendKeys(firtsName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		//Verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title>h1")).getText(), "MY DASHBOARD");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");
		Assert.assertEquals(driver.findElement(By.cssSelector("p.hello>strong")).getText(), "Hello, " + fullName + "!");
		String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(contactInformation.contains(fullName));
		Assert.assertTrue(contactInformation.contains(emailAddress));
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div.page-title img[src$='logo.png']")).isDisplayed());
		
	}
	@Test
	public void TC_05_Login_Password_Or_Email_Incorrect() {
		// exist email+ incorrect Password ==> Uncuccess
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(passwordTextboxBy).sendKeys("1234567");
		driver.findElement(buttonLoginBy).click();
		// Verify message password invalid
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).getText(), "Invalid login or password.");
	   //Non exist Email +correct/valid Password -> Unsuccess
		driver.findElement(emailTextboxBy).clear();
		driver.findElement(emailTextboxBy).sendKeys(nonExistedEmail);
		driver.findElement(passwordTextboxBy).clear();
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(buttonLoginBy).click();
		
	}
	
	
	@Test
	public void TC_06_Login_Password_And_Email_Valid() {
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(buttonLoginBy).click();
		//Verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title>h1")).getText(), "MY DASHBOARD");
		Assert.assertEquals(driver.findElement(By.cssSelector("p.hello>strong")).getText(), "Hello, " + fullName + "!");
		String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(contactInformation.contains(fullName));
		Assert.assertTrue(contactInformation.contains(emailAddress));
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void sleepInSecond(long second) {
		
		}
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
		
	}
	}

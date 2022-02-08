package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Exer_Topic_06_Browner {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	By buttonMyAccountBy = By.xpath("//div[@class='footer']//a[@title='My Account' ]");
	By buttonCreatanAccountBy = By.xpath("//a[@title='Create an Account']");
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	
	
	}
	@BeforeMethod
	public void beforeMethod() {
		driver.get("http://live.techpanda.org/");
		
	}

	@Test
	public void TC_01_Verify_UrlCurent() {
		driver.findElement(buttonMyAccountBy).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		driver.findElement(buttonCreatanAccountBy).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
	}

	@Test
	public void TC_02_Verify_TitlePage() {
		driver.findElement(buttonMyAccountBy).click();
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		driver.findElement(buttonCreatanAccountBy).click();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		
	}
	@Test
	public void TC_03_Verify_Navigate() {
		driver.findElement(buttonMyAccountBy).click();
		driver.findElement(buttonCreatanAccountBy).click();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		driver.navigate().back();
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		driver.navigate().forward();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		
	}
	@Test
	public void TC_04_Verify_PageSource() {
		driver.findElement(buttonMyAccountBy).click();
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		driver.findElement(buttonCreatanAccountBy).click();
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
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

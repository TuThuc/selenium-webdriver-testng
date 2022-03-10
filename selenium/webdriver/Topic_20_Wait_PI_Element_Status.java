package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Wait_PI_Element_Status {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait expliciWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		expliciWait = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_Visible() {
		//Visible: Có trên UI và có trong DOM/HTML
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='email']")).isDisplayed());
	}

	@Test
	public void TC_02_Invisible_In_DOM() {
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		//Invisible: Không có trên UI và có trong DOM(K bắt buộc)
		//Ket qua như nhau nhưng thời gian chạy moi case khác nhau 
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name= 'reg_email_confirmation__']")));
		//Khong hien thi->Pass ~1s
		Assert.assertFalse(driver.findElement(By.xpath("//input[@name= 'reg_email_confirmation__']")).isDisplayed());
	}

		@Test
		public void TC_03_Invisible_Not_In_DOM() {	
			//Invisible: Không có trên UI và không có trong DOM(K bắt buộc)
			driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
			sleepInSecond(2);
			//chạy mất 15s
			expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name= 'reg_email_confirmation__']")));
			//Khong hien thi->Fail ->20s
			Assert.assertFalse(driver.findElement(By.xpath("//input[@name= 'reg_email_confirmation__']")).isDisplayed());
		}
	@Test
	public void TC_03_Presence() {
		
	}
	@Test
	public void TC_04_Staleness() {
		
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

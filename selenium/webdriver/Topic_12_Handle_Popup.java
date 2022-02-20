package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Handle_Popup {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//Trạng thái của element
		expliciWait = new WebDriverWait(driver, 30);
		//Để tìm Element
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	
	}

	//@Test
	public void TC_01_Fix_Popup() {
	driver.get("https://ngoaingu24h.vn/");
	By madalLoginPopup = By.xpath("//div[@id='modal-login-v1']");
	// kiểm tra popup khong hien thi
	Assert.assertFalse(driver.findElement(madalLoginPopup).isDisplayed());
	
	//Click đăng nhập
	driver.findElement(By.cssSelector("button.icon-before")).click();
	sleepInSecond(3);
	
	//Kiem tra popup dang nhap hien thi
	 Assert.assertTrue(driver.findElement(madalLoginPopup).isDisplayed());
	 
	 // Nhập thông tin vào popup
	 driver.findElement(By.id("account-input")).sendKeys("automationfc");
	 driver.findElement(By.id("password-input")).sendKeys("automationfc");
	 //Click Đăng nhap
	 driver.findElement(By.cssSelector("button.btn-login-v1")).click();
	 
	 //Kiem tra text hien thi
	 
	 Assert.assertEquals(driver.findElement(By.cssSelector("div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
	 //Assert.assertEquals(driver.findElement(By.xpath("//div[@class='input']/following-sibling::div[text()='Tài khoản không tồn tại!']")).getText(), "Tài khoản không tồn tại!");
	driver.findElement(By.cssSelector("div#modal-login-v1 button.close")).click();
	Assert.assertFalse(driver.findElement(madalLoginPopup).isDisplayed());

	}
	@Test
	public void TC_02_Fix_Popup() {
	driver.get("https://tiki.vn/");
	By  loginWithPhone = By.cssSelector("div[class*='LoginWithPhone']");
	
	Assert.assertEquals(driver.findElements(loginWithPhone).size(),0);

	
	driver.findElement(By.cssSelector("span.account-label")).click();
	sleepInSecond(3);
	Assert.assertTrue(driver.findElement(loginWithPhone).isDisplayed());

	driver.findElement(By.cssSelector("p.login-with-email")).click();
	
	driver.findElement(By.name("email")).sendKeys("abc@email.com");
	driver.findElement(By.cssSelector("input[type='password']")).sendKeys("123456");
	driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
	
	Assert.assertEquals(driver.findElement(By.cssSelector("span.error-mess")).getText(), "Thông tin đăng nhập không đúng");
	
	driver.findElement(By.xpath("//button[text()='Đăng nhập']")).sendKeys(Keys.ESCAPE);
	//driver.findElement(By.cssSelector("img.close-img")).click();
	sleepInSecond(2);
	Assert.assertEquals(driver.findElements(loginWithPhone).size(),0);

	}
	@Test
	public void TC_03_Fix_Popup() {
		driver.get("https://jtexpress.vn/");
		By homePagePopup = By.cssSelector("div#homepage-popup");
		//Chờ cho popup hiển thị rồi mới verify
		expliciWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(homePagePopup));
		//Kiem tra popup hien thi
		 Assert.assertTrue(driver.findElement(homePagePopup).isDisplayed());
		 
		 //Click close
		driver.findElement(By.cssSelector("div#homepage-popup button.close")).click();
		Assert.assertFalse(driver.findElement(homePagePopup).isDisplayed());
		driver.findElement(By.cssSelector("input#billcodes")).sendKeys("841000072647");
		driver.findElement(By.xpath("//form[@id='formTrack']//button[text()='Tra cứu vận đơn']")).click();
		sleepInSecond(5);
		

		}
	//@Test
	public void TC_03_Random_In_DOM() {
		
	}
	@Test
	public void TC_04_Random_NotIn_DOM() {
		
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

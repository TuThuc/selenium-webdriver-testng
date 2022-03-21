package webdriver;

import java.time.Duration;
import java.util.NoSuchElementException;


import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_20_Wait_PVII_Fluent {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	FluentWait<WebDriver> fluentDriver;
	FluentWait<WebElement> fluentElement;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01() {
		//Find Element với tổng thời gian 15s
		//Tần số lặp lại để tìm nếu không thấy là 1s/1 lần
		fluentDriver = new FluentWait<WebDriver>(driver);
		// Tong thoi gian chờ cho dieu kien
		fluentDriver.withTimeout(Duration.ofSeconds(15))
				// Polling time: lặp lại để tìm điều kiện nếu chưa thỏa mãn
				.pollingEvery(Duration.ofSeconds(1))
				// Nếu gặp exception là find khong thấy element sẽ bỏ qua
				.ignoring(NoSuchElementException.class)
       // Điều kiện của Fluent Wait
		.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//input[@name='btnI-fail']"));
			}
		});
		//Setting time
		WebElement loginButton = driver.findElement(By.xpath(""));
		fluentElement = new FluentWait<WebElement>(loginButton);
		fluentElement.withTimeout(Duration.ofSeconds(60))
		.pollingEvery(Duration.ofMillis(200))
		.ignoring(ElementNotVisibleException.class);
		//Apply điều kiện và trả về dữ liệu String
		String loginButtonText =fluentElement.until(new Function<WebElement, String>() {
			public String apply(WebElement element) {
				
				return element.getText();
			}
		});
		Assert.assertEquals(loginButton, "");
	//Apply điều kiện trả về kiểu boolean
		boolean loginButtonStatus = fluentElement.until(new Function<WebElement, Boolean>() {
			public Boolean apply(WebElement element) {
				
				return element.isDisplayed();
			}
		});
				Assert.assertTrue(loginButtonStatus);
	}
	
		
	@Test
	public void TC_02() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
		}
		
	

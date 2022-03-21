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
	driver.get("https://automationfc.github.io/dynamic-loading/");
	
	fluentDriver = new  FluentWait<WebDriver>(driver);
	
	driver.findElement(By.cssSelector("div#start>button")).click();
	
	// Sau khi bấm Loading icon xuất hiện và mất sau 5s
	// Icon loading biến mất = Helloword xuất hi ện
	
	fluentDriver.withTimeout(Duration.ofSeconds(6))
	.pollingEvery(Duration.ofSeconds(1))
	.ignoring(NoSuchElementException.class)
	.until(new Function<WebDriver, String>() { 

		@Override
		public String apply(WebDriver driver) {
			String text = driver.findElement(By.cssSelector("div#finish>h4")).getText();
			System.out.println(text);
			return text;
		}
	}          ); 
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
		
	

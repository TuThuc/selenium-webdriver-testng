package webdriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Wait_Part_III_Implicit {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		
	}

@Test
	public void TC_01() {
	driver.get("https://automationfc.github.io/dynamic-loading/");
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		Assert.assertEquals(driver.findElement(By.xpath("//h4[text()='Hello World!']")).getText(), "Hello World!");
	}

@Test
public void TC_02() {
driver.get("https://automationfc.github.io/dynamic-loading/");
driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	driver.findElement(By.xpath("//button[text()='Start']")).click();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	Assert.assertEquals(driver.findElement(By.xpath("//h4[text()='Hello World!']")).getText(), "Hello World!");
}
@Test
public void TC_03() {
driver.get("https://automationfc.github.io/dynamic-loading/");

	driver.findElement(By.xpath("//button[text()='Start']")).click();

	Assert.assertEquals(driver.findElement(By.xpath("//h4[text()='Hello World!']")).getText(), "Hello World!");
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

package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Wait_Part_IV_Static {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

	}

	@Test
	public void TC_01_Equal() throws InterruptedException {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(By.xpath("//button[text()='Start']")).click();
		// Loading icon bien mat
		Thread.sleep(5000);

		Assert.assertEquals(driver.findElement(By.xpath("//h4[text()='Hello World!']")).getText(), "Hello World!");
	}

	@Test
	public void TC_02_Less() throws InterruptedException {
		driver.get("https://automationfc.github.io/dynamic-loading/");
//2s khong du de loading icon bien mat
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		Thread.sleep(2000);

		Assert.assertEquals(driver.findElement(By.xpath("//h4[text()='Hello World!']")).getText(), "Hello World!");
	}

	@Test
	public void TC_03_Greater() throws InterruptedException {
		driver.get("https://automationfc.github .io/dynamic-loading/");

		driver.findElement(By.xpath("//button[text()='Start']")).click();
		// 10s due loading icon bien mat= du 5s
		Thread.sleep(10000);

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

package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Actions_Part_I {
	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor)driver;
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Hover() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(), "We ask for your age only for statistical purposes.");
	}

	//@Test
	public void TC_02_Hover_To_Element() {
		driver.get("https://www.fahasa.com/");
		action.moveToElement(driver.findElement(By.xpath("//div[contains(@class,'background-menu-homepage')]//span[text()='Sách Trong Nước']"))).perform();
		sleepInSecond(3);
		action.click(driver.findElement(By.xpath("//div[contains(@class,'background-menu-homepage')]//a[text()='Kỹ Năng Sống']"))).perform();
		sleepInSecond(5);

		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Kỹ năng sống']")).isDisplayed());
		}
	//@Test
	public void TC_03_Click_And_Hold_Block() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		List<WebElement> retangleNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		System.out.println("Tổng element = " + retangleNumber.size());
		
		action.clickAndHold(retangleNumber.get(0)).moveToElement(retangleNumber.get(7)).release().perform();
		sleepInSecond(4);
		List<WebElement> retangleNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li[class$='ui-selected']"));
		System.out.println("Tổng element selected = " + retangleNumberSelected.size());
		sleepInSecond(4);
		Assert.assertEquals(retangleNumberSelected.size(), 8);
		
	}
	//@Test
	public void TC_04_Click_And_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> retangleNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		System.out.println("Tổng element = " + retangleNumber.size());
		// Dang nhan control xuong
		action.keyDown(Keys.CONTROL).perform();
		action.click(retangleNumber.get(2))
		.click(retangleNumber.get(4))
		.click(retangleNumber.get(10)).click(retangleNumber.get(5));
		//Nhả phím Ctrl ra
		action.keyUp(Keys.CONTROL).perform();
		sleepInSecond(5);
		List<WebElement> retangleNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li[class$='ui-selected']"));
		System.out.println("Tổng element selected = " + retangleNumberSelected.size());
		sleepInSecond(4);
		Assert.assertEquals(retangleNumberSelected.size(), 4);
		
	}
	@Test
		public void TC_05_Double_Click() {
			driver.get("https://automationfc.github.io/basic-form/index.html");
			//scroll to element
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']")));
			sleepInSecond(2);
			action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
			sleepInSecond(3);
			Assert.assertEquals(driver.findElement(By.id("demo")).getText(), "Hello Automation Guys!");
			
			}
	@Test
	public void TC_07_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		sleepInSecond(3);
		action.clickAndHold(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-paste.context-menu-hover.context-menu-visible")).isDisplayed());
	action.click(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
	sleepInSecond(3);
	Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: paste");
	driver.switchTo().alert().accept();
	Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());

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

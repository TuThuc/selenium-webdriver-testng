package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Wait_PVIII_Page_Loading {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	
	}

	//@Test
	public void TC_01_OrangeHRM_Implicit() {
		driver.get("https://api.orangehrm.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='OrangeHRM REST API Documentation']")).getText(), "OrangeHRM REST API Documentation");
	}
	//@Test
	public void TC_02_OrangeHRM_Explicit() {
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner")));
		Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='OrangeHRM REST API Documentation']")).getText(), "OrangeHRM REST API Documentation");
	}
	//@Test
	public void TC_03_OrangeHRM_Page_Ready() {
		driver.get("https://api.orangehrm.com/");

		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		//Wait cho page loading success/ready
		Assert.assertTrue(isPageLoadedSuccess());
		Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='OrangeHRM REST API Documentation']")).getText(), "OrangeHRM REST API Documentation");
	}
	@Test
	public void TC_04_OrangeHRM_UI_Page_Ready() {
		driver.get("https://opensource-demo.orangehrmlive.com");
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		// Cách 1: Wait page ready
		Assert.assertTrue(isPageLoadedSuccess());
		//Cách 2: Wait cho loading icon biến mất(Invisible)
		Assert.assertTrue(driver.findElement(By.cssSelector("div#div_graph_display_emp_distribution")).isDisplayed());
		
	}
	// Only JQuery + JavaScript
	public boolean isPageLoadedSuccess() {
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor= (JavascriptExecutor)driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean)jsExecutor.executeScript("return (window.JQuery!=null) && (JQuery.active === 0);");
				
			}
		};
			ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {

				@Override
				public Boolean apply(WebDriver driver) {
					return (Boolean)jsExecutor.executeScript("return document.readyState").toString().equals("complete");
					
				}
			
			};
			return explicitWait.until(jQueryLoad)&& explicitWait.until(jsLoad);
		 	
	}
		
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	}

package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.interactions.Actions;
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
	Actions action;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 30);
		action = new Actions(driver); 
	}

	//@Test
	public void TC_01_OrangeHRM_Implicit() {
		driver.get("https://api.orangehrm.com/");
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
	//@Test
	public void TC_04_OrangeHRM_UI_Page_Ready() {
		String searchEmployee = "Peter Mac";
		driver.get("https://opensource-demo.orangehrmlive.com");
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		// C??ch 1: Wait page ready
		Assert.assertTrue(isPageLoadedSuccess());
		//C??ch 2: Wait cho loading icon bi???n m???t(Invisible)
//explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.loadmask"))))	;	
		Assert.assertTrue(driver.findElement(By.cssSelector("div#div_graph_display_emp_distribution")).isDisplayed());
		driver.findElement(By.cssSelector("a#menu_pim_viewPimModule")).click();
		Assert.assertTrue(isPageLoadedSuccess());
		driver.findElement(By.id("empsearch_employee_name_empName")).sendKeys(searchEmployee);
		driver.findElement(By.id("searchBtn")).click();
		Assert.assertTrue(isPageLoadedSuccess());
		//Verify k???t qu???
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Peter Mac']")).getText(),searchEmployee);
		
	}
	@Test
	public void TC_05_Test_Project_Page_Ready() {
		driver.get("https://blog.testproject.io/");
	//Handle popup
		if(driver.findElement(By.cssSelector("div#mailch-bg")).isDisplayed()) {
			driver.findElement(By.cssSelector("div#close-mailch")).click();
		}
		//Hover mouse to search textbox
				action.moveToElement(driver.findElement(By.cssSelector("section#search-2 input.search-field"))).perform();
				Assert.assertTrue(isPageLoadedSuccess());
		driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys("Selenium");
		driver.findElement(By.cssSelector("section#search-2 span.glass")).click();
		
		Assert.assertTrue(isPageLoadedSuccess());
		List<WebElement> firstAllPostTitle = driver.findElements(By.cssSelector("h3.post-title>a"));
		for (WebElement  postTitle: firstAllPostTitle) {
			Assert.assertTrue(postTitle.getText().contains("Selenium"));
		}
	}
	//  JQuery + JavaScript
	public boolean isPageLoadedSuccess() {
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor= (JavascriptExecutor)driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean)jsExecutor.executeScript("return (window.jQuery!=null) && (jQuery.active === 0);");
				
			}
		};
			ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {

				@Override
				public Boolean apply(WebDriver driver) {
					return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
					
				}
			
			};
			return explicitWait.until(jQueryLoad)&& explicitWait.until(jsLoad);
		 	
	}
		
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	}
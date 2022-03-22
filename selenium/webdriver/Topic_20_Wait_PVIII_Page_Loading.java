package webdriver;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Wait_PVIII_Page_Loading {
	WebDriver driver;
WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver,30);
	
	}

	@Test
	public void TC_01() {
		driver.get("https://api.orangehrm.com/");
		//Wait cho spinner invisible
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner")));
		Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='OrangeHRM REST API Documentation']")), "OrangeHRM REST API Documentation");
		
	}

	@Test
	public void TC_02() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	}

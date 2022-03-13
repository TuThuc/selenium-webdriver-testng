package webdriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Wait_Part_V_Explicit {
	WebDriver driver;
	//Wait rõ ràng: với các điều kiện.status cụ thể
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	By loadingIcon = By.cssSelector("div#loading");
By hellowordText = By.xpath("//h4[text()='Hello World!']");
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();


	}

	//@Test
	public void TC_01_Invisible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait= new WebDriverWait(driver, 30);
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		// Loading biến mất sau 5s 
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));

		Assert.assertEquals(driver.findElement(By.xpath("//h4[text()='Hello World!']")).getText(), "Hello World!");
	}

	//@Test
	public void TC_02_Visible() throws InterruptedException {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait= new WebDriverWait(driver, 30);
		
		
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		// Helle word text hiển thị sau 30s
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(hellowordText));

		Assert.assertEquals(driver.findElement(hellowordText).getText(), "Hello World!");
	}

	//@Test
	public void TC_03_Number() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait= new WebDriverWait(driver, 30); 
		
		driver.findElement(By.xpath("//button[text()='Start']")).click();
	
		explicitWait.until(ExpectedConditions.numberOfElementsToBe(hellowordText,1));


		Assert.assertEquals(driver.findElement(By.xpath("//h4[text()='Hello World!']")).getText(), "Hello World!");
	}
	@Test
	public void TC_04_AjaxLoading() {
		explicitWait= new WebDriverWait(driver, 30); 
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.contentWrapper")));
		WebElement selectedDateText = driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"));
		Assert.assertEquals(selectedDateText.getText(), "No Selected Dates to display.");
		//Wait cho ngay 11 co the click va Click len no
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='11']"))).click();
		// Wait cho icon biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar']>div.raDiv")));
		// Verify ngày được upadate
         selectedDateText = driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"));
// Wait cho ngay duoc selected thanh cong(visible)
         WebElement todayselected = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']/a[text()='11']")));
	
		//Verify ngày được chọn 
	Assert.assertTrue(todayselected.isDisplayed()); 	
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

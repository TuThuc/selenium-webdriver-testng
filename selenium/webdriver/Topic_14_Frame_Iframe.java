package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Frame_Iframe {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	
	}

	//@Test
	public void TC_01_Iframe() {
		
	driver.get("https://kyna.vn/");	
	
	// Switch vào iframe facebook
	driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='face-content']/iframe")));
	
	String kynaFacebookLikes = driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText();
	Assert.assertEquals(kynaFacebookLikes, "167K likes");
	//Switch về trang parent
	driver.switchTo().defaultContent();
	//Switch vao iframe feedback
	driver.switchTo().frame("cs_chat_iframe");
	driver.findElement(By.cssSelector("div.border_overlay")).click();
	sleepInSecond(2);
	driver.findElement(By.cssSelector("input.input_name")).sendKeys("Test");
	driver.findElement(By.cssSelector("input.input_phone")).sendKeys("09873132153");
	new Select(driver.findElement(By.id("serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
	sleepInSecond(3);
	//Swich ve trang parent
	driver.switchTo().defaultContent();
	String keyword = "Excel";
	driver.findElement(By.id("live-search-bar")).sendKeys(keyword);
	driver.findElement(By.cssSelector("button.search-button")).click();
	sleepInSecond(3);
	List<WebElement> courseNames = driver.findElements(By.cssSelector("div.content>h4"));
    for (WebElement course : courseNames) {
		System.out.println(course.getText().toLowerCase());
		Assert.assertTrue(course.getText().toLowerCase().contains(keyword.toLowerCase()));
	}

	}

	@Test
	public void TC_02_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		// Switch to frame
		driver.switchTo().frame("login_page");
		driver.findElement(By.name("fldLoginUserId")).sendKeys("Automationtest");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(3);
		//Verify pass textbox is display
		Assert.assertTrue(driver.findElement(By.name("fldPassword")).isDisplayed());
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

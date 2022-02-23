package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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

	@Test
	public void TC_01_Iframe() {
	driver.get("https://kyna.vn/");	
	
	// Switch vào iframe facebook
	driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='face-content']/iframe")));
	
	String kynaFacebookLikes = driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText();
	Assert.assertEquals(kynaFacebookLikes, "167K likes");
	//Switch về trang parent
	driver.switchTo().defaultContent();
	//Switch vao iframe feedback
	driver.switchTo().frame("_hjRemoteVarsFrame");
	driver.findElement(By.xpath("//div[@class='_hj_feedback_container']//div[text()='Phản hồi']")).click();
	sleepInSecond(2);
	driver.findElement(By.xpath("//div[text()='Rất thích']")).click();
	//Swich ve trang parent
	driver.switchTo().defaultContent();
	driver.findElement(By.id("live-search-bar")).sendKeys("Excel");
	driver.findElement(By.cssSelector("button.search-button")).click();


	}

	@Test
	public void TC_02_Frame() {
		
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

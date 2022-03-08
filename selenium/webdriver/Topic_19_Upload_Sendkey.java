   package webdriver;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Upload_Sendkey {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	 String separatorChar = File.separator;
	 String uploadLocation =  projectPath + separatorChar+"uploadFiles"+ separatorChar;
// Image name :verify
	String test1Image= "test1.png";
    String test2Image= "test2.png";
   //image location: sendkey
    String test1Location = uploadLocation+ test1Image;
    String test2Location = uploadLocation + test2Image;
    
    
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	
	}

	//@Test
	public void TC_01_One_File_Per_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		By uploadFileBy = By.xpath("//input[@type='file']");
		//Load File
		driver.findElement(uploadFileBy).sendKeys(test1Location);
		sleepInSecond(1);
	driver.findElement(uploadFileBy).sendKeys(test2Location);
	sleepInSecond(1);
	//Upload
		List<WebElement> startButtons = driver.findElements(By.xpath("//span[text()='Start']"));
		for (WebElement start : startButtons) {
			start.click();
			sleepInSecond(1);
		}
		//Verify
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+ test1Image +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+ test2Image +"']")).isDisplayed());
	}

	@Test
	public void TC_02_Multiple_File_Per_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		By uploadFileBy = By.xpath("//input[@type='file']");
		//Load File
		driver.findElement(uploadFileBy).sendKeys(test1Location+ "\n"+ test2Location);
		sleepInSecond(1);
	
	//Upload
		List<WebElement> startButtons = driver.findElements(By.xpath("//span[text()='Start']"));
		for (WebElement start : startButtons) {
			start.click();
			sleepInSecond(1);
		}
		//Verify
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+ test1Image +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+ test2Image +"']")).isDisplayed());
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

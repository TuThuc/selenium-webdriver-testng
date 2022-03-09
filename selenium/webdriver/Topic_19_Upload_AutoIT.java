   package webdriver;

import java.io.File;
import java.io.IOException;
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

public class Topic_19_Upload_AutoIT {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	 String separatorChar = File.separator;
	 String uploadLocation =  projectPath + separatorChar+"uploadFiles"+ separatorChar;
	 String autoITFolderLocation =  projectPath + separatorChar+"autoIT"+ separatorChar;
// Image name :verify
	String test1Image= "test1.png";
    String test2Image= "test2.png";
   //image location: 
    String test1Location = uploadLocation+ test1Image;
    String test2Location = uploadLocation + test2Image;
   //autoIT script locator
    String singleFirefox = autoITFolderLocation + "firefoxUploadOneTime.exe";
    String singleChrome = autoITFolderLocation + "chromeUploadOneTime.exe";
    String multipleChrome = autoITFolderLocation + "chromeUploadMultiple.exe";
    String multipleFirefox = autoITFolderLocation + "firefoxUploadMultiple.exe";
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	
	}

	//@Test
	public void TC_01_One_File_Per_Time() throws IOException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		//click để bật lên open file dialog
		driver.findElement(By.cssSelector("span.fileinput-button")).click();
		
		//Load File
		Runtime.getRuntime().exec(new String[] {singleFirefox,test1Image});
		
		sleepInSecond(2);
Runtime.getRuntime().exec(new String[] {singleFirefox,test2Image});
		
	sleepInSecond(5);
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
	public void TC_02_Multiple_File_Per_Time() throws IOException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		//click để bật lên open file dialog
				driver.findElement(By.cssSelector("span.fileinput-button")).click();
				
				//Load File
				Runtime.getRuntime().exec(new String[] {multipleFirefox,test1Location,test2Location});
				
				sleepInSecond(8);
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

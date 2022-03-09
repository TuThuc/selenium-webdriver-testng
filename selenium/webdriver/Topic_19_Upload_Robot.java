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
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.AWTException;
import java.awt.Robot;

public class Topic_19_Upload_Robot {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String separatorChar = File.separator;
	String uploadLocation = projectPath + separatorChar + "uploadFiles" + separatorChar;
	String autoITFolderLocation = projectPath + separatorChar + "autoIT" + separatorChar;
// Image name :verify
	String test1Image = "test1.png";
	String test2Image = "test2.png";
	// image location:
	String test1Location = uploadLocation + test1Image;
	String test2Location = uploadLocation + test2Image;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_One_File_Per_Time() throws IOException, AWTException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		// Copy duong dan file vao clipboard
		StringSelection select = new StringSelection(test1Location);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
		// click để bật lên open file dialog
		driver.findElement(By.cssSelector("span.fileinput-button")).click();
		loadFileByJavaRobot();
		// Copy duong dan file vao clipboard
		select = new StringSelection(test2Location);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
		// click để bật lên open file dialog
		driver.findElement(By.cssSelector("span.fileinput-button")).click();
		loadFileByJavaRobot();

		// Upload
		List<WebElement> startButtons = driver.findElements(By.xpath("//span[text()='Start']"));
		for (WebElement start : startButtons) {
			start.click();
			sleepInSecond(1);
		}
		// Verify
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']/a[@title='" + test1Image + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']/a[@title='" + test2Image + "']")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void loadFileByJavaRobot() {
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Nhan phim Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		// nhan xuong Ctrl-V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		// Nha phim Ctr-V
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		sleepInSecond(1);
		// Nhan Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		sleepInSecond(1);
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
}

package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Window_Tab {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_Only_Two_Window_Or_Tab() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//Lấy ra ID của 1 tab/window mà driver đang đứng(active)
		String parentTabID = driver.getWindowHandle();
		System.out.println("Parent tab ID= " +parentTabID);
		//click vào google link
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(2);
		
		//Switch qua Google tab thành công
		switchToTabByID(parentTabID);
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
		
		
		String googleTabID = driver.getWindowHandle();
		//Switch về parent tab
		switchToTabByID(googleTabID);
		sleepInSecond(3);
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
	   //Click vào facebook link
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
	}

	@Test
	public void TC_02_Greater_Than_One_Window_Or_Tab() {
		
	}
	// Chỉ dùng cho trường hợp có 2 tab/windows
public void switchToTabByID(String expectedID) {
	// Lấy tất cả ID của các tab/window đang có
	Set<String> allTabIDs = driver.getWindowHandles();
	// Dùng vòng lặp để duyêt qua từng ID một
	for (String id : allTabIDs) {//id là biến tạm để so sánh
		//ID nào khác với expectedID truyền vào thì switch qua ID đó
		if(!id.equals(expectedID)) {
			driver.switchTo().window(id);
			break;
		}
	}
}
// DÙng cho trường hợp có: 2 hoặc nhiều hơn 2 tab/windows
	public void switchToTabByTitle(String expectedTitle) {
		// Lấy tất cả ID của các tab/window đang có
		Set<String> allTabIDs = driver.getWindowHandles();
		// Dùng vòng lặp để duyêt qua từng ID một
		for (String id : allTabIDs) {//id là biến tạm để so sánh
			//Switch  vào từng tab trước rồi kiểm tra sau
			driver.switchTo().window(id);
			//Lấy ra title của tab vừa switch vào
			String actualTitle = driver.getTitle();
			//Kiểm tra nếu title bằng với title mong muốn
			if(actualTitle.equals(expectedTitle)) {
				break;
			}
		}
	}
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

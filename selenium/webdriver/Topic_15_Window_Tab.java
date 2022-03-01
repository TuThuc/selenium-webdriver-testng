package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Window_Tab {
	WebDriver driver;
	Alert alert;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	//@Test
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

	//@Test
	public void TC_02_Greater_Than_One_Window_Or_Tab() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//click vào google link
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(2);
		
		//Switch qua Google tab thành công
		switchToTabByTitle("Google");
		
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
		//Switch về parent tab
		switchToTabByTitle("SELENIUM WEBDRIVER FORM DEMO");
		sleepInSecond(3);
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
	   //Click vào facebook link
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(3);
		//Switch qua tab facebook
		
		switchToTabByTitle("Facebook – log in or sign up");
		sleepInSecond(3);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/");
		//Switch về parent tab
		switchToTabByTitle("SELENIUM WEBDRIVER FORM DEMO");
		sleepInSecond(3);
			   //Click vào Lazada link
				driver.findElement(By.xpath("//a[text()='LAZADA']")).click();
				sleepInSecond(3);
				//Switch qua tab lazada
				
				switchToTabByTitle("Shopping online - Buy online on Lazada.vn");
				sleepInSecond(3);
				Assert.assertEquals(driver.getCurrentUrl(), "https://www.lazada.vn/");
				


	}
	//@Test
	public void TC_03_Greater_Than_One_Window_Or_Tab() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		// Click vào add to compare của sản phẩm samsung
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(), "The product Samsung Galaxy has been added to comparison list.");
		// Click vào add to compare của sản phẩm Sony
				driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
				
				Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(), "The product Sony Xperia has been added to comparison list.");
			// click vào compare
			driver.findElement(By.xpath("//button[@title='Compare']")).click();
			// Switch vào compare window
			switchToTabByTitle("Products Comparison List - Magento Commerce");
			sleepInSecond(2);
			Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='Compare Products']")).getText(), "COMPARE PRODUCTS");
			driver.findElement(By.xpath("//button[@title='Close Window']")).click();
			sleepInSecond(2);
			switchToTabByTitle("Mobile");
			driver.findElement(By.xpath("//a[text()='Clear All']")).click();
	sleepInSecond(3);
			//alert = explicitWait.until(ExpectedConditions.alertIsPresent());
	alert = driver.switchTo().alert();
			
			alert.accept();
			sleepInSecond(3);
			Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(), "The comparison list was cleared.");
			
	}
	@Test
	public void TC_04_Greater_Than_One_Window_Or_Tab() {
		driver.get("https://dictionary.cambridge.org/vi/");
		driver.findElement(By.xpath("//span[text()='Đăng nhập']")).click();
		sleepInSecond(5);
		switchToTabByTitle("Login");
		
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='username']/following-sibling::span[text()='This field is required']")).getText(), "This field is required");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='password']/following-sibling::span[text()='This field is required']")).getText(), "This field is required");
		
		driver.findElement(By.xpath("//input[@name='username'and@placeholder='Email *']")).sendKeys("automationfc.com@gmail.com");
		driver.findElement(By.xpath("//input[@name='password'and@placeholder='Password *']")).sendKeys("Automation000***");
		sleepInSecond(2);
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		
		switchToTabByTitle("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Automation FC']")).getText(), "Automation FC");
		

		
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

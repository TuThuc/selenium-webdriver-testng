package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Exer08_Default_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	Actions action;
	Select select;
//Java 
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//khởi tạo sau khi driver này được sinh ra
		//JavascriptExecutor/WebDriverWait/Actions/..
		jsExecutor= (JavascriptExecutor) driver;
		explicitWait= new  WebDriverWait(driver, 30);
		action = new Actions(driver);
			
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	
	}

	//@Test
	public void TC_01_Rode() {
		driver.get("https://www.rode.com/wheretobuy");
	// Khởi tạo khi sử dụng(element xuất hiện)
	//Khởi tạo select để thao tác với element(country dropdown)
		select = new Select(driver.findElement(By.xpath("//select[@id='where_country']")));
		// Không support multiple select
		Assert.assertFalse(select.isMultiple());
		//select giá trị Vietnam
		select.selectByVisibleText("Vietnam");
		sleepInSecond(5);
		//Verify Vietnam selected success
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");
		driver.findElement(By.id("search_loc_submit")).click();
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result_count>span")).getText(), "32");
		List<WebElement> storename = driver.findElements(By.cssSelector("div#search_results div.store_name"));
		//Verify tổng số lượng storename = 32
		Assert.assertEquals(storename.size(), 32);
		for (WebElement store : storename) {
			System.out.println(store.getText());
		}
		
		
	}

	@Test
	public void TC_02_NopCommerce() {
		String firstName= "John";
		String lastName= "Wick";
		String date= "10";
		String month= "January";
		String year= "1975";
		String emailAddress= "keane" + getRandomNumber() + "@email.com";
		String password= "123456";
		
		driver.get("https://demo.nopcommerce.com");
		driver.findElement(By.cssSelector("a.ico-register")).click();
		driver.findElement(By.id("gender-male")).click();
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		//Date
		select= new Select(driver.findElement(By.name("DateOfBirthDay")));
		select.selectByVisibleText(date);
		//Month
		select= new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText(month);
		select= new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText(year);
		
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		driver.findElement(By.id("register-button")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
		driver.findElement(By.cssSelector("a.ico-account")).click();
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		
		select= new Select(driver.findElement(By.name("DateOfBirthDay")));
	    Assert.assertEquals(select.getFirstSelectedOption().getText(), date);
	    
	    select= new Select(driver.findElement(By.name("DateOfBirthMonth")));
	    Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
	    
	    select= new Select(driver.findElement(By.name("DateOfBirthYear")));
	    Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
	    Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), emailAddress);
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
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
		
	}
	}

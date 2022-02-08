package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Exer_Topic_04_05_Register {
	//Khai báo biến đại diện cho Selenium Webdriver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	By fullnameTextboxBy = By.id("txtFirstname");
	By emailTextboxBy = By.cssSelector("input#txtEmail");
	By confirmEmailTextboxBy = By.id("txtCEmail");
	By passwordTexboxBy = By.id("txtPassword");
	By confirmPassTexboxBy = By.id("txtCPassword");
	By phoneTextboxBy = By.id("txtPhone");
	By buttonDangKy = By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']");
    By fullnameErrorMessage = By.id("txtFirstname-error");
    By emailErrorMessage = By.id("txtEmail-error");
    By confirmemailErrorMessage = By.id("txtCEmail-error");
    By passErrorMessage = By.id("txtPassword-error");
    
    By confirmPassErrorMessage = By.id("txtCPassword-error");
    By phoneErrorMessage = By.id("txtPhone-error");
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		//Khởi tạo trình duyệt
		driver = new FirefoxDriver();
		//Set thời gian chờ để tìm được element
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	@BeforeMethod
	public void beforeMethod() {
	    driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		//nhập trống các trường
		driver.findElement(fullnameTextboxBy).sendKeys("");
		driver.findElement(emailTextboxBy).sendKeys("");
		driver.findElement(confirmEmailTextboxBy).sendKeys("");
		driver.findElement(passwordTexboxBy).sendKeys("");
		driver.findElement(confirmPassTexboxBy).sendKeys("");
		driver.findElement(phoneTextboxBy).sendKeys("");
		driver.findElement(buttonDangKy).click();
		//so sánh errormessage
		Assert.assertEquals(driver.findElement(fullnameErrorMessage).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(emailErrorMessage).getText(), "Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(confirmemailErrorMessage).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(passErrorMessage).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(confirmPassErrorMessage).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(phoneErrorMessage).getText(), "Vui lòng nhập số điện thoại.");
				
	}

	@Test
	public void TC_02_Input_Email_Invalid() {
		//Nhập thông tin
		driver.findElement(fullnameTextboxBy).sendKeys("Nguyen van A");
		driver.findElement(emailTextboxBy).sendKeys("abc@mail$");
		driver.findElement(confirmEmailTextboxBy).sendKeys("abc@amail");
		driver.findElement(passwordTexboxBy).sendKeys("anhthuc");
		driver.findElement(confirmPassTexboxBy).sendKeys("anhthuc");
		driver.findElement(phoneTextboxBy).sendKeys("0987403392");
		// Click button đăng ký
		driver.findElement(buttonDangKy).click();
		//kiểm tra mess error
		Assert.assertEquals(driver.findElement(emailErrorMessage).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(confirmemailErrorMessage).getText(), "Email nhập lại không đúng");
	}
	@Test
	public void TC_03_Input_ConfirmEmail_Invalid() {
		//Testcase 03 check error confirm email
		//Nhập thông tin
		driver.findElement(fullnameTextboxBy).sendKeys("Nguyen van B");
		driver.findElement(emailTextboxBy).sendKeys("tuthuc176216@gmail");
		driver.findElement(confirmEmailTextboxBy).sendKeys("thuctv@miraway.vn");
		driver.findElement(passwordTexboxBy).sendKeys("anhthuc");
		driver.findElement(confirmPassTexboxBy).sendKeys("anhthuc");
		driver.findElement(phoneTextboxBy).sendKeys("0987403392");
		// Click button đăng ký
		driver.findElement(buttonDangKy).click();
		//kiểm tra mess error	
		Assert.assertEquals(driver.findElement(confirmemailErrorMessage).getText(), "Email nhập lại không đúng");
	}
	@Test
	public void TC_04_Input_Password_Invalid() {
		//Testcase 04 check error hien thi nhap sai pass
		//Nhập thông tin
		driver.findElement(fullnameTextboxBy).sendKeys("Nguyen van C");
		driver.findElement(emailTextboxBy).sendKeys("abc@email.com");
		driver.findElement(confirmEmailTextboxBy).sendKeys("abc@email.com");
		driver.findElement(passwordTexboxBy).sendKeys("1234");
		driver.findElement(confirmPassTexboxBy).sendKeys("1234");
		driver.findElement(phoneTextboxBy).sendKeys("0987403392");
		// Click button đăng ký
		driver.findElement(buttonDangKy).click();
		//kiểm tra mess error
		Assert.assertEquals(driver.findElement(passErrorMessage).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
	
		Assert.assertEquals(driver.findElement(confirmPassErrorMessage).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
	}
	@Test
	public void TC_05_Input_ConfirmPassWord_Invalid() {
		//Testcase 05 check error hien thi nhap sai confirmpass
		//Nhập thông tin
		driver.findElement(fullnameTextboxBy).sendKeys("Nguyen van D");
		driver.findElement(emailTextboxBy).sendKeys("abc@email.com");
		driver.findElement(confirmEmailTextboxBy).sendKeys("abc@email.com");
		driver.findElement(passwordTexboxBy).sendKeys("anhthuc");
		driver.findElement(confirmPassTexboxBy).sendKeys("anhthuc1");
		driver.findElement(phoneTextboxBy).sendKeys("0987403392");
		// Click button đăng ký
		driver.findElement(buttonDangKy).click();
		//kiểm tra mess error
		
		Assert.assertEquals(driver.findElement(confirmPassErrorMessage).getText(), "Mật khẩu bạn nhập không khớp");
	}
	@Test
	public void TC_06_Innput_Phone_Invalid() {
		//Testcase 06 check error hien thi nhap phone không hợp lệ
		driver.findElement(fullnameTextboxBy).sendKeys("Nguyen van E");
		driver.findElement(emailTextboxBy).sendKeys("abc@email.com");
		driver.findElement(confirmEmailTextboxBy).sendKeys("abc@email.com");
		driver.findElement(passwordTexboxBy).sendKeys("anhthuc");
		driver.findElement(confirmPassTexboxBy).sendKeys("anhthuc");
		driver.findElement(phoneTextboxBy).sendKeys("098740339");
		// Click button đăng ký
		driver.findElement(buttonDangKy).click();
		//kiểm tra mess error
	
		Assert.assertEquals(driver.findElement(phoneErrorMessage).getText(), "Số điện thoại phải từ 10-11 số.");
	}
	@Test
	public void TC_07_Input_Phone_Fail() {
		//Testcase 07 check error hien thi nhap phone khong dung
		//Nhập thông tin
		driver.findElement(fullnameTextboxBy).sendKeys("Nguyen van D");
		driver.findElement(emailTextboxBy).sendKeys("abc@email.com");
		driver.findElement(confirmEmailTextboxBy).sendKeys("abc@email.com");
		driver.findElement(passwordTexboxBy).sendKeys("anhthuc");
		driver.findElement(confirmPassTexboxBy).sendKeys("anhthuc");
		driver.findElement(phoneTextboxBy).sendKeys("0587403392");
		// Click button đăng ký
		driver.findElement(buttonDangKy).click();
		//kiểm tra mess error
	
		Assert.assertEquals(driver.findElement(phoneErrorMessage).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
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

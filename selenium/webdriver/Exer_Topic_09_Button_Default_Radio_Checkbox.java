package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Exer_Topic_09_Button_Default_Radio_Checkbox {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osname = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;
    
	@BeforeClass
	public void beforeClass() {
		if(osname.startsWith("Windows")) {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else if(osname.startsWith("Mac")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver_mac");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver_linux");

		}
		
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		By loginButtonBy = By.cssSelector("button.fhs-btn-login");
		// verify login button is disabled
		Assert.assertFalse(driver.findElement(loginButtonBy).isEnabled());
	
		driver.findElement(By.cssSelector("input#login_username")).sendKeys("automationfc@gmail.com");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("automationfc");
		sleepInSecond(1);
		//verify  lofin button is enable
		Assert.assertTrue(driver.findElement(loginButtonBy).isEnabled());
		// Verify background color =RED
		String loginButtonBackgroundcolorRgb = driver.findElement(loginButtonBy).getCssValue("background-color");
		System.out.println("RGB color = " + loginButtonBackgroundcolorRgb);
		
		//Verify = RGB
		Assert.assertEquals(loginButtonBackgroundcolorRgb, "rgb(201, 33, 39)");
		
		//Convert qua Hexa
		String loginButtonBackgroundColorHexa = Color.fromString(loginButtonBackgroundcolorRgb).asHex();
		System.out.println("Hexa color = " + loginButtonBackgroundColorHexa);

		Assert.assertEquals(loginButtonBackgroundColorHexa.toUpperCase(), "#C92127");
		driver.navigate().refresh();
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		//Remove Attribute
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", driver.findElement(loginButtonBy));
		
		sleepInSecond(2);
		
		driver.findElement(loginButtonBy).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content'] //label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content'] //label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
		
		
		
		
	}

	//@Test
	public void TC_02_Defaul_Radio() {
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		By oneDotEightPetrolRadio = By.xpath("//label[text()='1.8 Petrol, 118kW']/preceding-sibling::input");
		By twoDotZeroPetrolRadio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
		By threeDotSixPetrolRadio = By.xpath("//label[text()='3.6 Petrol, 191kW']/preceding-sibling::input");
		
		Assert.assertFalse(driver.findElement(oneDotEightPetrolRadio).isSelected());
		driver.findElement(oneDotEightPetrolRadio).click();
		sleepInSecond(2);
	
		Assert.assertTrue(driver.findElement(oneDotEightPetrolRadio).isSelected());
		driver.findElement(twoDotZeroPetrolRadio).click();
		sleepInSecond(2);
				
		//Deselected
		Assert.assertFalse(driver.findElement(oneDotEightPetrolRadio).isSelected());
		//Selected
		Assert.assertTrue(driver.findElement(twoDotZeroPetrolRadio).isSelected());
		// Enabled
		Assert.assertTrue(driver.findElement(oneDotEightPetrolRadio).isEnabled());
		Assert.assertTrue(driver.findElement(twoDotZeroPetrolRadio).isEnabled());
		//Disabled = Read only
		Assert.assertFalse(driver.findElement(threeDotSixPetrolRadio).isEnabled());

		
		
		
	}

	//@Test
	public void TC_03_Defaul_Checkbox() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		By luggageCheckbox = By.xpath("//label[text()='Luggage compartment cover']/preceding-sibling::input");
		By heatedCheckbox = By.xpath("//label[text()='Heated front and rear seats']/preceding-sibling::input");
		By leatherCheckbox = By.xpath("//label[text()='Leather trim']/preceding-sibling::input");
		By towbarCheckbox = By.xpath("//label[text()='Towbar preparation']/preceding-sibling::input");
		// Select
		checkToCheckbox(luggageCheckbox);
		checkToCheckbox(heatedCheckbox);
		//Select
		Assert.assertTrue(isElementSelected(heatedCheckbox));
		Assert.assertTrue(isElementSelected(luggageCheckbox));
		Assert.assertTrue(isElementSelected(leatherCheckbox));
		// Disable
		Assert.assertFalse(isElementEnabled(towbarCheckbox));
		Assert.assertFalse(isElementEnabled(leatherCheckbox));
		//Deselect
		uncheckToCheckbox(leatherCheckbox);
		
			uncheckToCheckbox(heatedCheckbox);
		//DeSelected
			Assert.assertFalse(isElementSelected(luggageCheckbox));
			Assert.assertFalse(isElementSelected(heatedCheckbox));
			Assert.assertFalse(isElementSelected(towbarCheckbox));
		
	}
	//@Test
	public void TC_04_Multiple_Checkbox() {
		driver.get("https://automationfc.github.io/multiple-fields");
		
		List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
		System.out.println("Checkbox size = "+ checkboxes.size());
	 //Action = Selected
		for (WebElement checkbox : checkboxes) {
			if(!checkbox.isSelected())
			{checkbox.click();
			}
		}
   //Verify = Selected
		for (WebElement checkbox : checkboxes) {
			Assert.assertTrue(checkbox.isSelected());
		
		}	
	//Action = Deselected
			for (WebElement checkbox : checkboxes) {
				if(checkbox.isSelected())
				{checkbox.click();
				}
			}
	   //Verify = DeSelected
			for (WebElement checkbox : checkboxes) {
				Assert.assertFalse(checkbox.isSelected());
			
			}	
		}
	//@Test
	public void TC_05_Custom_Radio() {
		driver.get("https://material.angular.io/components/radio/examples");
		By winterRadioBy = By.xpath("//input[@value='Winter']");
		clickByJavascript(winterRadioBy);
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(winterRadioBy).isSelected());
	}
	//@Test
	public void TC_06_Custom_Checkbox() {
		driver.get("https://material.angular.io/components/checkbox/examples");
		By checkedCheckboxBy = By.xpath("//span[text()='Checked']/preceding-sibling::span/input");
		By indeterminateCheckboxBy = By.xpath("//span[text()='Indeterminate']/preceding-sibling::span/input");
		
		clickByJavascript(checkedCheckboxBy);
		clickByJavascript(indeterminateCheckboxBy);
		sleepInSecond(2);
		Assert.assertTrue(isElementSelected(checkedCheckboxBy));
		Assert.assertTrue(isElementSelected(indeterminateCheckboxBy));
		clickByJavascript(checkedCheckboxBy);
		clickByJavascript(indeterminateCheckboxBy);
		sleepInSecond(2);
		Assert.assertFalse(isElementSelected(checkedCheckboxBy));
		Assert.assertFalse(isElementSelected(indeterminateCheckboxBy));

	}
	//@Test
	public void TC_07_Custom_Radio() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		By myselfRadio= By.xpath("//div[text()='Đăng ký bản thân']/preceding-sibling::div/input");
		By myfamilyRadio= By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");
		clickByJavascript(myfamilyRadio);
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='registerFullname']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@formcontrolname='registerPhoneNumber']")).isDisplayed());
		clickByJavascript(myselfRadio);
		sleepInSecond(2);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		Assert.assertEquals(driver.findElements(By.xpath("//input[@formcontrolname='registerFullname']")).size(),0);
		Assert.assertEquals(driver.findElements(By.xpath("//input[@formcontrolname='registerPhoneNumber']")).size(),0);
	}
	@Test
	public void TC_08_Custom_Radio_Google_Doc() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		By haiphongRadioBy = By.xpath("//div[@aria-label='Hải Phòng']");
		By quangnamCheckboxBy = By.xpath("//div[@aria-label='Quảng Nam']");
		By quangbinhCheckboxBy = By.xpath("//div[@aria-label='Quảng Bình']");
		
		Assert.assertEquals(driver.findElement(haiphongRadioBy).getAttribute("aria-checked"), "false");
		Assert.assertEquals(driver.findElement(quangnamCheckboxBy).getAttribute("aria-checked"), "false");
		Assert.assertEquals(driver.findElement(quangbinhCheckboxBy).getAttribute("aria-checked"), "false");

		
		driver.findElement(haiphongRadioBy).click();
		sleepInSecond(2);
		driver.findElement(quangbinhCheckboxBy).click();
		sleepInSecond(2);
		driver.findElement(quangnamCheckboxBy).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(haiphongRadioBy).getAttribute("aria-checked"), "true");
		Assert.assertEquals(driver.findElement(quangnamCheckboxBy).getAttribute("aria-checked"), "true");
		Assert.assertEquals(driver.findElement(quangbinhCheckboxBy).getAttribute("aria-checked"), "true");
	
	}
public void checkToCheckbox(By by) {
		if(!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
public void clickByJavascript(By by) {
	jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
}
	public void uncheckToCheckbox(By by) {
		if(driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	public boolean isElementSelected(By by) {
		if(driver.findElement(by).isSelected()) {
			return true;}
			else {
			return false;
			
		}
	}
		public boolean isElementEnabled(By by) {
			if(driver.findElement(by).isEnabled()) {
				return true;}
				else {
				return false;
				
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

package webdriver;


import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Wait_Part_VI_Mix_Implicit_Explicit {
	WebDriver driver;
	// Wait rõ ràng: với các điều kiện.status cụ thể
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
	}

	// @Test
	public void TC_01_Element_Found() {
		driver.get("https://www.facebook.com/");
		By emailIDBy = By.id("email");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		System.out.println("Start implicit" + getTimeNow());
		driver.findElement(emailIDBy).isDisplayed();
		System.out.println("End implicit" + getTimeNow());
		explicitWait = new WebDriverWait(driver, 15);
		
		System.out.println("Start explicit" + getTimeNow());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailIDBy));

		System.out.println("End explicit" + getTimeNow());
	}


	 //@Test
		public void TC_02_Element_Not_Found_Only_Implicit() {
			driver.get("https://www.facebook.com/");
			By emailIDBy = By.id("emailc");
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			System.out.println("Start implicit" + getTimeNow());
			driver.findElement(emailIDBy).isDisplayed();
			System.out.println("End implicit" + getTimeNow());
		}
			//@Test
			public void TC_03_Element_Not_Found_Only_Explicit() {
				driver.get("https://www.facebook.com/");
				By emailIDBy = By.id("emailc");
				
				explicitWait = new WebDriverWait(driver, 15);
				
				System.out.println("Start explicit" + getTimeNow());
				try {
					explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailIDBy));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					
				}

				System.out.println("End explicit" + getTimeNow());
			}
			@Test
			public void TC_04_Element_Not_Found_Implicit_Explicit() {
				driver.get("https://www.facebook.com/");
				By emailIDBy = By.id("emailc");
				// 1 - Implicit <Explicit
				// 1 - Implicit = Explicit
				// 1 - Implicit >Explicit
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				System.out.println("Start implicit" + getTimeNow());
				try {
					driver.findElement(emailIDBy).isDisplayed();
				} catch (Exception e1) {
				
				}
				System.out.println("End implicit" + getTimeNow());
				explicitWait = new WebDriverWait(driver, 10);
				
				System.out.println("Start explicit" + getTimeNow());
				try {
					explicitWait.until(ExpectedConditions.visibilityOfElementLocated(emailIDBy));
				} catch (Exception e) {
				
				}

				System.out.println("End explicit" + getTimeNow());
			}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public String getTimeNow() {
		Date date = new Date();
		return date.toString();
	}


}

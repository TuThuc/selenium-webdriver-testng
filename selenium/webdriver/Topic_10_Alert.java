package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Alert {
	WebDriver driver;
	Alert alert;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	
	}

	//@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		// Switch qua alert
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Alert");

		alert.accept();
		sleepInSecond(3);
		 
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
	}

	//@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		// Switch qua alert
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
       //Cancel 1 alert
		alert.dismiss();
	
		sleepInSecond(3);	
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");

	}
	//@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String textToSendkey = "Automation FC";
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		// Switch qua alert
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert.sendKeys(textToSendkey);
       //Accept 1 alert
		alert.accept();
	
		sleepInSecond(3);	
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: "+ textToSendkey);

	}
	//@Test
	public void TC_04_Authentication_Alert_I() {
		String username = "admin";
		String password = "admin";
		driver.get("http://" + username +":" +password + "@"+"the-internet.herokuapp.com/basic_auth");
		sleepInSecond(3);
	    Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}
	@Test
	public void TC_04_Authentication_Alert_II() {
		String username = "admin";
		String password = "admin";
		driver.get("http://the-internet.herokuapp.com");
		String basicAuthenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		
		driver.get(getAuthenticateLink(basicAuthenLink, username, password));
		sleepInSecond(3);
	    Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}
	
	public String getAuthenticateLink(String url , String username, String password) {
		String[] links = url.split("//");
		url = links[0] + "//" + username + ":" + password + "@" + links[1];
		return url;
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

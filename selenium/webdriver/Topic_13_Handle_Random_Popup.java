package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Handle_Random_Popup {
	WebDriver driver;
	WebDriverWait expliciWait;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor)driver;
		//Trạng thái của element
		expliciWait = new WebDriverWait(driver, 10);
		//Để tìm Element
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Random_Popup_In_DOM() {
		driver.get("https://www.kmplayer.com/home");
		
		WebElement supportHomePopup = driver.findElement(By.cssSelector("img#support-home"));
		//Nếu popup hiển thị thì close đi hoặc là xxx lên popup đó
		if(supportHomePopup.isDisplayed()) {
			System.out.println("Case 1- Popup hiển thị- thì close đi");
			//close đi
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//area[@id='btn-r']")));	
		sleepInSecond(2);
		
		}else {
		// Expected home poup khong hien thị 
		Assert.assertFalse(supportHomePopup.isDisplayed());
		System.out.println("Nếu popup khong hien thi- thì không làm gì. ");
		}
		// Step tiep dù popup co hien thi hay khong  
		// Clic vào PC 64 bit thì nó bật tiếp lên 1 popup(Layer popup)
		driver.findElement(By.xpath("//div[@id='container']//a[text()='PC 64X']")).click();
		sleepInSecond(2);
		WebElement layerPopup = driver.findElement(By.xpath("//img[@class='layer-popup']"));
		// verify layer popup hiển thị
		Assert.assertTrue(layerPopup.isDisplayed());
		
		//Click để dow file cài về
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("area#down-url")));
		sleepInSecond(5);
	}
	

	@Test
	public void TC_02_Random_Popup_Not_In_DOM() {
		driver.get("https://dehieu.vn/");
		
		List<WebElement> counpondPopup = driver.findElements(By.cssSelector("div.popup-content"));
		System.out.println("Popup size:=" + counpondPopup.size());
		//popup có hien thị thì xuất hiện element trong DOM
		// Popup không hiển thì thì không xuất hiện element trong DOM
		//Nếu popup hiển thị thì size của element >0
		if(counpondPopup.size()>0) {
			System.out.println("Case 1- Popup hiển thị- thì close đi");
			//close đi
			driver.findElement(By.cssSelector("button#close-popup")).click();
		sleepInSecond(2);
		
		}else {
		// Expected home poup khong hien thị 
		System.out.println("Nếu popup khong hien thi- thì không làm gì. ");
		}
		// Step tiep dù popup co hien thi hay khong  
		// Clic vào PC 64 bit thì nó bật tiếp lên 1 popup(Layer popup)
		driver.findElement(By.xpath("//h4[text()='Khóa học thiết kế hệ thống M&E - Tòa nhà']")).click();
		sleepInSecond(2);
	
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

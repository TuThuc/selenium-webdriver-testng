package webdriver;

import java.time.Duration;
import java.util.NoSuchElementException;


import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_20_Wait_PVII_Fluent {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	FluentWait<WebDriver> fluentDriver;
	FluentWait<WebElement> fluentElement;
long pollingtime= 1;
long sumTime = 30;
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01() {
	driver.get("https://automationfc.github.io/dynamic-loading/");
	fluentDriver = new FluentWait<WebDriver>(driver);
	clickToElement(By.cssSelector("div#start>button"));
	isElementDisplayed(By.cssSelector("div#finish>h4"));
	
	
	
	}
		
	@Test
	public void TC_02() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
public WebElement findElement(By locator) {
	FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
	.withTimeout(Duration.ofSeconds(sumTime))
			 .pollingEvery(Duration.ofSeconds(pollingtime))
			 .ignoring(NoSuchElementException.class);
	WebElement element = fluentWait.until(new Function<WebDriver, WebElement>() {
		public WebElement apply(WebDriver driver) {
			return driver.findElement(locator);
		}
	});
	return element;
}
public void clickToElement(By locator) {
	WebElement element= findElement(locator);
	element.click();
}
public WebElement waitWebElementVisible(By locator) {
	FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
			.withTimeout(Duration.ofSeconds(sumTime))
			.pollingEvery(Duration.ofSeconds(pollingtime))
			.ignoring(NoSuchElementException.class);
	return fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
}

public boolean isElementDisplayed (By locator) {
	WebElement element = waitWebElementVisible(locator);
	FluentWait<WebElement> wait = new FluentWait<WebElement>(element)
			.withTimeout(Duration.ofSeconds(sumTime))
			.pollingEvery(Duration.ofSeconds(pollingtime))
			.ignoring(NoSuchElementException.class);
	boolean isDisplayed = wait.until(new Function<WebElement, Boolean>() {
		public Boolean apply(WebElement element) {
			boolean flag = element.isDisplayed();
			return flag;
		}
	});
	return isDisplayed;
}


	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
		}
		

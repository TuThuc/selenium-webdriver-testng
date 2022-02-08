package webdriver;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Textbox_Textarea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		//driver = new FirefoxDriver();
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	
	}

	@Test
	public void TC_01_Add_Employee(){
		driver.get("https://opensource-demo.orangehrmlive.com/");
		//Textbox
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");	
		//Button
		driver.findElement(By.id("btnLogin")).click();
		// Open  add
	
		
	}

	@Test
	public void TC_02() {
		
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

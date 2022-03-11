package webdriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Wait_Part_II_FindElement_FindElements {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.facebook.com/");
	}

//	@Test
	public void TC_01_Find_Element() {
		// - Có duy nhất 1 element
		// Nếu element xuất hiện ngay=> trả về element đó không cần chờ hết timeout(10s)
		// Nếu element không xuất hiện -> sau 0.5s sẽ tìm lại cho đến khi nào hết time
		// thì thôi
		// Trả về duy nhất 1 element
//		System.out.println("Date start:" + getCurrentTime());
//		driver.findElement(By.xpath("//input[@name='firstname']"));
//		System.out.println("Date end:" + getCurrentTime());
		// - Không có element
		// Nó sẽ tìm đi tìm lại cho đến khi hết timeout. sau khi hết timeout thì đánh
		// fail cả testcase này
		// K chạy step tiếp theo
		// Throw/Log ra 1 exception(ngoại lệ): NosuchElement: không tìm thấy element
//		System.out.println("Date start:" + getCurrentTime());
//		driver.findElement(By.xpath("//input[@name='firsname']"));
//		System.out.println("Date end:" + getCurrentTime());
//		// - Có nhiều hơn 1 element
		//Lấy ra element đầu tiên
		driver.findElement(By.xpath("//div[@id='pageFooterChildren']//a[text()]")).click();
	}

	@Test
	public void TC_02_Find_Elements() {
		int elementNumber = 0;
		// - Có duy nhất 1 element
		// - Có nhiều hơn 1 element trả về element đó không cần chờ hết timeout(10s)
		// Nếu element không xuất hiện -> sau 0.5s sẽ tìm lại cho đến khi nào hết time
		elementNumber= driver.findElements(By.xpath("//input[@id='email']")).size();
		System.out.println("1 element:" + elementNumber);
		//Nhiều element
		elementNumber= driver.findElements(By.xpath("//div[@id='pageFooterChildren']//a[text()]")).size();
		System.out.println("1 element:" + elementNumber);
		// - Không có element
		//Nó sẽ tìm đi tìm lại cho đến khi hết timeout. sau khi hết timeout thì đánh
		// Sau khi hết timeout thì không đánh fail testcase
		//Vẫn chạy step tiếp
		System.out.println("Date start:" + getCurrentTime());
		elementNumber= driver.findElements(By.xpath("//input[@name='firsname']")).size();
		System.out.println("0 element:" + elementNumber) ;
	System.out.println("Date end:" + getCurrentTime());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private String getCurrentTime() {
		Date date = new Date();
		return date.toString();

	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
}

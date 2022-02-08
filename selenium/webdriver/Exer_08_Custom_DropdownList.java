package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Exer_08_Custom_DropdownList {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//Wait cho các trạng thái của element : visible/ presence/ invisible/ staleness
		explicitWait = new WebDriverWait(driver, 15);
		//Ép kiểu tường minh
		jsExecutor= (JavascriptExecutor) driver;
		//Wait cho việc tìm element ( findElement)
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	
	}

	//@Test
	public void TC_01_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		selectItemInCustomDropdownList("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "5");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(), "5");
		selectItemInCustomDropdownList("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "15");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(), "15");
		selectItemInCustomDropdownList("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "19");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(), "19");
		selectItemInCustomDropdownList("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "3");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(), "3");
		
	}

	//@Test
	public void TC_02_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection");
		selectItemInCustomDropdownList("i.dropdown", "div.item>span.text", "Jenny Hess");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui")).getText(), "Jenny Hess");
		selectItemInCustomDropdownList("i.dropdown", "div.item>span.text", "Christian");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui")).getText(), "Christian");
		selectItemInCustomDropdownList("i.dropdown", "span.text", "Justen Kitsune");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui")).getText(), "Justen Kitsune");
	}
	//@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemInCustomDropdownList("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
		selectItemInCustomDropdownList("li.dropdown-toggle", "ul.dropdown-menu a", "First Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");
		selectItemInCustomDropdownList("li.dropdown-toggle", "ul.dropdown-menu a", "Third Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");
	}
	//@Test
	public void TC_04_Angular_Select() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		selectItemInCustomDropdownList("ng-select[bindvalue='provinceCode'] span.ng-arrow-wrapper", "div[role='option']>span.ng-option-label", "Thành phố Hồ Chí Minh");
		//1 dùng javascript
		String actualText = (String) jsExecutor.executeScript("return document.querySelector(\"ng-select[bindvalue='provinceCode'] span.ng-value-label\").innerText");
		Assert.assertEquals(actualText, "Thành phố Hồ Chí Minh");
		//2 dùng gettext
		Assert.assertEquals(driver.findElement(By.cssSelector("ng-select[bindvalue='provinceCode'] span.ng-value-label")).getText(), "Thành phố Hồ Chí Minh");
		//3
		Assert.assertEquals(driver.findElement(By.cssSelector("ng-select[bindvalue='provinceCode'] span.ng-value-label")).getAttribute("textContent"), "Thành phố Hồ Chí Minh");
		jsExecutor.executeScript("window.scrollBy(0,-200)");

		sleepInSecond(2);
		selectItemInCustomDropdownList("ng-select[bindvalue='districtCode'] span.ng-arrow-wrapper", "div[role='option']>span.ng-option-label", "Quận Bình Tân");
		Assert.assertEquals(driver.findElement(By.cssSelector("ng-select[bindvalue='districtCode'] span.ng-value-label")).getText(), "Quận Bình Tân");
		
		//scrollToElement(By.xpath("//ng-select[@bindvalue='wardCode']/ancestor::div[@class='col-lg-6 col-md-12']"));
		jsExecutor.executeScript("window.scrollBy(0,-200)");
		sleepInSecond(2);

		selectItemInCustomDropdownList("ng-select[bindvalue='wardCode'] span.ng-arrow-wrapper", "div[role='option']>span.ng-option-label", "Phường Bình Trị Đông A");
		Assert.assertEquals(driver.findElement(By.cssSelector("ng-select[bindvalue='wardCode'] span.ng-value-label")).getText(), "Phường Bình Trị Đông A");
	}

	//@Test
	public void TC_05_Angular_Enter() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		enterToCustomDropdownList("ng-select[bindvalue='provinceCode'] input[role='combobox']", "div[role='option']>span.ng-option-label", "Thành phố Hồ Chí Minh");
		//1 dùng javascript
		String actualText = (String) jsExecutor.executeScript("return document.querySelector(\"ng-select[bindvalue='provinceCode'] span.ng-value-label\").innerText");
		Assert.assertEquals(actualText, "Thành phố Hồ Chí Minh");
		//2 dùng gettext
		Assert.assertEquals(driver.findElement(By.cssSelector("ng-select[bindvalue='provinceCode'] span.ng-value-label")).getText(), "Thành phố Hồ Chí Minh");
		//3
		Assert.assertEquals(driver.findElement(By.cssSelector("ng-select[bindvalue='provinceCode'] span.ng-value-label")).getAttribute("textContent"), "Thành phố Hồ Chí Minh");
		
		enterToCustomDropdownList("ng-select[bindvalue='districtCode'] input[role='combobox']", "div[role='option']>span.ng-option-label", "Quận Bình Tân");
		Assert.assertEquals(driver.findElement(By.cssSelector("ng-select[bindvalue='districtCode'] span.ng-value-label")).getText(), "Quận Bình Tân");
		
		enterToCustomDropdownList("ng-select[bindvalue='wardCode'] input[role='combobox']", "div[role='option']>span.ng-option-label", "Phường Bình Trị Đông A");
		Assert.assertEquals(driver.findElement(By.cssSelector("ng-select[bindvalue='wardCode'] span.ng-value-label")).getText(), "Phường Bình Trị Đông A");
	}
	@Test
	public void TC_06_React_Semantic_Enter() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		enterToCustomDropdownList("div[role='combobox'] input.search", "div[role='listbox'] span.text", "Afghanistan");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui div.divider")).getAttribute("innerText"), "Afghanistan");
		
		enterToCustomDropdownList("div[role='combobox'] input.search", "div[role='listbox'] span.text", "Armenia");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui div.divider")).getAttribute("textContent"), "Armenia");
		
		
		enterToCustomDropdownList("div[role='combobox'] input.search", "div[role='listbox'] span.text", "Belgium"); 
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui div.divider")).getAttribute("textContent"), "Belgium");

	
	}
public void selectItemInCustomDropdownList(String parentLocator, String childLocator, String expectedTextItem) {
	// Step 1: Click vao element cho nó xổ hết ra
			driver.findElement(By.cssSelector(parentLocator)).click();
			sleepInSecond(2);
			//Step 2: Chờ cho các item load hết ra thành công
			//Lưu ý 1: Locator chứa hết tất cả các item
			//Lưu ý 2: Locator phải đến nốt cuối cùng chứa text
			//Wait presence 19 cái
			explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
			// Step 3: Tìm item cần chọn
			// + B1 Nếu item cần chọn trong vùng nhìn thấy không cần scroll tìm tiếp
			// + B2 Nếu item nằm ở dưới thì scroll xuống dưới item đó
			
			//Lấy tất các element(item) ra sau đó duyệt qua từng item
			List<WebElement> allitems = driver.findElements(By.cssSelector(childLocator ));
			//Duyệt qua từng item getText của item ra
			for (WebElement item : allitems) {
				String actualText =item.getText();
				System.out.println("Actual Text =" + actualText);
				//Nếu text = text mình mong muốn(item cần click vào)
				if (actualText.equals(expectedTextItem)) {
					// + B1 Nếu item cần chọn trong vùng nhìn thấy không cần scroll tìm tiếp
					// + B2 Nếu item nằm ở dưới thì scroll tới item đó
				jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
			 // Step 4: Click vào item đó	
					item.click();
					sleepInSecond(2);
			//Thoát khỏi vòng lặp không có kiểm tra tiếp theo nữa
					break;
				}
			}
}
public void enterToCustomDropdownList(String parentLocator, String childLocator, String expectedTextItem) {
	// Step 1: phải lấy đến thẻ input(textbox) để sendkey vào
			driver.findElement(By.cssSelector(parentLocator)).sendKeys(expectedTextItem);
			sleepInSecond(2);
			//Step 2: Chờ cho các item load hết ra thành công
			//Lưu ý 1: Locator chứa hết tất cả các item
			//Lưu ý 2: Locator phải đến nốt cuối cùng chưa text
		
			explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
			// Step 3: Tìm item cần chọn
			// + B1 Nếu item cần chọn trong vùng nhìn thấy không cần scroll tìm tiếp
			// + B2 Nếu item nằm ở dưới thì scroll xuống dưới item đó
			
			//Lấy tất các element(item) ra sau đó duyệt qua từng item
			List<WebElement> allitems = driver.findElements(By.cssSelector(childLocator ));
			//Duyệt qua từng item getText của item ra
			for (WebElement item : allitems) {
				String actualText =item.getText();
				System.out.println("Actual Text =" + actualText);
				//Nếu text = text mình mong muốn(item cần click vào)
				if (actualText.equals(expectedTextItem)) {
					// + B1 Nếu item cần chọn trong vùng nhìn thấy không cần scroll tìm tiếp
					// + B2 Nếu item nằm ở dưới thì scroll tới item đó
				jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
			 // Step 4: Click vào item đó	
					item.click();
					sleepInSecond(2);
			//Thoát khỏi vòng lặp không có kiểm tra tiếp theo nữa
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

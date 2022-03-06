package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_JavascriptExecutor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String loginPageUrl, userID,userPassword;
	String customerName, gender, dateofBirth, addressInput, addressOutput, city, state,pin, phone, email, password,customerID; 
	By customerNameTextboxBy = By.name("name");
	By dateOfBirthTextboxBy = By.name("dob");
	By addressTextboxBy = By.name("addr");
	By cityTextboxBy = By.name("city");
	By stateTextboxBy = By.name("state");
	By pinTextboxBy = By.name("pinno");
	By  phoneTextboxBy = By.name("telephoneno");
	By emailTextboxBy = By.name("emailid");
	By passwordTextboxBy = By.name("password");
	By buttonSubmitBy = By.name("sub");
	By customerIdBy = By.xpath("//td[text()='Customer ID']/following-sibling::td");
	By customerNameBy = By.xpath("//td[text()='Customer Name']/following-sibling::td");
	By genderBy = By.xpath("//td[text()='Gender']/following-sibling::td");
	By birthdayBy = By.xpath("//td[text()='Birthdate']/following-sibling::td");
	By addressBy = By.xpath("//td[text()='Address']/following-sibling::td");
	By cityBy = By.xpath("//td[text()='City']/following-sibling::td");
	By stateBy = By.xpath("//td[text()='State']/following-sibling::td");
	By pinBy = By.xpath("//td[text()='Pin']/following-sibling::td");
	By mobileNoBy = By.xpath("//td[text()='Mobile No.']/following-sibling::td");
	By emailBy = By.xpath("//td[text()='Email']/following-sibling::td");
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//khởi tạo  != null
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		loginPageUrl = "http://demo.guru99.com/v4";
		customerName="Gerrard";
		gender="male"; 
		dateofBirth="1990-01-23"; 
		addressInput="Mercyside\nEngland";
		addressOutput="Mercyside England";
		city="Liverpool";
		state="London";
		pin="565459";
		phone="0989573949"; 
		email="gerrard"+ getRandomNumber()+"@email.com";
		password="123456";
	}

	//@Test
	public void TC_01_() {
		
		// Mở URL ra
navigateToUrlByJS("http://live.techpanda.org/");
sleepInSecond(3);
		// Get domain của page
		String liveGuruDomain = (String) executeForBrower("return document.domain;");
		Assert.assertEquals(liveGuruDomain, "live.techpanda.org");
		String liveURL = (String) executeForBrower("return document.URL");
		Assert.assertEquals(liveURL, "http://live.techpanda.org/");
		hightlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		hightlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		sleepInSecond(6);
		Assert.assertTrue(areExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));
		hightlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");
		sleepInSecond(3);
		String   customServiceTitle = (String) executeForBrower("return document.title");
		Assert.assertEquals(customServiceTitle, "Customer Service");
		hightlightElement("//input[@id='newsletter']");
		
		scrollToElementOnTop("//input[@id='newsletter']");
		sendkeyToElementByJS("//input[@id='newsletter']", "jsexecutor"+ getRandomNumber()+ "@hotmail.com");
		hightlightElement("//button[@title='Subscribe']");
		clickToElementByJS("//button[@title='Subscribe']");
		sleepInSecond(6);
		
		String subscribeMessage = getInnerText();
		Assert.assertTrue(subscribeMessage.contains("Thank you for your subscription."));
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		sleepInSecond(3);
		Assert.assertEquals(executeForBrower("return document.domain"), "demo.guru99.com");
	}

	//@Test
	public void TC_02() {
		driver.get("https://login.ubuntu.com/");
		sleepInSecond(5);
		String emailTextboxXpath = "//input[@id='id_email']"; 
		WebElement loginButton = driver.findElement(By.cssSelector("button[data-qa-id='login_button']"));
		loginButton.click();
		Assert.assertEquals(getElementValidationMessage("//input[@id='id_email']"), "Please fill out this field.");
		driver.findElement(By.xpath(emailTextboxXpath)).sendKeys("123@234!@#");
	loginButton.click();
	if(driver.toString().contains("FirefoxDriver")){
		Assert.assertEquals(getElementValidationMessage(emailTextboxXpath), "Please enter an email address.");
	}else if (driver.toString().contains("ChromeDriver")) {
		Assert.assertEquals(getElementValidationMessage(emailTextboxXpath), "Phần đứng sau '@' không được chứa biểu tượng '!'.");
	}
		
	}
	//@Test
	public void TC_3_Remove_Attribute() {
		driver.get(loginPageUrl);
	 
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys("tuthuc" + getRandomNumber()+ "@email.com");
		driver.findElement(By.name("btnLogin")).click();
		
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
		driver.get(loginPageUrl);
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		// verify homepage
		Assert.assertEquals(driver.findElement(By.cssSelector("marquee.heading3")).getText(), "Welcome To Manager's Page of Guru99 Bank");
		Assert.assertEquals(driver.findElement(By.cssSelector("tr.heading3>td")).getText(), "Manger Id : "+ userID);
		
		driver.findElement(By.xpath("//ul[@class='menusubnav']//a[text()='New Customer']")).click();
		// Input text newcustomer
		driver.findElement(customerNameTextboxBy).sendKeys(customerName);
		removeAttributeInDOM("//input[@name='dob']", "type");
		
		sleepInSecond(3);
		driver.findElement(dateOfBirthTextboxBy).sendKeys(dateofBirth);
		driver.findElement(addressTextboxBy).sendKeys(addressInput);
		driver.findElement(cityTextboxBy).sendKeys(city);
		driver.findElement(stateTextboxBy).sendKeys(state);
		driver.findElement(pinTextboxBy).sendKeys(pin);
		driver.findElement(phoneTextboxBy).sendKeys(phone);
		driver.findElement(emailTextboxBy).sendKeys(email);
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(buttonSubmitBy).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p.heading3")).getText(), "Customer Registered Successfully!!!");
		//Verify 
		Assert.assertEquals(driver.findElement(customerNameBy).getText(), customerName);
		Assert.assertEquals(driver.findElement(genderBy).getText(), gender);
		Assert.assertEquals(driver.findElement(birthdayBy).getText(), dateofBirth);
		Assert.assertEquals(driver.findElement(addressBy).getText(), addressOutput);
		Assert.assertEquals(driver.findElement(cityBy).getText(), city);
		Assert.assertEquals(driver.findElement(stateBy).getText(), state);
		Assert.assertEquals(driver.findElement(pinBy).getText(), pin);
		Assert.assertEquals(driver.findElement(mobileNoBy).getText(), phone);
		Assert.assertEquals(driver.findElement(emailBy).getText(), email);
	}
	//@Test
		public void TC_04_Message_HTML() {
			driver.get("https://automationfc.github.io/html5/index.html");
			WebElement submitButton = driver.findElement(By.name("submit-btn"));
			submitButton.click();
			sleepInSecond(3);
			
			Assert.assertEquals(getElementValidationMessage("//input[@id='fname']"), "Please fill out this field.");
			driver.findElement(By.id("fname")).sendKeys("tu thuc");
			submitButton.click();
			sleepInSecond(3);
			Assert.assertEquals(getElementValidationMessage("//input[@id= 'pass']"), "Please fill out this field.");
			driver.findElement(By.id("pass")).sendKeys("123456");
			submitButton.click();
			sleepInSecond(3);
			Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please fill out this field.");

	}
		@Test
				public void TC_05_Message_HTML() {
					driver.get("https://sieuthimaymocthietbi.com/account/register");
					WebElement submitButton = driver.findElement(By.xpath("//button[text()='Đăng ký']"));
					submitButton.click();
					sleepInSecond(3);
					Assert.assertEquals(getElementValidationMessage("//input[@id='lastName']"),"Please fill out this field.");
		}	
		public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
		if (status) {
			return true;
		}
		return false;
	}

	public WebElement getElement(String locator) {

		return driver.findElement(By.xpath(locator));
	}
	public Object executeForBrower(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
		
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

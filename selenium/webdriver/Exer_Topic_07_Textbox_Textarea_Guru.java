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

public class Exer_Topic_07_Textbox_Textarea_Guru {
	WebDriver driver;
	JavascriptExecutor jsExcutor;
	String projectPath = System.getProperty("user.dir");
	String loginPageUrl, userID,userPassword;
	String customerName, gender, dateofBirth, addressInput, addressOutput, city, state,pin, phone, email, password,customerID; 
	String EditAddressInput, EditAddressOutput, EditCity, EditState, EditPin, EditPhone, EditEmail;
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
		//System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new FirefoxDriver();
		jsExcutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("http://demo.guru99.com/v4");
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
		EditAddressInput="Mercyside\nUnited";
		EditAddressOutput="Mercyside United";
		EditCity="Liverpool";
		EditState="London";
		EditPin="565459";
		EditPhone="0989573949"; 
		EditEmail="gerrard"+ getRandomNumber()+"@email.vn";

	}

	@Test
	public void TC_01_Register() {
		loginPageUrl = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys("tuthuc" + getRandomNumber()+ "@email.com");
		driver.findElement(By.name("btnLogin")).click();
		
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
	}
	@Test
	public void TC_02_Login() {
		driver.get(loginPageUrl);
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		// verify homepage
		Assert.assertEquals(driver.findElement(By.cssSelector("marquee.heading3")).getText(), "Welcome To Manager's Page of Guru99 Bank");
		Assert.assertEquals(driver.findElement(By.cssSelector("tr.heading3>td")).getText(), "Manger Id : "+ userID);
	}
	@Test
	public void TC_03_New_Customer() {
		// Click newcustomer
		driver.findElement(By.xpath("//ul[@class='menusubnav']//a[text()='New Customer']")).click();
		// Input text newcustomer
		driver.findElement(customerNameTextboxBy).sendKeys(customerName);
		
		WebElement dobTextbox = driver.findElement(By.name("dob"));
		jsExcutor.executeScript("arguments[0].removeAttribute('type')", dobTextbox);
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
		customerID = driver.findElement(customerIdBy).getText();   
	}

	@Test
	public void TC_04_Edit_Customer() {
		driver.findElement(By.xpath("//ul[@class='menusubnav']//a[text()='Edit Customer']")).click();
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();
		// Verify gia tri cac truong duoc tao
		Assert.assertEquals(driver.findElement(customerNameTextboxBy).getAttribute("value"), customerName);
		Assert.assertEquals(driver.findElement(By.name("gender")).getAttribute("value"), gender);
		Assert.assertEquals(driver.findElement(dateOfBirthTextboxBy).getAttribute("value"), dateofBirth);
		Assert.assertEquals(driver.findElement(addressTextboxBy).getText(), addressInput);
		Assert.assertEquals(driver.findElement(cityTextboxBy).getAttribute("value"), city);
		Assert.assertEquals(driver.findElement(stateTextboxBy).getAttribute("value"), state);
		Assert.assertEquals(driver.findElement(pinTextboxBy).getAttribute("value"), pin);
		Assert.assertEquals(driver.findElement(emailTextboxBy).getAttribute("value"), email);
		//Edit customer
		driver.findElement(addressTextboxBy).sendKeys(EditAddressInput);
		driver.findElement(cityTextboxBy).sendKeys(EditCity);
		driver.findElement(stateTextboxBy).sendKeys(EditState);
		driver.findElement(pinTextboxBy).sendKeys(EditPin);
		driver.findElement(phoneTextboxBy).sendKeys(EditPhone);
		driver.findElement(emailTextboxBy).sendKeys(EditEmail);
		
		driver.findElement(By.name("sub")).click();
		//Verify giá trị sau khi sửa
		Assert.assertEquals(driver.findElement(By.cssSelector("p.heading3")).getText(), "Customer details updated Successfully!!!");
		Assert.assertEquals(driver.findElement(customerNameBy).getText(), customerName);
		Assert.assertEquals(driver.findElement(genderBy).getText(), gender);
		Assert.assertEquals(driver.findElement(birthdayBy).getText(), dateofBirth);
		
		Assert.assertEquals(driver.findElement(addressBy).getText(), EditAddressOutput);
		Assert.assertEquals(driver.findElement(cityBy).getText(), EditCity);
		Assert.assertEquals(driver.findElement(stateBy).getText(), EditState);
		Assert.assertEquals(driver.findElement(pinBy).getText(), EditPin);
		Assert.assertEquals(driver.findElement(mobileNoBy).getText(), EditPhone);
		Assert.assertEquals(driver.findElement(emailBy).getText(), EditEmail);
		
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
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
		
	}
	}

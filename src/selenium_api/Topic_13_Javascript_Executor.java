package selenium_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Javascript_Executor {
    WebDriver driver;
	JavascriptExecutor javascriptExe;
	WebElement element;
	String customerName, gender, dateOfBirth, address, city, state, pin, phone, email, password;
	By customerNameTextbox = By.xpath("//input[@name='name']");
	By genderTextbox = By.xpath("//input[@name='gender']");
	By dateOFBirthTextbox = By.xpath("//input[@name='dob']");
	By addressTextArea = By.xpath("//textarea[//@name='addr']");
	By cityTextbox = By.xpath("//input[@name='city']");
	By stateTextbox = By.xpath("//input[@name='state']");
	By pinTextbox = By.xpath("//input[@name='pinno']");
	By phoneTextbox = By.xpath("//input[@name='telephoneno']");
	By emailTextbox = By.xpath("//input[@name='emailid']");
	By passwordTextbox = By.xpath("//input[@name='password']");
	By submitButton = By.xpath("//input[@name='sub']");

	@BeforeClass
	public void beforeClass() {
		//driver = new FirefoxDriver();

		 String projectFolder = System.getProperty("user.dir");
		 System.setProperty("webdriver.chrome.driver",projectFolder + ".\\lib\\chromedriver.exe");
		 driver = new ChromeDriver();

		javascriptExe = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		customerName = "Elaine Bence";
		gender = "male";
		dateOfBirth = "2000-01-01";
		address = "16 Village";
		city = "Fresno";
		state = "California";
		pin = "937946";
		phone = "2036397465";
		email = "echate_kim@02multiply.com";
		password = "123123";

	}

	// @Test
	public void TC_01() {
		driver.get("http://live.guru99.com/");

		String liveDomainGuru = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(liveDomainGuru, "live.guru99.com");

		String homePageUrl = (String) executeForBrowser("return document.URL");
		Assert.assertEquals(homePageUrl, "http://live.guru99.com/");

		clickToElementByJS("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//button");
		Assert.assertTrue(isInnerTextMatched("Samsung Galaxy was added to your shopping cart."));
		clickToElementByJS("//a[text()='Customer Service']");
		scrollToBottomPage();
		Assert.assertTrue(isInnerTextContained("Praesent ipsum libero, auctor ac, tempus nec, tempor nec, justo."));
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='Guru99 Bank']")).isDisplayed());
		String demoGuruDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(demoGuruDomain, "demo.guru99.com");

	}

	// @Test
	public void TC_02() throws Exception {
		driver.get("http://demo.guru99.com/v4/");

		sendkeyToElement(By.xpath("//input[@name='uid']"), "mngr209708");
		sendkeyToElement(By.xpath("//input[@name='password']"), "arasYsY");
		clickToElement(By.xpath("//input[@name='btnLogin']"));
		clickToElement(By.xpath("//a[text()='New Customer']"));

		// input data
		sendkeyToElement(customerNameTextbox, customerName);

		removeAttributeInDOM("//input[@name='dob']", "type");
		Thread.sleep(2000);
		sendkeyToElement(dateOFBirthTextbox, dateOfBirth);

		sendkeyToElement(addressTextArea, address);
		sendkeyToElement(cityTextbox, city);
		sendkeyToElement(stateTextbox, state);
		sendkeyToElement(pinTextbox, pin);
		sendkeyToElement(phoneTextbox, phone);
		sendkeyToElement(emailTextbox, email);
		sendkeyToElement(passwordTextbox, password);

		clickToElement(submitButton);

		// output data (verify = input)
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")),
				customerName);
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Gender']/following-sibling::td")), gender);
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")), dateOfBirth);
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Address']/following-sibling::td")), address);
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='City']/following-sibling::td")), city);
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='State']/following-sibling::td")), state);
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Pin']/following-sibling::td")), pin);
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")), phone);
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Email']/following-sibling::td")), email);
	}

	@Test
	public void TC_03() {
		driver.get("http://live.guru99.com/");

		clickToElementByJS("//div[@class='footer']//a[text()='My Account']");

		clickToElementByJS("//span[text()='Create an Account']");

		sendkeyToElementByJS("//input[@id='firstname']", "Automation");
		sendkeyToElementByJS("//input[@id='lastname']", "Testing");
		sendkeyToElementByJS("//input[@id='email_address']", "autotest" + randomData() + "@hot.com");
		sendkeyToElementByJS("//input[@id='password']", "123123");
		sendkeyToElementByJS("//input[@id='confirmation']", "123123");

		clickToElementByJS("//button[@title='Register']");

		Assert.assertTrue(isInnerTextMatched("Thank you for registering with Main Website Store."));
		clickToElementByJS("//header[@id='header']//span[text()='Account']");
		clickToElementByJS("//a[text()='Log Out']");
		Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'logo.png')]")).isDisplayed());
		Assert.assertTrue(isInnerTextContained("THIS IS DEMO SITE FOR"));
	}

	public int randomData() {
		Random rand=new Random();
		return rand.nextInt();
	}

//Browser
	public Object executeForBrowser(String javaSript) {
		return javascriptExe.executeScript(javaSript);
	}

	public boolean isInnerTextMatched(String textExpected) {
		String textActual = (String) javascriptExe
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		System.out.println("Text actual = " + textActual);
		return textActual.equals(textExpected);
	}

	public boolean isInnerTextContained(String textExpected) {
		return (boolean) javascriptExe
				.executeScript("return document.documentElement.innerText.contains('" + textExpected + "')");
	}

	public void scrollToBottomPage() {
		javascriptExe.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		javascriptExe.executeScript("window.location = '" + url + "'");
	}

	// Element
	public void highlightElement(String locatorXP) {
		element = driver.findElement(By.xpath(locatorXP));
		String originalStyle = element.getAttribute("style");
		javascriptExe.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 5px solid red; border-style: dashed;");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		javascriptExe.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);

	}

	public void clickToElementByJS(String locatorXP) {
		element = driver.findElement(By.xpath(locatorXP));
		javascriptExe.executeScript("arguments[0].click();", element);
	}

	public void scrollToElement(String locatorXP) {
		element = driver.findElement(By.xpath(locatorXP));
		javascriptExe.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void sendkeyToElementByJS(String locatorXP, String value) {
		element = driver.findElement(By.xpath(locatorXP));
		javascriptExe.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public void removeAttributeInDOM(String locatorXP, String attributeRemove) {
		element = driver.findElement(By.xpath(locatorXP));
		javascriptExe.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}

	// Selenium
	public String getTextElement(By by) {
		return driver.findElement(by).getText();
	}

	public void clickToElement(By by) {
		driver.findElement(by).click();

	}

	public void sendkeyToElement(By by, String value) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(value);
	}

	public String getAttributeValue(By by, String attributeName) {
		return driver.findElement(by).getAttribute(attributeName);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
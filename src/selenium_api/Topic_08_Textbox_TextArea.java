package selenium_api;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_08_Textbox_TextArea {
    WebDriver driver;
	String customerName, gender, dateOfBirth, address, city, state, pin, phone, email, password, customerID;
	String addressEdit, cityEdit, stateEdit, pinEdit, phoneEdit, emailEdit;

	By customerNameTextbox = By.xpath("//input[@name='name']");
	By genderTextbox = By.xpath("//input[@name='gender']");
	By dateOFBirthTextbox = By.xpath("//input[@name='dob']");
	By addressTextArea = By.xpath("//textarea[//@name='addr']");
	By cityTextbox = By.xpath("//input[@name='city']");
	By stateTextbox = By.xpath("//input[@name='state']");
	By pinTextbox = By.xpath("//input[@name='pinno']");
	By phoneTextbox = By.xpath("//input[@name='telephoneno']");
	By emailTextbox = By.xpath("//input[@name='password']");
	By passwordTextbox = By.xpath("//input[@name='password']");
	By submitButton = By.xpath("//input[@name='sub']");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://demo.guru99.com/v4/");

		customerName = "Elaine Bence";
		gender = "male";
		dateOfBirth = "2000-01-01";
		address = "16 Village";
		city = "Fresno";
		state = "California";
		pin = "937946";
		phone = "2036397465";
		email = "echate01@multiply.com";
		password = "123123";

		addressEdit = "16 Village edit";
		cityEdit = "Fresno edit";
		stateEdit = "California edit";
		pinEdit = "937947";
		phoneEdit = "2036397444";
		emailEdit = "echate02@multiply.com";

	}

	@Test
	public void TC01_CreateNewCustomer() {

		sendkeyToElement(By.xpath("//input[@name='uid']"), "mngr209708");
		sendkeyToElement(By.xpath("//input[@name='password']"), "arasYsY");
		clickToElement(By.xpath("//input[@name='btnLogin']"));
		clickToElement(By.xpath("//a[text()='New Customer']"));

		// input data
		sendkeyToElement(customerNameTextbox, customerName);
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
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Customer Name']/following sibling::id")),
				customerName);
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Gender']/following sibling::id")), gender);
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Birthdate']/following sibling::id")), dateOfBirth);
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Address']/following sibling::id")), address);
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='City']/following sibling::id")), city);
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='State']/following sibling::id")), state);
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Pin']/following sibling::id")), pin);
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Mobile No.']/following sibling::id")), phone);
		Assert.assertEquals(getTextElement(By.xpath("//td[text()='Email']/following sibling::id")), email);

		customerID = getTextElement(By.xpath("//td[text()='Customer ID']/following sibling::td"));

	}
	
	@Test
	public void TC02_EditCustomer() {
		clickToElement(By.xpath("//a[text()='Edit Customer']"));
		
		sendkeyToElement(By.xpath("//input[@name='cusid']"),customerID);
		clickToElement(By.xpath("//input[@name='AccSubmit']"));
		
		//Verify data at Edit Customer = Input (New Customer)
		Assert.assertEquals(getAttributeValue(customerNameTextbox,"value"),customerName);
		Assert.assertEquals(getAttributeValue(genderTextbox,"value"),gender);
		Assert.assertEquals(getAttributeValue(dateOFBirthTextbox,"value"),dateOfBirth);
		
		//Address(option - get text)
		Assert.assertEquals(getTextElement(addressTextArea),address);
		Assert.assertEquals(getAttributeValue(cityTextbox,"value"),city);
		Assert.assertEquals(getAttributeValue(stateTextbox,"value"),state);
		Assert.assertEquals(getAttributeValue(pinTextbox,"value"),pin);
		Assert.assertEquals(getAttributeValue(phoneTextbox,"value"),phone);
		Assert.assertEquals(getAttributeValue(emailTextbox,"value"),email);
		
		//Edit
		sendkeyToElement(addressTextArea, addressEdit);
		sendkeyToElement(cityTextbox, city);
		sendkeyToElement(stateTextbox, state);
		sendkeyToElement(pinTextbox, pin);
		sendkeyToElement(phoneTextbox, phone);
		sendkeyToElement(emailTextbox, email);
		clickToElement(submitButton);
	}

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
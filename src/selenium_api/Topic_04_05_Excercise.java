package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_04_05_Excercise {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.get("http://live.guru99.com");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	}

	@Test
	public void TC01_Email_PW_Empty() throws Exception {

		WebElement emailTextbox = driver.findElement(By.id("email"));
		emailTextbox.sendKeys("");

		WebElement pwTextbox = driver.findElement(By.id("pass"));
		pwTextbox.sendKeys("");

		WebElement loginButton = driver.findElement(By.id("send2"));
		loginButton.click();

		String emailError = driver.findElement(By.id("advice-required-entry-email")).getText();
		Assert.assertEquals(emailError, "This is a required field.");

		String pwError = driver.findElement(By.id("advice-required-entry-pass")).getText();
		Assert.assertEquals(pwError, "This is a required field.");
	}

	@Test
	public void TC02_Invalid_Email() throws Exception {

		WebElement emailTextbox = driver.findElement(By.id("email"));
		emailTextbox.sendKeys("123434234@12312.123123");

		WebElement loginButton = driver.findElement(By.id("send2"));
		loginButton.click();

		String invalidEmail = driver.findElement(By.id("advice-validate-email-email")).getText();
		Assert.assertEquals(invalidEmail, "Please enter a valid email address. For example johndoe@domain.com.");

		Thread.sleep(5000);
		driver.findElement(By.id("email")).clear();
	}

	@Test
	public void TC03_correctEmail_invalidPW() throws Exception {

		WebElement emailTextbox = driver.findElement(By.id("email"));
		emailTextbox.sendKeys("automation@gmail.com");

		WebElement pwTextbox = driver.findElement(By.id("pass"));
		pwTextbox.sendKeys("123");

		WebElement loginButton = driver.findElement(By.id("send2"));
		loginButton.click();

		String invalidPW = driver.findElement(By.id("advice-validate-password-pass")).getText();
		Assert.assertEquals(invalidPW, "Please enter 6 or more characters without leading or trailing spaces.");

		Thread.sleep(5000);
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("pass")).clear();

	}

	@Test
	public void TC04_correctEmail_IncorrectPW() throws Exception {

		WebElement emailTextbox = driver.findElement(By.id("email"));
		emailTextbox.sendKeys("automation@gmail.com");

		WebElement pwTextbox = driver.findElement(By.id("pass"));
		pwTextbox.sendKeys("123123123");

		WebElement loginButton = driver.findElement(By.id("send2"));
		loginButton.click();

		String incorrectPW = driver.findElement(By.xpath("//li[@class='error-msg']")).getText();
		Assert.assertEquals(incorrectPW, "Invalid login or password.");

		Thread.sleep(5000);
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("pass")).clear();

	}

	@Test
	public void TC05_registerAccount() throws Exception {

		WebElement createButton = driver.findElement(By.xpath("//a[contains(@title,'Create an Account')]"));
		createButton.click();
		Thread.sleep(5000);

		WebElement firstName = driver.findElement(By.id("firstname"));
		firstName.sendKeys("Kim123");

		WebElement lastName = driver.findElement(By.id("lastname"));
		lastName.sendKeys("Nguyen");

		WebElement emailAddress = driver.findElement(By.id("email_address"));
		emailAddress.sendKeys("vanhungnb1610@gmail.com");

		WebElement pass = driver.findElement(By.id("password"));
		pass.sendKeys("123456789");

		WebElement passConfirm = driver.findElement(By.id("confirmation"));
		passConfirm.sendKeys("123456789");

		WebElement registerButton = driver.findElement(By.xpath("//div[@class='button-set']//button[@type='submit']"));
		registerButton.click();

		String successMess = driver.findElement(By.xpath("//li[@class='success-msg']")).getText();
		Assert.assertEquals(successMess, "Thank you for registering with Main Website Store.");
		Thread.sleep(5000);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
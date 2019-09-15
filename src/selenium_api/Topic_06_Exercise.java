package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_06_Exercise {
	
    WebDriver driver;
	By emailTextbox = By.xpath("//input[@id='mail']");
	By ageRadio = By.xpath("//input[@id='under_18']");
	By educationTextbox = By.xpath("//textarea[@id='edu']");
	By passTextbox = By.xpath("//input[@id='password']");
	By job01Dropdown = By.xpath("//select[@id='job1']");
	By developmentCheckbox = By.xpath("//input[@id='development']");
	By age_over18Radio = By.xpath("//input[@id='over_18']");
	By slider_01 = By.xpath("//input[@id='slider-1']");
	By disabled_ageRadio = By.xpath("//input[@id='radio-disabled']");
	By bioTextArea = By.xpath("//textarea[@id='bio']");
	By job02Dropdown = By.xpath("//select[@id='job2']");
	By disabledCheckbox = By.xpath("//input[@id='check-disbaled']");
	By slider_02 = By.xpath("//input[@id='slider-2']");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		driver.manage().window().maximize();
	}

	@Test
	public void TC01_isDisplayed() throws Exception {

		if (isElementDisplayed(emailTextbox)) {
			sendkeyToElement(emailTextbox, "Automation Testing");
		}
		Assert.assertTrue(isElementDisplayed(emailTextbox));

		if (isElementDisplayed(ageRadio)) {
			clickToElement(ageRadio);
		}
		Assert.assertTrue(isElementDisplayed(ageRadio));

		if (isElementDisplayed(educationTextbox)) {
			sendkeyToElement(educationTextbox, "Automation Testing");
		}
		Assert.assertTrue(isElementDisplayed(educationTextbox));

		Thread.sleep(5000);
	}

	@Test
	public void TC02_isEnabled() {

		Assert.assertTrue(isElementEnabled(emailTextbox));
		Assert.assertTrue(isElementEnabled(ageRadio));
		Assert.assertTrue(isElementEnabled(educationTextbox));
		Assert.assertTrue(isElementEnabled(job01Dropdown));
		Assert.assertTrue(isElementEnabled(developmentCheckbox));
		Assert.assertTrue(isElementEnabled(slider_01));

		Assert.assertFalse(isElementEnabled(passTextbox));
		Assert.assertFalse(isElementEnabled(disabled_ageRadio));
		Assert.assertFalse(isElementEnabled(bioTextArea));
		Assert.assertFalse(isElementEnabled(job02Dropdown));
		Assert.assertFalse(isElementEnabled(disabledCheckbox));
		Assert.assertFalse(isElementEnabled(slider_02));

	}

	@Test
	public void TC03_isSelected() throws Exception {

		clickToElement(ageRadio);
		clickToElement(developmentCheckbox);

		Assert.assertTrue(isElementSelected(ageRadio));
		Assert.assertTrue(isElementSelected(developmentCheckbox));

		Thread.sleep(5000);

		clickToElement(developmentCheckbox);
		Assert.assertFalse(isElementSelected(developmentCheckbox));

	}

	public boolean isElementDisplayed(By by) {
		if (driver.findElement(by).isDisplayed()) {
			System.out.println("Element with locator [" + by + "]is displayed");
			return true;
		} else {
			System.out.println("Element with locator [" + by + "]is NOT displayed");
			return false;
		}
	}

	public boolean isElementEnabled(By by) {
		if (driver.findElement(by).isEnabled()) {
			System.out.println("Element with locator [" + by + "]is enabled");
			return true;
		} else {
			System.out.println("Element with locator [" + by + "]is disabled");
			return false;
		}

	}

	public boolean isElementSelected(By by) {
		if (driver.findElement(by).isSelected()) {
			System.out.println("Element with locator [" + by + "]is selected");
			return true;
		} else {
			System.out.println("Element with locator [" + by + "]is unselected");
			return false;
		}

	}

	public void sendkeyToElement(By by, String value) {
		driver.findElement(by).sendKeys(value);
	}

	public void clickToElement(By by) {
		driver.findElement(by).click();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
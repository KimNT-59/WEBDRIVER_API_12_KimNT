package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_09_Checkbox_Radio_Alert {
	
	WebDriver driver;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		driver=new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01() {
		driver.get("http://live.guru99.com/");
		clickElementByJavascript(By.xpath("//div[@class='footer']//a[(text()='My Account')]"));
		//Assert.assertEquals("//span[text()='Create My Account'", expected);
		
	}

	public void clickElementByJavascript(By by) {
		jsExecutor= (JavascriptExecutor) driver;
		jsExecutor.executeScript("argument[0].click;", driver.findElement(by));
		
	}

	@Test
	public void TC_02() {
	}

	//Post-condition
	@AfterClass
	public void afterClass() {
	}

}

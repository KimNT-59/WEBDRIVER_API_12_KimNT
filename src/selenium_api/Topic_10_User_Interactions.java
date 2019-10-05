package selenium_api;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;

public class Topic_10_User_Interactions {
    WebDriver driver;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		// FirefoxProfile profile = new FirefoxProfile();
		// profile.setPreference("dom.webnotifications.enabled", false);
		// driver = new FirefoxDriver(profile);
		driver = new FirefoxDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void TC_01_Hover() throws Exception {
		driver.get("http://www.myntra.com/");

		WebElement discoverLink = driver.findElement(By.xpath("//nav[@class='desktop-navbar']//a[text()='Discover']"));
		action.moveToElement(discoverLink).perform();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//a[text()='American Eagle']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb' and text()='American Eagle']"))
				.isDisplayed());

	}

	public void TC_02_Click_Hold_01() throws Exception {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");

		// WebElement cell = driver.findElement(By.xpath("//ol[@id='selectable']/li"));
		List<WebElement> allCells = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		System.out.println("All items=" + allCells.size());

		action.clickAndHold(allCells.get(0)).moveToElement(allCells.get(7)).release().perform();
		;
		Thread.sleep(3000);

		List<WebElement> cellSelected = driver
				.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		System.out.println("All selected items = " + cellSelected.size());
		Assert.assertEquals(cellSelected.size(), 8);

	}

	public void TC_03_Randoom() throws Exception {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");

		// WebElement cell = driver.findElement(By.xpath("//ol[@id='selectable']/li"));
		List<WebElement> allCells = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		System.out.println("All items=" + allCells.size());

		action.keyDown(Keys.CONTROL).perform();
		action.click(allCells.get(0)).click(allCells.get(0)).click(allCells.get(3)).click(allCells.get(4))
				.click(allCells.get(7)).perform();
		action.keyUp(Keys.CONTROL).perform();
		// action.clickAndHold(allCells.get(0)).moveToElement(allCells.get(7)).release().perform();
		// ;
		Thread.sleep(3000);

		List<WebElement> cellSelected = driver
				.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		System.out.println("All selected items = " + cellSelected.size());
		Assert.assertEquals(cellSelected.size(), 8);

	}

	public void TC_04_Double_Click() throws Exception {
		driver.get("https://automationfc.github.io/basic-form/index.html ");
		WebElement doubleClick = driver.findElement(By.xpath("//button[text()='Double click me']"));
		action.doubleClick(doubleClick).perform();
		Thread.sleep(3000);
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@id='demo' and text()='Hello Automation Guys!']")).isDisplayed());
	}

	
	public void TC_05_Right_Click() throws Exception {
		
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		WebElement rightClick = driver.findElement(By.xpath("//span[text()='right click me']"));
		action.contextClick(rightClick).perform();
		Thread.sleep(3000);
		
		WebElement quitBeforeHover = driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')and not(contains(@class,'context-menu-visible')) and not (contains(@class,'context-menu-hover'))]"));
		action.moveToElement(quitBeforeHover).perform();
		
		WebElement quitAfterHover = driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')and contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]"));
		Assert.assertTrue(quitAfterHover.isDisplayed());
	}
	@Test
	public void TC_06_Drag_Drop() throws Exception {
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/angular");
		
		WebElement sourceCircle = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement targetCircle = driver.findElement(By.xpath("//div[@id='droptarget']"));
		action.dragAndDrop(sourceCircle, targetCircle).perform();
		Thread.sleep(3000);
		WebElement targetSuccess = driver.findElement(By.xpath("//div[@id='droptarget' and text()='You did great!']"));
		Assert.assertTrue(targetSuccess.isDisplayed());
	
	}
	
	public void TC_07_Drag_Drop_HTML5() throws Exception {
		
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
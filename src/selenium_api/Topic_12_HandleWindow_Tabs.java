package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_12_HandleWindow_Tabs {
    WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01() throws Exception {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// lay ra ID cua current page
		String parentWindowID = driver.getWindowHandle();
		System.out.println("Parent ID = " + parentWindowID);

		// Truoc khi click (1 cua so cua parent)
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		Thread.sleep(2000);

		switchToWindowByTitle("Google");

		Assert.assertEquals(driver.getTitle(), "Google");
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='q']")).isDisplayed());

		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		Thread.sleep(2000);

		switchToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");

		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		Thread.sleep(2000);

		switchToWindowByTitle("Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		Assert.assertEquals(driver.getTitle(), "Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");

		Assert.assertTrue(closeAllWindowsWithoutParent(parentWindowID));

	}

	public boolean closeAllWindowsWithoutParent(String parentWindow) {
		// get ra tat ca cac ID
		Set<String> allWindows = driver.getWindowHandles();
		// Dung vong lap de duyet qua cac ID da get
		for (String runWindows : allWindows) {
			// neu cua so nao ma khac voi parent ID
			if (!runWindows.equals(parentWindow)) {
				// switch vao cua so do
				driver.switchTo().window(runWindows);
				// dung ham close de dong cua so do lai
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
		// de kiem tra chi con lai duy nhat 1 cua so parent
		if (driver.getWindowHandles().size() == 1)
			return true;
		else

			return false;
	}

	public void switchToWindowByTitle(String title) {
		// Get ra tat ca cac ID
		Set<String> allWindows = driver.getWindowHandles();

		// Dung vong lap de duyet qua cac ID da get
		for (String runWindows : allWindows) {
			// cho switch vao cac ID truoc
			driver.switchTo().window(runWindows);
			// get ra title cua page do xem no bang cai gi
			String currentWin = driver.getTitle();
			// Neu nhu title bang voi title minh expected thi thoat khoi vong lap
			if (currentWin.equals(title)) {
				break;
			}

		}

	}

	@Test
	public void TC_02() {
	}

	// Post-condition
	@AfterClass
	public void afterClass() {
	}

}
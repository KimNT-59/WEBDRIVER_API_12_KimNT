package selenium_api;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_01_CheckEnviroment {
    
	//khi bao bien driver
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		System.out.println("Pre-condition step 01: Init Firefox browser");
		driver=new FirefoxDriver();
		
		System.out.println("Pre-condition step 02: wait for page loading success");
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		System.out.println("pre-condition step 03: Open iTviec app url");
		driver.get("https://itviec.com/");
	}
	
	@Test
	public void TC_01_Check_HomePage02() {
		System.out.println("Run TestCase 001:Get current page url");
		String homePageUrl=driver.getCurrentUrl();
		
		System.out.println("TC_02: Verify expected url matching with actual url");
		Assert.assertEquals(homePageUrl,"https:itviec.com/");
		
	}
	@Test
	public void TC_02_Check_Homepage_Title02() {
		System.out.println("Run TestCase 02_Step01: Get Curent title");
		String homePageTitle=driver.getTitle();
		
		System.out.println("TC02_Step002: Verify expected url matching with actual url");
		Assert.assertEquals(homePageTitle, "ITviec | Top Jobs for You");
		
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("Run post-condition: close browser");
		driver.quit();
	}

}
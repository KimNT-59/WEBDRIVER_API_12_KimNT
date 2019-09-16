package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_03_WebBrowser_WebElement_APIs {
  //Khai báo biến
    WebDriver driver;
	String firstname, lastname, address, city;
	int price, productSize;
	
	//Chạy đầu tiên, để nó khởi tạo browser.khởi tạo data test, khởi tạo biến/....
@BeforeTest
public void initData() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
  
  }

  @Test
public void TC_01_WebBrowser() {
	//đóng browser (dùng khi browser có nhiều tab)
	  driver.quit();
	  //dùng khi chỉ có 1 tab
	  driver.close(); 
	  
	  //Mở ra 1 url (App link)
	  driver.get("http://live.guru99.com");
	  
	  
	  //Get ra cais url cua page hien tai
	  String homePage=driver.getCurrentUrl();
	  
	  // verify dữ liệu đúng 
	  Assert.assertTrue(homePage.equals("http://live.guru99.com"));
	  
	  //Verify 1 ddkien sai
	  Assert.assertEquals(homePage,("http://live.guru99.com/index.php/customer/account/"));
	  
	  //Verify 1 dkien đầu vào bằng đầu ra
	  Assert.assertEquals(homePage,"http://live.guru99.com");
	
	  //assertTrue/False: trả về kiểu boolean(true/false) -->isDisplayed/isEnabled/isSelected...
	  //assertEquals: trả về kiểu dữ liệu String,int,double, float/...
	  
	  //trả về source code của page hiện tại:html/csss/jquery/js...
	  String pageSource=driver.getPageSource();
	  Assert.assertTrue(pageSource.contains("This is site for"));
	  
	  //Trả về title của page hiện tại: console: document.title
	  String homePageTitle=driver.getTitle();
	  Assert.assertEquals(homePageTitle, "Home page");
	  
	  driver.getWindowHandle();
	  driver.getWindowHandles();
	  
	  //Chờ cho element đc tìm thấy
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  //Chờ cho load thành công
	  driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	  
	  //Test GUI: graphic user interface
	  driver.manage().window().maximize();
	  
	  //driver.navigate().back/refresh/forward();
	  
	  //tracking lại history
	  driver.navigate().to("http://live.guru99.com");
	  
	  //Alert/iframe/windows
	  driver.switchTo().alert().accept();
	  driver.switchTo().frame("");
	  driver.switchTo().window("");
	  
  }
  
  @Test
public void TC_02_WebElementAPI() {
	  //hàm tìm 1 or nhiều element với locator là gì
	  //nếu element chỉ dùng 1 lần thì dùng cách này
	  driver.findElement(By.id("search")).sendKeys("Sam sung");
	  
	  //nếu element này cần dùng/thao tác nhiều lần thì khai báo biến
	  WebElement searchTextbox=driver.findElement(By.id("search"));
	  //Xóa dữ liệu trc khi sendkey
	  searchTextbox.clear();
	  //Nhập dữ liệu vào 1 textbox/textarea
	  searchTextbox.sendKeys();
	  //Click vào 1 element:button/link/radio/checkbox
	  searchTextbox.click();
	  //TÌm và thao tác với 1 element: findElemnt
	  searchTextbox.findElement(By.id("search")).click();
	  //Tìm và thao tác với nhiều element: find Elelemtns
	  searchTextbox.findElements(By.id("search")).get(0).click();
	  //0 1 2 3 4 5 >> index
	  //A B C D E F >> data
	  
	  //tagname[@attribute]=value
	  String searchPlaceholderValue=searchTextbox.getAttribute("placeholder");
	 
	  //TestGUI:font/size/color/position....
	  String loginButtonColor=searchTextbox.getCssValue("background");
	  
	  //học trong bài build framwork
	 // searchTextbox.getScreenshotAs(arg0);
	  WebElement searchTextbox1=driver.findElement(By.cssSelector("#search"));
	  String searchTextboxTagname=searchTextbox1.getTagName();
	  //searchTextboxTagName=input
	  
	  //Trả về text của 1 element: link/button/label/...
	  String searchText=searchTextbox.getText();
	  
	  //AssertTrue/False đển check xem value trả về đúng hay sai
	  Assert.assertTrue(searchTextbox.isDisplayed());
	  Assert.assertTrue(searchTextbox.isEnabled());
	  Assert.assertTrue(searchTextbox.isSelected());
	  Assert.assertFalse(searchTextbox.isSelected());
	  //boolean searchTextboxStatus=searchTextbox.isSelected();
	  //Assert.assertFalse(searchTextboxStatus);
	  //Assert.assertEquals(searchTextboxStatus,false);
	  
	  //work duy nhất với form(login/register) >>tagname=form
	  searchTextbox.submit();
	  
  }
  
  //Chạy cuối cùng: đóng browser,clean data....
  @AfterClass
  public void cleanData() {
  driver.quit();
  }

}
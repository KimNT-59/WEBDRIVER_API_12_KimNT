package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_02_XPath_Css_Part_1 {
  WebDriver driver;
    
	
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	  driver.get("http://live.guru99.com/");
  }

  @Test
  public void TC_01() throws Exception {
	  driver.findElement(By.xpath("//div[@class='footer']/a[text()='My Account']")).click();
	  
	  //từ browser, mở page cần mở, tìm đến element có id=email, sau khi tìm đc, nhập dữ liệu vào element này
	  driver.findElement(By.id("email")).sendKeys("id_email@gmail.com");
	  //fix sleep 5s
	  Thread.sleep(2000);
	  driver.findElement(By.id("email")).clear();
	  
	  driver.findElement(By.className("validate email")).sendKeys("class_email@gmail.com");
	  Thread.sleep(2000);
	  driver.findElement(By.id("email")).clear();
	  
	  driver.findElement(By.name("login[username]")).sendKeys("name_email@gmail.com");
	  Thread.sleep(2000);
	  driver.findElement(By.id("email")).clear();
	  
	  //Work vs link, voi Linktext thi phai nhap full text
	  driver.findElement(By.linkText("SITE MAP")).click();
	  
	  //Co the chi can "Advanced" or "Search" mien la khong trung text vs link khac
	  driver.findElement(By.partialLinkText("ADVANCED")).click();
	  		
	  driver.findElement(By.xpath("//div[@class='footer']/a[text()='My Account']")).click();
	  
	  driver.findElement(By.tagName("a"));
	  driver.findElements(By.tagName("a")).size();
	  System.out.println("Tat ca link tren page = "+ driver.findElements(By.tagName("a")).size());
	  
	  //CSS
	  driver.findElement(By.cssSelector("#email")).sendKeys("css_01@gmail.com");
	  Thread.sleep(2000);
	  driver.findElement(By.cssSelector("#email")).clear();
	  
	  driver.findElement(By.cssSelector(".validate email")).sendKeys("css_02@gmail.com");
	  Thread.sleep(2000);
	  driver.findElement(By.cssSelector(".validate email")).clear();
	  
	  driver.findElement(By.cssSelector("input[name=['login[username]']")).sendKeys("css_03@gmail.com");
	  Thread.sleep(2000);
	  driver.findElement(By.cssSelector("input[name=['login[username]']")).clear();
	  System.out.println("Tat ca link tren page = "+ driver.findElements(By.cssSelector("a")).size());
	  
	  //XPATH
	  driver.findElement(By.xpath("//input[@id='email']")).sendKeys("xpath_01@gmail.com");
	  Thread.sleep(2000);
	  driver.findElement(By.cssSelector("//input[@id='email']")).clear();
	  
	  driver.findElement(By.xpath("//input[contain(@class,'validate-email')]")).sendKeys("xpath_02@gmail.com");
	  Thread.sleep(2000);
	  driver.findElement(By.cssSelector("//input[contain(@class,'validate-email')]")).clear();
	  
	  driver.findElement(By.xpath("//input[@name='login[username]']")).sendKeys("xpath_03@gmail.com");
	  Thread.sleep(2000);
	  driver.findElement(By.cssSelector("//input[@name='login[username]']")).clear();
	  
	  System.out.println("Tat ca link tren page = "+ driver.findElements(By.xpath("//a")).size());
  }
  
  @Test
  public void TC_02() {
  }
  
  //Post-condition
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
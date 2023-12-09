package qaiserhabib.appium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

public class mobileBrowserTest extends browserBaseTest {

	@Test
	public void browserTest() throws InterruptedException {
		
		//Open google
//		driver.get("https://www.google.com");
//		System.out.println(driver.getTitle());
//		driver.findElement(By.name("q")).sendKeys("Qaiser Habib");
//		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		
		//Open Angularapp
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.xpath("//span[@class='navbar-toggler-icon']")).click();
		driver.findElement(By.xpath("//li[@class='nav-item active']")).click();
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0.1000,0)", "");
		String text = driver.findElement(By.cssSelector("a[href*='products/3']")).getText();
		Assert.assertEquals(text, "Devops");
		
		Thread.sleep(2000);
	}
}

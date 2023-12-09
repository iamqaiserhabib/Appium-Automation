package qaiserhabib.appium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class ecommerce_tc_3 extends baseTest {

	@Test
	public void fillForm() throws InterruptedException {
		
		//Fill the form
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Qaiser Habib");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text= 'Female']")).click();
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text= 'Argentina']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		//Add products to cart
		driver.findElements(By.xpath("//android.widget.TextView[@text= 'ADD TO CART']")).get(0).click();
		driver.findElements(By.xpath("//android.widget.TextView[@text= 'ADD TO CART']")).get(0).click();
		
		//Click on cart
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		
		//Verify the products prices
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
		List<WebElement> productPrices = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
		int count = productPrices.size();
		double totalSum=0;
		for(int i=0; i<count; i++) {
			String amountString = productPrices.get(i).getText();
			double price = getFormattedAmount(amountString);  
			totalSum = totalSum + price;
		}
		String displaySum = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
		double displayFormattedSum = getFormattedAmount(displaySum);
		Assert.assertEquals(totalSum, displayFormattedSum);
		//Submit the order
		WebElement ele = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
		longPressAction(ele);
		driver.findElement(By.id("android:id/button1")).click();
		driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
		
		Thread.sleep(2000);
		
	}
}

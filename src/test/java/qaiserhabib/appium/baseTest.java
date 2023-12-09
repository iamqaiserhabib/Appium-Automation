package qaiserhabib.appium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class baseTest {

	public AndroidDriver driver;
	public AppiumDriverLocalService serviceBuilder;
	
	@BeforeClass
	public void configureAppium() throws MalformedURLException {
		serviceBuilder = new AppiumServiceBuilder().withAppiumJS(new File("C://Users//admin//AppData//Roaming//npm//node_modules//appium//build//lib//main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
		serviceBuilder.start();
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("QaiserEmulator");
		options.setChromedriverExecutable("C://Users//admin//Desktop//chromedriver.exe");
//		options.setApp("C://Users//admin//eclipse-workspace//appium//src//test//java//resources//ApiDemos-debug.apk");
		options.setApp("C://Users//admin//eclipse-workspace//appium//src//test//java//resources//General-Store.apk");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	public void longPressAction(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement) ele).getId(),
				"duration", 2000));
	}
	
	public void swipeAction(WebElement firstImage, String direction) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) firstImage).getId(),
			    "direction", direction,
			    "percent", 0.75
			));
	}
	
	public Double getFormattedAmount(String amount) {
		Double price = Double.parseDouble(amount.substring(1));
		return price;
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
		serviceBuilder.stop();
	}
}

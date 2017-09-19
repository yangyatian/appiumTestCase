package baseInit;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class BaseInit {
	protected AppiumDriver<AndroidElement> driver;
	String deviceId = "BY3AME158E003597";
	String platfromVersion = "4.4.2";
	String appPackage = "com.autonavi.minimap";
	String appActivity = "com.autonavi.map.activity.NewMapActivity";
	String platfromName = "Android";
	@BeforeSuite
	public void setUp() throws MalformedURLException{		
		DesiredCapabilities cap = new DesiredCapabilities();

		cap.setCapability("platfromName", platfromName);
		cap.setCapability("deviceName", deviceId);
		cap.setCapability("platfromVersion", platfromVersion);
		cap.setCapability("appPackage", appPackage);
		cap.setCapability("appActivity", appActivity );
		// 覆盖new session，解决无法创建new session
		cap.setCapability("appWaitActivity", appActivity );
		cap.setCapability("sessionOverride", true);
		// 支持中文输入
		cap.setCapability("unicodeKeyboard", "True");
		cap.setCapability("resetKeyboard", "True");

		driver = new AndroidDriver<>(new URL(
				"http://127.0.0.1:4723/wd/hub"), cap);
	}
	
	@AfterSuite
	public void tearDown(){
		driver.quit();
	}

}

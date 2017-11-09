package baseInit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

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
		// cap.setCapability("app", "C:\\software\\jrtt.apk");//安装apk
		// cap.setCapability("browserName", "chrome");//设置HTML5的自动化，打开谷歌浏览器
		cap.setCapability("platfromName", platfromName);
		cap.setCapability("deviceName", deviceId);
		cap.setCapability("platfromVersion", platfromVersion);
		cap.setCapability("appPackage", appPackage);
		cap.setCapability("appActivity", appActivity );
		// 覆盖new session，解决无法创建new session
		cap.setCapability("appWaitActivity", appActivity );
		cap.setCapability("sessionOverride", true);
		cap.setCapability("unicodeKeyboard", "True");// 支持中文输入
		cap.setCapability("resetKeyboard", "True");//重置输入法
		cap.setCapability("noSign", "True"); //不重新签名apk
		cap.setCapability("newCommandTimeout", "30"); //没有新命令，appium30秒退出
		driver = new AndroidDriver<AndroidElement>(new URL(
				"http://127.0.0.1:4723/wd/hub"), cap);
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		
		
	}
	
	@AfterSuite
	public void tearDown(){
		//driver.quit();
	}

}

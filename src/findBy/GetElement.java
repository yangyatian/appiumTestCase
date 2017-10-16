package findBy;

import java.util.List;
import org.openqa.selenium.WebElement;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;

public class GetElement {
	private AppiumDriver<AndroidElement> driver;

	public GetElement(AppiumDriver<AndroidElement> driver) {
		this.driver = driver;
	}
	
	

	/***
	 * xpath根据content-desc查找元素
	 * @param view的类型
	 * @param content-desc 的内容
	 * @return
	 */
	public WebElement getViewbyXpathWithcontentdesc(String view, String name) {
		return this.driver.findElementByXPath("//" + view
				+ "[contains(@content-desc,'" + name + "')]");
	}

	/***
	 * xpath根据text查找元素
	 * 
	 * @param view的类型
	 * @param text的内容
	 * @return
	 */
	public WebElement getViewbyXpathWithtext(String view, String name) {
		return this.driver.findElementByXPath("//" + view + "[contains(@text,'"
				+ name + "')]");
	}

	// 通过classname和index定位多个元素list方法（需要引入sdk下的android和uiautomator的jar包）

	@SuppressWarnings("unchecked")
	public List<MobileElement> getElementsByClassAndIndex(String classname,
			int index) {
		List<MobileElement> lis = null;
		lis = ((FindsByAndroidUIAutomator<MobileElement>) driver)
				.findElementsByAndroidUIAutomator("new UiSelector().className("
						+ classname + ").index(" + index + ")");
		return lis;
	}
}

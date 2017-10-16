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
	 * xpath����content-desc����Ԫ��
	 * @param view������
	 * @param content-desc ������
	 * @return
	 */
	public WebElement getViewbyXpathWithcontentdesc(String view, String name) {
		return this.driver.findElementByXPath("//" + view
				+ "[contains(@content-desc,'" + name + "')]");
	}

	/***
	 * xpath����text����Ԫ��
	 * 
	 * @param view������
	 * @param text������
	 * @return
	 */
	public WebElement getViewbyXpathWithtext(String view, String name) {
		return this.driver.findElementByXPath("//" + view + "[contains(@text,'"
				+ name + "')]");
	}

	// ͨ��classname��index��λ���Ԫ��list��������Ҫ����sdk�µ�android��uiautomator��jar����

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

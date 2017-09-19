package findBy;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

public class GetElement {
	private AppiumDriver<AndroidElement> driver;
	public GetElement(AppiumDriver<AndroidElement> driver){
		this.driver = driver;		
	}
	/***
	* xpath����content-desc����Ԫ��
	* @param view������
	* @param content-desc ������
	* @return
	*/
	public WebElement getViewbyXathwithcontentdesc(String view,String name){
	return this.driver.findElementByXPath("//"+view+"[contains(@content-desc,'"+name+"')]");
	}
	/***
	* xpath����text����Ԫ��
	* @param view������
	* @param text������
	* @return
	*/
	public WebElement getViewbyXathwithtext(String view,String name){
	return this.driver.findElementByXPath("//"+view+"[contains(@text,'"+name+"')]");
	}

}

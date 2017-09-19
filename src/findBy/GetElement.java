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
	* xpath根据content-desc查找元素
	* @param view的类型
	* @param content-desc 的内容
	* @return
	*/
	public WebElement getViewbyXathwithcontentdesc(String view,String name){
	return this.driver.findElementByXPath("//"+view+"[contains(@content-desc,'"+name+"')]");
	}
	/***
	* xpath根据text查找元素
	* @param view的类型
	* @param text的内容
	* @return
	*/
	public WebElement getViewbyXathwithtext(String view,String name){
	return this.driver.findElementByXPath("//"+view+"[contains(@text,'"+name+"')]");
	}

}

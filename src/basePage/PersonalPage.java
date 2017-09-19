package basePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import baseInit.BaseInit;

public class PersonalPage extends BaseInit {
	public PersonalPage(AppiumDriver<AndroidElement> driver){
		this.driver = driver;
	}
	//��¼ע�����
	By loginEntry = By.id("com.autonavi.minimap:id/tvNickName");
	By back = By.name("����");
	By setting = By.name("����");
	
	
	public WebElement getloginEntry(){
		return this.driver.findElement(loginEntry);
	}
	public WebElement getback(){
		return this.driver.findElement(back);
	}
	public WebElement getSetting(){
		return this.driver.findElement(setting);
	}
	
}

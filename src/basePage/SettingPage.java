package basePage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import findBy.GetElement;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import baseInit.BaseInit;

public class SettingPage extends BaseInit {
	
	public SettingPage(AppiumDriver<AndroidElement> driver){
		this.driver = driver;
	}
	
	By settingTitle = By.id("com.autonavi.minimap:id/title_title");
	By back = By.name("����");
	By mapSetting = By.name("��ͼ����");
	By naviSetting = By.name("��������");
	By citySetting = By.name("�����л�");
	
	By cle = By.name("��ջ���");
	By cleBtn = By.name("�������");
	
	By about = By.name("���ڸߵµ�ͼ");
	
	public WebElement getSettingTitle(){
		return this.driver.findElement(settingTitle);
	}
	public WebElement getback(){
		return this.driver.findElement(back);
	}
	public WebElement getmapSetting(){
		return this.driver.findElement(mapSetting);
		
	}public WebElement getnaviSetting(){
		return this.driver.findElement(naviSetting);
		
	}public WebElement getcitySetting(){
		return this.driver.findElement(citySetting);
		
	}public WebElement getcle(){
		return this.driver.findElement(cle);
		
	}public WebElement getcleBtn(){
		return this.driver.findElement(cleBtn);
	}
		
	public void clickThefrist(){
		String classname = "android.widget.TextView";
		int index = 2;
		GetElement getElement = new GetElement(driver);
		List<MobileElement> fristBtn = getElement.getElementsByClassAndIndex(classname, index);
		fristBtn.get(0).click();
	}
	//�建��
	public void clearCache(){
		getcle().click();
		getcleBtn().click();
	}
}

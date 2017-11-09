package basePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import baseInit.BaseInit;

public class SettingPage extends BaseInit {
	
	public SettingPage(AppiumDriver<AndroidElement> driver){
		this.driver = driver;
	}
	
	By settingTitle = By.id("com.autonavi.minimap:id/title_title");
	By back = By.name("返回");
	By mapSetting = By.name("地图设置");
	By naviSetting = By.name("导航设置");
	By citySetting = By.name("城市切换");
	By cle = By.name("清空缓存");
	By cleBtn = By.name("立即清空");
	
	By about = By.name("关于高德地图");
	
	public WebElement getSettingTitle() {
		return this.driver.findElement(settingTitle);
	}

	public WebElement getback() {
		return this.driver.findElement(back);
	}

	public WebElement getmapSetting() {
		return this.driver.findElement(mapSetting);
	}

	public WebElement getnaviSetting() {
		return this.driver.findElement(naviSetting);
	}

	public WebElement getcitySetting() {
		return this.driver.findElement(citySetting);
	}

	public WebElement getcle() {
		return this.driver.findElement(cle);

	}

	public WebElement getcleBtn() {
		return this.driver.findElement(cleBtn);
	}
	
	By mapSkin1 = By.xpath("//android.widget.TextView[@resource-id='com.autonavi.minimap:id/map_skin_item_0_fl']");
	public WebElement getSkin1() {
		return this.driver.findElement(mapSkin1);
	}
	
	//清缓存
	public void clearCache(){
		getcle().click();
		getcleBtn().click();
	}
}

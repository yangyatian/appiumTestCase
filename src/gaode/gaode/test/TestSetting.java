package gaode.gaode.test;

import static org.testng.Assert.*;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import basePage.HomePage;
import basePage.SettingPage;
import baseInit.BaseInit;

public class TestSetting extends BaseInit {
	@BeforeMethod
	public void beforeMethod() {
		HomePage homepage = new HomePage(driver);
		homepage.toSetting();
	}

	@Test
	public void testSettingMap() {
		SettingPage setting = new SettingPage(driver);
		setting.getmapSetting().click();
		assertEquals(setting.getSettingTitle().getText(), "��ͼ����");
	}
	
	@Test
	//��ͼƤ������
	public void testSettingMap2() {
		SettingPage setting = new SettingPage(driver);
		setting.getmapSetting().click();
		setting.getSkin1().click();
	}

	@AfterMethod
	public void afterMethod() {
		//driver.quit();
	}

}

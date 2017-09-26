package gaode.gaode.test;

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
		setting.clickThefrist();
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}

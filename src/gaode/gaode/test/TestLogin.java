package gaode.gaode.test;

import static org.testng.Assert.*;
import org.testng.annotations.*;
import baseInit.BaseInit;
import basePage.HomePage;
import basePage.LoginPage;
import basePage.PersonalPage;

public class TestLogin extends BaseInit {
	String username = "13436975946";
	String pwd = "298896";
	String Rpwd = "298890";
	String userLoginName = "amap_129284383";
	
	@BeforeMethod
	public void beforeTestcase(){
		HomePage homePage = new HomePage(driver);
		homePage.toLoginPage();
	}
	
	@Test
	// 正常登录
	public void testLogin1() throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginStep(username, pwd);
		loginPage.localdataAlert();
		//登录后自动跳转到上一页
		PersonalPage persionalPage = new PersonalPage(driver);
		assertTrue(persionalPage.getloginEntry().getText().equals(userLoginName));
		//退出登录
		loginPage.logoutStep();
	}
	
	@Test
	// 非正常登录
	public void testLogin2() throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginStep(username, Rpwd);
		Thread.sleep(1000);
		assertTrue(loginPage.loginPageTitle().getText().equals("帐号登录"));
	}
	
	@AfterMethod
	public void afterTestcase(){		
		HomePage homePage = new HomePage(driver);
		homePage.backToHome();
	}
}

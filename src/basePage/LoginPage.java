package basePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import baseInit.BaseInit;
import com.appAction.Action;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

public class LoginPage extends BaseInit{
	
	public LoginPage(AppiumDriver<AndroidElement> driver){
		this.driver = driver;
	}
	By back = By.name("返回");
	By register = By.name("注册");
	By userNameInput = By.id("com.autonavi.minimap:id/accout");
	By pwdInput = By.id("com.autonavi.minimap:id/psw");
	By loginSubmit = By.id("com.autonavi.minimap:id/phonelogin");
	By otherLogin = By.name("其他方式登录");
	By ForgotPwd = By.name("找回密码");
	
	public WebElement getback(){
		return this.driver.findElement(back);
	}
	public WebElement getRegister(){
		return this.driver.findElement(register);
	}
	public WebElement getUserNameInput(){
		return this.driver.findElement(userNameInput);
	}
	public WebElement getPwdInput(){
		return this.driver.findElement(pwdInput);
	}
	public WebElement getLoginSubmit(){
		return this.driver.findElement(loginSubmit);
	}
	public WebElement getotherLogin(){
		return this.driver.findElement(otherLogin);
	}
	public WebElement getForgotPwd(){
		return this.driver.findElement(ForgotPwd);
	}
	//本地使用记录alert，不一定弹出
	By yesINeed = By.id("com.autonavi.minimap:id/button2");
	
	public WebElement getYesINeedBtn(){
		return this.driver.findElement(yesINeed);
	}

	//登录步骤
	public void loginStep(String username, String pwd){
		getUserNameInput().sendKeys(username);
		getPwdInput().sendKeys(pwd);
		getLoginSubmit().click();
}	
	//本地使用记录alert确认，不一定弹出
	public void localdataAlert(){
		if(getYesINeedBtn().isDisplayed()){
			getYesINeedBtn().click();
		}else{
			System.out.println("未弹出数据确认警告");
		}
	}
	
	By logoutBtn = By.id("com.autonavi.minimap:id/exitSys");
	By logoutConf = By.id("com.autonavi.minimap:id/button2");
	
	public WebElement getLogoutBtn(){
		return this.driver.findElement(logoutBtn);
	}
	public WebElement getlogoutConf(){
		return this.driver.findElement(logoutConf);
	}
	//登出步骤
	public void logoutStep() throws InterruptedException{
		PersonalPage personalPage = new PersonalPage(driver);
		personalPage.getloginEntry().click();
		Thread.sleep(1000);
		Action action = new Action(this.driver);
		action.slideUPs();
		getLogoutBtn().click();
		getlogoutConf().click();
		Thread.sleep(1000);
	}
	
	//验证是否登录并退出登录
	public void checkLoginPage(String userLoginName) throws InterruptedException{
		PersonalPage personalPage = new PersonalPage(driver);
		if(personalPage.getloginEntry().getText().contains(userLoginName)){
			System.out.println("已登录，进行登出操作");
			logoutStep();
		}else{
			System.out.println("未登录，可继续登录用例执行");
		}
	}
	//登录页面title
	By loginPageTit = By.name("帐号登录");
	public WebElement loginPageTitle() {
		return driver.findElement(loginPageTit);
	}

}

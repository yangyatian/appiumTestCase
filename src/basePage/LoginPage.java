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
	By back = By.name("����");
	By register = By.name("ע��");
	By userNameInput = By.id("com.autonavi.minimap:id/accout");
	By pwdInput = By.id("com.autonavi.minimap:id/psw");
	By loginSubmit = By.id("com.autonavi.minimap:id/phonelogin");
	By otherLogin = By.name("������ʽ��¼");
	By ForgotPwd = By.name("�һ�����");
	
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
	//����ʹ�ü�¼alert����һ������
	By yesINeed = By.id("com.autonavi.minimap:id/button2");
	
	public WebElement getYesINeedBtn(){
		return this.driver.findElement(yesINeed);
	}

	//��¼����
	public void loginStep(String username, String pwd){
		getUserNameInput().sendKeys(username);
		getPwdInput().sendKeys(pwd);
		getLoginSubmit().click();
}	
	//����ʹ�ü�¼alertȷ�ϣ���һ������
	public void localdataAlert(){
		if(getYesINeedBtn().isDisplayed()){
			getYesINeedBtn().click();
		}else{
			System.out.println("δ��������ȷ�Ͼ���");
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
	//�ǳ�����
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
	
	//��֤�Ƿ��¼���˳���¼
	public void checkLoginPage(String userLoginName) throws InterruptedException{
		PersonalPage personalPage = new PersonalPage(driver);
		if(personalPage.getloginEntry().getText().contains(userLoginName)){
			System.out.println("�ѵ�¼�����еǳ�����");
			logoutStep();
		}else{
			System.out.println("δ��¼���ɼ�����¼����ִ��");
		}
	}
	//��¼ҳ��title
	By loginPageTit = By.name("�ʺŵ�¼");
	public WebElement loginPageTitle() {
		return driver.findElement(loginPageTit);
	}

}

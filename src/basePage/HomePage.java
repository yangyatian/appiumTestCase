package basePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.appAction.Action;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import baseInit.BaseInit;

public class HomePage extends BaseInit{
	String userLoginName = "amap_129284383";
	public HomePage(AppiumDriver<AndroidElement> driver){
		this.driver = driver;
	}
	//�˺�����
	By accountSetting = By.name("�ʺ�����");
	//���������
	By searchInput = By.xpath("//android.widget.TextView[@resource-id='com.autonavi.minimap:id/btn_search']");
	By message = By.name("��Ϣ");
	By closeMessage = By.name("�ر���Ϣ����");
	By maplayers = By.id("com.autonavi.minimap:id/btn_maplayers");
	By tmc = By.xpath("//android.widget.LinearLayout[1]/android.widget.LinearLayout[0]/android.widget.ImageView[0]");
	By taxt = By.xpath("//android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.ImageView[0]");
	By bicycle = By.xpath("//android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.ImageView[0]");
	By news = By.xpath("//android.widget.LinearLayout[1]/android.widget.LinearLayout[3]/android.widget.ImageView[0]");
	By location = By.xpath("//android.view.View/android.view.View/android.widget.ImageButton");
	By line = By.id("com.autonavi.minimap:id/tv_path_txt");
	By zoomIn = By.name("�Ŵ�ͼ��");
	By zoomOut = By.name("��Сͼ��");
	By nearby = By.name("̽������");
	
	public WebElement getAccSetting(){
		return this.driver.findElement(accountSetting);
	}
	public WebElement getSearchInput(){
		return this.driver.findElement(searchInput);
	}
	public WebElement getMessage(){
		return this.driver.findElement(message);
	}
	public WebElement getCloseMessage(){
		return this.driver.findElement(closeMessage);
	}
	public WebElement getMaplayers(){
		return this.driver.findElement(maplayers);
	}
	public WebElement getTMC(){
		return this.driver.findElement(tmc);
	}
	public WebElement getTaxt(){
		return this.driver.findElement(taxt);
	}
	public WebElement getBicycle(){
		return this.driver.findElement(bicycle);
	}
	public WebElement getNews(){
		return this.driver.findElement(news);
	}
	public WebElement getlocation(){
		return this.driver.findElement(location);
	}
	public WebElement getline(){
		return this.driver.findElement(line);
	}
	public WebElement getZoomIn(){
		return this.driver.findElement(zoomIn);
	}
	public WebElement getZoomout(){
		return this.driver.findElement(zoomOut);
	}
	public WebElement getNearby(){
		return this.driver.findElement(nearby);
	}
	//��ת����¼ҳ��
	public void toLoginPage(){
		getAccSetting().click();
		LoginPage loginPage = new LoginPage(driver);
		try {
			loginPage.checkLoginPage(userLoginName);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		PersonalPage personalPage = new PersonalPage(driver);
		personalPage.getloginEntry().click();
	}
	public void backToHome(){
		String backId = "com.autonavi.minimap:id/title_btn_left";
		Action action = new Action(driver);
		action.clickIfExitId(backId);
	}
	//��ת������
	public void toSetting(){
		getAccSetting().click();
		PersonalPage personalPage = new PersonalPage(driver);
		personalPage.getSetting().click();
	}
}

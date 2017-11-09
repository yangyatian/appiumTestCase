package gaode.gaode.test;

import static org.testng.Assert.assertTrue;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import findBy.GetElement;

import baseInit.BaseInit;
import basePage.HomePage;

public class TestRodeLine extends BaseInit {
	
	String addr1 = "������";
	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {
	}
	@Test
	// ����λ����֤���	
	public void testSearchLocation() throws InterruptedException {		
		this.SearchLocation(addr1);
		Thread.sleep(1000);
		GetElement getElement = new GetElement(driver);
		AndroidElement addrlist = getElement.getElementsByIdAndIndex("com.autonavi.minimap:id/list_text_2001", 0);
		assertTrue(addrlist.getText().contains(addr1));
	}
	@Test
	// ����λ����֤���
	public void testDrivingDirections() throws InterruptedException {
		this.SearchLocation(addr1);
		Thread.sleep(1000);
		GetElement getElement = new GetElement(driver);
		AndroidElement routeBtn = getElement.getElementsByIdAndIndex("com.autonavi.minimap:id/route_btn", 0);
		routeBtn.click();
		Thread.sleep(2000);
		driver.findElement(By.name("�ݳ�")).click();
		driver.findElement(By.name("��ʼ����")).click();
		Thread.sleep(2000);
		assertTrue(driver.findElement(By.name("�˳�")).isDisplayed());
		
	}
	
	
	//�������裬����Ϊ������ַ
	public void SearchLocation(String addr1) throws InterruptedException{
		HomePage homePage = new HomePage(driver);
		homePage.getSearchInput().click();
		this.driver.findElement(By.id("com.autonavi.minimap:id/search_text")).sendKeys(addr1);
		Thread.sleep(2000);		
		this.driver.findElement(By.name("����")).click();		
	}

}

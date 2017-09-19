package com.appAction;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;

import baseInit.BaseInit;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

public class Action extends BaseInit {
	
	public Action(AppiumDriver<AndroidElement> driver){
		this.driver = driver;
	}
	
	public void waitForFindElement(){
		this.driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	}
	//��ȡ��ǰʱ�䲢��ͼ
	public String getScreen(){
		String fileRoute = "//testing/testPicture/";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
		String picname = fileRoute + df.format(new Date()).toString() + ".png";
		File screen = this.driver.getScreenshotAs(OutputType.FILE);
		System.out.println(picname);
		File screenFile = new File(picname);
		try {
			FileUtils.copyFile(screen, screenFile);
			String time = df.format(new Date()).toString();
			System.out.println("��ǰʱ��" + time);
			return time;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		

	}
	/***
	* �ϻ�1/4��Ļ
	*/
	public void slideUP() {
		int x = driver.manage().window().getSize().width;
		int y = driver.manage().window().getSize().height;
		this.driver.swipe(x / 2, y / 3 * 2, x / 2, y / 3 * 1, 0);
	}
	/***
	* �ϻ���Ļ
	*/
	public void slideUPs() {
		int x = driver.manage().window().getSize().width;
		int y = driver.manage().window().getSize().height;
		this.driver.swipe(x / 2, y / 10 * 2, x / 2, y / 10 * 1, 0);
	}
	/***
	* �»�1/4��Ļ
	*/
	public void slideDown() {
		int x = driver.manage().window().getSize().width;
		int y = driver.manage().window().getSize().height;
		this.driver.swipe(x / 2, y / 3 * 1, x / 2, y / 3 * 2, 0);
	}
	/***
	* ��1/2��Ļ
	*/
	public void slideLeft() {
		int x = driver.manage().window().getSize().width;
		int y = driver.manage().window().getSize().height;
		this.driver.swipe(x / 4 * 3, y / 2, x / 4 * 1, y / 2, 0);
	}
	/***
	* �һ�1/2��Ļ
	*/
	public void slideRight() {
		int x = driver.manage().window().getSize().width;
		int y = driver.manage().window().getSize().height;
		this.driver.swipe(x / 4 * 1, y / 2, x / 4 * 3, y / 2, 0);
	}
	/***
	* �����ϻ�
	* @param ��������ҿ�ȵİٷֱ�(1-99֮��)
	*/
	public void slideUP(int i) {
		Assert.assertFalse("�ϻ���ȴ������", i <= 0 || i >= 100);
		int x = driver.manage().window().getSize().width;
		int y = driver.manage().window().getSize().height;
		this.driver.swipe(x / 10 * i, y / 3 * 2, x / 10 * i, y / 3 * 1, 0);
	}
	/***
	* �����»�
	* @param ��������ҿ�ȵİٷֱ�(1-99֮��)
	*/
	public void slideDown(int i) {
		Assert.assertFalse("�»���ȴ������", i <= 0 || i >= 100);
		int x = driver.manage().window().getSize().width;
		int y = driver.manage().window().getSize().height;
		this.driver.swipe(x / 10 * i, y / 3 * 1, x / 10 * i, y / 3 * 2, 0);
	}
	/***
	* ������
	* @param ������ϵ��¿�ȵİٷֱ�(1-99֮��)
	*/
	public void slideLeft(int i) {
		Assert.assertFalse("�󻬿�ȴ������", i <= 0 || i >= 100);
		int x = driver.manage().window().getSize().width;
		int y = driver.manage().window().getSize().height;
		this.driver.swipe(x / 4 * 3, y / 10 * i, x / 4 * 2, y / 10 * i, 0);
	}
	/***
	* �����һ�
	* @param ������ϵ��¿�ȵİٷֱ�(1-99֮��)
	*/
	public void slideRight(int i) {
		Assert.assertFalse("�󻬿�ȴ������", i <= 0 || i >= 100);
		int x = driver.manage().window().getSize().width;
		int y = driver.manage().window().getSize().height;
		this.driver.swipe(x / 4 * 2, y / 10 * i, x / 4 * 3, y / 10 * i, 0);
	}
	/***
	* �л�WEBҳ�����Ԫ��
	*/
	public void switchtoWeb(){
		try {
			Set<String> contextNames = this.driver.getContextHandles();
			for (String contextName : contextNames) {
				// ���ڷ��ر���app��NATIVE_APP����WEBVIEW��������߶��о��ǻ����App
				if (contextName.contains("WEBVIEW")
						|| contextName.contains("webview")) {
					this.driver.context(contextName);
					System.out.println("��ת��webҳ ��ʼ����webҳ��");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//���ݴ���id����������ڵ�idԪ��
	public void clickIfExitId(String id) {
	    try {
	      driver.findElementById(id).click();
	    } catch (NoSuchElementException e) {
	      System.out.println("Can not find element with id " + id + " . Skip!");
	    }
	  }

	  public void clickIfExitIds(String[] ids) {
	    for (String id : ids) {
	      clickIfExitId(id);
	    }
	  }
}

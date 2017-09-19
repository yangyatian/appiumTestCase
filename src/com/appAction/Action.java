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
	//获取当前时间并截图
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
			System.out.println("当前时间" + time);
			return time;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		

	}
	/***
	* 上滑1/4屏幕
	*/
	public void slideUP() {
		int x = driver.manage().window().getSize().width;
		int y = driver.manage().window().getSize().height;
		this.driver.swipe(x / 2, y / 3 * 2, x / 2, y / 3 * 1, 0);
	}
	/***
	* 上滑屏幕
	*/
	public void slideUPs() {
		int x = driver.manage().window().getSize().width;
		int y = driver.manage().window().getSize().height;
		this.driver.swipe(x / 2, y / 10 * 2, x / 2, y / 10 * 1, 0);
	}
	/***
	* 下滑1/4屏幕
	*/
	public void slideDown() {
		int x = driver.manage().window().getSize().width;
		int y = driver.manage().window().getSize().height;
		this.driver.swipe(x / 2, y / 3 * 1, x / 2, y / 3 * 2, 0);
	}
	/***
	* 左滑1/2屏幕
	*/
	public void slideLeft() {
		int x = driver.manage().window().getSize().width;
		int y = driver.manage().window().getSize().height;
		this.driver.swipe(x / 4 * 3, y / 2, x / 4 * 1, y / 2, 0);
	}
	/***
	* 右滑1/2屏幕
	*/
	public void slideRight() {
		int x = driver.manage().window().getSize().width;
		int y = driver.manage().window().getSize().height;
		this.driver.swipe(x / 4 * 1, y / 2, x / 4 * 3, y / 2, 0);
	}
	/***
	* 特殊上滑
	* @param 传入从左到右宽度的百分比(1-99之间)
	*/
	public void slideUP(int i) {
		Assert.assertFalse("上滑宽度传入错误", i <= 0 || i >= 100);
		int x = driver.manage().window().getSize().width;
		int y = driver.manage().window().getSize().height;
		this.driver.swipe(x / 10 * i, y / 3 * 2, x / 10 * i, y / 3 * 1, 0);
	}
	/***
	* 特殊下滑
	* @param 传入从左到右宽度的百分比(1-99之间)
	*/
	public void slideDown(int i) {
		Assert.assertFalse("下滑宽度传入错误", i <= 0 || i >= 100);
		int x = driver.manage().window().getSize().width;
		int y = driver.manage().window().getSize().height;
		this.driver.swipe(x / 10 * i, y / 3 * 1, x / 10 * i, y / 3 * 2, 0);
	}
	/***
	* 特殊左滑
	* @param 传入从上到下宽度的百分比(1-99之间)
	*/
	public void slideLeft(int i) {
		Assert.assertFalse("左滑宽度传入错误", i <= 0 || i >= 100);
		int x = driver.manage().window().getSize().width;
		int y = driver.manage().window().getSize().height;
		this.driver.swipe(x / 4 * 3, y / 10 * i, x / 4 * 2, y / 10 * i, 0);
	}
	/***
	* 特殊右滑
	* @param 传入从上到下宽度的百分比(1-99之间)
	*/
	public void slideRight(int i) {
		Assert.assertFalse("左滑宽度传入错误", i <= 0 || i >= 100);
		int x = driver.manage().window().getSize().width;
		int y = driver.manage().window().getSize().height;
		this.driver.swipe(x / 4 * 2, y / 10 * i, x / 4 * 3, y / 10 * i, 0);
	}
	/***
	* 切换WEB页面查找元素
	*/
	public void switchtoWeb(){
		try {
			Set<String> contextNames = this.driver.getContextHandles();
			for (String contextName : contextNames) {
				// 用于返回被测app是NATIVE_APP还是WEBVIEW，如果两者都有就是混合型App
				if (contextName.contains("WEBVIEW")
						|| contextName.contains("webview")) {
					this.driver.context(contextName);
					System.out.println("跳转到web页 开始操作web页面");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//根据传入id连续点击存在的id元素
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

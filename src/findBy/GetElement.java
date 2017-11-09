package findBy;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

public class GetElement {
	private AppiumDriver<AndroidElement> driver;
	public static boolean flag = true;
    public static List<Error> errors = new ArrayList<>();
    
	public GetElement(AppiumDriver<AndroidElement> driver) {
		this.driver = driver;
	}
	
	/***
	 * xpath根据content-desc查找元素
	 * @param view的类型
	 * @param content-desc 的内容
	 * @return
	 */
	public WebElement getViewbyXpathWithcontentdesc(String view, String name) {
		return this.driver.findElementByXPath("//" + view + "[contains(@content-desc,'" + name + "')]");
	}
	//xpath根据content查找元素并获取该文本值
	public String getTextByXpathWithCd(String view, String name) {
		return this.driver.findElementByXPath("//" + view + "[contains(@content-desc,'" + name + "')]").getText();
	}

	/***
	 * xpath根据text查找元素
	 * @param view的类型
	 * @param text的内容
	 * @return
	 */
	public WebElement getViewbyXpathWithtext(String view, String name) {
		return this.driver.findElementByXPath("//" + view + "[contains(@text,'" + name + "')]");
	}

	// 通过classname和index定位多个元素中的一个
	public AndroidElement getElementsByClassAndIndex(String className, int index) {
		List<AndroidElement> lis = null;
		lis = driver.findElementsByClassName(className);		
		return lis.get(index);
	}
	// 通过resouceId和index定位多个元素中的一个
	public AndroidElement getElementsByIdAndIndex(String resouceId, int index) {
		List<AndroidElement> lis = null;
		lis = driver.findElementsById(resouceId);		
		return lis.get(index);
	}


    /**
     * 验证值是否相等
     * @param actual 第一个值
     * @param expected 要对比的值
     */
    public static void verifyEquals(Object actual, Object expected){
        try{
            Assert.assertEquals(actual, expected);
        }catch(Error e){
            errors.add(e);
            flag = false;
        }
    }


    /**
     * 验证值是否相等
     * @param actual 第一个值
     * @param expected 要对比的值
     * @param message 出错时候的提示消息
     */
    public static void verifyEquals(Object actual, Object expected, String message){
        try{
            Assert.assertEquals(actual, expected, message);
        }catch(Error e){
            errors.add(e);
            flag = false;
        }
    }
	
}

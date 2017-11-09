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
	 * xpath����content-desc����Ԫ��
	 * @param view������
	 * @param content-desc ������
	 * @return
	 */
	public WebElement getViewbyXpathWithcontentdesc(String view, String name) {
		return this.driver.findElementByXPath("//" + view + "[contains(@content-desc,'" + name + "')]");
	}
	//xpath����content����Ԫ�ز���ȡ���ı�ֵ
	public String getTextByXpathWithCd(String view, String name) {
		return this.driver.findElementByXPath("//" + view + "[contains(@content-desc,'" + name + "')]").getText();
	}

	/***
	 * xpath����text����Ԫ��
	 * @param view������
	 * @param text������
	 * @return
	 */
	public WebElement getViewbyXpathWithtext(String view, String name) {
		return this.driver.findElementByXPath("//" + view + "[contains(@text,'" + name + "')]");
	}

	// ͨ��classname��index��λ���Ԫ���е�һ��
	public AndroidElement getElementsByClassAndIndex(String className, int index) {
		List<AndroidElement> lis = null;
		lis = driver.findElementsByClassName(className);		
		return lis.get(index);
	}
	// ͨ��resouceId��index��λ���Ԫ���е�һ��
	public AndroidElement getElementsByIdAndIndex(String resouceId, int index) {
		List<AndroidElement> lis = null;
		lis = driver.findElementsById(resouceId);		
		return lis.get(index);
	}


    /**
     * ��ֵ֤�Ƿ����
     * @param actual ��һ��ֵ
     * @param expected Ҫ�Աȵ�ֵ
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
     * ��ֵ֤�Ƿ����
     * @param actual ��һ��ֵ
     * @param expected Ҫ�Աȵ�ֵ
     * @param message ����ʱ�����ʾ��Ϣ
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

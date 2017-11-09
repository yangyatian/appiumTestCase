package cn.httpApitest.www.HttpApitest;

import static org.testng.Assert.assertTrue;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import cn.httpApitest.www.request.*;
import dataProvider.TestData;

public class TestHttpApi extends TestData {
	
	String url = "http://www.sojson.com/open/api/weather/json.shtml?city=����";
	String loginUrl = "http://localhost/wordpress/wp-login.php";
	String afterUrl = "http://localhost/wordpress/wp-admin/";
	String loginBody = "log=yangyatian&pwd=timyang1840&wp-submit=%E7%99%BB%E5%BD%95&redirect_to=http%3A%2F%2Flocalhost%2Fwordpress%2Fwp-admin%2F&testcookie=1";
	String assert1 = "��¼";
	String [][] paraList = {
			{"log","yangyatian"},
			{"pwd","timyang1840"},
			{"redirect_to","http://localhost/wordpress/wp-admin/"},
			{"testcookie","1"},
			{"wp-submit","��¼"}
	};

	
	//@Test
	public void testGet() throws ClientProtocolException, IOException { //��httpclient����get������֤ҳ���ַ�
		HttpRequest httpRequest = new HttpRequest();		
		httpRequest.get(loginUrl,assert1);
	}
	
	@Test
	// row data����
	public void testPostByHttpClient() throws ClientProtocolException,IOException { 		
		HttpRequest httpRequest = new HttpRequest();
		httpRequest.postWithCookies(loginUrl, loginBody);
	}

	//@Test
	public void testGetbyJsoup() {
		HttpRequestByJsoup httpRequest2 = new HttpRequestByJsoup();
		assertTrue(httpRequest2.testGetDoc(url).toString().contains("����"));
	}
	
	// @Test
	public void testPostbyJsoup() {
		HttpRequestByJsoup httpRequest2 = new HttpRequestByJsoup();
		assertTrue(httpRequest2.testJsop2(loginUrl, afterUrl, paraList)
				.toString().contains("�Ǳ���"));
	}
	
		
	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {
	}

}

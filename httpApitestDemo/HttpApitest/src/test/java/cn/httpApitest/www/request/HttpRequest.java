package cn.httpApitest.www.request;

import static org.testng.Assert.assertTrue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.Reporter;

public class HttpRequest {
	static CookieStore cookieStore = null;
	// get请求（参数在url中）
	public void get(String url, String assert1) throws ClientProtocolException,IOException {
		CloseableHttpClient httpClient = null;
		HttpGet httpGet = null;
		httpClient = HttpClients.createDefault();// 创建默认的httpClient实例
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(20000).setConnectTimeout(20000).build();// 设置超时
		httpGet = new HttpGet(url); // 创建GET方法的实例，并传入待连接的地址
		httpGet.setConfig(requestConfig);// 设置超时
		httpGet.addHeader("content-type","application/x-www-form-urlencoded; charset=UTF-8");// 设置请求头
		CloseableHttpResponse response = httpClient.execute(httpGet);// 执行请求
		HttpEntity httpEntity = response.getEntity();// 获取响应实体
		String getReturn = EntityUtils.toString(httpEntity, "utf-8");// 字符形式返回内容流
		getReturn = getReturn.replaceAll("[^\\u4e00-\\u9fa5]", "");// 只匹配汉字

		if (getReturn != null) {
			System.out.println("接口响应：" + getReturn + "\n"); // 打印响应内容
			EntityUtils.consume(httpEntity);// 关闭内容流
		}

		try {
			if (httpGet != null) {
				httpGet.releaseConnection();
			}
			if (httpClient != null) {
				httpClient.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(getReturn.contains(assert1));// 断言
		//Reporter.log("getTest1");

	}

	// get请求（单独传参数）
	public void get(String url, Map<String, String> params, String assert2) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = null;
		HttpGet httpGet = null;
	
			httpClient = HttpClients.createDefault();
			RequestConfig requestConfig = RequestConfig.custom()
					.setSocketTimeout(20000).setConnectTimeout(20000).build();
			String ps = "";
			for (String pKey : params.keySet()) {
				if (!"".equals(ps)) {
					ps = ps + "&";
				}
				ps = pKey + "=" + params.get(pKey);
			}
			if (!"".equals(ps)) {
				url = url + "?" + ps;
			}
			httpGet = new HttpGet(url);
			httpGet.setConfig(requestConfig);
			httpGet.addHeader("content-type","application/x-www-form-urlencoded; charset=UTF-8");// 设置请求头
			CloseableHttpResponse response = httpClient.execute(httpGet);
			HttpEntity httpEntity = response.getEntity();
			String getReturn = EntityUtils.toString(httpEntity, "utf-8");// 字符形式返回内容流
			getReturn = getReturn.replaceAll("[^\\u4e00-\\u9fa5]", "");// 只匹配汉字

			if (getReturn != null) {
				System.out.println("接口响应：" + getReturn + "\n"); // 打印响应内容
				EntityUtils.consume(httpEntity);// 关闭内容流
			}
	
			try {
				if (httpGet != null) {
					httpGet.releaseConnection();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			assertTrue(getReturn.contains(assert2));// 断言
			Reporter.log("getTest2");
		}


	// post请求，表单键值对提交参数
	public void post(String url, Map<String, String> params)throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault(); 
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();		
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requestConfig);
		
		// 设置请求头
		httpPost.addHeader("content-type","application/x-www-form-urlencoded; charset=UTF-8");	
		List<NameValuePair> ps = new ArrayList<NameValuePair>();

		for (String pKey : params.keySet()) {
			ps.add(new BasicNameValuePair(pKey, params.get(pKey)));
		}
		httpPost.setEntity(new UrlEncodedFormEntity(ps));
		CloseableHttpResponse response = httpClient.execute(httpPost);//执行请求

		HttpEntity httpEntity = response.getEntity();//获取响应
		String postResult = EntityUtils.toString(httpEntity, "UTF-8");// 读入内容流，并以字符串形式返回
		String responses = unicodeToString(postResult); // 把Unicode编码转中文
		responses = responses.replaceAll("[^\\u4e00-\\u9fa5\\，]", "");// 匹配汉字和逗号

		if (responses != null) {
			System.out.println("接口响应：" + responses + "\n"); // 打印响应内容
			EntityUtils.consume(httpEntity);// 关闭内容流
		}
		try {
			if (httpPost != null) {
				httpPost.releaseConnection();
			}
			if (httpClient != null) {
				httpClient.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Reporter.log("postTest1");
	}

	// POST请求(RAW参数传递)
	public void post(String url, String body) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = null;
		//CookieStore cookieStore = new BasicCookieStore();
		try {
			httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();			
			httpPost = new HttpPost(url);
			httpPost.addHeader("Cookie","wordpress_test_cookie=WP+Cookie+check");
			httpPost.setConfig(requestConfig);
			httpPost.setEntity(new StringEntity(body));
			CloseableHttpResponse response = httpClient.execute(httpPost);
			
		/*	List<Cookie> cookies = cookieStore.getCookies();
			for (int i = 0; i < cookies.size(); i++) {
				System.out.println("Local cookie: " + cookies.get(i));
			}*/
			
			HttpEntity httpEntity = response.getEntity();
			System.out.println(EntityUtils.toString(httpEntity, "utf-8"));
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (httpPost != null) {
					httpPost.releaseConnection();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// POST请求(RAW参数传递)
		public void postWithCookies(String url, String body) {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost httpPost = null;
			CookieStore cookieStore = new BasicCookieStore();
			try {
				httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
				RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();			
				httpPost = new HttpPost(url);

				httpPost.setConfig(requestConfig);
				httpPost.setEntity(new StringEntity(body));
				CloseableHttpResponse response = httpClient.execute(httpPost);
				response.setHeader("Cookie","wordpress_test_cookie=WP+Cookie+check");
				List<Cookie> cookies = cookieStore.getCookies();
				for (int i = 0; i < cookies.size(); i++) {
					System.out.println("Local cookie: " + cookies.get(i).toString());
				}
				
				HttpEntity httpEntity = response.getEntity();
				System.out.println(EntityUtils.toString(httpEntity, "utf-8"));
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (httpPost != null) {
						httpPost.releaseConnection();
					}
					if (httpClient != null) {
						httpClient.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	

	//Unicode转中文  
	 public static String unicodeToString(String str) {
		 
	        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
	        Matcher matcher = pattern.matcher(str);
	        char ch;
	        while (matcher.find()) {
	            ch = (char) Integer.parseInt(matcher.group(2), 16);
	            str = str.replace(matcher.group(1), ch+"" );
	        }
	        return str;
	    }
	 
	
}

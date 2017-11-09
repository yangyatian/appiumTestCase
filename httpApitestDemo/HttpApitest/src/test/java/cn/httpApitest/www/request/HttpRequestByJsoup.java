package cn.httpApitest.www.request;

import java.io.IOException;
import java.util.Map;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HttpRequestByJsoup {

	// ֱ��url�д���get����
	public void JsopGet(String UrlAndParm) {
		try {
			Document doc = Jsoup.connect(UrlAndParm).get();
			System.out.println(doc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// �ֱ𴫲�get����
	public void JsopGet(String getUrl, String par1, String par2) {
		try {
			Connection conn = Jsoup.connect(getUrl);
			conn.data(par1, par2);// ����
			Document doc = conn.get();
			System.out.println(doc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// get����url�д��Σ�����doc
	public Document testGetDoc(String url) {
		try {
			Document doc = Jsoup.connect(url).get();
			return (doc);
		} catch (IOException e) {
			e.printStackTrace();
			return (null);
		}
	}

	// post�������鴫��
	public void JsopPost(String postUrl, String param1, String param2) {
		try {
			Connection conn = Jsoup.connect(postUrl);
			conn.data(param1, param2);

			conn.timeout(30000);
			Document doc = conn.post();
			System.out.println(doc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ����cookies��post����
	public void testJsop(String postUrl, String param1, String param2, String afterUrl) {
		try {
			Connection conn = Jsoup.connect(postUrl);
			conn.data(param1, param2);

			conn.timeout(30000);
			conn.method(Method.POST);
			Response response = conn.execute();
			Map<String, String> cookies = response.cookies();
			Document doc = Jsoup.connect(afterUrl).cookies(cookies)
					.timeout(30000).get();
			System.out.println(doc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ����cookie��post�����װ,����Ϊ��ά����
	public Document testJsop2(String postUrl, String afterUrl, String[][] paraList) {

		try {
			Connection conn = Jsoup.connect(postUrl);
			for (String[] item : paraList) {
				String keys = item[0];
				String values = item[1];
				conn.data(keys, values);
			}
			conn.timeout(30000);
			conn.method(Method.POST);
			Response response = conn.execute();
			Map<String, String> cookies = response.cookies();
			Document doc = Jsoup.connect(afterUrl).cookies(cookies)
					.timeout(30000).get();
			return (doc);
		} catch (IOException e) {
			e.printStackTrace();
			return (null);
		}
	}

}

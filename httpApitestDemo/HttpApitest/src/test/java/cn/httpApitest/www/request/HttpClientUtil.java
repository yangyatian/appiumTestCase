package cn.httpApitest.www.request;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;



public class HttpClientUtil {

   
    /**
     * ����GET����HTTP����K-V��ʽ
     * @param url
     * @param params
     * @author Charlie.chen��
     * @return
     */
    public static String doGet(String url,Map<String, Object> params) {

        String apiUrl = url;
        StringBuffer param = new StringBuffer();
        int i = 0;
        for (String key : params.keySet()) {
            if (i == 0)
                param.append("?");
            else
                param.append("&");
            param.append(key).append("=").append(params.get(key));
            i++;
        }
        apiUrl += param;

        // ����Ĭ�ϵ�HttpClientʵ��.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {

            // ����һ��get���󷽷�
            HttpGet httpget = new HttpGet(apiUrl);

            // ִ��get���󣬷���response��������Ӧ����, ���а�����״̬��Ϣ�ͷ��������ص�����
            CloseableHttpResponse httpResponse = httpclient.execute(httpget);

            // ʹ����Ӧ����, ���״̬��, ��������
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // ʹ����Ӧ�����ȡ��Ӧʵ��
                HttpEntity entity = httpResponse.getEntity();
                // ����Ӧʵ��תΪ�ַ���
                String response = EntityUtils.toString(entity, "utf-8");
                return response;

            } else {
                // log.error("����ʧ��"+statusCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // �ر�����, ���ͷ���Դ
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * ����POST����HTTP����K-V��ʽ
     * @param url
     * @param params
     * @author Charlie.chen
     * @return
     */
    public static String doPost(String url, Map<String, String> params) {

        // ����Ĭ�ϵ�HttpClientʵ��.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {

            // ����һ��get���󷽷�
            HttpPost httppost = new HttpPost(url);

            // List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            // parameters.add(new BasicNameValuePair("username", userName));
            // parameters.add(new BasicNameValuePair("password", password));

            // ����post����Ĳ���
            // ����һ��NameValuePair���飬���ڴ洢�����͵Ĳ���
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = params.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, String> elem = (Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
            }
            if (list.size() > 0) {
                httppost.setEntity(new UrlEncodedFormEntity(list, "utf-8"));
            }


            // httppost.setHeader("Content-type","application/json,charset=utf-8");
            // httppost.setHeader("Accept", "application/json");


            // ִ��post���󣬷���response��������Ӧ����, ���а�����״̬��Ϣ�ͷ��������ص�����
            CloseableHttpResponse httpResponse = httpclient.execute(httppost);

            // ʹ����Ӧ����, ���״̬��, ��������
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // ʹ����Ӧ�����ȡ��Ӧʵ��
                HttpEntity entity = httpResponse.getEntity();
                // ����Ӧʵ��תΪ�ַ���
                String response = EntityUtils.toString(entity, "utf-8");
                return response;

            } else {
                // log.error("����ʧ��"+statusCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // �ر�����, ���ͷ���Դ
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


     /**
     * ����post����,���ϴ��ļ�
     */
     public String postWithFile(String url,String filePath) {
         CloseableHttpClient httpclient = HttpClients.createDefault();
         try {
             HttpPost httppost = new HttpPost(url);
             FileBody bin=null;

             File file = new File(filePath);
             if (file.exists()) {
                 bin=new FileBody(file);
             }else{
                 System.out.println("Can't find " + filePath);
             }

         StringBody comment = new StringBody("A binary file of some kind",ContentType.TEXT_PLAIN);

         //����ʵ��
         HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("bin",bin).addPart("comment", comment).build();
         httppost.setEntity(reqEntity);

         //ִ��post���󣬷���response��������Ӧ����, ���а�����״̬��Ϣ�ͷ��������ص�����
         CloseableHttpResponse httpResponse = httpclient.execute(httppost);

         // ʹ����Ӧ����, ���״̬��, ��������
         int statusCode = httpResponse.getStatusLine().getStatusCode();
         if(statusCode == 200) {
             // ʹ����Ӧ�����ȡ��Ӧʵ��
             HttpEntity entity = httpResponse.getEntity();
             //����Ӧʵ��תΪ�ַ���
             String response = EntityUtils.toString(entity,"utf-8");
             return response;

         }else{
             System.out.println("����ʧ��"+statusCode);
         }

         }catch (Exception e) {
             e.printStackTrace();
         } finally {
             try {
                 // �ر�����, ���ͷ���Դ
                 httpclient.close();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
         return null;
     }

}

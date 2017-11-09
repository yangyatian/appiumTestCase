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
     * 发送GET请求（HTTP），K-V形式
     * @param url
     * @param params
     * @author Charlie.chen；
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

        // 创建默认的HttpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {

            // 定义一个get请求方法
            HttpGet httpget = new HttpGet(apiUrl);

            // 执行get请求，返回response服务器响应对象, 其中包含了状态信息和服务器返回的数据
            CloseableHttpResponse httpResponse = httpclient.execute(httpget);

            // 使用响应对象, 获得状态码, 处理内容
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // 使用响应对象获取响应实体
                HttpEntity entity = httpResponse.getEntity();
                // 将响应实体转为字符串
                String response = EntityUtils.toString(entity, "utf-8");
                return response;

            } else {
                // log.error("访问失败"+statusCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭连接, 和释放资源
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 发送POST请求（HTTP），K-V形式
     * @param url
     * @param params
     * @author Charlie.chen
     * @return
     */
    public static String doPost(String url, Map<String, String> params) {

        // 创建默认的HttpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {

            // 定义一个get请求方法
            HttpPost httppost = new HttpPost(url);

            // List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            // parameters.add(new BasicNameValuePair("username", userName));
            // parameters.add(new BasicNameValuePair("password", password));

            // 定义post请求的参数
            // 建立一个NameValuePair数组，用于存储欲传送的参数
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


            // 执行post请求，返回response服务器响应对象, 其中包含了状态信息和服务器返回的数据
            CloseableHttpResponse httpResponse = httpclient.execute(httppost);

            // 使用响应对象, 获得状态码, 处理内容
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // 使用响应对象获取响应实体
                HttpEntity entity = httpResponse.getEntity();
                // 将响应实体转为字符串
                String response = EntityUtils.toString(entity, "utf-8");
                return response;

            } else {
                // log.error("访问失败"+statusCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭连接, 和释放资源
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


     /**
     * 发送post请求,并上传文件
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

         //请求实体
         HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("bin",bin).addPart("comment", comment).build();
         httppost.setEntity(reqEntity);

         //执行post请求，返回response服务器响应对象, 其中包含了状态信息和服务器返回的数据
         CloseableHttpResponse httpResponse = httpclient.execute(httppost);

         // 使用响应对象, 获得状态码, 处理内容
         int statusCode = httpResponse.getStatusLine().getStatusCode();
         if(statusCode == 200) {
             // 使用响应对象获取响应实体
             HttpEntity entity = httpResponse.getEntity();
             //将响应实体转为字符串
             String response = EntityUtils.toString(entity,"utf-8");
             return response;

         }else{
             System.out.println("访问失败"+statusCode);
         }

         }catch (Exception e) {
             e.printStackTrace();
         } finally {
             try {
                 // 关闭连接, 和释放资源
                 httpclient.close();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
         return null;
     }

}

package com.test.springboot.general.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtil {
    
    private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);

    private static final String ENCODE = "UTF-8";

    /**
     * 发送get请求
     * 
     * @param url
     *            地址
     * @param params
     *            参数
     * @param headers
     *            请求头
     * @return
     */
    private static String doGet(String url, Map<String, String> params, Map<String, String> headers) {
        String content = null;
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        try {
            httpClient = HttpClients.createDefault();
            URIBuilder uriBuilder = new URIBuilder(url);
            if (params != null) {
                Set<Entry<String, String>> entrySet = params.entrySet();
                for (Entry<String, String> entry : entrySet) {
                    uriBuilder.setParameter(entry.getKey(), entry.getValue());
                }
            }
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            if (headers != null) {
                Set<Entry<String, String>> entrySet = headers.entrySet();
                for (Entry<String, String> entry : entrySet) {
                    httpGet.setHeader(entry.getKey(), entry.getValue());
                }
            }
//            RequestConfig requestConfig = RequestConfig.custom()
//                    .setConnectTimeout(5000) // 设置连接超时时间，单位毫秒
//                    .setConnectionRequestTimeout(5000) // 设置从connectManager(连接池)获取Connection超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的
//                    .setSocketTimeout(5000) // 请求获取数据的超时时间(即响应时间)，单位毫秒。如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用
//                    .build();
//            httpGet.setConfig(requestConfig);
            httpResponse = httpClient.execute(httpGet);
            if (httpResponse != null && httpResponse.getStatusLine().getStatusCode() == 200) {
                if (httpResponse.getEntity() != null) {
                    content = EntityUtils.toString(httpResponse.getEntity(), ENCODE);
                }
            }
        } catch (Exception e) {
            log.error("发送get请求异常：{}", e.getMessage(), e);
        } finally {
            try {
                if (httpResponse != null)
                    httpResponse.close();
                if (httpClient != null)
                    httpClient.close();
            } catch (IOException e) {
                log.error("get请求关闭连接异常：{}", e.getMessage(), e);
            }
        }
        return content;
    }

    /**
     * 发送post请求
     * 
     * @param url
     *            地址
     * @param params
     *            参数
     * @param headers
     *            请求头
     * @return
     */
    private static String doPost(String url, Map<String, String> params, Map<String, String> headers) {
        String content = null;
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        try {
            httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            if (params != null) {
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                Set<Entry<String, String>> entrySet = params.entrySet();
                for (Entry<String, String> entry : entrySet) {
                    nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, ENCODE));
            }
            if (headers != null) {
                Set<Entry<String, String>> entrySet = headers.entrySet();
                for (Entry<String, String> entry : entrySet) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }
            httpResponse = httpClient.execute(httpPost);
            if (httpResponse != null && httpResponse.getStatusLine().getStatusCode() == 200) {
                if (httpResponse.getEntity() != null) {
                    content = EntityUtils.toString(httpResponse.getEntity(), ENCODE);
                }
            }
        } catch (Exception e) {
            log.error("发送post异常：{}", e.getMessage(), e);
        } finally {
            try {
                if (httpResponse != null)
                    httpResponse.close();
                if (httpClient != null)
                    httpClient.close();
            } catch (IOException e) {
                log.error("post请求关闭连接异常：{}", e.getMessage(), e);
            }
        }
        return content;
    }

    /**
     * get请求
     * 
     * @param url
     *            地址
     * @return
     */
    public static String get(String url) {
        return doGet(url, null, null);
    }

    /**
     * get 请求
     * 
     * @param url
     *            地址
     * @param params
     *            参数
     * @return
     */
    public static String get(String url, Map<String, String> params) {
        return doGet(url, params, null);
    }

    /**
     * get 请求
     * 
     * @param url
     *            地址
     * @param params
     *            参数
     * @param headers
     *            请求头
     * @return
     */
    public static String get(String url, Map<String, String> params, Map<String, String> headers) {
        return doGet(url, params, headers);
    }

    /**
     * post请求
     * 
     * @param url
     *            地址
     * @return
     */
    public static String post(String url) {
        return doPost(url, null, null);
    }

    /**
     * post请求
     * 
     * @param url
     *            地址
     * @param params
     *            参数
     * @return
     */
    public static String post(String url, Map<String, String> params) {
        return doPost(url, params, null);
    }

    /**
     * post请求
     * 
     * @param url
     *            地址
     * @param params
     *            参数
     * @param headers
     *            请求头
     * @return
     */
    public static String post(String url, Map<String, String> params, Map<String, String> headers) {
        return doPost(url, params, headers);
    }

    @Test
    public void demo(){
        String url = "http://localhost:8080/common/get?name=哈哈哈";
        String data = get(url);
        System.out.println(data);
    }
    
    @Test
    public void demo2(){
        String url = "http://localhost:8080/common/post?username=哈哈哈";
        String data = post(url);
        System.out.println(data);
    }

}

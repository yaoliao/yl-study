package com.yl.common.utils.serialize;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author DELL
 * @date 2017/11/1
 */
public class HttpClientUtil {








    // java 代理ip 请求
    public static void main(String[] args) {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig config = RequestConfig.custom().setProxy(new HttpHost("122.228.179.178", 80)).build();

        HttpGet httpGet = new HttpGet("http://2017.ip138.com/ic.asp");
//        HttpGet httpGet = new HttpGet("http://city.ip138.com/ip2city.asp");
        httpGet.setConfig(config);
        Map<String, String> headerParams = new HashMap<>();
        headerParams.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        headerParams.put("Accept-Encoding", "gzip, deflate, sdch");
        headerParams.put("Accept-Language", "zh-CN,zh;q=0.8");
        headerParams.put("Connection", "keep-alive");
        headerParams.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");

        Set<Map.Entry<String, String>> entries = headerParams.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            httpGet.setHeader(entry.getKey(), entry.getValue());
        }

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            String string = EntityUtils.toString(response.getEntity(), "GBK");
            System.out.println(string);
            EntityUtils.consume(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}

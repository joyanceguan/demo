package com.joyance.demo.base.http;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.joyance.demo.utils.FileUtils4;


public class HttpUtils {
	
	private static final String host = "http://10.19.1.1:8080/do_mask?param=";
	private static final String param="{\"action_type\":1,\"mask_id\":\"%s\",\"mask_type\":0,\"punish_type\":4,\"punish_method\":17,\"mark\":\"线上回归\",\"operator\":\"mab\"}";
	
	
	private static String invokeGet(String userId) {
		String requestUrl = host + URLEncoder.encode(String.format(param, userId));
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = null;
        try {
            httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(requestUrl);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
                    .setConnectionRequestTimeout(35000)// 请求超时时间
                    .setSocketTimeout(60000)// 数据读取超时时间
                    .build();
            httpGet.setConfig(requestConfig);
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

	public static void main(String[] args) throws Exception {
		List<String> list = FileUtils4.readFile("/Users/joyance/Documents/y.txt");
		for(String userId:list){
			String result = HttpUtils.invokeGet(userId);
			System.out.println(userId+"    "+result);
		}
	}
}

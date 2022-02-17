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

import com.alibaba.druid.util.StringUtils;
import com.joyance.demo.utils.FileUtils4;

public class GetByTicket {

	private static final String host = "http://10.33.152.42:8080/getDataByTicket?ticket=";
	
	
	private static String invokeGet(String ticket) {
		String requestUrl = host + ticket;
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
		for(String ticket:list){
			if(!ticket.startsWith("P")){
				System.out.println("null");
				continue;
			}
			String result = GetByTicket.invokeGet(ticket);
			while(!result.startsWith("\"1")){
				result = GetByTicket.invokeGet(ticket);
			}
			System.out.println(result);
		}
	}
	
}

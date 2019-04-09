package com.joyance.demo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class HttpClientUtils {

	/**
     * get请求，参数放在map里
     * @param url 请求地址
     * @param params 参数params
     * @param headers 请求头headers
     * @return 响应
     */
    public static String doGet(String url,Map<String,String> params,Map<String,String> headers){
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            URIBuilder builder = new URIBuilder(url);
            List<NameValuePair> pairs = new ArrayList<NameValuePair>();
            //设置入参
            if(params!=null){
            	for(Map.Entry<String,String> entry : params.entrySet()){
                    pairs.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
                }
            }
            builder.setParameters(pairs);
            //设置请求头
            HttpGet httpGet = new HttpGet(builder.build());
            if(headers!=null){
            	for(Map.Entry<String,String> entry : headers.entrySet()){
            		httpGet.setHeader(entry.getKey(),entry.getValue());
                }
            }
            response = httpClient.execute(httpGet);
            if(response != null && response.getStatusLine().getStatusCode() == 200)
            {
                result = EntityUtils.toString(response.getEntity());
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            try {
                httpClient.close();
                if(response != null){
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    /**
	 * post请求(用于key-value格式的参数)
	 * @param url
	 * @param params
	 * @param headers
	 * @return
	 */
	public static String doPost(String url, Map<String,Object> params,Map<String,String> headers){
		String result = null;
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
        try {  
            // 定义HttpClient  
            client = HttpClients.createDefault();
            // 实例化HTTP方法  
            HttpPost request = new HttpPost();  
            request.setURI(new URI(url));
            //设置参数
            List<NameValuePair> nvps = new ArrayList<NameValuePair>(); 
            for (Iterator<String> iter = params.keySet().iterator(); iter.hasNext();) {
    			String name = (String) iter.next();
    			String value = String.valueOf(params.get(name));
    			nvps.add(new BasicNameValuePair(name, value));
    		}
            //设置请求头
            if(headers!=null){
            	for(Map.Entry<String,String> entry : headers.entrySet()){
            		request.setHeader(entry.getKey(),entry.getValue());
                }
            }
            request.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8"));
            response = client.execute(request);  
            int code = response.getStatusLine().getStatusCode();
            if(code == 200){	//请求成功
            	result = EntityUtils.toString(response.getEntity());
            }
        }catch(Exception e){
        	e.printStackTrace();
        }finally {
            try {
                client.close();
                if(response != null){
                	response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
	}
	
	/**
	 * post请求（用于请求json格式的参数）
	 * @param url
	 * @param params
	 * @param headers
	 * @return
	 */
	public static String doPost(String url, String params,Map<String,String> headers){
		String result = null;
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);// 创建httpPost   
    	httpPost.setHeader("Accept", "application/json"); 
    	httpPost.setHeader("Content-Type", "application/json");
    	//设置请求头
        if(headers!=null){
        	for(Map.Entry<String,String> entry : headers.entrySet()){
        		httpPost.setHeader(entry.getKey(),entry.getValue());
            }
        }
    	String charSet = "UTF-8";
    	StringEntity entity = new StringEntity(params, charSet);
    	httpPost.setEntity(entity);        
        CloseableHttpResponse response = null;
        try {
        	response = client.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
            	HttpEntity responseEntity = response.getEntity();
            	result = EntityUtils.toString(responseEntity);
            }
        }catch(Exception e){
        	e.printStackTrace();
        }finally {
            try {
                client.close();
                if(response != null){
                	response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
	}
	
	/**
     * 中转文件
     * 
     * @param file
     *            上传的文件
     * @return 响应结果
     */
    public static String uploadFile(String remote_url,List<MultipartFile> multipartFiles,String charset,String path){
    	String result = "";
        CloseableHttpResponse response = null;
        CloseableHttpClient client = HttpClients.createDefault();
        RequestConfig  requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();//设置请求和传输超时时间
        try {
            HttpPost httpPost = new HttpPost(remote_url);
            httpPost.setConfig(requestConfig);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            for(MultipartFile multipartFile:multipartFiles){
            	builder.addBinaryBody(multipartFile.getName(), multipartFile.getInputStream(), 
            			ContentType.create(multipartFile.getContentType(),Consts.UTF_8), multipartFile.getOriginalFilename()).setCharset(Consts.UTF_8);// 文件流
            }
            builder.addTextBody("imagePath", path);// 类似浏览器表单提交，对应input的name和value
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            response = client.execute(httpPost);// 执行提交
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                // 将响应内容转换为字符串
                result = EntityUtils.toString(responseEntity,charset);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
                if(response != null){
                	response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
	
	private static String readFile(String filePath){
		InputStream is = HttpClientUtils.class.getClassLoader().getResourceAsStream(filePath);
	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();   
	       String line = null;   
	       try {   
	           while ((line = reader.readLine()) != null) {   
	               sb.append(line);   
	           }   
	       } catch (IOException e) {   
	           e.printStackTrace();   
	       } finally {   
	           try {   
	               is.close();   
	           } catch (IOException e) {   
	               e.printStackTrace();   
	           }   
	       }  
	       return sb.toString();
	}
	
	/*
	 * 调用上传文件的例子
	 */
	public String uploadPic(HttpServletRequest request, String imagePath) {
		String remote_url="http://localhost:8081/dgjs-static/picture/uploadPic";
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
		Iterator<String> iterator=multipartRequest.getFileNames();
		List<MultipartFile> list = new ArrayList<MultipartFile>();
		while(iterator.hasNext()){
			String fileName=iterator.next();
			MultipartFile file = multipartRequest.getFile(fileName);
			list.add(file);
		}
		String result;
		result = HttpClientUtils.uploadFile(remote_url, list, request.getCharacterEncoding(), imagePath);
		return result;
	}
	
	public static void main(String[] args) {
//		验证get
//		String result = HttpClientUtils.doGet("https://www.baidu.com", null, null);
//		System.out.println(result);
		
//		验证post
//		Map<String,Object> params = new HashMap<String,Object>();
//		params.put("foodName", "apple");
//		String result = HttpClientUtils.doPost("http://localhost:8080/demo/test/eat", params, null);
//		System.out.println(result);
		
//		验证post json格式
		String result = HttpClientUtils.doPost("http://xapi.elong.com/tcindex/hotellist", readFile("test.json"), null);
		System.out.println(result);
	}
	
}

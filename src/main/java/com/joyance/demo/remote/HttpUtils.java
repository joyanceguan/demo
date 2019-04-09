package com.joyance.demo.remote;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.SSLContext;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class HttpUtils {

    public static String doGet(String url, Map<String, String> params) {
        StringBuilder json = new StringBuilder();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(combineURL(url, params).toString());
        try {
            request.setHeader("Cache-Control", "max-age=10");
            CloseableHttpResponse response = null;
            response = httpClient.execute(request);
            BufferedReader rd = null;
            if (response.getEntity() != null) {
                rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = "";
                while ((line = rd.readLine()) != null) {
                    json.append(line);
                }
            }
            rd.close();
            response.close();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json.toString();
    }


    private static String combineURL(String url, Map<String, String> params) {
        Iterator<String> iter = params == null ? null : params.keySet().iterator();
        int count = 0;
        StringBuilder str = new StringBuilder(url);
        while (iter != null && iter.hasNext()) {
            String key = iter.next();
            if (count++ == 0) {
                str.append("?" + key + "=" + params.get(key));
            } else {
                str.append("&" + key + "=" + params.get(key));
            }
        }
        return str.toString();
    }


    private static List<NameValuePair> getParamList(Map<String, String> map) {
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        for (String key : map.keySet()) {
            parameters.add(new BasicNameValuePair(key, map.get(key)));
        }
        return parameters;
    }


    public static String doHead(String url, Map<String, String> params) {
        StringBuilder json = new StringBuilder();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpHead request = new HttpHead(combineURL(url, params).toString());
        try {
            CloseableHttpResponse response = null;
            response = httpClient.execute(request);
            BufferedReader rd = null;
            if (response.getEntity() != null) {
                rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = "";
                while ((line = rd.readLine()) != null) {
                    json.append(line);
                }
            }
            Header[] headers = response.getAllHeaders();
            for (Header header : headers) {
                System.out.println("header_name=" + header.getName() + ",header_value=" + header.getValue());
            }
            if (rd != null)
                rd.close();
            response.close();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json.toString();
    }


    public static String doPut(String url, Map<String, String> params) {
        StringBuilder json = new StringBuilder();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPut request = new HttpPut(url);

        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(getParamList(params), "UTF-8");
            request.setEntity(entity);
            CloseableHttpResponse response = null;
            response = httpClient.execute(request);
            BufferedReader rd = null;
            if (response.getEntity() != null) {
                rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = "";
                while ((line = rd.readLine()) != null) {
                    json.append(line);
                }
            }
            if (rd != null)
                rd.close();
            response.close();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json.toString();
    }


    // public static void testUpdate() throws Exception {
    // String url = "http://localhost:8080/springmvc/views/testhttp/e.html";
    // HttpClient client = HttpClientBuilder.create().build();
    // HttpPut put = new HttpPut(url);
    // put.setHeader("Content-type", "application/json");
    //
    // StringEntity params = new StringEntity("name=joy age=12");
    // put.setEntity(params);
    //
    //
    // HttpResponse response = client.execute(put);
    // System.out.println("Response Code:" + response.getStatusLine().getStatusCode());
    // BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
    //
    // StringBuffer result = new StringBuffer();
    // String line = "";
    // while ((line = rd.readLine()) != null) {
    // result.append(line);
    // }
    // System.out.println("result:" + result);
    // }

    public static String doDelete(String url) {
        StringBuilder json = new StringBuilder();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpDelete request = new HttpDelete(url);
        try {
            CloseableHttpResponse response = null;
            response = httpClient.execute(request);
            BufferedReader rd = null;
            if (response.getEntity() != null) {
                rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = "";
                while ((line = rd.readLine()) != null) {
                    json.append(line);
                }
            }
            if (rd != null)
                rd.close();
            response.close();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json.toString();
    }


    public static String doOptions(String url) {
        StringBuilder json = new StringBuilder();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpOptions request = new HttpOptions(url);
        try {
            CloseableHttpResponse response = null;
            response = httpClient.execute(request);
            Set<String> set = request.getAllowedMethods(response);
            for (String str : set) {
                System.out.println(str);
            }
            BufferedReader rd = null;
            if (response.getEntity() != null) {
                rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = "";
                while ((line = rd.readLine()) != null) {
                    json.append(line);
                }
            }
            if (rd != null)
                rd.close();
            response.close();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json.toString();
    }


    public static String doTrace(String url) {
        StringBuilder json = new StringBuilder();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpTrace request = new HttpTrace(url);
        try {
            CloseableHttpResponse response = null;
            response = httpClient.execute(request);
            System.out.println(request.getMethod());

            BufferedReader rd = null;
            if (response.getEntity() != null) {
                rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = "";
                while ((line = rd.readLine()) != null) {
                    json.append(line);
                }
            }
            if (rd != null)
                rd.close();
            response.close();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json.toString();
    }


    public static void doConnect(String host, String ip, int port, String access_url) {
        StringBuffer str = new StringBuffer();
        str.append("CONNECT " + host + " HTTP/1.1\r\n");
        str.append("Host:  " + host + "\r\n");
        str.append("Proxy-Connection: Keep-Alive\r\n");
        str.append("Proxy-Authorization: Basic YWJjOjEyMw==\r\n");
        str.append("Content-Length: 0\r\n");
        str.append("\r\n");
        Socket socket = null;
        try {
            socket = new Socket(ip, port);
            OutputStream socketOut = socket.getOutputStream();
            // System.out.println(str.toString());
            // socketOut.write(str.toString().getBytes());
            // Thread.sleep(5000);
            InputStream socketIn = socket.getInputStream();
            // int size = socketIn.available();
            // byte[] buf = new byte[size];
            // socketIn.read(buf);
            // System.out.println(new String(buf));

            System.out.println("+++++++++++++++++++++++++");
            str.delete(0, str.length() - 1);
            str.append("GET " + access_url + " HTTP/1.1\r\n");
            str.append("Host: " + host + "\r\n");
            str.append("Connection: Keep-Alive\r\n");
            str.append("Accept-Encoding: gzip,deflate,sdch\r\n");
            str.append("Accept-Language:zh-CN,zh;q=0.8\r\n");
            str.append("If-None-Match: \"cea8-5322eb21ae51a-gzip\"\r\n");
            str.append("Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n");
            str.append("User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36\r\n");
            str.append("\r\n");
            socketOut.write(str.toString().getBytes());
            Thread.sleep(3000);
            socketIn = socket.getInputStream();
            int size = socketIn.available();
            byte[] buf = new byte[size];
            socketIn.read(buf);
            System.out.println(new String(buf));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    // public static void doConnect1() {
    // StringBuffer str = new StringBuffer();
    // str.append("GET /springmvc/testHttp/testGet.do?id=1&name=joyance&age=27 HTTP/1.1\r\n");
    // str.append("Host: localhost:8080\r\n");
    // str.append("Connection: Keep-Alive\r\n");
    // str.append("Accept-Encoding: gzip,deflate\r\n");
    // str.append("Accept-Language:zh-CN,zh;q=0.8\r\n");
    // str.append("Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n");
    // str.append("User-Agent: Java/1.7.0_67\r\n");
    // str.append("\r\n");
    // Socket socket = null;
    // try {
    // socket = new Socket("127.0.0.1", 8080);
    // OutputStream socketOut = socket.getOutputStream();
    // System.out.println(str.toString());
    // socketOut.write(str.toString().getBytes());
    // Thread.sleep(3000);
    // InputStream socketIn = socket.getInputStream();
    // int size = socketIn.available();
    // byte[] buf = new byte[size];
    // socketIn.read(buf);
    // System.out.println(new String(buf));
    // } catch (Exception e) {
    // e.printStackTrace();
    // } finally {
    // try {
    // socket.close();
    // } catch (IOException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // }
    // }

    public static void doConnect2(String host, String ip, int port, String access_url) {
        StringBuffer str = new StringBuffer();
        Socket socket = null;
        try {
            socket = new Socket(ip, port);
            System.out.println("+++++++++++++++++++++++++");
            // str.delete(0, str.length() - 1);
            str.append("GET " + access_url + " HTTP/1.1\r\n");
            str.append("Host: blog.csdn.net:80 \r\n");
            str.append("Connection: Keep-Alive\r\n");
            str.append("Proxy-Authorization: Basic am95OjEyMzQ1Ng==\r\n");
            str.append("Accept-Encoding: gzip,deflate,sdch\r\n");
            str.append("Accept-Language:zh-CN,zh;q=0.8\r\n");
            // str.append("Accept-Charset: UTF-8 \r\n");
            str.append("If-None-Match: \"cea8-5322eb21ae51a-gzip\"\r\n");
            str.append("Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n");
            str.append("User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36\r\n");
            str.append("\r\n");
            OutputStream socketOut = socket.getOutputStream();

            // PrintWriter output = new PrintWriter(new OutputStreamWriter(socketOut, "UTF-8"), true);
            // output.write(str.toString());
            socketOut.write(str.toString().getBytes());
            Thread.sleep(3000);
            InputStream socketIn = socket.getInputStream();
            int size = socketIn.available();
            byte[] buf = new byte[size];
            socketIn.read(buf);
            System.out.println(new String(buf));

            // BufferedReader stdin = new BufferedReader(new InputStreamReader(socketIn,));
            // String st = null;
            // str.delete(0, str.length() - 1);
            // while ((st = stdin.readLine()) != null) {
            // System.out.println(str.toString());
            // }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    public static void doConnect1(String host, String ip, int port, String access_url) {
        StringBuffer str = new StringBuffer();
        str.append("CONNECT " + host + " HTTP/1.1\r\n");
        // str.append("Host:  " + host + "\r\n");YWJjOjEyMw==
        str.append("Proxy-Connection: Keep-Alive\r\n");
        str.append("Proxy-Authorization: Basic am95OjEyMzQ1Ng==\r\n");
        str.append("Content-Length: 0\r\n");
        str.append("\r\n");
        Socket socket = null;
        try {
            socket = new Socket(ip, port);
            OutputStream socketOut = socket.getOutputStream();
            System.out.println(str.toString());
            socketOut.write(str.toString().getBytes());
            Thread.sleep(2000);
            InputStream socketIn = socket.getInputStream();
            int size = socketIn.available();
            byte[] buf = new byte[size];
            socketIn.read(buf);
            System.out.println(new String(buf));

            System.out.println("+++++++++++++++++++++++++");
            str.delete(0, str.length() - 1);
            str.append("GET " + access_url + " HTTP/1.1\r\n");
            str.append("Host: " + host + " \r\n");
            str.append("Connection: Keep-Alive\r\n");
            // str.append("Accept-Encoding: gzip,deflate,sdch\r\n");
            str.append("Accept-Language:zh-CN,zh;q=0.8\r\n");
            str.append("If-None-Match: \"cea8-5322eb21ae51a-gzip\"\r\n");
            str.append("Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n");
            str.append("User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36\r\n");
            str.append("\r\n");
            socketOut.write(str.toString().getBytes());
            Thread.sleep(3000);
            socketIn = socket.getInputStream();
            size = socketIn.available();
            buf = new byte[size];
            socketIn.read(buf);
            System.out.println(new String(buf));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }



    public static void main(String[] args) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "1");
        params.put("name", "joy2");
        params.put("age", "42");
        // get
        // System.out.println("get==============");
        // String url = "https://192.168.1.100:8443/springmvc/testHttp/testGet.do";
        // String returnValue = HttpUtils.doGet(url, params);
        // System.out.println(returnValue);
        // head
        // System.out.println("head==============");
        // String url = "http://192.168.87.72:8680/springmvc/testHttp/testHead.do";
        // String returnValue = HttpUtils.doHead(url, params);
        // System.out.println("returnValue=" + returnValue);
        // put
        // System.out.println("put==============");
        // String url = "http://localhost:8080/springmvc/views/testhttp/j.html";
        // HttpUtils.doPut(url, params);
        // delete
        // System.out.println("delete==============");
        // String url = "http://localhost:8080/springmvc/views/testhttp/c.html";
        // HttpUtils.doDelete(url);
        // options
        // System.out.println("options==============");
        // String url = "http://localhost:8080/springmvc/*";
        // HttpUtils.doOptions(url);
        // trace
        // System.out.println("trace==============");
        // String url = "http://localhost:8080/springmvc";
        // String returnValue = HttpUtils.doTrace(url);
        // System.out.println(returnValue);
        // connect
        // System.out.println("connect==============");
        // HttpUtils.doConnect("zht0454.oicp.net:15059", "169.254.248.5", 808, "/springstruts/index.jsp");
        // HttpUtils.doConnect2("169.254.176.179:808", "169.254.176.179", 808, "http://blog.csdn.net/kobejayandy/article/details/24606521");
        // HttpUtils.doConnect1("blog.csdn.net:80", "169.254.248.5", 808, "http://blog.csdn.net/kobejayandy/article/details/24606521");
        // HttpUtils.doConnect("blog.csdn.net", "43.226.162.107", 80, "/kobejayandy/article/details/24606521");
    }
    
    public void test1_https() throws Exception {
        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        // 加载证书文件
        FileInputStream instream = new FileInputStream(new File("F:/学习资料/certificate/httpExample.com.keystore"));
        try {
            trustStore.load(instream, "123456".toCharArray());
        } finally {
            instream.close();
        }
        SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(trustStore).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext,
        SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        try
        {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("id", "1"));
            nvps.add(new BasicNameValuePair("name", "joy"));
            nvps.add(new BasicNameValuePair("age", "15"));
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nvps, "GBK");
            org.apache.http.client.methods.HttpPost hr = new org.apache.http.client.methods.HttpPost(
                    "https://joyance.httpExample.com/httpExample/testHttp/testPost.do");
            hr.setEntity(urlEncodedFormEntity);
            hr.setHeader("Content-Type", "application/x-www-form-urlencoded");
            CloseableHttpResponse response = httpclient.execute(hr);
            // HttpGet httpget = new HttpGet("https://joyance.com.cn:8443/springmvc/testhttp/testGet.do?name=joy&age=14&id=1");
            // System.out.println("executing request" + httpget.getRequestLine());
            // CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                HttpEntity entity = response.getEntity();
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                if (entity != null) {
                    System.out.println(EntityUtils.toString(entity));
                }
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }
}

package com.joyance.demo.remote;

import java.util.HashMap;
import java.util.Map;
import junit.framework.TestCase;

public class HttpMethodTest extends TestCase {

    // joyance.httpexample.com
    String contextURL = "http://localhost:8080/httpExample/";


    public void test_head() {
        String head_url = contextURL + "testhttp/testHead.do";
        String returnValue = HttpUtils.doHead(head_url, getParams());
        System.out.println(returnValue);
    }


    public void test_put() {
        String put_url = contextURL + "views/testhttp/j.html";
        HttpUtils.doPut(put_url, getParams());
    }


    public void test_delete() {
        String put_url = contextURL + "views/testhttp/j.html";
        HttpUtils.doDelete(put_url);
    }


    public void test_options() {
        String put_url = contextURL + "*";
        HttpUtils.doOptions(put_url);
    }


    // public void test_trace() {
    // String url = "http://localhost:8080/springmvc";
    // String returnValue = HttpUtils.doTrace(url);
    // System.out.println(returnValue);
    // }


    public void test_connect1() {
        // http://blog.csdn.net/kobejayandy/article/details/24606521
        // HttpUtils.doConnect("blog.csdn.net", "169.254.248.5", 808, "/kobejayandy/article/details/24606521");
        HttpUtils.doConnect1("blog.csdn.net:80", "169.254.193.117", 808, "http://blog.csdn.net/kobejayandy/article/details/24606521");
    }


    public void test_connect2() {
        // http://blog.csdn.net/kobejayandy/article/details/24606521
        // HttpUtils.doConnect("blog.csdn.net", "169.254.248.5", 808, "/kobejayandy/article/details/24606521");
        HttpUtils.doConnect2("169.254.176.179:808", "169.254.176.179", 808, "http://blog.csdn.net/kobejayandy/article/details/24606521");
    }


    private Map<String, String> getParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "1");
        params.put("name", "joy2");
        params.put("age", "43");
        return params;
    }

}

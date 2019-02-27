package com.zheng.common.util;

import com.zheng.common.util.RequestUtil;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.AsyncContext;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RequestUtilTest {

  @Test
  public void getBasePathHttp() {
    final RequestUtil requestUtil = new RequestUtil();
    final MockHttpServletRequest request = new MockHttpServletRequest();
    
    request.setScheme("http");
    request.setServerPort(80);
    Assert.assertEquals("http://localhost", requestUtil.getBasePath(request));
    
    request.setServerPort(81);
    Assert.assertEquals("http://localhost:81", requestUtil.getBasePath(request));
  }

  @Test
  public void getBasePathHttps() {
    final RequestUtil requestUtil = new RequestUtil();
    final MockHttpServletRequest request = new MockHttpServletRequest();

    request.setScheme("https");
    request.setServerPort(443);
    Assert.assertEquals("https://localhost", requestUtil.getBasePath(request));
    
    request.setServerPort(444);
    Assert.assertEquals("https://localhost:444", requestUtil.getBasePath(request));
  }

  @Test
  public void getBasePathOther() {
    final RequestUtil requestUtil = new RequestUtil();
    final MockHttpServletRequest request = new MockHttpServletRequest();

    request.setScheme("ftp");
    Assert.assertEquals("ftp://localhost", requestUtil.getBasePath(request));
  }

  @Test
  public void getIpAddr() {
    final RequestUtil requestUtil = new RequestUtil();
    MockHttpServletRequest request = new MockHttpServletRequest();

    request.addHeader("Cdn-Src-Ip", "192.168.0.1");
    Assert.assertEquals("192.168.0.1", requestUtil.getIpAddr(request));

    request = new MockHttpServletRequest();
    Assert.assertEquals("127.0.0.1", requestUtil.getIpAddr(request));

    request = new MockHttpServletRequest();
    request.addHeader("Cdn-Src-Ip", "");
    Assert.assertEquals("127.0.0.1", requestUtil.getIpAddr(request));

    request = new MockHttpServletRequest();
    request.addHeader("Cdn-Src-Ip", " unknown");
    Assert.assertEquals("127.0.0.1", requestUtil.getIpAddr(request));       
  }

  @Test
  public void getIpAddr2() {
    final RequestUtil requestUtil = new RequestUtil();
    MockHttpServletRequest request = new MockHttpServletRequest();

    request.addHeader("HTTP_CLIENT_IP", "192.168.0.1");
    Assert.assertEquals("192.168.0.1", requestUtil.getIpAddr(request));   

    request = new MockHttpServletRequest();
    Assert.assertEquals("127.0.0.1", requestUtil.getIpAddr(request));   

    request = new MockHttpServletRequest();
    request.addHeader("HTTP_CLIENT_IP", "");
    Assert.assertEquals("127.0.0.1", requestUtil.getIpAddr(request));   

    request = new MockHttpServletRequest();
    request.addHeader("HTTP_CLIENT_IP", " unknown");
    Assert.assertEquals("127.0.0.1", requestUtil.getIpAddr(request));   
  }

  @Test
  public void getIpAddr3() {
    final RequestUtil requestUtil = new RequestUtil();
    MockHttpServletRequest request = new MockHttpServletRequest();

    request.addHeader("X-Forwarded-For", "192.168.0.1");
    Assert.assertEquals("192.168.0.1", requestUtil.getIpAddr(request));   

    request = new MockHttpServletRequest();
    Assert.assertEquals("127.0.0.1", requestUtil.getIpAddr(request));   

    request = new MockHttpServletRequest();
    request.addHeader("X-Forwarded-For", "");
    Assert.assertEquals("127.0.0.1", requestUtil.getIpAddr(request));   

    request = new MockHttpServletRequest();
    request.addHeader("X-Forwarded-For", " unknown");
    Assert.assertEquals("127.0.0.1", requestUtil.getIpAddr(request));   
  }
  
  @Test
  public void getIpAddr4() {
    final RequestUtil requestUtil = new RequestUtil();
    MockHttpServletRequest request = new MockHttpServletRequest();

    request.addHeader("Proxy-Client-IP", "192.168.0.1");
    Assert.assertEquals("192.168.0.1", requestUtil.getIpAddr(request));   

    request = new MockHttpServletRequest();
    Assert.assertEquals("127.0.0.1", requestUtil.getIpAddr(request));   

    request = new MockHttpServletRequest();
    request.addHeader("Proxy-Client-IP", "");
    Assert.assertEquals("127.0.0.1", requestUtil.getIpAddr(request));   

    request = new MockHttpServletRequest();
    request.addHeader("Proxy-Client-IP", "unknown");
    Assert.assertEquals("127.0.0.1", requestUtil.getIpAddr(request));   
  }

  @Test
  public void getIpAddr5() {
    final RequestUtil requestUtil = new RequestUtil();
    MockHttpServletRequest request = new MockHttpServletRequest();

    request.addHeader("WL-Proxy-Client-IP", "192.168.0.1");
    Assert.assertEquals("192.168.0.1", requestUtil.getIpAddr(request));   

    request = new MockHttpServletRequest();
    Assert.assertEquals("127.0.0.1", requestUtil.getIpAddr(request));   

    request = new MockHttpServletRequest();
    request.addHeader("WL-Proxy-Client-IP", "");
    Assert.assertEquals("127.0.0.1", requestUtil.getIpAddr(request));   

    request = new MockHttpServletRequest();
    request.addHeader("WL-Proxy-Client-IP", "unknown");
    Assert.assertEquals("127.0.0.1", requestUtil.getIpAddr(request));   
  }

  @Test
  public void getParameterMap() {
    final RequestUtil requestUtil = new RequestUtil();
    final MockHttpServletRequest request = new MockHttpServletRequest();

    final HashMap<String, String> parameterMap = new HashMap<String, String>();
    parameterMap.put("param1", "val1");

    request.addParameters(parameterMap);
    Assert.assertEquals(parameterMap, requestUtil.getParameterMap(request));
  }

  @Test
  public void getParameterMap2() {
    final RequestUtil requestUtil = new RequestUtil();
    final MockHttpServletRequest request = new MockHttpServletRequest();

    final HashMap<String, String> parameterMap = new HashMap<String, String>();
    parameterMap.put("param1", "val1");
    parameterMap.put("param2", "val2");

    request.addParameters(parameterMap);
    Assert.assertEquals(parameterMap, requestUtil.getParameterMap(request));
  }

  @Test
  public void removeParam() {
    final RequestUtil requestUtil = new RequestUtil();
    final MockHttpServletRequest request = new MockHttpServletRequest();

    final HashMap<String, String> parameterMap = new HashMap<String, String>();
    parameterMap.put("param1", "val1");
    parameterMap.put("param2", "val2");

    request.addParameters(parameterMap);
    Assert.assertEquals("param1=val1&param2=val2", requestUtil.removeParam(request, "param3"));
  }
}

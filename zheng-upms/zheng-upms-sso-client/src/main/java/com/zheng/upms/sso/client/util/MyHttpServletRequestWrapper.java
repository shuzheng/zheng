package com.zheng.upms.sso.client.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZhangShuzheng on 2016/12/12.
 */
public class MyHttpServletRequestWrapper extends HttpServletRequestWrapper {

	private Map params = new HashMap();

	public MyHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		//params = request.getParameterMap();
	}

	public void removeParameter(String name) {
		params.remove(name);
	}

	public Map getParameterMap() {
		return params;
	}

	public String getParameter(String name) {
		return (String) params.get(name);
	}

	public Enumeration getParameterNames() {
		return Collections.enumeration(params.keySet());
	}

	public String[] getParameterValues(String name) {
		return (String[])params.get(name);
	}

}

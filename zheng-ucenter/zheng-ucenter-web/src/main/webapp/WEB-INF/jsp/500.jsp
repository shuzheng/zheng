<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<title><spring:message code="500"/></title>
</head>
<body>
<center style="margin:50px auto">
	<p>错误代码：500<%//=request.getAttribute("javax.servlet.error.status_code")%></p>
	<p>您访问的页面有错误！</p>
	<p>错误原因：${error.message}</p>
	<p>错误内容：${error}</p>
	<p><!--页面将在<span id="stime">5</span>秒后-->跳转到<a href="${pageContext.request.contextPath}/">首页</a>！</p>
</center>
<%
/**
监控出错人的IP
String ip = request.getHeader(" x-forwarded-for");
if (ip == null || ip.length() == 0 || " unknown".equalsIgnoreCase(ip)) {
	ip = request.getHeader(" Proxy-Client-IP"); // 获取代理ip
}
if (ip == null || ip.length() == 0 || " unknown".equalsIgnoreCase(ip)) {
	ip = request.getHeader(" WL-Proxy-Client-IP"); // 获取代理ip
}
if (ip == null || ip.length() == 0 || " unknown".equalsIgnoreCase(ip)) {
	ip = request.getRemoteAddr(); // 获取真实ip
}
//out.println(ip+"<br/><br/>你的地址是：<br/><br/>");

Document doc = Jsoup.connect("http://ip.chinaz.com/?IP="+ip).timeout(9000).get();
Element e = doc.select("#status").first();
//out.println(e);
*/
%>
</body>
</html>
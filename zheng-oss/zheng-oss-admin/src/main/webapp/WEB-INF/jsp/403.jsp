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
<title><spring:message code="403"/></title>
</head>
<body>
<% Exception e = (Exception)request.getAttribute("ex"); %>
<h2>错误: <%= e.getClass().getSimpleName()%></h2>
<hr />
<h5>错误描述：</h5>
<%= e.getMessage()%>
</body>
</html>
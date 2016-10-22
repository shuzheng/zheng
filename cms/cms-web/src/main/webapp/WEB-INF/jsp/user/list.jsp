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
<title><spring:message code="user.list"/></title>
</head>
<body>
<div class="breadcrumb">
	<span class="crust"><a href="${basePath}/" class="crumb">首页</a><span class="arrow"><span>&gt;</span></span></span>
	<span class="crust"><a href="${basePath}/user" class="crumb">用户管理</a><span class="arrow"><span>&gt;</span></span></span>
	<span class="crust"><a href="" class="crumb">用户列表</a><span class="arrow"><span>&gt;</span></span></span>
</div>
<div id="main">
	<table id="datagrid" class="datagrid" border="1">
		<caption><i class="fa fa-list-ol"></i> 用户列表 <a href="${basePath}/user/add">添加</a></caption>
		<thead>
			<tr>
				<th>ID</th>
				<th>账号</th>
				<th>密码</th>
				<th>昵称</th>
				<th>性别</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${users}">
			<tr>
				<td>${user.id}</td>
				<td>${user.username}</td>
				<td>${user.password}</td>
				<td>${user.nickname}</td>
				<td>
					<c:if test="${user.sex==1}">男</c:if>
					<c:if test="${user.sex==2}">女</c:if>
				</td>
				<td>
					<%-- 
					<fmt:parseDate value="${user.ctime}" var="date" pattern="yyyyMMddHHmm"/> 
					<fmt:formatDate value="${date}" type="both" pattern="yyyy-MM-dd HH:mm:ss" timeZone="Asia/Shanghai"/>
					 --%>
					${user.ctime}
				</td>
				<td>
					<a href="${basePath}/book/list/${user.id}">书籍管理</a>
					<a href="${basePath}/user/update/${user.id}">修改</a>
					<a href="${basePath}/user/delete/${user.id}" onclick="return confirm('确认删除吗？');">删除</a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pages">${paginator.html}</div>
</div>
</body>
</html>
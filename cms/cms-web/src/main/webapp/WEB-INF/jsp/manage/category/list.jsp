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
<title>列表</title>
</head>
<body>
<div>
	<a href="${basePath}/manage">首页</a> &gt; 类目列表
</div>
<div>
	<table border="1">
		<caption><a href="${basePath}/manage/category/add">新增</a> <a href="${basePath}/manage/category/batchDelete">批量删除</a></caption>
		<thead>
			<tr>
				<th>ID</th>
				<th>上级编号</th>
				<th>层级</th>
				<th>名称</th>
				<th>描述</th>
				<th>图标</th>
				<th>类型</th>
				<th>别名</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="category" items="${categorys}">
			<tr>
				<td>${category.categoryId}</td>
				<td>${category.pid}</td>
				<td>${category.level}</td>
				<td>${category.name}</td>
				<td>${category.description}</td>
				<td>${category.icon}</td>
				<td>
					<c:if test="${category.type==1}">普通</c:if>
					<c:if test="${category.type==2}">热门</c:if>
				</td>
				<td>${category.alias}</td>
				<td>
					<jsp:useBean id="date" class="java.util.Date"/>
					<jsp:setProperty name="date" property="time" value="${category.ctime}"/>
					<fmt:formatDate value="${date}" type="both" pattern="yyyy-MM-dd HH:mm:ss" timeZone="Asia/Shanghai"/>
				</td>
				<td>
					<a href="${basePath}/manage/category/update/${category.categoryId}">修改</a>
					<a href="${basePath}/manage/category/delete/${category.categoryId}" onclick="return confirm('确认删除吗？');">删除</a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>${paginator.html}</div>
</div>
</body>
</html>
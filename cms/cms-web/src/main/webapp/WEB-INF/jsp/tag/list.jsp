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
	<a href="${basePath}/">首页</a> &gt; 标签列表
</div>
<div>
	<table border="1">
		<caption><a href="${basePath}/tag/add">新增</a></caption>
		<thead>
			<tr>
				<th>ID</th>
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
			<c:forEach var="tag" items="${tags}">
			<tr>
				<td>${tag.tagId}</td>
				<td>${tag.name}</td>
				<td>${tag.description}</td>
				<td>${tag.icon}</td>
				<td>
					<c:if test="${tag.type==1}">普通</c:if>
					<c:if test="${tag.type==2}">热门</c:if>
				</td>
				<td>${tag.alias}</td>
				<td>
					<jsp:useBean id="date" class="java.util.Date"/>
					<jsp:setProperty name="date" property="time" value="${tag.ctime}"/>
					<fmt:formatDate value="${date}" type="both" pattern="yyyy-MM-dd HH:mm:ss" timeZone="Asia/Shanghai"/>
				</td>
				<td>
					<a href="${basePath}/tag/update/${tag.tagId}">修改</a>
					<a href="${basePath}/tag/delete/${tag.tagId}" onclick="return confirm('确认删除吗？');">删除</a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>${paginator.html}</div>
</div>
</body>
</html>
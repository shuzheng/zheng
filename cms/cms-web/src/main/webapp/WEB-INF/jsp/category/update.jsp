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
<title>编辑</title>
</head>
<body>
<div>
	<a href="${basePath}/">首页</a> &gt; <a href="${basePath}/category/list">类目列表</a> &gt; 编辑
</div>
<div>
	<form method="post">
	<table border="1">
		<input type="hidden" name="tagId" value="${category.categoryId}"/>
		<tr><td>上级编号：</td><td><input type="text" name="pid" value="${category.pid}"/></td></tr>
		<tr><td>层级：</td><td><input type="text" name="level" value="${category.level}"/></td></tr>
		<tr><td>名称：</td><td><input type="text" name="name" value="${category.name}"/></td></tr>
		<tr><td>描述：</td><td><input type="text" name="description" value="${category.description}"/></td></tr>
		<tr><td>图标：</td><td><input type="text" name="icon" value="${category.icon}"/></td></tr>
		<tr>
			<td>类型：</td>
			<td>
				<select name="type">
					<option value="1" <c:if test="${category.type==1}">selected="selected"</c:if>>普通</option>
					<option value="2" <c:if test="${category.type==2}">selected="selected"</c:if>>热门</option>
				</select>
			</td>
		</tr>
		<tr><td>别名：</td><td><input type="text" name="alias" value="${category.alias}"/></td></tr>
		<tr><td></td><td><a href="${basePath}/category/list">取消</a>　<input type="submit" value="保存"/></td></tr>
	</table>
	</form>
</div>
</body>
</html>
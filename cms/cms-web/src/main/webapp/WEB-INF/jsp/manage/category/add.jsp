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
<title>新增</title>
</head>
<body>
<div>
	<a href="${basePath}/manage">首页</a> &gt; <a href="${basePath}/manage/category/list">类目列表</a> &gt; 新增
</div>
<div>
	<form method="post">
	<table border="1">
		<tr><td>上级编号：</td><td><input type="text" name="pid" autofocus/></td></tr>
		<tr><td>层级：</td><td><input type="text" name="level"/></td></tr>
		<tr><td>名称：</td><td><input type="text" name="name"/></td></tr>
		<tr><td>描述：</td><td><input type="text" name="description"/></td></tr>
		<tr><td>图标：</td><td><input type="text" name="icon"/></td></tr>
		<tr>
			<td>类型：</td>
			<td>
				<select name="type">
					<option value="1">普通</option>
					<option value="2">热门</option>
				</select>
			</td>
		</tr>
		<tr><td>别名：</td><td><input type="text" name="alias"/></td></tr>
		<tr><td></td><td><a href="${basePath}/manage/category/list">取消</a>　<input type="submit" value="保存"/></td></tr>
	</table>
	</form>
</div>
</body>
</html>
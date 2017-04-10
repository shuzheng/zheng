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
	<a href="${basePath}/manage/index">首页</a> &gt; <a href="${basePath}/manage/comment/list">评论列表</a> &gt; 新增
</div>
<div>
	<form method="post">
	<table border="1">
		<tr><td>楼中楼编号：</td><td><input type="text" name="pid" autofocus/></td></tr>
		<tr><td>所属系统：</td><td><input type="text" name="systemId"/></td></tr>
		<tr><td>文章编号：</td><td><input type="text" name="articleId"/></td></tr>
		<tr><td>用户编号：</td><td><input type="text" name="userId"/></td></tr>
		<tr><td>评论内容：</td><td><input type="text" name="content"/></td></tr>
		<tr>
			<td>状态：</td>
			<td>
				<select name="status">
					<option value="0">未审核</option>
					<option value="-1">不通过</option>
					<option value="1">通过</option>
				</select>
			</td>
		</tr>
		<tr><td>IP地址：</td><td><input type="text" name="ip"/></td></tr>
		<tr><td>终端信息：</td><td><input type="text" name="agent"/></td></tr>
		<tr><td></td><td><a href="${basePath}/manage/comment/list">取消</a>　<input type="submit" value="保存"/></td></tr>
	</table>
	</form>
</div>
</body>
</html>
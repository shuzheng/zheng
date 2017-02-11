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
	<a href="${basePath}/manage/index">首页</a> &gt; <a href="${basePath}/manage/article/list">文章列表</a> &gt; 新增
</div>
<div>
	<form method="post">
	<table border="1">
		<tr><td>标题：</td><td><input type="text" name="title" autofocus/></td></tr>
		<tr><td>原作者：</td><td><input type="text" name="author"/></td></tr>
		<tr><td>来源：</td><td><input type="text" name="fromurl"/></td></tr>
		<tr><td>封面图：</td><td><input type="text" name="image"/></td></tr>
		<tr><td>关键字：</td><td><input type="text" name="keywords"/></td></tr>
		<tr><td>简介：</td><td><input type="text" name="description"/></td></tr>
		<tr>
			<td>类型：</td>
			<td>
				<select name="type">
					<option value="1">普通</option>
					<option value="2">热门</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>是否允许评论：</td>
			<td>
				<select name="allowcomments">
					<option value="1">允许</option>
					<option value="0">不允许</option>
				</select>
			</td>
		</tr>
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
		<tr><td>内容：</td><td><input type="text" name="content"/></td></tr>
		<tr><td>发布人ID：</td><td><input type="text" name="userId"/></td></tr>
		<tr><td>阅读量：</td><td><input type="text" name="readnumber" value="0"/></td></tr>
		<tr><td></td><td><a href="${basePath}/manage/article/list">取消</a>　<input type="submit" value="保存"/></td></tr>
	</table>
	</form>
</div>
</body>
</html>
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
	<a href="${basePath}/manage/index">首页</a> &gt; <a href="${basePath}/manage/article/list">文章列表</a> &gt; 编辑
</div>
<div>
	<form method="post">
	<table border="1">
		<input type="hidden" name="articleId" value="${article.articleId}"/>
		<tr><td>标题：</td><td><input type="text" name="title" value="${article.title}"/></td></tr>
		<tr><td>原作者：</td><td><input type="text" name="author" value="${article.author}"/></td></tr>
		<tr><td>来源：</td><td><input type="text" name="fromurl" value="${article.fromurl}"/></td></tr>
		<tr><td>封面图：</td><td><input type="text" name="image" value="${article.image}"/></td></tr>
		<tr><td>关键字：</td><td><input type="text" name="keywords" value="${article.keywords}"/></td></tr>
		<tr><td>简介：</td><td><input type="text" name="description" value="${article.description}"/></td></tr>
		<tr>
			<td>类型：</td>
			<td>
				<select name="type">
					<option value="1" <c:if test="${article.type==1}">selected="selected"</c:if>>普通</option>
					<option value="2" <c:if test="${article.type==2}">selected="selected"</c:if>>热门</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>是否允许评论：</td>
			<td>
				<select name="allowcomments">
					<option value="1" <c:if test="${article.allowcomments==1}">selected="selected"</c:if>>允许</option>
					<option value="0" <c:if test="${article.allowcomments==0}">selected="selected"</c:if>>不允许</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>状态：</td>
			<td>
				<select name="status">
					<option value="0" <c:if test="${article.status==0}">selected="selected"</c:if>>未审核</option>
					<option value="-1" <c:if test="${article.status==-1}">selected="selected"</c:if>>不通过</option>
					<option value="1" <c:if test="${article.status==1}">selected="selected"</c:if>>通过</option>
				</select>
			</td>
		</tr>
		<tr><td>内容：</td><td><input type="text" name="content" value="${article.content}"/></td></tr>
		<tr><td>发布人ID：</td><td><input type="text" name="userId" value="${article.userId}"/></td></tr>
		<tr><td>阅读量：</td><td><input type="text" name="readnumber" value="${article.readnumber}"/></td></tr>
		<tr><td></td><td><a href="${basePath}/manage/article/list">取消</a>　<input type="submit" value="保存"/></td></tr>
	</table>
	</form>
</div>
</body>
</html>
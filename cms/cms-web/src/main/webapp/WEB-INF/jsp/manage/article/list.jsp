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
	<a href="${basePath}/manage">首页</a> &gt; 文章列表
</div>
<div>
	<table border="1">
		<caption><a href="${basePath}/manage/article/add">新增</a></caption>
		<thead>
			<tr>
				<th>ID</th>
				<th>标题</th>
				<th>原作者</th>
				<th>来源</th>
				<th>封面图</th>
				<th>关键字</th>
				<th>简介</th>
				<th>类型</th>
				<th>是否允许评论</th>
				<th>状态</th>
				<th>内容</th>
				<th>发布人ID</th>
				<th>阅读数</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="article" items="${articles}">
			<tr>
				<td>${article.articleId}</td>
				<td>${article.title}</td>
				<td>${article.author}</td>
				<td>${article.fromurl}</td>
				<td>${article.image}</td>
				<td>${article.keywords}</td>
				<td>${article.description}</td>
				<td>
					<c:if test="${article.type==1}">普通</c:if>
					<c:if test="${article.type==2}">热门</c:if>
				</td>
				<td>
					<c:if test="${article.allowcomments==0}">不允许</c:if>
					<c:if test="${article.allowcomments==1}">允许</c:if>
				</td>
				<td>
					<c:if test="${article.status==-1}">不通过</c:if>
					<c:if test="${article.status==0}">未审核</c:if>
					<c:if test="${article.status==1}">通过</c:if>
				</td>
				<td>${article.content}</td>
				<td>${article.userId}</td>
				<td>${article.readnumber}</td>
				<td>
					<jsp:useBean id="date" class="java.util.Date"/>
					<jsp:setProperty name="date" property="time" value="${article.ctime}"/>
					<fmt:formatDate value="${date}" type="both" pattern="yyyy-MM-dd HH:mm:ss" timeZone="Asia/Shanghai"/>
				</td>
				<td>
					<a href="${basePath}/manage/article/update/${article.articleId}">修改</a>
					<a href="${basePath}/manage/article/delete/${article.articleId}" onclick="return confirm('确认删除吗？');">删除</a>
					<a href="${basePath}/manage/article/up/${article.articleId}">上移</a>
					<a href="${basePath}/manage/article/down/${article.articleId}">下移</a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>${paginator.html}</div>
</div>
</body>
</html>
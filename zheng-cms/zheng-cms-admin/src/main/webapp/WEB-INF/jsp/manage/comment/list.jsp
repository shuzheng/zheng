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
	<a href="${basePath}/manage/index">首页</a> &gt; 评论列表
</div>
<div>
	<table border="1">
		<caption><a href="${basePath}/manage/comment/add">新增</a></caption>
		<thead>
			<tr>
				<th>ID</th>
				<th>楼中楼ID</th>
				<th>文章编号</th>
				<th>用户编号</th>
				<th>评论内容</th>
				<th>状态</th>
				<th>IP地址</th>
				<th>终端信息</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="comment" items="${comments}">
			<tr>
				<td>${comment.commentId}</td>
				<td>${comment.pid}</td>
				<td>${comment.articleId}</td>
				<td>${comment.userId}</td>
				<td>${comment.content}</td>
				<td>
					<c:if test="${comment.status==-1}">不通过</c:if>
					<c:if test="${comment.status==0}">未审核</c:if>
					<c:if test="${comment.status==1}">通过</c:if>
				</td>
				<td>${comment.ip}</td>
				<td>${comment.agent}</td>
				<td>
					<jsp:useBean id="date" class="java.util.Date"/>
					<jsp:setProperty name="date" property="time" value="${comment.ctime}"/>
					<fmt:formatDate value="${date}" type="both" pattern="yyyy-MM-dd HH:mm:ss" timeZone="Asia/Shanghai"/>
				</td>
				<td>
					<a href="${basePath}/manage/comment/update/${comment.commentId}">修改</a>
					<a href="${basePath}/manage/comment/delete/${comment.commentId}" onclick="return confirm('确认删除吗？');">删除</a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>${paginator.html}</div>
</div>
</body>
</html>
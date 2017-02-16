<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<div id="updateDialog" class="crudDialog">
	<form action="${basePath}/manage/system/update" method="post">
		<div class="form-group">
			<label for="input1">标题</label>
			<input id="input1" type="text" class="form-control">
		</div>
		<div class="form-group">
			<label for="input2">名称</label>
			<input id="input2" type="text" class="form-control">
		</div>
		<div class="form-group">
			<label for="input3">根目录</label>
			<input id="input3" type="text" class="form-control">
		</div>
		<div class="form-group">
			<label for="input4">图标</label>
			<input id="input4" type="text" class="form-control">
		</div>
	</form>
</div>
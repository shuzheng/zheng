<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<div id="updateDialog" class="crudDialog">
	<form id="updateForm" method="post">
		<div class="form-group">
			<label for="pid">上级</label>
			<input id="pid" type="text" class="form-control" name="pid" value="${page.pid}">
		</div>
		<div class="form-group">
			<label for="title">标题</label>
			<input id="title" type="text" class="form-control" name="title" maxlength="20" value="${page.title}">
		</div>
		<div class="form-group">
			<label for="alias">别名</label>
			<input id="alias" type="text" class="form-control" name="alias" maxlength="20" value="${page.alias}">
		</div>
		<div class="form-group">
			<label for="keywords">关键字</label>
			<input id="keywords" type="text" class="form-control" name="keywords" maxlength="100" value="${page.keywords}">
		</div>
		<div class="form-group">
			<label for="description">描述</label>
			<input id="description" type="text" class="form-control" name="description" maxlength="200" value="${page.description}">
		</div>
		<div class="form-group">
			<label for="content">内容</label>
			<input id="content" type="text" class="form-control" name="content" value="${page.content}">
		</div>
		<div class="form-group text-right dialog-buttons">
			<a class="waves-effect waves-button" href="javascript:;" onclick="createSubmit();">保存</a>
			<a class="waves-effect waves-button" href="javascript:;" onclick="updateDialog.close();">取消</a>
		</div>
	</form>
</div>
<script>
	function createSubmit() {
		$.ajax({
			type: 'post',
			url: '${basePath}/manage/page/update/${page.pageId}',
			data: $('#updateForm').serialize(),
			beforeSend: function() {
				if ($('#name').val() == '') {
					$('#name').focus();
					return false;
				}
				if ($('#alias').val() == '') {
					$('#alias').focus();
					return false;
				}
			},
			success: function(result) {
				if (result.code != 1) {
					if (result.data instanceof Array) {
						$.each(result.data, function(index, value) {
							$.confirm({
								theme: 'dark',
								animation: 'rotateX',
								closeAnimation: 'rotateX',
								title: false,
								content: value.errorMsg,
								buttons: {
									confirm: {
										text: '确认',
										btnClass: 'waves-effect waves-button waves-light'
									}
								}
							});
						});
					} else {
						$.confirm({
							theme: 'dark',
							animation: 'rotateX',
							closeAnimation: 'rotateX',
							title: false,
							content: result.data.errorMsg,
							buttons: {
								confirm: {
									text: '确认',
									btnClass: 'waves-effect waves-button waves-light'
								}
							}
						});
					}
				} else {
					updateDialog.close();
					$table.bootstrapTable('refresh');
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				$.confirm({
					theme: 'dark',
					animation: 'rotateX',
					closeAnimation: 'rotateX',
					title: false,
					content: textStatus,
					buttons: {
						confirm: {
							text: '确认',
							btnClass: 'waves-effect waves-button waves-light'
						}
					}
				});
			}
		});
	}
</script>
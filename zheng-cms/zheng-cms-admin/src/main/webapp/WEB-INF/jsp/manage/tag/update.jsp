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
			<label for="systemId">所属系统</label>
			<input id="systemId" type="text" class="form-control" name="systemId" value="${tag.systemId}">
		</div>
		<div class="form-group">
			<label for="name">名称</label>
			<input id="name" type="text" class="form-control" name="name" maxlength="20" value="${tag.name}">
		</div>
		<div class="form-group">
			<label for="alias">别名</label>
			<input id="alias" type="text" class="form-control" name="alias" maxlength="20" value="${tag.alias}">
		</div>
		<div class="form-group">
			<label for="description">描述</label>
			<input id="description" type="text" class="form-control" name="description" maxlength="200" value="${tag.description}">
		</div>
		<div class="form-group">
			<label for="icon">图标</label>
			<input id="icon" type="text" class="form-control" name="icon" maxlength="20" value="${tag.icon}">
		</div>
		<div class="radio">
			<div class="radio radio-inline radio-info">
				<input id="type_1" type="radio" name="type" value="1" <c:if test="${tag.type==1}">checked</c:if>>
				<label for="type_1">普通 </label>
			</div>
			<div class="radio radio-inline radio-danger">
				<input id="type_2" type="radio" name="type" value="2" <c:if test="${tag.type==2}">checked</c:if>>
				<label for="type_2">热门 </label>
			</div>
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
			url: '${basePath}/manage/tag/update/${tag.tagId}',
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
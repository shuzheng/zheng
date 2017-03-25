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
			<label for="icon">图标</label>
			<input id="icon" type="text" class="form-control" name="icon" maxlength="20" value="${system.icon}">
		</div>
		<div class="form-group">
			<label for="title">标题</label>
			<input id="title" type="text" class="form-control" name="title" maxlength="20" value="${system.title}">
		</div>
		<div class="form-group">
			<label for="name">名称</label>
			<input id="name" type="text" class="form-control" name="name" maxlength="20" value="${system.name}">
		</div>
		<div class="form-group">
			<label for="theme">主题</label>
			<input id="theme" type="text" class="form-control" name="theme" maxlength="50" value="${system.theme}">
		</div>
		<div class="form-group">
			<label for="banner">背景图</label>
			<input id="banner" type="text" class="form-control" name="banner" maxlength="50" value="${system.banner}">
		</div>
		<div class="form-group">
			<label for="description">描述</label>
			<input id="description" type="text" class="form-control" name="description" maxlength="300" value="${system.description}">
		</div>
		<div class="form-group">
			<label for="basepath">根目录</label>
			<input id="basepath" type="text" class="form-control" name="basepath" maxlength="100" value="${system.basepath}">
		</div>
		<div class="radio">
			<div class="radio radio-inline radio-success">
				<input id="status_1" type="radio" name="status" value="1" <c:if test="${system.status==1}">checked</c:if>>
				<label for="status_1">正常 </label>
			</div>
			<div class="radio radio-inline">
				<input id="status_0" type="radio" name="status" value="-1" <c:if test="${system.status!=1}">checked</c:if>>
				<label for="status_0">锁定 </label>
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
        url: '${basePath}/manage/system/update/${system.systemId}',
        data: $('#updateForm').serialize(),
        beforeSend: function() {
            if ($('#title').val() == '') {
                $('#title').focus();
                return false;
            }
            if ($('#name').val() == '') {
                $('#name').focus();
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
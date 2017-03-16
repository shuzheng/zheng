<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<div id="organizationDialog" class="crudDialog">
	<form id="organizationForm" method="post">
		<div class="form-group">
			<select id="organizationId" name="organizationId" multiple="multiple" style="width: 100%">
				<c:forEach var="upmsOrganization" items="${upmsOrganizations}">
					<option value="${upmsOrganization.organizationId}" <c:forEach var="upmsUserOrganization" items="${upmsUserOrganizations}"><c:if test="${upmsOrganization.organizationId==upmsUserOrganization.organizationId}">selected="selected"</c:if></c:forEach>>${upmsOrganization.name}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group text-right dialog-buttons">
			<a class="waves-effect waves-button" href="javascript:;" onclick="organizationSubmit();">保存</a>
			<a class="waves-effect waves-button" href="javascript:;" onclick="organizationDialog.close();">取消</a>
		</div>
	</form>
</div>
<script>
function organizationSubmit() {
    $.ajax({
        type: 'post',
        url: '${basePath}/manage/user/organization/' + organizationUserId,
        data: $('#organizationForm').serialize(),
		beforeSend: function() {

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
				organizationDialog.close();
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
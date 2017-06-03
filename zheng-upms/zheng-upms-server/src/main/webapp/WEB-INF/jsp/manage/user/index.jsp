<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>用户管理</title>
	<jsp:include page="/resources/inc/head.jsp" flush="true"/>
</head>
<body>
<div id="main">
	<div id="toolbar">
		<shiro:hasPermission name="upms:user:create"><a class="waves-effect waves-button" href="javascript:;" onclick="createAction()"><i class="zmdi zmdi-plus"></i> 新增用户</a></shiro:hasPermission>
		<shiro:hasPermission name="upms:user:update"><a class="waves-effect waves-button" href="javascript:;" onclick="updateAction()"><i class="zmdi zmdi-edit"></i> 编辑用户</a></shiro:hasPermission>
		<shiro:hasPermission name="upms:user:delete"><a class="waves-effect waves-button" href="javascript:;" onclick="deleteAction()"><i class="zmdi zmdi-close"></i> 删除用户</a></shiro:hasPermission>
		<shiro:hasPermission name="upms:user:organization"><a class="waves-effect waves-button" href="javascript:;" onclick="organizationAction()"><i class="zmdi zmdi-accounts-list"></i> 用户组织</a></shiro:hasPermission>
		<shiro:hasPermission name="upms:user:role"><a class="waves-effect waves-button" href="javascript:;" onclick="roleAction()"><i class="zmdi zmdi-accounts"></i> 用户角色</a></shiro:hasPermission>
		<shiro:hasPermission name="upms:user:permission"><a class="waves-effect waves-button" href="javascript:;" onclick="permissionAction()"><i class="zmdi zmdi-key"></i> 用户权限</a></shiro:hasPermission>
	</div>
	<table id="table"></table>
</div>
<jsp:include page="/resources/inc/footer.jsp" flush="true"/>
<script>
var $table = $('#table');
$(function() {
	// bootstrap table初始化
	$table.bootstrapTable({
		url: '${basePath}/manage/user/list',
		height: getHeight(),
		striped: true,
		search: true,
		showRefresh: true,
		showColumns: true,
		minimumCountColumns: 2,
		clickToSelect: true,
		detailView: true,
		detailFormatter: 'detailFormatter',
		pagination: true,
		paginationLoop: false,
		sidePagination: 'server',
		silentSort: false,
		smartDisplay: false,
		escape: true,
		searchOnEnterKey: true,
		idField: 'userId',
		maintainSelected: true,
		toolbar: '#toolbar',
		columns: [
			{field: 'ck', checkbox: true},
			{field: 'userId', title: '编号', sortable: true, align: 'center'},
            {field: 'username', title: '帐号'},
			{field: 'realname', title: '姓名'},
			{field: 'avatar', title: '头像', align: 'center', formatter: 'avatarFormatter'},
			{field: 'phone', title: '电话'},
			{field: 'email', title: '邮箱'},
			{field: 'sex', title: '性别', formatter: 'sexFormatter'},
			{field: 'locked', title: '状态', sortable: true, align: 'center', formatter: 'lockedFormatter'},
			{field: 'action', title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
		]
	});
});
// 格式化操作按钮
function actionFormatter(value, row, index) {
    return [
		'<a class="update" href="javascript:;" onclick="updateAction()" data-toggle="tooltip" title="Edit"><i class="glyphicon glyphicon-edit"></i></a>　',
		'<a class="delete" href="javascript:;" onclick="deleteAction()" data-toggle="tooltip" title="Remove"><i class="glyphicon glyphicon-remove"></i></a>'
    ].join('');
}
// 格式化图标
function avatarFormatter(value, row, index) {
    return '<img src="${basePath}' + value + '" style="width:20px;height:20px;"/>';
}
// 格式化性别
function sexFormatter(value, row, index) {
	if (value == 1) {
		return '男';
	}
	if (value == 2) {
		return '女';
	}
	return '-';
}
// 格式化状态
function lockedFormatter(value, row, index) {
	if (value == 1) {
		return '<span class="label label-default">锁定</span>';
	} else {
		return '<span class="label label-success">正常</span>';
	}
}
// 新增
var createDialog;
function createAction() {
	createDialog = $.dialog({
		animationSpeed: 300,
		title: '新增用户',
		content: 'url:${basePath}/manage/user/create',
		onContentReady: function () {
			initMaterialInput();
            initUploader();
		}
	});
}
// 编辑
var updateDialog;
function updateAction() {
	var rows = $table.bootstrapTable('getSelections');
	if (rows.length != 1) {
		$.confirm({
			title: false,
			content: '请选择一条记录！',
			autoClose: 'cancel|3000',
			backgroundDismiss: true,
			buttons: {
				cancel: {
					text: '取消',
					btnClass: 'waves-effect waves-button'
				}
			}
		});
	} else {
		updateDialog = $.dialog({
			animationSpeed: 300,
			title: '编辑用户',
			content: 'url:${basePath}/manage/user/update/' + rows[0].userId,
			onContentReady: function () {
				initMaterialInput();
                initUploader();
			}
		});
	}
}
// 删除
var deleteDialog;
function deleteAction() {
	var rows = $table.bootstrapTable('getSelections');
	if (rows.length == 0) {
		$.confirm({
			title: false,
			content: '请至少选择一条记录！',
			autoClose: 'cancel|3000',
			backgroundDismiss: true,
			buttons: {
				cancel: {
					text: '取消',
					btnClass: 'waves-effect waves-button'
				}
			}
		});
	} else {
		deleteDialog = $.confirm({
			type: 'red',
			animationSpeed: 300,
			title: false,
			content: '确认删除该用户吗？',
			buttons: {
				confirm: {
					text: '确认',
					btnClass: 'waves-effect waves-button',
					action: function () {
						var ids = new Array();
						for (var i in rows) {
							ids.push(rows[i].userId);
						}
						$.ajax({
							type: 'get',
							url: '${basePath}/manage/user/delete/' + ids.join("-"),
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
									deleteDialog.close();
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
				},
				cancel: {
					text: '取消',
					btnClass: 'waves-effect waves-button'
				}
			}
		});
	}
}
// 用户组织
var organizationDialog;
var organizationUserId;
function organizationAction() {
	var rows = $table.bootstrapTable('getSelections');
	if (rows.length != 1) {
		$.confirm({
			title: false,
			content: '请选择一条记录！',
			autoClose: 'cancel|3000',
			backgroundDismiss: true,
			buttons: {
				cancel: {
					text: '取消',
					btnClass: 'waves-effect waves-button'
				}
			}
		});
	} else {
		organizationUserId = rows[0].userId;
		organizationDialog = $.dialog({
			animationSpeed: 300,
			title: '用户组织',
			content: 'url:${basePath}/manage/user/organization/' + organizationUserId,
			onContentReady: function () {
				initMaterialInput();
				$('select').select2({
					placeholder: '请选择用户组织',
					allowClear: true
				});
			}
		});
	}
}
// 用户角色
var roleDialog;
var roleUserId;
function roleAction() {
	var rows = $table.bootstrapTable('getSelections');
	if (rows.length != 1) {
		$.confirm({
			title: false,
			content: '请选择一条记录！',
			autoClose: 'cancel|3000',
			backgroundDismiss: true,
			buttons: {
				cancel: {
					text: '取消',
					btnClass: 'waves-effect waves-button'
				}
			}
		});
	} else {
		roleUserId = rows[0].userId;
		roleDialog = $.dialog({
			animationSpeed: 300,
			title: '用户角色',
			content: 'url:${basePath}/manage/user/role/' + roleUserId,
			onContentReady: function () {
				initMaterialInput();
				$('select').select2({
					placeholder: '请选择用户角色',
					allowClear: true
				});
			}
		});
	}
}
// 用户权限
var permissionDialog;
var permissionUserId;
function permissionAction() {
	var rows = $table.bootstrapTable('getSelections');
	if (rows.length != 1) {
		$.confirm({
			title: false,
			content: '请选择一条记录！',
			autoClose: 'cancel|3000',
			backgroundDismiss: true,
			buttons: {
				cancel: {
					text: '取消',
					btnClass: 'waves-effect waves-button'
				}
			}
		});
	} else {
		permissionUserId = rows[0].userId;
		permissionDialog = $.dialog({
			animationSpeed: 300,
			title: '用户授权',
			columnClass: 'large',
			content: 'url:${basePath}/manage/user/permission/' + permissionUserId,
			onContentReady: function () {
				initMaterialInput();
				initTree();
			}
		});
	}
}
</script>
</body>
</html>
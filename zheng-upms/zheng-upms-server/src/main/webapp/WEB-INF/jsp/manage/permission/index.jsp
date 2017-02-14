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
	<title>权限管理</title>
	<link href="${basePath}/resources/zheng-ui/plugins/bootstrap-3.3.0/css/bootstrap.min.css" rel="stylesheet"/>
	<link href="${basePath}/resources/zheng-ui/plugins/material-design-iconic-font-2.2.0/css/material-design-iconic-font.min.css" rel="stylesheet"/>
	<link href="${basePath}/resources/zheng-ui/plugins/bootstrap-table-1.11.0/bootstrap-table.min.css" rel="stylesheet"/>
	<link href="${basePath}/resources/zheng-ui/plugins/waves-0.7.5/waves.min.css" rel="stylesheet"/>
	<link href="${basePath}/resources/zheng-ui/css/common.css" rel="stylesheet"/>
</head>
<body>
<div id="main">
	<div id="toolbar">
		<shiro:hasPermission name="upms:permission:create"><a class="waves-effect waves-button" href="javascript:;" data-toggle="modal" data-target=".create-modal"><i class="zmdi zmdi-plus"></i> 新增权限</a></shiro:hasPermission>
		<shiro:hasPermission name="upms:permission:update"><a class="waves-effect waves-button" href="javascript:;" onclick="updateAction()"><i class="zmdi zmdi-edit"></i> 编辑权限</a></shiro:hasPermission>
		<shiro:hasPermission name="upms:permission:delete"><a class="waves-effect waves-button" href="javascript:;" onclick="deleteAction()"><i class="zmdi zmdi-close"></i> 删除权限</a></shiro:hasPermission>
	</div>
	<table id="table"></table>
</div>
<!-- 新增 -->
<div class="modal fade create-modal" tabindex="-1" role="dialog">
	<div class="modal-dialog modal-sm" role="document">
		<div class="modal-content">
			<div class="modal-body">
				内容
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary" onclick="createAction()">保存</button>
			</div>
		</div>
	</div>
</div>
<script src="${basePath}/resources/zheng-ui/plugins/jquery.1.12.4.min.js"></script>
<script src="${basePath}/resources/zheng-ui/plugins/bootstrap-3.3.0/js/bootstrap.min.js"></script>
<script src="${basePath}/resources/zheng-ui/plugins/bootstrap-table-1.11.0/bootstrap-table.min.js"></script>
<script src="${basePath}/resources/zheng-ui/plugins/bootstrap-table-1.11.0/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="${basePath}/resources/zheng-ui/plugins/waves-0.7.5/waves.min.js"></script>
<script src="${basePath}/resources/zheng-ui/js/common.js"></script>
<script>
$(function() {
	// bootstrap table初始化
	$('#table').bootstrapTable({
		url: '${basePath}/manage/permission/list',
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
		idField: 'permissionId',
		maintainSelected: true,
		toolbar: '#toolbar',
		columns: [
			{field: 'state', checkbox: true},
			{field: 'permissionId', title: '编号', sortable: true, align: 'center'},
            {field: 'systemId', title: '所属系统'},
			{field: 'pid', title: '所属上级'},
			{field: 'name', title: '权限名称'},
			{field: 'type', title: '类型', formatter: 'typeFormatter'},
			{field: 'permissionValue', title: '权限值'},
			{field: 'uri', title: '路径'},
			{field: 'icon', title: '图标', align: 'center', formatter: 'iconFormatter'},
			{field: 'status', title: '状态', sortable: true, align: 'center', formatter: 'statusFormatter'},
			{field: 'action', title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
		]
	}).on('all.bs.table', function (e, name, args) {
		$('[data-toggle="tooltip"]').tooltip();
		$('[data-toggle="popover"]').popover();
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
function iconFormatter(value, row, index) {
    return '<i class="' + value + '"></i>';
}
// 格式化类型
function typeFormatter(value, row, index) {
	if (value == 1) {
		return '菜单';
	}
	if (value == 2) {
		return '按钮';
	}
	return '-';
}
// 格式化状态
function statusFormatter(value, row, index) {
	if (value == 1) {
		return '<span class="label label-success">正常</span>';
	} else {
		return '<span class="label label-danger">锁定</span>';
	}
}
// 新增
function createAction() {

}
// 编辑
function updateAction() {
	var rows = $table.bootstrapTable('getSelections');
}
// 删除
function deleteAction() {
	var rows = $table.bootstrapTable('getSelections');
}
</script>
</body>
</html>
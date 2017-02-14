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
	<link href="${basePath}/resources/zheng-ui/plugins/bootstrap-3.3.0/css/bootstrap.min.css" rel="stylesheet"/>
	<link href="${basePath}/resources/zheng-ui/plugins/material-design-iconic-font-2.2.0/css/material-design-iconic-font.min.css" rel="stylesheet"/>
	<link href="${basePath}/resources/zheng-ui/plugins/bootstrap-table-1.11.0/bootstrap-table.min.css" rel="stylesheet"/>
	<link href="${basePath}/resources/zheng-ui/plugins/waves-0.7.5/waves.min.css" rel="stylesheet"/>
	<link href="${basePath}/resources/zheng-ui/css/common.css" rel="stylesheet"/>
</head>
<body>
<div id="main">
	<div id="toolbar">
		<shiro:hasPermission name="upms:user:create"><a class="waves-effect waves-button" href="javascript:;" data-toggle="modal" data-target=".create-modal"><i class="zmdi zmdi-plus"></i> 新增用户</a></shiro:hasPermission>
		<shiro:hasPermission name="upms:user:update"><a class="waves-effect waves-button" href="javascript:;" onclick="updateAction()"><i class="zmdi zmdi-edit"></i> 编辑用户</a></shiro:hasPermission>
		<shiro:hasPermission name="upms:user:delete"><a class="waves-effect waves-button" href="javascript:;" onclick="deleteAction()"><i class="zmdi zmdi-close"></i> 删除用户</a></shiro:hasPermission>
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
			{field: 'state', checkbox: true},
			{field: 'userId', title: '编号', sortable: true, align: 'center'},
            {field: 'username', title: '帐号'},
			{field: 'realname', title: '姓名'},
			{field: 'avatar', title: '头像', align: 'center', formatter: 'avatarFormatter'},
			{field: 'phone', title: '电话'},
			{field: 'email', title: '邮箱'},
			{field: 'sex', title: '性别', formatter: 'sexFormatter'},
			{field: 'locked', title: '状态', sortable: true, align: 'center', formatter: 'statusFormatter'},
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
function statusFormatter(value, row, index) {
	if (value == 1) {
		return '<span class="label label-danger">锁定</span>';
	} else {
		return '<span class="label label-success">正常</span>';
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
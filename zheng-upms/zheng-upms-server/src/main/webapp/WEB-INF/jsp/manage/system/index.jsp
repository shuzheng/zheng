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
	<title>系统管理</title>
	<link href="${basePath}/resources/zheng-ui/plugins/bootstrap-3.3.0/css/bootstrap.min.css" rel="stylesheet"/>
	<link href="${basePath}/resources/zheng-ui/plugins/material-design-iconic-font-2.2.0/css/material-design-iconic-font.min.css" rel="stylesheet"/>
	<link href="${basePath}/resources/zheng-ui/plugins/bootstrap-table-1.11.0/bootstrap-table.min.css" rel="stylesheet"/>
	<link href="${basePath}/resources/zheng-ui/plugins/waves-0.7.5/waves.min.css" rel="stylesheet"/>
	<link href="${basePath}/resources/zheng-ui/plugins/jquery-confirm/jquery-confirm.min.css" rel="stylesheet"/>
	<link href="${basePath}/resources/zheng-ui/css/common.css" rel="stylesheet"/>
</head>
<body>
<div id="main">
	<div id="toolbar">
		<shiro:hasPermission name="upms:system:create"><a class="waves-effect waves-button" href="javascript:;" onclick="createAction()"><i class="zmdi zmdi-plus"></i> 新增系统</a></shiro:hasPermission>
		<shiro:hasPermission name="upms:system:update"><a class="waves-effect waves-button" href="javascript:;" onclick="updateAction()"><i class="zmdi zmdi-edit"></i> 编辑系统</a></shiro:hasPermission>
		<shiro:hasPermission name="upms:system:delete"><a class="waves-effect waves-button" href="javascript:;" onclick="deleteAction()"><i class="zmdi zmdi-close"></i> 删除系统</a></shiro:hasPermission>
	</div>
	<table id="table"></table>
</div>
<script src="${basePath}/resources/zheng-ui/plugins/jquery.1.12.4.min.js"></script>
<script src="${basePath}/resources/zheng-ui/plugins/bootstrap-3.3.0/js/bootstrap.min.js"></script>
<script src="${basePath}/resources/zheng-ui/plugins/bootstrap-table-1.11.0/bootstrap-table.min.js"></script>
<script src="${basePath}/resources/zheng-ui/plugins/bootstrap-table-1.11.0/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="${basePath}/resources/zheng-ui/plugins/waves-0.7.5/waves.min.js"></script>
<script src="${basePath}/resources/zheng-ui/plugins/jquery-confirm/jquery-confirm.min.js"></script>
<script src="${basePath}/resources/zheng-ui/js/common.js"></script>
<script>
var $table = $('#table');
$(function() {
	$(document).on('focus', 'input[type="text"]', function() {
		$(this).parent().find('label').addClass('active');
	}).on('blur', 'input[type="text"]', function() {
		if ($(this).val() == '') {
			$(this).parent().find('label').removeClass('active');
		}
	});
	// bootstrap table初始化
	$table.bootstrapTable({
		url: '${basePath}/manage/system/list',
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
		idField: 'systemId',
		maintainSelected: true,
		toolbar: '#toolbar',
		columns: [
			{field: 'state', checkbox: true},
			{field: 'systemId', title: '编号', sortable: true, align: 'center'},
			{field: 'icon', title: '图标', sortable: true, align: 'center', formatter: 'iconFormatter'},
            {field: 'title', title: '系统标题'},
			{field: 'name', title: '系统名称'},
			{field: 'basepath', title: '根目录'},
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
	$.confirm({
		animationSpeed: 300,
		title: '新增系统',
		content: 'url:${basePath}/manage/system/create',
		buttons: {
			confirm: {
				text: '确认',
				btnClass: 'waves-effect waves-button',
				action: function () {
					console.log(this.find('form').html());
				}
			},
			cancel: {
				text: '取消',
				btnClass: 'waves-effect waves-button'
			}
		}
	});
}
// 编辑
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
		$.confirm({
			animationSpeed: 300,
			title: '编辑系统',
			content: 'url:${basePath}/manage/system/update/' + rows[0].systemId,
			buttons: {
				confirm: {
					text: '确认',
					btnClass: 'waves-effect waves-button',
					action: function () {
						$.alert('确认');
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
// 删除
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
		$.confirm({
			type: 'red',
			animationSpeed: 300,
			title: false,
			content: '确认删除该系统吗？',
			buttons: {
				confirm: {
					text: '确认',
					btnClass: 'waves-effect waves-button',
					action: function () {
						var ids = new Array();
						for (var i in rows) {
							ids.push(rows[i].systemId);
						}
						$.alert('删除：id=' + ids.join("-"));
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
</script>
</body>
</html>
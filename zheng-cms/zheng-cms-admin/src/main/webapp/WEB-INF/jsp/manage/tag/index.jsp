<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>标签列表</title>

	<link href="${basePath}/resources/zheng-admin/plugins/bootstrap-3.3.0/css/bootstrap.min.css" rel="stylesheet"/>
	<link href="${basePath}/resources/zheng-admin/plugins/material-design-iconic-font-2.2.0/css/material-design-iconic-font.min.css" rel="stylesheet"/>
	<link href="${basePath}/resources/zheng-admin/plugins/bootstrap-table-1.11.0/bootstrap-table.min.css" rel="stylesheet"/>
	<link href="${basePath}/resources/zheng-admin/plugins/waves-0.7.5/waves.min.css" rel="stylesheet"/>

	<link href="${basePath}/resources/zheng-admin/css/common.css" rel="stylesheet"/>
</head>
<body>
<div id="main">
	<div id="toolbar">
		<a class="waves-effect waves-button" href="javascript:;"><i class="zmdi zmdi-plus"></i> 新增标签</a>
		<a class="waves-effect waves-button" href="javascript:;"><i class="zmdi zmdi-edit"></i> 编辑标签</a>
		<a class="waves-effect waves-button" href="javascript:;"><i class="zmdi zmdi-close"></i> 删除标签</a>
	</div>
	<table id="table"></table>
</div>
<script src="${basePath}/resources/zheng-admin/plugins/jquery.1.12.4.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/bootstrap-3.3.0/js/bootstrap.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/bootstrap-table-1.11.0/bootstrap-table.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/bootstrap-table-1.11.0/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/waves-0.7.5/waves.min.js"></script>

<script src="${basePath}/resources/zheng-admin/js/common.js"></script>
<script>
	$(function() {
		// bootstrap table初始化
		$('#table').bootstrapTable({
			url: '${basePath}/manage/tag/list',
			height: getHeight(),
			striped: true,
			search: true,
			searchOnEnterKey: true,
			showRefresh: true,
			showToggle: true,
			showColumns: true,
			minimumCountColumns: 2,
			showPaginationSwitch: true,
			clickToSelect: true,
			detailView: true,
			detailFormatter: 'detailFormatter',
			pagination: true,
			paginationLoop: false,
			classes: 'table table-hover table-no-bordered',
			sidePagination: 'server',
			idField: 'tag_id',
			sortName: 'tag_id',
			sortOrder: 'desc',
			toolbar: '#toolbar',
			columns: [
				{field: 'state', checkbox: true},
				{field: 'tagId', title: '编号', sortable: true, halign: 'center'},
				{field: 'name', title: '标签名称', sortable: true, halign: 'center'},
				{field: 'description', title: '标签描述', sortable: true, halign: 'center'},
				{field: 'icon', title: '图标', sortable: true, halign: 'center'},
				{field: 'type', title: '类型', sortable: true, halign: 'center'},
				{field: 'alias', title: '别名', sortable: true, halign: 'center'},
				{field: 'ctime', title: '创建时间', sortable: true, halign: 'center'},
				{field: 'action', title: '操作', halign: 'center', align: 'center', formatter: 'actionFormatter', events: 'actionEvents'}
			]
		}).on('all.bs.table', function (e, name, args) {
			$('[data-toggle="tooltip"]').tooltip();
			$('[data-toggle="popover"]').popover();
		});
		$(window).resize(function () {
			$('#table').bootstrapTable('resetView', {
				height: getHeight()
			});
		});
	});
	function actionFormatter(value, row, index) {
		return [
			'<a class="like" href="javascript:void(0)" data-toggle="tooltip" title="Like"><i class="glyphicon glyphicon-heart"></i></a>　',
			'<a class="edit ml10" href="javascript:void(0)" data-toggle="tooltip" title="Edit"><i class="glyphicon glyphicon-edit"></i></a>　',
			'<a class="remove ml10" href="javascript:void(0)" data-toggle="tooltip" title="Remove"><i class="glyphicon glyphicon-remove"></i></a>'
		].join('');
	}

	window.actionEvents = {
		'click .like': function (e, value, row, index) {
			alert('You click like icon, row: ' + JSON.stringify(row));
			console.log(value, row, index);
		},
		'click .edit': function (e, value, row, index) {
			alert('You click edit icon, row: ' + JSON.stringify(row));
			console.log(value, row, index);
		},
		'click .remove': function (e, value, row, index) {
			alert('You click remove icon, row: ' + JSON.stringify(row));
			console.log(value, row, index);
		}
	};
	function detailFormatter(index, row) {
		var html = [];
		$.each(row, function (key, value) {
			html.push('<p><b>' + key + ':</b> ' + value + '</p>');
		});
		return html.join('');
	}
	function getHeight() {
		return $(window).height() - 20;
	}
</script>
</body>
</html>
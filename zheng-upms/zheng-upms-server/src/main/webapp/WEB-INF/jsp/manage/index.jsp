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
	<title>用户权限管理系统</title>

	<link href="${basePath}/resources/zheng-admin/plugins/bootstrap-3.3.0/css/bootstrap.min.css" rel="stylesheet"/>
	<link href="${basePath}/resources/zheng-admin/plugins/material-design-iconic-font-2.2.0/css/material-design-iconic-font.min.css" rel="stylesheet"/>
	<link href="${basePath}/resources/zheng-admin/plugins/waves-0.7.5/waves.min.css" rel="stylesheet"/>
	<link href="${basePath}/resources/zheng-admin/plugins/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.min.css" rel="stylesheet"/>
	<link href="${basePath}/resources/zheng-admin/plugins/fullPage/jquery.fullPage.css" rel="stylesheet"/>
	<link href="${basePath}/resources/zheng-admin/css/admin.css" rel="stylesheet"/>
</head>
<body>
<header id="header">
	<ul id="menu">
		<li id="guide" class="line-trigger">
			<div class="line-wrap">
				<div class="line top"></div>
				<div class="line center"></div>
				<div class="line bottom"></div>
			</div>
		</li>
		<li id="logo" class="hidden-xs">
			<a href="index.jsp">
				<img src="${basePath}/resources/zheng-admin/images/logo.png"/>
			</a>
		</li>
		<li class="pull-right">
			<ul class="hi-menu">
				<!-- 搜索 -->
				<li class="dropdown">
					<a class="waves-effect waves-light" data-toggle="dropdown" href="javascript:;">
						<i class="him-icon zmdi zmdi-search"></i>
					</a>
					<ul class="dropdown-menu dm-icon pull-right">
						<form id="search-form" class="form-inline">
							<div class="input-group">
								<input id="keywords" type="text" name="keywords" class="form-control" placeholder="搜索"/>
								<div class="input-group-btn">
									<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
								</div>
							</div>
						</form>
					</ul>
				</li>
				<li class="dropdown">
					<a class="waves-effect waves-light" data-toggle="dropdown" href="javascript:;">
						<i class="him-icon zmdi zmdi-dropbox"></i>
					</a>
					<ul class="dropdown-menu dm-icon pull-right">
						<li class="skin-switch hidden-xs">
							请选择系统切换
						</li>
						<li class="divider hidden-xs"></li>
						<c:forEach var="upmsSystem" items="${upmsSystems}">
						<li>
							<a class="waves-effect" href="javascript:;"><i class="${upmsSystem.icon}"></i> ${upmsSystem.name}</a>
						</li>
						</c:forEach>
					</ul>
				</li>
				<li class="dropdown">
					<a class="waves-effect waves-light" data-toggle="dropdown" href="javascript:;">
						<i class="him-icon zmdi zmdi-more-vert"></i>
					</a>
					<ul class="dropdown-menu dm-icon pull-right">
						<li class="hidden-xs">
							<a class="waves-effect" data-ma-action="fullscreen" href="javascript:fullPage();"><i class="zmdi zmdi-fullscreen"></i> 全屏模式</a>
						</li>
						<li>
							<a class="waves-effect" data-ma-action="clear-localstorage" href="javascript:;"><i class="zmdi zmdi-delete"></i> 清除缓存</a>
						</li>
						<li>
							<a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-face"></i> 隐私管理</a>
						</li>
						<li>
							<a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-settings"></i> 系统设置</a>
						</li>
						<li>
							<a class="waves-effect" href="${basePath}/sso/logout"><i class="zmdi zmdi-run"></i> 退出登录</a>
						</li>
					</ul>
				</li>
			</ul>
		</li>
	</ul>
</header>
<section id="main">
	<!-- 左侧导航区 -->
	<aside id="sidebar">
		<!-- 个人资料区 -->
		<div class="s-profile">
			<a class="waves-effect waves-light" href="javascript:;">
				<div class="sp-pic">
					<img src="${basePath}/resources/zheng-admin/images/avatar.jpg"/>
				</div>
				<div class="sp-info">
					张恕征，您好！
					<i class="zmdi zmdi-caret-down"></i>
				</div>
			</a>
			<ul class="main-menu">
				<li>
					<a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-account"></i> 个人资料</a>
				</li>
				<li>
					<a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-face"></i> 隐私管理</a>
				</li>
				<li>
					<a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-settings"></i> 系统设置</a>
				</li>
				<li>
					<a class="waves-effect" href="${basePath}/sso/logout"><i class="zmdi zmdi-run"></i> 退出登录</a>
				</li>
			</ul>
		</div>
		<!-- /个人资料区 -->
		<!-- 菜单区 -->
		<ul class="main-menu">
			<li>
				<a class="waves-effect" href="javascript:Tab.addTab('首页', 'home');"><i class="zmdi zmdi-home"></i> 首页</a>
			</li>
			<shiro:hasPermission name="upms:system,upms:organization">
			<li class="sub-menu">
				<a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-widgets"></i> 系统组织管理</a>
				<ul>
					<shiro:hasPermission name="upms:system:read"><li><a class="waves-effect" href="javascript:Tab.addTab('系统管理', '${basePath}/system/index');">系统管理</a></li></shiro:hasPermission>
					<shiro:hasPermission name="upms:organization:read"><li><a class="waves-effect" href="javascript:Tab.addTab('组织管理', '${basePath}/organization/index');">组织管理</a></li></shiro:hasPermission>
				</ul>
			</li>
			</shiro:hasPermission>
			<shiro:hasPermission name="upms:user,upms:role">
			<li class="sub-menu">
				<a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-widgets"></i> 用户角色管理</a>
				<ul>
					<shiro:hasPermission name="upms:user:read"><li><a class="waves-effect" href="javascript:Tab.addTab('用户管理', '${basePath}/user/index');">用户管理</a></li></shiro:hasPermission>
					<shiro:hasPermission name="upms:role:read"><li><a class="waves-effect" href="javascript:Tab.addTab('角色管理', '${basePath}/role/index');">角色管理</a></li></shiro:hasPermission>
				</ul>
			</li>
			</shiro:hasPermission>
			<shiro:hasPermission name="upms:permission">
			<li class="sub-menu">
				<a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-widgets"></i> 权限资源管理</a>
				<ul>
					<shiro:hasPermission name="upms:role:read"><li><a class="waves-effect" href="javascript:Tab.addTab('权限管理', '${basePath}/permission/index');">权限管理</a></li></shiro:hasPermission>
				</ul>
			</li>
			</shiro:hasPermission>
			<shiro:hasPermission name="upms:role_permission,upms:user_permission">
			<li class="sub-menu">
				<a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-widgets"></i> 权限分配管理</a>
				<ul>
					<shiro:hasPermission name="upms:role_permission:read"><li><a class="waves-effect" href="javascript:Tab.addTab('角色权限', '${basePath}/role_permission/index');">角色权限</a></li></shiro:hasPermission>
					<shiro:hasPermission name="upms:user_permission:read"><li><a class="waves-effect" href="javascript:Tab.addTab('用户权限', '${basePath}/user_permission/index');">用户权限</a></li></shiro:hasPermission>
				</ul>
			</li>
			</shiro:hasPermission>
			<shiro:hasPermission name="upms:coder,upms:session,upms:log">
			<li class="sub-menu">
				<a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-widgets"></i> 系统数据管理</a>
				<ul>
					<shiro:hasPermission name="upms:coder:read"><li><a class="waves-effect" href="javascript:Tab.addTab('公共码表', '${basePath}/coder/index');">公共码表</a></li></shiro:hasPermission>
					<shiro:hasPermission name="upms:session:read"><li><a class="waves-effect" href="javascript:Tab.addTab('会话管理', '${basePath}/session/index');">会话管理</a></li></shiro:hasPermission>
					<shiro:hasPermission name="upms:log:read"><li><a class="waves-effect" href="javascript:Tab.addTab('日志记录', '${basePath}/log/index');">日志记录</a></li></shiro:hasPermission>
				</ul>
			</li>
			</shiro:hasPermission>
			<li>
				<div class="upms-version">&copy; ZHENG-UPMS V1.0.0</div>
			</li>
		</ul>
		<!-- /菜单区 -->
	</aside>
	<!-- /左侧导航区 -->
	<section id="content">
		<div class="content_tab">
			<div class="tab_left">
				<a class="waves-effect waves-light" href="javascript:;"><i class="zmdi zmdi-chevron-left"></i></a>
			</div>
			<div class="tab_right">
				<a class="waves-effect waves-light" href="javascript:;"><i class="zmdi zmdi-chevron-right"></i></a>
			</div>
			<ul id="tabs" class="tabs">
				<li id="tab_home" data-index="home" data-closeable="false" class="cur">
					<a class="waves-effect waves-light" href="javascript:;">首页</a>
				</li>
			</ul>
		</div>
		<div class="content_main">
			<div id="iframe_home" class="iframe cur">
				<p><h4>通用用户权限管理系统</h4></p>
				<p><b>演示地址</b>：<a href="http://www.zhangshuzheng.cn/upms" target="_blank">http://www.zhangshuzheng.cn/upms</a></p>
				<p><b>系统简介</b>：本系统是基于RBAC授权和基于用户授权的细粒度权限控制通用平台，并提供单点登录、会话管理和日志管理。接入的系统可自由定义组织、角色、权限、资源等。</p><br/>
				<p><h4>系统功能概述：</h4></p>
				<p><b>系统组织管理</b>：系统和组织增加、删除、修改、查询功能。</p>
				<p><b>用户角色管理</b>：用户和角色增加、删除、修改、查询功能。</p>
				<p><b>资源权限管理</b>：资源和权限增加、删除、修改、查询功能。</p>
				<p><b>权限分配管理</b>：提供给角色和用户的权限增加、删除、修改、查询功能。</p>
				<p><b>单点登录(SSO)</b>：提供统一用户单点登录认证、用户鉴权功能。</p>
				<p><b>用户会话管理</b>：提供分布式用户会话管理</p>
				<p><b>操作日志管理</b>：提供记录用户登录、操作等日志。</p><br/>
				<p><h4>对外接口概述：</h4></p>
				<p><b>系统组织数据接口</b>、<b>用户角色数据接口</b>、<b>资源权限数据接口</b>、<b>单点登录(SSO)接口</b>、<b>用户鉴权接口</b></p><br/>
				<p><h4>关于作者</h4></p>
				<p><b>姓　　名</b>：张恕征</p>
				<p><b>电子邮箱</b>：469741414@qq.com</p>
			</div>
		</div>
	</section>
</section>
<footer id="footer"></footer>

<script src="${basePath}/resources/zheng-admin/plugins/jquery.1.12.4.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/bootstrap-3.3.0/js/bootstrap.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/waves-0.7.5/waves.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/BootstrapMenu.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/device.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/fullPage/jquery.fullPage.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/fullPage/jquery.jdirk.min.js"></script>
<script src="${basePath}/resources/zheng-admin/js/admin.js"></script>
</body>
</html>
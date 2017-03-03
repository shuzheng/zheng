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
    <title>内容管理系统</title>

    <link href="${basePath}/resources/zheng-ui/plugins/bootstrap-3.3.0/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${basePath}/resources/zheng-ui/plugins/material-design-iconic-font-2.2.0/css/material-design-iconic-font.min.css" rel="stylesheet"/>
    <link href="${basePath}/resources/zheng-ui/plugins/waves-0.7.5/waves.min.css" rel="stylesheet"/>
    <link href="${basePath}/resources/zheng-ui/plugins/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.min.css" rel="stylesheet"/>
    <link href="${basePath}/resources/zheng-ui/css/admin.css" rel="stylesheet"/>

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
            <a href="${basePath}/manage/index">
                <img src="${basePath}/resources/zheng-ui/images/logo.png"/>
            </a>
            <span id="system_title">内容管理系统</span>
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
                       <%-- <c:forEach var="upmsSystem" items="${upmsSystems}">
                            <li>
                                <a class="waves-effect switch-systems" href="javascript:;" systemid="${upmsSystem.systemId}" systemtitle="${upmsSystem.title}"><i class="${upmsSystem.icon}"></i> ${upmsSystem.title}</a>
                            </li>
                        </c:forEach>--%>
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
                    <!--头像URL的值-->
                    <img src=""/>
                </div>
                <div class="sp-info">

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
        <ul class="main-menu menu-list">
            <li>
                <a class="waves-effect" href="javascript:Tab.addTab('首页', 'home');"><i class="zmdi zmdi-home"></i> 首页</a>
            </li>

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
                <p><h4>通用内容管理系统</h4></p>
                <p><b>演示地址</b>：<a href="http://www.zhangshuzheng.cn/zhengAdmin" target="_blank">http://www.zhangshuzheng.cn/zhengAdmin</a></p>
                <p><b>系统简介</b>：内容管理系统：支持多标签、多类目、强大评论的内容管理，有基本单页展示，菜单管理，系统设置等功能。</p><br/>
                <p><h4>关于作者</h4></p>
                <p><b>姓　　名</b>：张恕征</p>
                <p><b>电子邮箱</b>：469741414@qq.com</p>
            </div>
        </div>
    </section>
</section>
<input id="sso_server_url" type="hidden">
<footer id="footer"></footer>
<script id="menuListScriptTemplate" type="text/html">
    {{each data.upmsPermissions as upmsPermission index}}
    {{if upmsPermission.pid == 0 }}
            <li class="sub-menu system_menus system_{{upmsPermission.systemId}} {{index}}">
                <a class="waves-effect" href="javascript:;"><i class="{{upmsPermission.icon}}"></i> {{upmsPermission.name}}</a>
                <ul>
                    {{each data.upmsPermissions as subUpmsPermission}}
                    {{if subUpmsPermission.pid == upmsPermission.permissionId }}
                            <li><a class="waves-effect" href="javascript:Tab.addTab('{{subUpmsPermission.name}}', '${basePath}{{subUpmsPermission.uri}}');">{{subUpmsPermission.name}}</a></li>
                    {{/if}}
                    {{/each}}
                </ul>
            </li>
       {{/if}}
    {{/each}}
</script>
<!--系统遍历列表-->
<script id="upmsSysyemListTemplate" type="text/html">
    {{each data.upmsSystems as upmsSystem}}
    <li>
        <a class="waves-effect switch-systems" href="javascript:;" systemid="{{upmsSystem.systemId}}" systemtitle="{{upmsSystem.title}}"><i class="{{upmsSystem.icon}}"></i> {{upmsSystem.title}}</a>
    </li>
    {{/each}}
</script>
<script src="${basePath}/resources/zheng-ui/plugins/jquery.1.12.4.min.js"></script>
<script src="${basePath}/resources/zheng-ui/plugins/bootstrap-3.3.0/js/bootstrap.min.js"></script>
<script src="${basePath}/resources/zheng-ui/plugins/waves-0.7.5/waves.min.js"></script>
<script src="${basePath}/resources/zheng-ui/plugins/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="${basePath}/resources/zheng-ui/plugins/BootstrapMenu.min.js"></script>
<script src="${basePath}/resources/zheng-ui/plugins/device.min.js"></script>
<script src="${basePath}/resources/zheng-ui/js/admin.js"></script>
<script src="${basePath}/resources/zheng-ui/plugins/fullPage/jquery.fullPage.min.js"></script>
<script src="${basePath}/resources/zheng-ui/plugins/fullPage/jquery.jdirk.min.js"></script>
<script src="${basePath}/resources/zheng-ui/plugins/template/template.js"></script>
<script>
    $("#sso_server_url").val($.cookie('sso_server_url'));
    $.ajax({
        url : $("#sso_server_url").val() + "/manage/api/index",
        dataType: "jsonp",
        data:{systemId:2},
        jsonpCallback: "callback"
    });
    function callback(msg) {
        if(msg.code == 1){
            var umpsSystemTemplateHtml = template('upmsSysyemListTemplate', {data:msg.data});
            var menuListTemplateHtml = template('menuListScriptTemplate', {data:msg.data});
            $(".divider").after(umpsSystemTemplateHtml);
            $(".menu-list > li:eq(0)").after(menuListTemplateHtml);
            $(".sp-pic > img").attr("src",msg.data.upmsUser.avatar);
            $(".sp-info > i:eq(0)").before(msg.data.upmsUser.realname+"，您好！");
        }
    }
</script>
</body>
</html>
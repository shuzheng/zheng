<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <title>首页 - ZhengCms</title>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <meta name="renderer" content="webkit"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="shortcut icon" href="${basePath}/resources/favicon.ico"/>
    <link rel="stylesheet" href="${basePath}/resources/css/bootstrap.min.css"/>
    <!--[if lt IE 9]>
    <script src="${basePath}/resources/js/vendor/html5shiv.js"></script>
    <script src="${basePath}/resources/js/vendor/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
        if (/msie [6|7|8]/i.test(navigator.userAgent)) {};
    </script>
    <link rel="stylesheet" href="${basePath}/resources/css/main.css"/>
</head>
<body>
<!-- .navbar -->
<nav class="navbar navbar-inverse navbar-fixed-top"><!--navbar-inverse-->
    <div class="container">
        <!-- navbar-header -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#nav-menu">
                <span class="sr-only">切换菜单</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${basePath}/" title="zhengCMS"><span id="logo-text-left">zheng</span><span id="logo-text-right">CMS</span></a>
            <p class="navbar-text hidden-sm">最简约的内容管理系统</p>
        </div>
        <!-- /.navbar-header -->
        <!-- navbar-collapse -->
        <div class="collapse navbar-collapse" id="nav-menu">
            <ul class="nav navbar-nav">
                <li class="active"><a href="${basePath}/">首页</a>
                <c:forEach var="menu" items="${menus}">
                    <c:if test="${menu.pid==null}">
                    <li><a href="<c:url value="${menu.url}"/>" target="${menu.target}" class="dropdown-toggle" data-toggle="dropdown">${menu.name}</a>
                        <ul class="dropdown-menu" role="menu">
                        <c:forEach var="subMenu" items="${menus}">
                            <c:if test="${menu.menuId==subMenu.pid}">
                            <li><a href="<c:url value="${subMenu.url}"/>" target="${subMenu.target}">${subMenu.name}</a></li>
                            </c:if>
                        </c:forEach>
                        </ul>
                    </li>
                    </c:if>
                </c:forEach>
            </ul>
            <ul class="nav navbar-right navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-search"></span></a>
                    <ul class="dropdown-menu">
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
                <!--<li><a href="#">登录</a></li>
                <li><a href="#">注册</a></li>-->
                <li id="messages" class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-bell"></span>
                        <span class="badge bg-important">5</span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#"><span class="glyphicon glyphicon-envelope"></span> 我的私信(<span class="msg-txt">1</span>)</a></li>
                        <li><a href="#"><span class="glyphicon glyphicon-volume-up"></span> 系统消息(<span class="msg-txt">4</span>)</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-user"></span>
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#"><span class="glyphicon glyphicon-home"></span> 个人博客</a></li>
                        <li><a href="#"><span class="glyphicon glyphicon-question-sign"></span> 我的提问</a></li>
                        <li><a href="#"><span class="glyphicon glyphicon-ok-sign"></span> 我的回答</a></li>
                        <li><a href="#"><span class="glyphicon glyphicon-star"></span> 我的收藏</a></li>
                        <li class="divider"></li>
                        <li><a href="#"><span class="glyphicon glyphicon-cog"></span> 修改资料</a></li>
                        <li><a href="#"><span class="glyphicon glyphicon-lock"></span> 密码安全</a></li>
                        <li><a href="#"><span class="glyphicon glyphicon-log-out"></span> 安全退出</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
</nav>
<!-- /.navbar -->
<!-- .container -->
<div class="container" style="min-height: 900px;">

</div>
<!-- /.container -->
<!-- footer -->
<footer class="container-fuild" style="background:#222;padding-top:20px;min-height:100px;">
    <div class="container">
        <div class="row">
            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">

            </div>
            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">

            </div>
            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">

            </div>
            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 hidden-md">

            </div>
        </div>
    </div>
</footer>
<!-- /footer -->
<script src="${basePath}/resources/js/jquery-1.11.2.min.js"></script>
<script src="${basePath}/resources/js/bootstrap.min.js"></script>
<script src="${basePath}/resources/js/main.js"></script>
</body>
</html>
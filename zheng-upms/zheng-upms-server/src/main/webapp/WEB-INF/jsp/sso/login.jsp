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
    <title>权限管理系统</title>

    <link href="${basePath}/resources/zheng-admin/plugins/bootstrap-3.3.0/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${basePath}/resources/zheng-admin/plugins/material-design-iconic-font-2.2.0/css/material-design-iconic-font.min.css" rel="stylesheet"/>
    <link href="${basePath}/resources/zheng-admin/plugins/waves-0.7.5/waves.min.css" rel="stylesheet"/>
    <link href="${basePath}/resources/zheng-admin/plugins/waves-0.7.5/waves.min.css" rel="stylesheet"/>
    <link href="${basePath}/resources/zheng-admin/plugins/checkbix/css/checkbix.min.css" rel="stylesheet"/>
    <link href="${basePath}/resources/zheng-admin/css/login.css" rel="stylesheet"/>
</head>
<body>
<div id="login-window">
    <div class="input-group m-b-20">
        <span class="input-group-addon"><i class="zmdi zmdi-account"></i></span>
        <div class="fg-line">
            <input id="username" type="text" class="form-control" name="username" placeholder="帐号" required autofocus value="admin">
        </div>
    </div>
    <div class="input-group m-b-20">
        <span class="input-group-addon"><i class="zmdi zmdi-male"></i></span>
        <div class="fg-line">
            <input id="password" type="password" class="form-control" name="password" placeholder="密码" required value="123456">
        </div>
    </div>
    <div class="clearfix">
    </div>
    <div class="checkbox">
        <input id="rememberMe" type="checkbox" class="checkbix" data-text="自动登录" name="rememberMe">
    </div>
    <a id="login-bt" href="javascript:;" class="waves-effect waves-button waves-float"><i class="zmdi zmdi-arrow-forward"></i></a>
</div>
<script src="${basePath}/resources/zheng-admin/plugins/jquery.1.12.4.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/bootstrap-3.3.0/js/bootstrap.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/waves-0.7.5/waves.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/checkbix/js/checkbix.min.js"></script>
<script>var BASE_PATH = '${basePath}';</script>
<script>var BACK_URL = '${param.backurl}';</script>
<script src="${basePath}/resources/zheng-admin/js/login.js"></script>
<script>
<c:if test="${param.forceLogout == 1}">
alert('您已被强制下线！');
top.location.href = '${basePath}/sso/login';
</c:if>
//解决iframe下系统超时无法跳出iframe框架的问题
if (window != top){
    top.location.href = location.href;
}
</script>
</body>
</html>

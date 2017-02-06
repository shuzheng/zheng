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
    <title>登录页</title>

    <link href="${basePath}/resources/zheng-admin/plugins/bootstrap-3.3.0/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${basePath}/resources/zheng-admin/plugins/material-design-iconic-font-2.2.0/css/material-design-iconic-font.min.css" rel="stylesheet"/>
    <link href="${basePath}/resources/zheng-admin/plugins/waves-0.7.5/waves.min.css" rel="stylesheet"/>
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
        <label>
            <input id="rememberme" type="checkbox" name="rememberme">
            <i class="input-helper"></i>
            自动登录
        </label>
    </div>
    <a id="login-bt" href="javascript:;" class="waves-effect waves-button waves-float"><i class="zmdi zmdi-arrow-forward"></i></a>
</div>
<script src="${basePath}/resources/zheng-admin/plugins/jquery.1.12.4.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/bootstrap-3.3.0/js/bootstrap.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/waves-0.7.5/waves.min.js"></script>

<script src="${basePath}/resources/zheng-admin/js/login.js"></script>
<script>
    $(function() {
        // 点击登录按钮
        $('#login-bt').click(function() {
            login();
        });
        // 回车事件
        $('#username, #password').keypress(function (event) {
            if (13 == event.keyCode) {
                login();
            }
        });
    });
    // 登录
    function login() {
        $.ajax({
            url: '${basePath}/sso/login',
            type: 'POST',
            data: {
                username: $('#username').val(),
                password: $('#password').val(),
                rememberme: $('#rememberme').val(),
                backurl: '${param.backurl}'
            },
            success: function(json){
                if (json.result) {
                    location.href = json.data;
                } else {
                    if (10001 == json.data) {
                        alert("帐号不能为空！");
                        $('#username').focus();
                    }
                    if (10002 == json.data) {
                        alert("密码不能为空！");
                        $('#password').focus();
                    }
                    if (10004 == json.data) {
                        alert("该帐号不存在！");
                    }
                    if (10005 == json.data) {
                        alert("密码错误！");
                    }
                    if (10006 == json.data) {
                        alert("帐号被封！");
                    }
                }
            },
            error: function(error){
                console.log(error);
            }
        });
    }
</script>
</body>
</html>

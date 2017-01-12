<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>后台登录</title>
</head>
<body>
<form method="post">
    <c:if test="${param.error != null}">
        <p>Invalid username and password.</p>
    </c:if>
    <c:if test="${param.logout != null}">
        <p>You have been logged out.</p>
    </c:if>
    <p>
        <label for="username">Username</label>
        <input type="text" id="username" name="username" />
    </p>
    <p>
        <label for="password">Password</label>
        <input type="password" id="password" name="password" />
    </p>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <button type="submit" class="btn">Log in</button>
</form>
</body>
</html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>后台首页</title>
</head>
<body>
<ul>
    <li><a href="${basePath}/manage/tag/list">标签管理</a></li>
    <li><a href="${basePath}/manage/category/list">类目管理</a></li>
</ul>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang user</title>
</head>
<body>
	<h1>Trang của user</h1>
	<h2>Xin chào <c:out value="${sessionScope.sessionNguoiDung}" /></h2>
	<a href="${contextPath}/logout">Đăng xuất</a>
</body>
</html>
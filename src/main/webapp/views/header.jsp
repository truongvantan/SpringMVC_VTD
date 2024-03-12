<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!-- Kiểm tra người dùng đã đăng nhập hay chưa?  -->
<c:if test="${empty sessionScope.sessionNguoiDung}">
	<c:redirect url="/login?error=1" />
</c:if>
<nav class="navbar sticky-top">
	<a href="#" class="navbar-brand"> <img
		src="<c:url value="/resources/images/logo.png" />" alt="logo"
		height="50" />
	</a>
	<h2 class="text-white font-weight-bold">TÊN CỬA HÀNG</h2>
	<div class="row">
		<div class="dropdown d-flex align-items-center">
			<div
				class="logo d-inline-block rounded-circle text-center border-yellow">
				<i class="fa-solid fa-gear text-white"></i>
			</div>
			<button class="btn btn-default dropdown-toggle border-0 text-white"
				type="button" id="dropdownMenuButton" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false">
				Xin chào
				<c:out value="${sessionScope.sessionNguoiDung}" />
			</button>
			<div class="dropdown-menu dropdown-menu-right"
				aria-labelledby="dropdownMenuButton">
				<a class="dropdown-item text-danger" href="${contextPath}/logout">Đăng
					xuất</a>
			</div>
		</div>
	</div>
</nav>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Trang chủ admin</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
	integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
	integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
	crossorigin="anonymous" />
<link rel="stylesheet" href='<c:url value="/resources/css/styles.css" />' />
</head>
<body>
	
	<!-- Kiểm tra người dùng đã đăng nhập hay chưa?  -->
	<c:if test="${empty sessionScope.sessionNguoiDung}">
        <c:redirect url="/login?error=1"/>
    </c:if>

	<c:import url="/views/header.jsp"></c:import>

	<div class="container-fluid mt-3">
		<div class="row justify-content-around">
			<div class="card" style="width: 30%">
				<img class="card-img-top"
					src="https://www.cnet.com/a/img/resize/164d867001244a81a743aa47da7c37ced8f2ecc6/hub/2021/01/07/3626616c-83af-4199-a029-76db418938f0/galaxy-z-flip-fold-samsung-product-promo-hoyle-2021.jpg?auto=webp&fit=crop&height=675&width=1200"
					alt="Card image cap" />
				<div class="card-body">
					<h5 class="card-title">Sản phẩm</h5>
					<p class="card-text">Quản lí danh sách sản phẩm</p>
					<a href="${contextPath}/product/show" class="btn btn-primary">Bắt
						đầu</a>
				</div>
			</div>
			<div class="card" style="width: 30%">
				<img class="card-img-top"
					src="https://www.cnet.com/a/img/resize/164d867001244a81a743aa47da7c37ced8f2ecc6/hub/2021/01/07/3626616c-83af-4199-a029-76db418938f0/galaxy-z-flip-fold-samsung-product-promo-hoyle-2021.jpg?auto=webp&fit=crop&height=675&width=1200"
					alt="Card image cap" />
				<div class="card-body">
					<h5 class="card-title">Danh mục sản phẩm</h5>
					<p class="card-text">Quản lí danh mục sản phẩm</p>
					<a href="#" class="btn btn-primary">Bắt đầu</a>
				</div>
			</div>
			<div class="card" style="width: 30%">
				<img class="card-img-top"
					src="https://www.cnet.com/a/img/resize/164d867001244a81a743aa47da7c37ced8f2ecc6/hub/2021/01/07/3626616c-83af-4199-a029-76db418938f0/galaxy-z-flip-fold-samsung-product-promo-hoyle-2021.jpg?auto=webp&fit=crop&height=675&width=1200"
					alt="Card image cap" />
				<div class="card-body">
					<h5 class="card-title">Tài khoản</h5>
					<p class="card-text">Quản lí danh sách tài khoản</p>
					<a href="#" class="btn btn-primary">Bắt đầu</a>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
		crossorigin="anonymous"></script>
</body>
</html>
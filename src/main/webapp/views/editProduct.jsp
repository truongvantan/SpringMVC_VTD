<%@page import="com.springmvc.common.StringCommon"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Trang chỉnh sửa sản phẩm</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
	integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
	integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href='<c:url value="/resources/css/styles.css" />' />
</head>
<body>

	<!-- Kiểm tra người dùng đã đăng nhập hay chưa?  -->
	<c:if test="${empty sessionScope.sessionNguoiDung}">
		<c:redirect url="/login?error=1" />
	</c:if>

	<c:import url="/views/header.jsp"></c:import>

	<div class="container-fluid">
		<h2 class="text-center mt-2">Thêm mới sản phẩm</h2>
		<p class="text-center text-danger">${errorMessage}</p>
		<div class="row d-flex justify-content-center">
			<form:form action="${contextPath}/product/edit"
				modelAttribute="sanPham" method="POST" class="w-75 mt-2">
				<div class="row d-flex justify-content-center">
					<div class="form-outline mb-4 w-50 text-center">
						<label
							class="form-label text-center text-primary font-weight-bold"
							for="maSanPham">Mã sản phẩm</label> <input type="text"
							value='${empty errorMessage ? sanPham.id : param.maSanPham}'
							class="form-control text-center" disabled /> <input
							type="hidden"
							value='${empty errorMessage ? sanPham.id : param.maSanPham}'
							name="maSanPham" id="maSanPham" />
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-xs-12">
						<div class="form-outline mb-4">
							<label class="form-label text-primary font-weight-bold"
								for="tenSanPham">Tên sản phẩm</label> <input type="text"
								name="tenSanPham" id="tenSanPham" class="form-control"
								placeholder="Tên sản phẩm"
								value='${empty errorMessage ? sanPham.tenSanPham : param.tenSanPham}' />
						</div>
						<div class="form-outline mb-4">
							<label class="form-label text-primary font-weight-bold"
								for="dsThuongHieu">Thương hiệu</label> <select
								class="form-control d-inline-block" name="thuongHieu"
								id="dsThuongHieu">
								<option value="">Chọn thương hiệu</option>
								<c:forEach var="th" items="${listThuongHieus}">
									<option value="${th.id}">${th.tenThuongHieu}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-outline mb-4">
							<label class="form-label text-primary font-weight-bold"
								for="donGiaNhap">Đơn giá nhập</label> <input type="text"
								name="donGiaNhap" id="donGiaNhap" class="form-control"
								placeholder="Đơn giá nhập"
								value='${empty errorMessage ? sanPham.donGiaNhap : param.donGiaNhap}' />
						</div>
					</div>
					<div class="col-md-6 col-xs-12">
						<div class="form-outline mb-4">
							<label class="form-label text-primary font-weight-bold"
								for="donGiaBan">Đơn giá bán</label> <input type="text"
								name="donGiaBan" id="donGiaBan" class="form-control"
								placeholder="Đơn giá bán"
								value='${empty errorMessage ? sanPham.donGiaBan : param.donGiaBan}' />
						</div>
						<div class="form-outline mb-4">
							<label class="form-label text-primary font-weight-bold"
								for="linkHinhAnh">Link hình ảnh</label> <input type="text"
								name="linkHinhAnh" id="linkHinhAnh" class="form-control"
								placeholder="Link hình ảnh"
								value='${empty errorMessage ? sanPham.linkHinhAnh : param.linkHinhAnh}' />
						</div>
						<div class="form-outline mb-4">
							<label class="form-label text-primary font-weight-bold"
								for="moTa">Mô tả</label> <input type="text" name="moTa"
								id="moTa" class="form-control" placeholder="Mô tả"
								value='${empty errorMessage ? sanPham.moTa : param.moTa}' />
						</div>
					</div>
				</div>
				<div class="row d-flex justify-content-center">
					<input type="submit" class="btn btn-primary w-25 mr-1" value="Cập nhật" />
					<input type="reset" class="btn btn-secondary w-25" value="Reset" />
				</div>
			</form:form>
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

	<script>
		$(document).ready(function() {
			$('#dsThuongHieu').val('${empty errorMessage ? sanPham.thuongHieu : param.thuongHieu}');
		});
	</script>
</body>
</html>
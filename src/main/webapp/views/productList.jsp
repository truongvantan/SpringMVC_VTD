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
<title>Quản lí sản phẩm</title>
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
		<div class="panel panel-default panel-table mt-3">
			<div class="panel-heading">
				<form action="${contextPath}/product/search" method="POST"
					class="row form-inline">
					<div class="col col-xs-4">
						<select class="form-control w-75 d-inline-block"
							name="thuongHieu" id="dsThuongHieu">
							<option value="">Tất cả thương hiệu</option>
							<c:forEach var="th" items="${listThuongHieus}">
								<option value="${th.id}">${th.tenThuongHieu}</option>>
							</c:forEach>

						</select>
					</div>
					<div class="col col-xs-6">
						<div class="form-inline">
							<input name="searchText" id="searchText"
								class="form-control mr-1 w-75" type="search"
								placeholder="Chọn tất cả thương hiệu để tìm kiếm..." aria-label="Search"
								value="${sessionScope.searchText}" /> <input class="btn btn-outline-success"
								type="submit" id="btnSearch" value="Tìm kiếm" />
						</div>
					</div>
					<div class="col col-xs-2 text-right">
						<a href="${contextPath}/product/showAdd"
							class="btn btn-primary btn-sm mr-4"> <i
							class="fa-solid fa-plus"></i> Thêm mới
						</a>
					</div>
				</form>
			</div>
			<div class="panel-body">
				<div class="row mt-2">
					<div class="col-12">
						<p class="text-center text-success">${successMessage}</p>
						<p class="text-center text-danger">${errorMessage}</p>
					</div>
				</div>
				<table class="table table-bordered table-striped">
					<thead>
						<tr class="d-flex">
							<th class="col col-1 text-center"><i
								class="fa-solid fa-gear fa-sm"></i></th>
							<th class="col col-1">STT</th>
							<th class="col">Mã Sản phẩm</th>
							<th class="col">Tên sản phẩm</th>
							<th class="col">Thương hiệu</th>
							<th class="col">Đơn giá nhập</th>
							<th class="col">Đơn giá bán</th>
							<th class="col">Hình ảnh</th>
							<th class="col">Mô tả</th>
						</tr>
					</thead>
					<tbody>
						<%
						int stt = 0;
						%>
						<c:forEach var="sp" items="${listSanPhams}">
							<tr class="d-flex">
								<td class="col col-1 d-flex justify-content-center"><a
									href="${contextPath}/product/showEdit?idProduct=${sp.id}"
									class="btn btn-success mr-1" data-toggle="tooltip"
									title="Chỉnh sửa"> <i class="fa-solid fa-pencil fa-xs"></i>
								</a> <a onclick="return confirmDeleteProduct('${sp.id}');"
									href="${contextPath}/product/delete?idProduct=${sp.id}"
									class="btn btn-danger" data-toggle="tooltip" title="Xóa"> <i
										class="fa-solid fa-trash-can fa-xs"></i>
								</a></td>
								<td class="col col-1 d-flex align-items-center"><%=++stt%></td>
								<td class="col d-flex align-items-center">${sp.id}</td>
								<td class="col d-flex align-items-center">${sp.tenSanPham}</td>
								<td class="col d-flex align-items-center">${sp.tenThuongHieu}</td>
								<td class="col d-flex align-items-center"><fmt:formatNumber
										type="number" pattern="###,###.00" value="${sp.donGiaNhap}" /></td>
								<td class="col d-flex align-items-center"><fmt:formatNumber
										type="number" pattern="###,###.00" value="${sp.donGiaBan}" /></td>
								<td class="col d-flex align-items-center">${sp.linkHinhAnh}</td>
								<td class="col d-flex align-items-center">${sp.moTa}</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr class="d-flex">
							<th class="col col-1 text-center"><i
								class="fa-solid fa-gear fa-sm"></i></th>
							<th class="col col-1">STT</th>
							<th class="col">Mã Sản phẩm</th>
							<th class="col">Tên sản phẩm</th>
							<th class="col">Thương hiệu</th>
							<th class="col">Đơn giá nhập</th>
							<th class="col">Đơn giá bán</th>
							<th class="col">Hình ảnh</th>
							<th class="col">Mô tả</th>
						</tr>
					</tfoot>
				</table>
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
	<script>
		$(function() {
			$('[data-toggle="tooltip"]').tooltip();
		});
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#dsThuongHieu').on('change', function() {
				location.href = '${contextPath}/product/show?idThuongHieu=' + $(this).val() + '&page=1';
			});
			
			$('#dsThuongHieu').val('${param.idThuongHieu}');
			
			if ($('#dsThuongHieu').val() == '') {
				$('input[name="searchText"]').removeAttr("disabled");
            	$("#btnSearch").removeAttr("disabled");
			} else {
				$('input[name="searchText"]').attr("disabled", true);
            	$("#btnSearch").attr("disabled", true);
			}
		});
	</script>
	<script type="text/javascript">
		function confirmDeleteProduct(idProduct) {
			return confirm("Bạn muốn xóa sản phẩm có id là: " + idProduct + "?");
		}
	</script>
</body>
</html>
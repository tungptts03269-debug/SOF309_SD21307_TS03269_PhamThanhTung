<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Nhật ký hoạt động</title>

<meta name="viewport"
      content="width=device-width, initial-scale=1">

<link
href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
rel="stylesheet">

<link
rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

<style>

body{
    background:#f5f6fa;
}

.card{
    border:none;
    border-radius:12px;
}

.table th{
    background:#198754;
    color:white;
    text-align:center;
}

.table td{
    text-align:center;
    vertical-align:middle;
}

.page-title{
    font-weight:bold;
}

</style>

</head>

<body>

<c:if test="${sessionScope.user == null}">
    <c:redirect url="/index.jsp"/>
</c:if>

<div class="container mt-4">

<div class="d-flex justify-content-between align-items-center mb-3">

<h2 class="page-title">

<i class="fa-solid fa-clipboard-list"></i>

Nhật ký hoạt động

</h2>

<a
href="${pageContext.request.contextPath}/indexadmin.jsp"
class="btn btn-primary">

<i class="fa-solid fa-house"></i>

Trang chủ

</a>

</div>

<div class="card shadow">

<div class="card-body">

<c:if test="${sessionScope.user.vaiTro == 'Admin'}">

<form
action="${pageContext.request.contextPath}/nhatkyhoatdong"
method="get">

<div class="row mb-3">

<div class="col-md-10">

<input
type="text"
name="keyword"
class="form-control"
placeholder="Tìm kiếm..."
value="${param.keyword}">

</div>

<div class="col-md-2">

<button
class="btn btn-success w-100">

<i class="fa-solid fa-magnifying-glass"></i>

Tìm kiếm

</button>

</div>

</div>

</form>

</c:if>

<div class="table-responsive">

<table class="table table-bordered table-hover">

<thead>

<tr>

<th>Mã</th>

<th>Tài khoản</th>

<th>Hành động</th>

<th>Bảng</th>

<th>Mã đối tượng</th>

<th>Địa chỉ IP</th>

<th>Ghi chú</th>

<th>Thời gian</th>

</tr>

</thead>

<tbody>

<c:forEach items="${list}" var="nk">

<tr>

<td>${nk.maNhatKy}</td>

<td>${nk.taiKhoan.tenDangNhap}</td>

<td>

<c:choose>

<c:when test="${nk.hanhDong=='Đăng nhập'}">

<span class="badge bg-success">

${nk.hanhDong}

</span>

</c:when>

<c:when test="${nk.hanhDong=='Đăng ký'}">

<span class="badge bg-primary">

${nk.hanhDong}

</span>

</c:when>

<c:when test="${nk.hanhDong=='Đặt lịch'}">

<span class="badge bg-info text-dark">

${nk.hanhDong}

</span>

</c:when>

<c:when test="${nk.hanhDong=='Hủy lịch'}">

<span class="badge bg-danger">

${nk.hanhDong}

</span>

</c:when>

<c:otherwise>

<span class="badge bg-secondary">

${nk.hanhDong}

</span>

</c:otherwise>

</c:choose>

</td>

<td>${nk.bangTacDong}</td>

<td>${nk.maDoiTuong}</td>

<td>${nk.diaChiIP}</td>

<td>${nk.ghiChu}</td>

<td>${nk.thoiGian}</td>

</tr>

</c:forEach>

<c:if test="${empty list}">

<tr>

<td colspan="8">

Không có dữ liệu.

</td>

</tr>

</c:if>

</tbody>

</table>

</div>

</div>

</div>

<c:if test="${not empty message}">

<div class="alert alert-success alert-dismissible fade show mt-3">

<i class="fa-solid fa-circle-check"></i>

${message}

<button
type="button"
class="btn-close"
data-bs-dismiss="alert"></button>

</div>

</c:if>

<c:if test="${not empty error}">

<div class="alert alert-danger alert-dismissible fade show mt-3">

<i class="fa-solid fa-circle-exclamation"></i>

${error}

<button
type="button"
class="btn-close"
data-bs-dismiss="alert"></button>

</div>

</c:if>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>
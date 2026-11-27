<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Quản lý thanh toán</title>

<link
href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
rel="stylesheet">

<link
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
rel="stylesheet">

<style>

body{
    background:#f5f5f5;
}

.card-stat{
    border:none;
    border-radius:15px;
    box-shadow:0 2px 8px rgba(0,0,0,.1);
}

.table{
    background:white;
}

.badge{
    font-size:14px;
}

</style>

</head>

<body>

<div class="container mt-4">

<!-- ================= QUAY VỀ ADMIN ================= -->

    <div class="mb-4">

        <a href="${pageContext.request.contextPath}/indexadmin.jsp"
           class="btn btn-secondary">

            <i class="fa-solid fa-arrow-left"></i>
            Quay về Admin

        </a>

    </div>


<h2 class="mb-4">
<i class="fa-solid fa-money-check-dollar"></i>
Quản lý thanh toán
</h2>

<!-- ================= THỐNG KÊ ================= -->

<div class="row g-3 mb-4">

<div class="col-md-3">
<div class="card card-stat bg-success text-white">
<div class="card-body">
<h6>Đã thanh toán</h6>
<h3>${daThanhToan}</h3>
</div>
</div>
</div>

<div class="col-md-3">
<div class="card card-stat bg-danger text-white">
<div class="card-body">
<h6>Chưa thanh toán</h6>
<h3>${chuaThanhToan}</h3>
</div>
</div>
</div>

<div class="col-md-3">
<div class="card card-stat bg-primary text-white">
<div class="card-body">
<h6>Doanh thu hôm nay</h6>

<h5>

<fmt:formatNumber
value="${doanhThuHomNay}"
type="number"/>

đ

</h5>

</div>
</div>
</div>

<div class="col-md-3">
<div class="card card-stat bg-warning text-dark">
<div class="card-body">
<h6>Doanh thu tháng</h6>

<h5>

<fmt:formatNumber
value="${doanhThuThang}"
type="number"/>

đ

</h5>

</div>
</div>
</div>

</div>

<div class="row mb-4">

<div class="col-md-4">

<div class="card card-stat">

<div class="card-body">

<h6>Tổng doanh thu</h6>

<h3 class="text-success">

<fmt:formatNumber
value="${tongDoanhThu}"
type="number"/>

đ

</h3>

</div>

</div>

</div>

<div class="col-md-4">

<div class="card card-stat">

<div class="card-body">

<h6>Doanh thu năm</h6>

<h3 class="text-primary">

<fmt:formatNumber
value="${doanhThuNam}"
type="number"/>

đ

</h3>

</div>

</div>

</div>

</div>

<!-- ================= TÌM KIẾM ================= -->

<form action="${pageContext.request.contextPath}/quanlythanhtoan"
method="get">

<div class="row mb-4">

<div class="col-md-5">

<input
type="text"
name="keyword"
value="${keyword}"
class="form-control"
placeholder="Tìm mã hóa đơn, bệnh nhân...">

</div>

<div class="col-md-3">

<select
name="trangThai"
class="form-select">

<option value="">--Trạng thái--</option>

<option value="Đã thanh toán"
${trangThai=='Đã thanh toán'?'selected':''}>

Đã thanh toán

</option>

<option value="Chưa thanh toán"
${trangThai=='Chưa thanh toán'?'selected':''}>

Chưa thanh toán

</option>

</select>

</div>

<div class="col-md-2">

<button class="btn btn-primary w-100">

<i class="fa fa-search"></i>

Tìm

</button>

</div>

<div class="col-md-2">

<a href="${pageContext.request.contextPath}/quanlythanhtoan"
class="btn btn-secondary w-100">

Làm mới

</a>

</div>

</div>

</form>

<!-- ================= DANH SÁCH ================= -->

<div class="card">

<div class="card-header bg-dark text-white">

Danh sách thanh toán

</div>

<div class="card-body p-0">

<table class="table table-bordered table-hover mb-0">

<thead class="table-light">

<tr>

<th>Mã HĐ</th>

<th>Bệnh nhân</th>

<th>Bác sĩ</th>

<th>Dịch vụ</th>

<th>Số tiền</th>

<th>Phương thức</th>

<th>Trạng thái</th>

<th>Ngày thanh toán</th>

</tr>

</thead>

<tbody>

<c:forEach items="${listHoaDon}" var="hd">

<tr>

<td>${hd.maThanhToan}</td>

<td>${hd.lichKham.benhNhan.hoTen}</td>

<td>${hd.lichKham.bacSi.hoTen}</td>

<td>${hd.lichKham.dichVu.tenDichVu}</td>

<td>

<fmt:formatNumber
value="${hd.soTien}"
type="number"/>

đ

</td>

<td>${hd.phuongThuc}</td>

<td>

<c:choose>

<c:when test="${hd.trangThai=='Đã thanh toán'}">

<span class="badge bg-success">

Đã thanh toán

</span>

</c:when>

<c:otherwise>

<span class="badge bg-danger">

Chưa thanh toán

</span>

</c:otherwise>

</c:choose>

</td>

<td>

<fmt:formatDate
value="${hd.ngayThanhToan}"
pattern="dd/MM/yyyy HH:mm"/>

</td>

</tr>

</c:forEach>

<c:if test="${empty listHoaDon}">

<tr>

<td colspan="8" class="text-center">

Không có dữ liệu.

</td>

</tr>

</c:if>

</tbody>

</table>

</div>

</div>

</div>

</body>
</html>
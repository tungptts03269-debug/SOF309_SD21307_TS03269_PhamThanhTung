<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Quản lý lịch khám</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
rel="stylesheet">

<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

<style>

body{
    background:#f4f6f9;
}

.card{
    border:none;
    border-radius:12px;
}

.table th{
    background:#0d6efd;
    color:white;
    text-align:center;
}

.table td{
    vertical-align:middle;
    text-align:center;
}

.page-title{
    font-weight:bold;
}

.btn-action{
    width:38px;
}

</style>

</head>

<body>
<c:set var="user" value="${sessionScope.user}" />
<div class="container mt-4">

<c:if test="${not empty message}">
    <div class="alert alert-success alert-dismissible fade show">
        ${message}
        <button class="btn-close"
                data-bs-dismiss="alert"></button>
    </div>
</c:if>

<c:if test="${not empty error}">
    <div class="alert alert-danger alert-dismissible fade show">
        ${error}
        <button class="btn-close"
                data-bs-dismiss="alert"></button>
    </div>
</c:if>

<div class="d-flex justify-content-between align-items-center mb-3">

<h2 class="page-title">

<i class="fa-solid fa-calendar-check"></i>

Quản lý lịch khám

</h2>

<c:if test="${sessionScope.user.vaiTro=='Admin'}">

<c:if test="${user.vaiTro eq 'Admin'}">

    <button
        class="btn btn-primary"
        data-bs-toggle="modal"
        data-bs-target="#lichKhamModal">

        <i class="fa-solid fa-plus"></i>

        Thêm lịch khám

    </button>

</c:if>

</c:if>

</div>

<div class="card shadow">

<div class="card-body">

<c:if test="${sessionScope.user.vaiTro=='Admin'}">

<form
action="${pageContext.request.contextPath}/lichkham"
method="get">

<div class="row mb-3">

<div class="col-md-10">

<input
type="text"
class="form-control"
name="keyword"
placeholder="Tìm theo tên bệnh nhân...">

</div>

<div class="col-md-2">

<button class="btn btn-success w-100">

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

<th>Bệnh nhân</th>

<th>Bác sĩ</th>

<th>Dịch vụ</th>

<th>Ngày khám</th>

<th>Giờ khám</th>

<th>Trạng thái</th>

<c:if test="${sessionScope.user.vaiTro=='Admin'}">
<c:if test="${user.vaiTro eq 'Admin'}">

    <th width="150">
        Thao tác
    </th>

</c:if>
</c:if>

</tr>

</thead>

<tbody>

<c:forEach items="${list}" var="lk">

<tr>

<td>${lk.maLichKham}</td>

<td>${lk.benhNhan.hoTen}</td>

<td>${lk.bacSi.hoTen}</td>

<td>${lk.dichVu.tenDichVu}</td>

<td>${lk.ngayKham}</td>

<td>${lk.gioKham}</td>

<td>

<c:choose>
<c:when test="${lk.trangThai=='Yêu cầu hủy'}">
    <span class="badge bg-danger">
     ${lk.trangThai}  
    </span>
</c:when>

<c:when test="${lk.trangThai=='Chờ xác nhận'}">
<span class="badge bg-warning text-dark">
${lk.trangThai}
</span>
</c:when>

<c:when test="${lk.trangThai=='Đã xác nhận'}">
<span class="badge bg-primary">
${lk.trangThai}
</span>
</c:when>

<c:when test="${lk.trangThai=='Đã khám'}">
<span class="badge bg-success">
${lk.trangThai}
</span>
</c:when>

<c:otherwise>
<span class="badge bg-danger">
${lk.trangThai}
</span>
</c:otherwise>

</c:choose>

</td>

<c:if test="${sessionScope.user.vaiTro=='Admin'}">

<td>
<td>
<c:if test="${lk.trangThai == 'Chờ xác nhận'}">

    <form
        action="${pageContext.request.contextPath}/lichkham"
        method="post"
        style="display:inline;">

        <input type="hidden"
               name="action"
               value="confirm">

        <input type="hidden"
               name="maLichKham"
               value="${lk.maLichKham}">

        <button class="btn btn-success btn-sm"
                onclick="return confirm('Xác nhận lịch khám này?')">

            <i class="fa-solid fa-check"></i>

        </button>

    </form>

</c:if>



    <!-- Nút sửa -->
    <button
        type="button"
        class="btn btn-warning btn-sm btn-action"
        data-bs-toggle="modal"
        data-bs-target="#lichKhamModal"

        onclick="
            document.getElementsByName('maLichKham')[0].value='${lk.maLichKham}';
            document.getElementsByName('maBenhNhan')[0].value='${lk.benhNhan.maBenhNhan}';
            document.getElementsByName('maBacSi')[0].value='${lk.bacSi.maBacSi}';
            document.getElementsByName('maDichVu')[0].value='${lk.dichVu.maDichVu}';
            document.getElementsByName('ngayKham')[0].value='${lk.ngayKham}';
            document.getElementsByName('gioKham')[0].value='${lk.gioKham}';
            document.getElementsByName('trangThai')[0].value='${lk.trangThai}';
        ">

        <i class="fa-solid fa-pen"></i>

    </button>

    <!-- Xóa -->
    <form
        action="${pageContext.request.contextPath}/lichkham"
        method="post"
        style="display:inline;">

        <input
            type="hidden"
            name="action"
            value="delete">

        <input
            type="hidden"
            name="maLichKham"
            value="${lk.maLichKham}">

        <button
            class="btn btn-danger btn-sm btn-action"
            onclick="return confirm('Bạn có chắc muốn xóa lịch khám này?')">

            <i class="fa-solid fa-trash"></i>

        </button>

    </form>
    
    <c:if test="${lk.trangThai=='Yêu cầu hủy'}">

<form action="${pageContext.request.contextPath}/lichkham"
      method="post"
      style="display:inline;">

    <input type="hidden"
           name="action"
           value="acceptCancel">

    <input type="hidden"
           name="maLichKham"
           value="${lk.maLichKham}">

    <button class="btn btn-danger btn-sm">

        Duyệt hủy

    </button>

</form>

</c:if>

</td>

</c:if>

</tr>

</c:forEach>

</tbody>

</table>

</div>

</div>

</div>
<div class="mt-4 d-flex justify-content-between">

    <c:choose>

      
        <c:when test="${sessionScope.user.vaiTro == 'Admin'}">

            <a href="${pageContext.request.contextPath}/indexadmin.jsp"
               class="btn btn-secondary">

                <i class="fa-solid fa-arrow-left"></i>

                Quay về Trang quản trị

            </a>

        </c:when>

        
        <c:otherwise>

            <a href="${pageContext.request.contextPath}/"
               class="btn btn-primary">

                <i class="fa-solid fa-house"></i>

                Quay về Trang chủ

            </a>

        </c:otherwise>

    </c:choose>

</div>
<!-- ================= MODAL ================= -->

<div class="modal fade"
id="lichKhamModal"
tabindex="-1">

<div class="modal-dialog modal-lg">

<div class="modal-content">

<form
action="${pageContext.request.contextPath}/lichkham"
method="post">

<div class="modal-header bg-primary text-white">

<h5 class="modal-title">

Thêm / Cập nhật lịch khám

</h5>

<button
type="button"
class="btn-close btn-close-white"
data-bs-dismiss="modal">

</button>

</div>

<div class="modal-body">

<input
type="hidden"
name="maLichKham">

<div class="row">

<div class="col-md-6 mb-3">

<label>Bệnh nhân</label>

<select
name="maBenhNhan"
class="form-select"
required>

<option value="">-- Chọn bệnh nhân --</option>

<c:forEach items="${benhNhans}" var="bn">

<option value="${bn.maBenhNhan}">
${bn.hoTen}
</option>

</c:forEach>

</select>

</div>

<div class="col-md-6 mb-3">

<label>Bác sĩ</label>

<select
name="maBacSi"
class="form-select"
required>

<option value="">-- Chọn bác sĩ --</option>

<c:forEach items="${bacSis}" var="bs">

<option value="${bs.maBacSi}">
${bs.hoTen}
</option>

</c:forEach>

</select>

</div>

</div>

<div class="row">

<div class="col-md-6 mb-3">

<label>Dịch vụ</label>

<select
name="maDichVu"
class="form-select"
required>

<option value="">-- Chọn dịch vụ --</option>

<c:forEach items="${dichVus}" var="dv">

<option value="${dv.maDichVu}">
${dv.tenDichVu}
</option>

</c:forEach>

</select>

</div>

<div class="col-md-6 mb-3">

<label>Trạng thái</label>

<select
name="trangThai"
class="form-select">

<option value="Chờ xác nhận">Chờ xác nhận</option>

<option value="Đã xác nhận">Đã xác nhận</option>

<option value="Đã khám">Đã khám</option>

<option value="Đã hủy">Đã hủy</option>

</select>

</div>

</div>

<div class="row">

<div class="col-md-6 mb-3">

<label>Ngày khám</label>

<input
type="date"
name="ngayKham"
class="form-control"
required>

</div>

<div class="col-md-6 mb-3">

<label>Giờ khám</label>

<input
type="time"
name="gioKham"
class="form-control"
required>

</div>

</div>
                <!-- Lý do khám -->
                <div class="mb-3">

                    <label class="form-label">

                        Lý do khám

                    </label>

                    <textarea
                        name="lyDoKham"
                        class="form-control"
                        rows="3"></textarea>

                </div>

                <!-- Ghi chú -->

                <div class="mb-3">

                    <label class="form-label">

                        Ghi chú

                    </label>

                    <textarea
                        name="ghiChu"
                        class="form-control"
                        rows="3"></textarea>

                </div>

            </div>

            <div class="modal-footer">

                <button
                    type="button"
                    class="btn btn-secondary"
                    data-bs-dismiss="modal">

                    Đóng

                </button>

                <button
                    type="submit"
                    class="btn btn-primary">

                    <i class="fa-solid fa-floppy-disk"></i>

                    Lưu

                </button>

            </div>

        </form>

    </div>

</div>

</div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<script>

// Tự đóng thông báo sau 3 giây
setTimeout(function(){

    let alerts = document.querySelectorAll(".alert");

    alerts.forEach(function(a){

        let bsAlert = bootstrap.Alert.getOrCreateInstance(a);

        bsAlert.close();

    });

},3000);

</script>

</body>

</html>
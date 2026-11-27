<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Quản lý bác sĩ</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
rel="stylesheet">

<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

<style>

body{
    background:#f5f7fa;
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

.btn-action{
    width:38px;
}

.page-title{
    font-weight:bold;
}

</style>

</head>

<body>

<div class="container mt-4">

<div class="d-flex justify-content-between align-items-center mb-3">

<h2 class="page-title">

<i class="fa-solid fa-user-doctor"></i>

Quản lý bác sĩ

</h2>

<button
class="btn btn-primary"
data-bs-toggle="modal"
data-bs-target="#doctorModal">

<i class="fa-solid fa-plus"></i>

Thêm bác sĩ

</button>

</div>

<div class="card shadow">

<div class="card-body">

<form method="get"
action="${pageContext.request.contextPath}/bacsi">

<div class="row mb-3">

<div class="col-md-10">

<input
type="text"
name="keyword"
class="form-control"
placeholder="Nhập tên bác sĩ...">

</div>

<div class="col-md-2">

<button class="btn btn-success w-100">

<i class="fa-solid fa-magnifying-glass"></i>

Tìm kiếm

</button>

</div>

</div>

</form>

<div class="table-responsive">

<table class="table table-bordered table-hover">

<thead>

<tr>

<th>Mã</th>

<th>Họ tên</th>

<th>SĐT</th>

<th>Chuyên khoa</th>

<th>Phòng khám</th>

<th>Kinh nghiệm</th>

<th width="140">

Thao tác

</th>

</tr>

</thead>

<tbody>

<c:forEach items="${list}" var="bs">

<tr>

<td>

${bs.maBacSi}

</td>

<td>

${bs.hoTen}

</td>

<td>

${bs.soDienThoai}

</td>

<td>

${bs.chuyenKhoa.tenChuyenKhoa}

</td>

<td>

${bs.phongKham.tenPhongKham}

</td>

<td>

${bs.kinhNghiem}

</td>

<td>

<a
href="${pageContext.request.contextPath}/bacsi/edit?id=${bs.maBacSi}"
class="btn btn-warning btn-sm btn-action">

<i class="fa-solid fa-pen"></i>

</a>

<a
href="${pageContext.request.contextPath}/bacsi/delete?id=${bs.maBacSi}"
class="btn btn-danger btn-sm btn-action"
onclick="return confirm('Bạn có chắc muốn xóa?')">

<i class="fa-solid fa-trash"></i>

</a>

</td>

</tr>

</c:forEach>

</tbody>

</table>

</div>

</div>

</div>
<!-- ================= MODAL THÊM / CẬP NHẬT BÁC SĨ ================= -->

<div class="modal fade"
     id="doctorModal"
     tabindex="-1">

    <div class="modal-dialog modal-lg">

        <div class="modal-content">

            <form
                action="${pageContext.request.contextPath}/bacsi"
                method="post">

                <div class="modal-header bg-primary text-white">

                    <h5 class="modal-title">

                        <i class="fa-solid fa-user-doctor"></i>

                        Thêm / Cập nhật bác sĩ

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
                        name="maBacSi"
                        value="${doctor.maBacSi}">

                    <div class="row">

                        <div class="col-md-6 mb-3">

                            <label class="form-label">

                                Họ tên

                            </label>

                            <input
                                type="text"
                                class="form-control"
                                name="hoTen"
                                value="${doctor.hoTen}"
                                required>

                        </div>

                        <div class="col-md-6 mb-3">

                            <label class="form-label">

                                Số điện thoại

                            </label>

                            <input
                                type="text"
                                class="form-control"
                                name="soDienThoai"
                                value="${doctor.soDienThoai}"
                                required>

                        </div>

                    </div>

                    <div class="row">

                        <div class="col-md-6 mb-3">

                            <label class="form-label">

                                Chuyên khoa

                            </label>

                            <select
                                class="form-select"
                                name="maChuyenKhoa"
                                required>

                                <option value="">

                                    -- Chọn chuyên khoa --

                                </option>

                                <c:forEach
                                    items="${chuyenKhoas}"
                                    var="ck">

                                    <option
                                        value="${ck.maChuyenKhoa}">

                                        ${ck.tenChuyenKhoa}

                                    </option>

                                </c:forEach>

                            </select>

                        </div>

                        <div class="col-md-6 mb-3">

                            <label class="form-label">

                                Phòng khám

                            </label>

                            <select
                                class="form-select"
                                name="maPhongKham"
                                required>

                                <option value="">

                                    -- Chọn phòng khám --

                                </option>

                                <c:forEach
                                    items="${phongKhams}"
                                    var="pk">

                                    <option
                                        value="${pk.maPhongKham}">

                                        ${pk.tenPhongKham}

                                    </option>

                                </c:forEach>

                            </select>

                        </div>

                    </div>

                    <div class="mb-3">

                        <label class="form-label">

                            Kinh nghiệm

                        </label>

                        <textarea
                            class="form-control"
                            rows="4"
                            name="kinhNghiem">${doctor.kinhNghiem}</textarea>

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
<!-- ================= THÔNG BÁO ================= -->

<c:if test="${not empty message}">

    <div class="alert alert-success alert-dismissible fade show mt-3">

        <i class="fa-solid fa-circle-check"></i>

        ${message}

        <button
            type="button"
            class="btn-close"
            data-bs-dismiss="alert">
        </button>

    </div>

</c:if>

<c:if test="${not empty error}">

    <div class="alert alert-danger alert-dismissible fade show mt-3">

        <i class="fa-solid fa-circle-exclamation"></i>

        ${error}

        <button
            type="button"
            class="btn-close"
            data-bs-dismiss="alert">
        </button>

    </div>

</c:if>

</div>

<!-- Bootstrap -->

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<!-- ================= TỰ MỞ MODAL KHI SỬA ================= -->

<c:if test="${doctor != null}">

<script>

window.onload=function(){

    var modal=new bootstrap.Modal(
        document.getElementById("doctorModal")
    );

    modal.show();

}

</script>

</c:if>

<!-- ================= XÁC NHẬN XÓA ================= -->

<script>

function confirmDelete(name){

    return confirm("Bạn có chắc muốn xóa bác sĩ " + name + " ?");

}

</script>

</body>

</html>
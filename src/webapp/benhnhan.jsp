<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Quản lý bệnh nhân</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<link
href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
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

.btn-action{
    width:38px;
}

</style>

</head>

<body>

<div class="container mt-4">

<div class="d-flex justify-content-between align-items-center mb-3">

<h2 class="page-title">

<i class="fa-solid fa-user"></i>

Quản lý bệnh nhân

</h2>

<button
class="btn btn-success"
data-bs-toggle="modal"
data-bs-target="#benhNhanModal">

<i class="fa-solid fa-plus"></i>

Thêm bệnh nhân

</button>

</div>

<div class="card shadow">

<div class="card-body">

<form
action="${pageContext.request.contextPath}/benhnhan"
method="get">

<div class="row mb-3">

<div class="col-md-10">

<input
type="text"
name="keyword"
class="form-control"
placeholder="Nhập tên bệnh nhân...">

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

<th>Ngày sinh</th>

<th>Giới tính</th>

<th>SĐT</th>

<th>Địa chỉ</th>

<th width="150">

Thao tác

</th>

</tr>

</thead>

<tbody>

<c:forEach items="${list}" var="bn">

<tr>

<td>

${bn.maBenhNhan}

</td>

<td>

${bn.hoTen}

</td>

<td>

${bn.ngaySinh}

</td>

<td>

${bn.gioiTinh}

</td>

<td>

${bn.soDienThoai}

</td>

<td>

${bn.diaChi}

</td>

<td>

<a
href="${pageContext.request.contextPath}/benhnhan/edit?id=${bn.maBenhNhan}"
class="btn btn-warning btn-sm btn-action">

<i class="fa-solid fa-pen"></i>

</a>

<a
href="${pageContext.request.contextPath}/benhnhan/delete?id=${bn.maBenhNhan}"
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
<!-- ================= MODAL THÊM / CẬP NHẬT BỆNH NHÂN ================= -->

<div class="modal fade"
     id="benhNhanModal"
     tabindex="-1">

    <div class="modal-dialog modal-lg">

        <div class="modal-content">

            <form
                action="${pageContext.request.contextPath}/benhnhan"
                method="post">

                <div class="modal-header bg-success text-white">

                    <h5 class="modal-title">

                        <i class="fa-solid fa-user"></i>

                        Thêm / Cập nhật bệnh nhân

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
                        name="maBenhNhan"
                        value="${benhNhan.maBenhNhan}">

                    <div class="row">

                        <div class="col-md-6 mb-3">

                            <label class="form-label">

                                Họ và tên

                            </label>

                            <input
                                type="text"
                                class="form-control"
                                name="hoTen"
                                value="${benhNhan.hoTen}"
                                required>

                        </div>

                        <div class="col-md-6 mb-3">

                            <label class="form-label">

                                Ngày sinh

                            </label>

                            <input
                                type="date"
                                class="form-control"
                                name="ngaySinh"
                                value="${benhNhan.ngaySinh}"
                                required>

                        </div>

                    </div>

                    <div class="row">

                        <div class="col-md-6 mb-3">

                            <label class="form-label">

                                Giới tính

                            </label>

                            <select
                                class="form-select"
                                name="gioiTinh">

                                <option value="Nam">

                                    Nam

                                </option>

                                <option value="Nữ">

                                    Nữ

                                </option>

                            </select>

                        </div>

                        <div class="col-md-6 mb-3">

                            <label class="form-label">

                                Số điện thoại

                            </label>

                            <input
                                type="text"
                                class="form-control"
                                name="soDienThoai"
                                value="${benhNhan.soDienThoai}"
                                required>

                        </div>

                    </div>

                    <div class="mb-3">

                        <label class="form-label">

                            Địa chỉ

                        </label>

                        <textarea
                            class="form-control"
                            rows="3"
                            name="diaChi">${benhNhan.diaChi}</textarea>

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
                        class="btn btn-success">

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

<!-- ================= Bootstrap ================= -->

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<!-- ================= TỰ MỞ MODAL KHI SỬA ================= -->

<c:if test="${benhNhan != null}">

<script>

window.onload = function(){

    var modal = new bootstrap.Modal(
        document.getElementById("benhNhanModal")
    );

    modal.show();

}

</script>

</c:if>

<!-- ================= XÁC NHẬN XÓA ================= -->

<script>

function confirmDelete(name){

    return confirm("Bạn có chắc muốn xóa bệnh nhân " + name + " ?");

}

</script>

</body>

</html>
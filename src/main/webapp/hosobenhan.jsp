<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Quản lý hồ sơ bệnh án</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
rel="stylesheet">

<link rel="stylesheet"
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

.btn-action{
    width:38px;
}

</style>

</head>

<body>

<div class="container mt-4">

<div class="d-flex justify-content-between align-items-center mb-3">

<h2 class="page-title">

<i class="fa-solid fa-notes-medical"></i>

Quản lý hồ sơ bệnh án

</h2>

<button
class="btn btn-success"
data-bs-toggle="modal"
data-bs-target="#hoSoModal">

<i class="fa-solid fa-plus"></i>

Thêm hồ sơ

</button>

</div>

<div class="card shadow">

<div class="card-body">

<form
action="${pageContext.request.contextPath}/hosobenhan"
method="get">

<div class="row mb-3">

<div class="col-md-10">

<input
type="text"
name="keyword"
class="form-control"
placeholder="Tìm theo chẩn đoán...">

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

<th>Mã lịch khám</th>

<th>Triệu chứng</th>

<th>Chẩn đoán</th>

<th>Kết luận</th>

<th width="150">

Thao tác

</th>

</tr>

</thead>

<tbody>

<c:forEach items="${list}" var="hs">

<tr>

<td>

${hs.maHoSo}

</td>

<td>

${hs.lichKham.maLichKham}

</td>

<td>

${hs.trieuChung}

</td>

<td>

${hs.chanDoan}

</td>

<td>

${hs.ketLuan}

</td>

<td>

<a
href="${pageContext.request.contextPath}/hosobenhan/edit?id=${hs.maHoSo}"
class="btn btn-warning btn-sm btn-action">

<i class="fa-solid fa-pen"></i>

</a>

<a
href="${pageContext.request.contextPath}/hosobenhan/delete?id=${hs.maHoSo}"
class="btn btn-danger btn-sm btn-action"
onclick="return confirm('Bạn có chắc muốn xóa hồ sơ này?')">

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
<!-- ================= MODAL THÊM / CẬP NHẬT HỒ SƠ ================= -->

<div class="modal fade"
     id="hoSoModal"
     tabindex="-1">

    <div class="modal-dialog modal-lg">

        <div class="modal-content">

            <form
                action="${pageContext.request.contextPath}/hosobenhan"
                method="post">

                <div class="modal-header bg-success text-white">

                    <h5 class="modal-title">

                        <i class="fa-solid fa-notes-medical"></i>

                        Thêm / Cập nhật hồ sơ bệnh án

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
                        name="maHoSo"
                        value="${hoSo.maHoSo}">

                    <!-- LỊCH KHÁM -->

                    <div class="mb-3">

                        <label class="form-label">

                            Lịch khám

                        </label>

                        <select
                            name="maLichKham"
                            class="form-select"
                            required>

                            <option value="">

                                -- Chọn lịch khám --

                            </option>

                            <c:forEach items="${lichKhams}" var="lk">

                                <option
                                    value="${lk.maLichKham}">

                                    Lịch #${lk.maLichKham}
                                    -
                                    ${lk.benhNhan.hoTen}

                                </option>

                            </c:forEach>

                        </select>

                    </div>

                    <!-- TRIỆU CHỨNG -->

                    <div class="mb-3">

                        <label class="form-label">

                            Triệu chứng

                        </label>

                        <textarea
                            name="trieuChung"
                            class="form-control"
                            rows="3">${hoSo.trieuChung}</textarea>

                    </div>

                    <!-- CHẨN ĐOÁN -->

                    <div class="mb-3">

                        <label class="form-label">

                            Chẩn đoán

                        </label>

                        <textarea
                            name="chanDoan"
                            class="form-control"
                            rows="3">${hoSo.chanDoan}</textarea>

                    </div>

                    <!-- KẾT LUẬN -->

                    <div class="mb-3">

                        <label class="form-label">

                            Kết luận

                        </label>

                        <textarea
                            name="ketLuan"
                            class="form-control"
                            rows="3">${hoSo.ketLuan}</textarea>

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

                        Lưu hồ sơ

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

<!-- ================= MỞ MODAL KHI CHỈNH SỬA ================= -->

<c:if test="${hoSo != null}">

<script>

window.onload = function(){

    var modal = new bootstrap.Modal(
        document.getElementById("hoSoModal")
    );

    modal.show();

}

</script>

</c:if>

<!-- ================= XÁC NHẬN XÓA ================= -->

<script>

function confirmDelete(id){

    return confirm("Bạn có chắc muốn xóa hồ sơ bệnh án #" + id + " ?");

}

</script>

</body>

</html>
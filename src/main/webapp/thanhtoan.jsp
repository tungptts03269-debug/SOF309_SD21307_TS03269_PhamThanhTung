<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Quản lý thanh toán</title>

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

.btn-action{
    width:38px;
}

</style>

</head>

<body>

<div class="container mt-4">

<div class="d-flex justify-content-between align-items-center mb-3">

<h2 class="page-title">

<i class="fa-solid fa-money-bill-wave"></i>

Quản lý thanh toán

</h2>

<button
class="btn btn-success"
data-bs-toggle="modal"
data-bs-target="#thanhToanModal">

<i class="fa-solid fa-plus"></i>

Thêm thanh toán

</button>

</div>

<div class="card shadow">

<div class="card-body">

<form
action="${pageContext.request.contextPath}/thanhtoan"
method="get">

<div class="row mb-3">

<div class="col-md-10">

<input
type="text"
name="keyword"
class="form-control"
placeholder="Tìm kiếm thanh toán..."
value="${param.keyword}">

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

<th>Lịch khám</th>

<th>Tổng tiền</th>

<th>Ngày thanh toán</th>

<th>Phương thức</th>

<th width="150">

Thao tác

</th>

</tr>

</thead>

<tbody>

<c:forEach items="${list}" var="tt">

<tr>

<td>

${tt.maThanhToan}

</td>

<td>

#${tt.lichKham.maLichKham}

</td>

<td>

${tt.tongTien}

</td>

<td>

${tt.ngayThanhToan}

</td>

<td>

${tt.phuongThuc}

</td>

<td>

<a
href="${pageContext.request.contextPath}/thanhtoan/edit?id=${tt.maThanhToan}"
class="btn btn-warning btn-sm btn-action">

<i class="fa-solid fa-pen"></i>

</a>

<a
href="${pageContext.request.contextPath}/thanhtoan/delete?id=${tt.maThanhToan}"
class="btn btn-danger btn-sm btn-action"
onclick="return confirmDelete(${tt.maThanhToan})">

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
<!-- ================= MODAL THÊM / CẬP NHẬT THANH TOÁN ================= -->

<div class="modal fade"
     id="thanhToanModal"
     tabindex="-1"
     aria-labelledby="thanhToanModalLabel"
     aria-hidden="true">

    <div class="modal-dialog modal-lg">

        <div class="modal-content">

            <form
                action="${pageContext.request.contextPath}/thanhtoan"
                method="post">

                <!-- Header -->

                <div class="modal-header bg-success text-white">

                    <h5 class="modal-title"
                        id="thanhToanModalLabel">

                        <i class="fa-solid fa-money-bill-wave"></i>

                        Thêm / Cập nhật thanh toán

                    </h5>

                    <button
                        type="button"
                        class="btn-close btn-close-white"
                        data-bs-dismiss="modal">
                    </button>

                </div>

                <!-- Body -->

                <div class="modal-body">

                    <!-- Mã thanh toán -->

                    <input
                        type="hidden"
                        name="maThanhToan"
                        value="${thanhToan.maThanhToan}">

                    <!-- Lịch khám -->

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
                                    value="${lk.maLichKham}"

                                    <c:if test="${thanhToan.lichKham.maLichKham == lk.maLichKham}">
                                        selected
                                    </c:if>>

                                    Lịch #${lk.maLichKham}
                                    -
                                    ${lk.benhNhan.hoTen}

                                </option>

                            </c:forEach>

                        </select>

                    </div>

                    <!-- Tổng tiền -->

                    <div class="mb-3">

                        <label class="form-label">

                            Tổng tiền

                        </label>

                        <input
                            type="number"
                            name="tongTien"
                            class="form-control"
                            min="0"
                            step="1000"
                            value="${thanhToan.tongTien}"
                            required>

                    </div>

                    <!-- Ngày thanh toán -->

                    <div class="mb-3">

                        <label class="form-label">

                            Ngày thanh toán

                        </label>

                        <input
                            type="date"
                            name="ngayThanhToan"
                            class="form-control"
                            value="${thanhToan.ngayThanhToan}"
                            required>

                    </div>

                    <!-- Phương thức -->

                    <div class="mb-3">

                        <label class="form-label">

                            Phương thức thanh toán

                        </label>

                        <select
                            name="phuongThuc"
                            class="form-select"
                            required>

                            <option value="">

                                -- Chọn phương thức --

                            </option>

                            <option value="Tiền mặt"
                                ${thanhToan.phuongThuc == 'Tiền mặt' ? 'selected' : ''}>

                                Tiền mặt

                            </option>

                            <option value="Chuyển khoản"
                                ${thanhToan.phuongThuc == 'Chuyển khoản' ? 'selected' : ''}>

                                Chuyển khoản

                            </option>

                            <option value="Thẻ ngân hàng"
                                ${thanhToan.phuongThuc == 'Thẻ ngân hàng' ? 'selected' : ''}>

                                Thẻ ngân hàng

                            </option>

                            <option value="Ví điện tử"
                                ${thanhToan.phuongThuc == 'Ví điện tử' ? 'selected' : ''}>

                                Ví điện tử

                            </option>

                        </select>

                    </div>

                </div>

                <!-- Footer -->

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

                        Lưu thanh toán

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

<!-- ================= Bootstrap JS ================= -->

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<!-- ================= MỞ MODAL KHI CHỈNH SỬA ================= -->

<c:if test="${thanhToan != null}">

<script>

window.onload = function(){

    var modal = new bootstrap.Modal(
        document.getElementById("thanhToanModal")
    );

    modal.show();

}

</script>

</c:if>

<!-- ================= XÁC NHẬN XÓA ================= -->

<script>

function confirmDelete(id){

    return confirm("Bạn có chắc muốn xóa thanh toán #" + id + " ?");

}

</script>

</body>

</html>
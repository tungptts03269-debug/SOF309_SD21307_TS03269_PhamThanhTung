<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Danh sách hóa đơn</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet">

<link rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

</head>

<body class="bg-light">

<div class="container mt-5">

    <div class="card shadow">

        <div class="card-header bg-primary text-white">

            <h3>

                <i class="fa-solid fa-file-invoice-dollar"></i>

                Danh sách hóa đơn

            </h3>

        </div>

        <div class="card-body">
        <!-- ================= THỐNG KÊ ================= -->

<c:if test="${sessionScope.user.vaiTro=='Admin'}">

<div class="row mb-4">

    <div class="col-md-4">

        <div class="card border-success shadow-sm">

            <div class="card-body text-center">

                <h6 class="text-success">

                    Tổng doanh thu

                </h6>

                <h4 class="fw-bold text-danger">

                    ${tongDoanhThu} VNĐ

                </h4>

            </div>

        </div>

    </div>

    <div class="col-md-4">

        <div class="card border-primary shadow-sm">

            <div class="card-body text-center">

                <h6 class="text-primary">

                    Đã thanh toán

                </h6>

                <h3>

                    ${daThanhToan}

                </h3>

            </div>

        </div>

    </div>

    <div class="col-md-4">

        <div class="card border-danger shadow-sm">

            <div class="card-body text-center">

                <h6 class="text-danger">

                    Chưa thanh toán

                </h6>

                <h3>

                    ${chuaThanhToan}

                </h3>

            </div>

        </div>

    </div>

</div>

</c:if>
<c:if test="${sessionScope.user.vaiTro=='Admin'}">

<form
action="${pageContext.request.contextPath}/hoadon"
method="get">

<div class="row mb-3">

<div class="col-md-5">

<input
type="text"
name="keyword"
class="form-control"
placeholder="Tìm theo bệnh nhân, bác sĩ, dịch vụ...">

</div>

<div class="col-md-3">

<select
name="trangThai"
class="form-select">

<option value="">

Tất cả trạng thái

</option>

<option value="Đã thanh toán">

Đã thanh toán

</option>

<option value="Chưa thanh toán">

Chưa thanh toán

</option>

</select>

</div>

<div class="col-md-2">

<button class="btn btn-primary w-100">

<i class="fa-solid fa-magnifying-glass"></i>

Tìm

</button>

</div>

<div class="col-md-2">

<a
href="${pageContext.request.contextPath}/hoadon"
class="btn btn-secondary w-100">

Làm mới

</a>

</div>

</div>

</form>

</c:if>

            <table class="table table-bordered table-hover align-middle">

                <thead class="table-dark">

                <tr>

                    <th>Mã HĐ</th>

                    <th>Bệnh nhân</th>

                    <th>Bác sĩ</th>

                    <th>Dịch vụ</th>

                    <th>Ngày khám</th>

                    <th>Số tiền</th>

                    <th>Phương thức</th>

                    <th>Thanh toán</th>

                    <th>Trạng thái lịch</th>

                    <th>Yêu cầu hủy</th>

                    <th>Chi tiết hóa đơn</th>

                </tr>

                </thead>

                <tbody>

                <c:forEach items="${listHoaDon}" var="hd">

                    <tr>

                        <td>${hd.maThanhToan}</td>

                        <td>${hd.lichKham.benhNhan.hoTen}</td>

                        <td>${hd.lichKham.bacSi.hoTen}</td>

                        <td>${hd.lichKham.dichVu.tenDichVu}</td>

                        <td>${hd.lichKham.ngayKham}</td>

                        <td class="text-danger fw-bold">

                            ${hd.soTien} VNĐ

                        </td>

                        <td>

                            <c:choose>

                                <c:when test="${empty hd.phuongThuc}">

                                    <span class="text-muted">

                                        Chưa chọn

                                    </span>

                                </c:when>

                                <c:otherwise>

                                    ${hd.phuongThuc}

                                </c:otherwise>

                            </c:choose>

                        </td>

                        <td>

                            <c:choose>

                                <c:when test="${hd.trangThai=='Đã thanh toán'}">

                                    <span class="badge bg-success">

                                        Đã thanh toán

                                    </span>

                                </c:when>

                                <c:otherwise>

                                    <button
                                            class="btn btn-success btn-sm"
                                            data-bs-toggle="modal"
                                            data-bs-target="#pay${hd.maThanhToan}">

                                        <i class="fa-solid fa-credit-card"></i>

                                        Thanh toán

                                    </button>

                                </c:otherwise>

                            </c:choose>

                        </td>

                        <td>

                            <c:choose>

                                <c:when test="${hd.lichKham.trangThai=='Đã hủy'}">

                                    <span class="badge bg-danger">

                                        Đã hủy

                                    </span>

                                </c:when>

                                <c:otherwise>

                                    <span class="badge bg-primary">

                                        ${hd.lichKham.trangThai}

                                    </span>

                                </c:otherwise>

                            </c:choose>

                        </td>

                        <td>

                            <c:set var="coYeuCau" value="false"/>

                            <c:forEach items="${listYeuCau}" var="yc">

                                <c:if test="${yc.lichKham.maLichKham==hd.lichKham.maLichKham}">

                                    <c:set var="coYeuCau" value="true"/>

                                    <c:choose>

                                        <c:when test="${yc.trangThai=='Chờ duyệt'}">

                                            <span class="badge bg-warning text-dark">

                                                Chờ duyệt

                                            </span>

                                        </c:when>

                                        <c:when test="${yc.trangThai=='Đã duyệt'}">

                                            <span class="badge bg-success">

                                                Đã duyệt

                                            </span>

                                        </c:when>

                                        <c:otherwise>

                                            <span class="badge bg-danger">

                                                Từ chối

                                            </span>

                                        </c:otherwise>

                                    </c:choose>

                                </c:if>

                            </c:forEach>

                            <c:if test="${!coYeuCau && hd.lichKham.trangThai!='Đã hủy'}">

                                <button
                                        class="btn btn-danger btn-sm"
                                        data-bs-toggle="modal"
                                        data-bs-target="#modal${hd.lichKham.maLichKham}">

                                    <i class="fa-solid fa-ban"></i>

                                    Yêu cầu hủy

                                </button>

                            </c:if>

                        </td>

                        <!-- Cột Chi tiết hóa đơn -->
                        <td class="text-center">

                            <a href="${pageContext.request.contextPath}/chitiethoadon?id=${hd.maThanhToan}"
                               class="btn btn-primary btn-sm">

                                <i class="fa-solid fa-file-invoice"></i>

                                Chi tiết

                            </a>
                            
                            <a href="${pageContext.request.contextPath}/inhoadon?id=${hd.maThanhToan}"
   target="_blank"
   class="btn btn-outline-primary btn-sm">

    <i class="fa-solid fa-print"></i>
    In bill

</a>

                        </td>

                    </tr>
                    <!-- =======================
     MODAL THANH TOÁN
======================= -->

<div class="modal fade"
     id="pay${hd.maThanhToan}"
     tabindex="-1">

    <div class="modal-dialog">

        <div class="modal-content">

            <div class="modal-header bg-success text-white">

                <h5 class="modal-title">

                    <i class="fa-solid fa-credit-card"></i>

                    Thanh toán hóa đơn

                </h5>

                <button
                        type="button"
                        class="btn-close btn-close-white"
                        data-bs-dismiss="modal">
                </button>

            </div>

            <div class="modal-body text-center">

                <h5 class="mb-3">

                    Bạn muốn thanh toán bằng cách nào?

                </h5>

                <p class="text-muted">

                    Vui lòng chọn một trong hai hình thức thanh toán bên dưới.

                </p>

                <a href="${pageContext.request.contextPath}/payment?id=${hd.maThanhToan}"
                   class="btn btn-success w-100 mb-3">

                    <i class="fa-solid fa-money-check-dollar"></i>

                    Thanh toán ngay

                </a>

                <a href="${pageContext.request.contextPath}/thanhtoantaiphongkham?id=${hd.maThanhToan}"
                   class="btn btn-warning w-100">

                    <i class="fa-solid fa-hospital"></i>

                    Thanh toán khi khám

                </a>

            </div>

            <div class="modal-footer">

                <button
                        type="button"
                        class="btn btn-secondary"
                        data-bs-dismiss="modal">

                    Đóng

                </button>

            </div>

        </div>

    </div>

</div>

<!-- =======================
     MODAL YÊU CẦU HỦY
======================= -->

<div class="modal fade"
     id="modal${hd.lichKham.maLichKham}"
     tabindex="-1">

    <div class="modal-dialog">

        <div class="modal-content">

            <form action="${pageContext.request.contextPath}/yeucauhuylich"
                  method="post">

                <div class="modal-header bg-danger text-white">

                    <h5 class="modal-title">

                        <i class="fa-solid fa-ban"></i>

                        Yêu cầu hủy lịch khám

                    </h5>

                    <button
                            type="button"
                            class="btn-close btn-close-white"
                            data-bs-dismiss="modal">
                    </button>

                </div>

                <div class="modal-body">

                    <input type="hidden"
                           name="maLichKham"
                           value="${hd.lichKham.maLichKham}">

                    <div class="mb-3">

                        <label class="form-label">

                            Lý do hủy lịch

                        </label>

                        <textarea
                                name="lyDo"
                                class="form-control"
                                rows="4"
                                placeholder="Nhập lý do muốn hủy lịch khám..."
                                required></textarea>

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
                            class="btn btn-danger">

                        <i class="fa-solid fa-paper-plane"></i>

                        Gửi yêu cầu

                    </button>

                </div>

            </form>

        </div>

    </div>

</div>
                </c:forEach>

                </tbody>

            </table>

            <div class="mt-3">

                <a href="${pageContext.request.contextPath}/"
                   class="btn btn-secondary">

                    <i class="fa-solid fa-arrow-left"></i>

                    Quay lại

                </a>

            </div>

        </div>

    </div>

</div>

<!-- Bootstrap JS -->

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<!-- Thông báo thành công -->

<c:if test="${not empty message}">

<script>

alert("${message}");

</script>

</c:if>

<!-- Thông báo lỗi -->

<c:if test="${not empty error}">

<script>

alert("${error}");

</script>

</c:if>

</body>

</html>
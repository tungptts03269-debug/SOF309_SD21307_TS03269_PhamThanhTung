<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Chi tiết hóa đơn</title>

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

                Chi tiết hóa đơn

            </h3>

        </div>

        <div class="card-body">

            <table class="table table-bordered">

                <tr>

                    <th width="35%">Mã hóa đơn</th>

                    <td>${hd.maThanhToan}</td>

                </tr>

                <tr>

                    <th>Họ tên bệnh nhân</th>

                    <td>${hd.lichKham.benhNhan.hoTen}</td>

                </tr>

                <tr>

                    <th>Bác sĩ khám</th>

                    <td>${hd.lichKham.bacSi.hoTen}</td>

                </tr>

                <tr>

                    <th>Dịch vụ</th>

                    <td>${hd.lichKham.dichVu.tenDichVu}</td>

                </tr>

                <tr>

                    <th>Ngày khám</th>

                    <td>${hd.lichKham.ngayKham}</td>

                </tr>

                <tr>

                    <th>Số tiền</th>

                    <td class="text-danger fw-bold">

                        ${hd.soTien} VNĐ

                    </td>

                </tr>

                <tr>

                    <th>Phương thức thanh toán</th>

                    <td>

                        <c:choose>

                            <c:when test="${empty hd.phuongThuc}">

                                <span class="text-secondary">

                                    Chưa chọn

                                </span>

                            </c:when>

                            <c:otherwise>

                                ${hd.phuongThuc}

                            </c:otherwise>

                        </c:choose>

                    </td>

                </tr>

                <tr>

                    <th>Trạng thái thanh toán</th>

                    <td>

                        <c:choose>

                            <c:when test="${hd.trangThai=='Đã thanh toán'}">

                                <span class="badge bg-success">

                                    Đã thanh toán

                                </span>

                            </c:when>

                            <c:when test="${hd.trangThai=='Thanh toán tại phòng khám'}">

                                <span class="badge bg-warning text-dark">

                                    Thanh toán khi khám

                                </span>

                            </c:when>

                            <c:otherwise>

                                <span class="badge bg-danger">

                                    Chưa thanh toán

                                </span>

                            </c:otherwise>

                        </c:choose>

                    </td>

                </tr>

                <tr>

                    <th>Ngày thanh toán</th>

                    <td>

                        <c:choose>

                            <c:when test="${hd.ngayThanhToan != null}">

                                ${hd.ngayThanhToan}

                            </c:when>

                            <c:otherwise>

                                Chưa thanh toán

                            </c:otherwise>

                        </c:choose>

                    </td>

                </tr>

                <tr>

                    <th>Ghi chú</th>

                    <td>

                        <c:choose>

                            <c:when test="${empty hd.ghiChu}">

                                Không có

                            </c:when>

                            <c:otherwise>

                                ${hd.ghiChu}

                            </c:otherwise>

                        </c:choose>

                    </td>

                </tr>

            </table>
            <hr>

<h4 class="text-center mb-3">

    Thanh toán hóa đơn

</h4>

<c:choose>



    <c:when test="${hd.trangThai=='Đã thanh toán'}">

        <div class="alert alert-success text-center">

            <i class="fa-solid fa-circle-check"></i>

            Hóa đơn đã được thanh toán.

        </div>

    </c:when>



    <c:when test="${hd.trangThai=='Thanh toán tại phòng khám'}">

        <div class="alert alert-warning text-center">

            <i class="fa-solid fa-hospital"></i>

            Bạn đã chọn thanh toán tại phòng khám.

        </div>

    </c:when>

</c:choose>
            <div class="text-center mt-4">

                <a href="${pageContext.request.contextPath}/hoadon"
                   class="btn btn-secondary">

                    <i class="fa-solid fa-arrow-left"></i>

                    Quay lại

                </a>

            </div>

        </div>

    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>
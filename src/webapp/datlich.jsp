<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Đặt lịch khám</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet">

<!-- Font Awesome -->
<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

<style>

body{
    font-family:Arial,Helvetica,sans-serif;
    background:#f5f7fa;
}

.navbar{
    background:#0d6efd;
}

.navbar-brand{
    color:white!important;
    font-size:28px;
    font-weight:bold;
}

.nav-link{
    color:white!important;
    font-weight:500;
}

.nav-link:hover{
    color:yellow!important;
}

.page-title{
    margin:50px 0;
    text-align:center;
    font-size:40px;
    font-weight:bold;
}

.card{
    border:none;
    border-radius:15px;
    box-shadow:0 10px 20px rgba(0,0,0,.15);
}

.card-header{
    background:#0d6efd;
    color:white;
    font-size:24px;
    font-weight:bold;
    text-align:center;
}

.form-label{
    font-weight:bold;
}

.btn-submit{
    width:100%;
    padding:12px;
    font-size:20px;
}

.footer{
    margin-top:70px;
    background:#0d6efd;
    color:white;
    padding:30px;
}

</style>

</head>

<body>

<!-- ================= NAVBAR ================= -->

<nav class="navbar navbar-expand-lg navbar-dark">

<div class="container">

<a class="navbar-brand"
href="${pageContext.request.contextPath}/">

<i class="fa-solid fa-hospital"></i>

Phòng Khám Online

</a>

<button class="navbar-toggler"
type="button"
data-bs-toggle="collapse"
data-bs-target="#menu">

<span class="navbar-toggler-icon"></span>

</button>

<div class="collapse navbar-collapse"
id="menu">

<ul class="navbar-nav ms-auto">

<li class="nav-item">
<a class="nav-link"
href="${pageContext.request.contextPath}/">
Trang chủ
</a>
</li>

<li class="nav-item">
<a class="nav-link"
href="${pageContext.request.contextPath}/bacsi">
Bác sĩ
</a>
</li>

<li class="nav-item">
<a class="nav-link"
href="${pageContext.request.contextPath}/chuyenkhoa">
Chuyên khoa
</a>
</li>

<li class="nav-item">
<a class="nav-link"
href="${pageContext.request.contextPath}/dichvu">
Dịch vụ
</a>
</li>

<li class="nav-item">
<a class="nav-link active"
href="${pageContext.request.contextPath}/datlich">
Đặt lịch
</a>
</li>

</ul>

</div>

</div>

</nav>

<!-- ================= TIÊU ĐỀ ================= -->

<div class="container">

<h2 class="page-title">

ĐẶT LỊCH KHÁM

</h2>

<div class="row justify-content-center">

<div class="col-lg-8">

<div class="card">

<div class="card-header">

Thông tin đặt lịch

</div>

<div class="card-body">

<form action="${pageContext.request.contextPath}/datlich"
method="post">
<!-- Thông báo thành công -->

<c:if test="${param.success == 1}">
    <div class="alert alert-success">
        <i class="fa-solid fa-circle-check"></i>
        Đặt lịch khám thành công!
    </div>
</c:if>

<!-- Thông báo lỗi -->

<c:if test="${not empty error}">
    <div class="alert alert-danger">
        ${error}
    </div>
</c:if>

<!-- Chọn bác sĩ -->

<div class="mb-3">

    <label class="form-label">
        Bác sĩ
    </label>

    <select class="form-select"
            name="maBacSi"
            required>

        <option value="">
            -- Chọn bác sĩ --
        </option>

        <c:forEach items="${bacSis}" var="bs">

            <option value="${bs.maBacSi}">

                ${bs.hoTen}

            </option>

        </c:forEach>

    </select>

</div>

<!-- Chọn dịch vụ -->

<div class="mb-3">

    <label class="form-label">

        Dịch vụ khám

    </label>

    <select class="form-select"
            name="maDichVu"
            required>

        <option value="">
            -- Chọn dịch vụ --
        </option>

        <c:forEach items="${dichVus}" var="dv">

            <option value="${dv.maDichVu}">

                ${dv.tenDichVu}

                -

                ${dv.giaDichVu} VNĐ

            </option>

        </c:forEach>

    </select>

</div>

<div class="row">

    <!-- Ngày khám -->

    <div class="col-md-6 mb-3">

        <label class="form-label">

            Ngày khám

        </label>

        <input type="date"
               class="form-control"
               name="ngayKham"
               required>

    </div>

    <!-- Giờ khám -->

    <div class="col-md-6 mb-3">

        <label class="form-label">

            Giờ khám

        </label>

        <input type="time"
               class="form-control"
               name="gioKham"
               required>

    </div>

</div>

<hr>

<button type="submit"
        class="btn btn-primary btn-submit">

    <i class="fa-solid fa-calendar-check"></i>

    Đặt lịch ngay

</button>

</form>

</div>

</div>

</div>

</div>

</div>
<!-- ================= FOOTER ================= -->

<footer class="footer">

    <div class="container">

        <div class="row">

            <div class="col-md-4">

                <h4>

                    <i class="fa-solid fa-hospital"></i>

                    Phòng Khám Online

                </h4>

                <p>

                    Hệ thống đặt lịch khám trực tuyến giúp bệnh nhân
                    tiết kiệm thời gian, lựa chọn bác sĩ phù hợp và
                    quản lý lịch khám một cách dễ dàng.

                </p>

            </div>

            <div class="col-md-4">

                <h4>

                    Liên hệ

                </h4>

                <p>

                    <i class="fa-solid fa-location-dot"></i>

                    123 Nguyễn Huệ, TP.HCM

                </p>

                <p>

                    <i class="fa-solid fa-phone"></i>

                    0909 999 999

                </p>

                <p>

                    <i class="fa-solid fa-envelope"></i>

                    contact@phongkhamonline.vn

                </p>

            </div>

            <div class="col-md-4">

                <h4>

                    Theo dõi chúng tôi

                </h4>

                <i class="fa-brands fa-facebook fa-2x me-3"></i>

                <i class="fa-brands fa-instagram fa-2x me-3"></i>

                <i class="fa-brands fa-youtube fa-2x me-3"></i>

                <i class="fa-brands fa-tiktok fa-2x"></i>

            </div>

        </div>

        <hr>

        <div class="text-center">

            © 2026 Phòng Khám Online - All Rights Reserved.

        </div>

    </div>

</footer>

<!-- Bootstrap JS -->

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>
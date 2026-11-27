<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
uri="jakarta.tags.core"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Dịch vụ khám bệnh</title>

<meta name="viewport"
content="width=device-width, initial-scale=1">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
rel="stylesheet">

<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

<style>

body{
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

}

.section-title{

    text-align:center;

    margin:50px 0;

    font-weight:bold;

}

.card{

    transition:.3s;

}

.card:hover{

    transform:translateY(-8px);

    box-shadow:0 8px 20px rgba(0,0,0,.2);

}

.price{

    color:red;

    font-size:24px;

    font-weight:bold;

}

.footer{

    margin-top:80px;

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

<a class="nav-link active"
href="${pageContext.request.contextPath}/dichvu">

Dịch vụ

</a>

</li>

<li class="nav-item">

<a class="nav-link"
href="${pageContext.request.contextPath}/datlich">

Đặt lịch

</a>

</li>

</ul>

</div>

</nav>

<!-- ================= DANH SÁCH DỊCH VỤ ================= -->

<div class="container">

<h2 class="section-title">

Danh sách dịch vụ khám

</h2>

<div class="row">

<c:forEach items="${dichVus}" var="dv">

<div class="col-md-4 mb-4">

<div class="card h-100">

<div class="card-body text-center">

<i class="fa-solid fa-stethoscope
fa-4x
text-primary
mb-3"></i>

<h4>

${dv.tenDichVu}

</h4>

<p class="price">

${dv.giaDichVu} VNĐ

</p>

<a
href="${pageContext.request.contextPath}/datlich"
class="btn btn-primary">

Đặt lịch ngay

</a>

</div>

</div>

</div>

</c:forEach>

</div>

</div>

<!-- ================= FOOTER ================= -->

<footer class="footer">

<div class="container text-center">

<h4>

Phòng Khám Online

</h4>

<p>

Đặt lịch khám trực tuyến nhanh chóng

</p>

<p>

© 2026 All Rights Reserved

</p>

</div>

</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>
<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Phòng Khám Online</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap -->

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Font Awesome -->

<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

<style>

body{
    font-family: Arial, Helvetica, sans-serif;
    background:#f5f7fa;
}

.navbar{
    background:#0d6efd;
}

.navbar-brand{
    font-size:28px;
    font-weight:bold;
    color:white !important;
}

.nav-link{
    color:white !important;
    font-weight:500;
}

.nav-link:hover{
    color:yellow !important;
}

.hero{
    background:linear-gradient(135deg,#0d6efd,#4facfe);
    color:white;
    padding:90px 0;
}

.hero h1{
    font-size:48px;
    font-weight:bold;
}

.hero p{
    font-size:20px;
}

.btn-book{
    padding:12px 35px;
    font-size:20px;
}

.section-title{
    text-align:center;
    margin-top:60px;
    margin-bottom:40px;
    font-weight:bold;
}

.card{
    transition:.3s;
}

.card:hover{
    transform:translateY(-8px);
    box-shadow:0 10px 20px rgba(0,0,0,.2);
}

.footer{
    background:#0d6efd;
    color:white;
    padding:30px;
    margin-top:80px;
}


/* ================================
   NÚT CHAT TƯ VẤN
================================ */

.chat-toggle {
    position: fixed;
    right: 25px;
    bottom: 25px;

    width: 62px;
    height: 62px;

    border: none;
    border-radius: 50%;

    background: linear-gradient(135deg, #0d6efd, #00b4d8);
    color: white;

    font-size: 25px;

    cursor: pointer;

    box-shadow: 0 5px 20px rgba(0, 0, 0, 0.25);

    z-index: 9999;

    transition: all 0.3s ease;
}

.chat-toggle:hover {
    transform: scale(1.1);
    box-shadow: 0 8px 25px rgba(0, 0, 0, 0.3);
}


/* ================================
   CỬA SỔ CHAT
================================ */

.chat-box {
    position: fixed;

    right: 25px;
    bottom: 100px;

    width: 350px;
    max-width: calc(100vw - 40px);

    height: 500px;

    background: white;

    border-radius: 18px;

    box-shadow: 0 10px 40px rgba(0, 0, 0, 0.25);

    overflow: hidden;

    z-index: 9998;

    display: none;

    flex-direction: column;

    animation: chatOpen 0.25s ease;
}

.chat-box.active {
    display: flex;
}

@keyframes chatOpen {

    from {
        opacity: 0;
        transform: translateY(20px) scale(0.95);
    }

    to {
        opacity: 1;
        transform: translateY(0) scale(1);
    }
}


/* ================================
   HEADER CHAT
================================ */

.chat-header {
    background: linear-gradient(135deg, #0d6efd, #00b4d8);

    color: white;

    padding: 15px;

    display: flex;

    justify-content: space-between;

    align-items: center;
}

.chat-header-left {
    display: flex;
    align-items: center;
    gap: 10px;
}

.chat-avatar {
    width: 42px;
    height: 42px;

    border-radius: 50%;

    background: white;

    color: #0d6efd;

    display: flex;

    align-items: center;
    justify-content: center;

    font-size: 20px;
}

.chat-title {
    font-weight: bold;
    font-size: 16px;
}

.chat-status {
    font-size: 12px;
    opacity: 0.9;
}

.chat-close {
    background: transparent;

    border: none;

    color: white;

    font-size: 20px;

    cursor: pointer;
}


/* ================================
   NỘI DUNG CHAT
================================ */

.chat-content {
    flex: 1;

    padding: 15px;

    overflow-y: auto;

    background: #f5f7fa;
}


/* Tin nhắn */

.chat-message {
    display: flex;

    margin-bottom: 12px;
}

.chat-message.bot {
    justify-content: flex-start;
}

.chat-message.user {
    justify-content: flex-end;
}

.message {
    max-width: 80%;

    padding: 10px 13px;

    border-radius: 15px;

    font-size: 14px;

    line-height: 1.5;
}

.bot .message {
    background: white;

    color: #333;

    border-bottom-left-radius: 5px;

    box-shadow: 0 2px 5px rgba(0,0,0,0.08);
}

.user .message {
    background: #0d6efd;

    color: white;

    border-bottom-right-radius: 5px;
}


/* ================================
   CÂU HỎI NHANH
================================ */

.chat-options {
    padding: 10px;

    background: white;

    border-top: 1px solid #eee;

    display: flex;

    flex-wrap: wrap;

    gap: 7px;
}

.chat-option {
    border: 1px solid #0d6efd;

    background: white;

    color: #0d6efd;

    border-radius: 20px;

    padding: 7px 11px;

    font-size: 12px;

    cursor: pointer;

    transition: 0.2s;
}

.chat-option:hover {
    background: #0d6efd;

    color: white;
}


/* ================================
   Ô NHẬP TIN NHẮN
================================ */

.chat-input-area {
    display: flex;

    padding: 10px;

    background: white;

    border-top: 1px solid #eee;

    gap: 8px;
}

.chat-input {
    flex: 1;

    border: 1px solid #ddd;

    border-radius: 20px;

    padding: 9px 14px;

    outline: none;

    font-size: 13px;
}

.chat-input:focus {
    border-color: #0d6efd;
}

.chat-send {
    width: 40px;
    height: 40px;

    border: none;

    border-radius: 50%;

    background: #0d6efd;

    color: white;

    cursor: pointer;
}

.chat-send:hover {
    background: #0b5ed7;
}


/* ================================
   MOBILE
================================ */

@media (max-width: 500px) {

    .chat-box {
        right: 15px;
        bottom: 90px;

        width: calc(100vw - 30px);

        height: 480px;
    }

    .chat-toggle {
        right: 15px;
        bottom: 15px;
    }
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

<ul class="navbar-nav mx-auto">

<li class="nav-item">

<a class="nav-link"
   href="${pageContext.request.contextPath}/">
    Trang chủ
</a>

</li>

<li class="nav-item">

<a class="nav-link"
   href="${pageContext.request.contextPath}/datlich">
    Đặt lịch
</a>

</li>

<li class="nav-item">
    <a class="nav-link"
       href="${pageContext.request.contextPath}/lichsudangnhap">
        Lịch sử đăng nhập
    </a>
</li>

<li class="nav-item">
    <a class="nav-link"
       href="${pageContext.request.contextPath}/nhatkyhoatdong">
        Nhật ký hoạt động
    </a>
</li>


<li class="nav-item">
    <a class="nav-link"
       href="${pageContext.request.contextPath}/hoadon">

        <i class="fa-solid fa-file-invoice-dollar"></i>

        Hóa đơn

    </a>
</li>

<li class="nav-item">

<a class="nav-link"
href="${pageContext.request.contextPath}/thongbao">

<i class="fa-solid fa-bell"></i>

Thông báo

</a>

</li>
<c:if test="${sessionScope.user.vaiTro=='Admin'}">

<li class="nav-item">

<a class="nav-link"
href="${pageContext.request.contextPath}/quanlythongbao">

<i class="fa-solid fa-bullhorn"></i>

Quản lý thông báo

</a>

</li>

</c:if>

<li class="nav-item">

<a class="nav-link" href="#bacsi">
    Bác sĩ
</a>

</li>

<li class="nav-item">

<a class="nav-link"
   href="#chuyenkhoa">
    Chuyên khoa
</a>

</li>

<li class="nav-item">

<a class="nav-link"
   href="#dichvu">
    Dịch vụ
</a>

</li>

<li class="nav-item">

<a class="nav-link"
   href="${pageContext.request.contextPath}/lienhe">
    Liên hệ
</a>

</li>

<li class="nav-item">
<a class="nav-link"
   href="${pageContext.request.contextPath}/danhgiabacsi">
    Đánh giá bác sĩ
    </a>
</li>


</ul>

<!-- Góc phải -->

<c:choose>

    <c:when test="${sessionScope.user == null}">

        <button
            class="btn btn-light me-2"
            data-bs-toggle="modal"
            data-bs-target="#loginModal">

            Đăng nhập

        </button>

        <button
            class="btn btn-warning"
            data-bs-toggle="modal"
            data-bs-target="#registerModal">

            Đăng ký

        </button>

    </c:when>

    <c:otherwise>

        <div class="dropdown">

            <button
                class="btn btn-warning dropdown-toggle"
                data-bs-toggle="dropdown">

                Xin chào, ${sessionScope.user.hoTen}

            </button>

            <ul class="dropdown-menu">

                <li>
                    <a class="dropdown-item" href="#">
                        Hồ sơ
                    </a>
                </li>

                <li>
                    <a class="dropdown-item"
                       href="${pageContext.request.contextPath}/lichkham">
                        Lịch khám
                    </a>
                </li>
                
                <li>
    <a class="dropdown-item"
       href="${pageContext.request.contextPath}/lichsudangnhap">

        <i class="fa-solid fa-clock-rotate-left"></i>

        Lịch sử đăng nhập

    </a>
</li>

<li>
    <a class="dropdown-item"
       href="${pageContext.request.contextPath}/nhatkyhoatdong">

        <i class="fa-solid fa-list-check"></i>

        Nhật ký hoạt động

    </a>
</li>

<li>

<a class="dropdown-item"
href="${pageContext.request.contextPath}/thongbao">

<i class="fa-solid fa-bell"></i>

Thông báo

</a>

</li>

<li>

<a class="dropdown-item"
href="${pageContext.request.contextPath}/quanlythongbao">

<i class="fa-solid fa-bullhorn"></i>

Quản lý thông báo

</a>

</li>
                
                <li>
                    <a class="dropdown-item"
                       href="${pageContext.request.contextPath}/logout">
                        Đăng xuất
                    </a>
                </li>

            </ul>

        </div>

    </c:otherwise>

</c:choose>

</div>
</div>
</nav>
<c:if test="${param.loginRequired == 'true'}">

<div class="container mt-3">

    <div class="alert alert-warning text-center">

        <i class="fa-solid fa-circle-exclamation"></i>

        Vui lòng đăng nhập để đặt lịch khám!

    </div>

</div>

</c:if>

<!-- ================= HERO ================= -->

<section class="hero">

<div class="container">

<div class="row align-items-center">

<div class="col-md-6">

<h1>

Chăm sóc sức khỏe của bạn

</h1>

<p>

Đặt lịch khám trực tuyến nhanh chóng,tiết kiệm thời gian và lựa chọn bác sĩ phù hợp.

</p>

<button class="btn btn-warning btn-book">

Đặt lịch ngay

</button>

</div>

<div class="col-md-6 text-center">

<i class="fa-solid fa-user-doctor"
style="font-size:220px;"></i>

</div>

</div>

</div>

</section>
<!-- ================= CHUYÊN KHOA ================= -->

<div class="container" id="chuyenkhoa">

<h2 class="section-title">
    Chuyên khoa nổi bật
</h2>

<div class="row">

    <div class="col-md-3 mb-4">

        <div class="card text-center p-4">

            <i class="fa-solid fa-heart-pulse fa-3x text-danger mb-3"></i>

            <h5>Nội khoa</h5>

            <p>
                Khám và điều trị các bệnh nội khoa.
            </p>

        </div>

    </div>

    <div class="col-md-3 mb-4">

        <div class="card text-center p-4">

            <i class="fa-solid fa-user-doctor fa-3x text-primary mb-3"></i>

            <h5>Ngoại khoa</h5>

            <p>
                Phẫu thuật và điều trị ngoại khoa.
            </p>

        </div>

    </div>

    <div class="col-md-3 mb-4">

        <div class="card text-center p-4">

            <i class="fa-solid fa-heart fa-3x text-danger mb-3"></i>

            <h5>Tim mạch</h5>

            <p>
                Khám và điều trị bệnh tim mạch.
            </p>

        </div>

    </div>

    <div class="col-md-3 mb-4">

        <div class="card text-center p-4">

            <i class="fa-solid fa-child fa-3x text-success mb-3"></i>

            <h5>Nhi khoa</h5>

            <p>
                Chăm sóc sức khỏe trẻ em.
            </p>

        </div>

    </div>

</div>

</div>

<!-- ================= BÁC SĨ ================= -->

<section id="bacsi">

<div class="container">

    <h2 class="section-title">

        Bác sĩ nổi bật

    </h2>

    <div class="row">

    <div class="col-md-4 mb-4">

        <div class="card text-center">

            <div class="card-body">

                <i class="fa-solid fa-user-doctor fa-5x text-primary mb-3"></i>

                <h4>BS. Nguyễn Văn An</h4>

                <p>Nội khoa</p>

                <button class="btn btn-primary">

                    Xem lịch khám

                </button>

            </div>

        </div>

    </div>

    <div class="col-md-4 mb-4">

        <div class="card text-center">

            <div class="card-body">

                <i class="fa-solid fa-user-doctor fa-5x text-success mb-3"></i>

                <h4>BS. Trần Minh Đức</h4>

                <p>Tim mạch</p>

                <button class="btn btn-success">

                    Xem lịch khám

                </button>

            </div>

        </div>

    </div>

    <div class="col-md-4 mb-4">

        <div class="card text-center">

            <div class="card-body">

                <i class="fa-solid fa-user-doctor fa-5x text-danger mb-3"></i>

                <h4>BS. Lê Thị Hoa</h4>

                <p>Nhi khoa</p>

                <button class="btn btn-danger">

                    Xem lịch khám

                </button>

            </div>

        </div>

    </div>

</div>

</div>
</section>

<!-- ================= DỊCH VỤ ================= -->

<div class="container" id="dichvu">

<h2 class="section-title">

    Dịch vụ khám

</h2>

<div class="row">

    <div class="col-md-3 mb-4">

        <div class="card">

            <div class="card-body text-center">

                <i class="fa-solid fa-stethoscope fa-3x text-primary mb-3"></i>

                <h5>Khám tổng quát</h5>

                <h4 class="text-danger">

                    150.000 VNĐ

                </h4>

            </div>

        </div>

    </div>

    <div class="col-md-3 mb-4">

        <div class="card">

            <div class="card-body text-center">

                <i class="fa-solid fa-heart-pulse fa-3x text-danger mb-3"></i>

                <h5>Khám tim mạch</h5>

                <h4 class="text-danger">

                    250.000 VNĐ

                </h4>

            </div>

        </div>

    </div>

    <div class="col-md-3 mb-4">

        <div class="card">

            <div class="card-body text-center">

                <i class="fa-solid fa-vials fa-3x text-success mb-3"></i>

                <h5>Xét nghiệm</h5>

                <h4 class="text-danger">

                    300.000 VNĐ

                </h4>

            </div>

        </div>

    </div>

    <div class="col-md-3 mb-4">

        <div class="card">

            <div class="card-body text-center">

                <i class="fa-solid fa-x-ray fa-3x text-warning mb-3"></i>

                <h5>Siêu âm</h5>

                <h4 class="text-danger">

                    200.000 VNĐ

                </h4>

            </div>

        </div>

    </div>

</div>

</div>
<!-- ================= FOOTER ================= -->

<footer class="footer"
id="lienhe">

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

<!-- ================= LOGIN MODAL ================= -->

<div class="modal fade"
id="loginModal"
tabindex="-1">

<div class="modal-dialog">

<div class="modal-content">

<div class="modal-header bg-primary text-white">

<h5 class="modal-title">

Đăng nhập

</h5>

<button
type="button"
class="btn-close btn-close-white"
data-bs-dismiss="modal">

</button>

</div>

<form
action="${pageContext.request.contextPath}/login"
method="post">

<div class="modal-body">

<div class="mb-3">

<label>

Tên đăng nhập

</label>

<input
type="text"
name="username"
class="form-control"
required>

</div>

<div class="mb-3">

<label>

Mật khẩu

</label>

<input
type="password"
name="password"
class="form-control"
required>

</div>
<!-- Quên mật khẩu -->
<div class="text-end mb-3">

    <a href="${pageContext.request.contextPath}/forgotpassword"
       class="text-decoration-none">

        <i class="fa-solid fa-key"></i>

        Quên mật khẩu?

    </a>

</div>


<c:if test="${not empty error}">

    <div class="alert alert-danger">

        ${error}

    </div>

</c:if>

</div>

<div class="modal-footer">

<button
type="submit"
class="btn btn-primary">

Đăng nhập

</button>

</div>

</form>

</div>

</div>

</div>

<!-- ================= REGISTER MODAL ================= -->

<div class="modal fade"
id="registerModal"
tabindex="-1">

<div class="modal-dialog modal-lg">

<div class="modal-content">

<div class="modal-header bg-success text-white">

<h5 class="modal-title">

Đăng ký tài khoản

</h5>

<button
type="button"
class="btn-close btn-close-white"
data-bs-dismiss="modal">

</button>

</div>

<form
action="${pageContext.request.contextPath}/register"
method="post">

<div class="modal-body">
<c:if test="${not empty registerError}">
    <div class="alert alert-danger alert-dismissible fade show">
        ${registerError}
        <button type="button"
                class="btn-close"
                data-bs-dismiss="alert">
        </button>
    </div>
</c:if>

<c:if test="${not empty sessionScope.message}">
    <div class="alert alert-success alert-dismissible fade show">
        ${sessionScope.message}
        <button type="button"
                class="btn-close"
                data-bs-dismiss="alert">
        </button>
    </div>

    <c:remove var="message" scope="session"/>
</c:if>

<div class="row">

<div class="col-md-6 mb-3">

<label>Họ và tên</label>

<input
type="text"
name="hoTen"
class="form-control"
required>

</div>

<div class="col-md-6 mb-3">

<label>Tên đăng nhập</label>

<input
type="text"
name="tenDangNhap"
class="form-control"
required>

</div>

<div class="col-md-6 mb-3">

<label>Email</label>

<input
type="email"
name="email"
class="form-control"
required>

</div>

<div class="col-md-6 mb-3">

<label>Số điện thoại</label>

<input
type="text"
name="soDienThoai"
class="form-control"
required>

</div>

<div class="col-md-6 mb-3">

<label>Mật khẩu</label>

<input
type="password"
name="matKhau"
class="form-control"
required>

</div>

<div class="col-md-6 mb-3">

<label>Nhập lại mật khẩu</label>

<input
type="password"
name="confirmPassword"
class="form-control"
required>

</div>

</div>

</div>

<div class="modal-footer">

<button
type="submit"
class="btn btn-success">

Đăng ký

</button>

</div>

</form>

</div>

</div>

</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<!-- Tự động mở Login Modal khi cần đăng nhập -->
<c:if test="${param.loginRequired == 'true'}">
<script>
document.addEventListener("DOMContentLoaded", function () {
    var loginModal = new bootstrap.Modal(
        document.getElementById("loginModal")
    );
    loginModal.show();
});
</script>
</c:if>

<!-- Tự động mở Register Modal khi có lỗi hoặc đăng ký thành công -->
<c:if test="${not empty registerError || not empty sessionScope.message}">
<script>
document.addEventListener("DOMContentLoaded", function () {
    var registerModal = new bootstrap.Modal(
        document.getElementById("registerModal")
    );
    registerModal.show();
});
</script>
</c:if>

<!-- =========================================
     CHAT TƯ VẤN PHÒNG KHÁM
========================================= -->

<!-- Nút mở chat -->
<button
    type="button"
    class="chat-toggle"
    id="chatToggle"
    title="Nhắn tin tư vấn">

    <i class="fa-solid fa-comments"></i>

</button>


<!-- Cửa sổ chat -->
<div class="chat-box" id="chatBox">

    <!-- Header -->
    <div class="chat-header">

        <div class="chat-header-left">

            <div class="chat-avatar">

                <i class="fa-solid fa-user-doctor"></i>

            </div>

            <div>

                <div class="chat-title">
                    Tư vấn phòng khám
                </div>

                <div class="chat-status">

                    <i class="fa-solid fa-circle"
                       style="font-size: 8px;">
                    </i>

                    Đang hoạt động

                </div>

            </div>

        </div>

        <button
            type="button"
            class="chat-close"
            id="chatClose">

            <i class="fa-solid fa-xmark"></i>

        </button>

    </div>


    <!-- Nội dung chat -->
    <div class="chat-content" id="chatContent">

        <!-- Tin nhắn đầu tiên -->

        <div class="chat-message bot">

            <div class="message">

                Xin chào! 👋

                <br><br>

                Chào mừng bạn đến với
                <strong>Phòng Khám Online</strong>.

                <br><br>

                Tôi có thể hỗ trợ bạn về:

                <br>
                • Đặt lịch khám
                <br>
                • Thông tin bác sĩ
                <br>
                • Dịch vụ khám
                <br>
                • Thanh toán
                <br>
                • Hủy lịch khám

                <br><br>

                Bạn cần hỗ trợ vấn đề gì?

            </div>

        </div>

    </div>


    <!-- Câu hỏi nhanh -->

    <div class="chat-options">

        <button
            type="button"
            class="chat-option"
            onclick="sendQuickMessage('Tôi muốn đặt lịch khám')">

            📅 Đặt lịch khám

        </button>

        <button
            type="button"
            class="chat-option"
            onclick="sendQuickMessage('Tôi muốn xem bác sĩ')">

            👨‍⚕️ Bác sĩ

        </button>

        <button
            type="button"
            class="chat-option"
            onclick="sendQuickMessage('Tôi muốn hỏi về thanh toán')">

            💳 Thanh toán

        </button>

        <button
            type="button"
            class="chat-option"
            onclick="sendQuickMessage('Tôi muốn hủy lịch khám')">

            ❌ Hủy lịch

        </button>

    </div>


    <!-- Nhập tin nhắn -->

    <div class="chat-input-area">

        <input
            type="text"
            id="chatInput"
            class="chat-input"
            placeholder="Nhập câu hỏi..."
            autocomplete="off">

        <button
            type="button"
            class="chat-send"
            onclick="sendMessage()">

            <i class="fa-solid fa-paper-plane"></i>

        </button>

    </div>

</div>
<script>

/* =========================================
   MỞ / ĐÓNG CHAT
========================================= */

const chatToggle =
    document.getElementById("chatToggle");

const chatBox =
    document.getElementById("chatBox");

const chatClose =
    document.getElementById("chatClose");

const chatInput =
    document.getElementById("chatInput");

const chatContent =
    document.getElementById("chatContent");


chatToggle.addEventListener("click", function () {

    chatBox.classList.toggle("active");

});


chatClose.addEventListener("click", function () {

    chatBox.classList.remove("active");

});


/* =========================================
   ENTER ĐỂ GỬI
========================================= */

chatInput.addEventListener("keypress", function(event) {

    if (event.key === "Enter") {

        event.preventDefault();

        sendMessage();

    }

});


/* =========================================
   THÊM TIN NHẮN VÀO CHAT
========================================= */

function addMessage(text, type) {

    const messageContainer =
        document.createElement("div");

    messageContainer.className =
        "chat-message " + type;


    const message =
        document.createElement("div");

    message.className = "message";

    message.innerHTML = text;


    messageContainer.appendChild(message);

    chatContent.appendChild(messageContainer);


    // Cuộn xuống cuối

    chatContent.scrollTop =
        chatContent.scrollHeight;

}


/* =========================================
   GỬI TIN NHẮN
========================================= */

function sendMessage() {

    const text =
        chatInput.value.trim();


    if (text === "") {

        return;

    }


    // Hiển thị tin nhắn người dùng

    addMessage(
        escapeHtml(text),
        "user"
    );


    chatInput.value = "";


    // Hiển thị đang trả lời

    setTimeout(function() {

        const reply =
            getBotReply(text);

        addMessage(
            reply,
            "bot"
        );

    }, 500);

}


/* =========================================
   GỬI CÂU HỎI NHANH
========================================= */

function sendQuickMessage(text) {

    addMessage(
        escapeHtml(text),
        "user"
    );


    setTimeout(function() {

        const reply =
            getBotReply(text);

        addMessage(
            reply,
            "bot"
        );

    }, 500);

}


/* =========================================
   TRẢ LỜI TỰ ĐỘNG
========================================= */

function getBotReply(text) {

    const message =
        text.toLowerCase();


    /* ĐẶT LỊCH */

    if (
        message.includes("đặt lịch") ||
        message.includes("lich kham") ||
        message.includes("lịch khám")
    ) {

        return `
            📅 <strong>Đặt lịch khám</strong>

            <br><br>

            Bạn có thể đặt lịch khám trực tuyến
            bằng cách chọn chức năng
            <strong>Đặt lịch khám</strong>
            trên menu.

            <br><br>

            Bạn có thể lựa chọn:

            <br>
            • Bác sĩ
            <br>
            • Dịch vụ khám
            <br>
            • Ngày khám
            <br>
            • Giờ khám

            <br><br>

            Sau khi đặt lịch thành công,
            hệ thống sẽ thông báo cho bạn.
        `;

    }


    /* BÁC SĨ */

    if (
        message.includes("bác sĩ") ||
        message.includes("bac si")
    ) {

        return `
            👨‍⚕️ <strong>Thông tin bác sĩ</strong>

            <br><br>

            Bạn có thể xem danh sách bác sĩ
            và thông tin chuyên môn của từng bác sĩ
            tại mục <strong>Bác sĩ</strong> trên website.

            <br><br>

            Nếu muốn đặt lịch với bác sĩ,
            bạn có thể chọn bác sĩ trực tiếp
            trong phần <strong>Đặt lịch khám</strong>.
        `;

    }


    /* THANH TOÁN */

    if (
        message.includes("thanh toán") ||
        message.includes("thanh toan") ||
        message.includes("tiền") ||
        message.includes("tien")
    ) {

        return `
            💳 <strong>Thanh toán</strong>

            <br><br>

            Phòng khám hỗ trợ thanh toán
            theo các phương thức được hiển thị
            trong phần thanh toán.

            <br><br>

            Bạn có thể chọn
            <strong>Thanh toán ngay</strong>
            hoặc
            <strong>Thanh toán khi khám</strong>
            nếu hệ thống cho phép.
        `;

    }


    /* HỦY LỊCH */

    if (
        message.includes("hủy lịch") ||
        message.includes("huy lich") ||
        message.includes("hủy") ||
        message.includes("huy")
    ) {

        return `
            ❌ <strong>Hủy lịch khám</strong>

            <br><br>

            Nếu bạn muốn hủy lịch khám,
            hãy vào phần
            <strong>Lịch khám của tôi</strong>.

            <br><br>

            Sau đó gửi
            <strong>yêu cầu hủy lịch</strong>
            và chờ Admin xét duyệt.
        `;

    }


    /* XIN CHÀO */

    if (
        message.includes("xin chào") ||
        message.includes("xin chao") ||
        message.includes("hello") ||
        message.includes("hi")
    ) {

        return `
            👋 Xin chào bạn!

            <br><br>

            Phòng khám có thể hỗ trợ bạn
            về đặt lịch, bác sĩ, dịch vụ,
            thanh toán và hủy lịch.

            <br><br>

            Bạn cần hỗ trợ vấn đề gì?
        `;

    }


    /* GIỜ LÀM VIỆC */

    if (
        message.includes("giờ") ||
        message.includes("gio") ||
        message.includes("thời gian") ||
        message.includes("thoi gian")
    ) {

        return `
            🕐 <strong>Thời gian khám</strong>

            <br><br>

            Thời gian khám phụ thuộc vào
            lịch làm việc của từng bác sĩ.

            <br><br>

            Bạn có thể vào phần
            <strong>Đặt lịch khám</strong>
            để xem các khung giờ còn trống.
        `;

    }


    /* CÂU HỎI KHÔNG XÁC ĐỊNH */

    return `
        🤖 Cảm ơn bạn đã gửi câu hỏi.

        <br><br>

        Hiện tại tôi có thể hỗ trợ
        các vấn đề sau:

        <br><br>

        📅 Đặt lịch khám
        <br>
        👨‍⚕️ Thông tin bác sĩ
        <br>
        💳 Thanh toán
        <br>
        ❌ Hủy lịch khám
        <br>
        🕐 Thời gian khám

        <br><br>

        Bạn có thể chọn một trong
        các nội dung trên để được hỗ trợ.
    `;

}


/* =========================================
   CHỐNG CHÈN HTML
========================================= */

function escapeHtml(text) {

    const div =
        document.createElement("div");

    div.textContent = text;

    return div.innerHTML;

}

</script>
</body>
</html>
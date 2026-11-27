<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Liên hệ</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
rel="stylesheet">

<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

<style>

body{
    background:#f5f7fa;
    font-family:Arial,Helvetica,sans-serif;
}

.navbar{
    background:#0d6efd;
}

.navbar-brand{
    color:#fff!important;
    font-size:28px;
    font-weight:bold;
}

.nav-link{
    color:#fff!important;
}

.title{
    text-align:center;
    margin:50px 0;
    font-size:40px;
    font-weight:bold;
}

.card{
    border:none;
    border-radius:15px;
    box-shadow:0 5px 15px rgba(0,0,0,.15);
}

.footer{
    background:#0d6efd;
    color:white;
    padding:30px;
    margin-top:60px;
}

</style>

</head>

<body>

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
<a class="nav-link"
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

<li class="nav-item">
<a class="nav-link active"
href="${pageContext.request.contextPath}/lienhe">
Liên hệ
</a>
</li>

</ul>

</div>

</nav>

<div class="container">

<h2 class="title">
LIÊN HỆ
</h2>

<div class="row">

<div class="col-md-6">

<div class="card">

<div class="card-body">

<h4>
<i class="fa-solid fa-location-dot text-danger"></i>
Địa chỉ
</h4>

<p>
123 Nguyễn Huệ, Quận 1, TP. Hồ Chí Minh
</p>

<hr>

<h4>
<i class="fa-solid fa-phone text-success"></i>
Điện thoại
</h4>

<p>
0909 999 999
</p>

<hr>

<h4>
<i class="fa-solid fa-envelope text-primary"></i>
Email
</h4>

<p>
contact@phongkhamonline.vn
</p>

<hr>

<h4>
<i class="fa-solid fa-clock text-warning"></i>
Giờ làm việc
</h4>

<p>Thứ 2 - Chủ nhật</p>

<p>07:00 - 21:00</p>

</div>

</div>

</div>

<div class="col-md-6">

<div class="card">

<div class="card-body">

<h4 class="mb-4">
Gửi phản hồi
</h4>

<%
String message=(String)request.getAttribute("message");
String error=(String)request.getAttribute("error");
%>

<% if(message!=null){ %>

<div class="alert alert-success">
<%=message%>
</div>

<% } %>

<% if(error!=null){ %>

<div class="alert alert-danger">
<%=error%>
</div>

<% } %>

<form action="${pageContext.request.contextPath}/lienhe"
method="post">

<div class="mb-3">

<label>Họ và tên</label>

<input type="text"
name="hoTen"
class="form-control"
required>

</div>

<div class="mb-3">

<label>Email</label>

<input type="email"
name="email"
class="form-control"
required>

</div>

<div class="mb-3">

<label>Số điện thoại</label>

<input type="text"
name="soDienThoai"
class="form-control">

</div>

<div class="mb-3">

<label>Nội dung</label>

<textarea
name="noiDung"
rows="5"
class="form-control"
required></textarea>

</div>

<button
type="submit"
class="btn btn-primary w-100">

<i class="fa-solid fa-paper-plane"></i>
Gửi liên hệ

</button>

</form>

</div>

</div>

</div>

</div>

</div>

<footer class="footer">

<div class="container">

<div class="text-center">

<h4>Phòng Khám Online</h4>

<p>Chăm sóc sức khỏe - Đồng hành cùng bạn.</p>

<hr>

<p>© 2026 Phòng Khám Online</p>

</div>

</div>

</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>
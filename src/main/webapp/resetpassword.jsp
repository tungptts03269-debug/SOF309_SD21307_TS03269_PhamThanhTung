<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Đặt lại mật khẩu</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
rel="stylesheet">

<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
rel="stylesheet">

<style>

body{

    background:#f5f5f5;

}

.card{

    margin-top:80px;
    border-radius:15px;
    box-shadow:0 3px 12px rgba(0,0,0,.15);

}

</style>

</head>

<body>

<div class="container">

<div class="row justify-content-center">

<div class="col-md-5">

<div class="card">

<div class="card-header bg-success text-white">

<h4 class="mb-0">

<i class="fa-solid fa-key"></i>

Đặt lại mật khẩu

</h4>

</div>

<div class="card-body">

<c:if test="${sessionScope.otpVerified != true}">

<div class="alert alert-danger">

Bạn chưa xác thực OTP.

</div>

<a href="${pageContext.request.contextPath}/forgotpassword"
class="btn btn-primary w-100">

Quay lại

</a>

</c:if>

<c:if test="${sessionScope.otpVerified == true}">

<form action="${pageContext.request.contextPath}/resetpassword"
method="post">

<div class="mb-3">

<label>Mật khẩu mới</label>

<input
type="password"
name="newPassword"
class="form-control"
required>

</div>

<div class="mb-3">

<label>Nhập lại mật khẩu</label>

<input
type="password"
name="confirmPassword"
class="form-control"
required>

</div>

<c:if test="${not empty error}">

<div class="alert alert-danger">

${error}

</div>

</c:if>

<c:if test="${not empty success}">

<div class="alert alert-success">

${success}

</div>

</c:if>

<button
type="submit"
class="btn btn-success w-100">

<i class="fa-solid fa-check"></i>

Đổi mật khẩu

</button>

</form>

</c:if>

</div>

</div>

</div>

</div>

</div>

</body>
</html>
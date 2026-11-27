<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Xác thực OTP</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
rel="stylesheet">

<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
rel="stylesheet">

<style>

body{

    background:#f5f5f5;

}

.card{

    max-width:500px;

    margin:80px auto;

    border-radius:15px;

    box-shadow:0 0 15px rgba(0,0,0,.15);

}

</style>

</head>

<body>

<div class="card">

<div class="card-header bg-primary text-white">

<h4>

<i class="fa-solid fa-shield-halved"></i>

Xác thực OTP

</h4>

</div>

<div class="card-body">

<p class="text-center">

Một mã OTP đã được gửi đến email của bạn.<br>

Vui lòng kiểm tra hộp thư và nhập mã xác thực.

</p>

<form action="${pageContext.request.contextPath}/verifyotp"
method="post">

<div class="mb-3">

<label class="form-label">

Mã OTP

</label>

<input
type="text"
name="otp"
class="form-control"
placeholder="Nhập mã OTP gồm 6 số"
maxlength="6"
required>

</div>

<c:if test="${not empty error}">

<div class="alert alert-danger">

${error}

</div>

</c:if>

<c:if test="${not empty message}">

<div class="alert alert-success">

${message}

</div>

</c:if>

<button
type="submit"
class="btn btn-primary w-100">

<i class="fa-solid fa-check"></i>

Xác nhận OTP

</button>

</form>

<div class="text-center mt-3">

<a href="${pageContext.request.contextPath}/forgotpassword">

<i class="fa-solid fa-arrow-left"></i>

Quay lại

</a>

</div>

</div>

</div>

</body>
</html>
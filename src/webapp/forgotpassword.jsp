<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Quên mật khẩu</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<link
href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
rel="stylesheet">

<link
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
rel="stylesheet">

<style>

body{
    background:#f4f6f9;
}

.card{
    max-width:500px;
    margin:60px auto;
    border:none;
    border-radius:15px;
    box-shadow:0 5px 15px rgba(0,0,0,.15);
}

.card-header{
    border-radius:15px 15px 0 0 !important;
}

</style>

</head>

<body>

<div class="container">

<div class="card">

<div class="card-header bg-primary text-white">

<h4 class="mb-0">

<i class="fa-solid fa-key"></i>

Quên mật khẩu

</h4>

</div>

<div class="card-body">

<form
action="${pageContext.request.contextPath}/forgotpassword"
method="post">

<div class="mb-3">

<label class="form-label">

Tên đăng nhập

</label>

<input
type="text"
name="username"
class="form-control"
placeholder="Nhập tên đăng nhập"
required>

</div>

<div class="mb-3">

<label class="form-label">

Email

</label>

<input
type="email"
name="email"
class="form-control"
placeholder="Nhập email đã đăng ký"
required>

</div>

<c:if test="${not empty message}">

<div class="alert alert-success">

${message}

</div>

</c:if>

<c:if test="${not empty error}">

<div class="alert alert-danger">

${error}

</div>

</c:if>

<div class="d-grid">

<button
type="submit"
class="btn btn-primary">

<i class="fa-solid fa-rotate"></i>

Đổi mật khẩu

</button>

</div>

<div class="text-center mt-3">

<a
href="${pageContext.request.contextPath}/index.jsp"
class="text-decoration-none">

<i class="fa-solid fa-arrow-left"></i>

Quay lại trang chủ

</a>

</div>

</form>

</div>

</div>

</div>

</body>

</html>
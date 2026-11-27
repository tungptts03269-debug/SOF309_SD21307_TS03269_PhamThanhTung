<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Xác nhận thanh toán</title>

<link
href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
rel="stylesheet">

<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

<style>

body{

    background:#f5f7fa;

}

.card-payment{

    max-width:450px;

    margin:auto;

    margin-top:50px;

    border-radius:15px;

}

.money{

    color:#dc3545;

    font-size:35px;

    font-weight:bold;

}

.logo{

    font-size:55px;

    color:#0d6efd;

}

.info{

    font-size:18px;

}

</style>

</head>

<body>

<div class="container">

<div class="card shadow card-payment">

<div class="card-header bg-primary text-white text-center">

<h3>

<i class="fa-solid fa-hospital"></i>

PHÒNG KHÁM ONLINE

</h3>

</div>

<div class="card-body text-center">

<div class="logo">

<i class="fa-solid fa-money-check-dollar"></i>

</div>

<br>

<h5>

XÁC NHẬN THANH TOÁN

</h5>

<hr>

<p class="info">

Mã thanh toán

</p>

<h4>

${tt.maThanhToan}

</h4>

<hr>

<p class="info">

Số tiền cần thanh toán

</p>

<div class="money">

${tt.soTien} VNĐ

</div>

<hr>

<p>

Bạn có chắc chắn muốn thanh toán hóa đơn này?

</p>

<form
action="${pageContext.request.contextPath}/thanhtoanngay"
method="get">

<input
type="hidden"
name="id"
value="${tt.maThanhToan}">

<button
type="submit"
class="btn btn-success btn-lg w-100">

<i class="fa-solid fa-circle-check"></i>

XÁC NHẬN THANH TOÁN

</button>

</form>

<br>

<a
href="javascript:history.back();"
class="btn btn-secondary w-100">

<i class="fa-solid fa-arrow-left"></i>

Quay lại

</a>

</div>

</div>

</div>

</body>

</html>
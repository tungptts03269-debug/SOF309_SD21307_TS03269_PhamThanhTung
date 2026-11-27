<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Thanh toán Online</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet">

<link rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

<style>

body{
    background:#f4f6f9;
}

.payment-card{
    max-width:700px;
    margin:auto;
    margin-top:40px;
}

.money{
    font-size:34px;
    color:#dc3545;
    font-weight:bold;
}

.qr-box{

    border:2px dashed #0d6efd;

    padding:20px;

    border-radius:15px;

    background:white;

    display:inline-block;
}

.status{

    font-size:18px;

    font-weight:bold;

    color:#fd7e14;
}

.info{

    font-size:17px;
}

</style>

</head>

<body>

<div class="container">

<div class="card shadow payment-card">

<div class="card-header bg-primary text-white">

<h3 class="text-center">

<i class="fa-solid fa-qrcode"></i>

THANH TOÁN ONLINE

</h3>

</div>

<div class="card-body text-center">

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

<br>

<div class="qr-box">

<img
src="data:image/png;base64,${qrImage}"
width="300"
height="300">

</div>

<br><br>

<div class="status">

<i class="fa-solid fa-clock"></i>

Đang chờ thanh toán...

</div>

<br>

<div class="alert alert-info">

<i class="fa-solid fa-mobile-screen-button"></i>

Vui lòng dùng điện thoại quét mã QR để thanh toán.

</div>

<br>

<a href="${pageContext.request.contextPath}/hoadon"
class="btn btn-secondary">

<i class="fa-solid fa-arrow-left"></i>

Quay lại hóa đơn

</a>

</div>

</div>

</div>
<script>

function checkPayment(){

    fetch("${pageContext.request.contextPath}/paymentstatus?id=${tt.maThanhToan}")

    .then(response => response.text())

    .then(result => {

        if(result.trim() === "PAID"){

            window.location.href =
                "${pageContext.request.contextPath}/hoadon";

        }

    })

    .catch(error => console.log(error));

}

setInterval(checkPayment,1000);

</script>

</body>

</html>
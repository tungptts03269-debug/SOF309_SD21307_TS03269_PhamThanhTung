<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Dashboard Thống Kê</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<link
href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
rel="stylesheet">

<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

<style>

body{
    background:#f4f6f9;
}

.card{
    border:none;
    border-radius:15px;
    box-shadow:0 5px 15px rgba(0,0,0,.08);
}

.card h2{
    font-weight:bold;
}

.icon{
    font-size:45px;
    margin-bottom:15px;
}

.title{
    font-weight:bold;
}

canvas{
    width:100%!important;
    height:400px!important;
}

</style>

</head>

<body>

<div class="container mt-4">

<div class="d-flex justify-content-between mb-4">

<h2>

<i class="fa-solid fa-chart-line text-primary"></i>

Dashboard Thống Kê

</h2>

<a href="${pageContext.request.contextPath}/indexadmin.jsp"
class="btn btn-secondary">

<i class="fa-solid fa-arrow-left"></i>

Quay lại

</a>

</div>

<!-- ================= THỐNG KÊ ================= -->

<div class="row g-4">

<div class="col-md-3">

<div class="card text-center p-4">

<i class="fa-solid fa-users icon text-primary"></i>

<h5>Tài khoản</h5>

<h2>${tongTaiKhoan}</h2>

</div>

</div>

<div class="col-md-3">

<div class="card text-center p-4">

<i class="fa-solid fa-user-doctor icon text-success"></i>

<h5>Bác sĩ</h5>

<h2>${tongBacSi}</h2>

</div>

</div>

<div class="col-md-3">

<div class="card text-center p-4">

<i class="fa-solid fa-hospital-user icon text-warning"></i>

<h5>Bệnh nhân</h5>

<h2>${tongBenhNhan}</h2>

</div>

</div>

<div class="col-md-3">

<div class="card text-center p-4">

<i class="fa-solid fa-calendar-check icon text-danger"></i>

<h5>Lịch khám</h5>

<h2>${tongLichKham}</h2>

</div>

</div>

</div>

<!-- ================= LỊCH KHÁM ================= -->

<div class="row mt-4 g-4">

<div class="col-md-3">

<div class="card text-center p-3">

<h5>Hôm nay</h5>

<h3 class="text-primary">

${lichHomNay}

</h3>

</div>

</div>

<div class="col-md-3">

<div class="card text-center p-3">

<h5>Chờ xác nhận</h5>

<h3 class="text-warning">

${choXacNhan}

</h3>

</div>

</div>

<div class="col-md-3">

<div class="card text-center p-3">

<h5>Đã xác nhận</h5>

<h3 class="text-info">

${daXacNhan}

</h3>

</div>

</div>

<div class="col-md-3">

<div class="card text-center p-3">

<h5>Đã khám</h5>

<h3 class="text-success">

${daKham}

</h3>

</div>

</div>

</div>

<!-- ================= DOANH THU ================= -->

<div class="row mt-4 g-4">

<div class="col-md-3">

<div class="card text-center p-4">

<i class="fa-solid fa-sack-dollar icon text-success"></i>

<h5>Doanh thu hôm nay</h5>

<h3>

${doanhThuHomNay} đ

</h3>

</div>

</div>

<div class="col-md-3">

<div class="card text-center p-4">

<i class="fa-solid fa-coins icon text-warning"></i>

<h5>Doanh thu tháng</h5>

<h3>

${doanhThuThang} đ

</h3>

</div>

</div>

<div class="col-md-3">

<div class="card text-center p-4">

<i class="fa-solid fa-money-bill-wave icon text-primary"></i>

<h5>Doanh thu năm</h5>

<h3>

${doanhThuNam} đ

</h3>

</div>

</div>

<div class="col-md-3">

<div class="card text-center p-4">

<i class="fa-solid fa-wallet icon text-danger"></i>

<h5>Tổng doanh thu</h5>

<h3>

${tongDoanhThu} đ

</h3>

</div>

</div>

</div>

<!-- ================= LIÊN HỆ ================= -->

<div class="row mt-4">

<div class="col-md-12">

<div class="card p-4">

<h4>

<i class="fa-solid fa-envelope text-danger"></i>

Liên hệ chưa phản hồi

</h4>

<hr>

<h2 class="text-danger">

${lienHe}

</h2>

</div>

</div>

</div>

<!-- ================= BIỂU ĐỒ ================= -->

<div class="row mt-4">

<div class="col-md-12">

<div class="card p-4">

<h4>

<i class="fa-solid fa-chart-column"></i>

Biểu đồ doanh thu

</h4>

<hr>

<canvas id="myChart"></canvas>

</div>

</div>

</div>

</div>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<script>

const doanhThu = new Array(12).fill(0);

<c:forEach items="${doanhThuTheoThang}" var="item">

doanhThu[${item[0]} - 1] = ${item[1]};

</c:forEach>

new Chart(document.getElementById("myChart"),{

    type:'bar',

    data:{

        labels:[
            'Th1',
            'Th2',
            'Th3',
            'Th4',
            'Th5',
            'Th6',
            'Th7',
            'Th8',
            'Th9',
            'Th10',
            'Th11',
            'Th12'
        ],

        datasets:[{

            label:'Doanh thu',

            data:doanhThu,

            backgroundColor:'#0d6efd'

        }]

    },

    options:{

        responsive:true,

        scales:{
            y:{
                beginAtZero:true
            }
        }

    }

});

</script>

</body>

</html>
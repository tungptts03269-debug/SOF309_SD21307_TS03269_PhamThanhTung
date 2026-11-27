<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    if (session.getAttribute("admin") == null) {
        response.sendRedirect(request.getContextPath() + "/loginadmin");
        return;
    }
%>

<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1">

    <title>Admin - Phòng Khám Online</title>

    <!-- Bootstrap -->
    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
        rel="stylesheet">

    <!-- Font Awesome -->
    <link
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
        rel="stylesheet">

    <style>

        body {
            background: #f5f7fa;
            font-family: Arial, Helvetica, sans-serif;
        }

        /* SIDEBAR */

        .sidebar {
            width: 250px;
            min-height: 100vh;
            background: #0d6efd;
            position: fixed;
            left: 0;
            top: 0;
            padding: 25px 15px;
            color: white;
        }

        .sidebar h3 {
            text-align: center;
            margin-bottom: 30px;
            font-weight: bold;
        }

        .sidebar a {
            display: block;
            color: white;
            text-decoration: none;
            padding: 13px 15px;
            margin-bottom: 8px;
            border-radius: 8px;
            transition: 0.3s;
        }

        .sidebar a:hover {
            background: rgba(255,255,255,0.2);
            transform: translateX(5px);
        }

        .sidebar a i {
            width: 25px;
            margin-right: 8px;
        }

        .sidebar hr {
            border-color: rgba(255,255,255,0.4);
            margin: 20px 0;
        }

        /* CONTENT */

        .content {
            margin-left: 250px;
            padding: 35px;
        }

        .card {
            border: none;
            border-radius: 15px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.08);
            transition: 0.3s;
        }

        .card:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 25px rgba(0,0,0,0.15);
        }

        .icon {
            font-size: 45px;
            margin-bottom: 15px;
        }

        .welcome {
            background: linear-gradient(
                135deg,
                #0d6efd,
                #4facfe
            );
            color: white;
        }

    </style>

</head>

<body>


<!-- ================= SIDEBAR ================= -->

<div class="sidebar">

    <h3>

        <i class="fa-solid fa-user-shield"></i>

        <br>

        ADMIN

    </h3>


    <!-- THỐNG KÊ -->

    <a href="${pageContext.request.contextPath}/dashboard">

        <i class="fa-solid fa-chart-line"></i>

        Thống kê doanh thu

    </a>


    <!-- LỊCH KHÁM -->

    <a href="${pageContext.request.contextPath}/lichkham">

        <i class="fa-solid fa-calendar-check"></i>

        Quản lý lịch khám

    </a>


    <!-- THANH TOÁN -->

    <a href="${pageContext.request.contextPath}/quanlythanhtoan">

        <i class="fa-solid fa-money-bill-wave"></i>

        Quản lý thanh toán

    </a>


    <!-- NHẬT KÝ -->

    <a href="${pageContext.request.contextPath}/nhatkyhoatdong">

        <i class="fa-solid fa-clock-rotate-left"></i>

        Nhật ký hoạt động

    </a>
    
    <a href="${pageContext.request.contextPath}/quanlyyeucauhuylich">
    <i class="fa-solid fa-calendar-xmark"></i>
    Quản lý yêu cầu hủy lịch
</a>


    <!-- QUẢN LÝ LIÊN HỆ -->

    <a href="${pageContext.request.contextPath}/quanlylienhe">

        <i class="fa-solid fa-envelope"></i>

        Quản lý liên hệ

    </a>


    <!-- QUẢN LÝ ĐÁNH GIÁ -->

    <a href="${pageContext.request.contextPath}/quanlydanhgia">

        <i class="fa-solid fa-star"></i>

        Quản lý đánh giá

    </a>


    <hr>


    <!-- ĐĂNG XUẤT -->

    <a href="${pageContext.request.contextPath}/logoutadmin">

        <i class="fa-solid fa-right-from-bracket"></i>

        Đăng xuất

    </a>

</div>



<!-- ================= CONTENT ================= -->

<div class="content">


    <!-- WELCOME -->

    <div class="card welcome p-4 mb-4">

        <h2>

            Xin chào ADMIN

        </h2>

        <p class="mb-0">

            Chào mừng bạn đến hệ thống quản trị
            Phòng Khám Online.

        </p>

    </div>



    <!-- ================= CARDS ================= -->

    <div class="row g-4">


        <!-- THỐNG KÊ -->

        <div class="col-md-3">

            <div class="card text-center p-4 h-100">

                <i class="fa-solid fa-chart-column icon text-info"></i>

                <h5>

                    Thống kê

                </h5>

                <a
                    href="${pageContext.request.contextPath}/dashboard"
                    class="btn btn-info mt-3">

                    Truy cập

                </a>

            </div>

        </div>



        <!-- LỊCH KHÁM -->

        <div class="col-md-3">

            <div class="card text-center p-4 h-100">

                <i class="fa-solid fa-calendar-check icon text-primary"></i>

                <h5>

                    Quản lý lịch khám

                </h5>

                <a
                    href="${pageContext.request.contextPath}/lichkham"
                    class="btn btn-primary mt-3">

                    Truy cập

                </a>

            </div>

        </div>



        <!-- THANH TOÁN -->

        <div class="col-md-3">

            <div class="card text-center p-4 h-100">

                <i class="fa-solid fa-money-bill-wave icon text-success"></i>

                <h5>

                    Quản lý thanh toán

                </h5>

                <a
                    href="${pageContext.request.contextPath}/quanlythanhtoan"
                    class="btn btn-success mt-3">

                    Truy cập

                </a>

            </div>

        </div>



        <!-- NHẬT KÝ -->

        <div class="col-md-3">

            <div class="card text-center p-4 h-100">

                <i class="fa-solid fa-clock-rotate-left icon text-warning"></i>

                <h5>

                    Nhật ký hoạt động

                </h5>

                <a
                    href="${pageContext.request.contextPath}/nhatkyhoatdong"
                    class="btn btn-warning mt-3">

                    Truy cập

                </a>

            </div>

        </div>
        
        
        <div class="col-md-3">

    <div class="card text-center p-4">

        <i class="fa-solid fa-calendar-xmark icon text-danger"></i>

        <h5>Yêu cầu hủy lịch khám</h5>

        <p class="text-muted">
            Xem và xử lý yêu cầu hủy lịch của bệnh nhân.
        </p>

        <a href="${pageContext.request.contextPath}/quanlyyeucauhuylich"
           class="btn btn-danger mt-3">

            <i class="fa-solid fa-arrow-right"></i>
            Truy cập

        </a>

    </div>

</div>

        <!-- ================= LIÊN HỆ ================= -->

        <div class="col-md-3">

            <div class="card text-center p-4 h-100">

                <i class="fa-solid fa-envelope icon text-danger"></i>

                <h5>

                    Quản lý liên hệ

                </h5>

                <p class="text-muted">

                    Xem và xử lý các yêu cầu liên hệ
                    của khách hàng.

                </p>

                <a
                    href="${pageContext.request.contextPath}/quanlylienhe"
                    class="btn btn-danger mt-auto">

                    Truy cập

                </a>

            </div>

        </div>



        <!-- ================= ĐÁNH GIÁ ================= -->

        <div class="col-md-3">

            <div class="card text-center p-4 h-100">

                <i class="fa-solid fa-star icon text-warning"></i>

                <h5>

                    Quản lý đánh giá

                </h5>

                <p class="text-muted">

                    Xem đánh giá của bệnh nhân
                    dành cho bác sĩ.

                </p>

                <a
                    href="${pageContext.request.contextPath}/quanlydanhgia"
                    class="btn btn-warning mt-auto">

                    Truy cập

                </a>

            </div>

        </div>


    </div>

</div>


</body>

</html>
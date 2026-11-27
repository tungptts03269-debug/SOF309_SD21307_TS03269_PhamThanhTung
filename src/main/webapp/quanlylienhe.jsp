<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1">

    <title>Quản lý liên hệ - Admin</title>

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

        .header {
            background: linear-gradient(
                135deg,
                #0d6efd,
                #4facfe
            );

            color: white;
            padding: 25px;
            border-radius: 15px;
            margin-bottom: 25px;
        }

        .card {
            border: none;
            border-radius: 15px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.08);
        }

        .table {
            vertical-align: middle;
        }

        .table thead {
            background: #0d6efd;
            color: white;
        }

        .badge-chua {
            background: #ffc107;
            color: #000;
        }

        .badge-da {
            background: #198754;
        }

        .btn-back {
            margin-bottom: 20px;
        }

        .content {
            max-width: 1400px;
            margin: auto;
        }

    </style>

</head>

<body>

<div class="container-fluid p-4">

    <div class="content">


        <!-- ================= QUAY LẠI ================= -->

        <a
            href="${pageContext.request.contextPath}/indexadmin.jsp"
            class="btn btn-secondary btn-back">

            <i class="fa-solid fa-arrow-left"></i>

            Quay lại Admin

        </a>


        <!-- ================= HEADER ================= -->

        <div class="header">

            <h2>

                <i class="fa-solid fa-envelope"></i>

                Quản lý liên hệ

            </h2>

            <p class="mb-0">

                Quản lý các yêu cầu liên hệ của khách hàng
                và bệnh nhân.

            </p>

        </div>


        <!-- ================= THÔNG BÁO ================= -->

        <c:if test="${not empty message}">

            <div class="alert alert-success">

                <i class="fa-solid fa-circle-check"></i>

                ${message}

            </div>

        </c:if>


        <c:if test="${not empty error}">

            <div class="alert alert-danger">

                <i class="fa-solid fa-circle-exclamation"></i>

                ${error}

            </div>

        </c:if>


        <!-- ================= DANH SÁCH ================= -->

        <div class="card p-4">

            <div class="d-flex justify-content-between align-items-center mb-3">

                <h4>

                    <i class="fa-solid fa-list"></i>

                    Danh sách liên hệ

                </h4>

                <span class="badge bg-primary">

                    Tổng: ${lienHes.size()}

                </span>

            </div>


            <div class="table-responsive">

                <table class="table table-bordered table-hover text-center">

                    <thead>

                        <tr>

                            <th>#</th>

                            <th>Họ tên</th>

                            <th>Email</th>

                            <th>Số điện thoại</th>

                            <th>Nội dung</th>

                            <th>Ngày gửi</th>

                            <th>Trạng thái</th>

                            <th>Thao tác</th>

                        </tr>

                    </thead>


                    <tbody>

                        <c:choose>

                            <c:when test="${not empty lienHes}">

                                <c:forEach
                                    var="lh"
                                    items="${lienHes}"
                                    varStatus="st">

                                    <tr>

                                        <!-- STT -->

                                        <td>

                                            ${st.count}

                                        </td>


                                        <!-- HỌ TÊN -->

                                        <td>

                                            <strong>

                                                ${lh.hoTen}

                                            </strong>

                                        </td>


                                        <!-- EMAIL -->

                                        <td>

                                            ${lh.email}

                                        </td>


                                        <!-- SỐ ĐIỆN THOẠI -->

                                        <td>

                                            ${lh.soDienThoai}

                                        </td>


                                        <!-- NỘI DUNG -->

                                        <td
                                            class="text-start"
                                            style="max-width:300px;">

                                            ${lh.noiDung}

                                        </td>


                                        <!-- NGÀY GỬI -->

                                        <td>

                                            ${lh.ngayGui}

                                        </td>


                                        <!-- TRẠNG THÁI -->

                                        <td>

                                            <c:choose>

                                                <c:when test="${lh.trangThai == 'Đã xử lý'}">

                                                    <span class="badge badge-da">

                                                        <i class="fa-solid fa-check"></i>

                                                        Đã xử lý

                                                    </span>

                                                </c:when>

                                                <c:otherwise>

                                                    <span class="badge badge-chua">

                                                        <i class="fa-solid fa-clock"></i>

                                                        Chưa xử lý

                                                    </span>

                                                </c:otherwise>

                                            </c:choose>

                                        </td>


                                        <!-- THAO TÁC -->

                                        <td>

                                            <a
                                                href="${pageContext.request.contextPath}/xemlienhe?id=${lh.maLienHe}"
                                                class="btn btn-primary btn-sm">

                                                <i class="fa-solid fa-eye"></i>

                                                Xem

                                            </a>


                                            <a
                                                href="${pageContext.request.contextPath}/xulylienhe?id=${lh.maLienHe}"
                                                class="btn btn-success btn-sm"
                                                onclick="return confirm('Bạn có chắc muốn đánh dấu liên hệ này là đã xử lý?');">

                                                <i class="fa-solid fa-check"></i>

                                                Xử lý

                                            </a>


                                            <a
                                                href="${pageContext.request.contextPath}/xoalienhe?id=${lh.maLienHe}"
                                                class="btn btn-danger btn-sm"
                                                onclick="return confirm('Bạn có chắc muốn xóa liên hệ này không?');">

                                                <i class="fa-solid fa-trash"></i>

                                                Xóa

                                            </a>

                                        </td>

                                    </tr>

                                </c:forEach>

                            </c:when>


                            <c:otherwise>

                                <tr>

                                    <td
                                        colspan="8"
                                        class="text-center text-muted p-4">

                                        <i
                                            class="fa-solid fa-envelope-open-text fa-2x mb-2">
                                        </i>

                                        <br>

                                        Chưa có liên hệ nào.

                                    </td>

                                </tr>

                            </c:otherwise>

                        </c:choose>

                    </tbody>

                </table>

            </div>

        </div>

    </div>

</div>


<!-- Bootstrap JS -->

<script
    src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js">
</script>

</body>

</html>
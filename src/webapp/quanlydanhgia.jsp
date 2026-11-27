<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1">

    <title>Quản lý đánh giá - Admin</title>

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
                #ffc107,
                #ff9800
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
            background: #ffc107;
            color: #000;
        }

        .star {
            color: #ffc107;
            font-size: 18px;
        }

        .btn-back {
            margin-bottom: 20px;
        }

        .content {
            max-width: 1400px;
            margin: auto;
        }

        .rating-content {
            max-width: 350px;
            text-align: left;
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

                <i class="fa-solid fa-star"></i>

                Quản lý đánh giá bác sĩ

            </h2>

            <p class="mb-0">

                Xem và quản lý đánh giá của bệnh nhân
                dành cho bác sĩ.

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

                    Danh sách đánh giá

                </h4>

                <span class="badge bg-warning text-dark">

                    Tổng: ${danhGias.size()}

                </span>

            </div>


            <div class="table-responsive">

                <table
                    class="table table-bordered table-hover text-center">

                    <thead>

                        <tr>

                            <th>#</th>

                            <th>Bệnh nhân</th>

                            <th>Bác sĩ</th>

                            <th>Số sao</th>

                            <th>Nội dung</th>

                            <th>Ngày đánh giá</th>

                            <th>Thao tác</th>

                        </tr>

                    </thead>


                    <tbody>

                        <c:choose>

                            <c:when test="${not empty danhGias}">

                                <c:forEach
                                    var="dg"
                                    items="${danhGias}"
                                    varStatus="st">

                                    <tr>

                                        <!-- STT -->

                                        <td>

                                            ${st.count}

                                        </td>


                                        <!-- BỆNH NHÂN -->

                                        <td>

                                            <strong>

                                                ${dg.benhNhan.hoTen}

                                            </strong>

                                        </td>


                                        <!-- BÁC SĨ -->

                                        <td>

                                            <strong>

                                                ${dg.bacSi.hoTen}

                                            </strong>

                                        </td>


                                        <!-- SỐ SAO -->

                                        <td>

                                            <c:forEach
                                                begin="1"
                                                end="5"
                                                var="i">

                                                <c:choose>

                                                    <c:when test="${i <= dg.soSao}">

                                                        <i class="fa-solid fa-star star"></i>

                                                    </c:when>

                                                    <c:otherwise>

                                                        <i
                                                            class="fa-regular fa-star text-muted">
                                                        </i>

                                                    </c:otherwise>

                                                </c:choose>

                                            </c:forEach>

                                            <br>

                                            <small>

                                                ${dg.soSao}/5

                                            </small>

                                        </td>


                                        <!-- NỘI DUNG -->

                                        <td>

                                            <div class="rating-content">

                                                ${dg.noiDung}

                                            </div>

                                        </td>


                                        <!-- NGÀY -->

                                        <td>

                                            ${dg.ngayDanhGia}

                                        </td>


                                        <!-- THAO TÁC -->

                                        <td>

                                            <a
                                                href="${pageContext.request.contextPath}/xemdanhgia?id=${dg.maDanhGia}"
                                                class="btn btn-primary btn-sm">

                                                <i class="fa-solid fa-eye"></i>

                                                Xem

                                            </a>


                                            <a
                                                href="${pageContext.request.contextPath}/xoadanhgia?id=${dg.maDanhGia}"
                                                class="btn btn-danger btn-sm"
                                                onclick="return confirm('Bạn có chắc muốn xóa đánh giá này không?');">

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
                                        colspan="7"
                                        class="text-center text-muted p-4">

                                        <i
                                            class="fa-regular fa-star fa-2x mb-2">
                                        </i>

                                        <br>

                                        Chưa có đánh giá nào.

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
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1">

    <title>Quản lý yêu cầu hủy lịch - Admin</title>

    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
        rel="stylesheet">

    <link
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
        rel="stylesheet">

    <style>

        body {
            background: #f5f7fa;
            font-family: Arial, Helvetica, sans-serif;
        }

        .container-main {
            max-width: 1500px;
            margin: 30px auto;
        }

        .header {
            background: linear-gradient(
                135deg,
                #dc3545,
                #ff6b6b
            );

            color: white;
            padding: 25px;
            border-radius: 15px;
            margin-bottom: 25px;

            box-shadow: 0 5px 15px rgba(0,0,0,0.12);
        }

        .header h2 {
            margin: 0;
            font-weight: bold;
        }

        .header p {
            margin-top: 8px;
            margin-bottom: 0;
        }

        .card {
            border: none;
            border-radius: 15px;

            box-shadow: 0 5px 15px rgba(0,0,0,0.08);
        }

        .stats-card {
            border-radius: 15px;
            border: none;

            box-shadow: 0 5px 15px rgba(0,0,0,0.08);
        }

        .table {
            vertical-align: middle;
            margin-bottom: 0;
        }

        .table thead {
            background: #212529;
            color: white;
        }

        .table thead th {
            white-space: nowrap;
            vertical-align: middle;
        }

        .table tbody td {
            vertical-align: middle;
        }

        .reason {
            max-width: 250px;
            min-width: 180px;
            white-space: normal;
            word-break: break-word;
        }

        .badge {
            font-size: 13px;
            padding: 7px 10px;
        }

        .badge-pending {
            background: #ffc107;
            color: #000;
        }

        .badge-approved {
            background: #198754;
            color: white;
        }

        .badge-rejected {
            background: #dc3545;
            color: white;
        }

        .btn-back {
            margin-bottom: 20px;
        }

        .empty-box {
            padding: 50px 20px;
            color: #6c757d;
        }

        .empty-box i {
            font-size: 45px;
            margin-bottom: 15px;
        }

        .action-buttons {
            min-width: 180px;
        }

        .appointment-info {
            line-height: 1.6;
        }

        .table-responsive {
            border-radius: 0 0 15px 15px;
        }

        .btn-action {
            margin: 2px;
        }

        @media (max-width: 768px) {

            .container-main {
                margin: 15px auto;
            }

            .header {
                padding: 20px;
            }

            .header h2 {
                font-size: 22px;
            }

            .action-buttons {
                min-width: 150px;
            }

        }

    </style>

</head>

<body>

<div class="container-fluid">


        <div class="header">

            <h2>

                <i class="fa-solid fa-calendar-xmark"></i>

                Quản lý yêu cầu hủy lịch khám

            </h2>

            <p>

                Xem, duyệt hoặc từ chối các yêu cầu hủy lịch khám
                của bệnh nhân.

            </p>

        </div>


        <c:if test="${not empty message}">

            <div
                class="alert alert-success alert-dismissible fade show"
                role="alert">

                <i class="fa-solid fa-circle-check"></i>

                ${message}

                <button
                    type="button"
                    class="btn-close"
                    data-bs-dismiss="alert">
                </button>

            </div>

        </c:if>


        <c:if test="${not empty error}">

            <div
                class="alert alert-danger alert-dismissible fade show"
                role="alert">

                <i class="fa-solid fa-circle-exclamation"></i>

                ${error}

                <button
                    type="button"
                    class="btn-close"
                    data-bs-dismiss="alert">
                </button>

            </div>

        </c:if>


        <div class="row g-3 mb-4">


            <div class="col-md-4">

                <div class="card stats-card bg-primary text-white">

                    <div class="card-body">

                        <div
                            class="d-flex justify-content-between align-items-center">

                            <div>

                                <h6>
                                    Tổng yêu cầu
                                </h6>

                                <h3 class="mb-0">

                                    <c:choose>

                                        <c:when test="${not empty danhSach}">
                                            ${danhSach.size()}
                                        </c:when>

                                        <c:otherwise>
                                            0
                                        </c:otherwise>

                                    </c:choose>

                                </h3>

                            </div>

                            <i
                                class="fa-solid fa-list-check"
                                style="font-size:35px;">
                            </i>

                        </div>

                    </div>

                </div>

            </div>


            <div class="col-md-4">

                <div class="card stats-card bg-warning text-dark">

                    <div class="card-body">

                        <div
                            class="d-flex justify-content-between align-items-center">

                            <div>

                                <h6>
                                    Chờ duyệt
                                </h6>

                                <h3 class="mb-0">

                                    ${soLuongChoDuyet}

                                </h3>

                            </div>

                            <i
                                class="fa-solid fa-hourglass-half"
                                style="font-size:35px;">
                            </i>

                        </div>

                    </div>

                </div>

            </div>


            <div class="col-md-4">

                <div class="card stats-card bg-info text-white">

                    <div class="card-body">

                        <div
                            class="d-flex justify-content-between align-items-center">

                            <div>

                                <h6>
                                    Chức năng
                                </h6>

                                <p class="mb-0">
                                    Duyệt / Từ chối yêu cầu
                                </p>

                            </div>

                            <i
                                class="fa-solid fa-user-shield"
                                style="font-size:35px;">
                            </i>

                        </div>

                    </div>

                </div>

            </div>

        </div>


        <div class="card">


            <div
                class="card-header bg-dark text-white
                       d-flex justify-content-between align-items-center">

                <h5 class="mb-0">

                    <i class="fa-solid fa-list"></i>

                    Danh sách yêu cầu hủy lịch

                </h5>


                <span class="badge bg-light text-dark">

                    <c:choose>

                        <c:when test="${not empty danhSach}">
                            ${danhSach.size()}
                        </c:when>

                        <c:otherwise>
                            0
                        </c:otherwise>

                    </c:choose>

                    yêu cầu

                </span>

            </div>


            <div class="card-body p-0">

                <div class="table-responsive">

                    <table
                        class="table table-bordered table-hover
                               text-center mb-0">


                        <thead>

                            <tr>

                                <th>
                                    #
                                </th>

                                <th>
                                    Mã yêu cầu
                                </th>

                                <th>
                                    Bệnh nhân
                                </th>

                                <th>
                                    Bác sĩ
                                </th>

                                <th>
                                    Dịch vụ
                                </th>

                                <th>
                                    Ngày khám
                                </th>

                                <th>
                                    Giờ khám
                                </th>

                                <th>
                                    Lý do hủy
                                </th>

                                <th>
                                    Ngày gửi
                                </th>

                                <th>
                                    Trạng thái
                                </th>

                                <th>
                                    Ngày xử lý
                                </th>

                                <th>
                                    Thao tác
                                </th>

                            </tr>

                        </thead>


                        <tbody>


                            <c:choose>

                                <c:when test="${not empty danhSach}">


                                    <c:forEach
                                        var="yc"
                                        items="${danhSach}"
                                        varStatus="st">


                                        <tr>


                                            <td>

                                                ${st.count}

                                            </td>


                                            <td>

                                                <strong>

                                                    #${yc.maYeuCau}

                                                </strong>

                                            </td>


                                            <td>

                                                <div
                                                    class="appointment-info">

                                                    <strong>

                                                        ${yc.lichKham.benhNhan.hoTen}

                                                    </strong>

                                                </div>

                                            </td>


                                            <td>

                                                <div
                                                    class="appointment-info">

                                                    <i
                                                        class="fa-solid
                                                               fa-user-doctor
                                                               text-primary">
                                                    </i>

                                                    ${yc.lichKham.bacSi.hoTen}

                                                </div>

                                            </td>


                                            <td>

                                                ${yc.lichKham.dichVu.tenDichVu}

                                            </td>


                                            <td>

                                                <c:if
                                                    test="${not empty yc.lichKham.ngayKham}">

                                                    <fmt:formatDate
                                                        value="${yc.lichKham.ngayKham}"
                                                        pattern="dd/MM/yyyy"/>

                                                </c:if>

                                            </td>


                                            <td>

                                                <c:if
                                                    test="${not empty yc.lichKham.gioKham}">

                                                    <fmt:formatDate
                                                        value="${yc.lichKham.gioKham}"
                                                        pattern="HH:mm"/>

                                                </c:if>

                                            </td>


                                            <td
                                                class="reason text-start">

                                                <c:choose>

                                                    <c:when
                                                        test="${not empty yc.lyDo}">

                                                        ${yc.lyDo}

                                                    </c:when>

                                                    <c:otherwise>

                                                        <span
                                                            class="text-muted">

                                                            Không có lý do

                                                        </span>

                                                    </c:otherwise>

                                                </c:choose>

                                            </td>


                                            <td>

                                                <c:if
                                                    test="${not empty yc.ngayGui}">

                                                    <fmt:formatDate
                                                        value="${yc.ngayGui}"
                                                        pattern="dd/MM/yyyy HH:mm"/>

                                                </c:if>

                                            </td>


                                            <td>


                                                <c:choose>


                                                    <c:when
                                                        test="${yc.trangThai == 'Chờ duyệt'}">

                                                        <span
                                                            class="badge badge-pending">

                                                            <i
                                                                class="fa-solid fa-clock">
                                                            </i>

                                                            Chờ duyệt

                                                        </span>

                                                    </c:when>


                                                    <c:when
                                                        test="${yc.trangThai == 'Đã duyệt'}">

                                                        <span
                                                            class="badge badge-approved">

                                                            <i
                                                                class="fa-solid fa-check">
                                                            </i>

                                                            Đã duyệt

                                                        </span>

                                                    </c:when>


                                                    <c:when
                                                        test="${yc.trangThai == 'Từ chối'}">

                                                        <span
                                                            class="badge badge-rejected">

                                                            <i
                                                                class="fa-solid fa-xmark">
                                                            </i>

                                                            Từ chối

                                                        </span>

                                                    </c:when>


                                                    <c:otherwise>

                                                        <span
                                                            class="badge bg-secondary">

                                                            ${yc.trangThai}

                                                        </span>

                                                    </c:otherwise>


                                                </c:choose>


                                            </td>


                                            <td>


                                                <c:choose>


                                                    <c:when
                                                        test="${not empty yc.ngayXuLy}">

                                                        <fmt:formatDate
                                                            value="${yc.ngayXuLy}"
                                                            pattern="dd/MM/yyyy HH:mm"/>

                                                    </c:when>


                                                    <c:otherwise>

                                                        <span
                                                            class="text-muted">

                                                            Chưa xử lý

                                                        </span>

                                                    </c:otherwise>


                                                </c:choose>


                                            </td>


                                            <td
                                                class="action-buttons">


                                                <c:choose>


                                                    <c:when
                                                        test="${yc.trangThai == 'Chờ duyệt'}">


                                                        <a
                                                            href="${pageContext.request.contextPath}/quanlyyeucauhuylich?action=approve&id=${yc.maYeuCau}"
                                                            class="btn btn-success btn-sm btn-action"
                                                            onclick="return confirm('Bạn có chắc muốn DUYỆT yêu cầu hủy lịch này không?');">

                                                            <i
                                                                class="fa-solid fa-check">
                                                            </i>

                                                            Duyệt

                                                        </a>


                                                        <a
                                                            href="${pageContext.request.contextPath}/quanlyyeucauhuylich?action=reject&id=${yc.maYeuCau}"
                                                            class="btn btn-danger btn-sm btn-action"
                                                            onclick="return confirm('Bạn có chắc muốn TỪ CHỐI yêu cầu hủy lịch này không?');">

                                                            <i
                                                                class="fa-solid fa-xmark">
                                                            </i>

                                                            Từ chối

                                                        </a>


                                                    </c:when>


                                                    <c:otherwise>


                                                        <span
                                                            class="text-muted">

                                                            <i
                                                                class="fa-solid
                                                                       fa-circle-check">
                                                            </i>

                                                            Đã xử lý

                                                        </span>


                                                    </c:otherwise>


                                                </c:choose>


                                            </td>


                                        </tr>


                                    </c:forEach>


                                </c:when>


                                <c:otherwise>


                                    <tr>

                                        <td
                                            colspan="12"
                                            class="empty-box text-center">


                                            <i
                                                class="fa-solid
                                                       fa-calendar-xmark d-block">
                                            </i>


                                            <h5>

                                                Chưa có yêu cầu hủy lịch

                                            </h5>


                                            <p class="mb-0">

                                                Hiện tại chưa có bệnh nhân nào
                                                gửi yêu cầu hủy lịch khám.

                                            </p>


                                        </td>

                                    </tr>


                                </c:otherwise>


                            </c:choose>


                        </tbody>


                    </table>

                </div>

            </div>

        </div>


        <div class="mt-4 mb-5">

            <a
                href="${pageContext.request.contextPath}/indexadmin.jsp"
                class="btn btn-secondary">

                <i class="fa-solid fa-arrow-left"></i>

                Quay về Admin

            </a>

        </div>


    </div>

</div>


<script
    src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js">
</script>


</body>

</html>
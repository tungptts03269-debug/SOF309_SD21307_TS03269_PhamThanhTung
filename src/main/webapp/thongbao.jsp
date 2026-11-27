<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="vi">

<head>
<meta charset="UTF-8">
<title>Thông báo</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet">

<style>

body{
    background:#f5f5f5;
}

.card{
    border-radius:12px;
}

.title{
    font-size:30px;
    font-weight:bold;
    color:#0d6efd;
}

.unread{
    border-left:6px solid #dc3545;
}

.read{
    border-left:6px solid #198754;
}

.time{
    color:gray;
    font-size:13px;
}

</style>

</head>

<body>

<div class="container mt-4">

    <div class="d-flex justify-content-between mb-4">

        <h2 class="title">
            🔔 Thông báo của bạn
        </h2>

        <a href="index.jsp" class="btn btn-secondary">
            Trang chủ
        </a>

    </div>


    <c:if test="${empty listThongBao}">

        <div class="alert alert-info">

            Bạn chưa có thông báo nào.

        </div>

    </c:if>



    <c:forEach var="tb" items="${listThongBao}">

        <div class="card mb-3 ${tb.daDoc ? 'read' : 'unread'}">

            <div class="card-body">

                <div class="d-flex justify-content-between">

                    <h5>

                        ${tb.tieuDe}

                    </h5>

                    <span class="time">

                        ${tb.ngayGui}

                    </span>

                </div>

                <hr>

                <p>

                    ${tb.noiDung}

                </p>

                <c:choose>

                    <c:when test="${tb.daDoc}">

                        <span class="badge bg-success">

                            Đã đọc

                        </span>

                    </c:when>

                    <c:otherwise>

                        <span class="badge bg-danger">

                            Chưa đọc

                        </span>

                    </c:otherwise>

                </c:choose>

            </div>

        </div>

    </c:forEach>

</div>

</body>

</html>
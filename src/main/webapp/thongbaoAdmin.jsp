<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="vi">

<head>

<meta charset="UTF-8">

<title>Thông báo Admin</title>

<link
href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
rel="stylesheet">

<style>

body{
    background:#f5f5f5;
}

.card{
    border-radius:12px;
    margin-bottom:15px;
}

.title{
    font-size:30px;
    font-weight:bold;
    color:#0d6efd;
}

.unread{
    border-left:6px solid red;
}

.read{
    border-left:6px solid green;
}

.time{
    color:gray;
    font-size:13px;
}

</style>

</head>

<body>

<div class="container mt-4">

    <div class="d-flex justify-content-between align-items-center mb-4">

        <h2 class="title">
            🔔 Thông báo Admin
        </h2>

        <span class="badge bg-danger fs-6">
            Chưa đọc:
            ${soThongBaoChuaDoc}
        </span>

    </div>

    <c:if test="${empty listThongBaoAdmin}">

        <div class="alert alert-info">

            Hiện chưa có thông báo nào.

        </div>

    </c:if>

    <c:forEach var="tb" items="${listThongBaoAdmin}">

        <div class="card ${tb.daDoc ? 'read' : 'unread'}">

            <div class="card-body">

                <div class="d-flex justify-content-between">

                    <div>

                        <h5>

                            Thông báo #${tb.maThongBao}

                        </h5>

                        <p>

                            ${tb.noiDung}

                        </p>

                        <small class="time">

                            ${tb.ngayGui}

                        </small>

                    </div>

                    <div class="text-end">

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

                        <br><br>

                        <a class="btn btn-primary btn-sm"

                           href="${pageContext.request.contextPath}/admin/thongbao?action=read&id=${tb.maThongBao}">

                            Đánh dấu đã đọc

                        </a>

                        <a class="btn btn-danger btn-sm"

                           onclick="return confirm('Bạn muốn xóa thông báo này?')"

                           href="${pageContext.request.contextPath}/admin/thongbao?action=delete&id=${tb.maThongBao}">

                            Xóa

                        </a>

                    </div>

                </div>

            </div>

        </div>

    </c:forEach>

</div>

</body>

</html>
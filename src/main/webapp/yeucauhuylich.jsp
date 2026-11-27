<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="vi">
<head>

<meta charset="UTF-8">

<title>Yêu cầu hủy lịch khám</title>

<link
href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
rel="stylesheet">

</head>

<body class="bg-light">

<div class="container mt-5">

    <div class="row justify-content-center">

        <div class="col-md-7">

            <div class="card shadow">

                <div class="card-header bg-danger text-white">

                    <h3 class="mb-0">
                        Yêu cầu hủy lịch khám
                    </h3>

                </div>

                <div class="card-body">

                    <c:if test="${not empty success}">
                        <div class="alert alert-success">
                            ${success}
                        </div>
                    </c:if>

                    <c:if test="${not empty error}">
                        <div class="alert alert-danger">
                            ${error}
                        </div>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/yeucauhuylich"
                          method="post">

                        <div class="mb-3">

                            <label class="form-label">
                                Mã lịch khám
                            </label>

                            <input
                                type="number"
                                class="form-control"
                                name="maLichKham"
                                required>

                        </div>

                        <div class="mb-3">

                            <label class="form-label">
                                Lý do hủy lịch
                            </label>

                            <textarea
                                class="form-control"
                                name="lyDo"
                                rows="5"
                                required></textarea>

                        </div>

                        <div class="d-grid">

                            <button
                                type="submit"
                                class="btn btn-danger">

                                Gửi yêu cầu hủy

                            </button>

                        </div>

                    </form>

                </div>

                <div class="card-footer text-center">

                    <a href="${pageContext.request.contextPath}/index.jsp"
                       class="btn btn-secondary">

                        Quay lại

                    </a>

                </div>

            </div>

        </div>

    </div>

</div>

</body>
</html>
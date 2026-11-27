<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Đánh giá bác sĩ</title>

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
            background: #f5f7fb;
            min-height: 100vh;
            font-family: Arial, sans-serif;
        }

        /* ================= CONTAINER ================= */

        .rating-container {
            max-width: 700px;
            margin: 60px auto;
            padding: 0 15px;
        }

        /* ================= CARD ================= */

        .rating-card {
            background: #ffffff;
            border-radius: 20px;
            box-shadow: 0 8px 30px rgba(0, 0, 0, 0.10);
            overflow: hidden;
        }

        /* ================= HEADER ================= */

        .rating-header {
            background: linear-gradient(135deg, #0d6efd, #4dabf7);
            color: white;
            padding: 30px;
            text-align: center;
        }

        .rating-header i {
            font-size: 45px;
            margin-bottom: 15px;
        }

        .rating-header h2 {
            font-weight: 700;
            margin-bottom: 8px;
        }

        .rating-header p {
            margin: 0;
            opacity: 0.9;
        }

        /* ================= BODY ================= */

        .rating-body {
            padding: 35px;
        }

        .form-label {
            font-weight: 600;
        }

        .form-control,
        .form-select {
            border-radius: 10px;
            padding: 12px;
        }

        .form-control:focus,
        .form-select:focus {
            box-shadow: 0 0 0 0.2rem rgba(13, 110, 253, 0.15);
        }

        /* ================= STAR ================= */

        .star-rating {
            display: flex;
            flex-direction: row-reverse;
            justify-content: flex-end;
            gap: 5px;
        }

        .star-rating input {
            display: none;
        }

        .star-rating label {
            font-size: 42px;
            color: #d5d5d5;
            cursor: pointer;
            transition: 0.2s;
        }

        .star-rating label:hover,
        .star-rating label:hover ~ label,
        .star-rating input:checked ~ label {
            color: #ffc107;
        }

        /* ================= BUTTON ================= */

        .btn-submit {
            width: 100%;
            padding: 13px;
            border-radius: 10px;
            font-size: 16px;
            font-weight: 600;
        }

        /* ================= BACK ================= */

        .back-link {
            text-decoration: none;
            color: #6c757d;
        }

        .back-link:hover {
            color: #0d6efd;
        }

        /* ================= ALERT ================= */

        .alert {
            border-radius: 10px;
        }

        /* ================= MOBILE ================= */

        @media (max-width: 576px) {

            .rating-container {
                margin: 30px auto;
            }

            .rating-body {
                padding: 25px 20px;
            }

            .rating-header {
                padding: 25px 15px;
            }

            .rating-header h2 {
                font-size: 24px;
            }

            .star-rating label {
                font-size: 36px;
            }
        }

    </style>

</head>


<body>


<div class="rating-container">

    <div class="rating-card">


        <!-- ================= HEADER ================= -->

        <div class="rating-header">

            <i class="fa-solid fa-user-doctor"></i>

            <h2>
                Đánh giá bác sĩ
            </h2>

            <p>
                Hãy chia sẻ trải nghiệm của bạn sau khi khám
            </p>

        </div>


        <!-- ================= BODY ================= -->

        <div class="rating-body">


            <!-- ================= THÔNG BÁO ================= -->

            <c:if test="${not empty error}">

                <div class="alert alert-danger">

                    <i class="fa-solid fa-circle-exclamation"></i>

                    ${error}

                </div>

            </c:if>


            <c:if test="${not empty message}">

                <div class="alert alert-success">

                    <i class="fa-solid fa-circle-check"></i>

                    ${message}

                </div>

            </c:if>


            <!-- ================= FORM ================= -->

            <form
                action="${pageContext.request.contextPath}/danhgia"
                method="post">


                <!-- ================= CHỌN BÁC SĨ ================= -->

                <div class="mb-3">
    <label class="form-label fw-bold">
        <i class="fa-solid fa-user-doctor"></i>
        Chọn bác sĩ
    </label>

    <div class="row g-3">

        <!-- Nguyễn Văn An -->
        <div class="col-md-6">
            <div class="card doctor-card h-100">
                <div class="card-body text-center">

                    <i class="fa-solid fa-user-doctor fa-4x text-primary mb-3"></i>

                    <h5 class="fw-bold">
                        BS. Nguyễn Văn An
                    </h5>

                    <p class="text-muted mb-3">
                        Chuyên khoa Nội
                    </p>

                    <input
                        type="radio"
                        class="btn-check"
                        name="maBacSi"
                        id="bacSi1"
                        value="1"
                        required>

                    <label
                        class="btn btn-outline-primary"
                        for="bacSi1">

                        <i class="fa-solid fa-check"></i>
                        Chọn bác sĩ

                    </label>

                </div>
            </div>
        </div>

        <!-- Trần Minh Đức -->
        <div class="col-md-6">
            <div class="card doctor-card h-100">
                <div class="card-body text-center">

                    <i class="fa-solid fa-user-doctor fa-4x text-success mb-3"></i>

                    <h5 class="fw-bold">
                        BS. Trần Minh Đức
                    </h5>

                    <p class="text-muted mb-3">
                        Chuyên khoa Tim mạch
                    </p>

                    <input
                        type="radio"
                        class="btn-check"
                        name="maBacSi"
                        id="bacSi2"
                        value="2">

                    <label
                        class="btn btn-outline-success"
                        for="bacSi2">

                        <i class="fa-solid fa-check"></i>
                        Chọn bác sĩ

                    </label>

                </div>
            </div>
        </div>

    </div>
</div>


                <!-- ================= ĐÁNH GIÁ SAO ================= -->

                <div class="mb-4">

                    <label class="form-label d-block">

                        <i class="fa-solid fa-star text-warning"></i>

                        Mức độ hài lòng

                    </label>


                    <div class="star-rating">


                        <input
                            type="radio"
                            name="soSao"
                            value="5"
                            id="star5"
                            required>

                        <label
                            for="star5"
                            title="5 sao">

                            ★

                        </label>


                        <input
                            type="radio"
                            name="soSao"
                            value="4"
                            id="star4">

                        <label
                            for="star4"
                            title="4 sao">

                            ★

                        </label>


                        <input
                            type="radio"
                            name="soSao"
                            value="3"
                            id="star3">

                        <label
                            for="star3"
                            title="3 sao">

                            ★

                        </label>


                        <input
                            type="radio"
                            name="soSao"
                            value="2"
                            id="star2">

                        <label
                            for="star2"
                            title="2 sao">

                            ★

                        </label>


                        <input
                            type="radio"
                            name="soSao"
                            value="1"
                            id="star1">

                        <label
                            for="star1"
                            title="1 sao">

                            ★

                        </label>


                    </div>

                </div>


                <!-- ================= NHẬN XÉT ================= -->

                <div class="mb-4">

                    <label
                        for="noiDung"
                        class="form-label">

                        <i class="fa-solid fa-comment-dots text-primary"></i>

                        Nhận xét của bạn

                    </label>


                    <textarea
                        id="noiDung"
                        name="noiDung"
                        class="form-control"
                        rows="5"
                        maxlength="500"
                        placeholder="Hãy chia sẻ cảm nhận của bạn về bác sĩ..."
                        required></textarea>


                    <div class="form-text">

                        Tối đa 500 ký tự.

                    </div>

                </div>


                <!-- ================= NÚT GỬI ================= -->

                <button
                    type="submit"
                    class="btn btn-primary btn-submit">

                    <i class="fa-solid fa-paper-plane"></i>

                    Gửi đánh giá

                </button>


            </form>


            <!-- ================= QUAY LẠI ================= -->

            <div class="text-center mt-4">

                <a
                    href="${pageContext.request.contextPath}/index.jsp"
                    class="back-link">

                    <i class="fa-solid fa-arrow-left"></i>

                    Quay lại trang chủ

                </a>

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
package poly.com.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import poly.com.dao.YeuCauHuyLichDAO;
import poly.com.entity.YeuCauHuyLich;

@WebServlet("/quanlyyeucauhuylich")
public class QuanLyYeuCauHuyLichServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final YeuCauHuyLichDAO yeuCauDAO =
            new YeuCauHuyLichDAO();

    // =========================================================
    // GET
    // =========================================================

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // =====================================================
        // KIỂM TRA ADMIN
        // =====================================================

        HttpSession session = request.getSession(false);

        if (session == null
                || session.getAttribute("admin") == null) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/loginadmin");

            return;
        }

        // =====================================================
        // LẤY ACTION
        // =====================================================

        String action = request.getParameter("action");

        /*
         * Nếu có action:
         *
         * /quanlyyeucauhuylich?action=approve&id=1
         *
         * /quanlyyeucauhuylich?action=reject&id=1
         *
         * thì xử lý yêu cầu.
         */

        if (action != null && !action.isBlank()) {

            handleAction(
                    action,
                    request,
                    response);

            return;
        }

        // =====================================================
        // KHÔNG CÓ ACTION -> HIỂN THỊ DANH SÁCH
        // =====================================================

        loadPage(
                request,
                response);
    }

    // =========================================================
    // POST
    // =========================================================

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        doGet(
                request,
                response);
    }

    // =========================================================
    // XỬ LÝ ACTION
    // =========================================================

    private void handleAction(
            String action,
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        String idStr =
                request.getParameter("id");

        // =====================================================
        // KIỂM TRA ID
        // =====================================================

        if (idStr == null
                || idStr.isBlank()) {

            redirectError(
                    request,
                    response,
                    "Không tìm thấy mã yêu cầu hủy lịch");

            return;
        }

        int id;

        try {

            id = Integer.parseInt(idStr);

        } catch (NumberFormatException e) {

            redirectError(
                    request,
                    response,
                    "Mã yêu cầu không hợp lệ");

            return;
        }

        // =====================================================
        // KIỂM TRA ADMIN
        // =====================================================

        HttpSession session =
                request.getSession(false);

        if (session == null
                || session.getAttribute("admin") == null) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/loginadmin");

            return;
        }

        try {

            // =================================================
            // TÌM YÊU CẦU
            // =================================================

            YeuCauHuyLich yc =
                    yeuCauDAO.findById(id);

            if (yc == null) {

                redirectError(
                        request,
                        response,
                        "Không tìm thấy yêu cầu hủy lịch");

                return;
            }

            // =================================================
            // KIỂM TRA TRẠNG THÁI
            // =================================================

            if (!"Chờ duyệt".equals(
                    yc.getTrangThai())) {

                redirectError(
                        request,
                        response,
                        "Yêu cầu này đã được xử lý trước đó");

                return;
            }

            // =================================================
            // ADMIN ID
            // =================================================

            /*
             * Entity YeuCauHuyLich của bạn:
             *
             * private Integer nguoiXuLy;
             *
             * Hiện tại có thể để null.
             *
             * Sau này nếu session admin lưu ID tài khoản,
             * bạn có thể lấy ID ở đây.
             */

            Integer adminId = null;

            // =================================================
            // DUYỆT
            // =================================================

            if ("approve".equalsIgnoreCase(action)) {

                yeuCauDAO.approve(
                        id,
                        adminId);

                redirectSuccess(
                        request,
                        response,
                        "Đã duyệt yêu cầu hủy lịch thành công!");

                return;
            }

            // =================================================
            // TỪ CHỐI
            // =================================================

            if ("reject".equalsIgnoreCase(action)) {

                yeuCauDAO.reject(
                        id,
                        adminId);

                redirectSuccess(
                        request,
                        response,
                        "Đã từ chối yêu cầu hủy lịch!");

                return;
            }

            // =================================================
            // ACTION KHÔNG HỢP LỆ
            // =================================================

            redirectError(
                    request,
                    response,
                    "Thao tác không hợp lệ");

        } catch (Exception e) {

            e.printStackTrace();

            redirectError(
                    request,
                    response,
                    "Không thể xử lý yêu cầu: "
                            + e.getMessage());
        }
    }

    // =========================================================
    // LOAD PAGE
    // =========================================================

    private void loadPage(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            // =================================================
            // LẤY DANH SÁCH
            // =================================================

            List<YeuCauHuyLich> danhSach =
                    yeuCauDAO.findAll();

            // =================================================
            // GỬI DANH SÁCH SANG JSP
            // =================================================

            request.setAttribute(
                    "danhSach",
                    danhSach);

            // =================================================
            // ĐẾM CHỜ DUYỆT
            // =================================================

            long soLuongChoDuyet =
                    yeuCauDAO.countPending();

            request.setAttribute(
                    "soLuongChoDuyet",
                    soLuongChoDuyet);

            // =================================================
            // MESSAGE
            // =================================================

            String message =
                    request.getParameter("message");

            if (message != null
                    && !message.isBlank()) {

                request.setAttribute(
                        "message",
                        message);
            }

            // =================================================
            // ERROR
            // =================================================

            String error =
                    request.getParameter("error");

            if (error != null
                    && !error.isBlank()) {

                request.setAttribute(
                        "error",
                        error);
            }

            // =================================================
            // FORWARD JSP
            // =================================================

            request.getRequestDispatcher(
                    "/quanlyyeucauhuylich.jsp")
                    .forward(
                            request,
                            response);

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute(
                    "error",
                    "Không thể tải danh sách yêu cầu hủy lịch: "
                            + e.getMessage());

            request.getRequestDispatcher(
                    "/quanlyyeucauhuylich.jsp")
                    .forward(
                            request,
                            response);
        }
    }

    // =========================================================
    // REDIRECT SUCCESS
    // =========================================================

    private void redirectSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            String message)
            throws IOException {

        String encodedMessage =
                URLEncoder.encode(
                        message,
                        StandardCharsets.UTF_8);

        response.sendRedirect(
                request.getContextPath()
                        + "/quanlyyeucauhuylich"
                        + "?message="
                        + encodedMessage);
    }

    // =========================================================
    // REDIRECT ERROR
    // =========================================================

    private void redirectError(
            HttpServletRequest request,
            HttpServletResponse response,
            String error)
            throws IOException {

        String encodedError =
                URLEncoder.encode(
                        error,
                        StandardCharsets.UTF_8);

        response.sendRedirect(
                request.getContextPath()
                        + "/quanlyyeucauhuylich"
                        + "?error="
                        + encodedError);
    }
}
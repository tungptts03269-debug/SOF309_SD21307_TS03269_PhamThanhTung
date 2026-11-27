package poly.com.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import poly.com.dao.DanhGiaDAO;
import poly.com.entity.DanhGia;

@WebServlet("/quanlydanhgia")
public class QuanLyDanhGiaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private DanhGiaDAO danhGiaDAO = new DanhGiaDAO();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // =====================================================
        // KIỂM TRA ADMIN
        // =====================================================

        HttpSession session = request.getSession(false);

        if (session == null
                || session.getAttribute("admin") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/loginadmin");

            return;
        }

        try {

            // =====================================================
            // LẤY TẤT CẢ ĐÁNH GIÁ
            //
            // QUAN TRỌNG:
            // Dùng findAllWithDetails()
            // thay vì findAll()
            // =====================================================

            List<DanhGia> danhGias =
                    danhGiaDAO.findAllWithDetails();

            request.setAttribute(
                    "danhGias",
                    danhGias);

            // =====================================================
            // THÔNG BÁO
            // =====================================================

            String message =
                    request.getParameter("message");

            if (message != null
                    && !message.isBlank()) {

                request.setAttribute(
                        "message",
                        message);
            }

            // =====================================================
            // CHUYỂN SANG JSP
            // =====================================================

            request.getRequestDispatcher(
                    "/quanlydanhgia.jsp")
                    .forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute(
                    "error",
                    "Không thể tải danh sách đánh giá: "
                            + e.getMessage());

            request.getRequestDispatcher(
                    "/quanlydanhgia.jsp")
                    .forward(request, response);
        }
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}
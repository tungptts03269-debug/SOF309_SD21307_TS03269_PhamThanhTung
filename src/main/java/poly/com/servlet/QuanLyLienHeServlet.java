package poly.com.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import poly.com.dao.LienHeDAO;
import poly.com.entity.LienHe;

@WebServlet("/quanlylienhe")
public class QuanLyLienHeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private LienHeDAO lienHeDAO = new LienHeDAO();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // ==========================================
        // KIỂM TRA ADMIN
        // ==========================================

        HttpSession session = request.getSession(false);

        if (session == null
                || session.getAttribute("admin") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/loginadmin");

            return;
        }

        try {

            // ==========================================
            // LẤY DANH SÁCH LIÊN HỆ
            // ==========================================

            List<LienHe> lienHes = lienHeDAO.findAll();

            request.setAttribute("lienHes", lienHes);

            // ==========================================
            // THÔNG BÁO
            // ==========================================

            String message = request.getParameter("message");

            if (message != null && !message.isBlank()) {

                request.setAttribute("message", message);

            }

            // ==========================================
            // FORWARD JSP
            // ==========================================

            request.getRequestDispatcher(
                    "/quanlylienhe.jsp")
                    .forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute(
                    "error",
                    "Không thể tải danh sách liên hệ: "
                            + e.getMessage());

            request.getRequestDispatcher(
                    "/quanlylienhe.jsp")
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
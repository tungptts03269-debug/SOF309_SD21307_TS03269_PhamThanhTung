package poly.com.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import poly.com.dao.NhatKyHoatDongDAO;
import poly.com.entity.NhatKyHoatDong;
import poly.com.entity.TaiKhoan;

@WebServlet("/nhatkyhoatdong")
public class NhatKyHoatDongServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private NhatKyHoatDongDAO dao = new NhatKyHoatDongDAO();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);

        // Chưa đăng nhập
        if (session == null || session.getAttribute("user") == null) {

            response.sendRedirect(request.getContextPath()
                    + "/index.jsp?loginRequired=true");

            return;
        }

        TaiKhoan user = (TaiKhoan) session.getAttribute("user");

        String keyword = request.getParameter("keyword");

        List<NhatKyHoatDong> list;

        // ================= ADMIN =================
        if ("Admin".equalsIgnoreCase(user.getVaiTro())) {

            if (keyword != null && !keyword.trim().isEmpty()) {

                list = dao.search(keyword.trim());

            } else {

                list = dao.findLatest(1000);

            }

        }

        // ================= NGƯỜI DÙNG =================
        else {

            list = dao.findByTaiKhoan(user.getMaTaiKhoan());

        }

        request.setAttribute("list", list);

        request.getRequestDispatcher("/nhatkyhoatdong.jsp")
                .forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);

    }

}
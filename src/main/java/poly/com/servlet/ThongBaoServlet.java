package poly.com.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import poly.com.dao.ThongBaoDAO;
import poly.com.entity.TaiKhoan;
import poly.com.entity.ThongBao;

@WebServlet("/thongbao")
public class ThongBaoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ThongBaoDAO thongBaoDAO = new ThongBaoDAO();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {

            response.sendRedirect(request.getContextPath() + "/thongbao.jsp");
            return;
        }

        TaiKhoan user =
                (TaiKhoan) session.getAttribute("user");

        List<ThongBao> list =
                thongBaoDAO.findByTaiKhoan(user.getMaTaiKhoan());

        request.setAttribute("listThongBao", list);

        request.getRequestDispatcher("/thongbao.jsp")
                .forward(request, response);

    }

}
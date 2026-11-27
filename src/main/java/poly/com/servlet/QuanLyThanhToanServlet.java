package poly.com.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import poly.com.dao.ThanhToanDAO;
import poly.com.entity.TaiKhoan;
import poly.com.entity.ThanhToan;

@WebServlet("/quanlythanhtoan")
public class QuanLyThanhToanServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ThanhToanDAO thanhToanDAO = new ThanhToanDAO();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Kiểm tra đăng nhập Admin
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect(request.getContextPath() + "/loginadmin");
            return;
        }

        TaiKhoan admin = (TaiKhoan) session.getAttribute("admin");

        if (!"Admin".equalsIgnoreCase(admin.getVaiTro())) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        String keyword = request.getParameter("keyword");
        String trangThai = request.getParameter("trangThai");

        request.setAttribute("keyword", keyword);
        request.setAttribute("trangThai", trangThai);

        try {

            List<ThanhToan> listHoaDon;

            if (keyword != null && !keyword.isBlank()) {

                listHoaDon = thanhToanDAO.search(keyword);

            } else if (trangThai != null && !trangThai.isBlank()) {

                listHoaDon = thanhToanDAO.findByTrangThai(trangThai);

            } else {

                listHoaDon = thanhToanDAO.findAll();

            }

            request.setAttribute("listHoaDon", listHoaDon);

            // Thống kê
            request.setAttribute("tongDoanhThu",
                    thanhToanDAO.tongDoanhThu());

            request.setAttribute("doanhThuHomNay",
                    thanhToanDAO.doanhThuHomNay());

            request.setAttribute("doanhThuThang",
                    thanhToanDAO.doanhThuThang());

            request.setAttribute("doanhThuNam",
                    thanhToanDAO.doanhThuNam());

            request.setAttribute("daThanhToan",
                    thanhToanDAO.countDaThanhToan());

            request.setAttribute("chuaThanhToan",
                    thanhToanDAO.countChuaThanhToan());

            request.getRequestDispatcher("/quanlythanhtoan.jsp")
                    .forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute("error",
                    "Không thể tải dữ liệu thanh toán.");

            request.getRequestDispatcher("/quanlythanhtoan.jsp")
                    .forward(request, response);
        }
    }
}
package poly.com.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import poly.com.dao.DashboardDAO;
import poly.com.entity.TaiKhoan;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private DashboardDAO dashboardDAO = new DashboardDAO();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Chưa đăng nhập
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        TaiKhoan user = (TaiKhoan) session.getAttribute("user");

        // Chỉ Admin mới được vào Dashboard
        if (!"Admin".equalsIgnoreCase(user.getVaiTro())) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        // ================= THỐNG KÊ =================

        request.setAttribute("tongTaiKhoan",
                dashboardDAO.countTaiKhoan());

        request.setAttribute("tongBacSi",
                dashboardDAO.countBacSi());

        request.setAttribute("tongBenhNhan",
                dashboardDAO.countBenhNhan());

        request.setAttribute("tongLichKham",
                dashboardDAO.countLichKham());

        request.setAttribute("lichHomNay",
                dashboardDAO.lichHomNay());

        request.setAttribute("choXacNhan",
                dashboardDAO.choXacNhan());

        request.setAttribute("daXacNhan",
                dashboardDAO.daXacNhan());

        request.setAttribute("daKham",
                dashboardDAO.daKham());

        request.setAttribute("lienHeChuaTraLoi",
                dashboardDAO.lienHeChuaTraLoi());

        request.setAttribute("yeuCauHuy",
                dashboardDAO.yeuCauHuy());

        request.setAttribute("thongBaoChuaDoc",
                dashboardDAO.thongBaoChuaDoc());

        // ================= DOANH THU =================

        request.setAttribute("doanhThuHomNay",
                dashboardDAO.doanhThuHomNay());

        request.setAttribute("doanhThuThang",
                dashboardDAO.doanhThuThang());

        request.setAttribute("doanhThuNam",
                dashboardDAO.doanhThuNam());

        request.setAttribute("tongDoanhThu",
                dashboardDAO.tongDoanhThu());
        
     // ================= BIỂU ĐỒ DOANH THU =================

        List<Object[]> list = dashboardDAO.doanhThuTheoThang();

        System.out.println("===== DOANH THU THEO THANG =====");

        for (Object[] o : list) {
            System.out.println(o[0] + " - " + o[1]);
        }

        request.setAttribute("doanhThuTheoThang", list);

        // ================= MỞ DASHBOARD =================

        request.getRequestDispatcher("/dashboard.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}
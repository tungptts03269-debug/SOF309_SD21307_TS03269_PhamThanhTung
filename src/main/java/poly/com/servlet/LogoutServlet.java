package poly.com.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import poly.com.dao.LichSuDangNhapDAO;
import poly.com.dao.NhatKyHoatDongDAO;
import poly.com.entity.NhatKyHoatDong;
import poly.com.entity.TaiKhoan;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private LichSuDangNhapDAO lichSuDAO = new LichSuDangNhapDAO();

    private NhatKyHoatDongDAO nhatKyDAO = new NhatKyHoatDongDAO();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session != null) {

            TaiKhoan user = (TaiKhoan) session.getAttribute("user");

            // ================= CẬP NHẬT LỊCH SỬ ĐĂNG NHẬP =================

            Object obj = session.getAttribute("loginHistoryId");

            if (obj != null) {

                int maLichSu = (Integer) obj;

                lichSuDAO.updateLogout(maLichSu);

            }

            // ================= GHI NHẬT KÝ HOẠT ĐỘNG =================

            if (user != null) {

                NhatKyHoatDong nk = new NhatKyHoatDong();

                nk.setTaiKhoan(user);

                nk.setHanhDong("Đăng xuất");

                nk.setBangTacDong("TaiKhoan");

                nk.setMaDoiTuong(user.getMaTaiKhoan());

                nk.setThoiGian(new Timestamp(System.currentTimeMillis()));

                nk.setDiaChiIP(request.getRemoteAddr());

                nk.setGhiChu("Đăng xuất khỏi hệ thống");

                nhatKyDAO.insert(nk);

            }

            // ================= XÓA SESSION =================

            session.invalidate();

        }

        // ================= QUAY VỀ TRANG CHỦ =================

        response.sendRedirect(request.getContextPath() + "/");

    }

}
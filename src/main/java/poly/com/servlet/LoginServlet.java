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
import poly.com.dao.TaiKhoanDAO;
import poly.com.entity.LichSuDangNhap;
import poly.com.entity.NhatKyHoatDong;
import poly.com.entity.TaiKhoan;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
    private LichSuDangNhapDAO lichSuDAO = new LichSuDangNhapDAO();
    private NhatKyHoatDongDAO nhatKyDAO = new NhatKyHoatDongDAO();

    public LoginServlet() {
    }

    // Constructor package-private dành cho unit test (dependency injection).
    LoginServlet(TaiKhoanDAO taiKhoanDAO, LichSuDangNhapDAO lichSuDAO,
                 NhatKyHoatDongDAO nhatKyDAO) {
        this.taiKhoanDAO = taiKhoanDAO;
        this.lichSuDAO = lichSuDAO;
        this.nhatKyDAO = nhatKyDAO;
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // ================= KIỂM TRA =================

        if (username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {

            request.setAttribute("error",
                    "Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu!");

            request.getRequestDispatcher("/index.jsp")
                    .forward(request, response);
            return;
        }

        // ================= ĐĂNG NHẬP =================

        TaiKhoan user = taiKhoanDAO.login(
                username.trim(),
                password.trim());

        if (user == null) {

            request.setAttribute("error",
                    "Tên đăng nhập hoặc mật khẩu không đúng!");

            request.getRequestDispatcher("/index.jsp")
                    .forward(request, response);
            return;
        }

        // ================= SESSION =================

        HttpSession session = request.getSession();

        session.setMaxInactiveInterval(30 * 60);

        session.setAttribute("user", user);
        session.setAttribute("userId", user.getMaTaiKhoan());
        session.setAttribute("username", user.getTenDangNhap());
        session.setAttribute("fullName", user.getHoTen());
        session.setAttribute("role", user.getVaiTro());

        // ================= LỊCH SỬ ĐĂNG NHẬP =================

        LichSuDangNhap ls = new LichSuDangNhap();

        ls.setTaiKhoan(user);

        ls.setThoiGianDangNhap(
                new Timestamp(System.currentTimeMillis()));

        ls.setDiaChiIP(request.getRemoteAddr());

        ls.setThietBi(request.getHeader("User-Agent"));

        ls.setTrangThai("Đang đăng nhập");

        lichSuDAO.insert(ls);

        session.setAttribute(
                "loginHistoryId",
                ls.getMaLichSu());

        // ================= NHẬT KÝ =================

        NhatKyHoatDong nk = new NhatKyHoatDong();

        nk.setTaiKhoan(user);

        nk.setHanhDong("Đăng nhập");

        nk.setBangTacDong("TaiKhoan");

        nk.setMaDoiTuong(user.getMaTaiKhoan());

        nk.setThoiGian(
                new Timestamp(System.currentTimeMillis()));

        nk.setDiaChiIP(request.getRemoteAddr());

        nk.setGhiChu("Đăng nhập thành công");

        nhatKyDAO.insert(nk);

        // ================= PHÂN QUYỀN =================

        if ("Admin".equalsIgnoreCase(user.getVaiTro())) {

            session.setAttribute("admin", user);

            response.sendRedirect(
                    request.getContextPath()
                    + "/indexadmin.jsp");

        }
        else if ("Bác sĩ".equalsIgnoreCase(user.getVaiTro())) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/doctor");

        }
        else {

            response.sendRedirect(
                    request.getContextPath()
                    + "/index.jsp");

        }

    }

}
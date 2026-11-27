package poly.com.servlet;

import java.io.IOException;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import poly.com.dao.BenhNhanDAO;
import poly.com.dao.TaiKhoanDAO;
import poly.com.dao.ThongBaoAdminDAO;
import poly.com.dao.ThongBaoDAO;
import poly.com.entity.BenhNhan;
import poly.com.entity.TaiKhoan;
import poly.com.entity.ThongBao;
import poly.com.entity.ThongBaoAdmin;
import poly.com.validation.RegisterValidator;
import poly.com.validation.DuplicateAccountException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
    private BenhNhanDAO benhNhanDAO = new BenhNhanDAO();
    private ThongBaoDAO thongBaoDAO = new ThongBaoDAO();
    private ThongBaoAdminDAO thongBaoAdminDAO = new ThongBaoAdminDAO();

    public RegisterServlet() {
    }

    // Constructor package-private dành cho unit test (dependency injection).
    RegisterServlet(TaiKhoanDAO taiKhoanDAO, BenhNhanDAO benhNhanDAO,
                    ThongBaoDAO thongBaoDAO, ThongBaoAdminDAO thongBaoAdminDAO) {
        this.taiKhoanDAO = taiKhoanDAO;
        this.benhNhanDAO = benhNhanDAO;
        this.thongBaoDAO = thongBaoDAO;
        this.thongBaoAdminDAO = thongBaoAdminDAO;
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("tenDangNhap");
        String password = request.getParameter("matKhau");
        String confirm = request.getParameter("confirmPassword");
        String hoTen = request.getParameter("hoTen");
        String email = request.getParameter("email");
        String sdt = request.getParameter("soDienThoai");

        // ================= KIỂM TRA DỮ LIỆU ĐẦU VÀO =================

        try {
            RegisterValidator.validate(username, password, confirm, hoTen, email, sdt);
        } catch (IllegalArgumentException e) {
            request.setAttribute("registerError", e.getMessage());
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        // ================= KIỂM TRA TRÙNG =================

        try {
            RegisterValidator.validateUnique(
                    taiKhoanDAO.existsUsername(username),
                    taiKhoanDAO.existsEmail(email),
                    taiKhoanDAO.existsPhone(sdt));
        } catch (DuplicateAccountException e) {
            request.setAttribute("registerError", e.getMessage());
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        try {

            // ================= TẠO TÀI KHOẢN =================

            TaiKhoan user = new TaiKhoan();

            user.setTenDangNhap(username);
            user.setMatKhau(password);
            user.setHoTen(hoTen);
            user.setEmail(email);
            user.setSoDienThoai(sdt);
            user.setVaiTro("Bệnh nhân");

            taiKhoanDAO.insert(user);

            // Kiểm tra đã sinh ID chưa
            if (user.getMaTaiKhoan() <= 0) {
                throw new Exception("Không lấy được mã tài khoản sau khi đăng ký.");
            }

            // ================= TẠO BỆNH NHÂN =================

            BenhNhan benhNhan = new BenhNhan();

            benhNhan.setTaiKhoan(user);
            benhNhan.setHoTen(user.getHoTen());
            benhNhan.setSoDienThoai(user.getSoDienThoai());
            benhNhan.setNgaySinh(null);
            benhNhan.setGioiTinh("");
            benhNhan.setDiaChi("");

            benhNhanDAO.insert(benhNhan);

            // ================= THÔNG BÁO USER =================

            ThongBao tb = new ThongBao();

            tb.setTaiKhoan(user);
            tb.setTieuDe("Đăng ký thành công");
            tb.setNoiDung("Xin chào " + user.getHoTen()
                    + ". Tài khoản của bạn đã được tạo thành công.");
            tb.setDaDoc(false);
            tb.setNgayGui(new Date());

            thongBaoDAO.insert(tb);

            // ================= THÔNG BÁO ADMIN =================

            ThongBaoAdmin admin = new ThongBaoAdmin();

            admin.setMaLichKham(null);
            admin.setNoiDung("Người dùng mới đăng ký: "
                    + user.getHoTen()
                    + " (" + user.getTenDangNhap() + ")");
            admin.setDaDoc(false);
            admin.setNgayGui(new Date());

            thongBaoAdminDAO.insert(admin);

            // ================= ĐĂNG NHẬP =================

            HttpSession session = request.getSession();

            session.setAttribute("user", user);
            session.setAttribute("message", "Đăng ký thành công.");

            response.sendRedirect(request.getContextPath() + "/index.jsp");

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute("registerError",
                    "Đăng ký thất bại: " + e.getMessage());

            request.getRequestDispatcher("/index.jsp")
                    .forward(request, response);
        }
    }
}
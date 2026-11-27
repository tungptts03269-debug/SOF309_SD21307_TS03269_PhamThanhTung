package poly.com.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import poly.com.dao.BacSiDAO;
import poly.com.dao.BenhNhanDAO;
import poly.com.dao.DichVuDAO;
import poly.com.dao.LichKhamDAO;
import poly.com.dao.NhatKyHoatDongDAO;
import poly.com.dao.ThongBaoAdminDAO;
import poly.com.dao.ThongBaoDAO;

import poly.com.entity.BacSi;
import poly.com.entity.BenhNhan;
import poly.com.entity.DichVu;
import poly.com.entity.LichKham;
import poly.com.entity.NhatKyHoatDong;
import poly.com.entity.TaiKhoan;
import poly.com.entity.ThongBao;
import poly.com.entity.ThongBaoAdmin;

@WebServlet("/datlich")
public class DatLichServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private BacSiDAO bacSiDAO = new BacSiDAO();
    private DichVuDAO dichVuDAO = new DichVuDAO();
    private BenhNhanDAO benhNhanDAO = new BenhNhanDAO();
    private LichKhamDAO lichKhamDAO = new LichKhamDAO();

    private ThongBaoDAO thongBaoDAO = new ThongBaoDAO();
    private ThongBaoAdminDAO thongBaoAdminDAO = new ThongBaoAdminDAO();

    private NhatKyHoatDongDAO nhatKyDAO = new NhatKyHoatDongDAO();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {

            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        request.setAttribute("bacSis", bacSiDAO.findAll());
        request.setAttribute("dichVus", dichVuDAO.findAll());

        request.getRequestDispatcher("/datlich.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {

            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        try {

            TaiKhoan user = (TaiKhoan) session.getAttribute("user");

            BenhNhan benhNhan =
                    benhNhanDAO.findByTaiKhoan(user.getMaTaiKhoan());

            if (benhNhan == null) {

                session.setAttribute("error",
                        "Không tìm thấy thông tin bệnh nhân.");

                response.sendRedirect(request.getContextPath() + "/datlich");
                return;
            }

            int maBacSi = Integer.parseInt(request.getParameter("maBacSi"));
            int maDichVu = Integer.parseInt(request.getParameter("maDichVu"));

            BacSi bacSi = bacSiDAO.findById(maBacSi);
            DichVu dichVu = dichVuDAO.findById(maDichVu);

            Date ngayKham =
                    Date.valueOf(request.getParameter("ngayKham"));

            Time gioKham =
                    Time.valueOf(request.getParameter("gioKham") + ":00");

          

            //=========================
            // LƯU LỊCH KHÁM
            //=========================

            LichKham lich = new LichKham();

            lich.setBenhNhan(benhNhan);
            lich.setBacSi(bacSi);
            lich.setDichVu(dichVu);

            lich.setNgayDat(
                    new Timestamp(System.currentTimeMillis()));

            lich.setNgayKham(ngayKham);
            lich.setGioKham(gioKham);

            lich.setLyDoKham(request.getParameter("lyDoKham"));
            lich.setGhiChu(request.getParameter("ghiChu"));

            // Muốn hiện luôn thì để Chờ xác nhận hoặc Đã xác nhận
            lich.setTrangThai("Chờ xác nhận");

            lichKhamDAO.insert(lich);

            //=========================
            // THÔNG BÁO BỆNH NHÂN
            //=========================

            ThongBao tb = new ThongBao();

            tb.setTaiKhoan(user);

            tb.setTieuDe("Đặt lịch thành công");

            tb.setNoiDung(
                    "Bạn đã đặt lịch khám với bác sĩ "
                            + bacSi.getHoTen());

            tb.setDaDoc(false);

            tb.setNgayGui(new java.util.Date());

            thongBaoDAO.insert(tb);

            //=========================
            // THÔNG BÁO ADMIN
            //=========================

            ThongBaoAdmin admin = new ThongBaoAdmin();

            admin.setMaLichKham(lich.getMaLichKham());

            admin.setNoiDung(
                    "Bệnh nhân "
                            + benhNhan.getHoTen()
                            + " vừa đặt lịch khám.");

            admin.setDaDoc(false);

            admin.setNgayGui(new java.util.Date());

            thongBaoAdminDAO.insert(admin);

            //=========================
            // NHẬT KÝ
            //=========================

            NhatKyHoatDong nk = new NhatKyHoatDong();

            nk.setTaiKhoan(user);

            nk.setHanhDong("Đặt lịch");

            nk.setBangTacDong("LichKham");

            nk.setMaDoiTuong(lich.getMaLichKham());

            nk.setThoiGian(
                    new Timestamp(System.currentTimeMillis()));

            nk.setDiaChiIP(request.getRemoteAddr());

            nk.setGhiChu("Đặt lịch khám");

            nhatKyDAO.insert(nk);

            //=========================
            // CHUYỂN SANG LỊCH KHÁM
            //=========================

            session.setAttribute("message",
                    "Đặt lịch thành công.");

            response.sendRedirect(request.getContextPath() + "/lichkham");

        } catch (Exception e) {

            e.printStackTrace();

            System.out.println("==============================");
            System.out.println(e.getMessage());
            System.out.println("==============================");

            session.setAttribute("error", e.getMessage());

            response.sendRedirect(request.getContextPath()+"/datlich");
        }
    }
}
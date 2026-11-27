package poly.com.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import poly.com.dao.LichKhamDAO;
import poly.com.dao.NhatKyHoatDongDAO;
import poly.com.dao.ThongBaoDAO;
import poly.com.dao.YeuCauHuyLichDAO;

import poly.com.entity.BenhNhan;
import poly.com.entity.LichKham;
import poly.com.entity.NhatKyHoatDong;
import poly.com.entity.TaiKhoan;
import poly.com.entity.ThongBao;
import poly.com.entity.YeuCauHuyLich;

@WebServlet("/yeucauhuylich")
public class YeuCauHuyLichServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private YeuCauHuyLichDAO dao =
            new YeuCauHuyLichDAO();

    private LichKhamDAO lichDAO =
            new LichKhamDAO();

    private ThongBaoDAO thongBaoDAO =
            new ThongBaoDAO();

    private NhatKyHoatDongDAO nhatKyDAO =
            new NhatKyHoatDongDAO();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {

            action = "list";

        }

        switch (action) {

        case "approve":

            approve(request, response);

            break;

        case "reject":

            reject(request, response);

            break;

        default:

            list(request, response);

            break;

        }

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        create(request, response);

    }

    /*=========================================
     * Danh sách yêu cầu
     =========================================*/

    private void list(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<YeuCauHuyLich> list =
                dao.findAll();

        request.setAttribute("listYeuCau", list);

        request.getRequestDispatcher("/yeucauhuylich.jsp")
                .forward(request, response);

    }

    /*=========================================
     * Bệnh nhân gửi yêu cầu hủy lịch
     =========================================*/

    private void create(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        HttpSession session =
                request.getSession(false);

        if (session == null
                || session.getAttribute("user") == null) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/login");

            return;

        }

        TaiKhoan user =
                (TaiKhoan) session.getAttribute("user");

        Integer maLichKham =
                Integer.parseInt(
                        request.getParameter("maLichKham"));

        String lyDo =
                request.getParameter("lyDo");

        LichKham lich =
                lichDAO.findByMaLichKham(maLichKham);

        if (lich == null) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/hoadon");

            return;

        }

        if ("Đã hủy".equals(
                lich.getTrangThai())) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/hoadon");

            return;

        }

        // Kiểm tra đã gửi yêu cầu chưa
        if (dao.existsByLichKham(maLichKham)) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/hoadon");

            return;

        }

        YeuCauHuyLich yc =
                new YeuCauHuyLich();

        yc.setLichKham(lich);

        yc.setLyDo(lyDo);

        yc.setTrangThai("Chờ duyệt");

        yc.setNgayGui(new Date());

        dao.create(yc);

        // ==========================
        // Nhật ký hoạt động
        // ==========================

        NhatKyHoatDong nk =
                new NhatKyHoatDong();

        nk.setTaiKhoan(user);

        nk.setHanhDong(
                "Yêu cầu hủy lịch");

        nk.setBangTacDong(
                "YeuCauHuyLich");

        nk.setMaDoiTuong(
                maLichKham);

        nk.setThoiGian(
                new Timestamp(
                        System.currentTimeMillis()));

        nk.setDiaChiIP(
                request.getRemoteAddr());

        nk.setGhiChu(
                "Bệnh nhân gửi yêu cầu hủy lịch khám.");

        nhatKyDAO.insert(nk);

        response.sendRedirect(
                request.getContextPath()
                        + "/hoadon");

    }
    /*=========================================
     * Admin duyệt yêu cầu hủy lịch
     =========================================*/

    private void approve(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        HttpSession session =
                request.getSession(false);

        if (session == null
                || session.getAttribute("user") == null) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/login");

            return;

        }

        TaiKhoan admin =
                (TaiKhoan) session.getAttribute("user");

        Integer id =
                Integer.parseInt(
                        request.getParameter("id"));

        YeuCauHuyLich yc =
                dao.findById(id);

        if (yc == null) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/yeucauhuylich");

            return;

        }

        // Nếu đã xử lý rồi thì không xử lý nữa
        if (!"Chờ duyệt".equals(yc.getTrangThai())) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/yeucauhuylich");

            return;

        }

        //==============================
        // Cập nhật trạng thái yêu cầu
        //==============================

        dao.approve(
                id,
                admin.getMaTaiKhoan());

        //==============================
        // Hủy lịch khám
        //==============================

        LichKham lich =
                yc.getLichKham();

        lich.setTrangThai("Đã hủy");

        lichDAO.update(lich);

        //==============================
        // Thông báo cho bệnh nhân
        //==============================

        BenhNhan benhNhan =
                lich.getBenhNhan();

        ThongBao tb =
                new ThongBao();

        tb.setTaiKhoan(
                benhNhan.getTaiKhoan());

        tb.setTieuDe(
                "Yêu cầu hủy lịch");

        tb.setNoiDung(
                "Yêu cầu hủy lịch khám ngày "
                + lich.getNgayKham()
                + " lúc "
                + lich.getGioKham()
                + " đã được phê duyệt.");

        tb.setDaDoc(false);

        tb.setNgayGui(
                new Date());

        thongBaoDAO.insert(tb);

        //==============================
        // Nhật ký hoạt động
        //==============================

        NhatKyHoatDong nk =
                new NhatKyHoatDong();

        nk.setTaiKhoan(admin);

        nk.setHanhDong(
                "Duyệt yêu cầu hủy lịch");

        nk.setBangTacDong(
                "YeuCauHuyLich");

        nk.setMaDoiTuong(
                yc.getMaYeuCau());

        nk.setThoiGian(
                new Timestamp(
                        System.currentTimeMillis()));

        nk.setDiaChiIP(
                request.getRemoteAddr());

        nk.setGhiChu(
                "Admin đã duyệt yêu cầu hủy lịch khám mã "
                + lich.getMaLichKham());

        nhatKyDAO.insert(nk);

        response.sendRedirect(
                request.getContextPath()
                        + "/yeucauhuylich");

    }
    /*=========================================
     * Admin từ chối yêu cầu hủy lịch
     =========================================*/

    private void reject(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        HttpSession session =
                request.getSession(false);

        if (session == null
                || session.getAttribute("user") == null) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/login");

            return;

        }

        TaiKhoan admin =
                (TaiKhoan) session.getAttribute("user");

        Integer id =
                Integer.parseInt(
                        request.getParameter("id"));

        YeuCauHuyLich yc =
                dao.findById(id);

        if (yc == null) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/yeucauhuylich");

            return;

        }

        // Nếu yêu cầu đã xử lý thì không xử lý lại
        if (!"Chờ duyệt".equals(yc.getTrangThai())) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/yeucauhuylich");

            return;

        }

        //==============================
        // Cập nhật trạng thái yêu cầu
        //==============================

        dao.reject(
                id,
                admin.getMaTaiKhoan());

        //==============================
        // Gửi thông báo cho bệnh nhân
        //==============================

        BenhNhan benhNhan =
                yc.getLichKham()
                  .getBenhNhan();

        ThongBao tb =
                new ThongBao();

        tb.setTaiKhoan(
                benhNhan.getTaiKhoan());

        tb.setTieuDe(
                "Yêu cầu hủy lịch");

        tb.setNoiDung(
                "Yêu cầu hủy lịch khám ngày "
                + yc.getLichKham().getNgayKham()
                + " lúc "
                + yc.getLichKham().getGioKham()
                + " đã bị từ chối.");

        tb.setDaDoc(false);

        tb.setNgayGui(
                new Date());

        thongBaoDAO.insert(tb);

        //==============================
        // Nhật ký hoạt động
        //==============================

        NhatKyHoatDong nk =
                new NhatKyHoatDong();

        nk.setTaiKhoan(admin);

        nk.setHanhDong(
                "Từ chối yêu cầu hủy lịch");

        nk.setBangTacDong(
                "YeuCauHuyLich");

        nk.setMaDoiTuong(
                yc.getMaYeuCau());

        nk.setThoiGian(
                new Timestamp(
                        System.currentTimeMillis()));

        nk.setDiaChiIP(
                request.getRemoteAddr());

        nk.setGhiChu(
                "Admin từ chối yêu cầu hủy lịch khám mã "
                + yc.getLichKham().getMaLichKham());

        nhatKyDAO.insert(nk);

        response.sendRedirect(
                request.getContextPath()
                        + "/yeucauhuylich");

    }

}
package poly.com.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

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
import java.math.BigDecimal;

import poly.com.dao.ThanhToanDAO;
import poly.com.entity.ThanhToan;

import poly.com.entity.BacSi;
import poly.com.entity.BenhNhan;
import poly.com.entity.DichVu;
import poly.com.entity.LichKham;
import poly.com.entity.TaiKhoan;
import poly.com.entity.NhatKyHoatDong;

@WebServlet("/lichkham")
public class LichKhamServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private LichKhamDAO lichKhamDAO = new LichKhamDAO();
    private BenhNhanDAO benhNhanDAO = new BenhNhanDAO();
    private BacSiDAO bacSiDAO = new BacSiDAO();
    private DichVuDAO dichVuDAO = new DichVuDAO();
    private NhatKyHoatDongDAO nhatKyDAO = new NhatKyHoatDongDAO();
    private ThanhToanDAO thanhToanDAO = new ThanhToanDAO();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null
                || session.getAttribute("user") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/index.jsp");
            return;
        }

        TaiKhoan user =
                (TaiKhoan) session.getAttribute("user");

        String keyword = request.getParameter("keyword");

        List<LichKham> list;

        //================ ADMIN ================

        if ("Admin".equalsIgnoreCase(user.getVaiTro())) {

            if (keyword != null && !keyword.isBlank()) {

                list = lichKhamDAO.search(keyword);

            } else {

                list = lichKhamDAO.findAllFetch();

            }

            request.setAttribute("benhNhans",
                    benhNhanDAO.findAll());

            request.setAttribute("bacSis",
                    bacSiDAO.findAll());

            request.setAttribute("dichVus",
                    dichVuDAO.findAll());

        }

        //================ BÁC SĨ ================

        else if ("Bác sĩ".equalsIgnoreCase(user.getVaiTro())) {

            BacSi bacSi =
                    bacSiDAO.findByTaiKhoan(
                            user.getMaTaiKhoan());

            if (bacSi == null) {

                list = List.of();

            } else {

                list = lichKhamDAO.findByBacSi(
                        bacSi.getMaBacSi());

            }

        }

        //================ BỆNH NHÂN ================

        else {

            BenhNhan benhNhan =
                    benhNhanDAO.findByTaiKhoan(
                            user.getMaTaiKhoan());

            if (benhNhan == null) {

                list = List.of();

            } else {

                list = lichKhamDAO.findByBenhNhan(
                        benhNhan.getMaBenhNhan());

            }

        }

        request.setAttribute("list", list);

        request.getRequestDispatcher("/lichkham.jsp")
                .forward(request, response);
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

        TaiKhoan user = (TaiKhoan) session.getAttribute("user");

        try {

            String action = request.getParameter("action");
            
         // ================= DUYỆT HỦY =================
            if ("acceptCancel".equals(action)) {

                int id = Integer.parseInt(request.getParameter("maLichKham"));

                LichKham lich = lichKhamDAO.findByMaLichKham(id);

                lich.setTrangThai("Đã hủy");

                lichKhamDAO.update(lich);

                session.setAttribute("message",
                        "Đã hủy lịch khám.");

                response.sendRedirect(request.getContextPath()+"/lichkham");
                return;
            }

            // ================= TỪ CHỐI HỦY =================
            if ("rejectCancel".equals(action)) {

                int id = Integer.parseInt(request.getParameter("maLichKham"));

                LichKham lich = lichKhamDAO.findByMaLichKham(id);

                lich.setTrangThai("Đã xác nhận");

                lichKhamDAO.update(lich);

                session.setAttribute("message",
                        "Đã từ chối yêu cầu hủy.");

                response.sendRedirect(request.getContextPath()+"/lichkham");
                return;
            }

            //================ XÓA =================
            if ("delete".equalsIgnoreCase(action)) {

                int maLichKham =
                        Integer.parseInt(request.getParameter("maLichKham"));

                lichKhamDAO.delete(maLichKham);

                NhatKyHoatDong nk = new NhatKyHoatDong();

                nk.setTaiKhoan(user);
                nk.setHanhDong("Xóa lịch khám");
                nk.setBangTacDong("LichKham");
                nk.setMaDoiTuong(maLichKham);
                nk.setThoiGian(new Timestamp(System.currentTimeMillis()));
                nk.setDiaChiIP(request.getRemoteAddr());
                nk.setGhiChu("Admin xóa lịch khám");

                nhatKyDAO.insert(nk);

                session.setAttribute("message",
                        "Xóa lịch khám thành công.");

                response.sendRedirect(request.getContextPath() + "/lichkham");
                return;
            }
          //================ XÁC NHẬN =================
            if ("confirm".equalsIgnoreCase(action)) {

                int maLichKham =
                        Integer.parseInt(request.getParameter("maLichKham"));

                LichKham lich =
                        lichKhamDAO.findByMaLichKham(maLichKham);

                if (lich != null) {

                    // cập nhật trạng thái lịch
                    lich.setTrangThai("Đã xác nhận");
                    lichKhamDAO.update(lich);

                    // kiểm tra hóa đơn
                    ThanhToan thanhToan =
                            thanhToanDAO.findByLichKham(maLichKham);

                    if (thanhToan == null) {

                        thanhToan = new ThanhToan();

                        thanhToan.setLichKham(lich);

                        if (lich.getDichVu() != null
                                && lich.getDichVu().getGiaDichVu() != null) {

                            thanhToan.setSoTien(
                                    lich.getDichVu().getGiaDichVu());

                        } else {

                            thanhToan.setSoTien(BigDecimal.ZERO);

                        }

                        thanhToan.setTrangThai("Chưa thanh toán");

                        thanhToan.setPhuongThuc(null);

                        thanhToan.setNgayThanhToan(null);

                        thanhToan.setGhiChu("");

                        thanhToanDAO.insert(thanhToan);
                    }

                    session.setAttribute(
                            "message",
                            "Đã xác nhận lịch khám và tạo hóa đơn.");
                }

                response.sendRedirect(
                        request.getContextPath() + "/lichkham");

                return;
            }

            String id = request.getParameter("maLichKham");

            int maBenhNhan =
                    Integer.parseInt(request.getParameter("maBenhNhan"));

            int maBacSi =
                    Integer.parseInt(request.getParameter("maBacSi"));

            int maDichVu =
                    Integer.parseInt(request.getParameter("maDichVu"));

            Date ngayKham =
                    Date.valueOf(request.getParameter("ngayKham"));

            Time gioKham =
                    Time.valueOf(request.getParameter("gioKham") + ":00");

            String trangThai =
                    request.getParameter("trangThai");

            BenhNhan benhNhan =
                    benhNhanDAO.findByMaBenhNhan(maBenhNhan);

            BacSi bacSi =
                    bacSiDAO.findByMaBacSi(maBacSi);

            DichVu dichVu =
                    dichVuDAO.findByMaDichVu(maDichVu);

            if (benhNhan == null
                    || bacSi == null
                    || dichVu == null) {

                session.setAttribute("error",
                        "Không tìm thấy dữ liệu.");

                response.sendRedirect(request.getContextPath() + "/lichkham");
                return;
            }
            
       
            //================ KIỂM TRA TRÙNG LỊCH =================

            if (id == null || id.isBlank()) {

                if (lichKhamDAO.isDoctorBusy(maBacSi,
                        ngayKham,
                        gioKham)) {

                    session.setAttribute("error",
                            "Bác sĩ đã có lịch khám vào thời gian này.");

                    response.sendRedirect(request.getContextPath() + "/lichkham");
                    return;
                }

            } else {

                if (lichKhamDAO.isDoctorBusy(
                        maBacSi,
                        ngayKham,
                        gioKham,
                        Integer.parseInt(id))) {

                    session.setAttribute("error",
                            "Bác sĩ đã có lịch khám vào thời gian này.");

                    response.sendRedirect(request.getContextPath() + "/lichkham");
                    return;
                }

            }

            LichKham lich;
            //================ THÊM MỚI =================

            if (id == null || id.isBlank()) {

                lich = new LichKham();

                lich.setNgayDat(
                        new Timestamp(System.currentTimeMillis()));

            }

            //================ CẬP NHẬT =================

            else {

                lich = lichKhamDAO.findByMaLichKham(
                        Integer.parseInt(id));

                if (lich == null) {

                    session.setAttribute("error",
                            "Không tìm thấy lịch khám.");

                    response.sendRedirect(
                            request.getContextPath() + "/lichkham");

                    return;
                }
            }

            //================ GÁN DỮ LIỆU =================

            lich.setBenhNhan(benhNhan);

            lich.setBacSi(bacSi);

            lich.setDichVu(dichVu);

            lich.setNgayKham(ngayKham);

            lich.setGioKham(gioKham);

            lich.setLyDoKham(
                    request.getParameter("lyDoKham"));

            lich.setGhiChu(
                    request.getParameter("ghiChu"));

            lich.setTrangThai(trangThai);

            //================ LƯU =================

            if (id == null || id.isBlank()) {

                lichKhamDAO.insert(lich);

                session.setAttribute("message",
                        "Thêm lịch khám thành công.");

            } else {

                lichKhamDAO.update(lich);

                session.setAttribute("message",
                        "Cập nhật lịch khám thành công.");

            }

            //================ NHẬT KÝ =================

            NhatKyHoatDong nk = new NhatKyHoatDong();

            nk.setTaiKhoan(user);

            nk.setHanhDong(
                    id == null || id.isBlank()
                            ? "Thêm lịch khám"
                            : "Cập nhật lịch khám");

            nk.setBangTacDong("LichKham");

            nk.setMaDoiTuong(
                    lich.getMaLichKham());

            nk.setThoiGian(
                    new Timestamp(System.currentTimeMillis()));

            nk.setDiaChiIP(
                    request.getRemoteAddr());

            nk.setGhiChu("Quản lý lịch khám");

            nhatKyDAO.insert(nk);

            response.sendRedirect(
                    request.getContextPath() + "/lichkham");

        } catch (Exception e) {

            e.printStackTrace();

            session.setAttribute("error",
                    "Có lỗi xảy ra khi lưu lịch khám.");

            response.sendRedirect(
                    request.getContextPath() + "/lichkham");
        }
    }
}
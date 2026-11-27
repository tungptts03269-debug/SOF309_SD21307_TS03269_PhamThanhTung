package poly.com.servlet;

import java.io.IOException;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import poly.com.dao.BenhNhanDAO;
import poly.com.dao.BacSiDAO;
import poly.com.dao.DanhGiaDAO;

import poly.com.entity.BacSi;
import poly.com.entity.BenhNhan;
import poly.com.entity.DanhGia;
import poly.com.entity.TaiKhoan;

@WebServlet("/danhgiabacsi")
public class DanhGiaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private DanhGiaDAO danhGiaDAO = new DanhGiaDAO();
    private BenhNhanDAO benhNhanDAO = new BenhNhanDAO();
    private BacSiDAO bacSiDAO = new BacSiDAO();

    // =====================================================
    // GET
    // =====================================================

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);

        if (session == null ||
            session.getAttribute("user") == null) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/index.jsp?loginRequired=true");

            return;
        }

        TaiKhoan user =
                (TaiKhoan) session.getAttribute("user");

        // Chỉ bệnh nhân mới được đánh giá

        if (!"Bệnh nhân".equals(user.getVaiTro())) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/index.jsp");

            return;
        }

        // Lấy bệnh nhân

        BenhNhan benhNhan =
                benhNhanDAO.findByTaiKhoan(
                        user.getMaTaiKhoan());

        if (benhNhan == null) {

            request.setAttribute(
                    "error",
                    "Không tìm thấy thông tin bệnh nhân.");

            request.getRequestDispatcher(
                    "/danhgiabacsi.jsp")
                    .forward(request, response);

            return;
        }

        // Lấy danh sách bác sĩ

        request.setAttribute(
                "benhNhan",
                benhNhan);

        request.setAttribute(
                "danhSachBacSi",
                bacSiDAO.findAll());

        // Lấy lịch sử đánh giá

        request.setAttribute(
                "danhSachDanhGia",
                danhGiaDAO.findByBenhNhan(
                        benhNhan.getMaBenhNhan()));

        request.getRequestDispatcher(
                "/danhgiabacsi.jsp")
                .forward(request, response);
    }

    // =====================================================
    // POST
    // =====================================================

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session =
                request.getSession(false);

        // ================= KIỂM TRA ĐĂNG NHẬP =================

        if (session == null ||
            session.getAttribute("user") == null) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/index.jsp?loginRequired=true");

            return;
        }

        TaiKhoan user =
                (TaiKhoan) session.getAttribute("user");

        // ================= KIỂM TRA VAI TRÒ =================

        if (!"Bệnh nhân".equals(user.getVaiTro())) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/index.jsp");

            return;
        }

        try {

            // ================= LẤY DỮ LIỆU =================

            String maBacSiStr =
                    request.getParameter("maBacSi");

            String soSaoStr =
                    request.getParameter("soSao");

            String noiDung =
                    request.getParameter("noiDung");

            if (maBacSiStr == null ||
                soSaoStr == null) {

                request.setAttribute(
                        "error",
                        "Vui lòng chọn bác sĩ và số sao.");

                request.getRequestDispatcher(
                        "/danhgiabacsi.jsp")
                        .forward(request, response);

                return;
            }

            int maBacSi =
                    Integer.parseInt(maBacSiStr);

            int soSao =
                    Integer.parseInt(soSaoStr);

            // ================= KIỂM TRA SỐ SAO =================

            if (soSao < 1 || soSao > 5) {

                request.setAttribute(
                        "error",
                        "Số sao phải từ 1 đến 5.");

                request.getRequestDispatcher(
                        "/danhgiabacsi.jsp")
                        .forward(request, response);

                return;
            }

            // ================= TÌM BỆNH NHÂN =================

            BenhNhan benhNhan =
                    benhNhanDAO.findByTaiKhoan(
                            user.getMaTaiKhoan());

            if (benhNhan == null) {

                request.setAttribute(
                        "error",
                        "Không tìm thấy thông tin bệnh nhân.");

                request.getRequestDispatcher(
                        "/danhgiabacsi.jsp")
                        .forward(request, response);

                return;
            }

            // ================= TÌM BÁC SĨ =================

            BacSi bacSi =
                    bacSiDAO.findById(maBacSi);

            if (bacSi == null) {

                request.setAttribute(
                        "error",
                        "Bác sĩ không tồn tại.");

                request.getRequestDispatcher(
                        "/danhgiabacsi.jsp")
                        .forward(request, response);

                return;
            }

            // ================= KIỂM TRA ĐÃ ĐÁNH GIÁ =================

            DanhGia danhGiaCu =
                    danhGiaDAO.findByBenhNhanAndBacSi(
                            benhNhan.getMaBenhNhan(),
                            maBacSi);

            if (danhGiaCu != null) {

                request.setAttribute(
                        "error",
                        "Bạn đã đánh giá bác sĩ này rồi.");

                request.getRequestDispatcher(
                        "/danhgiabacsi.jsp")
                        .forward(request, response);

                return;
            }

            // ================= TẠO ĐÁNH GIÁ =================

            DanhGia danhGia =
                    new DanhGia();

            danhGia.setBenhNhan(
                    benhNhan);

            danhGia.setBacSi(
                    bacSi);

            danhGia.setSoSao(
                    soSao);

            danhGia.setNoiDung(
                    noiDung);

            danhGia.setNgayDanhGia(
                    new Date());

            // ================= LƯU DATABASE =================

            danhGiaDAO.insert(
                    danhGia);

            // ================= THÔNG BÁO =================

            session.setAttribute(
                    "danhGiaMessage",
                    "Đánh giá bác sĩ thành công!");

            response.sendRedirect(
                    request.getContextPath()
                    + "/danhgia");

        } catch (NumberFormatException e) {

            request.setAttribute(
                    "error",
                    "Dữ liệu đánh giá không hợp lệ.");

            request.getRequestDispatcher(
                    "/danhgiabacsi.jsp")
                    .forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute(
                    "error",
                    "Có lỗi xảy ra khi gửi đánh giá: "
                    + e.getMessage());

            request.getRequestDispatcher(
                    "/danhgiabacsi.jsp")
                    .forward(request, response);
        }
    }
}
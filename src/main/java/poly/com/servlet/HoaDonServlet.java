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
import poly.com.dao.YeuCauHuyLichDAO;
import poly.com.entity.TaiKhoan;
import poly.com.entity.ThanhToan;
import poly.com.entity.YeuCauHuyLich;

@WebServlet("/hoadon")
public class HoaDonServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ThanhToanDAO thanhToanDAO = new ThanhToanDAO();

    private YeuCauHuyLichDAO yeuCauDAO =
            new YeuCauHuyLichDAO();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if (session == null
                || session.getAttribute("user") == null) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/index.jsp");

            return;
        }

        TaiKhoan user =
                (TaiKhoan) session.getAttribute("user");
        String keyword = request.getParameter("keyword");
        String trangThai = request.getParameter("trangThai");
        request.setAttribute("keyword", keyword);
        request.setAttribute("trangThai", trangThai);
        try {

            List<ThanhToan> listHoaDon;

            //=========================
            // ADMIN
            //=========================

            if ("Admin".equalsIgnoreCase(user.getVaiTro())) {

                if (keyword != null && !keyword.isBlank()) {

                    listHoaDon = thanhToanDAO.search(keyword);

                }

                else if (trangThai != null && !trangThai.isBlank()) {

                    listHoaDon = thanhToanDAO.findByTrangThai(trangThai);

                }

                else {

                    listHoaDon = thanhToanDAO.findAll();

                }

                // Thống kê
                request.setAttribute(
                        "tongDoanhThu",
                        thanhToanDAO.tongDoanhThu());

                request.setAttribute(
                        "doanhThuHomNay",
                        thanhToanDAO.doanhThuHomNay());

                request.setAttribute(
                        "doanhThuThang",
                        thanhToanDAO.doanhThuThang());

                request.setAttribute(
                        "doanhThuNam",
                        thanhToanDAO.doanhThuNam());

                request.setAttribute(
                        "daThanhToan",
                        thanhToanDAO.countDaThanhToan());

                request.setAttribute(
                        "chuaThanhToan",
                        thanhToanDAO.countChuaThanhToan());

            }

            //=========================
            // BỆNH NHÂN
            //=========================

            else {

                listHoaDon =
                        thanhToanDAO.findByBenhNhan(
                                user.getMaTaiKhoan());

            }

            //=========================
            // DANH SÁCH YÊU CẦU HỦY
            //=========================

            List<YeuCauHuyLich> listYeuCau =
                    yeuCauDAO.findAll();

            request.setAttribute(
                    "listHoaDon",
                    listHoaDon);

            request.setAttribute(
                    "listYeuCau",
                    listYeuCau);

            request.getRequestDispatcher(
                    "/hoadon.jsp")
                    .forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute(
                    "error",
                    "Không thể tải danh sách hóa đơn.");

            request.getRequestDispatcher(
                    "/hoadon.jsp")
                    .forward(request, response);

        }

    }

}
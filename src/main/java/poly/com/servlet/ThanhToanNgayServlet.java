package poly.com.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import poly.com.dao.ThanhToanDAO;
import poly.com.entity.ThanhToan;

@WebServlet("/thanhtoanngay")
public class ThanhToanNgayServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ThanhToanDAO thanhToanDAO = new ThanhToanDAO();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int maThanhToan = Integer.parseInt(request.getParameter("id"));

            ThanhToan tt = thanhToanDAO.findByMaThanhToan(maThanhToan);

            if (tt != null) {

                // Chỉ cập nhật nếu chưa thanh toán
                if (!"Đã thanh toán".equals(tt.getTrangThai())) {

                    tt.setTrangThai("Đã thanh toán");
                    tt.setPhuongThuc("Thanh toán QR");

                    tt.setNgayThanhToan(
                            new Timestamp(System.currentTimeMillis()));

                    thanhToanDAO.update(tt);
                }
            }

            response.sendRedirect(
                    request.getContextPath() + "/hoadon?id=" + maThanhToan);

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath() + "/hoadon");

        }

    }

}
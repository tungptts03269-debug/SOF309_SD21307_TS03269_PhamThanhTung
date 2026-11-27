package poly.com.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.com.dao.ThanhToanDAO;
import poly.com.entity.ThanhToan;

@WebServlet("/thanhtoantaiphongkham")
public class ThanhToanTaiPhongKhamServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ThanhToanDAO thanhToanDAO =
            new ThanhToanDAO();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int maThanhToan =
                    Integer.parseInt(request.getParameter("id"));

            ThanhToan tt =
                    thanhToanDAO.findByMaThanhToan(maThanhToan);

            if (tt != null) {
            	tt.setTrangThai("Chưa thanh toán");
            	tt.setPhuongThuc("Thanh toán tại phòng khám");

                thanhToanDAO.update(tt);

            }

            response.sendRedirect(
                    request.getContextPath() + "/hoadon");

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath() + "/hoadon");

        }

    }

}
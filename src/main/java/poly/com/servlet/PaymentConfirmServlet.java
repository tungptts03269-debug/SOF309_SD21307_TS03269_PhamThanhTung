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

@WebServlet("/paymentconfirm")
public class PaymentConfirmServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ThanhToanDAO thanhToanDAO = new ThanhToanDAO();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String id = request.getParameter("id");

            if (id == null || id.isBlank()) {

                response.sendRedirect(request.getContextPath());

                return;

            }

            int maThanhToan = Integer.parseInt(id);

            ThanhToan tt =
                    thanhToanDAO.findByMaThanhToan(maThanhToan);

            if (tt == null) {

                response.getWriter().println("Không tìm thấy hóa đơn.");

                return;

            }

            request.setAttribute("tt", tt);

            request.getRequestDispatcher("/paymentconfirm.jsp")
                    .forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int id =
                    Integer.parseInt(request.getParameter("id"));

            ThanhToan tt =
                    thanhToanDAO.findByMaThanhToan(id);

            if (tt != null) {

                tt.setTrangThai("Đã thanh toán");

                tt.setPhuongThuc("QR Code");

                tt.setNgayThanhToan(
                	    new Timestamp(System.currentTimeMillis()));

                thanhToanDAO.update(tt);

            }

            response.sendRedirect(
                    request.getContextPath()
                    + "/paymentsuccess.jsp");

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
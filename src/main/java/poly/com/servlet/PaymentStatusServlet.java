package poly.com.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.com.dao.ThanhToanDAO;
import poly.com.entity.ThanhToan;

@WebServlet("/paymentstatus")
public class PaymentStatusServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ThanhToanDAO thanhToanDAO = new ThanhToanDAO();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain;charset=UTF-8");

        try {

            int id = Integer.parseInt(request.getParameter("id"));

            ThanhToan tt =
                    thanhToanDAO.findByMaThanhToan(id);

            if (tt != null &&
                "Đã thanh toán".equals(tt.getTrangThai())) {

                response.getWriter().print("PAID");

            } else {

                response.getWriter().print("WAITING");

            }

        } catch (Exception e) {

            response.getWriter().print("WAITING");

        }

    }

}
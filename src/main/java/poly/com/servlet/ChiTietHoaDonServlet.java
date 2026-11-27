package poly.com.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import poly.com.dao.ThanhToanDAO;
import poly.com.entity.ThanhToan;

@WebServlet("/chitiethoadon")
public class ChiTietHoaDonServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ThanhToanDAO thanhToanDAO =
            new ThanhToanDAO();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int id =
                    Integer.parseInt(request.getParameter("id"));

            ThanhToan hd =
                    thanhToanDAO.findDetail(id);

            request.setAttribute("hd", hd);

            request.getRequestDispatcher("/chitiethoadon.jsp")
                    .forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                            + "/hoadon");

        }

    }

}
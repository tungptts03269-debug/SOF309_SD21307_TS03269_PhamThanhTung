package poly.com.servlet;

import java.io.IOException;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.com.dao.LienHeDAO;
import poly.com.entity.LienHe;

@WebServlet("/lienhe")
public class LienHeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private LienHeDAO lienHeDAO = new LienHeDAO();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/lienhe.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String hoTen = request.getParameter("hoTen");
        String email = request.getParameter("email");
        String soDienThoai = request.getParameter("soDienThoai");
        String noiDung = request.getParameter("noiDung");

        LienHe lienHe = new LienHe();
        lienHe.setHoTen(hoTen);
        lienHe.setEmail(email);
        lienHe.setSoDienThoai(soDienThoai);
        lienHe.setNoiDung(noiDung);
        lienHe.setNgayGui(new Date());
        lienHe.setTrangThai("Chưa phản hồi");

        try {
            lienHeDAO.create(lienHe);

            request.setAttribute("message",
                    "Gửi liên hệ thành công! Chúng tôi sẽ phản hồi sớm nhất.");

        } catch (Exception e) {

            request.setAttribute("error",
                    "Gửi liên hệ thất bại!");

            e.printStackTrace();
        }

        request.getRequestDispatcher("/lienhe.jsp")
               .forward(request, response);
    }
}
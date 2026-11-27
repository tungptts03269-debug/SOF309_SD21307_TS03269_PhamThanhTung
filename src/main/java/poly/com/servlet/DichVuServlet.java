package poly.com.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.com.dao.DichVuDAO;

@WebServlet("/dichvu")
public class DichVuServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private DichVuDAO dao = new DichVuDAO();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("dichVus", dao.findAll());

        request.getRequestDispatcher("/dichvu.jsp")
               .forward(request, response);
    }

}
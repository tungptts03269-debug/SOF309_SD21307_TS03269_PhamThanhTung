package poly.com.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.com.dao.ChuyenKhoaDAO;

@WebServlet("/chuyenkhoa")
public class ChuyenKhoaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ChuyenKhoaDAO dao = new ChuyenKhoaDAO();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("chuyenKhoas", dao.findAll());

        request.getRequestDispatcher("/chuyenkhoa.jsp")
               .forward(request, response);
    }

}
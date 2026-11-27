package poly.com.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.com.dao.BacSiDAO;
import poly.com.dao.ChuyenKhoaDAO;
import poly.com.dao.DichVuDAO;
import poly.com.dao.PhongKhamDAO;

@WebServlet("/")
public class TrangChuServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private BacSiDAO bacSiDAO = new BacSiDAO();
    private ChuyenKhoaDAO chuyenKhoaDAO = new ChuyenKhoaDAO();
    private DichVuDAO dichVuDAO = new DichVuDAO();
    private PhongKhamDAO phongKhamDAO = new PhongKhamDAO();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Danh sách bác sĩ
        request.setAttribute("bacSis", bacSiDAO.findAll());

        // Danh sách chuyên khoa
        request.setAttribute("chuyenKhoas", chuyenKhoaDAO.findAll());

        // Danh sách dịch vụ
        request.setAttribute("dichVus", dichVuDAO.findAll());

        // Danh sách phòng khám
        request.setAttribute("phongKhams", phongKhamDAO.findAll());

        request.getRequestDispatcher("/index.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);

    }

}
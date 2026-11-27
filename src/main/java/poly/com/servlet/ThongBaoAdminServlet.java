package poly.com.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.com.dao.ThongBaoAdminDAO;
import poly.com.entity.ThongBaoAdmin;

@WebServlet("/admin/thongbao")
public class ThongBaoAdminServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ThongBaoAdminDAO dao = new ThongBaoAdminDAO();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("read".equals(action)) {

            Integer id = Integer.parseInt(request.getParameter("id"));

            dao.markAsRead(id);

            response.sendRedirect(request.getContextPath()
                    + "/admin/thongbao");

            return;
        }

        if ("delete".equals(action)) {

            Integer id = Integer.parseInt(request.getParameter("id"));

            dao.delete(id);

            response.sendRedirect(request.getContextPath()
                    + "/admin/thongbao");

            return;
        }

        List<ThongBaoAdmin> list = dao.findAll();

        request.setAttribute("listThongBaoAdmin", list);

        request.setAttribute("soThongBaoChuaDoc",
                dao.countUnread());

        request.getRequestDispatcher("admin/thongbaoAdmin.jsp")
                .forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);

    }

}
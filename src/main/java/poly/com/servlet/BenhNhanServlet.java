package poly.com.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import poly.com.dao.BenhNhanDAO;
import poly.com.dao.TaiKhoanDAO;
import poly.com.entity.BenhNhan;
import poly.com.entity.TaiKhoan;

@WebServlet("/admin/benhnhan")
public class BenhNhanServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private BenhNhanDAO benhNhanDAO = new BenhNhanDAO();
    private TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {

            case "new":
                loadForm(request);
                request.getRequestDispatcher("/WEB-INF/views/admin/benhnhan/form.jsp")
                        .forward(request, response);
                break;

            case "edit":
                edit(request, response);
                break;

            case "delete":
                delete(request, response);
                break;

            default:
                list(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("maBenhNhan");

        if (id == null || id.isEmpty()) {

            insert(request);

        } else {

            update(request);

        }

        response.sendRedirect(request.getContextPath() + "/admin/benhnhan");

    }

    // ================= DANH SÁCH =================

    private void list(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<BenhNhan> list = benhNhanDAO.findAll();

        request.setAttribute("list", list);

        request.getRequestDispatcher("/WEB-INF/views/admin/benhnhan/list.jsp")
                .forward(request, response);

    }

    // ================= FORM SỬA =================

    private void edit(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        BenhNhan bn = benhNhanDAO.findById(id);

        request.setAttribute("benhNhan", bn);

        loadForm(request);

        request.getRequestDispatcher("/WEB-INF/views/admin/benhnhan/form.jsp")
                .forward(request, response);

    }

    // ================= THÊM =================

    private void insert(HttpServletRequest request) {

        BenhNhan bn = new BenhNhan();

        bn.setHoTen(request.getParameter("hoTen"));
        bn.setNgaySinh(java.sql.Date.valueOf(request.getParameter("ngaySinh")));
        bn.setGioiTinh(request.getParameter("gioiTinh"));
        bn.setSoDienThoai(request.getParameter("soDienThoai"));
        bn.setDiaChi(request.getParameter("diaChi"));

        int maTaiKhoan = Integer.parseInt(request.getParameter("maTaiKhoan"));

        TaiKhoan tk = taiKhoanDAO.findById(maTaiKhoan);

        bn.setTaiKhoan(tk);

        benhNhanDAO.insert(bn);

    }

    // ================= CẬP NHẬT =================

    private void update(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("maBenhNhan"));

        BenhNhan bn = benhNhanDAO.findById(id);

        bn.setHoTen(request.getParameter("hoTen"));
        bn.setNgaySinh(java.sql.Date.valueOf(request.getParameter("ngaySinh")));
        bn.setGioiTinh(request.getParameter("gioiTinh"));
        bn.setSoDienThoai(request.getParameter("soDienThoai"));
        bn.setDiaChi(request.getParameter("diaChi"));

        int maTaiKhoan = Integer.parseInt(request.getParameter("maTaiKhoan"));

        bn.setTaiKhoan(taiKhoanDAO.findById(maTaiKhoan));

        benhNhanDAO.update(bn);

    }

    // ================= XÓA =================

    private void delete(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        benhNhanDAO.delete(id);

        response.sendRedirect(request.getContextPath() + "/admin/benhnhan");

    }

    // ================= LOAD DỮ LIỆU FORM =================

    private void loadForm(HttpServletRequest request) {

        request.setAttribute("taiKhoans",
                taiKhoanDAO.findByRole("Bệnh nhân"));

    }

}
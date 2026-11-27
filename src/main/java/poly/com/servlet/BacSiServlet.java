package poly.com.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import poly.com.dao.BacSiDAO;
import poly.com.dao.ChuyenKhoaDAO;
import poly.com.dao.PhongKhamDAO;
import poly.com.dao.TaiKhoanDAO;
import poly.com.entity.BacSi;
import poly.com.entity.ChuyenKhoa;
import poly.com.entity.PhongKham;
import poly.com.entity.TaiKhoan;

@WebServlet("/admin/bacsi")
public class BacSiServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private BacSiDAO bacSiDAO = new BacSiDAO();
    private ChuyenKhoaDAO chuyenKhoaDAO = new ChuyenKhoaDAO();
    private PhongKhamDAO phongKhamDAO = new PhongKhamDAO();
    private TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null)
            action = "list";

        switch (action) {

            case "new":
                loadForm(request);
                request.getRequestDispatcher("/WEB-INF/views/admin/bacsi/form.jsp")
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

        String id = request.getParameter("maBacSi");

        if (id == null || id.isEmpty()) {

            insert(request);

        } else {

            update(request);

        }

        response.sendRedirect(request.getContextPath() + "/admin/bacsi");

    }

    private void list(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<BacSi> list = bacSiDAO.findAll();

        request.setAttribute("list", list);

        request.getRequestDispatcher("/WEB-INF/views/admin/bacsi/list.jsp")
                .forward(request, response);

    }

    private void edit(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        BacSi bs = bacSiDAO.findById(id);

        request.setAttribute("bacSi", bs);

        loadForm(request);

        request.getRequestDispatcher("/WEB-INF/views/admin/bacsi/form.jsp")
                .forward(request, response);

    }

    private void insert(HttpServletRequest request) {

        BacSi bs = new BacSi();

        bs.setHoTen(request.getParameter("hoTen"));
        bs.setSoDienThoai(request.getParameter("soDienThoai"));
        bs.setKinhNghiem(request.getParameter("kinhNghiem"));

        int maTaiKhoan = Integer.parseInt(request.getParameter("maTaiKhoan"));
        int maPhongKham = Integer.parseInt(request.getParameter("maPhongKham"));
        int maChuyenKhoa = Integer.parseInt(request.getParameter("maChuyenKhoa"));

        TaiKhoan tk = taiKhoanDAO.findById(maTaiKhoan);
        PhongKham pk = phongKhamDAO.findById(maPhongKham);
        ChuyenKhoa ck = chuyenKhoaDAO.findById(maChuyenKhoa);

        bs.setTaiKhoan(tk);
        bs.setPhongKham(pk);
        bs.setChuyenKhoa(ck);

        bacSiDAO.insert(bs);

    }

    private void update(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("maBacSi"));

        BacSi bs = bacSiDAO.findById(id);

        bs.setHoTen(request.getParameter("hoTen"));
        bs.setSoDienThoai(request.getParameter("soDienThoai"));
        bs.setKinhNghiem(request.getParameter("kinhNghiem"));

        int maTaiKhoan = Integer.parseInt(request.getParameter("maTaiKhoan"));
        int maPhongKham = Integer.parseInt(request.getParameter("maPhongKham"));
        int maChuyenKhoa = Integer.parseInt(request.getParameter("maChuyenKhoa"));

        bs.setTaiKhoan(taiKhoanDAO.findById(maTaiKhoan));
        bs.setPhongKham(phongKhamDAO.findById(maPhongKham));
        bs.setChuyenKhoa(chuyenKhoaDAO.findById(maChuyenKhoa));

        bacSiDAO.update(bs);

    }

    private void delete(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        bacSiDAO.delete(id);

        response.sendRedirect(request.getContextPath() + "/admin/bacsi");

    }

    private void loadForm(HttpServletRequest request) {

        request.setAttribute("taiKhoans",
                taiKhoanDAO.findByRole("Bác sĩ"));

        request.setAttribute("phongKhams",
                phongKhamDAO.findAll());

        request.setAttribute("chuyenKhoas",
                chuyenKhoaDAO.findAll());

    }

}
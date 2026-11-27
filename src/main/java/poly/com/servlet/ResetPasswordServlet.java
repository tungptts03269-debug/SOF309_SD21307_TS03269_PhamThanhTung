package poly.com.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import poly.com.dao.TaiKhoanDAO;

@WebServlet("/resetpassword")
public class ResetPasswordServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null
                || session.getAttribute("otpVerified") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/forgotpassword");

            return;
        }

        request.getRequestDispatcher("/resetpassword.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);

        if (session == null
                || session.getAttribute("otpVerified") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/forgotpassword");

            return;
        }

        String email = (String) session.getAttribute("email");

        String newPassword =
                request.getParameter("newPassword");

        String confirmPassword =
                request.getParameter("confirmPassword");

        if (newPassword == null
                || confirmPassword == null
                || newPassword.isBlank()
                || confirmPassword.isBlank()) {

            request.setAttribute("error",
                    "Vui lòng nhập đầy đủ thông tin.");

            request.getRequestDispatcher("/resetpassword.jsp")
                    .forward(request, response);

            return;
        }

        if (!newPassword.equals(confirmPassword)) {

            request.setAttribute("error",
                    "Mật khẩu xác nhận không khớp.");

            request.getRequestDispatcher("/resetpassword.jsp")
                    .forward(request, response);

            return;
        }

        boolean result =
                taiKhoanDAO.resetPassword(email, newPassword);

        if (result) {

            // Xóa dữ liệu OTP
            session.removeAttribute("otp");
            session.removeAttribute("otpExpire");
            session.removeAttribute("otpVerified");
            session.removeAttribute("email");

            request.setAttribute("message",
                    "Đổi mật khẩu thành công. Vui lòng đăng nhập.");

            request.getRequestDispatcher("/forgotpassword.jsp")
                    .forward(request, response);

        } else {

            request.setAttribute("error",
                    "Không thể đổi mật khẩu.");

            request.getRequestDispatcher("/resetpassword.jsp")
                    .forward(request, response);
        }
    }
}
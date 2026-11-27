package poly.com.servlet;

import java.io.IOException;
import java.util.Random;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import poly.com.dao.TaiKhoanDAO;
import poly.com.utils.EmailUtil;

@WebServlet("/forgotpassword")
public class ForgotPasswordServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/forgotpassword.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");

        // Kiểm tra email
        if (email == null || email.isBlank()) {

            request.setAttribute("error",
                    "Vui lòng nhập email.");

            request.getRequestDispatcher("/forgotpassword.jsp")
                    .forward(request, response);
            return;
        }

        // Kiểm tra email tồn tại
        if (!taiKhoanDAO.existsEmail(email)) {

            request.setAttribute("error",
                    "Email không tồn tại.");

            request.getRequestDispatcher("/forgotpassword.jsp")
                    .forward(request, response);
            return;
        }

        // Sinh OTP
        String otp = String.format("%06d",
                new Random().nextInt(1000000));

        HttpSession session = request.getSession();

        // Lưu Session
        session.setAttribute("otp", otp);
        session.setAttribute("email", email);

        // OTP hết hạn sau 5 phút
        session.setAttribute("otpExpire",
                System.currentTimeMillis() + 5 * 60 * 1000);

        boolean sent = EmailUtil.sendOTP(email, otp);

        if (sent) {

            response.sendRedirect(
                    request.getContextPath() + "/verifyotp");

        } else {

            request.setAttribute("error",
                    "Không gửi được email.");

            request.getRequestDispatcher("/forgotpassword.jsp")
                    .forward(request, response);
        }
    }
}
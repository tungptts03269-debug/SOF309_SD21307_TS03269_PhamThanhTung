package poly.com.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/verifyotp")
public class VerifyOTPServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/verifyotp.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null) {

            response.sendRedirect(
                    request.getContextPath() + "/forgotpassword");
            return;
        }

        String otpInput = request.getParameter("otp");

        String otpSession =
                (String) session.getAttribute("otp");

        Long expireTime =
                (Long) session.getAttribute("otpExpire");

        if (otpSession == null || expireTime == null) {

            request.setAttribute("error",
                    "OTP không tồn tại.");

            request.getRequestDispatcher("/verifyotp.jsp")
                    .forward(request, response);
            return;
        }

        // Kiểm tra hết hạn
        if (System.currentTimeMillis() > expireTime) {

            session.removeAttribute("otp");
            session.removeAttribute("otpExpire");
            session.removeAttribute("email");

            request.setAttribute("error",
                    "Mã OTP đã hết hạn. Vui lòng lấy mã mới.");

            request.getRequestDispatcher("/verifyotp.jsp")
                    .forward(request, response);
            return;
        }

        // Kiểm tra OTP
        if (!otpSession.equals(otpInput)) {

            request.setAttribute("error",
                    "Mã OTP không đúng.");

            request.getRequestDispatcher("/verifyotp.jsp")
                    .forward(request, response);
            return;
        }

        // OTP đúng
        session.setAttribute("otpVerified", true);

        // Xóa OTP để không dùng lại
        session.removeAttribute("otp");
        session.removeAttribute("otpExpire");

        response.sendRedirect(
                request.getContextPath() + "/resetpassword.jsp");
    }
}
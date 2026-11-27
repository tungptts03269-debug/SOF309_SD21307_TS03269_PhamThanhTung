package poly.com.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import poly.com.dao.ThanhToanDAO;
import poly.com.entity.ThanhToan;

@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ThanhToanDAO thanhToanDAO = new ThanhToanDAO();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String id = request.getParameter("id");

            if (id == null || id.trim().isEmpty()) {

                response.sendRedirect(
                        request.getContextPath() + "/hoadon");

                return;
            }

            int maThanhToan = Integer.parseInt(id);

            ThanhToan tt =
                    thanhToanDAO.findByMaThanhToan(maThanhToan);

            if (tt == null) {

                response.sendRedirect(
                        request.getContextPath() + "/hoadon");

                return;
            }

            /*
             * Nội dung QR
             * Có thể thay đổi theo ý muốn
             */

            String qrContent =
                    "http://192.168.100.127:8080"
                    + request.getContextPath()
                    + "/paymentconfirm?id="
                    + tt.getMaThanhToan();

            Map<EncodeHintType, Object> hints = new HashMap<>();

            hints.put(
                    EncodeHintType.CHARACTER_SET,
                    "UTF-8");

            BitMatrix matrix =
                    new MultiFormatWriter().encode(
                            qrContent,
                            BarcodeFormat.QR_CODE,
                            300,
                            300,
                            hints);

            ByteArrayOutputStream out =
                    new ByteArrayOutputStream();

            MatrixToImageWriter.writeToStream(
                    matrix,
                    "PNG",
                    out);

            String qrImage =
                    Base64.getEncoder()
                            .encodeToString(
                                    out.toByteArray());

            request.setAttribute("tt", tt);

            request.setAttribute("qrImage", qrImage);

            request.getRequestDispatcher("/payment.jsp")
                    .forward(request, response);

        }

        catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath() + "/hoadon");

        }

    }

}
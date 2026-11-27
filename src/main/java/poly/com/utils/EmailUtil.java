package poly.com.utils;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailUtil {

    // Email gửi OTP
    private static final String FROM_EMAIL = "tungptts03269@gmail.com";

    // App Password của Gmail (16 ký tự)
    private static final String APP_PASSWORD = "mhxe ecov qwgi hrfo";

    public static boolean sendOTP(String toEmail, String otp) {

        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new Authenticator() {

                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {

                        return new PasswordAuthentication(
                                FROM_EMAIL,
                                APP_PASSWORD);

                    }

                });

        try {

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(FROM_EMAIL));

            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));

            message.setSubject("Đặt lại mật khẩu - Phòng Khám Online");

            String content = """
                    <html>
                    <body style='font-family:Arial'>

                    <h2 style='color:#0d6efd'>
                    PHÒNG KHÁM ONLINE
                    </h2>

                    <p>Xin chào!</p>

                    <p>Bạn vừa yêu cầu đặt lại mật khẩu.</p>

                    <p>Mã OTP của bạn là:</p>

                    <h1 style='color:red'>
                    %s
                    </h1>

                    <p>
                    Mã OTP có hiệu lực trong 5 phút.
                    </p>

                    <hr>

                    <small>
                    Nếu bạn không yêu cầu đổi mật khẩu,
                    hãy bỏ qua email này.
                    </small>

                    </body>
                    </html>
                    """.formatted(otp);

            message.setContent(content, "text/html; charset=UTF-8");

            Transport.send(message);

            return true;

        } catch (MessagingException e) {

            e.printStackTrace();

            return false;

        }

    }

}
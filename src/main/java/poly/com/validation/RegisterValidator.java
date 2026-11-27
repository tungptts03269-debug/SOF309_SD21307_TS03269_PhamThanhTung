package poly.com.validation;

/**
 * Quy tắc kiểm tra dữ liệu đăng ký được tách riêng để có thể unit test độc lập.
 * Các quy tắc này phản ánh đúng điều kiện hiện có trong RegisterServlet.
 */
public final class RegisterValidator {

    private RegisterValidator() {
    }

    public static void validate(String username, String password, String confirm,
                                String fullName, String email, String phone) {
        if (isBlank(username) || isBlank(password) || isBlank(confirm)
                || isBlank(fullName) || isBlank(email) || isBlank(phone)) {
            throw new IllegalArgumentException("Vui lòng nhập đầy đủ thông tin.");
        }

        if (!password.equals(confirm)) {
            throw new IllegalArgumentException("Mật khẩu xác nhận không khớp.");
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@gmail\\.com$")) {
            throw new IllegalArgumentException("Email Gmail không hợp lệ.");
        }

        if (!phone.matches("^0\\d{9}$")) {
            throw new IllegalArgumentException("Số điện thoại phải gồm đúng 10 số.");
        }
    }

    public static void validateUnique(boolean usernameExists,
                                      boolean emailExists,
                                      boolean phoneExists) {
        if (usernameExists) {
            throw new DuplicateAccountException("Tên đăng nhập đã tồn tại.");
        }
        if (emailExists) {
            throw new DuplicateAccountException("Email đã được sử dụng.");
        }
        if (phoneExists) {
            throw new DuplicateAccountException("Số điện thoại đã được sử dụng.");
        }
    }

    private static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}

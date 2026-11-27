package poly.com.validation;

/** Ngoại lệ nghiệp vụ khi thông tin đăng ký đã được tài khoản khác sử dụng. */
public class DuplicateAccountException extends RuntimeException {
    public DuplicateAccountException(String message) {
        super(message);
    }
}

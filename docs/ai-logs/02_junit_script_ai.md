# AI Log 02 -- Hỗ trợ xây dựng Unit Test bằng JUnit 5

## 1. Mục tiêu

Sử dụng AI hỗ trợ xây dựng các bài kiểm thử Unit Test cho chức năng đăng
nhập và đăng ký tài khoản trong dự án PhongKhamOnline.

## 2. Công cụ sử dụng

-   AI: ChatGPT
-   Ngôn ngữ: Java 17
-   Framework kiểm thử: JUnit 5
-   Thư viện Mock: Mockito
-   Công cụ quản lý dự án: Maven
-   IDE: IntelliJ IDEA

## 3. Prompt đã sử dụng

Tôi đang thực hiện dự án Java Web PhongKhamOnline sử dụng Java Servlet,
JSP, Hibernate và Maven.

Hãy hỗ trợ tôi xây dựng Unit Test bằng JUnit 5 và Mockito cho hai chức
năng LoginServlet và RegisterServlet.

Yêu cầu: - Kiểm thử dữ liệu đầu vào không hợp lệ. - Kiểm thử trường hợp
đăng nhập sai thông tin. - Kiểm thử trường hợp đăng nhập thành công. -
Kiểm thử dữ liệu đăng ký không hợp lệ. - Kiểm thử trường hợp tài khoản,
email hoặc số điện thoại bị trùng. - Sử dụng @ParameterizedTest và
@CsvSource để kiểm thử nhiều bộ dữ liệu. - Sử dụng assertThrows() để
kiểm tra ngoại lệ. - Sử dụng @Timeout để kiểm tra thời gian thực thi. -
Đảm bảo mã kiểm thử phù hợp với cấu trúc Java Servlet hiện tại của dự
án.

## 4. Kết quả AI hỗ trợ

AI đã hỗ trợ xây dựng hai lớp kiểm thử:

-   LoginServletTest.java
-   RegisterServletTest.java

Các lớp kiểm thử sử dụng JUnit 5, Mockito và các phương thức kiểm tra
như: - assertEquals() - assertNotNull() - assertThrows() -
assertDoesNotThrow() - @ParameterizedTest - @CsvSource - @Timeout

## 5. Kết quả thực hiện

Sau khi hoàn thiện mã kiểm thử và chạy Maven, kết quả thu được:

-   Tổng số test: 15
-   Failures: 0
-   Errors: 0
-   Skipped: 0
-   Build: SUCCESS

## 6. Đánh giá

AI giúp tiết kiệm thời gian xây dựng mã kiểm thử, gợi ý các trường hợp
kiểm thử đầu vào hợp lệ và không hợp lệ, đồng thời hỗ trợ sử dụng các
annotation của JUnit 5.

Tuy nhiên, mã kiểm thử vẫn cần được người thực hiện kiểm tra và điều
chỉnh để phù hợp với cấu trúc thực tế của dự án.

## 7. Kết luận

Hai lớp kiểm thử LoginServletTest và RegisterServletTest đã chạy thành
công với tổng cộng 15 test case. Kết quả cho thấy các trường hợp kiểm
thử hiện tại đều đạt yêu cầu.

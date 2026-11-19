# TEST PLAN – PHONGKHAMONLINE

## 1. Mục tiêu
Kiểm tra các luồng nghiệp vụ quan trọng của ứng dụng PhongKhamOnline, ưu tiên đăng nhập và đăng ký; xác nhận các nhánh hợp lệ/không hợp lệ và hành vi điều hướng của Servlet.

## 2. Phạm vi
**Trong phạm vi tuần 1–2:**
- `LoginServlet` (`POST /login`).
- `RegisterServlet` (`POST /register`).
- `RegisterValidator` – lớp tách quy tắc kiểm tra dữ liệu đăng ký để kiểm thử độc lập.
- Kiểm tra tích hợp Maven/JUnit 5 ở mức unit test.

**Ngoài phạm vi hiện tại:**
- Kiểm thử giao diện trình duyệt (Selenium sẽ thực hiện ở tuần 3–4).
- Kiểm thử tải, bảo mật chuyên sâu, kết nối hệ thống thanh toán bên thứ ba.
- Kiểm thử truy vấn SQL Server/JPA thực tế trong unit test; DAO được mock để tách phụ thuộc cơ sở dữ liệu.

## 3. Chiến lược và kỹ thuật
- Unit Test với JUnit Jupiter 5.
- Mockito để giả lập DAO, HttpServletRequest, HttpServletResponse, HttpSession và RequestDispatcher.
- Parameterized Test với `@ParameterizedTest` + `@CsvSource` cho nhiều trường hợp dữ liệu.
- Exception Testing với `assertThrows()` cho lỗi dữ liệu và tài khoản trùng.
- Timeout Testing với `@Timeout` cho hàm kiểm tra dữ liệu.
- Không kết nối cơ sở dữ liệu thật trong các unit test này.

## 4. Môi trường
- JDK 17.
- Maven.
- Jakarta Servlet 6.0 (đã khai báo trong `pom.xml`).
- JUnit Jupiter 5.10.2.
- Mockito 5.12.0.
- Apache Tomcat 10.1 phù hợp với Jakarta Servlet 6.0.

## 5. Điều kiện bắt đầu
- Maven nhận diện project qua `pom.xml`.
- JDK 17 được chọn cho Project SDK và Module SDK.
- Các dependency Maven tải thành công.
- Source code biên dịch được.

## 6. Điều kiện hoàn thành
- Các test case trong phạm vi chạy hết.
- Không có test Failure/Error.
- Lưu được kết quả `mvn test` và cập nhật nếu có lỗi môi trường.
- Không ghi nhận “100% Pass” trước khi thực tế chạy Maven thành công.

## 7. Rủi ro và lưu ý
- Các DAO hiện khởi tạo trực tiếp trong Servlet; đã bổ sung constructor package-private để inject mock trong test, giữ nguyên constructor public không tham số cho container Servlet.
- `RegisterValidator` phản ánh quy tắc validation hiện có trong `RegisterServlet`.
- Kiểm thử Timeout ở đây chỉ xác nhận validator hoàn tất trong giới hạn thời gian, không thay thế kiểm thử hiệu năng hệ thống.
- Đăng nhập hiện so sánh mật khẩu qua DAO; kiểm thử unit không đánh giá độ an toàn lưu trữ mật khẩu.

## 8. Lệnh chạy
```bash
mvn clean test
mvn -Dtest=LoginServletTest,RegisterServletTest test
```

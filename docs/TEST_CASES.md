# TEST CASES – PHONGKHAMONLINE

## A. Đăng nhập – LoginServlet

| ID | Dữ liệu/điều kiện | Kết quả mong đợi | Kỹ thuật |
|---|---|---|---|
| LG-01 | username null, password có giá trị | Forward `/index.jsp`, thông báo yêu cầu nhập đủ | Parameterized |
| LG-02 | username có giá trị, password null | Forward `/index.jsp`, thông báo yêu cầu nhập đủ | Parameterized |
| LG-03 | username rỗng | Forward `/index.jsp`, thông báo yêu cầu nhập đủ | Parameterized |
| LG-04 | password rỗng | Forward `/index.jsp`, thông báo yêu cầu nhập đủ | Parameterized |
| LG-05 | DAO trả về null | Hiển thị sai tên đăng nhập/mật khẩu; không ghi lịch sử | Unit Test + Mockito |
| LG-06 | DAO trả về tài khoản bệnh nhân | Tạo session 30 phút, ghi lịch sử và nhật ký, redirect `/index.jsp` | Unit Test + Mockito |
| LG-07 | Tài khoản Admin | Redirect `/indexadmin.jsp` | Cần bổ sung test |
| LG-08 | Tài khoản Bác sĩ | Redirect `/doctor` | Cần bổ sung test |

## B. Đăng ký – RegisterServlet/RegisterValidator

| ID | Dữ liệu/điều kiện | Kết quả mong đợi | Kỹ thuật |
|---|---|---|---|
| RG-01 | Một trường bắt buộc rỗng/null | Báo “Vui lòng nhập đầy đủ thông tin.” | Exception Test |
| RG-02 | Mật khẩu và xác nhận khác nhau | Báo “Mật khẩu xác nhận không khớp.” | Parameterized |
| RG-03 | Email không thuộc định dạng Gmail | Báo “Email Gmail không hợp lệ.” | Parameterized |
| RG-04 | Số điện thoại không đủ 10 số hoặc không bắt đầu bằng 0 | Báo lỗi số điện thoại | Parameterized |
| RG-05 | Username đã tồn tại | Ném `DuplicateAccountException` với thông báo tương ứng | `assertThrows` |
| RG-06 | Email đã tồn tại | Ném `DuplicateAccountException` với thông báo tương ứng | `assertThrows` |
| RG-07 | Số điện thoại đã tồn tại | Ném `DuplicateAccountException` với thông báo tương ứng | `assertThrows` |
| RG-08 | Dữ liệu hợp lệ | Validator hoàn tất trong 2 giây | `@Timeout` |
| RG-09 | Dữ liệu hợp lệ và DAO không trùng | Tạo tài khoản, hồ sơ bệnh nhân, thông báo và session | Cần bổ sung test tích hợp mock |

## C. Đặt lịch khám – phạm vi kiểm thử tiếp theo

| ID | Điều kiện | Kết quả mong đợi |
|---|---|---|
| BK-01 | Không có session/user khi GET `/datlich` | Redirect `/index.jsp` |
| BK-02 | Có user khi GET `/datlich` | Nạp bác sĩ, dịch vụ và forward `/datlich.jsp` |
| BK-03 | Có user hợp lệ khi POST | Tạo lịch trạng thái “Chờ xác nhận”, ghi thông báo/nhật ký, redirect `/lichkham` |
| BK-04 | Không tìm thấy hồ sơ bệnh nhân | Lưu lỗi trong session, redirect `/datlich` |

## D. Kết quả thực thi
Cập nhật bảng này sau khi chạy Maven:
- Tổng số test:
- Passed:
- Failed:
- Errors:
- Thời điểm chạy:
- JDK/Maven:

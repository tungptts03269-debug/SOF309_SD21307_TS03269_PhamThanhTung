| Test Case ID | Chức năng | Mô tả ca kiểm thử | Loại test (Pos/Neg) | Các bước thực hiện | Dữ liệu kiểm thử (Test Data) | Kết quả mong đợi | Trạng thái (Pass/Fail) |
|---|---|---|:---:|---|---|---|:---:|
| TC_REG_01 | Register | Đăng ký thành công với dữ liệu hợp lệ | Positive | 1. Mở trang đăng ký<br>2. Nhập đủ thông tin hợp lệ<br>3. Bấm Đăng ký | user01 / user01@gmail.com / Abc@1234 / Abc@1234 | Đăng ký thành công, hiện thông báo thành công | |
| TC_REG_02 | Register | Username đúng biên dưới (5 ký tự) | Positive | 1. Nhập username 5 ký tự<br>2. Nhập các trường còn lại hợp lệ<br>3. Bấm Đăng ký | abcde | Đăng ký thành công | |
| TC_REG_03 | Register | Username dưới biên (4 ký tự) | Negative | 1. Nhập username 4 ký tự<br>2. Bấm Đăng ký | abcd | Báo lỗi độ dài username (5-20 ký tự) | |
| TC_REG_04 | Register | Username đúng biên trên (20 ký tự) | Positive | 1. Nhập username 20 ký tự<br>2. Bấm Đăng ký | abcdefghijklmnopqrst | Đăng ký thành công | |
| TC_REG_05 | Register | Username vượt biên (21 ký tự) | Negative | 1. Nhập username 21 ký tự<br>2. Bấm Đăng ký | abcdefghijklmnopqrstu | Báo lỗi độ dài username (5-20 ký tự) | |
| TC_REG_06 | Register | Username đã tồn tại | Negative | 1. Nhập username đã có trong hệ thống<br>2. Bấm Đăng ký | admin / a@gmail.com / Abc@1234 / Abc@1234 | Báo lỗi "Tên đăng nhập đã tồn tại" | |
| TC_REG_07 | Register | Để trống username | Negative | 1. Bỏ trống username<br>2. Bấm Đăng ký | "" | Báo lỗi bắt buộc nhập username | |
| TC_REG_08 | Register | Email sai định dạng | Negative | 1. Nhập email sai định dạng<br>2. Bấm Đăng ký | abc / abc@ / @gmail.com | Báo lỗi "Email không hợp lệ" | |
| TC_REG_09 | Register | Để trống email | Negative | 1. Bỏ trống email<br>2. Bấm Đăng ký | "" | Báo lỗi bắt buộc nhập email | |
| TC_REG_10 | Register | Mật khẩu đúng biên (8 ký tự, có ký tự đặc biệt) | Positive | 1. Nhập mật khẩu 8 ký tự có ký tự đặc biệt<br>2. Bấm Đăng ký | Abcdef@1 | Đăng ký thành công | |
| TC_REG_11 | Register | Mật khẩu dưới biên (7 ký tự) | Negative | 1. Nhập mật khẩu 7 ký tự<br>2. Bấm Đăng ký | Abcde@1 | Báo lỗi mật khẩu tối thiểu 8 ký tự | |
| TC_REG_12 | Register | Mật khẩu đủ dài nhưng thiếu ký tự đặc biệt | Negative | 1. Nhập mật khẩu không có ký tự đặc biệt<br>2. Bấm Đăng ký | Abcdefg12 | Báo lỗi yêu cầu ký tự đặc biệt | |
| TC_REG_13 | Register | Để trống mật khẩu | Negative | 1. Bỏ trống password<br>2. Bấm Đăng ký | "" | Báo lỗi bắt buộc nhập mật khẩu | |
| TC_REG_14 | Register | Xác nhận mật khẩu không khớp | Negative | 1. Nhập password và confirmPassword khác nhau<br>2. Bấm Đăng ký | Abc@1234 / Abc@12345 | Báo lỗi "Mật khẩu xác nhận không khớp" | |
| TC_REG_15 | Register | Để trống toàn bộ form | Negative | 1. Bấm Đăng ký khi chưa nhập gì | Tất cả để trống | Báo lỗi ở từng trường bắt buộc | |
| TC_REG_16 | Register | Gọi hàm đăng ký với dữ liệu null | Negative | 1. Gọi hàm đăng ký với đối số null | null | `assertThrows` bắt được ngoại lệ nghiệp vụ | |
| TC_AUTH_01 | Login | Đăng nhập thành công với tài khoản đúng | Positive | 1. Nhập username<br>2. Nhập password<br>3. Bấm Đăng nhập | tài khoản hợp lệ (ví dụ admin / 123456) | Chuyển hướng tới trang chủ | |
| TC_AUTH_02 | Login | Đăng nhập thất bại khi để trống mật khẩu | Negative | 1. Nhập username<br>2. Bỏ trống password<br>3. Bấm Đăng nhập | admin / "" | Báo lỗi "Mật khẩu không được để trống" | |
| TC_AUTH_03 | Login | Đăng nhập thất bại khi để trống username | Negative | 1. Bỏ trống username<br>2. Nhập password<br>3. Bấm Đăng nhập | "" / 123456 | Báo lỗi bắt buộc nhập username | |
| TC_AUTH_04 | Login | Đăng nhập thất bại khi sai mật khẩu | Negative | 1. Nhập đúng username<br>2. Nhập sai password<br>3. Bấm Đăng nhập | admin / saimatkhau | Ở lại trang đăng nhập, báo lỗi | |
| TC_AUTH_05 | Login | Đăng nhập thất bại khi tài khoản không tồn tại | Negative | 1. Nhập username chưa đăng ký<br>2. Nhập password<br>3. Bấm Đăng nhập | khongco123 / 123456 | Báo lỗi đăng nhập | |
| TC_AUTH_06 | Login | Đăng nhập thất bại khi để trống cả hai trường | Negative | 1. Bấm Đăng nhập khi chưa nhập gì | "" / "" | Báo lỗi nhập đủ thông tin | |
| TC_AUTH_07 | Login | Chống SQL Injection ở username | Negative | 1. Nhập chuỗi injection vào username<br>2. Bấm Đăng nhập | `' OR '1'='1` / bất kỳ | Đăng nhập thất bại, không lộ lỗi hệ thống | |
| TC_AUTH_08 | Login | Đăng nhập nhiều tài khoản (Data-Driven) | Positive / Negative | 1. Dùng `@DataProvider` lặp qua từng cặp tài khoản<br>2. Đăng nhập | Bảng tài khoản hợp lệ và không hợp lệ | Hợp lệ vào được hệ thống, không hợp lệ bị từ chối | |
| TC_CRUD_01 | CRUD | Hiển thị danh sách sản phẩm | Positive | 1. Đăng nhập<br>2. Mở trang danh sách sản phẩm | Có sẵn dữ liệu | Bảng hiển thị đúng các sản phẩm hiện có | |
| TC_CRUD_02 | CRUD | Thêm mới sản phẩm thành công | Positive | 1. Mở form thêm mới<br>2. Điền thông tin hợp lệ<br>3. Bấm Lưu | Tên: Áo thun / Giá: 150000 / SL: 10 | Lưu thành công, dữ liệu hiển thị trên bảng | |
| TC_CRUD_03 | CRUD | Thêm mới thất bại khi để trống tên | Negative | 1. Mở form thêm mới<br>2. Bỏ trống tên sản phẩm<br>3. Bấm Lưu | Tên: "" | Báo lỗi validation, không lưu vào DB | |
| TC_CRUD_04 | CRUD | Thêm mới thất bại khi trùng tên sản phẩm | Negative | 1. Mở form thêm mới<br>2. Nhập tên đã tồn tại<br>3. Bấm Lưu | Tên đã có trong DB | Báo lỗi "Tên đã tồn tại", không lưu vào DB | |
| TC_CRUD_05 | CRUD | Giá bán bằng 0 (biên) | Negative | 1. Nhập giá bán bằng 0<br>2. Bấm Lưu | Giá: 0 | Báo lỗi giá bán phải lớn hơn 0 | |
| TC_CRUD_06 | CRUD | Giá bán nhỏ nhất hợp lệ | Positive | 1. Nhập giá bán bằng 1<br>2. Bấm Lưu | Giá: 1 | Lưu thành công | |
| TC_CRUD_07 | CRUD | Giá bán âm | Negative | 1. Nhập giá bán âm<br>2. Bấm Lưu | Giá: -1 | Báo lỗi giá bán phải lớn hơn 0 | |
| TC_CRUD_08 | CRUD | Số lượng bằng 0 (biên) | Positive | 1. Nhập số lượng bằng 0<br>2. Bấm Lưu | SL: 0 | Lưu thành công | |
| TC_CRUD_09 | CRUD | Số lượng âm | Negative | 1. Nhập số lượng âm<br>2. Bấm Lưu | SL: -1 | Báo lỗi số lượng phải từ 0 trở lên | |
| TC_CRUD_10 | CRUD | Giá và số lượng sai định dạng | Negative | 1. Nhập chữ vào ô giá và số lượng<br>2. Bấm Lưu | Giá: abc / SL: xyz | Báo lỗi định dạng số, không lưu vào DB | |
| TC_CRUD_11 | CRUD | Sửa sản phẩm thành công | Positive | 1. Bấm Sửa ở một sản phẩm<br>2. Đổi thông tin hợp lệ<br>3. Bấm Lưu | Giá mới hợp lệ | Thông tin được cập nhật trên bảng và DB | |
| TC_CRUD_12 | CRUD | Sửa sản phẩm không tồn tại | Negative | 1. Gọi hàm cập nhật với ID không có | ID: 999999 | `assertThrows` bắt được ngoại lệ "không tìm thấy" | |
| TC_CRUD_13 | CRUD | Xóa sản phẩm có xác nhận | Positive | 1. Bấm Xóa<br>2. Chọn Đồng ý ở hộp xác nhận | Một sản phẩm bất kỳ | Sản phẩm bị xóa khỏi bảng và DB | |
| TC_CRUD_14 | CRUD | Hủy thao tác xóa | Negative | 1. Bấm Xóa<br>2. Chọn Hủy ở hộp xác nhận | Một sản phẩm bất kỳ | Sản phẩm vẫn còn nguyên | |
| TC_CRUD_15 | CRUD | Truy cập trang quản lý khi chưa đăng nhập | Negative | 1. Mở trực tiếp URL trang quản lý khi chưa đăng nhập | URL trang quản lý | Bị chuyển về trang đăng nhập | |

---

**Tổng hợp:** 39 test case (Register 16, Login 8, CRUD 15).

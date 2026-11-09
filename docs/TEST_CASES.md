## 1. Đăng ký (REGISTER)

| ID | Tên test case | Loại | Ưu tiên | Các bước | Dữ liệu test | Kết quả mong đợi | Kết quả thực tế | Trạng thái |
|---|---|:---:|:---:|---|---|---|---|:---:|
| TC_REG_01 | Đăng ký thành công | UI | H | 1. Mở trang đăng ký<br>2. Nhập thông tin hợp lệ<br>3. Bấm Đăng ký | user01 / user01@gmail.com / Abc@1234 / Abc@1234 | Đăng ký thành công, có thông báo | | |
| TC_REG_02 | Username đúng biên dưới (5 ký tự) | UT | M | Gọi validate username | abcde | Hợp lệ | | |
| TC_REG_03 | Username dưới biên (4 ký tự) | UT | H | Gọi validate username | abcd | Bị từ chối, báo lỗi độ dài | | |
| TC_REG_04 | Username đúng biên trên (20 ký tự) | UT | M | Gọi validate username | 20 ký tự bất kỳ | Hợp lệ | | |
| TC_REG_05 | Username vượt biên (21 ký tự) | UT | H | Gọi validate username | 21 ký tự bất kỳ | Bị từ chối, báo lỗi độ dài | | |
| TC_REG_06 | Username đã tồn tại | UI | H | Đăng ký bằng username đã có | admin / a@gmail.com / Abc@1234 / Abc@1234 | Báo "Tên đăng nhập đã tồn tại" | | |
| TC_REG_07 | Username để trống | UI | H | Bỏ trống username, bấm Đăng ký | "" | Báo lỗi bắt buộc nhập | | |
| TC_REG_08 | Email sai định dạng | UT | H | Gọi validate email | abc, abc@, @gmail.com | Bị từ chối, báo "Email không hợp lệ" | | |
| TC_REG_09 | Email để trống | UI | M | Bỏ trống email, bấm Đăng ký | "" | Báo lỗi bắt buộc nhập | | |
| TC_REG_10 | Mật khẩu đúng biên (8 ký tự, có ký tự đặc biệt) | UT | M | Gọi validate password | Abcdef@1 | Hợp lệ | | |
| TC_REG_11 | Mật khẩu dưới biên (7 ký tự) | UT | H | Gọi validate password | Abcde@1 | Bị từ chối, báo lỗi độ dài | | |
| TC_REG_12 | Mật khẩu đủ dài nhưng thiếu ký tự đặc biệt | UT | H | Gọi validate password | Abcdefg12 | Bị từ chối, yêu cầu ký tự đặc biệt | | |
| TC_REG_13 | Mật khẩu để trống | UI | H | Bỏ trống password, bấm Đăng ký | "" | Báo lỗi bắt buộc nhập | | |
| TC_REG_14 | Xác nhận mật khẩu không khớp | UI | H | Nhập password và confirmPassword khác nhau | Abc@1234 / Abc@12345 | Báo "Mật khẩu xác nhận không khớp" | | |
| TC_REG_15 | Để trống toàn bộ form | UI | M | Bấm Đăng ký khi chưa nhập gì | Tất cả trống | Báo lỗi ở từng trường bắt buộc | | |
| TC_REG_16 | Dữ liệu null | UT | M | Gọi hàm đăng ký với đối số null | null | `assertThrows` bắt được ngoại lệ nghiệp vụ | | |

## 2. Đăng nhập (LOGIN)

| ID | Tên test case | Loại | Ưu tiên | Các bước | Dữ liệu test | Kết quả mong đợi | Kết quả thực tế | Trạng thái |
|---|---|:---:|:---:|---|---|---|---|:---:|
| TC_LOGIN_01 | Đăng nhập thành công | UI | H | 1. Mở trang đăng nhập<br>2. Nhập đúng tài khoản<br>3. Bấm Đăng nhập | tài khoản hợp lệ | Chuyển về trang chủ | | |
| TC_LOGIN_02 | Sai mật khẩu | UI | H | Nhập đúng username, sai password | user01 / saimatkhau | Ở lại trang đăng nhập, báo lỗi | | |
| TC_LOGIN_03 | Tài khoản không tồn tại | UI | H | Nhập username chưa đăng ký | khongco123 / Abc@1234 | Báo lỗi đăng nhập | | |
| TC_LOGIN_04 | Để trống username | UI | H | Bỏ trống username | "" / Abc@1234 | Báo lỗi bắt buộc nhập | | |
| TC_LOGIN_05 | Để trống password | UI | H | Bỏ trống password | user01 / "" | Báo lỗi bắt buộc nhập | | |
| TC_LOGIN_06 | Để trống cả hai trường | UI | M | Bấm Đăng nhập khi chưa nhập | "" / "" | Báo lỗi nhập đủ thông tin | | |
| TC_LOGIN_07 | Chống SQL Injection | UI | H | Nhập chuỗi injection vào username | `' OR '1'='1` / bất kỳ | Đăng nhập thất bại, không lộ lỗi hệ thống | | |
| TC_LOGIN_08 | Nhiều tài khoản (Data-Driven) | UI | M | Dùng `@DataProvider` lặp qua nhiều cặp tài khoản | Bảng hợp lệ / không hợp lệ | Hợp lệ vào được, không hợp lệ bị từ chối | | |

## 3. CRUD Sản phẩm (PRODUCT)

| ID | Tên test case | Loại | Ưu tiên | Các bước | Dữ liệu test | Kết quả mong đợi | Kết quả thực tế | Trạng thái |
|---|---|:---:|:---:|---|---|---|---|:---:|
| TC_PRD_01 | Hiển thị danh sách sản phẩm | UI | H | Mở trang danh sách | Có sẵn dữ liệu | Bảng hiển thị đúng các sản phẩm | | |
| TC_PRD_02 | Thêm sản phẩm thành công | UI | H | 1. Bấm Thêm mới<br>2. Nhập thông tin hợp lệ<br>3. Bấm Lưu | Tên: Áo thun / Giá: 150000 / SL: 10 | Sản phẩm xuất hiện trong danh sách | | |
| TC_PRD_03 | Tên sản phẩm để trống | UI | H | Bỏ trống tên, bấm Lưu | Tên: "" | Báo lỗi bắt buộc, không tạo bản ghi | | |
| TC_PRD_04 | Tên sản phẩm bị trùng | UT | H | Thêm sản phẩm có tên đã tồn tại | Tên đã có | Bị từ chối, báo "Tên đã tồn tại" | | |
| TC_PRD_05 | Giá bán = 0 (biên) | UT | H | Nhập giá bằng 0 | Giá: 0 | Bị từ chối vì giá phải > 0 | | |
| TC_PRD_06 | Giá bán nhỏ nhất hợp lệ | UT | M | Nhập giá nhỏ nhất lớn hơn 0 | Giá: 1 | Hợp lệ | | |
| TC_PRD_07 | Giá bán âm | UT | H | Nhập giá âm | Giá: -1 | Bị từ chối | | |
| TC_PRD_08 | Số lượng = 0 (biên) | UT | M | Nhập số lượng bằng 0 | SL: 0 | Hợp lệ | | |
| TC_PRD_09 | Số lượng âm | UT | H | Nhập số lượng âm | SL: -1 | Bị từ chối vì SL phải >= 0 | | |
| TC_PRD_10 | Giá và số lượng không phải số | UI | M | Nhập chữ vào ô giá, số lượng | Giá: abc / SL: xyz | Báo lỗi định dạng số | | |
| TC_PRD_11 | Sửa sản phẩm thành công | UI | H | 1. Bấm Sửa<br>2. Đổi thông tin<br>3. Bấm Lưu | Giá mới hợp lệ | Danh sách và DB được cập nhật | | |
| TC_PRD_12 | Sửa sản phẩm không tồn tại | UT | M | Gọi cập nhật với ID không có | ID: 999999 | `assertThrows` bắt được ngoại lệ "không tìm thấy" | | |
| TC_PRD_13 | Xóa có xác nhận | UI | H | 1. Bấm Xóa<br>2. Chọn Đồng ý | Một sản phẩm bất kỳ | Sản phẩm bị xóa khỏi danh sách và DB | | |
| TC_PRD_14 | Hủy xóa | UI | M | 1. Bấm Xóa<br>2. Chọn Hủy | Một sản phẩm bất kỳ | Sản phẩm vẫn còn nguyên | | |
| TC_PRD_15 | Truy cập khi chưa đăng nhập | UI | H | Mở trực tiếp URL trang quản lý | URL trang quản lý | Bị chuyển về trang đăng nhập | | |

---

## 4. Tổng hợp

| Chức năng | Số test case | Ưu tiên cao (H) |
|---|:---:|:---:|
| Đăng ký | 16 | 9 |
| Đăng nhập | 8 | 6 |
| CRUD Sản phẩm | 15 | 9 |
| **Tổng** | **39** | **24** |

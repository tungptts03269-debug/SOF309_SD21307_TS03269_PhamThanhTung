# BỘ TEST CASE – SOF309

- Sinh viên: Phạm Thanh Tùng – TS03269 – Lớp SD21307
- URL kiểm thử: `http://localhost:8080`
- Loại test: **UT** = Unit Test (JUnit 5), **UI** = Selenium (TestNG)
- Độ ưu tiên: **H** = Cao, **M** = Trung bình, **L** = Thấp
- Nhóm: `smoke` = chạy trong smoke test

> Ghi chú: Dữ liệu và thông báo lỗi bên dưới là mẫu. Hãy sửa lại cho khớp với web thực tế của bạn trước khi nộp.

---

## 1. Chức năng Đăng nhập (LOGIN)

| ID | Tên test case | Loại | Ưu tiên | Nhóm | Điều kiện tiên quyết | Các bước thực hiện | Dữ liệu test | Kết quả mong đợi |
|---|---|:---:|:---:|:---:|---|---|---|---|
| TC_LOGIN_01 | Đăng nhập thành công | UI | H | smoke | Tài khoản `admin` đã tồn tại | 1. Mở trang đăng nhập<br>2. Nhập username, password<br>3. Bấm Đăng nhập | admin / 123456 | Chuyển tới trang chủ, hiển thị tên người dùng |
| TC_LOGIN_02 | Sai mật khẩu | UI | H | smoke | Tài khoản `admin` đã tồn tại | 1. Mở trang đăng nhập<br>2. Nhập đúng username, sai password<br>3. Bấm Đăng nhập | admin / sai_mk | Ở lại trang đăng nhập, hiện thông báo "Sai tên đăng nhập hoặc mật khẩu" |
| TC_LOGIN_03 | Tài khoản không tồn tại | UI | H | | Không | 1. Nhập username chưa đăng ký<br>2. Bấm Đăng nhập | khongtontai / 123456 | Hiện thông báo lỗi đăng nhập |
| TC_LOGIN_04 | Để trống username | UI | H | | Không | 1. Bỏ trống username, nhập password<br>2. Bấm Đăng nhập | "" / 123456 | Hiện thông báo "Vui lòng nhập tên đăng nhập" |
| TC_LOGIN_05 | Để trống password | UI | H | | Không | 1. Nhập username, bỏ trống password<br>2. Bấm Đăng nhập | admin / "" | Hiện thông báo "Vui lòng nhập mật khẩu" |
| TC_LOGIN_06 | Để trống cả hai trường | UI | M | | Không | 1. Bấm Đăng nhập khi chưa nhập gì | "" / "" | Hiện thông báo yêu cầu nhập đủ thông tin |
| TC_LOGIN_07 | Username có khoảng trắng đầu/cuối | UI | M | | Tài khoản `admin` tồn tại | 1. Nhập ` admin ` và password đúng<br>2. Bấm Đăng nhập | " admin " / 123456 | Hệ thống tự cắt khoảng trắng và đăng nhập thành công, hoặc báo lỗi rõ ràng |
| TC_LOGIN_08 | Chống SQL Injection | UI | H | | Không | 1. Nhập chuỗi injection vào username<br>2. Bấm Đăng nhập | `' OR '1'='1` / bất kỳ | Đăng nhập thất bại, không lộ lỗi hệ thống |
| TC_LOGIN_09 | Mật khẩu được ẩn | UI | L | | Không | 1. Nhập password vào ô mật khẩu | 123456 | Ký tự hiển thị dạng dấu chấm (type=password) |
| TC_LOGIN_10 | Kiểm tra nhiều tài khoản (Data-Driven) | UI | M | | Có danh sách tài khoản | 1. Dùng `@DataProvider` lặp qua từng cặp tài khoản<br>2. Đăng nhập | Bảng dữ liệu hợp lệ / không hợp lệ | Hợp lệ vào được hệ thống, không hợp lệ bị từ chối |

---

## 2. Chức năng Đăng ký (REGISTER)

| ID | Tên test case | Loại | Ưu tiên | Nhóm | Điều kiện tiên quyết | Các bước thực hiện | Dữ liệu test | Kết quả mong đợi |
|---|---|:---:|:---:|:---:|---|---|---|---|
| TC_REG_01 | Đăng ký thành công | UI | H | smoke | Username chưa tồn tại | 1. Mở trang đăng ký<br>2. Nhập đầy đủ thông tin hợp lệ<br>3. Bấm Đăng ký | user01 / 123456 / user01@gmail.com | Hiện thông báo thành công, chuyển về trang đăng nhập |
| TC_REG_02 | Username đã tồn tại | UI | H | | Tài khoản `admin` đã có | 1. Đăng ký bằng username đã có | admin / 123456 / a@gmail.com | Hiện thông báo "Tên đăng nhập đã tồn tại" |
| TC_REG_03 | Email sai định dạng | UT + UI | H | | Không | 1. Nhập email thiếu `@` hoặc thiếu tên miền | abc, abc@, @gmail.com | Hiện thông báo "Email không hợp lệ" |
| TC_REG_04 | Mật khẩu quá ngắn (biên dưới) | UT | H | | Không | 1. Nhập mật khẩu ngắn hơn quy định (giả sử tối thiểu 6 ký tự) | "12345" | Từ chối, báo lỗi độ dài mật khẩu |
| TC_REG_05 | Mật khẩu đúng biên tối thiểu | UT | M | | Không | 1. Nhập mật khẩu đúng 6 ký tự | "123456" | Chấp nhận |
| TC_REG_06 | Mật khẩu quá dài (biên trên) | UT | M | | Không | 1. Nhập mật khẩu dài hơn giới hạn (giả sử tối đa 32 ký tự) | 33 ký tự | Từ chối, báo lỗi độ dài mật khẩu |
| TC_REG_07 | Xác nhận mật khẩu không khớp | UI | H | | Không | 1. Nhập password và confirm khác nhau | 123456 / 654321 | Hiện thông báo "Mật khẩu xác nhận không khớp" |
| TC_REG_08 | Để trống các trường bắt buộc | UI | H | | Không | 1. Bấm Đăng ký khi chưa nhập gì | Tất cả để trống | Hiện thông báo lỗi ở từng trường bắt buộc |
| TC_REG_09 | Username chứa ký tự đặc biệt | UT | M | | Không | 1. Nhập username có ký tự không cho phép | user@#$ | Từ chối, báo lỗi định dạng |
| TC_REG_10 | Ném ngoại lệ khi dữ liệu null | UT | M | | Không | 1. Gọi hàm đăng ký với đối số null | null | `assertThrows` bắt được ngoại lệ nghiệp vụ (ví dụ `IllegalArgumentException`) |

---

## 3. Chức năng Quản lý – CRUD

> Thay "đối tượng" bằng thực thể trong web của bạn (sản phẩm, sinh viên, sách...).

| ID | Tên test case | Loại | Ưu tiên | Nhóm | Điều kiện tiên quyết | Các bước thực hiện | Dữ liệu test | Kết quả mong đợi |
|---|---|:---:|:---:|:---:|---|---|---|---|
| TC_CRUD_01 | Hiển thị danh sách | UI | H | smoke | Đã đăng nhập, có dữ liệu | 1. Mở trang danh sách | Không | Bảng hiển thị đúng các bản ghi hiện có |
| TC_CRUD_02 | Thêm mới thành công | UI | H | smoke | Đã đăng nhập | 1. Bấm Thêm mới<br>2. Nhập thông tin hợp lệ<br>3. Bấm Lưu | Dữ liệu hợp lệ | Bản ghi mới xuất hiện trong danh sách, có thông báo thành công |
| TC_CRUD_03 | Thêm mới thiếu trường bắt buộc | UI | H | | Đã đăng nhập | 1. Bấm Lưu khi bỏ trống trường bắt buộc | Trường bắt buộc để trống | Hiện thông báo lỗi, không tạo bản ghi |
| TC_CRUD_04 | Thêm mới trùng dữ liệu | UT + UI | M | | Đã có bản ghi cùng mã | 1. Thêm bản ghi trùng mã | Mã đã tồn tại | Từ chối, báo "Dữ liệu đã tồn tại" |
| TC_CRUD_05 | Kiểm tra giá trị biên của số | UT | H | | Không | 1. Nhập giá trị: -1, 0, 1, giá trị lớn nhất cho phép, lớn nhất + 1 | -1, 0, 1, max, max+1 | Giá trị âm và vượt max bị từ chối; 0 (nếu hợp lệ) và 1, max được chấp nhận |
| TC_CRUD_06 | Cập nhật thành công | UI | H | | Có bản ghi để sửa | 1. Bấm Sửa ở một bản ghi<br>2. Đổi thông tin<br>3. Bấm Lưu | Dữ liệu mới hợp lệ | Thông tin được cập nhật đúng trong danh sách |
| TC_CRUD_07 | Cập nhật bản ghi không tồn tại | UT | M | | Không | 1. Gọi cập nhật với ID không có | ID = 999999 | `assertThrows` bắt được ngoại lệ "không tìm thấy" |
| TC_CRUD_08 | Xóa thành công | UI | H | | Có bản ghi để xóa | 1. Bấm Xóa<br>2. Xác nhận | Một bản ghi bất kỳ | Bản ghi biến mất khỏi danh sách |
| TC_CRUD_09 | Hủy thao tác xóa | UI | L | | Có bản ghi | 1. Bấm Xóa<br>2. Chọn Hủy ở hộp xác nhận | Một bản ghi bất kỳ | Bản ghi vẫn còn nguyên |
| TC_CRUD_10 | Tìm kiếm theo từ khóa | UI | M | | Có nhiều bản ghi | 1. Nhập từ khóa vào ô tìm kiếm<br>2. Bấm Tìm | Từ khóa có trong dữ liệu | Chỉ hiển thị các bản ghi khớp từ khóa |
| TC_CRUD_11 | Tìm kiếm không có kết quả | UI | L | | Có dữ liệu | 1. Nhập từ khóa không tồn tại | "zzzzzz" | Hiện thông báo "Không có dữ liệu" |
| TC_CRUD_12 | Truy cập khi chưa đăng nhập | UI | H | | Chưa đăng nhập | 1. Mở trực tiếp URL trang quản lý | URL trang quản lý | Bị chuyển về trang đăng nhập |

---

## 4. Tổng hợp

| Chức năng | Số test case | Ưu tiên cao (H) | Nhóm smoke |
|---|:---:|:---:|:---:|
| Đăng nhập | 10 | 6 | 2 |
| Đăng ký | 10 | 6 | 1 |
| CRUD | 12 | 8 | 3 |
| **Tổng** | **32** | **20** | **6** |

## 5. Bảng ghi nhận kết quả chạy

| ID | Kết quả thực tế | Trạng thái (Pass/Fail) | Ngày chạy | Ghi chú / Link ảnh lỗi |
|---|---|:---:|---|---|
| TC_LOGIN_01 | | | | |
| TC_LOGIN_02 | | | | |
| ... | | | | |

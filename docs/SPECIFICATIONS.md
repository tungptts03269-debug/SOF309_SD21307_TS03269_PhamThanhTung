# ĐẶC TẢ NGHIỆP VỤ – WEB QUẢN LÝ SẢN PHẨM

> ⚠️ **Lưu ý:** Các URL, tên trường và ràng buộc bên dưới là giả định theo ví dụ trong cẩm nang SOF309.
> Hãy đối chiếu với code thật (Entity, Controller, thông báo lỗi) và sửa lại trước khi nộp.

## 1. Thông tin chung
- **Công nghệ:** Java, Spring Boot, Thymeleaf, MySQL
- **Địa chỉ chạy local:** `http://localhost:8080`
- **Vai trò:** Khách (chưa đăng nhập), Người dùng (đã đăng nhập)

## 2. Danh sách màn hình / URL

| STT | URL | Phương thức | Mô tả | Yêu cầu đăng nhập |
|-----|-----|-------------|-------|-------------------|
| 1 | `/register` | GET, POST | Đăng ký tài khoản | Không |
| 2 | `/login` | GET, POST | Đăng nhập | Không |
| 3 | `/logout` | GET | Đăng xuất | Có |
| 4 | `/products` | GET | Xem danh sách sản phẩm | Có |
| 5 | `/products/add` | GET, POST | Thêm sản phẩm | Có |
| 6 | `/products/edit/{id}` | GET, POST | Sửa sản phẩm | Có |
| 7 | `/products/delete/{id}` | GET/POST | Xóa sản phẩm | Có |

## 3. Chức năng Đăng ký (`/register`)

### 3.1 Trường dữ liệu và ràng buộc

| Trường | Kiểu | Bắt buộc | Ràng buộc | Thông báo lỗi |
|--------|------|----------|-----------|---------------|
| username | String | Có | 5–20 ký tự; chỉ gồm chữ, số, dấu `_`; không khoảng trắng; **không trùng** | "Username không được để trống" / "Username phải từ 5 đến 20 ký tự" / "Username đã tồn tại" |
| email | String | Có | Đúng định dạng email; không trùng | "Email không hợp lệ" |
| password | String | Có | Tối thiểu 8 ký tự, tối đa 30; có ít nhất 1 chữ và 1 số | "Mật khẩu phải từ 8 ký tự" |
| confirmPassword | String | Có | Phải trùng `password` | "Mật khẩu xác nhận không khớp" |

### 3.2 Luồng nghiệp vụ
- Dữ liệu hợp lệ → lưu tài khoản, chuyển về `/login`, hiển thị "Đăng ký thành công".
- Dữ liệu không hợp lệ → ở lại `/register`, hiển thị lỗi cạnh trường sai, không lưu DB.
- Username đã tồn tại → báo lỗi trùng lặp (ném `DuplicateUserException` ở tầng Service).

## 4. Chức năng Đăng nhập (`/login`)

| Trường | Bắt buộc | Ràng buộc |
|--------|----------|-----------|
| username | Có | Không để trống |
| password | Có | Không để trống |

- Đúng tài khoản → chuyển sang `/products`.
- Sai username hoặc password → ở lại `/login`, hiển thị "Sai tên đăng nhập hoặc mật khẩu".
- Để trống trường → hiển thị lỗi bắt buộc nhập.
- Truy cập `/products` khi chưa đăng nhập → chuyển hướng về `/login`.

## 5. Chức năng Quản lý sản phẩm (`/products`)

### 5.1 Trường dữ liệu và ràng buộc

| Trường | Kiểu | Bắt buộc | Ràng buộc | Thông báo lỗi |
|--------|------|----------|-----------|---------------|
| name | String | Có | 3–100 ký tự | "Tên sản phẩm phải từ 3 đến 100 ký tự" |
| price | Số thực | Có | > 0 và ≤ 1.000.000.000 | "Giá phải lớn hơn 0" |
| quantity | Số nguyên | Có | 0 ≤ quantity ≤ 10.000 | "Số lượng phải từ 0 đến 10000" |
| description | String | Không | Tối đa 500 ký tự | "Mô tả tối đa 500 ký tự" |

### 5.2 Luồng nghiệp vụ
- **Thêm:** dữ liệu hợp lệ → lưu, quay về danh sách, hiện "Thêm sản phẩm thành công".
- **Sửa:** tải đúng dữ liệu cũ lên form; lưu hợp lệ → cập nhật.
- **Xóa:** xóa theo id; id không tồn tại → báo lỗi / trang 404.
- **Danh sách:** hiển thị bảng gồm id, tên, giá, số lượng và nút Sửa/Xóa.

## 6. Yêu cầu phi chức năng
- Thời gian phản hồi một trang ≤ 3 giây.
- Hàm xử lý/validate dữ liệu không được chạy quá 2 giây (phục vụ Timeout Test).
- Mật khẩu không được hiển thị rõ trên giao diện.

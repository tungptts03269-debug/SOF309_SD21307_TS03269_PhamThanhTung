# ĐẶC TẢ NGHIỆP VỤ – PHONGKHAMONLINE

## 1. Thông tin dự án
- Tên dự án: PhongKhamOnline
- Loại ứng dụng: Java Web sử dụng Servlet/JSP.
- Đóng gói: Maven WAR.
- Java: 17.
- API web: Jakarta Servlet 6.0; Jakarta Persistence 3.1; Hibernate 6.5.2.
- Cơ sở dữ liệu: Microsoft SQL Server (theo dependency JDBC trong `pom.xml`).
- Giao diện: JSP trong `src/main/webapp/`.
- Mã nguồn: package `poly.com.servlet`, `poly.com.dao`, `poly.com.entity`, `poly.com.utils`.

## 2. Vai trò người dùng
Các vai trò được thể hiện trong mã nguồn đăng nhập gồm:
- Admin: chuyển đến `/indexadmin.jsp`.
- Bác sĩ: chuyển đến `/doctor`.
- Bệnh nhân/người dùng khác: chuyển đến `/index.jsp`.

## 3. Các luồng nghiệp vụ được xác nhận từ mã nguồn

### 3.1. Đăng nhập – `/login`
**Phương thức:** POST (GET chuyển về `/index.jsp`).

**Dữ liệu đầu vào:**
| Tên tham số | Ý nghĩa | Quy tắc đang được kiểm tra |
|---|---|---|
| `username` | Tên đăng nhập | Không null, không rỗng sau trim |
| `password` | Mật khẩu | Không null, không rỗng sau trim |

**Luồng xử lý:**
1. Đọc `username`, `password`.
2. Nếu thiếu một trong hai: đặt request attribute `error` và forward `/index.jsp`.
3. Gọi `TaiKhoanDAO.login(username.trim(), password.trim())`.
4. Nếu không tìm thấy tài khoản: đặt lỗi đăng nhập và forward `/index.jsp`.
5. Nếu tìm thấy: tạo session, thời gian không hoạt động 30 phút; lưu `user`, `userId`, `username`, `fullName`, `role`.
6. Ghi lịch sử đăng nhập và nhật ký hoạt động.
7. Chuyển hướng theo vai trò: Admin → `/indexadmin.jsp`; Bác sĩ → `/doctor`; vai trò còn lại → `/index.jsp`.

### 3.2. Đăng ký – `/register`
**Phương thức:** POST.

**Dữ liệu đầu vào:**
| Tên tham số | Ý nghĩa | Quy tắc đang được kiểm tra |
|---|---|---|
| `tenDangNhap` | Tên đăng nhập | Bắt buộc |
| `matKhau` | Mật khẩu | Bắt buộc |
| `confirmPassword` | Xác nhận mật khẩu | Bắt buộc, phải trùng mật khẩu |
| `hoTen` | Họ tên | Bắt buộc |
| `email` | Email | Bắt buộc, chỉ chấp nhận định dạng `@gmail.com` theo regex hiện tại |
| `soDienThoai` | Số điện thoại | Bắt buộc, đúng 10 chữ số và bắt đầu bằng `0` |

**Luồng xử lý:**
1. Kiểm tra các trường không null/rỗng.
2. Kiểm tra mật khẩu và xác nhận mật khẩu trùng nhau.
3. Kiểm tra email theo regex `^[A-Za-z0-9+_.-]+@gmail\\.com$`.
4. Kiểm tra số điện thoại theo regex `^0\\d{9}$`.
5. Kiểm tra trùng tên đăng nhập, email, số điện thoại qua `TaiKhoanDAO`.
6. Nếu hợp lệ: tạo tài khoản vai trò `Bệnh nhân`, tạo hồ sơ bệnh nhân, thông báo cho bệnh nhân và thông báo admin.
7. Tạo session và chuyển hướng về `/index.jsp`.
8. Nếu phát sinh lỗi trong quá trình tạo dữ liệu: hiển thị `registerError` tại `/index.jsp`.

**Lưu ý:** Đặc tả này ghi nhận đúng quy tắc hiện có trong mã nguồn; không tự bổ sung quy tắc độ dài username/password vì servlet hiện tại chưa kiểm tra các giới hạn đó.

### 3.3. Đặt lịch khám – `/datlich`
- GET: yêu cầu đã đăng nhập; nếu chưa có session hoặc session không có `user`, chuyển về `/index.jsp`. Nếu đã đăng nhập, nạp danh sách bác sĩ và dịch vụ rồi forward `/datlich.jsp`.
- POST: yêu cầu đăng nhập; lấy hồ sơ bệnh nhân từ tài khoản, đọc mã bác sĩ, mã dịch vụ, ngày khám, giờ khám, lý do và ghi chú; tạo lịch khám trạng thái `Chờ xác nhận`; ghi thông báo bệnh nhân, thông báo admin và nhật ký hoạt động; chuyển đến `/lichkham`.
- Nếu lỗi: lưu `error` trong session và chuyển lại `/datlich`.

## 4. Danh mục endpoint được khai báo bằng `@WebServlet`
| Endpoint | Servlet |
|---|---|
| `/` | TrangChuServlet |
| `/login` | LoginServlet |
| `/register` | RegisterServlet |
| `/logout` | LogoutServlet |
| `/logoutadmin` | LogoutAdminServlet |
| `/datlich` | DatLichServlet |
| `/lichkham` | LichKhamServlet |
| `/admin/bacsi` | BacSiServlet |
| `/admin/benhnhan` | BenhNhanServlet |
| `/chuyenkhoa` | ChuyenKhoaServlet |
| `/dichvu` | DichVuServlet |
| `/dashboard` | DashboardServlet |
| `/hoadon` | HoaDonServlet |
| `/chitiethoadon` | ChiTietHoaDonServlet |
| `/payment` | PaymentServlet |
| `/paymentconfirm` | PaymentConfirmServlet |
| `/paymentstatus` | PaymentStatusServlet |
| `/lienhe` | LienHeServlet |
| `/danhgiabacsi` | DanhGiaServlet |
| `/forgotpassword` | ForgotPasswordServlet |
| `/verifyotp` | VerifyOTPServlet |
| `/resetpassword` | ResetPasswordServlet |
| `/thongbao` | ThongBaoServlet |
| `/admin/thongbao` | ThongBaoAdminServlet |
| `/nhatkyhoatdong` | NhatKyHoatDongServlet |
| `/lichsudangnhap` | LichSuDangNhapServlet |
| `/yeucauhuylich` | YeuCauHuyLichServlet |

Các endpoint quản trị khác có trong source: `/quanlydanhgia`, `/quanlylienhe`, `/quanlythanhtoan`, `/quanlyyeucauhuylich`, `/thanhtoanngay`, `/thanhtoantaiphongkham`, `/inhoadon`.

## 5. Phạm vi kiểm thử ưu tiên
- Đăng nhập: dữ liệu thiếu, tài khoản/mật khẩu không đúng, đăng nhập thành công và điều hướng theo vai trò.
- Đăng ký: trường bắt buộc, xác nhận mật khẩu, định dạng Gmail, số điện thoại, dữ liệu trùng.
- Đặt lịch: chưa đăng nhập, đã đăng nhập, dữ liệu bác sĩ/dịch vụ/ngày giờ, xử lý lỗi.
- Các chức năng còn lại sẽ được mở rộng test case sau khi rà soát đầy đủ từng Servlet và JSP tương ứng.

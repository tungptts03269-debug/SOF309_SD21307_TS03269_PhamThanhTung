# BỘ TEST CASE CHI TIẾT

Ký hiệu: **EP** = Phân vùng tương đương, **BVA** = Giá trị biên, **EX** = Ngoại lệ.
Cột "Mức": **C** = Controller/Unit, **U** = UI Selenium.

## A. Đăng ký (`/register`)

| ID | Mô tả | Kỹ thuật | Dữ liệu vào | Kết quả mong đợi | Mức | Ưu tiên |
|----|-------|----------|-------------|------------------|-----|---------|
| REG-01 | Đăng ký hợp lệ | EP | user=`validUser`, email=`a@b.com`, pass=`Pass@1234`, confirm khớp | Thành công, chuyển `/login` | C,U | High |
| REG-02 | Username để trống | EP | user=`""` | Lỗi "Username không được để trống" | C,U | High |
| REG-03 | Username 4 ký tự | BVA | `abcd` | Lỗi "5 đến 20 ký tự" | C | High |
| REG-04 | Username 5 ký tự | BVA | `abcde` | Thành công | C | High |
| REG-05 | Username 6 ký tự | BVA | `abcdef` | Thành công | C | Medium |
| REG-06 | Username 19 ký tự | BVA | `a`×19 | Thành công | C | Medium |
| REG-07 | Username 20 ký tự | BVA | `a`×20 | Thành công | C | High |
| REG-08 | Username 21 ký tự | BVA | `a`×21 | Lỗi "5 đến 20 ký tự" | C | High |
| REG-09 | Username có ký tự đặc biệt | EP | `user@#$1` | Lỗi định dạng username | C | Medium |
| REG-10 | Username có khoảng trắng | EP | `user name` | Lỗi định dạng username | C | Medium |
| REG-11 | Password 7 ký tự | BVA | `Pass123` | Lỗi "Mật khẩu phải từ 8 ký tự" | C | High |
| REG-12 | Password 8 ký tự | BVA | `Pass1234` | Thành công | C | High |
| REG-13 | Password 9 ký tự | BVA | `Pass12345` | Thành công | C | Medium |
| REG-14 | Password 31 ký tự | BVA | 31 ký tự hợp lệ | Lỗi độ dài tối đa | C | Medium |
| REG-15 | Password chỉ có chữ | EP | `abcdefgh` | Lỗi thiếu chữ số | C | Medium |
| REG-16 | Password để trống | EP | `""` | Lỗi bắt buộc nhập | C,U | High |
| REG-17 | Confirm không khớp | EP | pass=`Pass1234`, confirm=`Pass9999` | Lỗi "không khớp" | C,U | High |
| REG-18 | Email sai định dạng | EP | `abc.com` | Lỗi "Email không hợp lệ" | C | Medium |
| REG-19 | Email để trống | EP | `""` | Lỗi bắt buộc nhập | C | Medium |
| REG-20 | Username đã tồn tại | EX | `admin` (đã có) | Ném `DuplicateUserException`, lỗi "đã tồn tại" | C,U | High |
| REG-21 | Hàm validate chạy nhanh | Timeout | Bộ dữ liệu bất kỳ | Hoàn thành ≤ 2 giây | C | Low |

## B. Đăng nhập (`/login`)

| ID | Mô tả | Kỹ thuật | Dữ liệu vào | Kết quả mong đợi | Mức | Ưu tiên |
|----|-------|----------|-------------|------------------|-----|---------|
| LOG-01 | Đăng nhập đúng | EP | tài khoản hợp lệ | Chuyển `/products` | C,U | High |
| LOG-02 | Sai mật khẩu | EP | user đúng, pass sai | Lỗi "Sai tên đăng nhập hoặc mật khẩu" | C,U | High |
| LOG-03 | Sai username | EP | user không tồn tại | Lỗi như LOG-02 | C,U | High |
| LOG-04 | Username trống | EP | `""` | Lỗi bắt buộc nhập | C,U | High |
| LOG-05 | Password trống | EP | `""` | Lỗi bắt buộc nhập | C,U | High |
| LOG-06 | Cả hai trống | EP | `""`, `""` | Lỗi bắt buộc nhập | U | Medium |
| LOG-07 | Chuỗi chứa SQL | EP | `' OR '1'='1` | Không đăng nhập được | C | Medium |
| LOG-08 | Vào `/products` khi chưa đăng nhập | EP | không | Chuyển về `/login` | U | High |
| LOG-09 | Đăng xuất | EP | đã đăng nhập → `/logout` | Về `/login`, không vào được `/products` | U | Medium |

## C. Sản phẩm (`/products`)

| ID | Mô tả | Kỹ thuật | Dữ liệu vào | Kết quả mong đợi | Mức | Ưu tiên |
|----|-------|----------|-------------|------------------|-----|---------|
| PRD-01 | Thêm sản phẩm hợp lệ | EP | name=`Laptop`, price=`1500`, qty=`10` | Thêm thành công, hiện trong bảng | C,U | High |
| PRD-02 | Tên trống | EP | `""` | Lỗi "Tên sản phẩm..." | C,U | High |
| PRD-03 | Tên 2 ký tự | BVA | `ab` | Lỗi độ dài | C | High |
| PRD-04 | Tên 3 ký tự | BVA | `abc` | Thành công | C | High |
| PRD-05 | Tên 100 ký tự | BVA | `a`×100 | Thành công | C | Medium |
| PRD-06 | Tên 101 ký tự | BVA | `a`×101 | Lỗi độ dài | C | Medium |
| PRD-07 | Giá âm | EX | `-1` | Lỗi "Giá phải lớn hơn 0" | C,U | High |
| PRD-08 | Giá bằng 0 | BVA | `0` | Lỗi "Giá phải lớn hơn 0" | C | High |
| PRD-09 | Giá nhỏ nhất hợp lệ | BVA | `0.01` | Thành công | C | High |
| PRD-10 | Giá tối đa | BVA | `1000000000` | Thành công | C | Medium |
| PRD-11 | Giá vượt tối đa | BVA | `1000000001` | Lỗi giá vượt giới hạn | C | Medium |
| PRD-12 | Giá không phải số | EP | `abc` | Lỗi định dạng số | C,U | Medium |
| PRD-13 | Số lượng âm | BVA | `-1` | Lỗi "0 đến 10000" | C | High |
| PRD-14 | Số lượng bằng 0 | BVA | `0` | Thành công | C | Medium |
| PRD-15 | Số lượng 10000 | BVA | `10000` | Thành công | C | Medium |
| PRD-16 | Số lượng 10001 | BVA | `10001` | Lỗi "0 đến 10000" | C | Medium |
| PRD-17 | Mô tả 501 ký tự | BVA | `a`×501 | Lỗi "tối đa 500 ký tự" | C | Low |
| PRD-18 | Sửa sản phẩm hợp lệ | EP | đổi giá thành `2000` | Cập nhật thành công | C,U | High |
| PRD-19 | Sửa với giá không hợp lệ | EP | giá `-5` | Lỗi, không cập nhật | C | Medium |
| PRD-20 | Xóa sản phẩm tồn tại | EP | id hợp lệ | Xóa thành công, biến khỏi bảng | C,U | High |
| PRD-21 | Xóa id không tồn tại | EX | id=`99999` | Báo lỗi / 404, không crash | C | Medium |
| PRD-22 | Hiển thị danh sách | EP | đã đăng nhập | Bảng hiển thị đúng dữ liệu | U | Medium |

## D. Thống kê
- Đăng ký: 21 · Đăng nhập: 9 · Sản phẩm: 22 · **Tổng: 52 Test Case**
- Phân nhóm `smoke` (dùng cho Tuần 4–5): REG-01, LOG-01, LOG-02, LOG-08, PRD-01, PRD-07, PRD-20.

# KẾ HOẠCH KIỂM THỬ (TEST PLAN)

## 1. Giới thiệu
- **Dự án:** Web quản lý sản phẩm (Spring Boot + Thymeleaf + MySQL)
- **Môn học:** SOF309 – Kiểm thử nâng cao cùng AI
- **Tài liệu tham chiếu:** `docs/SPECIFICATIONS.md`

## 2. Mục tiêu
- Kiểm chứng các chức năng Đăng ký, Đăng nhập, Quản lý sản phẩm đúng đặc tả.
- Phát hiện lỗi validation tại các giá trị biên.
- Xây dựng bộ kiểm thử tự động tái sử dụng được (JUnit 5, Selenium POM, TestNG, CI/CD).

## 3. Phạm vi
| Trong phạm vi | Ngoài phạm vi |
|---------------|---------------|
| Validation form Đăng ký, Đăng nhập, Sản phẩm | Kiểm thử hiệu năng tải lớn |
| Luồng CRUD sản phẩm | Kiểm thử bảo mật chuyên sâu |
| Chuyển hướng khi chưa đăng nhập | Tương thích đa trình duyệt (chỉ Chrome) |

## 4. Kỹ thuật thiết kế Test Case
1. **Phân vùng tương đương (Equivalence Partitioning):** chia dữ liệu thành lớp hợp lệ và không hợp lệ, mỗi lớp chọn một đại diện.
2. **Phân tích giá trị biên (Boundary Value Analysis):** chọn giá trị tại và quanh biên: min−1, min, min+1, max−1, max, max+1.
3. **Kiểm thử ngoại lệ:** trùng dữ liệu, id không tồn tại, giá âm.

### Bảng biên tổng hợp
| Trường | Hợp lệ | Biên cần test |
|--------|--------|---------------|
| username (5–20) | 5–20 ký tự | 4, 5, 6, 19, 20, 21 |
| password (8–30) | 8–30 ký tự | 7, 8, 9, 29, 30, 31 |
| name (3–100) | 3–100 ký tự | 2, 3, 4, 99, 100, 101 |
| price (>0, ≤1 tỷ) | 0 < giá ≤ 1.000.000.000 | -1, 0, 0.01, 1.000.000.000, 1.000.000.001 |
| quantity (0–10000) | 0–10000 | -1, 0, 1, 9999, 10000, 10001 |

## 5. Các mức kiểm thử và công cụ
| Mức | Công cụ | Nội dung | Tuần |
|-----|---------|----------|------|
| Unit Test Controller | JUnit 5, Mockito/MockMvc | Validation, ngoại lệ, timeout | 2 |
| UI Automation | Selenium WebDriver + POM | Luồng đăng nhập, CRUD | 3 |
| Data-Driven / Nhóm | TestNG (`@DataProvider`, groups) | Nhiều bộ tài khoản, nhóm smoke | 4 |
| CI/CD | GitHub Actions | Chạy tự động, upload báo cáo | 5 |

## 6. Môi trường kiểm thử
- JDK 17, Maven, Chrome (bản mới), MySQL local
- Hệ điều hành: Windows (local), Ubuntu (GitHub Actions)

## 7. Tiêu chí
- **Bắt đầu:** web chạy ổn định ở local, đã có đặc tả và Test Case.
- **Hoàn thành:** `mvn test` đạt 100% pass; pipeline GitHub Actions màu xanh.
- **Mức ưu tiên:** High (đăng nhập, đăng ký, thêm sản phẩm) → Medium (sửa, xóa) → Low (giao diện).

## 8. Rủi ro
| Rủi ro | Giảm thiểu |
|--------|------------|
| Locator thay đổi khi sửa giao diện | Dùng POM, ưu tiên `id`/`name` |
| Test không ổn định do chờ trang | Dùng `WebDriverWait`, không dùng `Thread.sleep()` |
| Dữ liệu test dính nhau | Dùng dữ liệu riêng cho mỗi test, dọn sau khi chạy |

## 9. Sản phẩm bàn giao
`TEST_PLAN.md`, `TEST_CASES.md`, mã test, `testng.xml`, ảnh chụp lỗi, báo cáo CI, nhật ký AI.

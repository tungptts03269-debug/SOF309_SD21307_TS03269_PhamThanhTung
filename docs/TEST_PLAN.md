# KẾ HOẠCH KIỂM THỬ (TEST PLAN)

- **Dự án:** Website bán hàng trực tuyến
- **Sinh viên:** Phạm Thanh Tùng - TS03269
- **Lớp:** SD21307
- **Môn học:** SOF309 - Kiểm thử nâng cao cùng AI
- **Giảng viên hướng dẫn:** ThS. Trần Quang Bình

---

## 1. MỤC TIÊU KIỂM THỬ

- Kiểm thử logic backend (Unit Test với JUnit 5 cho Controller), gồm kiểm tra giá trị biên bằng `@ParameterizedTest` + `@CsvSource` và kiểm tra ngoại lệ nghiệp vụ bằng `assertThrows()`.
- Kiểm thử giao diện tự động (Automation UI Test với Selenium WebDriver), áp dụng Page Object Model, `WebDriverWait`, TestNG Groups, `@DataProvider` và tự chụp ảnh màn hình khi test fail.
- Tích hợp kiểm thử tự động với GitHub Actions CI/CD, chạy Chrome Headless và lưu báo cáo, ảnh lỗi lên Artifacts.

## 2. PHẠM VI KIỂM THỬ

**In-Scope (trong phạm vi):**

- Luồng **Đăng ký**: kiểm tra `username` (5-20 ký tự), `email`, `password` (tối thiểu 8 ký tự, có ký tự đặc biệt), `confirmPassword`.
- Luồng **Đăng nhập**: đúng tài khoản chuyển về trang chủ, sai tài khoản báo lỗi.
- Thao tác **CRUD Sản phẩm** trên giao diện: thêm mới, xem danh sách, sửa, xóa (có xác nhận); kiểm tra tên sản phẩm, giá bán, số lượng.

**Out-of-Scope (ngoài phạm vi):**

- Kiểm thử hiệu năng (Performance Test).
- Kiểm thử bảo mật chuyên sâu (chỉ kiểm tra cơ bản như chuỗi SQL Injection ở form đăng nhập).

## 3. TIÊU CHÍ HOÀN THÀNH

- 100% test script thực thi pass.
- Pipeline GitHub Actions báo xanh (Success).

---

## 4. CHIẾN LƯỢC VÀ CÔNG CỤ

| Cấp độ kiểm thử | Công cụ | Nội dung |
|---|---|---|
| Unit Test | JUnit 5, Maven | Kiểm thử Controller: dữ liệu biên, ngoại lệ nghiệp vụ |
| UI Test | Selenium WebDriver 4, TestNG | Kiểm thử luồng người dùng trên Chrome, Page Object Model |
| CI/CD | GitHub Actions | Tự chạy test khi push code, Chrome Headless trên Ubuntu |
| Hỗ trợ AI | Claude | Sinh test case, sinh code test, phân tích lỗi, code review |

## 5. MÔI TRƯỜNG KIỂM THỬ

- Ngôn ngữ và nền tảng: Java 17, Spring Boot, MySQL hoặc H2.
- Công cụ build: Maven.
- Trình duyệt: Google Chrome (bản mới nhất).
- Địa chỉ chạy ứng dụng: `http://localhost:8080`.
- Môi trường CI: Ubuntu (GitHub Actions).

## 6. LỊCH TRÌNH THỰC HIỆN

| Tuần | Công việc | Sản phẩm |
|---|---|---|
| Tuần 1 | Đưa web vào repo, viết đặc tả, Test Plan, Test Case | `SPECIFICATIONS.md`, `TEST_PLAN.md`, `TEST_CASES.md` |
| Tuần 2 | Viết Unit Test Controller | `src/test/java/.../controllers/` |
| Tuần 3 | Xây dựng Selenium Page Object Model | `src/test/java/.../ui/pages/` |
| Tuần 4 | Nâng cấp TestNG: Groups, DataProvider, chụp ảnh khi fail | `testng.xml`, `screenshots/` |
| Tuần 5 | Cấu hình GitHub Actions, phân tích lỗi với AI | `.github/workflows/test.yml` |
| Tuần 6 | Code review, rà soát nhật ký AI, chuẩn bị bảo vệ | `docs/ai-logs/`, slide |

## 7. SẢN PHẨM BÀN GIAO

- Tài liệu: `SPECIFICATIONS.md`, `TEST_PLAN.md`, `TEST_CASES.md`.
- Mã kiểm thử: Unit Test (JUnit 5) và UI Test (Selenium + TestNG).
- Cấu hình CI/CD: `.github/workflows/test.yml`.
- Minh chứng sử dụng AI: các file trong `docs/ai-logs/`.

## 8. RỦI RO VÀ BIỆN PHÁP

| Rủi ro | Biện pháp |
|---|---|
| Selenium test không ổn định do trang tải chậm | Dùng `WebDriverWait` thay cho `Thread.sleep` |
| Dữ liệu test bị trùng giữa các lần chạy | Dùng dữ liệu ngẫu nhiên hoặc dọn dữ liệu trước và sau khi test |
| Test chạy local pass nhưng fail trên CI | Chạy Chrome Headless và cấu hình kích thước cửa sổ cố định |

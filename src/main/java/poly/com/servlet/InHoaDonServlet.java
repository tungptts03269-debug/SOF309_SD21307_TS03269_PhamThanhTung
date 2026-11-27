package poly.com.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import poly.com.dao.ThanhToanDAO;
import poly.com.entity.ThanhToan;

@WebServlet("/inhoadon")
public class InHoaDonServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final ThanhToanDAO thanhToanDAO = new ThanhToanDAO();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // =====================================================
        // 1. LẤY MÃ THANH TOÁN
        // =====================================================

        String idStr = request.getParameter("id");

        if (idStr == null || idStr.trim().isEmpty()) {

            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Không tìm thấy mã thanh toán."
            );

            return;
        }

        int maThanhToan;

        try {

            maThanhToan = Integer.parseInt(idStr.trim());

        } catch (NumberFormatException e) {

            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Mã thanh toán không hợp lệ."
            );

            return;
        }

        // =====================================================
        // 2. TÌM HÓA ĐƠN
        // =====================================================

        ThanhToan thanhToan =
                thanhToanDAO.findByMaThanhToan(maThanhToan);

        if (thanhToan == null) {

            response.sendError(
                    HttpServletResponse.SC_NOT_FOUND,
                    "Không tìm thấy hóa đơn có mã: "
                            + maThanhToan
            );

            return;
        }

        // =====================================================
        // 3. CẤU HÌNH PDF
        // =====================================================

        response.setContentType("application/pdf");

        response.setHeader(
                "Content-Disposition",
                "inline; filename=\"hoadon_"
                        + maThanhToan
                        + ".pdf\""
        );

        // =====================================================
        // 4. TẠO DOCUMENT
        // =====================================================

        Document document = new Document(
                PageSize.A4,
                45,
                45,
                45,
                45
        );

        try {

            PdfWriter.getInstance(
                    document,
                    response.getOutputStream()
            );

            document.open();

            // =================================================
            // 5. FONT
            // =================================================

            /*
             * Không dùng BaseColor.
             * Không dùng arial.ttf.
             *
             * Bản này ưu tiên chạy ổn định trước.
             */

            Font clinicFont =
                    new Font(
                            Font.HELVETICA,
                            18,
                            Font.BOLD
                    );

            Font titleFont =
                    new Font(
                            Font.HELVETICA,
                            20,
                            Font.BOLD
                    );

            Font sectionFont =
                    new Font(
                            Font.HELVETICA,
                            12,
                            Font.BOLD
                    );

            Font normalFont =
                    new Font(
                            Font.HELVETICA,
                            10,
                            Font.NORMAL
                    );

            Font boldFont =
                    new Font(
                            Font.HELVETICA,
                            10,
                            Font.BOLD
                    );

            Font smallFont =
                    new Font(
                            Font.HELVETICA,
                            9,
                            Font.NORMAL
                    );

            Font totalFont =
                    new Font(
                            Font.HELVETICA,
                            15,
                            Font.BOLD
                    );

            // =================================================
            // 6. HEADER
            // =================================================

            PdfPTable header =
                    new PdfPTable(2);

            header.setWidthPercentage(100);

            header.setWidths(
                    new float[]{
                            65,
                            35
                    }
            );

            // -----------------------------------------------
            // THÔNG TIN PHÒNG KHÁM
            // -----------------------------------------------

            PdfPCell clinicCell =
                    new PdfPCell();

            clinicCell.setBorder(
                    PdfPCell.NO_BORDER
            );

            Paragraph clinicName =
                    new Paragraph(
                            "PHÒNG KHÁM ONLINE",
                            clinicFont
                    );

            Paragraph address =
                    new Paragraph(
                            "Hệ thống đặt lịch khám trực tuyến",
                            smallFont
                    );

            Paragraph hotline =
                    new Paragraph(
                            "Hotline: 1900 1234",
                            smallFont
                    );

            clinicCell.addElement(clinicName);
            clinicCell.addElement(address);
            clinicCell.addElement(hotline);

            header.addCell(clinicCell);

            // -----------------------------------------------
            // MÃ HÓA ĐƠN
            // -----------------------------------------------

            PdfPCell codeCell =
                    new PdfPCell();

            codeCell.setBorder(
                    PdfPCell.NO_BORDER
            );

            Paragraph hoaDon =
                    new Paragraph(
                            "HÓA ĐƠN",
                            sectionFont
                    );

            hoaDon.setAlignment(
                    Element.ALIGN_RIGHT
            );

            Paragraph maHoaDon =
                    new Paragraph(
                            "HD-" + maThanhToan,
                            boldFont
                    );

            maHoaDon.setAlignment(
                    Element.ALIGN_RIGHT
            );

            codeCell.addElement(hoaDon);
            codeCell.addElement(maHoaDon);

            header.addCell(codeCell);

            document.add(header);

            // =================================================
            // 7. ĐƯỜNG KẺ
            // =================================================

            Paragraph line =
                    new Paragraph(
                            "------------------------------------------------------------",
                            smallFont
                    );

            line.setAlignment(
                    Element.ALIGN_CENTER
            );

            line.setSpacingBefore(10);
            line.setSpacingAfter(10);

            document.add(line);

            // =================================================
            // 8. TIÊU ĐỀ
            // =================================================

            Paragraph title =
                    new Paragraph(
                            "HÓA ĐƠN THANH TOÁN",
                            titleFont
                    );

            title.setAlignment(
                    Element.ALIGN_CENTER
            );

            title.setSpacingBefore(10);
            title.setSpacingAfter(5);

            document.add(title);

            Paragraph subtitle =
                    new Paragraph(
                            "Thông tin chi tiết giao dịch",
                            smallFont
                    );

            subtitle.setAlignment(
                    Element.ALIGN_CENTER
            );

            subtitle.setSpacingAfter(20);

            document.add(subtitle);

            // =================================================
            // 9. THÔNG TIN THANH TOÁN
            // =================================================

            addSectionTitle(
                    document,
                    "THÔNG TIN THANH TOÁN",
                    sectionFont
            );

            PdfPTable paymentTable =
                    createInfoTable();

            addRow(
                    paymentTable,
                    "Mã thanh toán",
                    String.valueOf(
                            thanhToan.getMaThanhToan()
                    ),
                    boldFont,
                    normalFont
            );

            if (thanhToan.getNgayThanhToan() != null) {

                SimpleDateFormat sdf =
                        new SimpleDateFormat(
                                "dd/MM/yyyy HH:mm"
                        );

                addRow(
                        paymentTable,
                        "Ngày thanh toán",
                        sdf.format(
                                thanhToan.getNgayThanhToan()
                        ),
                        boldFont,
                        normalFont
                );
            }

            addRow(
                    paymentTable,
                    "Phương thức",
                    safe(
                            thanhToan.getPhuongThuc()
                    ),
                    boldFont,
                    normalFont
            );

            addRow(
                    paymentTable,
                    "Trạng thái",
                    safe(
                            thanhToan.getTrangThai()
                    ),
                    boldFont,
                    normalFont
            );

            document.add(paymentTable);

            // =================================================
            // 10. THÔNG TIN LỊCH KHÁM
            // =================================================

            addSectionTitle(
                    document,
                    "THÔNG TIN LỊCH KHÁM",
                    sectionFont
            );

            PdfPTable appointmentTable =
                    createInfoTable();

            if (thanhToan.getLichKham() != null) {

                var lichKham =
                        thanhToan.getLichKham();

                // Bệnh nhân
                if (lichKham.getBenhNhan() != null) {

                    addRow(
                            appointmentTable,
                            "Bệnh nhân",
                            safe(
                                    lichKham
                                            .getBenhNhan()
                                            .getHoTen()
                            ),
                            boldFont,
                            normalFont
                    );
                }

                // Bác sĩ
                if (lichKham.getBacSi() != null) {

                    addRow(
                            appointmentTable,
                            "Bác sĩ",
                            safe(
                                    lichKham
                                            .getBacSi()
                                            .getHoTen()
                            ),
                            boldFont,
                            normalFont
                    );
                }

                // Dịch vụ
                if (lichKham.getDichVu() != null) {

                    addRow(
                            appointmentTable,
                            "Dịch vụ",
                            safe(
                                    lichKham
                                            .getDichVu()
                                            .getTenDichVu()
                            ),
                            boldFont,
                            normalFont
                    );
                }

                // Ngày khám
                if (lichKham.getNgayKham() != null) {

                    SimpleDateFormat dateFormat =
                            new SimpleDateFormat(
                                    "dd/MM/yyyy"
                            );

                    addRow(
                            appointmentTable,
                            "Ngày khám",
                            dateFormat.format(
                                    lichKham.getNgayKham()
                            ),
                            boldFont,
                            normalFont
                    );
                }

                // Giờ khám
                if (lichKham.getGioKham() != null) {

                    SimpleDateFormat timeFormat =
                            new SimpleDateFormat(
                                    "HH:mm"
                            );

                    addRow(
                            appointmentTable,
                            "Giờ khám",
                            timeFormat.format(
                                    lichKham.getGioKham()
                            ),
                            boldFont,
                            normalFont
                    );
                }

                // Lý do khám
                if (lichKham.getLyDoKham() != null
                        && !lichKham
                                .getLyDoKham()
                                .trim()
                                .isEmpty()) {

                    addRow(
                            appointmentTable,
                            "Lý do khám",
                            lichKham.getLyDoKham(),
                            boldFont,
                            normalFont
                    );
                }
            }

            document.add(appointmentTable);

            // =================================================
            // 11. TỔNG THANH TOÁN
            // =================================================

            Paragraph totalTitle =
                    new Paragraph(
                            "TỔNG THANH TOÁN",
                            sectionFont
                    );

            totalTitle.setSpacingBefore(25);
            totalTitle.setSpacingAfter(10);

            document.add(totalTitle);

            PdfPTable totalTable =
                    new PdfPTable(2);

            totalTable.setWidthPercentage(100);

            totalTable.setWidths(
                    new float[]{
                            55,
                            45
                    }
            );

            PdfPCell totalLabel =
                    new PdfPCell(
                            new Phrase(
                                    "TỔNG CỘNG",
                                    boldFont
                            )
                    );

            totalLabel.setPadding(12);

            PdfPCell totalValue =
                    new PdfPCell(
                            new Phrase(
                                    formatMoney(
                                            thanhToan.getSoTien()
                                    ),
                                    totalFont
                            )
                    );

            totalValue.setPadding(12);

            totalValue.setHorizontalAlignment(
                    Element.ALIGN_RIGHT
            );

            totalTable.addCell(totalLabel);
            totalTable.addCell(totalValue);

            document.add(totalTable);

            // =================================================
            // 12. TRẠNG THÁI
            // =================================================

            Paragraph status =
                    new Paragraph(
                            "[DA THANH TOAN] "
                                    + safe(
                                            thanhToan.getTrangThai()
                                    ),
                            boldFont
                    );

            status.setAlignment(
                    Element.ALIGN_CENTER
            );

            status.setSpacingBefore(18);

            document.add(status);

            // =================================================
            // 13. LỜI CẢM ƠN
            // =================================================

            Paragraph thankYou =
                    new Paragraph(
                            "Cảm ơn quý khách đã sử dụng "
                                    + "dịch vụ của Phòng Khám Online!",
                            normalFont
                    );

            thankYou.setAlignment(
                    Element.ALIGN_CENTER
            );

            thankYou.setSpacingBefore(30);

            document.add(thankYou);

            Paragraph note =
                    new Paragraph(
                            "Vui lòng giữ lại hóa đơn này "
                                    + "để đối chiếu khi cần thiết.",
                            smallFont
                    );

            note.setAlignment(
                    Element.ALIGN_CENTER
            );

            document.add(note);

        } catch (Exception e) {

            e.printStackTrace();

            /*
             * Không gọi document.close() trước khi xử lý lỗi.
             */
            throw new ServletException(
                    "Lỗi tạo PDF hóa đơn: "
                            + e.getMessage(),
                    e
            );

        } finally {

            if (document.isOpen()) {
                document.close();
            }
        }
    }

    // =========================================================
    // TẠO BẢNG THÔNG TIN
    // =========================================================

    private PdfPTable createInfoTable()
            throws Exception {

        PdfPTable table =
                new PdfPTable(2);

        table.setWidthPercentage(100);

        table.setWidths(
                new float[]{
                        35,
                        65
                }
        );

        return table;
    }

    // =========================================================
    // TIÊU ĐỀ SECTION
    // =========================================================

    private void addSectionTitle(
            Document document,
            String title,
            Font font)
            throws Exception {

        PdfPTable table =
                new PdfPTable(1);

        table.setWidthPercentage(100);

        PdfPCell cell =
                new PdfPCell(
                        new Phrase(
                                title,
                                font
                        )
                );

        cell.setPadding(8);

        table.addCell(cell);

        document.add(table);

        Paragraph space =
                new Paragraph(" ");

        space.setSpacingAfter(3);

        document.add(space);
    }

    // =========================================================
    // THÊM DÒNG
    // =========================================================

    private void addRow(
            PdfPTable table,
            String label,
            String value,
            Font labelFont,
            Font valueFont) {

        PdfPCell labelCell =
                new PdfPCell(
                        new Phrase(
                                label,
                                labelFont
                        )
                );

        PdfPCell valueCell =
                new PdfPCell(
                        new Phrase(
                                value,
                                valueFont
                        )
                );

        labelCell.setPadding(8);
        valueCell.setPadding(8);

        table.addCell(labelCell);
        table.addCell(valueCell);
    }

    // =========================================================
    // FORMAT TIỀN
    // =========================================================

    private String formatMoney(
            BigDecimal money) {

        if (money == null) {
            return "0 VNĐ";
        }

        NumberFormat formatter =
                NumberFormat.getNumberInstance(
                        Locale.forLanguageTag("vi-VN")
                );

        formatter.setMaximumFractionDigits(0);

        return formatter.format(money)
                + " VNĐ";
    }

    // =========================================================
    // XỬ LÝ NULL
    // =========================================================

    private String safe(String value) {

        if (value == null
                || value.trim().isEmpty()) {

            return "Không có";
        }

        return value;
    }
}
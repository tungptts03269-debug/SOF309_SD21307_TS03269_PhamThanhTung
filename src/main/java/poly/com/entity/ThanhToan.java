package poly.com.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.persistence.*;

@Entity
@Table(name = "ThanhToan")
public class ThanhToan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaThanhToan")
    private int maThanhToan;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaLichKham")
    private LichKham lichKham;

    @Column(name = "SoTien", nullable = false)
    private BigDecimal soTien;

    @Column(name = "PhuongThuc")
    private String phuongThuc;

    @Column(name = "NgayThanhToan")
    private Timestamp ngayThanhToan;

    @Column(name = "TrangThai")
    private String trangThai;

    @Column(name = "GhiChu")
    private String ghiChu;

    public ThanhToan() {
    }

    public ThanhToan(int maThanhToan,
                     LichKham lichKham,
                     BigDecimal soTien,
                     String phuongThuc,
                     Timestamp ngayThanhToan,
                     String trangThai,
                     String ghiChu) {

        this.maThanhToan = maThanhToan;
        this.lichKham = lichKham;
        this.soTien = soTien;
        this.phuongThuc = phuongThuc;
        this.ngayThanhToan = ngayThanhToan;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
    }

    public int getMaThanhToan() {
        return maThanhToan;
    }

    public void setMaThanhToan(int maThanhToan) {
        this.maThanhToan = maThanhToan;
    }

    public LichKham getLichKham() {
        return lichKham;
    }

    public void setLichKham(LichKham lichKham) {
        this.lichKham = lichKham;
    }

    public BigDecimal getSoTien() {
        return soTien;
    }

    public void setSoTien(BigDecimal soTien) {
        this.soTien = soTien;
    }

    public String getPhuongThuc() {
        return phuongThuc;
    }

    public void setPhuongThuc(String phuongThuc) {
        this.phuongThuc = phuongThuc;
    }

    public Timestamp getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Timestamp ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
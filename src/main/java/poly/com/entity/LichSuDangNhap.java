package poly.com.entity;

import java.sql.Timestamp;

import jakarta.persistence.*;

@Entity
@Table(name = "LichSuDangNhap")
public class LichSuDangNhap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaLichSu")
    private int maLichSu;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaTaiKhoan", nullable = false)
    private TaiKhoan taiKhoan;

    @Column(name = "ThoiGianDangNhap")
    private Timestamp thoiGianDangNhap;

    @Column(name = "ThoiGianDangXuat")
    private Timestamp thoiGianDangXuat;

    @Column(name = "DiaChiIP")
    private String diaChiIP;

    @Column(name = "ThietBi")
    private String thietBi;

    @Column(name = "TrangThai")
    private String trangThai;

    public LichSuDangNhap() {
    }

    public LichSuDangNhap(int maLichSu, TaiKhoan taiKhoan,
            Timestamp thoiGianDangNhap,
            Timestamp thoiGianDangXuat,
            String diaChiIP,
            String thietBi,
            String trangThai) {

        this.maLichSu = maLichSu;
        this.taiKhoan = taiKhoan;
        this.thoiGianDangNhap = thoiGianDangNhap;
        this.thoiGianDangXuat = thoiGianDangXuat;
        this.diaChiIP = diaChiIP;
        this.thietBi = thietBi;
        this.trangThai = trangThai;
    }

    public int getMaLichSu() {
        return maLichSu;
    }

    public void setMaLichSu(int maLichSu) {
        this.maLichSu = maLichSu;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public Timestamp getThoiGianDangNhap() {
        return thoiGianDangNhap;
    }

    public void setThoiGianDangNhap(Timestamp thoiGianDangNhap) {
        this.thoiGianDangNhap = thoiGianDangNhap;
    }

    public Timestamp getThoiGianDangXuat() {
        return thoiGianDangXuat;
    }

    public void setThoiGianDangXuat(Timestamp thoiGianDangXuat) {
        this.thoiGianDangXuat = thoiGianDangXuat;
    }

    public String getDiaChiIP() {
        return diaChiIP;
    }

    public void setDiaChiIP(String diaChiIP) {
        this.diaChiIP = diaChiIP;
    }

    public String getThietBi() {
        return thietBi;
    }

    public void setThietBi(String thietBi) {
        this.thietBi = thietBi;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

}
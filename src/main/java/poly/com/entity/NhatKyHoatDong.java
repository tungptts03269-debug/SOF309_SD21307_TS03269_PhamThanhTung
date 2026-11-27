package poly.com.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.*;

@Entity
@Table(name = "NhatKyHoatDong")
public class NhatKyHoatDong implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaNhatKy")
    private int maNhatKy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaTaiKhoan", nullable = false)
    private TaiKhoan taiKhoan;

    @Column(name = "HanhDong")
    private String hanhDong;

    @Column(name = "BangTacDong")
    private String bangTacDong;

    @Column(name = "MaDoiTuong")
    private Integer maDoiTuong;

    @Column(name = "ThoiGian")
    private Timestamp thoiGian;

    @Column(name = "DiaChiIP")
    private String diaChiIP;

    @Column(name = "GhiChu")
    private String ghiChu;

    public NhatKyHoatDong() {
    }

    public NhatKyHoatDong(int maNhatKy, TaiKhoan taiKhoan,
            String hanhDong, String bangTacDong,
            Integer maDoiTuong, Timestamp thoiGian,
            String diaChiIP, String ghiChu) {

        this.maNhatKy = maNhatKy;
        this.taiKhoan = taiKhoan;
        this.hanhDong = hanhDong;
        this.bangTacDong = bangTacDong;
        this.maDoiTuong = maDoiTuong;
        this.thoiGian = thoiGian;
        this.diaChiIP = diaChiIP;
        this.ghiChu = ghiChu;
    }

    public int getMaNhatKy() {
        return maNhatKy;
    }

    public void setMaNhatKy(int maNhatKy) {
        this.maNhatKy = maNhatKy;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getHanhDong() {
        return hanhDong;
    }

    public void setHanhDong(String hanhDong) {
        this.hanhDong = hanhDong;
    }

    public String getBangTacDong() {
        return bangTacDong;
    }

    public void setBangTacDong(String bangTacDong) {
        this.bangTacDong = bangTacDong;
    }

    public Integer getMaDoiTuong() {
        return maDoiTuong;
    }

    public void setMaDoiTuong(Integer maDoiTuong) {
        this.maDoiTuong = maDoiTuong;
    }

    public Timestamp getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Timestamp thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getDiaChiIP() {
        return diaChiIP;
    }

    public void setDiaChiIP(String diaChiIP) {
        this.diaChiIP = diaChiIP;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
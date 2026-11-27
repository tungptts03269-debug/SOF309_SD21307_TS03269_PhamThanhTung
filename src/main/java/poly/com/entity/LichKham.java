package poly.com.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import jakarta.persistence.*;

@Entity
@Table(name = "LichKham")
public class LichKham implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaLichKham")
    private Integer maLichKham;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaBenhNhan")
    private BenhNhan benhNhan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaBacSi")
    private BacSi bacSi;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaDichVu")
    private DichVu dichVu;

    @Column(name = "NgayDat")
    private Timestamp ngayDat;

    @Column(name = "NgayKham")
    private Date ngayKham;

    @Column(name = "GioKham")
    private Time gioKham;

    @Column(name = "LyDoKham")
    private String lyDoKham;

    @Column(name = "GhiChu")
    private String ghiChu;

    @Column(name = "TrangThai")
    private String trangThai;

    public LichKham() {
    }

    public Integer getMaLichKham() {
        return maLichKham;
    }

    public void setMaLichKham(Integer maLichKham) {
        this.maLichKham = maLichKham;
    }

    public BenhNhan getBenhNhan() {
        return benhNhan;
    }

    public void setBenhNhan(BenhNhan benhNhan) {
        this.benhNhan = benhNhan;
    }

    public BacSi getBacSi() {
        return bacSi;
    }

    public void setBacSi(BacSi bacSi) {
        this.bacSi = bacSi;
    }

    public DichVu getDichVu() {
        return dichVu;
    }

    public void setDichVu(DichVu dichVu) {
        this.dichVu = dichVu;
    }

    public Timestamp getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Timestamp ngayDat) {
        this.ngayDat = ngayDat;
    }

    public Date getNgayKham() {
        return ngayKham;
    }

    public void setNgayKham(Date ngayKham) {
        this.ngayKham = ngayKham;
    }

    public Time getGioKham() {
        return gioKham;
    }

    public void setGioKham(Time gioKham) {
        this.gioKham = gioKham;
    }

    public String getLyDoKham() {
        return lyDoKham;
    }

    public void setLyDoKham(String lyDoKham) {
        this.lyDoKham = lyDoKham;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

}
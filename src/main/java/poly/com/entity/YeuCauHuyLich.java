package poly.com.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "YeuCauHuyLich")
public class YeuCauHuyLich implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaYeuCau")
    private Integer maYeuCau;

    // ==========================
    // Liên kết tới lịch khám
    // ==========================
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaLichKham", nullable = false)
    private LichKham lichKham;

    @Column(name = "LyDo", columnDefinition = "NVARCHAR(MAX)")
    private String lyDo;

    @Column(name = "TrangThai", length = 50)
    private String trangThai;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "NgayGui")
    private Date ngayGui;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "NgayXuLy")
    private Date ngayXuLy;

    @Column(name = "NguoiXuLy")
    private Integer nguoiXuLy;

    public YeuCauHuyLich() {
    }

    public Integer getMaYeuCau() {
        return maYeuCau;
    }

    public void setMaYeuCau(Integer maYeuCau) {
        this.maYeuCau = maYeuCau;
    }

    public LichKham getLichKham() {
        return lichKham;
    }

    public void setLichKham(LichKham lichKham) {
        this.lichKham = lichKham;
    }

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Date getNgayGui() {
        return ngayGui;
    }

    public void setNgayGui(Date ngayGui) {
        this.ngayGui = ngayGui;
    }

    public Date getNgayXuLy() {
        return ngayXuLy;
    }

    public void setNgayXuLy(Date ngayXuLy) {
        this.ngayXuLy = ngayXuLy;
    }

    public Integer getNguoiXuLy() {
        return nguoiXuLy;
    }

    public void setNguoiXuLy(Integer nguoiXuLy) {
        this.nguoiXuLy = nguoiXuLy;
    }

}
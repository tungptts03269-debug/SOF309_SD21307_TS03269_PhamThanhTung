package poly.com.entity;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(
    name = "DanhGia",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "UQ_DanhGia_BenhNhan_BacSi",
            columnNames = {"MaBenhNhan", "MaBacSi"}
        )
    }
)
public class DanhGia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaDanhGia")
    private int maDanhGia;

    // ================= BỆNH NHÂN =================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaBenhNhan", nullable = false)
    private BenhNhan benhNhan;

    // ================= BÁC SĨ =================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaBacSi", nullable = false)
    private BacSi bacSi;

    // ================= SỐ SAO =================

    @Column(name = "SoSao", nullable = false)
    private int soSao;

    // ================= NỘI DUNG =================

    @Column(name = "NoiDung", length = 1000)
    private String noiDung;

    // ================= NGÀY ĐÁNH GIÁ =================

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "NgayDanhGia")
    private Date ngayDanhGia;

    // ================= CONSTRUCTOR =================

    public DanhGia() {
    }

    public DanhGia(
            int maDanhGia,
            BenhNhan benhNhan,
            BacSi bacSi,
            int soSao,
            String noiDung,
            Date ngayDanhGia) {

        this.maDanhGia = maDanhGia;
        this.benhNhan = benhNhan;
        this.bacSi = bacSi;
        this.soSao = soSao;
        this.noiDung = noiDung;
        this.ngayDanhGia = ngayDanhGia;
    }

    // ================= GETTER / SETTER =================

    public int getMaDanhGia() {
        return maDanhGia;
    }

    public void setMaDanhGia(int maDanhGia) {
        this.maDanhGia = maDanhGia;
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

    public int getSoSao() {
        return soSao;
    }

    public void setSoSao(int soSao) {
        this.soSao = soSao;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Date getNgayDanhGia() {
        return ngayDanhGia;
    }

    public void setNgayDanhGia(Date ngayDanhGia) {
        this.ngayDanhGia = ngayDanhGia;
    }
}	
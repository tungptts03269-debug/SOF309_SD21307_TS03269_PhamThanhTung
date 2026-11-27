package poly.com.entity;

import java.math.BigDecimal;
import jakarta.persistence.*;

@Entity
@Table(name = "DichVu")
public class DichVu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaDichVu")
    private int maDichVu;

    @Column(name = "TenDichVu", nullable = false)
    private String tenDichVu;

    @Column(name = "GiaDichVu", nullable = false)
    private BigDecimal giaDichVu;

    public DichVu() {
    }

    public DichVu(int maDichVu, String tenDichVu, BigDecimal giaDichVu) {
        this.maDichVu = maDichVu;
        this.tenDichVu = tenDichVu;
        this.giaDichVu = giaDichVu;
    }

    public int getMaDichVu() {
        return maDichVu;
    }

    public void setMaDichVu(int maDichVu) {
        this.maDichVu = maDichVu;
    }

    public String getTenDichVu() {
        return tenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }

    public BigDecimal getGiaDichVu() {
        return giaDichVu;
    }

    public void setGiaDichVu(BigDecimal giaDichVu) {
        this.giaDichVu = giaDichVu;
    }
}
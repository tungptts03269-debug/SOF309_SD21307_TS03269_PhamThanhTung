package poly.com.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ChuyenKhoa")
public class ChuyenKhoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaChuyenKhoa")
    private int maChuyenKhoa;

    @Column(name = "TenChuyenKhoa", nullable = false)
    private String tenChuyenKhoa;

    @Column(name = "MoTa")
    private String moTa;

    public ChuyenKhoa() {
    }

    public ChuyenKhoa(int maChuyenKhoa, String tenChuyenKhoa, String moTa) {
        this.maChuyenKhoa = maChuyenKhoa;
        this.tenChuyenKhoa = tenChuyenKhoa;
        this.moTa = moTa;
    }

    public int getMaChuyenKhoa() {
        return maChuyenKhoa;
    }

    public void setMaChuyenKhoa(int maChuyenKhoa) {
        this.maChuyenKhoa = maChuyenKhoa;
    }

    public String getTenChuyenKhoa() {
        return tenChuyenKhoa;
    }

    public void setTenChuyenKhoa(String tenChuyenKhoa) {
        this.tenChuyenKhoa = tenChuyenKhoa;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
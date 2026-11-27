package poly.com.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "PhongKham")
public class PhongKham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaPhongKham")
    private int maPhongKham;

    @Column(name = "TenPhongKham", nullable = false)
    private String tenPhongKham;

    @Column(name = "DiaChi")
    private String diaChi;

    @Column(name = "SoDienThoai")
    private String soDienThoai;

    @Column(name = "Email")
    private String email;

    @Column(name = "MoTa")
    private String moTa;

    public PhongKham() {
    }

    public PhongKham(int maPhongKham, String tenPhongKham, String diaChi,
            String soDienThoai, String email, String moTa) {
        this.maPhongKham = maPhongKham;
        this.tenPhongKham = tenPhongKham;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.moTa = moTa;
    }

    public int getMaPhongKham() {
        return maPhongKham;
    }

    public void setMaPhongKham(int maPhongKham) {
        this.maPhongKham = maPhongKham;
    }

    public String getTenPhongKham() {
        return tenPhongKham;
    }

    public void setTenPhongKham(String tenPhongKham) {
        this.tenPhongKham = tenPhongKham;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

}
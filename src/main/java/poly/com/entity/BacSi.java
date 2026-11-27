package poly.com.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "BacSi")
public class BacSi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaBacSi")
    private int maBacSi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaTaiKhoan", nullable = false)
    private TaiKhoan taiKhoan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaPhongKham", nullable = false)
    private PhongKham phongKham;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaChuyenKhoa", nullable = false)
    private ChuyenKhoa chuyenKhoa;

    @Column(name = "HoTen", nullable = false)
    private String hoTen;

    @Column(name = "SoDienThoai")
    private String soDienThoai;

    @Column(name = "KinhNghiem")
    private String kinhNghiem;

    public BacSi() {
    }

    public BacSi(int maBacSi, TaiKhoan taiKhoan,
            PhongKham phongKham,
            ChuyenKhoa chuyenKhoa,
            String hoTen,
            String soDienThoai,
            String kinhNghiem) {

        this.maBacSi = maBacSi;
        this.taiKhoan = taiKhoan;
        this.phongKham = phongKham;
        this.chuyenKhoa = chuyenKhoa;
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.kinhNghiem = kinhNghiem;
    }

    public int getMaBacSi() {
        return maBacSi;
    }

    public void setMaBacSi(int maBacSi) {
        this.maBacSi = maBacSi;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public PhongKham getPhongKham() {
        return phongKham;
    }

    public void setPhongKham(PhongKham phongKham) {
        this.phongKham = phongKham;
    }

    public ChuyenKhoa getChuyenKhoa() {
        return chuyenKhoa;
    }

    public void setChuyenKhoa(ChuyenKhoa chuyenKhoa) {
        this.chuyenKhoa = chuyenKhoa;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getKinhNghiem() {
        return kinhNghiem;
    }

    public void setKinhNghiem(String kinhNghiem) {
        this.kinhNghiem = kinhNghiem;
    }
}
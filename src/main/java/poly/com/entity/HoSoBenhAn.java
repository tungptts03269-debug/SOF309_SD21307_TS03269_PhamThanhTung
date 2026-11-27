package poly.com.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "HoSoBenhAn")
public class HoSoBenhAn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaHoSo")
    private int maHoSo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaLichKham")
    private LichKham lichKham;

    @Column(name = "TrieuChung")
    private String trieuChung;

    @Column(name = "ChanDoan")
    private String chanDoan;

    @Column(name = "KetLuan")
    private String ketLuan;

    public HoSoBenhAn() {
    }

    public HoSoBenhAn(int maHoSo, LichKham lichKham,
            String trieuChung,
            String chanDoan,
            String ketLuan) {

        this.maHoSo = maHoSo;
        this.lichKham = lichKham;
        this.trieuChung = trieuChung;
        this.chanDoan = chanDoan;
        this.ketLuan = ketLuan;
    }

    public int getMaHoSo() {
        return maHoSo;
    }

    public void setMaHoSo(int maHoSo) {
        this.maHoSo = maHoSo;
    }

    public LichKham getLichKham() {
        return lichKham;
    }

    public void setLichKham(LichKham lichKham) {
        this.lichKham = lichKham;
    }

    public String getTrieuChung() {
        return trieuChung;
    }

    public void setTrieuChung(String trieuChung) {
        this.trieuChung = trieuChung;
    }

    public String getChanDoan() {
        return chanDoan;
    }

    public void setChanDoan(String chanDoan) {
        this.chanDoan = chanDoan;
    }

    public String getKetLuan() {
        return ketLuan;
    }

    public void setKetLuan(String ketLuan) {
        this.ketLuan = ketLuan;
    }
}
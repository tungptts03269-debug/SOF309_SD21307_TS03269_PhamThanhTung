package poly.com.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import jakarta.persistence.EntityManager;
import poly.com.entity.LichKham;
import poly.com.utils.JpaUtil;

public class LichKhamDAO extends GenericDAO<LichKham> {

    public LichKhamDAO() {
        super(LichKham.class);
    }

    // ================= TÌM THEO MÃ =================
    public LichKham findByMaLichKham(int maLichKham) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    SELECT l
                    FROM LichKham l
                    JOIN FETCH l.benhNhan
                    JOIN FETCH l.bacSi
                    JOIN FETCH l.dichVu
                    WHERE l.maLichKham = :id
                    """;

            return em.createQuery(jpql, LichKham.class)
                    .setParameter("id", maLichKham)
                    .getSingleResult();

        } finally {

            em.close();

        }

    }

    // ================= TẤT CẢ LỊCH KHÁM =================
    public List<LichKham> findAllFetch() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    SELECT l
                    FROM LichKham l
                    JOIN FETCH l.benhNhan
                    JOIN FETCH l.bacSi
                    JOIN FETCH l.dichVu
                    ORDER BY l.ngayDat DESC
                    """;

            return em.createQuery(jpql, LichKham.class)
                    .getResultList();

        } finally {

            em.close();

        }

    }

    // ================= LỊCH KHÁM THEO BỆNH NHÂN =================
    public List<LichKham> findByBenhNhan(int maBenhNhan) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    SELECT l
                    FROM LichKham l
                    JOIN FETCH l.benhNhan
                    JOIN FETCH l.bacSi
                    JOIN FETCH l.dichVu
                    WHERE l.benhNhan.maBenhNhan = :id
                    ORDER BY l.ngayDat DESC
                    """;

            return em.createQuery(jpql, LichKham.class)
                    .setParameter("id", maBenhNhan)
                    .getResultList();

        } finally {

            em.close();

        }

    }

    // ================= LỊCH KHÁM THEO BÁC SĨ =================
    public List<LichKham> findByBacSi(int maBacSi) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    SELECT l
                    FROM LichKham l
                    JOIN FETCH l.benhNhan
                    JOIN FETCH l.bacSi
                    JOIN FETCH l.dichVu
                    WHERE l.bacSi.maBacSi = :id
                    ORDER BY l.ngayDat DESC
                    """;

            return em.createQuery(jpql, LichKham.class)
                    .setParameter("id", maBacSi)
                    .getResultList();

        } finally {

            em.close();

        }

    }

    // ================= LỊCH KHÁM THEO DỊCH VỤ =================
    public List<LichKham> findByDichVu(int maDichVu) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    SELECT l
                    FROM LichKham l
                    JOIN FETCH l.benhNhan
                    JOIN FETCH l.bacSi
                    JOIN FETCH l.dichVu
                    WHERE l.dichVu.maDichVu = :id
                    ORDER BY l.ngayDat DESC
                    """;

            return em.createQuery(jpql, LichKham.class)
                    .setParameter("id", maDichVu)
                    .getResultList();

        } finally {

            em.close();

        }

    }

    // ================= LỊCH KHÁM THEO NGÀY =================
    public List<LichKham> findByNgayKham(Date ngayKham) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    SELECT l
                    FROM LichKham l
                    JOIN FETCH l.benhNhan
                    JOIN FETCH l.bacSi
                    JOIN FETCH l.dichVu
                    WHERE l.ngayKham = :ngay
                    ORDER BY l.gioKham
                    """;

            return em.createQuery(jpql, LichKham.class)
                    .setParameter("ngay", ngayKham)
                    .getResultList();

        } finally {

            em.close();

        }

    }

    // ================= LỊCH KHÁM THEO TRẠNG THÁI =================
    public List<LichKham> findByTrangThai(String trangThai) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    SELECT l
                    FROM LichKham l
                    JOIN FETCH l.benhNhan
                    JOIN FETCH l.bacSi
                    JOIN FETCH l.dichVu
                    WHERE l.trangThai = :tt
                    ORDER BY l.ngayDat DESC
                    """;

            return em.createQuery(jpql, LichKham.class)
                    .setParameter("tt", trangThai)
                    .getResultList();

        } finally {

            em.close();

        }

    }
    // ================= KIỂM TRA BÁC SĨ CÓ BẬN KHÔNG (THÊM MỚI) =================
    public boolean isDoctorBusy(int maBacSi,
                                Date ngayKham,
                                Time gioKham) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    SELECT COUNT(l)
                    FROM LichKham l
                    WHERE l.bacSi.maBacSi = :bs
                      AND l.ngayKham = :ngay
                      AND l.gioKham = :gio
                      AND l.trangThai <> 'Đã hủy'
                    """;

            Long count = em.createQuery(jpql, Long.class)
                    .setParameter("bs", maBacSi)
                    .setParameter("ngay", ngayKham)
                    .setParameter("gio", gioKham)
                    .getSingleResult();

            return count > 0;

        } finally {

            em.close();

        }

    }

    // ================= KIỂM TRA KHI CẬP NHẬT =================
    public boolean isDoctorBusy(int maBacSi,
                                Date ngayKham,
                                Time gioKham,
                                int maLichKham) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    SELECT COUNT(l)
                    FROM LichKham l
                    WHERE l.bacSi.maBacSi = :bs
                      AND l.ngayKham = :ngay
                      AND l.gioKham = :gio
                      AND l.trangThai <> 'Đã hủy'
                      AND l.maLichKham <> :ma
                    """;

            Long count = em.createQuery(jpql, Long.class)
                    .setParameter("bs", maBacSi)
                    .setParameter("ngay", ngayKham)
                    .setParameter("gio", gioKham)
                    .setParameter("ma", maLichKham)
                    .getSingleResult();

            return count > 0;

        } finally {

            em.close();

        }

    }

    // ================= TÌM KIẾM =================
    public List<LichKham> search(String keyword) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    SELECT l
                    FROM LichKham l
                    JOIN FETCH l.benhNhan
                    JOIN FETCH l.bacSi
                    JOIN FETCH l.dichVu
                    WHERE LOWER(l.benhNhan.hoTen) LIKE LOWER(:kw)
                       OR LOWER(l.bacSi.hoTen) LIKE LOWER(:kw)
                       OR LOWER(l.trangThai) LIKE LOWER(:kw)
                    ORDER BY l.ngayDat DESC
                    """;

            return em.createQuery(jpql, LichKham.class)
                    .setParameter("kw", "%" + keyword + "%")
                    .getResultList();

        } finally {

            em.close();

        }

    }

    // ================= ĐẾM LỊCH KHÁM =================
    @Override
    public long count() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            return em.createQuery(
                    "SELECT COUNT(l) FROM LichKham l",
                    Long.class)
                    .getSingleResult();

        } finally {

            em.close();

        }

    }

    // ================= HỦY LỊCH =================
    public void huyLich(int maLichKham) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            LichKham lich = em.find(LichKham.class, maLichKham);

            if (lich != null) {

                lich.setTrangThai("Đã hủy");

                em.merge(lich);

            }

            em.getTransaction().commit();

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {

                em.getTransaction().rollback();

            }

            throw e;

        } finally {

            em.close();

        }

    }
 // ================= TÌM LỊCH KHÁM =================
    public LichKham find(int maLichKham) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            return em.find(LichKham.class, maLichKham);

        } finally {

            em.close();

        }

    }
 // ================= KIỂM TRA LỊCH CỦA BỆNH NHÂN =================
    public boolean belongsToPatient(int maLichKham, int maTaiKhoan) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    SELECT COUNT(l)
                    FROM LichKham l
                    WHERE l.maLichKham = :maLK
                      AND l.benhNhan.taiKhoan.maTaiKhoan = :maTK
                    """;

            Long count = em.createQuery(jpql, Long.class)
                    .setParameter("maLK", maLichKham)
                    .setParameter("maTK", maTaiKhoan)
                    .getSingleResult();

            return count > 0;

        } finally {

            em.close();

        }

    }
 // ================= ĐÃ GỬI YÊU CẦU HỦY =================
    public boolean daGuiYeuCauHuy(int maLichKham) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    SELECT COUNT(y)
                    FROM YeuCauHuyLich y
                    WHERE y.lichKham.maLichKham = :ma
                      AND y.trangThai = 'Chờ duyệt'
                    """;

            Long count = em.createQuery(jpql, Long.class)
                    .setParameter("ma", maLichKham)
                    .getSingleResult();

            return count > 0;

        } finally {

            em.close();

        }

    }
 // ================= ADMIN HỦY LỊCH =================
    public void huyLichSauKhiDuyet(int maLichKham) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            LichKham lich = em.find(LichKham.class, maLichKham);

            if (lich != null) {

                lich.setTrangThai("Đã hủy");

                em.merge(lich);

            }

            em.getTransaction().commit();

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {

                em.getTransaction().rollback();

            }

            throw e;

        } finally {

            em.close();

        }

    }
    
    public long countHomNay() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                SELECT COUNT(l)
                FROM LichKham l
                WHERE l.ngayKham = CURRENT_DATE
                """;

            return em.createQuery(jpql, Long.class)
                     .getSingleResult();

        } finally {

            em.close();

        }

    }
    public long countChoXacNhan() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            return em.createQuery("""
                SELECT COUNT(l)
                FROM LichKham l
                WHERE l.trangThai='Chờ xác nhận'
                """, Long.class)
                .getSingleResult();

        } finally {

            em.close();

        }

    }
    public long countDaXacNhan() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            return em.createQuery("""
                SELECT COUNT(l)
                FROM LichKham l
                WHERE l.trangThai='Đã xác nhận'
                """, Long.class)
                .getSingleResult();

        } finally {

            em.close();

        }

    }
    public long countDaKham() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            return em.createQuery("""
                SELECT COUNT(l)
                FROM LichKham l
                WHERE l.trangThai='Đã khám'
                """, Long.class)
                .getSingleResult();

        } finally {

            em.close();

        }

    }
}
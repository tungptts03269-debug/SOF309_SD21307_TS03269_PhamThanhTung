package poly.com.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.time.LocalDate;
import java.sql.Timestamp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import poly.com.entity.ThanhToan;
import poly.com.utils.JpaUtil;

public class ThanhToanDAO extends GenericDAO<ThanhToan> {

    public ThanhToanDAO() {
        super(ThanhToan.class);
    }

    // ================= TÌM THEO MÃ =================
    public ThanhToan findByMaThanhToan(int maThanhToan) {

        return findById(maThanhToan);

    }

    // ================= TÌM THEO LỊCH KHÁM =================
    public ThanhToan findByLichKham(int maLichKham) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    SELECT t
                    FROM ThanhToan t
                    JOIN FETCH t.lichKham lk
                    JOIN FETCH lk.benhNhan
                    JOIN FETCH lk.bacSi
                    JOIN FETCH lk.dichVu
                    WHERE lk.maLichKham = :maLK
                    """;

            List<ThanhToan> list = em.createQuery(jpql, ThanhToan.class)
                    .setParameter("maLK", maLichKham)
                    .getResultList();

            return list.isEmpty() ? null : list.get(0);

        } finally {

            em.close();

        }

    }

    // ================= TÌM THEO TRẠNG THÁI =================
    public List<ThanhToan> findByTrangThai(String trangThai) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                SELECT DISTINCT t
                FROM ThanhToan t
                JOIN FETCH t.lichKham lk
                JOIN FETCH lk.benhNhan bn
                JOIN FETCH lk.bacSi bs
                JOIN FETCH lk.dichVu dv
                WHERE t.trangThai = :tt
                ORDER BY t.maThanhToan DESC
                """;

            return em.createQuery(jpql, ThanhToan.class)
                    .setParameter("tt", trangThai)
                    .getResultList();

        } finally {

            em.close();

        }
    }

    // ================= TÌM THEO PHƯƠNG THỨC =================
    public List<ThanhToan> findByPhuongThuc(String phuongThuc) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM ThanhToan
                    WHERE phuongThuc = :pt
                    ORDER BY ngayThanhToan DESC
                    """;

            TypedQuery<ThanhToan> query =
                    em.createQuery(jpql, ThanhToan.class);

            query.setParameter("pt", phuongThuc);

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    // ================= TÌM THEO NGÀY =================
    public List<ThanhToan> findByNgay(Date ngay) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM ThanhToan
                    WHERE ngayThanhToan = :ngay
                    ORDER BY ngayThanhToan DESC
                    """;

            TypedQuery<ThanhToan> query =
                    em.createQuery(jpql, ThanhToan.class);

            query.setParameter("ngay", ngay);

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    // ================= TÌM TRONG KHOẢNG NGÀY =================
    public List<ThanhToan> findByDateRange(Date from, Date to) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM ThanhToan
                    WHERE ngayThanhToan BETWEEN :from AND :to
                    ORDER BY ngayThanhToan DESC
                    """;

            TypedQuery<ThanhToan> query =
                    em.createQuery(jpql, ThanhToan.class);

            query.setParameter("from", from);
            query.setParameter("to", to);

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    // ================= TÌM KIẾM =================
    public List<ThanhToan> search(String keyword) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                SELECT DISTINCT t
                FROM ThanhToan t
                JOIN FETCH t.lichKham lk
                JOIN FETCH lk.benhNhan bn
                JOIN FETCH lk.bacSi bs
                JOIN FETCH lk.dichVu dv
                WHERE LOWER(bn.hoTen) LIKE LOWER(:kw)
                   OR LOWER(bs.hoTen) LIKE LOWER(:kw)
                   OR LOWER(dv.tenDichVu) LIKE LOWER(:kw)
                   OR LOWER(t.trangThai) LIKE LOWER(:kw)
                   OR LOWER(t.phuongThuc) LIKE LOWER(:kw)
                ORDER BY t.maThanhToan DESC
                """;

            return em.createQuery(jpql, ThanhToan.class)
                    .setParameter("kw","%"+keyword+"%")
                    .getResultList();

        } finally {

            em.close();

        }

    }

    // ================= ĐẾM SỐ LƯỢNG THANH TOÁN =================
    public long count() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            return em.createQuery(
                    "SELECT COUNT(t) FROM ThanhToan t",
                    Long.class)
                    .getSingleResult();

        } finally {

            em.close();

        }

    }
 // ================= DOANH THU THÁNG =================
    public BigDecimal doanhThuThang() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    SELECT COALESCE(SUM(t.soTien),0)
                    FROM ThanhToan t
                    WHERE t.trangThai = :trangThai
                    AND MONTH(t.ngayThanhToan) = MONTH(CURRENT_DATE)
                    AND YEAR(t.ngayThanhToan) = YEAR(CURRENT_DATE)
                    """;

            return em.createQuery(jpql, BigDecimal.class)
                    .setParameter("trangThai", "Đã thanh toán")
                    .getSingleResult();

        } finally {

            em.close();

        }

    }
 // ================= DOANH THU NĂM =================
    public BigDecimal doanhThuNam() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    SELECT COALESCE(SUM(t.soTien),0)
                    FROM ThanhToan t
                    WHERE t.trangThai = :trangThai
                    AND YEAR(t.ngayThanhToan) = YEAR(CURRENT_DATE)
                    """;

            return em.createQuery(jpql, BigDecimal.class)
                    .setParameter("trangThai", "Đã thanh toán")
                    .getSingleResult();

        } finally {

            em.close();

        }

    }
 // ================= TỔNG DOANH THU =================
    public BigDecimal tongDoanhThu() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    SELECT COALESCE(SUM(t.soTien),0)
                    FROM ThanhToan t
                    WHERE t.trangThai = :trangThai
                    """;

            return em.createQuery(jpql, BigDecimal.class)
                    .setParameter("trangThai", "Đã thanh toán")
                    .getSingleResult();

        } finally {

            em.close();

        }

    }
 // ================= HÓA ĐƠN THEO BỆNH NHÂN =================
    public List<ThanhToan> findByBenhNhan(int maTaiKhoan) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                SELECT DISTINCT t
                FROM ThanhToan t
                JOIN FETCH t.lichKham lk
                JOIN FETCH lk.benhNhan
                JOIN FETCH lk.bacSi
                JOIN FETCH lk.dichVu
                WHERE lk.benhNhan.taiKhoan.maTaiKhoan = :maTK
                ORDER BY t.ngayThanhToan DESC
                """;

            return em.createQuery(jpql, ThanhToan.class)
                    .setParameter("maTK", maTaiKhoan)
                    .getResultList();

        } finally {

            em.close();

        }

    }
    
    @Override
    public List<ThanhToan> findAll() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                SELECT DISTINCT t
                FROM ThanhToan t
                JOIN FETCH t.lichKham lk
                JOIN FETCH lk.benhNhan
                JOIN FETCH lk.bacSi
                JOIN FETCH lk.dichVu
                ORDER BY t.ngayThanhToan DESC
                """;

            return em.createQuery(jpql, ThanhToan.class)
                    .getResultList();

        } finally {

            em.close();

        }

    }
    public ThanhToan findDetail(int maThanhToan) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                SELECT t
                FROM ThanhToan t
                JOIN FETCH t.lichKham lk
                JOIN FETCH lk.benhNhan bn
                JOIN FETCH bn.taiKhoan
                JOIN FETCH lk.bacSi bs
                JOIN FETCH lk.dichVu dv
                WHERE t.maThanhToan = :id
                """;

            return em.createQuery(jpql, ThanhToan.class)
                    .setParameter("id", maThanhToan)
                    .getSingleResult();

        } finally {

            em.close();

        }

    }
    public long countDaThanhToan() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                SELECT COUNT(t)
                FROM ThanhToan t
                WHERE t.trangThai='Đã thanh toán'
                """;

            return em.createQuery(jpql,Long.class)
                    .getSingleResult();

        } finally {

            em.close();

        }

    }
    public long countChuaThanhToan() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                SELECT COUNT(t)
                FROM ThanhToan t
                WHERE t.trangThai='Chưa thanh toán'
                """;

            return em.createQuery(jpql,Long.class)
                    .getSingleResult();

        } finally {

            em.close();

        }

    }
 // ================= ĐÃ THANH TOÁN =================
    public long daThanhToan() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            return em.createQuery("""
                SELECT COUNT(t)
                FROM ThanhToan t
                WHERE LOWER(TRIM(t.trangThai)) = 'đã thanh toán'
                """, Long.class)
                .getSingleResult();

        } finally {

            em.close();

        }

    }
 // ================= CHƯA THANH TOÁN =================
    public long chuaThanhToan() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            return em.createQuery("""
                SELECT COUNT(t)
                FROM ThanhToan t
                WHERE LOWER(TRIM(t.trangThai)) <> 'đã thanh toán'
                """, Long.class)
                .getSingleResult();

        } finally {

            em.close();

        }

    }
 // ================= DOANH THU HÔM NAY =================
    public BigDecimal doanhThuHomNay() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            LocalDate today = LocalDate.now();

            Timestamp start = Timestamp.valueOf(today.atStartOfDay());
            Timestamp end = Timestamp.valueOf(today.plusDays(1).atStartOfDay());

            String jpql = """
                SELECT COALESCE(SUM(t.soTien), 0)
                FROM ThanhToan t
                WHERE t.trangThai = :tt
                  AND t.ngayThanhToan >= :start
                  AND t.ngayThanhToan < :end
                """;

            return em.createQuery(jpql, BigDecimal.class)
                    .setParameter("tt", "Đã thanh toán")
                    .setParameter("start", start)
                    .setParameter("end", end)
                    .getSingleResult();

        } finally {

            em.close();

        }

    }
}
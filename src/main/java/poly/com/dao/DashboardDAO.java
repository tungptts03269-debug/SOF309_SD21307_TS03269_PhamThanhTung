package poly.com.dao;

import jakarta.persistence.EntityManager;
import poly.com.utils.JpaUtil;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.time.LocalDate;
public class DashboardDAO {

    // ================= TỔNG TÀI KHOẢN =================

    public long countTaiKhoan() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            return em.createQuery(
                    "SELECT COUNT(t) FROM TaiKhoan t",
                    Long.class)
                    .getSingleResult();

        } finally {

            em.close();

        }

    }

    // ================= TỔNG BÁC SĨ =================

    public long countBacSi() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            return em.createQuery(
                    "SELECT COUNT(b) FROM BacSi b",
                    Long.class)
                    .getSingleResult();

        } finally {

            em.close();

        }

    }

    // ================= TỔNG BỆNH NHÂN =================

    public long countBenhNhan() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            return em.createQuery(
                    "SELECT COUNT(b) FROM BenhNhan b",
                    Long.class)
                    .getSingleResult();

        } finally {

            em.close();

        }

    }

    // ================= TỔNG LỊCH KHÁM =================

    public long countLichKham() {

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

    // ================= LỊCH KHÁM HÔM NAY =================

    public long lichHomNay() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            return em.createQuery(
                    "SELECT COUNT(l) FROM LichKham l WHERE l.ngayKham = CURRENT_DATE",
                    Long.class)
                    .getSingleResult();

        } finally {

            em.close();

        }

    }

    // ================= CHỜ XÁC NHẬN =================

    public long choXacNhan() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            Long count = em.createQuery("""
                SELECT COUNT(l)
                FROM LichKham l
                WHERE l.trangThai = :tt
                """, Long.class)
                .setParameter("tt", "Chờ xác nhận")
                .getSingleResult();

            System.out.println("=== CHO XAC NHAN = " + count);

            return count;

        } finally {

            em.close();

        }

    }

    public long daXacNhan() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            Long count = em.createQuery("""
                SELECT COUNT(l)
                FROM LichKham l
                WHERE l.trangThai = :tt
                """, Long.class)
                .setParameter("tt", "Đã xác nhận")
                .getSingleResult();

            System.out.println("=== DA XAC NHAN = " + count);

            return count;

        } finally {

            em.close();

        }

    }

 // ================= HOÀN THÀNH =================

    public long daKham() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            return em.createQuery("""
                    SELECT COUNT(l)
                    FROM LichKham l
                    WHERE l.trangThai = 'Hoàn thành'
                    """, Long.class)
                    .getSingleResult();

        } finally {

            em.close();

        }

    }

    // ================= LIÊN HỆ CHƯA PHẢN HỒI =================

    public long lienHeChuaTraLoi() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            return em.createQuery(
                    "SELECT COUNT(l) FROM LienHe l WHERE l.trangThai = 'Chưa phản hồi'",
                    Long.class)
                    .getSingleResult();

        } finally {

            em.close();

        }

    }

    // ================= YÊU CẦU HỦY LỊCH =================

    public long yeuCauHuy() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            return em.createQuery(
                    "SELECT COUNT(y) FROM YeuCauHuyLich y WHERE y.trangThai = 'Chờ duyệt'",
                    Long.class)
                    .getSingleResult();

        } finally {

            em.close();

        }

    }

    // ================= THÔNG BÁO ADMIN CHƯA ĐỌC =================

    public long thongBaoChuaDoc() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            return em.createQuery(
                    "SELECT COUNT(t) FROM ThongBaoAdmin t WHERE t.daDoc = false",
                    Long.class)
                    .getSingleResult();

        } finally {

            em.close();

        }

    }
    
    public BigDecimal doanhThuHomNay() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            LocalDate today = LocalDate.now();

            Timestamp start = Timestamp.valueOf(today.atStartOfDay());
            Timestamp end = Timestamp.valueOf(today.plusDays(1).atStartOfDay());

            String jpql = """
                SELECT COALESCE(SUM(t.soTien), 0)
                FROM ThanhToan t
                WHERE t.trangThai = :trangThai
                  AND t.ngayThanhToan >= :start
                  AND t.ngayThanhToan < :end
                """;

            return em.createQuery(jpql, BigDecimal.class)
                    .setParameter("trangThai", "Đã thanh toán")
                    .setParameter("start", start)
                    .setParameter("end", end)
                    .getSingleResult();

        } finally {

            em.close();

        }

    }
 // ================= DOANH THU THÁNG =================
    public BigDecimal doanhThuThang() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            java.time.LocalDate now = java.time.LocalDate.now();

            Timestamp start = Timestamp.valueOf(
                    now.withDayOfMonth(1).atStartOfDay());

            Timestamp end = Timestamp.valueOf(
                    now.plusMonths(1).withDayOfMonth(1).atStartOfDay());

            BigDecimal tong = em.createQuery("""
                    SELECT SUM(t.soTien)
                    FROM ThanhToan t
                    WHERE t.trangThai = :tt
                    AND t.ngayThanhToan >= :start
                    AND t.ngayThanhToan < :end
                    """, BigDecimal.class)
                    .setParameter("tt", "Đã thanh toán")
                    .setParameter("start", start)
                    .setParameter("end", end)
                    .getSingleResult();

            return tong == null ? BigDecimal.ZERO : tong;

        } finally {

            em.close();

        }

    }
 // ================= DOANH THU NĂM =================
    public BigDecimal doanhThuNam() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            java.time.LocalDate now = java.time.LocalDate.now();

            Timestamp start = Timestamp.valueOf(
                    now.withDayOfYear(1).atStartOfDay());

            Timestamp end = Timestamp.valueOf(
                    now.plusYears(1).withDayOfYear(1).atStartOfDay());

            BigDecimal tong = em.createQuery("""
                    SELECT SUM(t.soTien)
                    FROM ThanhToan t
                    WHERE t.trangThai = :tt
                    AND t.ngayThanhToan >= :start
                    AND t.ngayThanhToan < :end
                    """, BigDecimal.class)
                    .setParameter("tt", "Đã thanh toán")
                    .setParameter("start", start)
                    .setParameter("end", end)
                    .getSingleResult();

            return tong == null ? BigDecimal.ZERO : tong;

        } finally {

            em.close();

        }

    }
 // ================= TỔNG DOANH THU =================
    public BigDecimal tongDoanhThu() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            BigDecimal tong = em.createQuery("""
                    SELECT SUM(t.soTien)
                    FROM ThanhToan t
                    WHERE t.trangThai = :tt
                    """, BigDecimal.class)
                    .setParameter("tt", "Đã thanh toán")
                    .getSingleResult();

            return tong == null ? BigDecimal.ZERO : tong;

        } finally {

            em.close();

        }

    }
 // ================= DOANH THU THEO THÁNG =================
    public List<Object[]> doanhThuTheoThang() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            java.time.LocalDate now = java.time.LocalDate.now();

            Timestamp start = Timestamp.valueOf(
                    now.withDayOfYear(1).atStartOfDay());

            Timestamp end = Timestamp.valueOf(
                    now.plusYears(1).withDayOfYear(1).atStartOfDay());

            String jpql = """
                    SELECT FUNCTION('MONTH', t.ngayThanhToan),
                           SUM(t.soTien)
                    FROM ThanhToan t
                    WHERE t.trangThai = :tt
                    AND t.ngayThanhToan >= :start
                    AND t.ngayThanhToan < :end
                    GROUP BY FUNCTION('MONTH', t.ngayThanhToan)
                    ORDER BY FUNCTION('MONTH', t.ngayThanhToan)
                    """;

            return em.createQuery(jpql, Object[].class)
                    .setParameter("tt", "Đã thanh toán")
                    .setParameter("start", start)
                    .setParameter("end", end)
                    .getResultList();

        } finally {

            em.close();

        }

    }

}
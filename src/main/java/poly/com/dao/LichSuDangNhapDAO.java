package poly.com.dao;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import poly.com.entity.LichSuDangNhap;
import poly.com.utils.JpaUtil;

public class LichSuDangNhapDAO extends GenericDAO<LichSuDangNhap> {

    public LichSuDangNhapDAO() {
        super(LichSuDangNhap.class);
    }

    // ================= TÌM THEO MÃ =================
    public LichSuDangNhap findByMaLichSu(int maLichSu) {

        return findById(maLichSu);

    }

    // ================= LỊCH SỬ THEO TÀI KHOẢN =================
    public List<LichSuDangNhap> findByTaiKhoan(int maTaiKhoan) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM LichSuDangNhap
                    WHERE taiKhoan.maTaiKhoan = :maTK
                    ORDER BY thoiGianDangNhap DESC
                    """;

            TypedQuery<LichSuDangNhap> query =
                    em.createQuery(jpql, LichSuDangNhap.class);

            query.setParameter("maTK", maTaiKhoan);

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    // ================= TÌM THEO TRẠNG THÁI =================
    public List<LichSuDangNhap> findByTrangThai(String trangThai) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM LichSuDangNhap
                    WHERE trangThai = :tt
                    ORDER BY thoiGianDangNhap DESC
                    """;

            TypedQuery<LichSuDangNhap> query =
                    em.createQuery(jpql, LichSuDangNhap.class);

            query.setParameter("tt", trangThai);

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    // ================= TÌM THEO ĐỊA CHỈ IP =================
    public List<LichSuDangNhap> findByIP(String ip) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM LichSuDangNhap
                    WHERE diaChiIP = :ip
                    ORDER BY thoiGianDangNhap DESC
                    """;

            TypedQuery<LichSuDangNhap> query =
                    em.createQuery(jpql, LichSuDangNhap.class);

            query.setParameter("ip", ip);

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    // ================= TÌM THEO THIẾT BỊ =================
    public List<LichSuDangNhap> findByThietBi(String thietBi) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM LichSuDangNhap
                    WHERE LOWER(thietBi) LIKE LOWER(:tb)
                    ORDER BY thoiGianDangNhap DESC
                    """;

            TypedQuery<LichSuDangNhap> query =
                    em.createQuery(jpql, LichSuDangNhap.class);

            query.setParameter("tb", "%" + thietBi + "%");

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    // ================= TÌM THEO KHOẢNG THỜI GIAN =================
    public List<LichSuDangNhap> findByDateRange(Timestamp from, Timestamp to) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM LichSuDangNhap
                    WHERE thoiGianDangNhap BETWEEN :fromDate AND :toDate
                    ORDER BY thoiGianDangNhap DESC
                    """;

            TypedQuery<LichSuDangNhap> query =
                    em.createQuery(jpql, LichSuDangNhap.class);

            query.setParameter("fromDate", from);
            query.setParameter("toDate", to);

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    // ================= PHIÊN ĐĂNG NHẬP MỚI NHẤT =================
    public LichSuDangNhap findLatestByTaiKhoan(int maTaiKhoan) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM LichSuDangNhap
                    WHERE taiKhoan.maTaiKhoan = :maTK
                    ORDER BY thoiGianDangNhap DESC
                    """;

            List<LichSuDangNhap> list = em.createQuery(jpql, LichSuDangNhap.class)
                    .setParameter("maTK", maTaiKhoan)
                    .setMaxResults(1)
                    .getResultList();

            return list.isEmpty() ? null : list.get(0);

        } finally {

            em.close();

        }

    }

    // ================= TÌM KIẾM =================
    public List<LichSuDangNhap> search(String keyword) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM LichSuDangNhap l
                    WHERE LOWER(l.taiKhoan.tenDangNhap) LIKE LOWER(:kw)
                       OR LOWER(l.trangThai) LIKE LOWER(:kw)
                       OR LOWER(l.diaChiIP) LIKE LOWER(:kw)
                       OR LOWER(l.thietBi) LIKE LOWER(:kw)
                    ORDER BY l.thoiGianDangNhap DESC
                    """;

            TypedQuery<LichSuDangNhap> query =
                    em.createQuery(jpql, LichSuDangNhap.class);

            query.setParameter("kw", "%" + keyword + "%");

            return query.getResultList();

        } finally {

            em.close();

        }

    }
    
    // ================= ĐẾM TỔNG SỐ =================
    public long count() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            return em.createQuery(
                    "SELECT COUNT(l) FROM LichSuDangNhap l",
                    Long.class)
                    .getSingleResult();

        } finally {

            em.close();

        }

    }
 // ================= LẤY TOÀN BỘ LỊCH SỬ =================
    public List<LichSuDangNhap> findAll() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM LichSuDangNhap
                    ORDER BY thoiGianDangNhap DESC
                    """;

            return em.createQuery(jpql, LichSuDangNhap.class)
                    .getResultList();

        } finally {

            em.close();

        }
    }
 // ================= CẬP NHẬT ĐĂNG XUẤT =================
    public void updateLogout(int maLichSu) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            LichSuDangNhap ls = em.find(LichSuDangNhap.class, maLichSu);

            if (ls != null) {

                ls.setThoiGianDangXuat(
                        new Timestamp(System.currentTimeMillis()));

                ls.setTrangThai("Đã đăng xuất");

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
}
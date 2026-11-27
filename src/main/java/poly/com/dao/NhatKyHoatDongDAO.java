package poly.com.dao;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import poly.com.entity.NhatKyHoatDong;
import poly.com.utils.JpaUtil;

public class NhatKyHoatDongDAO extends GenericDAO<NhatKyHoatDong> {

    public NhatKyHoatDongDAO() {
        super(NhatKyHoatDong.class);
    }

    // ================= TÌM THEO MÃ =================
    public NhatKyHoatDong findByMaNhatKy(int maNhatKy) {

        return findById(maNhatKy);

    }

    // ================= NHẬT KÝ THEO TÀI KHOẢN =================
    public List<NhatKyHoatDong> findByTaiKhoan(int maTaiKhoan) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM NhatKyHoatDong
                    WHERE taiKhoan.maTaiKhoan = :maTK
                    ORDER BY thoiGian DESC
                    """;

            TypedQuery<NhatKyHoatDong> query =
                    em.createQuery(jpql, NhatKyHoatDong.class);

            query.setParameter("maTK", maTaiKhoan);

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    // ================= TÌM THEO HÀNH ĐỘNG =================
    public List<NhatKyHoatDong> findByHanhDong(String hanhDong) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM NhatKyHoatDong
                    WHERE hanhDong = :hd
                    ORDER BY thoiGian DESC
                    """;

            TypedQuery<NhatKyHoatDong> query =
                    em.createQuery(jpql, NhatKyHoatDong.class);

            query.setParameter("hd", hanhDong);

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    // ================= TÌM THEO BẢNG TÁC ĐỘNG =================
    public List<NhatKyHoatDong> findByBangTacDong(String bangTacDong) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM NhatKyHoatDong
                    WHERE bangTacDong = :bang
                    ORDER BY thoiGian DESC
                    """;

            TypedQuery<NhatKyHoatDong> query =
                    em.createQuery(jpql, NhatKyHoatDong.class);

            query.setParameter("bang", bangTacDong);

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    // ================= TÌM THEO KHOẢNG THỜI GIAN =================
    public List<NhatKyHoatDong> findByDateRange(Timestamp from, Timestamp to) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM NhatKyHoatDong
                    WHERE thoiGian BETWEEN :fromDate AND :toDate
                    ORDER BY thoiGian DESC
                    """;

            TypedQuery<NhatKyHoatDong> query =
                    em.createQuery(jpql, NhatKyHoatDong.class);

            query.setParameter("fromDate", from);
            query.setParameter("toDate", to);

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    // ================= TÌM KIẾM =================
    public List<NhatKyHoatDong> search(String keyword) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM NhatKyHoatDong
                    WHERE LOWER(hanhDong) LIKE LOWER(:kw)
                       OR LOWER(bangTacDong) LIKE LOWER(:kw)
                       OR LOWER(ghiChu) LIKE LOWER(:kw)
                    ORDER BY thoiGian DESC
                    """;

            TypedQuery<NhatKyHoatDong> query =
                    em.createQuery(jpql, NhatKyHoatDong.class);

            query.setParameter("kw", "%" + keyword + "%");

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    // ================= HOẠT ĐỘNG MỚI NHẤT =================
    public List<NhatKyHoatDong> findLatest(int limit) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM NhatKyHoatDong
                    ORDER BY thoiGian DESC
                    """;

            return em.createQuery(jpql, NhatKyHoatDong.class)
                    .setMaxResults(limit)
                    .getResultList();

        } finally {

            em.close();

        }

    }

    // ================= ĐẾM TỔNG SỐ =================
    public long count() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            return em.createQuery(
                    "SELECT COUNT(n) FROM NhatKyHoatDong n",
                    Long.class)
                    .getSingleResult();

        } finally {

            em.close();

        }

    }
 // ================= LẤY TOÀN BỘ NHẬT KÝ =================
    public List<NhatKyHoatDong> findAll() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM NhatKyHoatDong
                    ORDER BY thoiGian DESC
                    """;

            return em.createQuery(jpql, NhatKyHoatDong.class)
                    .getResultList();

        } finally {

            em.close();

        }

    }

}
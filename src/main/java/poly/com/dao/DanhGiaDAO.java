package poly.com.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import poly.com.entity.DanhGia;
import poly.com.utils.JpaUtil;

public class DanhGiaDAO extends GenericDAO<DanhGia> {

    public DanhGiaDAO() {
        super(DanhGia.class);
    }

    // ================= TÌM THEO MÃ =================

    public DanhGia findByMaDanhGia(int maDanhGia) {

        return findById(maDanhGia);

    }

    // ================= TÌM ĐÁNH GIÁ CỦA BỆNH NHÂN =================

    public List<DanhGia> findByBenhNhan(int maBenhNhan) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    SELECT dg
                    FROM DanhGia dg
                    JOIN FETCH dg.bacSi
                    WHERE dg.benhNhan.maBenhNhan = :maBenhNhan
                    ORDER BY dg.ngayDanhGia DESC
                    """;

            TypedQuery<DanhGia> query =
                    em.createQuery(jpql, DanhGia.class);

            query.setParameter("maBenhNhan", maBenhNhan);

            return query.getResultList();

        } finally {

            em.close();

        }
    }

    // ================= TÌM ĐÁNH GIÁ CỦA BÁC SĨ =================

    public List<DanhGia> findByBacSi(int maBacSi) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    SELECT dg
                    FROM DanhGia dg
                    JOIN FETCH dg.benhNhan
                    WHERE dg.bacSi.maBacSi = :maBacSi
                    ORDER BY dg.ngayDanhGia DESC
                    """;

            TypedQuery<DanhGia> query =
                    em.createQuery(jpql, DanhGia.class);

            query.setParameter("maBacSi", maBacSi);

            return query.getResultList();

        } finally {

            em.close();

        }
    }

    // ================= KIỂM TRA ĐÃ ĐÁNH GIÁ =================

    public DanhGia findByBenhNhanAndBacSi(
            int maBenhNhan,
            int maBacSi) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    SELECT dg
                    FROM DanhGia dg
                    WHERE dg.benhNhan.maBenhNhan = :maBenhNhan
                    AND dg.bacSi.maBacSi = :maBacSi
                    """;

            TypedQuery<DanhGia> query =
                    em.createQuery(jpql, DanhGia.class);

            query.setParameter("maBenhNhan", maBenhNhan);
            query.setParameter("maBacSi", maBacSi);

            List<DanhGia> list = query.getResultList();

            return list.isEmpty() ? null : list.get(0);

        } finally {

            em.close();

        }
    }

    // ================= TÍNH ĐIỂM TRUNG BÌNH =================

    public Double getAverageRating(int maBacSi) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    SELECT AVG(dg.soSao)
                    FROM DanhGia dg
                    WHERE dg.bacSi.maBacSi = :maBacSi
                    """;

            return em.createQuery(jpql, Double.class)
                    .setParameter("maBacSi", maBacSi)
                    .getSingleResult();

        } finally {

            em.close();

        }
    }

    // ================= ĐẾM SỐ ĐÁNH GIÁ =================

    public long countByBacSi(int maBacSi) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    SELECT COUNT(dg)
                    FROM DanhGia dg
                    WHERE dg.bacSi.maBacSi = :maBacSi
                    """;

            return em.createQuery(jpql, Long.class)
                    .setParameter("maBacSi", maBacSi)
                    .getSingleResult();

        } finally {

            em.close();

        }
    }

    // ================= TẤT CẢ ĐÁNH GIÁ =================

    public List<DanhGia> findAllWithDetails() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    SELECT dg
                    FROM DanhGia dg
                    JOIN FETCH dg.benhNhan
                    JOIN FETCH dg.bacSi
                    ORDER BY dg.ngayDanhGia DESC
                    """;

            return em.createQuery(
                    jpql,
                    DanhGia.class
            ).getResultList();

        } finally {

            em.close();

        }
    }
}
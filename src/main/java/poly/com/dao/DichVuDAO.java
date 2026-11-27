package poly.com.dao;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import poly.com.entity.DichVu;
import poly.com.utils.JpaUtil;

public class DichVuDAO extends GenericDAO<DichVu> {

    public DichVuDAO() {
        super(DichVu.class);
    }

    // ================= TÌM THEO MÃ =================
    public DichVu findByMaDichVu(int maDichVu) {

        return findById(maDichVu);

    }

    // ================= TÌM THEO TÊN =================
    public DichVu findByTenDichVu(String tenDichVu) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM DichVu
                    WHERE tenDichVu = :ten
                    """;

            TypedQuery<DichVu> query =
                    em.createQuery(jpql, DichVu.class);

            query.setParameter("ten", tenDichVu);

            List<DichVu> list = query.getResultList();

            return list.isEmpty() ? null : list.get(0);

        } finally {

            em.close();

        }

    }

    // ================= KIỂM TRA TỒN TẠI =================
    public boolean existsTenDichVu(String tenDichVu) {

        return findByTenDichVu(tenDichVu) != null;

    }

    // ================= TÌM KIẾM =================
    public List<DichVu> search(String keyword) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM DichVu
                    WHERE LOWER(tenDichVu) LIKE LOWER(:kw)
                    ORDER BY tenDichVu
                    """;

            TypedQuery<DichVu> query =
                    em.createQuery(jpql, DichVu.class);

            query.setParameter("kw", "%" + keyword + "%");

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    // ================= TÌM THEO KHOẢNG GIÁ =================
    public List<DichVu> findByPrice(BigDecimal min, BigDecimal max) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM DichVu
                    WHERE giaDichVu BETWEEN :min AND :max
                    ORDER BY giaDichVu
                    """;

            TypedQuery<DichVu> query =
                    em.createQuery(jpql, DichVu.class);

            query.setParameter("min", min);
            query.setParameter("max", max);

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    // ================= SẮP XẾP GIÁ TĂNG =================
    public List<DichVu> findAllOrderByPriceAsc() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM DichVu
                    ORDER BY giaDichVu ASC
                    """;

            return em.createQuery(jpql, DichVu.class)
                    .getResultList();

        } finally {

            em.close();

        }

    }

    // ================= SẮP XẾP GIÁ GIẢM =================
    public List<DichVu> findAllOrderByPriceDesc() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM DichVu
                    ORDER BY giaDichVu DESC
                    """;

            return em.createQuery(jpql, DichVu.class)
                    .getResultList();

        } finally {

            em.close();

        }

    }

    // ================= ĐẾM TỔNG SỐ DỊCH VỤ =================
    public long count() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = "SELECT COUNT(d) FROM DichVu d";

            return em.createQuery(jpql, Long.class)
                    .getSingleResult();

        } finally {

            em.close();

        }

    }

}
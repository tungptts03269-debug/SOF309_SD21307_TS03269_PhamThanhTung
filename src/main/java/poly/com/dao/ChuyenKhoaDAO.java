package poly.com.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import poly.com.entity.ChuyenKhoa;
import poly.com.utils.JpaUtil;

public class ChuyenKhoaDAO extends GenericDAO<ChuyenKhoa> {

    public ChuyenKhoaDAO() {
        super(ChuyenKhoa.class);
    }

    // ================= TÌM THEO MÃ =================
    public ChuyenKhoa findByMaChuyenKhoa(int maChuyenKhoa) {

        return findById(maChuyenKhoa);

    }

    // ================= TÌM THEO TÊN =================
    public ChuyenKhoa findByTenChuyenKhoa(String tenChuyenKhoa) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM ChuyenKhoa
                    WHERE tenChuyenKhoa = :ten
                    """;

            TypedQuery<ChuyenKhoa> query =
                    em.createQuery(jpql, ChuyenKhoa.class);

            query.setParameter("ten", tenChuyenKhoa);

            List<ChuyenKhoa> list = query.getResultList();

            return list.isEmpty() ? null : list.get(0);

        } finally {

            em.close();

        }

    }

    // ================= KIỂM TRA TỒN TẠI =================
    public boolean existsTenChuyenKhoa(String tenChuyenKhoa) {

        return findByTenChuyenKhoa(tenChuyenKhoa) != null;

    }

    // ================= TÌM KIẾM =================
    public List<ChuyenKhoa> search(String keyword) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM ChuyenKhoa
                    WHERE LOWER(tenChuyenKhoa) LIKE LOWER(:kw)
                       OR LOWER(moTa) LIKE LOWER(:kw)
                    ORDER BY tenChuyenKhoa
                    """;

            TypedQuery<ChuyenKhoa> query =
                    em.createQuery(jpql, ChuyenKhoa.class);

            query.setParameter("kw", "%" + keyword + "%");

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    // ================= LẤY DANH SÁCH A-Z =================
    public List<ChuyenKhoa> findAllOrderByName() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM ChuyenKhoa
                    ORDER BY tenChuyenKhoa ASC
                    """;

            return em.createQuery(jpql, ChuyenKhoa.class)
                    .getResultList();

        } finally {

            em.close();

        }

    }

    // ================= ĐẾM TỔNG SỐ =================
    public long count() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = "SELECT COUNT(c) FROM ChuyenKhoa c";

            return em.createQuery(jpql, Long.class)
                    .getSingleResult();

        } finally {

            em.close();

        }

    }

}
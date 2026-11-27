package poly.com.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import poly.com.entity.HoSoBenhAn;
import poly.com.utils.JpaUtil;

public class HoSoBenhAnDAO extends GenericDAO<HoSoBenhAn> {

    public HoSoBenhAnDAO() {
        super(HoSoBenhAn.class);
    }

    // ================= TÌM THEO MÃ =================
    public HoSoBenhAn findByMaHoSo(int maHoSo) {

        return findById(maHoSo);

    }

    // ================= TÌM THEO LỊCH KHÁM =================
    public HoSoBenhAn findByLichKham(int maLichKham) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM HoSoBenhAn
                    WHERE lichKham.maLichKham = :maLK
                    """;

            TypedQuery<HoSoBenhAn> query =
                    em.createQuery(jpql, HoSoBenhAn.class);

            query.setParameter("maLK", maLichKham);

            List<HoSoBenhAn> list = query.getResultList();

            return list.isEmpty() ? null : list.get(0);

        } finally {

            em.close();

        }

    }

    // ================= KIỂM TRA ĐÃ CÓ HỒ SƠ =================
    public boolean existsByLichKham(int maLichKham) {

        return findByLichKham(maLichKham) != null;

    }

    // ================= TÌM THEO TRIỆU CHỨNG =================
    public List<HoSoBenhAn> findByTrieuChung(String trieuChung) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM HoSoBenhAn
                    WHERE LOWER(trieuChung) LIKE LOWER(:tc)
                    ORDER BY maHoSo DESC
                    """;

            TypedQuery<HoSoBenhAn> query =
                    em.createQuery(jpql, HoSoBenhAn.class);

            query.setParameter("tc", "%" + trieuChung + "%");

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    // ================= TÌM THEO CHẨN ĐOÁN =================
    public List<HoSoBenhAn> findByChanDoan(String chanDoan) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM HoSoBenhAn
                    WHERE LOWER(chanDoan) LIKE LOWER(:cd)
                    ORDER BY maHoSo DESC
                    """;

            TypedQuery<HoSoBenhAn> query =
                    em.createQuery(jpql, HoSoBenhAn.class);

            query.setParameter("cd", "%" + chanDoan + "%");

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    // ================= TÌM THEO KẾT LUẬN =================
    public List<HoSoBenhAn> findByKetLuan(String ketLuan) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM HoSoBenhAn
                    WHERE LOWER(ketLuan) LIKE LOWER(:kl)
                    ORDER BY maHoSo DESC
                    """;

            TypedQuery<HoSoBenhAn> query =
                    em.createQuery(jpql, HoSoBenhAn.class);

            query.setParameter("kl", "%" + ketLuan + "%");

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    // ================= TÌM KIẾM =================
    public List<HoSoBenhAn> search(String keyword) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM HoSoBenhAn
                    WHERE LOWER(trieuChung) LIKE LOWER(:kw)
                       OR LOWER(chanDoan) LIKE LOWER(:kw)
                       OR LOWER(ketLuan) LIKE LOWER(:kw)
                    ORDER BY maHoSo DESC
                    """;

            TypedQuery<HoSoBenhAn> query =
                    em.createQuery(jpql, HoSoBenhAn.class);

            query.setParameter("kw", "%" + keyword + "%");

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    // ================= HỒ SƠ MỚI NHẤT =================
    public List<HoSoBenhAn> findLatest() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM HoSoBenhAn
                    ORDER BY maHoSo DESC
                    """;

            return em.createQuery(jpql, HoSoBenhAn.class)
                    .setMaxResults(10)
                    .getResultList();

        } finally {

            em.close();

        }

    }

    // ================= ĐẾM TỔNG HỒ SƠ =================
    public long count() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = "SELECT COUNT(h) FROM HoSoBenhAn h";

            return em.createQuery(jpql, Long.class)
                    .getSingleResult();

        } finally {

            em.close();

        }

    }

}
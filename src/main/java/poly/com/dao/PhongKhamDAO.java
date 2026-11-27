package poly.com.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import poly.com.entity.PhongKham;
import poly.com.utils.JpaUtil;

public class PhongKhamDAO extends GenericDAO<PhongKham> {

    public PhongKhamDAO() {
        super(PhongKham.class);
    }

    // ================= TÌM THEO MÃ =================
    public PhongKham findByMaPhongKham(int maPhongKham) {

        return findById(maPhongKham);

    }

    // ================= TÌM THEO TÊN =================
    public PhongKham findByTenPhongKham(String tenPhongKham) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM PhongKham
                    WHERE tenPhongKham = :ten
                    """;

            List<PhongKham> list = em.createQuery(jpql, PhongKham.class)
                    .setParameter("ten", tenPhongKham)
                    .getResultList();

            return list.isEmpty() ? null : list.get(0);

        } finally {

            em.close();

        }

    }

    // ================= TÌM THEO EMAIL =================
    public PhongKham findByEmail(String email) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM PhongKham
                    WHERE email = :email
                    """;

            List<PhongKham> list = em.createQuery(jpql, PhongKham.class)
                    .setParameter("email", email)
                    .getResultList();

            return list.isEmpty() ? null : list.get(0);

        } finally {

            em.close();

        }

    }

    // ================= TÌM THEO SỐ ĐIỆN THOẠI =================
    public PhongKham findBySoDienThoai(String soDienThoai) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM PhongKham
                    WHERE soDienThoai = :sdt
                    """;

            List<PhongKham> list = em.createQuery(jpql, PhongKham.class)
                    .setParameter("sdt", soDienThoai)
                    .getResultList();

            return list.isEmpty() ? null : list.get(0);

        } finally {

            em.close();

        }

    }

    // ================= TÌM KIẾM THEO TÊN =================
    public List<PhongKham> search(String keyword) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM PhongKham
                    WHERE LOWER(tenPhongKham) LIKE LOWER(:kw)
                    ORDER BY tenPhongKham
                    """;

            TypedQuery<PhongKham> query =
                    em.createQuery(jpql, PhongKham.class);

            query.setParameter("kw", "%" + keyword + "%");

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    // ================= TÌM THEO ĐỊA CHỈ =================
    public List<PhongKham> findByDiaChi(String diaChi) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM PhongKham
                    WHERE LOWER(diaChi) LIKE LOWER(:dc)
                    ORDER BY tenPhongKham
                    """;

            TypedQuery<PhongKham> query =
                    em.createQuery(jpql, PhongKham.class);

            query.setParameter("dc", "%" + diaChi + "%");

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    // ================= KIỂM TRA EMAIL =================
    public boolean existsEmail(String email) {

        return findByEmail(email) != null;

    }

    // ================= KIỂM TRA SỐ ĐIỆN THOẠI =================
    public boolean existsPhone(String soDienThoai) {

        return findBySoDienThoai(soDienThoai) != null;

    }

    // ================= KIỂM TRA TÊN PHÒNG KHÁM =================
    public boolean existsTenPhongKham(String tenPhongKham) {

        return findByTenPhongKham(tenPhongKham) != null;

    }

    // ================= ĐẾM TỔNG SỐ =================
    public long count() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            return em.createQuery(
                    "SELECT COUNT(p) FROM PhongKham p",
                    Long.class)
                    .getSingleResult();

        } finally {

            em.close();

        }

    }

}
package poly.com.dao;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import poly.com.entity.BenhNhan;
import poly.com.utils.JpaUtil;

public class BenhNhanDAO extends GenericDAO<BenhNhan> {

    public BenhNhanDAO() {
        super(BenhNhan.class);
    }

    // ================= TÌM THEO MÃ =================
    public BenhNhan findByMaBenhNhan(int maBenhNhan) {

        return findById(maBenhNhan);

    }

    // ================= TÌM THEO TÀI KHOẢN =================
    public BenhNhan findByTaiKhoan(int maTaiKhoan) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM BenhNhan
                    WHERE taiKhoan.maTaiKhoan = :maTK
                    """;

            TypedQuery<BenhNhan> query =
                    em.createQuery(jpql, BenhNhan.class);

            query.setParameter("maTK", maTaiKhoan);

            List<BenhNhan> list = query.getResultList();

            return list.isEmpty() ? null : list.get(0);

        } finally {

            em.close();

        }

    }

    // ================= TÌM THEO HỌ TÊN =================
    public List<BenhNhan> findByHoTen(String hoTen) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM BenhNhan
                    WHERE LOWER(hoTen) LIKE LOWER(:ten)
                    ORDER BY hoTen
                    """;

            TypedQuery<BenhNhan> query =
                    em.createQuery(jpql, BenhNhan.class);

            query.setParameter("ten", "%" + hoTen + "%");

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    // ================= TÌM THEO SỐ ĐIỆN THOẠI =================
    public BenhNhan findBySoDienThoai(String soDienThoai) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM BenhNhan
                    WHERE soDienThoai = :sdt
                    """;

            TypedQuery<BenhNhan> query =
                    em.createQuery(jpql, BenhNhan.class);

            query.setParameter("sdt", soDienThoai);

            List<BenhNhan> list = query.getResultList();

            return list.isEmpty() ? null : list.get(0);

        } finally {

            em.close();

        }

    }

    // ================= KIỂM TRA SỐ ĐIỆN THOẠI =================
    public boolean existsPhone(String phone) {

        return findBySoDienThoai(phone) != null;

    }

    // ================= TÌM THEO GIỚI TÍNH =================
    public List<BenhNhan> findByGioiTinh(String gioiTinh) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM BenhNhan
                    WHERE gioiTinh = :gt
                    ORDER BY hoTen
                    """;

            TypedQuery<BenhNhan> query =
                    em.createQuery(jpql, BenhNhan.class);

            query.setParameter("gt", gioiTinh);

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    // ================= TÌM THEO NGÀY SINH =================
    public List<BenhNhan> findByNgaySinh(Date ngaySinh) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM BenhNhan
                    WHERE ngaySinh = :ns
                    ORDER BY hoTen
                    """;

            TypedQuery<BenhNhan> query =
                    em.createQuery(jpql, BenhNhan.class);

            query.setParameter("ns", ngaySinh);

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    // ================= TÌM THEO ĐỊA CHỈ =================
    public List<BenhNhan> findByDiaChi(String diaChi) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM BenhNhan
                    WHERE LOWER(diaChi) LIKE LOWER(:dc)
                    ORDER BY hoTen
                    """;

            TypedQuery<BenhNhan> query =
                    em.createQuery(jpql, BenhNhan.class);

            query.setParameter("dc", "%" + diaChi + "%");

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    // ================= TÌM KIẾM =================
    public List<BenhNhan> search(String keyword) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM BenhNhan
                    WHERE LOWER(hoTen) LIKE LOWER(:kw)
                       OR LOWER(soDienThoai) LIKE LOWER(:kw)
                       OR LOWER(diaChi) LIKE LOWER(:kw)
                       OR LOWER(gioiTinh) LIKE LOWER(:kw)
                    ORDER BY hoTen
                    """;

            TypedQuery<BenhNhan> query =
                    em.createQuery(jpql, BenhNhan.class);

            query.setParameter("kw", "%" + keyword + "%");

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    // ================= GIỚI TÍNH + TỪ KHÓA =================
    public List<BenhNhan> findByGioiTinhAndKeyword(String gioiTinh, String keyword) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM BenhNhan
                    WHERE gioiTinh = :gt
                    AND LOWER(hoTen) LIKE LOWER(:kw)
                    ORDER BY hoTen
                    """;

            TypedQuery<BenhNhan> query =
                    em.createQuery(jpql, BenhNhan.class);

            query.setParameter("gt", gioiTinh);
            query.setParameter("kw", "%" + keyword + "%");

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    // ================= ĐẾM SỐ BỆNH NHÂN =================
    public long count() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = "SELECT COUNT(b) FROM BenhNhan b";

            return em.createQuery(jpql, Long.class)
                    .getSingleResult();

        } finally {

            em.close();

        }

    }

}
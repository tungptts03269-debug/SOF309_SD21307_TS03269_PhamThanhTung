package poly.com.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import poly.com.entity.BacSi;
import poly.com.utils.JpaUtil;

public class BacSiDAO extends GenericDAO<BacSi> {

    public BacSiDAO() {
        super(BacSi.class);
    }

    // ================= LẤY BÁC SĨ THEO CHUYÊN KHOA =================
    public List<BacSi> findByChuyenKhoa(int maChuyenKhoa) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM BacSi
                    WHERE chuyenKhoa.maChuyenKhoa = :maCK
                    ORDER BY hoTen
                    """;

            TypedQuery<BacSi> query = em.createQuery(jpql, BacSi.class);

            query.setParameter("maCK", maChuyenKhoa);

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    // ================= LẤY BÁC SĨ THEO PHÒNG KHÁM =================
    public List<BacSi> findByPhongKham(int maPhongKham) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM BacSi
                    WHERE phongKham.maPhongKham = :maPK
                    ORDER BY hoTen
                    """;

            TypedQuery<BacSi> query = em.createQuery(jpql, BacSi.class);

            query.setParameter("maPK", maPhongKham);

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    // ================= LẤY BÁC SĨ THEO TÀI KHOẢN =================
    public BacSi findByTaiKhoan(int maTaiKhoan) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM BacSi
                    WHERE taiKhoan.maTaiKhoan = :maTK
                    """;

            TypedQuery<BacSi> query = em.createQuery(jpql, BacSi.class);

            query.setParameter("maTK", maTaiKhoan);

            List<BacSi> list = query.getResultList();

            return list.isEmpty() ? null : list.get(0);

        } finally {

            em.close();

        }

    }

    // ================= TÌM THEO MÃ =================
    public BacSi findByMaBacSi(int maBacSi) {

        return findById(maBacSi);

    }

    // ================= TÌM THEO TÊN =================
    public List<BacSi> findByHoTen(String hoTen) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM BacSi
                    WHERE LOWER(hoTen) LIKE LOWER(:ten)
                    ORDER BY hoTen
                    """;

            TypedQuery<BacSi> query = em.createQuery(jpql, BacSi.class);

            query.setParameter("ten", "%" + hoTen + "%");

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    // ================= TÌM THEO SỐ ĐIỆN THOẠI =================
    public BacSi findByPhone(String phone) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM BacSi
                    WHERE soDienThoai = :phone
                    """;

            TypedQuery<BacSi> query = em.createQuery(jpql, BacSi.class);

            query.setParameter("phone", phone);

            List<BacSi> list = query.getResultList();

            return list.isEmpty() ? null : list.get(0);

        } finally {

            em.close();

        }

    }

    // ================= KIỂM TRA SỐ ĐIỆN THOẠI =================
    public boolean existsPhone(String phone) {

        return findByPhone(phone) != null;

    }

    // ================= TÌM THEO KINH NGHIỆM =================
    public List<BacSi> findByKinhNghiem(String kinhNghiem) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM BacSi
                    WHERE kinhNghiem = :kn
                    ORDER BY hoTen
                    """;

            TypedQuery<BacSi> query = em.createQuery(jpql, BacSi.class);

            query.setParameter("kn", kinhNghiem);

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    // ================= TÌM KIẾM =================
    public List<BacSi> search(String keyword) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM BacSi
                    WHERE LOWER(hoTen) LIKE LOWER(:kw)
                       OR LOWER(soDienThoai) LIKE LOWER(:kw)
                       OR LOWER(chuyenKhoa.tenChuyenKhoa) LIKE LOWER(:kw)
                       OR LOWER(phongKham.tenPhongKham) LIKE LOWER(:kw)
                    ORDER BY hoTen
                    """;

            TypedQuery<BacSi> query = em.createQuery(jpql, BacSi.class);

            query.setParameter("kw", "%" + keyword + "%");

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    // ================= CHUYÊN KHOA + TỪ KHÓA =================
    public List<BacSi> findByChuyenKhoaAndKeyword(int maCK, String keyword) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM BacSi
                    WHERE chuyenKhoa.maChuyenKhoa = :maCK
                    AND LOWER(hoTen) LIKE LOWER(:kw)
                    ORDER BY hoTen
                    """;

            TypedQuery<BacSi> query = em.createQuery(jpql, BacSi.class);

            query.setParameter("maCK", maCK);
            query.setParameter("kw", "%" + keyword + "%");

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    // ================= PHÒNG KHÁM + TỪ KHÓA =================
    public List<BacSi> findByPhongKhamAndKeyword(int maPK, String keyword) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM BacSi
                    WHERE phongKham.maPhongKham = :maPK
                    AND LOWER(hoTen) LIKE LOWER(:kw)
                    ORDER BY hoTen
                    """;

            TypedQuery<BacSi> query = em.createQuery(jpql, BacSi.class);

            query.setParameter("maPK", maPK);
            query.setParameter("kw", "%" + keyword + "%");

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    // ================= ĐẾM SỐ LƯỢNG BÁC SĨ =================
    public long count() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = "SELECT COUNT(b) FROM BacSi b";

            return em.createQuery(jpql, Long.class)
                    .getSingleResult();

        } finally {

            em.close();

        }

    }

}
package poly.com.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import poly.com.entity.TaiKhoan;
import poly.com.utils.JpaUtil;

public class TaiKhoanDAO extends GenericDAO<TaiKhoan> {

    public TaiKhoanDAO() {
        super(TaiKhoan.class);
    }

    // ================= TÌM THEO MÃ =================
    public TaiKhoan findByMaTaiKhoan(int maTaiKhoan) {
        return findById(maTaiKhoan);
    }

    // ================= ĐĂNG NHẬP =================
    public TaiKhoan login(String username, String password) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM TaiKhoan
                    WHERE tenDangNhap = :u
                    AND matKhau = :p
                    """;

            TypedQuery<TaiKhoan> query = em.createQuery(jpql, TaiKhoan.class);

            query.setParameter("u", username);
            query.setParameter("p", password);

            return query.getSingleResult();

        } catch (NoResultException e) {

            return null;

        } finally {

            em.close();

        }

    }

    // ================= ĐĂNG NHẬP THEO VAI TRÒ =================
    public TaiKhoan login(String username, String password, String role) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM TaiKhoan
                    WHERE tenDangNhap = :u
                    AND matKhau = :p
                    AND vaiTro = :r
                    """;

            TypedQuery<TaiKhoan> query = em.createQuery(jpql, TaiKhoan.class);

            query.setParameter("u", username);
            query.setParameter("p", password);
            query.setParameter("r", role);

            return query.getSingleResult();

        } catch (NoResultException e) {

            return null;

        } finally {

            em.close();

        }

    }

    // ================= TÌM USERNAME =================
    public TaiKhoan findByUsername(String username) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM TaiKhoan
                    WHERE tenDangNhap = :u
                    """;

            TypedQuery<TaiKhoan> query = em.createQuery(jpql, TaiKhoan.class);

            query.setParameter("u", username);

            return query.getSingleResult();

        } catch (NoResultException e) {

            return null;

        } finally {

            em.close();

        }

    }

    // ================= TÌM EMAIL =================
    public TaiKhoan findByEmail(String email) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM TaiKhoan
                    WHERE email = :e
                    """;

            TypedQuery<TaiKhoan> query = em.createQuery(jpql, TaiKhoan.class);

            query.setParameter("e", email);

            return query.getSingleResult();

        } catch (NoResultException e) {

            return null;

        } finally {

            em.close();

        }

    }

    // ================= TÌM SỐ ĐIỆN THOẠI =================
    public TaiKhoan findByPhone(String phone) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM TaiKhoan
                    WHERE soDienThoai = :p
                    """;

            TypedQuery<TaiKhoan> query = em.createQuery(jpql, TaiKhoan.class);

            query.setParameter("p", phone);

            return query.getSingleResult();

        } catch (NoResultException e) {

            return null;

        } finally {

            em.close();

        }

    }

    // ================= KIỂM TRA USERNAME =================
    public boolean existsUsername(String username) {
        return findByUsername(username) != null;
    }

    // ================= KIỂM TRA EMAIL =================
    public boolean existsEmail(String email) {
        return findByEmail(email) != null;
    }

    // ================= KIỂM TRA PHONE =================
    public boolean existsPhone(String phone) {
        return findByPhone(phone) != null;
    }

    // ================= ĐĂNG KÝ =================
    public boolean register(TaiKhoan tk) {

        if (existsUsername(tk.getTenDangNhap()))
            return false;

        if (existsEmail(tk.getEmail()))
            return false;

        if (existsPhone(tk.getSoDienThoai()))
            return false;

        insert(tk);

        return true;

    }

    // ================= CẬP NHẬT THÔNG TIN =================
    public boolean updateProfile(TaiKhoan tk) {

        TaiKhoan old = findById(tk.getMaTaiKhoan());

        if (old == null) {
            return false;
        }

        update(tk);

        return true;

    }

    // ================= ĐỔI MẬT KHẨU =================
    public boolean changePassword(int id, String newPassword) {

        TaiKhoan tk = findById(id);

        if (tk == null)
            return false;

        tk.setMatKhau(newPassword);

        update(tk);

        return true;

    }

    // ================= RESET MẬT KHẨU =================
    public boolean resetPassword(String email, String newPassword) {

        TaiKhoan tk = findByEmail(email);

        if (tk == null)
            return false;

        tk.setMatKhau(newPassword);

        update(tk);

        return true;

    }

    // ================= TÌM THEO VAI TRÒ =================
    public List<TaiKhoan> findByRole(String role) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM TaiKhoan
                    WHERE vaiTro = :r
                    ORDER BY hoTen
                    """;

            return em.createQuery(jpql, TaiKhoan.class)
                    .setParameter("r", role)
                    .getResultList();

        } finally {

            em.close();

        }

    }

    // ================= TÌM THEO HỌ TÊN =================
    public List<TaiKhoan> findByHoTen(String hoTen) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM TaiKhoan
                    WHERE LOWER(hoTen) LIKE LOWER(:ten)
                    ORDER BY hoTen
                    """;

            return em.createQuery(jpql, TaiKhoan.class)
                    .setParameter("ten", "%" + hoTen + "%")
                    .getResultList();

        } finally {

            em.close();

        }

    }

    // ================= TÌM KIẾM =================
    public List<TaiKhoan> search(String keyword) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM TaiKhoan
                    WHERE LOWER(hoTen) LIKE LOWER(:kw)
                       OR LOWER(tenDangNhap) LIKE LOWER(:kw)
                       OR LOWER(email) LIKE LOWER(:kw)
                       OR LOWER(soDienThoai) LIKE LOWER(:kw)
                       OR LOWER(vaiTro) LIKE LOWER(:kw)
                    ORDER BY hoTen
                    """;

            return em.createQuery(jpql, TaiKhoan.class)
                    .setParameter("kw", "%" + keyword + "%")
                    .getResultList();

        } finally {

            em.close();

        }

    }

    // ================= TÌM THEO VAI TRÒ VÀ TỪ KHÓA =================
    public List<TaiKhoan> findByRoleAndKeyword(String role, String keyword) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM TaiKhoan
                    WHERE vaiTro = :role
                    AND LOWER(hoTen) LIKE LOWER(:kw)
                    ORDER BY hoTen
                    """;

            return em.createQuery(jpql, TaiKhoan.class)
                    .setParameter("role", role)
                    .setParameter("kw", "%" + keyword + "%")
                    .getResultList();

        } finally {

            em.close();

        }

    }

    // ================= TÀI KHOẢN MỚI NHẤT =================
    public List<TaiKhoan> findLatest(int limit) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    FROM TaiKhoan
                    ORDER BY maTaiKhoan DESC
                    """;

            return em.createQuery(jpql, TaiKhoan.class)
                    .setMaxResults(limit)
                    .getResultList();

        } finally {

            em.close();

        }

    }

    // ================= ĐẾM THEO VAI TRÒ =================
    public long countByRole(String role) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    SELECT COUNT(t)
                    FROM TaiKhoan t
                    WHERE t.vaiTro = :role
                    """;

            return em.createQuery(jpql, Long.class)
                    .setParameter("role", role)
                    .getSingleResult();

        } finally {

            em.close();

        }

    }

    // ================= ĐẾM TỔNG SỐ =================
    public long count() {

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

}
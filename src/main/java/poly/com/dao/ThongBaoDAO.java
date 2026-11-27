package poly.com.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import poly.com.entity.ThongBao;
import poly.com.utils.JpaUtil;

public class ThongBaoDAO extends GenericDAO<ThongBao> {

    public ThongBaoDAO() {
        super(ThongBao.class);
    }

    // Lấy thông báo theo tài khoản
    public List<ThongBao> findByTaiKhoan(int maTaiKhoan) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    SELECT t
                    FROM ThongBao t
                    WHERE t.taiKhoan.maTaiKhoan = :id
                    ORDER BY t.ngayGui DESC
                    """;

            TypedQuery<ThongBao> query =
                    em.createQuery(jpql, ThongBao.class);

            query.setParameter("id", maTaiKhoan);

            return query.getResultList();

        } finally {
            em.close();
        }
    }

    // Đếm thông báo chưa đọc
    public long countUnread(int maTaiKhoan) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    SELECT COUNT(t)
                    FROM ThongBao t
                    WHERE t.taiKhoan.maTaiKhoan=:id
                    AND t.daDoc=false
                    """;

            return em.createQuery(jpql, Long.class)
                    .setParameter("id", maTaiKhoan)
                    .getSingleResult();

        } finally {
            em.close();
        }
    }

    // Đánh dấu đã đọc
    public void markAsRead(int maThongBao) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            ThongBao tb = em.find(ThongBao.class, maThongBao);

            if (tb != null) {

                tb.setDaDoc(true);

            }

            em.getTransaction().commit();

        } catch (Exception e) {

            em.getTransaction().rollback();
            throw e;

        } finally {

            em.close();

        }

    }

}
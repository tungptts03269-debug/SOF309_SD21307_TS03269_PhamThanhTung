package poly.com.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import poly.com.entity.LienHe;
import poly.com.utils.JpaUtil;

public class LienHeDAO {

    // Thêm liên hệ
    public void create(LienHe lienHe) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(lienHe);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    // Cập nhật liên hệ
    public void update(LienHe lienHe) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(lienHe);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    // Xóa liên hệ
    public void delete(Integer id) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            LienHe lh = em.find(LienHe.class, id);
            if (lh != null) {
                em.remove(lh);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    // Tìm theo ID
    public LienHe findById(Integer id) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            return em.find(LienHe.class, id);
        } finally {
            em.close();
        }
    }

    // Lấy tất cả
    public List<LienHe> findAll() {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            TypedQuery<LienHe> query = em.createQuery(
                    "FROM LienHe l ORDER BY l.ngayGui DESC",
                    LienHe.class);

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Đếm số liên hệ
    public long count() {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT COUNT(l) FROM LienHe l",
                    Long.class)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    // Đếm liên hệ chưa phản hồi
    public long countChuaPhanHoi() {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT COUNT(l) FROM LienHe l WHERE l.trangThai='Chưa phản hồi'",
                    Long.class)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }
}
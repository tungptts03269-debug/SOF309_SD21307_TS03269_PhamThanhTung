package poly.com.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import poly.com.entity.ThongBaoAdmin;
import poly.com.utils.JpaUtil;

public class ThongBaoAdminDAO {

    /*==============================
     * Lấy tất cả thông báo Admin
     ==============================*/
    public List<ThongBaoAdmin> findAll() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            TypedQuery<ThongBaoAdmin> query =
                    em.createQuery(
                            "FROM ThongBaoAdmin ORDER BY ngayGui DESC",
                            ThongBaoAdmin.class);

            return query.getResultList();

        } finally {
            em.close();
        }

    }

    /*==============================
     * Lấy theo ID
     ==============================*/
    public ThongBaoAdmin findById(Integer id) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            return em.find(ThongBaoAdmin.class, id);

        } finally {

            em.close();

        }

    }

    /*==============================
     * Thêm thông báo
     ==============================*/
    public void create(ThongBaoAdmin tb) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            em.persist(tb);

            em.getTransaction().commit();

        } catch (Exception e) {

            em.getTransaction().rollback();

            e.printStackTrace();

        } finally {

            em.close();

        }

    }

    /*==============================
     * Cập nhật
     ==============================*/
    public void update(ThongBaoAdmin tb) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            em.merge(tb);

            em.getTransaction().commit();

        } catch (Exception e) {

            em.getTransaction().rollback();

            e.printStackTrace();

        } finally {

            em.close();

        }

    }

    /*==============================
     * Xóa
     ==============================*/
    public void delete(Integer id) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            ThongBaoAdmin tb = em.find(ThongBaoAdmin.class, id);

            if (tb != null) {

                em.getTransaction().begin();

                em.remove(tb);

                em.getTransaction().commit();

            }

        } catch (Exception e) {

            em.getTransaction().rollback();

            e.printStackTrace();

        } finally {

            em.close();

        }

    }

    /*==============================
     * Thông báo chưa đọc
     ==============================*/
    public List<ThongBaoAdmin> findUnread() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            TypedQuery<ThongBaoAdmin> query =
                    em.createQuery(
                            "FROM ThongBaoAdmin WHERE daDoc = false ORDER BY ngayGui DESC",
                            ThongBaoAdmin.class);

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    /*==============================
     * Đánh dấu đã đọc
     ==============================*/
    public void markAsRead(Integer id) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            ThongBaoAdmin tb = em.find(ThongBaoAdmin.class, id);

            if (tb != null) {

                tb.setDaDoc(true);

                em.merge(tb);

            }

            em.getTransaction().commit();

        } catch (Exception e) {

            em.getTransaction().rollback();

            e.printStackTrace();

        } finally {

            em.close();

        }

    }

    /*==============================
     * Đếm số thông báo chưa đọc
     ==============================*/
    public long countUnread() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            return em.createQuery(
                    "SELECT COUNT(t) FROM ThongBaoAdmin t WHERE t.daDoc=false",
                    Long.class)
                    .getSingleResult();

        } finally {

            em.close();

        }

    }
    public void insert(ThongBaoAdmin tb) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            em.persist(tb);

            em.getTransaction().commit();

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            e.printStackTrace();

        } finally {

            em.close();
        }
    }

}
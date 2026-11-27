package poly.com.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import poly.com.utils.JpaUtil;

public class GenericDAO<T> {

    private Class<T> clazz;

    public GenericDAO(Class<T> clazz) {
        this.clazz = clazz;
    }

    /*====================================
     * INSERT
     ====================================*/
    public void insert(T entity) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            em.persist(entity);

            // Đồng bộ xuống database để sinh IDENTITY ngay
            em.flush();

            em.getTransaction().commit();

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            e.printStackTrace();
            throw e;

        } finally {

            em.close();

        }

    }

    /*====================================
     * UPDATE
     ====================================*/
    public void update(T entity) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            em.merge(entity);

            em.getTransaction().commit();

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            e.printStackTrace();
            throw e;

        } finally {

            em.close();

        }

    }

    /*====================================
     * DELETE
     ====================================*/
    public void delete(Object id) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            T entity = em.find(clazz, id);

            if (entity != null) {
                em.remove(entity);
            }

            em.getTransaction().commit();

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            e.printStackTrace();
            throw e;

        } finally {

            em.close();

        }

    }

    /*====================================
     * FIND BY ID
     ====================================*/
    public T findById(Object id) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            return em.find(clazz, id);

        } finally {

            em.close();

        }

    }

    /*====================================
     * FIND ALL
     ====================================*/
    public List<T> findAll() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = "FROM " + clazz.getSimpleName();

            TypedQuery<T> query =
                    em.createQuery(jpql, clazz);

            return query.getResultList();

        } finally {

            em.close();

        }

    }

    /*====================================
     * COUNT
     ====================================*/
    public long count() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = "SELECT COUNT(e) FROM "
                    + clazz.getSimpleName() + " e";

            return em.createQuery(jpql, Long.class)
                    .getSingleResult();

        } finally {

            em.close();

        }

    }

}
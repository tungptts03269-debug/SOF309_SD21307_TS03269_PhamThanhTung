package poly.com.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import poly.com.entity.LichKham;
import poly.com.entity.YeuCauHuyLich;
import poly.com.utils.JpaUtil;

public class YeuCauHuyLichDAO {

    // =================== DANH SÁCH ===================
    public List<YeuCauHuyLich> findAll() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    SELECT y
                    FROM YeuCauHuyLich y
                    JOIN FETCH y.lichKham lk
                    JOIN FETCH lk.benhNhan
                    JOIN FETCH lk.bacSi
                    JOIN FETCH lk.dichVu
                    ORDER BY y.ngayGui DESC
                    """;

            return em.createQuery(jpql, YeuCauHuyLich.class)
                    .getResultList();

        } finally {

            em.close();

        }

    }

    // =================== TÌM THEO ID ===================
    public YeuCauHuyLich findById(Integer id) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            return em.find(YeuCauHuyLich.class, id);

        } finally {

            em.close();

        }

    }

    // =================== TÌM THEO LỊCH KHÁM ===================
    public YeuCauHuyLich findByLichKham(int maLichKham) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    SELECT y
                    FROM YeuCauHuyLich y
                    WHERE y.lichKham.maLichKham = :id
                    """;

            List<YeuCauHuyLich> list =
                    em.createQuery(jpql, YeuCauHuyLich.class)
                    .setParameter("id", maLichKham)
                    .getResultList();

            return list.isEmpty() ? null : list.get(0);

        } finally {

            em.close();

        }

    }

    // =================== THÊM ===================
    public void create(YeuCauHuyLich yc) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            em.persist(yc);

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

    // =================== CẬP NHẬT ===================
    public void update(YeuCauHuyLich yc) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            em.merge(yc);

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

    // =================== XÓA ===================
    public void delete(Integer id) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            YeuCauHuyLich yc = em.find(YeuCauHuyLich.class, id);

            if (yc != null) {

                em.remove(yc);

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

    // =================== THEO TRẠNG THÁI ===================
    public List<YeuCauHuyLich> findByTrangThai(String trangThai) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    SELECT y
                    FROM YeuCauHuyLich y
                    JOIN FETCH y.lichKham
                    WHERE y.trangThai = :tt
                    ORDER BY y.ngayGui DESC
                    """;

            return em.createQuery(jpql, YeuCauHuyLich.class)
                    .setParameter("tt", trangThai)
                    .getResultList();

        } finally {

            em.close();

        }

    }

    // =================== DUYỆT ===================
    public void approve(Integer id, Integer adminId) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            YeuCauHuyLich yc =
                    em.find(YeuCauHuyLich.class, id);

            if (yc != null) {

                yc.setTrangThai("Đã duyệt");

                yc.setNguoiXuLy(adminId);

                yc.setNgayXuLy(new java.util.Date());

                // Hủy lịch khám

                LichKham lk = yc.getLichKham();

                lk.setTrangThai("Đã hủy");

                em.merge(lk);

                em.merge(yc);

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

    // =================== TỪ CHỐI ===================
    public void reject(Integer id, Integer adminId) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            YeuCauHuyLich yc =
                    em.find(YeuCauHuyLich.class, id);

            if (yc != null) {

                yc.setTrangThai("Từ chối");

                yc.setNguoiXuLy(adminId);

                yc.setNgayXuLy(new java.util.Date());

                em.merge(yc);

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

    // =================== ĐẾM CHỜ DUYỆT ===================
    public long countPending() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            return em.createQuery("""
                    SELECT COUNT(y)
                    FROM YeuCauHuyLich y
                    WHERE y.trangThai='Chờ duyệt'
                    """, Long.class)
                    .getSingleResult();

        } finally {

            em.close();

        }

    }
    public boolean existsByLichKham(int maLichKham) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    SELECT COUNT(y)
                    FROM YeuCauHuyLich y
                    WHERE y.lichKham.maLichKham = :id
                    AND y.trangThai = 'Chờ duyệt'
                    """;

            Long count = em.createQuery(jpql, Long.class)
                    .setParameter("id", maLichKham)
                    .getSingleResult();

            return count > 0;

        } finally {

            em.close();

        }

    }
}
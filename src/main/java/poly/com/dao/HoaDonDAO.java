package poly.com.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import poly.com.entity.HoaDon;
import poly.com.utils.JpaUtil;

public class HoaDonDAO extends GenericDAO<HoaDon> {

    public HoaDonDAO() {
        super(HoaDon.class);
    }

    // ================= LẤY TẤT CẢ HÓA ĐƠN =================
    public List<HoaDon> findAllFetch() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    SELECT h
                    FROM HoaDon h
                    JOIN FETCH h.lichKham l
                    JOIN FETCH l.benhNhan
                    JOIN FETCH l.bacSi
                    JOIN FETCH l.dichVu
                    ORDER BY h.ngayThanhToan DESC
                    """;

            return em.createQuery(jpql, HoaDon.class)
                    .getResultList();

        } finally {

            em.close();

        }
    }

    // ================= TÌM THEO MÃ =================
    public HoaDon findById(int maThanhToan) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    SELECT h
                    FROM HoaDon h
                    JOIN FETCH h.lichKham l
                    JOIN FETCH l.benhNhan
                    JOIN FETCH l.bacSi
                    JOIN FETCH l.dichVu
                    WHERE h.maThanhToan = :id
                    """;

            return em.createQuery(jpql, HoaDon.class)
                    .setParameter("id", maThanhToan)
                    .getSingleResult();

        } finally {

            em.close();

        }
    }

    // ================= TÌM THEO LỊCH KHÁM =================
    public HoaDon findByLichKham(int maLichKham) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    SELECT h
                    FROM HoaDon h
                    JOIN FETCH h.lichKham l
                    JOIN FETCH l.benhNhan
                    JOIN FETCH l.bacSi
                    JOIN FETCH l.dichVu
                    WHERE l.maLichKham = :id
                    """;

            List<HoaDon> list = em.createQuery(jpql, HoaDon.class)
                    .setParameter("id", maLichKham)
                    .getResultList();

            return list.isEmpty() ? null : list.get(0);

        } finally {

            em.close();

        }
    }

    // ================= TÌM KIẾM =================
    public List<HoaDon> search(String keyword) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String jpql = """
                    SELECT h
                    FROM HoaDon h
                    JOIN FETCH h.lichKham l
                    JOIN FETCH l.benhNhan
                    JOIN FETCH l.bacSi
                    JOIN FETCH l.dichVu
                    WHERE LOWER(l.benhNhan.hoTen) LIKE LOWER(:kw)
                       OR LOWER(l.bacSi.hoTen) LIKE LOWER(:kw)
                    ORDER BY h.ngayThanhToan DESC
                    """;

            return em.createQuery(jpql, HoaDon.class)
                    .setParameter("kw", "%" + keyword + "%")
                    .getResultList();

        } finally {

            em.close();

        }
    }

    // ================= ĐẾM HÓA ĐƠN =================
    @Override
    public long count() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            return em.createQuery(
                    "SELECT COUNT(h) FROM HoaDon h",
                    Long.class)
                    .getSingleResult();

        } finally {

            em.close();

        }
    }
}
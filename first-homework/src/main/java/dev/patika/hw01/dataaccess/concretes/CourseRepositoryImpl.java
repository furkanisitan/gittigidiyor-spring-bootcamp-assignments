package dev.patika.hw01.dataaccess.concretes;

import dev.patika.hw01.core.constants.DbConstants;
import dev.patika.hw01.core.helpers.EntityManagerHelper;
import dev.patika.hw01.dataaccess.abstracts.CourseRepository;
import dev.patika.hw01.entities.concretes.Course;
import dev.patika.hw01.entities.concretes.Student;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class CourseRepositoryImpl implements CourseRepository {

    private final EntityManagerHelper emHelper;

    public CourseRepositoryImpl() {
        this.emHelper = new EntityManagerHelper(DbConstants.PERSISTENCE_UNIT_NAME);
    }

    @Override
    public Course findByCode(String code) {
        EntityManager em = emHelper.getEntityManager();
        try {
            return em.createQuery("from Course c where c.code=:code", Course.class).setParameter("code", code).getSingleResult();
        } finally {
            emHelper.close(em);
        }
    }

    @Override
    public Course findByCodeFetchStudents(String code) {
        EntityManager em = emHelper.getEntityManager();
        try {
            return em
                    .createQuery("from Course c left join fetch c.students where c.code=:code", Course.class)
                    .setParameter("code", code)
                    .getSingleResult();
        } finally {
            emHelper.close(em);
        }
    }

    @Override
    public List<Course> findAll() {
        EntityManager em = emHelper.getEntityManager();
        try {
            return em.createQuery("from Course", Course.class).getResultList();
        } finally {
            emHelper.close(em);
        }
    }

    @Override
    public Course findById(Long id) {
        EntityManager em = emHelper.getEntityManager();
        try {
            return em.find(Course.class, id);
        } finally {
            emHelper.close(em);
        }
    }

    @Override
    public void save(Course entity) {
        EntityManager em = emHelper.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            emHelper.close(em);
        }
    }

    @Override
    public void update(Course entity) {
        EntityManager em = emHelper.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            emHelper.close(em);
        }
    }

    @Override
    public void delete(Course entity) {
        deleteById(entity.getId());
    }

    @Override
    public void deleteById(Long id) {
        EntityManager em = emHelper.getEntityManager();
        try {
            em.getTransaction().begin();

            Course course = em.find(Course.class, id);
            em.remove(course);
            List<Student> students = new ArrayList<>(course.getStudents());
            for (Student student : students) {
                student.removeCourse(course);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            emHelper.close(em);
        }
    }
}

package dev.patika.hw02.dataaccess.concretes;

import dev.patika.hw02.dataaccess.abstracts.BaseRepository;
import dev.patika.hw02.dataaccess.abstracts.CourseRepository;
import dev.patika.hw02.entities.concretes.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@Repository
class CourseRepositoryImpl extends BaseRepository<Course, Long> implements CourseRepository {

    private final EntityManager entityManager;

    @Autowired
    protected CourseRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Course findByCode(String code) {
        try {
            return entityManager.createQuery("select c from Course c where c.code=:code", Course.class)
                    .setParameter("code", code).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}

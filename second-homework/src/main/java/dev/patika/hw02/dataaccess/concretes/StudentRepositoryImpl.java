package dev.patika.hw02.dataaccess.concretes;

import dev.patika.hw02.dataaccess.abstracts.BaseRepository;
import dev.patika.hw02.dataaccess.abstracts.StudentRepository;
import dev.patika.hw02.entities.concretes.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
class StudentRepositoryImpl extends BaseRepository<Student, Long> implements StudentRepository {

    @Autowired
    protected StudentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }
}

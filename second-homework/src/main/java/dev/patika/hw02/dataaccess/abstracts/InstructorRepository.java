package dev.patika.hw02.dataaccess.abstracts;

import dev.patika.hw02.entities.concretes.Instructor;

public interface InstructorRepository extends Repository<Instructor, Long> {

    Instructor findByPhoneNumber(String phoneNumber);
}

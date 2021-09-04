package dev.patika.hw01.clients;

import dev.patika.hw01.api.controllers.CourseController;
import dev.patika.hw01.business.abstracts.CourseService;
import dev.patika.hw01.business.concretes.CourseManager;
import dev.patika.hw01.core.constants.DbConstants;
import dev.patika.hw01.core.helpers.EntityManagerHelper;
import dev.patika.hw01.entities.concretes.*;
import dev.patika.hw01.entities.enums.Gender;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityManagerHelper emHelper = new EntityManagerHelper(DbConstants.PERSISTENCE_UNIT_NAME);
        EntityManager em = emHelper.getEntityManager();

        CourseController courseController = new CourseController();
        CourseService courseService = new CourseManager();

        initDatabase();

        // get all courses
        List<Course> courses = courseController.getAll();
        System.out.println("get all courses");
        System.out.println("Number of Courses: " + courses.size());
        System.out.println("----------------------------------");

        // get course by code
        Course course = courseController.getByCode("code1");
        System.out.println("get course by code");
        System.out.println("Course Name: " + course.getName());
        System.out.println("----------------------------------");

        // get students of course
        System.out.println("get students of course");
        for (Student student : courseController.getByCodeWithStudents("code1").getStudents()) {
            System.out.println("Student Name: " + student.getName());
        }
        System.out.println("----------------------------------");

        // create new course
        Course newCourse = new Course("newCourse", "newCourseCode", 6, em.find(Instructor.class, 1L));
        courseController.create(newCourse);
        newCourse = courseController.getByCode("newCourseCode");
        System.out.println("create new course");
        System.out.println("Course Name: " + newCourse.getName());

        // update course
        Course updateCourse = courseController.getById(2L);
        updateCourse.setName("updatedCourse2");
        courseController.update(updateCourse);
        System.out.println("update course");
        System.out.println("Course Name: " + courseController.getById(2L).getName());

        // delete course
        courseController.deleteById(3L);
        System.out.println("delete course");
    }

    private static void initDatabase() {

        // instructors
        VisitingResearcher visitingResearcher = new VisitingResearcher("VS1", "VS1Address", "+905055557555", BigDecimal.valueOf(75.53));
        PermanentInstructor permanentInstructor = new PermanentInstructor("PI1", "PI1Address", "+905055557556", BigDecimal.valueOf(10123.78));

        // courses
        Course course1 = new Course("Course1", "code1", 4, visitingResearcher);
        Course course2 = new Course("Course2", "code2", 3, permanentInstructor);
        Course course3 = new Course("Course3", "code3", 5, permanentInstructor);

        // students
        Student student1 = new Student("Student1", "StudentAddress1", LocalDate.of(2000, 1, 1), Gender.MALE);
        Student student2 = new Student("Student2", "StudentAddress2", LocalDate.of(2001, 2, 2), Gender.FEMALE);
        Student student3 = new Student("Student3", "StudentAddress3", LocalDate.of(1999, 4, 13), Gender.FEMALE);
        Student student4 = new Student("Student4", "StudentAddress4", LocalDate.of(2000, 7, 22), Gender.MALE);

        // relationships
        course1.addStudent(student1);
        course1.addStudent(student3);
        course1.addStudent(student4);

        course2.addStudent(student2);
        course2.addStudent(student4);

        course3.addStudent(student1);
        course3.addStudent(student2);
        course3.addStudent(student3);
        course3.addStudent(student4);

        EntityManagerHelper emHelper = new EntityManagerHelper(DbConstants.PERSISTENCE_UNIT_NAME);
        EntityManager em = emHelper.getEntityManager();

        try {
            em.getTransaction().begin();

            // add instructors
            em.persist(visitingResearcher);
            em.persist(permanentInstructor);

            // add students
            em.persist(student1);
            em.persist(student2);
            em.persist(student3);
            em.persist(student4);

            // add courses
            em.persist(course1);
            em.persist(course2);
            em.persist(course3);

            em.getTransaction().commit();

            System.out.println("All data persisted...");
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            emHelper.close(em);
        }

    }

}

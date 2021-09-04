package dev.patika.schoolmanagementsystem.dataaccess.dataloaders;

import dev.patika.schoolmanagementsystem.dataaccess.CourseRepository;
import dev.patika.schoolmanagementsystem.dataaccess.InstructorRepository;
import dev.patika.schoolmanagementsystem.dataaccess.StudentRepository;
import dev.patika.schoolmanagementsystem.entities.concretes.*;
import dev.patika.schoolmanagementsystem.entities.enums.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DataLoader implements CommandLineRunner {

    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public DataLoader(CourseRepository courseRepository, InstructorRepository instructorRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //region instructors
        Instructor pi1 = getPermanentInstructor("PI1", "+905055557555", "PI1Address", BigDecimal.valueOf(10123.78));
        Instructor pi2 = getPermanentInstructor("PI2", "+905055557554", "PI2Address", BigDecimal.valueOf(5643.77));
        Instructor pi3 = getPermanentInstructor("PI3", "+905055557553", "PI3Address", BigDecimal.valueOf(15689.06));
        Instructor pi4 = getPermanentInstructor("PI4", "+905055557552", "PI4Address", BigDecimal.valueOf(3428.99));
        Instructor vr1 = getVisitingResearcher("VR1", "+905055557556", "VR1Address", BigDecimal.valueOf(75.53));
        Instructor vr2 = getVisitingResearcher("VR2", "+905055557557", "VR2Address", BigDecimal.valueOf(44.86));
        Instructor vr3 = getVisitingResearcher("VR3", "+905055557558", "VR3Address", BigDecimal.valueOf(147.99));
        Instructor vr4 = getVisitingResearcher("VR4", "+905055557559", "VR4Address", BigDecimal.valueOf(66.77));
        instructorRepository.saveAll(Stream.of(pi1, pi2, pi3, pi4, vr1, vr2, vr3, vr4).collect(Collectors.toList()));
        //endregion

        //region courses
        Course c1 = getCourse("Course1", "code1", 4, pi1);
        Course c2 = getCourse("Course2", "code2", 5, vr1);
        Course c3 = getCourse("Course3", "code3", 6, pi1);
        courseRepository.saveAll(Stream.of(c1, c2, c3).collect(Collectors.toList()));
        //endregion

        //region students and student_course
        Student s1 = getStudent("Student1", "StudentAddress1", LocalDate.of(2000, 1, 1), Gender.FEMALE);
        Student s2 = getStudent("Student2", "StudentAddress2", LocalDate.of(1999, 4, 24), Gender.MALE);
        Student s3 = getStudent("Student3", "StudentAddress3", LocalDate.of(1987, 8, 15), Gender.FEMALE);
        Student s4 = getStudent("Student4", "StudentAddress4", LocalDate.of(2005, 2, 27), Gender.MALE);

        s1.addCourse(c1);
        s1.addCourse(c3);
        s2.addCourse(c2);
        s2.addCourse(c3);
        s3.addCourse(c1);
        s3.addCourse(c3);
        s4.addCourse(c1);
        s4.addCourse(c2);
        s4.addCourse(c3);

        studentRepository.saveAll(Stream.of(s1, s2, s3, s4).collect(Collectors.toList()));
        //endregion
    }

    private Course getCourse(String name, String code, int creditScore, Instructor instructor) {
        Course course = new Course();
        course.setName(name);
        course.setCode(code);
        course.setCreditScore(creditScore);
        course.setInstructor(instructor);
        return course;
    }

    private Instructor getPermanentInstructor(String name, String phoneNumber, String address, BigDecimal fixedSalary) {
        PermanentInstructor permanentInstructor = new PermanentInstructor();
        permanentInstructor.setName(name);
        permanentInstructor.setPhoneNumber(phoneNumber);
        permanentInstructor.setAddress(address);
        permanentInstructor.setFixedSalary(fixedSalary);
        return permanentInstructor;
    }

    private Instructor getVisitingResearcher(String name, String phoneNumber, String address, BigDecimal hourlySalary) {
        VisitingResearcher visitingResearcher = new VisitingResearcher();
        visitingResearcher.setName(name);
        visitingResearcher.setPhoneNumber(phoneNumber);
        visitingResearcher.setAddress(address);
        visitingResearcher.setHourlySalary(hourlySalary);
        return visitingResearcher;
    }

    private Student getStudent(String name, String address, LocalDate birthDate, Gender gender) {
        Student student = new Student();
        student.setName(name);
        student.setAddress(address);
        student.setBirthDate(birthDate);
        student.setGender(gender);
        return student;
    }
}

INSERT INTO instructors (instructor_id, phone_number, name, address) VALUES (1, "+905055557556", "PI1", "PI1Address");
INSERT INTO instructors (instructor_id, phone_number, name, address) VALUES (2, "+905055557555", "VS1", "VS1Address");

INSERT INTO permanent_instructors (instructor_id, fixed_salary) VALUES (1, 10123.78);
INSERT INTO visiting_researchers (instructor_id, hourly_salary) VALUES (2, 75.53);

INSERT INTO courses (course_id, instructor_id, code, name, credit_score) VALUES (1, 1, "code1", "Course1", 4);
INSERT INTO courses (course_id, instructor_id, code, name, credit_score) VALUES (2, 2, "code2", "Course2", 5);
INSERT INTO courses (course_id, instructor_id, code, name, credit_score) VALUES (3, 1, "code3", "Course3", 7);

INSERT INTO students (student_id, name, address, birth_date, gender) VALUES (1, "Student1", "StudentAddress1", "2000-1-1", 0);
INSERT INTO students (student_id, name, address, birth_date, gender) VALUES (2, "Student2", "StudentAddress2", "2000-2-2", 1);
INSERT INTO students (student_id, name, address, birth_date, gender) VALUES (3, "Student3", "StudentAddress3", "2000-4-13", 1);
INSERT INTO students (student_id, name, address, birth_date, gender) VALUES (4, "Student4", "StudentAddress4", "2000-7-22", 0);

INSERT INTO student_course (student_id, course_id) VALUES (1, 1);
INSERT INTO student_course (student_id, course_id) VALUES (3, 1);
INSERT INTO student_course (student_id, course_id) VALUES (4, 1);
INSERT INTO student_course (student_id, course_id) VALUES (2, 2);
INSERT INTO student_course (student_id, course_id) VALUES (4, 2);
INSERT INTO student_course (student_id, course_id) VALUES (1, 3);
INSERT INTO student_course (student_id, course_id) VALUES (2, 3);
INSERT INTO student_course (student_id, course_id) VALUES (3, 3);
INSERT INTO student_course (student_id, course_id) VALUES (4, 3);

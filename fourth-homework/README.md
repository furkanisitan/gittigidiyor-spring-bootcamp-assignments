## Dördüncü hafta ödevi son teslim tarihi : 06.08.2021(Gelecek hafta pazartesi) - Saat =>  23:30

![homework](https://user-images.githubusercontent.com/45206582/131386439-6727321a-5a50-4c20-9413-ea4013013434.PNG)

# school-management-system

An example REST API with Spring Boot

- applicationUrl: `http://localhost:8080`

## Filtering

In this API, some endpoints support an optional `filter` parameter. 

If allowed, filtering can be done for one or more fields.
Filter expressions are separated by **commas**. Each filter expression is combined with the **and** operation.

A filter expression consists of 3 components.
* The property or field name
* The operator such as `eq`, `ct` and etc.
* The filter value

The expected filter format is as follows.

`?filter=field_name1<operator1>filter_value1,field_name2<operator2>filter_value2,...`

Below is the list of supported operators.
* `eq` : equal
* `ne` : not equal
* `gt` : greater than
* `lt` : less than
* `ge` : greater than or equal
* `le` : less than or equal
* `st` : starts with
* `en` : ends with
* `ct` : contains

> Filtering may not be supported for every field on an endpoint. Also, not all operators may be supported for a field where filtering is supported. You can find the supported fields and operators for filtering in an endpoint from the description of the relevant endpoint.

The description format is similar to the following.

- `filter=[String]` (optional) : Filters ... 
    - `name` => `ct`, `eq` (field_name => supported operators)
    - `gender` => `eq` (field_name => supported operators)

A wrong format for the filter parameter, an incorrect field or operator component, or an invalid value for field will result in a `BAD_REQUEST` response.


## Endpoints

### Course related

* [Show Courses](doc/api/course/show-courses.md) : `GET /api/courses`
* [Show Course](doc/api/course/show-course.md) : `GET /api/courses:id`
* [Create Course](doc/api/course/create-course.md) : `POST /api/courses`
* [Update Course](doc/api/course/update-course.md) : `PUT /api/courses/:id`
* [Delete Course](doc/api/course/delete-course.md) : `DELETE /api/courses/:id`
* [Delete All Courses by Name](doc/api/course/delete-all-courses-by-name.md) : `DELETE /api/courses`
* [Show Students of Course](doc/api/course/show-students-of-course.md) : `GET /api/courses/:id/students`
* [Add Student to Course](doc/api/course/add-student-to-course.md) : `PUT /api/courses/:courseId/students/:studentId`

### Instructor related

* [Show Instructors](doc/api/instructor/show-instructors.md) : `GET /api/instructors`
* [Show Instructor](doc/api/instructor/show-instructor.md) : `GET /api/instructors/:id`
* [Create Instructor](doc/api/instructor/create-instructor.md) : `POST /api/instructors`
* [Update Instructor](doc/api/instructor/update-instructor.md) : `PUT /api/instructors/:id`
* [Delete Instructor](doc/api/instructor/delete-instructor.md) : `DELETE /api/instructors/:id`
* [Delete All Instructors by Name](doc/api/instructor/delete-all-instructors-by-name.md) : `DELETE /api/instructors`

### Student related

* [Show Students](doc/api/student/show-students.md) : `GET /api/students`
* [Show Student](doc/api/student/show-student.md) : `GET /api/students/:id`
* [Show Student's Gender Counts](doc/api/student/show-student-gender-count.md) : `GET /api/students/gender-counts`
* [Create Student](doc/api/student/create-student.md) : `POST /api/students`
* [Update Student](doc/api/student/update-student.md) : `PUT /api/students/:id`
* [Delete Student](doc/api/student/delete-student.md) : `DELETE /api/students/:id`
* [Delete All Students by Name](doc/api/student/delete-all-students-by-name.md) : `DELETE /api/students`

### ExceptionLog related

* [Show Exception Logs](doc/api/exception-log/show-exception-logs.md) : `GET /api/exception-logs`

## Author

**Furkan Işıtan**

* [github/furkanisitan](https://github.com/furkanisitan)
# Add Student to Course

Adds the Student to the Course.

**URL** : `/api/courses/:courseId/students/:studentId`

**URL Parameters** : `courseId=[long]` , `studentId=[long]`

**Method** : `PUT`

## Success Response

**Code** : `204 NO CONTENT`

## Error Responses

**Code** : `404 NOT FOUND`

**Sample Response Bodies** : 
```json
{
    "success": false,
    "message": "The resource not found.",
    "errors": [
        "Course not found for parameters {courseId='55'}."
    ]
}
```

```json
{
    "success": false,
    "message": "The resource not found.",
    "errors": [
        "Student not found for parameters {studentId='55'}."
    ]
}
```
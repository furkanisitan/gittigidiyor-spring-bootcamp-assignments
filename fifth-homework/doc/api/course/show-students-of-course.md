# Show Students of Course

Returns a list of Students of the Course by id.

**URL** : `/api/courses/:id/students`

**URL Parameters** : `id=[long]` (id of the course)

**Method** : `GET`

## Success Response

**Code** : `200 OK`

**Sample Response Body** :

```json
{
    "success": true,
    "message": " The request has been processed successfully.",
    "payload": [
        {
            "id": 1,
            "createdDate": "2021-09-09T09:27:20.563Z",
            "lastModifiedDate": "2021-09-09T09:27:20.563Z",
            "name": "Student1",
            "address": "StudentAddress1",
            "birthDate": "1999-03-03",
            "gender": "MALE"
        },
        {
            "id": 4,
            "createdDate": "2021-09-09T09:27:20.563Z",
            "lastModifiedDate": "2021-09-09T09:27:20.563Z",
            "name": "Student4",
            "address": "StudentAddress4",
            "birthDate": "1996-01-30",
            "gender": "MALE"
        },
        {
            "id": 5,
            "createdDate": "2021-09-09T09:27:20.563Z",
            "lastModifiedDate": "2021-09-09T09:27:20.563Z",
            "name": "Student5",
            "address": "StudentAddress5",
            "birthDate": "2001-12-19",
            "gender": "MALE"
        }
    ]
}
```

## Error Responses

**Code** : `404 NOT FOUND`

**Sample Response Body** : 
```json
{
    "success": false,
    "message": "The resource not found.",
    "errors": [
        "Course not found for parameters {id='55'}."
    ]
}
```
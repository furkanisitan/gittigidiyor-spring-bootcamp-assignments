# Update Student

Updates the Student.

**URL** : `/api/students/:id`

**URL Parameters** : `id=[long]`

**Method** : `PUT`

**Sample Request Body**

```json
{
    "name": "StudentUpdate1",
    "address": "StudentUpdateAddress1",
    "birthDate": "2000-01-21",
    "gender": "MALE"
}
```

## Success Response

**Code** : `204 NO CONTENT`

## Error Responses

**Code** : `404 NOT FOUND`

**Sample Response Body** : 
```json
{
    "success": false,
    "message": "The resource not found.",
    "errors": [
        "Student not found for parameters {id='55'}."
    ]
}
```

----

**Code** : `400 BAD REQUEST`

**Sample Response Body** : 
```json
{
    "success": false,
    "message": "A validation error has occurred.",
    "errors": [
        "Student age must be between 18 and 40."
    ]
}
```
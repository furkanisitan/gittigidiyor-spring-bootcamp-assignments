# Update Course

Updates the Course.

**URL** : `/api/courses/:id`

**URL Parameters** : `id=[long]`

**Method** : `PUT`

**Sample Request Body**

```json
{
    "code": "updatecode1",
    "name": "updateCourse1",
    "creditScore": 4,
    "instructorId": 2
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
        "Course not found for parameters {id='55'}."
    ]
}
```

----

**Code** : `409 CONFLICT`

**Sample Response Body** : 
```json
{
    "success": false,
    "message": "A unique constraint error has occurred.",
    "errors": [
        "'code' must be unique. {rejectedValue: code2}"
    ]
}
```

----

**Code** : `409 CONFLICT`

**Sample Response Body** : 
```json
{
    "success": false,
    "message": "A foreign key constraint error has occurred.",
    "errors": [
        "A foreign key with the 'instructorId: 55' does not exist."
    ]
}
```

# Update Student

Updates the Student.

**URL** : `/api/students/:id`

**URL Parameters** : `id=[long]`

**Method** : `PUT`

**Sample Request Body**

```json
{
    "name": "StudentUpdate1",
    "address": "StudentAddress1",
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
    "message": "No Records Found.",
    "errors": [
        "Student not found for parameters {id='6'}."
    ]
}
```

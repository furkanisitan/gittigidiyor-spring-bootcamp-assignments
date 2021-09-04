# Create Course

Creates a new Course.

**URL** : `/api/courses`

**Method** : `POST`

**Sample Request Body**

```json
{
    "code": "newCode1",
    "name": "newCourse1",
    "creditScore": 4,
    "instructor": {
        "id": 1
    }
}
```

## Success Response

**Code** : `201 CREATED`

**Sample Response Body** :

```json
{
    "success": true,
    "data": {
        "id": 4,
        "code": "newCode1",
        "name": "newCourse1",
        "creditScore": 4,
        "instructor": {
            "id": 1
        }
    }
}
```

**Header Examples** :

* **Location**: `/api/courses/:id`

## Error Responses

**Code** : `409 CONFLICT`

**Sample Response Body** : 
```json
{
    "success": false,
    "message": "A unique constraint error has occurred.",
    "errors": [
        "'code' must be unique. {rejectedValue: code1}"
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

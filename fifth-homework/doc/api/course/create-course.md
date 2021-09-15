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
    "instructorId": 2
}
```

## Success Response

**Code** : `201 CREATED`

**Sample Response Body** :

```json
{
    "success": true,
    "message": "Resource(s) added successfully.",
    "payload": {
        "id": 11,
        "createdDate": "2021-09-09T09:31:07.179Z",
        "lastModifiedDate": "2021-09-09T09:31:07.179Z",
        "code": "newCode1",
        "name": "newCourse1",
        "creditScore": 4,
        "instructorId": 2
    }
}
```

**Header Values** :

* **Location**: `/api/courses/11`

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

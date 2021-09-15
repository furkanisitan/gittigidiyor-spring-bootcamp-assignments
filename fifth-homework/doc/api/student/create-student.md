# Create Student

Creates a new Student.

**URL** : `/api/students`

**Method** : `POST`

**Sample Request Body**

```json
{
    "name": "StudentNew1",
    "address": "StudentAddress1",
    "birthDate": "2000-01-21",
    "gender": "FEMALE"
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
        "id": 21,
        "createdDate": "2021-09-09T09:37:14.342Z",
        "lastModifiedDate": "2021-09-09T09:37:14.342Z",
        "name": "StudentNew1",
        "address": "StudentAddress1",
        "birthDate": "2000-01-21",
        "gender": "FEMALE"
    }
}
```

**Header Values** :

* **Location**: `/api/students/21`

## Error Responses

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
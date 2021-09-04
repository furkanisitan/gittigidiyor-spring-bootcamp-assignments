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
    "data": {
        "id": 5,
        "name": "StudentNew1",
        "address": "StudentAddress1",
        "birthDate": "2000-01-21",
        "gender": "FEMALE"
    }
}
```

**Header Examples** :

* **Location**: `/api/students/:id`

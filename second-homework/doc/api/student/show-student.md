# Show Student

Returns a Student with the given id.

**URL** : `/api/students/:id`

**URL Parameters** : `id=[long]`

**Method** : `GET`

## Success Response

**Code** : `200 OK`

**Sample Response Body** :

```json
{
    "success": true,
    "data": {
        "id": 1,
        "name": "Student1",
        "address": "StudentAddress1",
        "birthDate": "2000-01-01",
        "gender": "MALE"
    }
}
```

## Error Responses

**Code** : `404 NOT FOUND`

**Sample Response Body** : 
```json
{
    "success": false,
    "message": "No Records Found.",
    "errors": [
        "Student not found for parameters {id='55'}."
    ]
}
```
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
    "message": " The request has been processed successfully.",
    "payload": {
        "id": 1,
        "createdDate": "2021-09-09T09:27:20.563Z",
        "lastModifiedDate": "2021-09-09T09:27:20.563Z",
        "name": "Student1",
        "address": "StudentAddress1",
        "birthDate": "1999-03-03",
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
    "message": "The resource not found.",
    "errors": [
        "Student not found for parameters {id='55'}."
    ]
}
```
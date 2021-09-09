# Show Students

Returns a list of Students.

**URL** : `/api/students`

**Method** : `GET`

**Request Params**

- `filter=[String]` (optional) : Filters students. 
    - `name` => `ct`

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
            "id": 2,
            "createdDate": "2021-09-09T09:27:20.563Z",
            "lastModifiedDate": "2021-09-09T09:27:20.563Z",
            "name": "Student2",
            "address": "StudentAddress2",
            "birthDate": "1984-06-22",
            "gender": "FEMALE"
        }
    ]
}
```

## Error Responses

**Code** : `400 BAD REQUEST`

**Sample Request URL**
`/api/students?filter=nme<ct>Student1`

**Sample Response Body** : 
```json
{
    "success": false,
    "message": "Filter contains invalid or disallowed parameter(s).",
    "errors": [
        "The 'nme' field is invalid or not supported for querying."
    ]
}
```

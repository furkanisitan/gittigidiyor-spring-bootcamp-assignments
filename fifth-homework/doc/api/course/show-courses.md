# Show Courses

Returns a list of Courses.

**URL** : `/api/courses`

**Method** : `GET`

**Request Params**

- `filter=[String]` (optional) : Filters courses. 
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
            "code": "code1",
            "name": "Course1",
            "creditScore": 7,
            "instructorId": 1
        },
        {
            "id": 2,
            "createdDate": "2021-09-09T09:27:20.563Z",
            "lastModifiedDate": "2021-09-09T09:27:20.563Z",
            "code": "code2",
            "name": "Course2",
            "creditScore": 8,
            "instructorId": 2
        }
    ]
}
```

## Error Responses

**Code** : `400 BAD REQUEST`

**Sample Request URL**
`/api/courses?filter=name<eq>Course1`

**Sample Response Body** : 
```json
{
    "success": false,
    "message": "Filter contains invalid or disallowed parameter(s).",
    "errors": [
        "The 'eq' operator is invalid or not supported for 'name' field."
    ]
}
```
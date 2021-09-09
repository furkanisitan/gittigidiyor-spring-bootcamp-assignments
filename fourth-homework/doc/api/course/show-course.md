# Show Course

Returns a Course with the given id.

**URL** : `/api/courses/:id`

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
        "code": "code1",
        "name": "Course1",
        "creditScore": 7,
        "instructorId": 1
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
        "Course not found for parameters {id='55'}."
    ]
}
```
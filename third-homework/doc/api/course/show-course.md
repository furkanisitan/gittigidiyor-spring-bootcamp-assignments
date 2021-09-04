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
    "data": {
        "id": 1,
        "code": "code1",
        "name": "Course1",
        "creditScore": 4,
        "instructor": {
            "id": 1
        }
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
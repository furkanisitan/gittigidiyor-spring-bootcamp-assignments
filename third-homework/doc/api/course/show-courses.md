# Show Courses

Returns a list of Courses.

**URL** : `/api/courses`

**Method** : `GET`

**Request Params**

- `name=[String]` (optional) : Returns all courses those containing the name.

## Success Response

**Code** : `200 OK`

**Sample Response Body** :

```json
{
    "success": true,
    "data": [
        {
            "id": 1,
            "code": "code1",
            "name": "Course1",
            "creditScore": 4,
            "instructor": {
                "id": 1
            }
        },
        {
            "id": 2,
            "code": "code2",
            "name": "Course2",
            "creditScore": 5,
            "instructor": {
                "id": 2
            }
        }
    ]
}
```

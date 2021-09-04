# Show Students

Groups students by gender and returns gender counts.

**URL** : `/api/students/genders`

**Method** : `GET`

## Success Response

**Code** : `200 OK`

**Sample Response Body** :

```json
{
    "success": true,
    "data": [
        {
            "count": 2,
            "gender": "MALE"
        },
        {
            "count": 2,
            "gender": "FEMALE"
        }
    ]
}
```

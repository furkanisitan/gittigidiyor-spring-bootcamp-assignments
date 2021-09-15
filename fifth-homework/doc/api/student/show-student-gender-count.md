# Show Students

Groups students by gender and returns gender counts.

**URL** : `/api/students/gender-counts`

**Method** : `GET`

## Success Response

**Code** : `200 OK`

**Sample Response Body** :

```json
{
    "success": true,
    "message": " The request has been processed successfully.",
    "payload": [
        {
            "count": 11,
            "gender": "MALE"
        },
        {
            "count": 10,
            "gender": "FEMALE"
        }
    ]
}
```

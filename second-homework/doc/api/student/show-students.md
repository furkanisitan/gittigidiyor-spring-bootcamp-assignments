# Show Students

Returns a list of Students.

**URL** : `/api/students`

**Method** : `GET`

## Success Response

**Code** : `200 OK`

**Sample Response Body** :

```json
{
  "success": true,
  "data": [
    {
      "id": 1,
      "name": "Student1",
      "address": "StudentAddress1",
      "birthDate": "2000-01-01",
      "gender": "MALE"
    },
    {
      "id": 2,
      "name": "Student2",
      "address": "StudentAddress2",
      "birthDate": "2000-02-02",
      "gender": "FEMALE"
    }
  ]
}
```

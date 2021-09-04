# Show Instructors

Returns a list of Instructors.

**URL** : `/api/instructors`

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
            "phoneNumber": "+905055557556",
            "name": "PI1",
            "address": "PI1Address",
            "fixedSalary": 10123.78
        },
        {
            "id": 2,
            "phoneNumber": "+905055557555",
            "name": "VS1",
            "address": "VS1Address",
            "hourlySalary": 75.53
        }
    ]
}
```

# Show Instructors

Returns a list of Instructors.

**URL** : `/api/instructors`

**Method** : `GET`

**Request Params**

- `name=[String]` (optional) : Returns all students those containing the name.
- `sort=[String]` (optional) : The field name to be sorted.
    - `fixedSalary` or `fixedSalary.asc`: Sorts in ascending order by 'fixedSalary' field.
    - `fixedSalary.desc`: Sorts in descending order by 'fixedSalary' field.
    - `hourlySalary` or `hourlySalary.asc`: Sorts in ascending order by 'hourlySalary' field.
    - `hourlySalary`: Sorts in descending order by 'hourlySalary' field.
- `limit=[int]` (optional) : Specifies the size of the list to return.

## Success Responses

**Code** : `200 OK`

**Request URL** : `/api/instructors`

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
---

**Code** : `200 OK`

**Request URL** : `/api/instructors?sort=fixedSalary.desc&limit=2`

**Sample Response Body** :

```json
{
    "success": true,
    "data": [
        {
            "id": 3,
            "phoneNumber": "+905055557553",
            "name": "PI3",
            "address": "PI3Address",
            "fixedSalary": 15689.06
        },
        {
            "id": 1,
            "phoneNumber": "+905055557555",
            "name": "PI1",
            "address": "PI1Address",
            "fixedSalary": 10123.78
        }
    ]
}
```
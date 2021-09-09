# Show Instructors

Returns a list of Instructors.

**URL** : `/api/instructors`

**Method** : `GET`

**Request Params**

- `filter=[String]` (optional) : Filters instructors. 
    - `name` => `ct`
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
    "message": " The request has been processed successfully.",
    "payload": [
        {
            "id": 1,
            "createdDate": "2021-09-09T09:27:20.547Z",
            "lastModifiedDate": "2021-09-09T09:27:20.547Z",
            "phoneNumber": "+905055557550",
            "name": "PI1",
            "address": "PI1Address",
            "fixedSalary": 10000.00
        },
        {
            "id": 6,
            "createdDate": "2021-09-09T09:27:20.547Z",
            "lastModifiedDate": "2021-09-09T09:27:20.547Z",
            "phoneNumber": "+905055557560",
            "name": "VR1",
            "address": "VR1Address",
            "hourlySalary": 55.19
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

## Error Responses

**Code** : `400 BAD REQUEST`

**Sample Request URL**
`/api/instructors?filter=name<gt>PI`

**Sample Response Body** : 
```json
{
    "success": false,
    "message": "Filter contains invalid or disallowed parameter(s).",
    "errors": [
        "The 'gt' operator is invalid or not supported for 'name' field."
    ]
}
```
# Show Instructor

Returns a Instructor with the given id.

**URL** : `/api/instructors/:id`

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
        "phoneNumber": "+905055557556",
        "name": "PI1",
        "address": "PI1Address",
        "fixedSalary": 10123.78
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
        "Instructor not found for parameters {id='55'}."
    ]
}
```
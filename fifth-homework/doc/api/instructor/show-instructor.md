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
    "message": " The request has been processed successfully.",
    "payload": {
        "id": 1,
        "createdDate": "2021-09-09T09:27:20.547Z",
        "lastModifiedDate": "2021-09-09T09:27:20.547Z",
        "phoneNumber": "+905055557550",
        "name": "PI1",
        "address": "PI1Address",
        "fixedSalary": 10000.00
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
        "Instructor not found for parameters {id='55'}."
    ]
}
```
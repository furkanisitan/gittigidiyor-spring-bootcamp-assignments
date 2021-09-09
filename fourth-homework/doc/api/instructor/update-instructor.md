# Update Instructor

Updates the Instructor.

**URL** : `/api/instructors/:id`

**URL Parameters** : `id=[long]`

**Method** : `PUT`

**Sample Request Body (PermanentInstructor)**

```json
{
    "phoneNumber": "+905055557556",
    "name": "updatePI1",
    "address": "updatePI1Address",
    "fixedSalary": 7975.22
}
```

**Sample Request Body (VisitingResearcher)**

```json
{
    "phoneNumber": "+905055557555",
    "name": "updateVS2",
    "address": "updateVS2Address",
    "hourlySalary": 66.43
}
```

## Success Response

**Code** : `204 NO CONTENT`

## Error Responses

**Code** : `400 BAD REQUEST`

**Sample Response Body** : 
```json
{
    "success": false,
    "message": "An invalid entity type error has occurred.",
    "errors": [
        "The type of the submitted model is not of type 'PermanentInstructor'."
    ]
}
```

----

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

----

**Code** : `409 CONFLICT`

**Sample Response Body** : 
```json
{
    "success": false,
    "message": "A unique constraint error has occurred.",
    "errors": [
        "'phoneNumber' must be unique. {rejectedValue: +905055557551}"
    ]
}
```

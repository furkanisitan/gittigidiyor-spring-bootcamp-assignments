# Create Instructor

Creates a new Instructor.

**URL** : `/api/instructors`

**Method** : `POST`

**Sample Request Body (PermanentInstructor)**

```json
{
    "phoneNumber": "905057570001",
    "name": "newPI2",
    "address": "newPI2Address",
    "fixedSalary": 5675.55
}
```

**Sample Request Body (VisitingResearcher)**

```json
{
    "phoneNumber": "+905057570002",
    "name": "newVS2",
    "address": "newVS2Address",
    "hourlySalary": 44.53
}
```

## Success Response

**Code** : `201 CREATED`

**Sample Response Body** :

```json
{
    "success": true,
    "data": {
        "id": 3,
        "phoneNumber": "905057570001",
        "name": "newPI2",
        "address": "newPI2Address",
        "fixedSalary": 5675.55
    }
}
```

**Header Examples** :

* **Location**: `/api/instructors/:id`

## Error Responses

**Code** : `409 CONFLICT`

**Sample Response Body** : 
```json
{
    "success": false,
    "message": "A unique constraint error has occurred.",
    "errors": [
        "'phoneNumber' must be unique. {rejectedValue: +905055557555}"
    ]
}
```

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
    "message": "Resource(s) added successfully.",
    "payload": {
        "id": 11,
        "createdDate": "2021-09-09T09:46:43.506Z",
        "lastModifiedDate": "2021-09-09T09:46:43.506Z",
        "phoneNumber": "905057570001",
        "name": "newPI2",
        "address": "newPI2Address",
        "fixedSalary": 5675.55
    }
}
```

**Header Examples** :

* **Location**: `/api/instructors/11`

## Error Responses

**Code** : `409 CONFLICT`

**Sample Response Body** : 
```json
{
    "success": false,
    "message": "A unique constraint error has occurred.",
    "errors": [
        "'phoneNumber' must be unique. {rejectedValue: 905057570001}"
    ]
}
```

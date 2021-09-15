# Update HourlySalary of Visiting Researcher

Updates the HourlySalary of the Visiting Researcher.

**URL** : `/api/instructors/visiting-researchers/:id/hourly-salary/:percent`

**URL Parameters** : `id=[long]`, `percent=[double]`

**Method** : `PUT`

## Success Response

**Code** : `204 NO CONTENT`

## Error Responses

**Code** : `404 NOT FOUND`

**Sample Response Body** : 
```json
{
    "success": false,
    "message": "The resource not found.",
    "errors": [
        "VisitingResearcher not found for parameters {id='55'}."
    ]
}
```

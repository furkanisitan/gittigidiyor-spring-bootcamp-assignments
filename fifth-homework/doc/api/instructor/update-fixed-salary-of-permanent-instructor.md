# Update FixedSalary of Permanent Instructor

Updates the FixedSalary of the Permanent Instructor.

**URL** : `/api/instructors/permanent-instructors/:id/fixed-salary/:percent`

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
        "PermanentInstructor not found for parameters {id='55'}."
    ]
}
```

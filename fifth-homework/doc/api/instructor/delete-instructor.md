# Delete Instructor

Deletes the Instructor.

**URL** : `/api/instructors/:id`

**URL Parameters** : `id=[long]`

**Method** : `DELETE`

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
        "Instructor not found for parameters {id='55'}."
    ]
}
```
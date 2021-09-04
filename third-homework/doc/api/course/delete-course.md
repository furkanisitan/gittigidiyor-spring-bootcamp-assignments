# Delete Course

Deletes the Course.

**URL** : `/api/courses/:id`

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
    "message": "No Records Found.",
    "errors": [
        "Course not found for parameters {id='55'}."
    ]
}
```

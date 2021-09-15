# Show Exception Logs

Returns a list of Exception Logs.

**URL** : `/api/exception-logs`

**Method** : `GET`

**Request Params**

- `filter=[String]` (optional) : Filters exception logs. 
    - `exceptionType` => `eq`
    - `date` => `eq`

## Success Response

**Code** : `200 OK`

**Sample Response Body** :

```json
{
    "success": true,
    "message": " The request has been processed successfully.",
    "payload": [
        {
            "id": 1,
            "exceptionType": "CourseIsAlreadyExistException",
            "date": "2021-09-09T13:31:47.721",
            "message": "'code' must be unique. {rejectedValue: code1}"
        },
        {
            "id": 2,
            "exceptionType": "StudentAgeNotValidException",
            "date": "2021-09-09T13:32:02.848",
            "message": "Student age must be between 18 and 40."
        }
    ]
}
```

## Error Responses

**Code** : `400 BAD REQUEST`

**Sample Request URL**
`/api/exception-logs?filter=exceptionType<ct>CourseIsAlreadyExistException`

**Sample Response Body** : 
```json
{
    "success": false,
    "message": "Filter contains invalid or disallowed parameter(s).",
    "errors": [
        "The 'ct' operator is invalid or not supported for 'exceptionType' field."
    ]
}
```
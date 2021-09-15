# Show All FixedSalary History of Permanent Instructor

Returns a List of PermanentInstructor's FixedSalary logs.

**URL** : `/api/instructors/permanent-instructors/:id/fixed-salary/history`

**URL Parameters** : `id=[long]`

**Method** : `GET`

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
            "instructorId": 1,
            "clientIpAddress": "0:0:0:0:0:0:0:1",
            "clientUrl": "/api/instructors/permanent-instructors/1/fixed-salary/10",
            "sessionActivityId": "D243494045FA944F9D1F3E3DC4B24145",
            "date": "2021-09-14T18:10:47.667",
            "previousSalary": 10000.00,
            "currentSalary": 11000.00,
            "percent": 10.0
        },
        {
            "id": 2,
            "instructorId": 1,
            "clientIpAddress": "0:0:0:0:0:0:0:1",
            "clientUrl": "/api/instructors/permanent-instructors/1/fixed-salary/10",
            "sessionActivityId": "D243494045FA944F9D1F3E3DC4B24145",
            "date": "2021-09-14T18:20:16.529",
            "previousSalary": 11000.00,
            "currentSalary": 12100.00,
            "percent": 10.0
        }
    ]
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
        "PermanentInstructor not found for parameters {id='55'}."
    ]
}
```
# Show All HourlySalary History of Visiting Researcher

Returns a List of VisitingResearcher's HourlySalary logs.

**URL** : `/api/instructors/visiting-researchers/:id/hourly-salary/history`

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
            "id": 3,
            "instructorId": 6,
            "clientIpAddress": "0:0:0:0:0:0:0:1",
            "clientUrl": "/api/instructors/visiting-researchers/6/hourly-salary/-10",
            "sessionActivityId": "D243494045FA944F9D1F3E3DC4B24145",
            "date": "2021-09-14T18:24:05.127",
            "previousSalary": 55.19,
            "currentSalary": 49.67,
            "percent": -10.0
        },
        {
            "id": 4,
            "instructorId": 6,
            "clientIpAddress": "0:0:0:0:0:0:0:1",
            "clientUrl": "/api/instructors/visiting-researchers/6/hourly-salary/16",
            "sessionActivityId": "D243494045FA944F9D1F3E3DC4B24145",
            "date": "2021-09-14T18:24:11.159",
            "previousSalary": 49.67,
            "currentSalary": 57.62,
            "percent": 16.0
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
        "VisitingResearcher not found for parameters {id='55'}."
    ]
}
```
## Requirement

To see Google maps in application API key is needed.
One can be obtained from [here](https://developers.google.com/maps/documentation/embed/get-api-key).

After key is obtained  *local.properties* file must contain:
MAPS_API_KEY=**MY_API_KEY_REPLACE_ME**

**Query parameters**:

 * `boundingBox`
   * bounding box in CSV format "top-right latitude (lat1), top-right longitude (lon1), bottom-left latitude (lat2), bottom-left longitude (lon2)"
   * example: `48.16124,11.60912,48.12229,11.52741`

 **Response** : (HTTP 200)

```json
  {
    "count": 109,
    "betshops": [
      {
        "name": " Lenbachplatz 7",
        "location": {
          "lng": 11.5689638,
          "lat": 48.1405515
        },
        "id": 2350329,
        "county": "Bayern",
        "city_id": 80333,
        "city": "Muenchen",
        "address": "80333 Muenchen"
      },
      {
        "name": " St-Galler-Str 4",
        "location": {
          "lng": 11.5310798,
          "lat": 48.1600108
        },
        "id": 2350575,
        "county": "Bayern",
        "city_id": 80637,
        "city": "Muenchen",
        "address": "80637 Muenchen"
      },
      {
        "name": " Marienplatz 1/U-Bahn-Kiosk",
        "location": {
          "lng": 11.5754485,
          "lat": 48.1373932
        },
        "id": 2350022,
        "county": "Bayern",
        "city_id": 80331,
        "city": "Muenchen",
        "address": "80331 Muenchen"
      }
    ]
  }
```

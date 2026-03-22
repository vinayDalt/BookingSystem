Theatre Management Service
############################################
Purpose: Provides APIs to manage cities, theatres, screens, shows, movies, and seats.

Entities
###############################
City: Contains multiple theatres.
Theatre: Belongs to a city, contains multiple screens.
Screen: Belongs to a theatre, contains multiple shows.
Show: Belongs to a screen and theatre, linked to a movie, contains seats.
Movie: Represents a movie being screened.
Seat: Represents a seat in a show with type and price.


Controller Endpoints
################################
POST /v1/api/admin/cities
- Add a city

POST /v1/api/admin/cities/{cityId}/theatres
- Add a theatre under a city

POST /v1/api/admin/theatres/{theatreId}/screens
- Add a screen under a theatre

POST /v1/api/admin/screens/{screenId}/shows/full
- Add a show along with movie and seats

PUT /v1/api/admin/theatres/{theatreId}
- Update theatre

DELETE /v1/api/admin/theatres/{theatreId}
- Delete theatre

PUT /v1/api/admin/shows/{showId}
- Update show details

DELETE /v1/api/admin/shows/{showId}
- Delete show
  

Request Structure
#############################

Theatre Management Service ( Allows theatre partners to onboard their theatres over this platform and get access to a bigger customer base while going digital)
#############################################################################################################################################################################
APIs:

1. Add City
----------------------
POST API: http://localhost:8080/v1/api/admin/cities

Request:
{
"name": "Bangalore"
}

Response:
{
"id": 1,
"name": "Bangalore",
"theatres": []
}



2. Add Theatre
---------------------
POST API: http://localhost:8080/v1/api/admin/cities/1/theatres

Request:
{
"name": "PVR Forum Mall"
}

Response:
{
"id": 1,
"name": "PVR Forum Mall",
"screens": []
}

3. Add Screens
-----------------------
POST API: http://localhost:8080/v1/api/admin/theatres/1/screens

Request:
{
"name": "Screen 1"
}

Response:
{
"id": 1,
"name": "Screen 1",
"shows": []
}

4. Add Show
-------------------------
POST API: http://localhost:8080/v1/api/admin/screens/1/shows/full

Request:
{
"startTime": "2026-03-22T14:00:00",
"endTime": "2026-03-22T17:00:00",

"movie": {
"name": "Interstellar",
"language": "English",
"genre": "Sci-Fi",
"duration": 169,
"description": "Space exploration movie"
},

"seats": [
{
"seatType": "REGULAR",
"basePrice": 200,
"description": "Front row"
},
{
"seatType": "REGULAR",
"basePrice": 200,
"description": "Front row"
},
{
"seatType": "PREMIUM",
"basePrice": 300,
"description": "Middle row"
},
{
"seatType": "RECLINER",
"basePrice": 500,
"description": "Back row"
}
]
}

Response:
{
"id": 1,
"startTime": "2026-03-22T14:00:00",
"endTime": "2026-03-22T17:00:00",
"movie": {
"id": 1,
"language": "English",
"genres": null,
"description": "Space exploration movie",
"duration": 169.0
},
"seats": [
{
"id": 1,
"seatType": "REGULAR",
"basePrice": 200.0,
"seatStatus": "AVAILABLE",
"description": "Front row"
},
{
"id": 2,
"seatType": "REGULAR",
"basePrice": 200.0,
"seatStatus": "AVAILABLE",
"description": "Front row"
},
{
"id": 3,
"seatType": "PREMIUM",
"basePrice": 300.0,
"seatStatus": "AVAILABLE",
"description": "Middle row"
},
{
"id": 4,
"seatType": "RECLINER",
"basePrice": 500.0,
"seatStatus": "AVAILABLE",
"description": "Back row"
}
]
}


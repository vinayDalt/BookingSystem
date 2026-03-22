Booking Service
#########################################
Purpose: Provides APIs for users to view available seats and book tickets for shows.

Entities
########################################################
GET /api/booking/show/{showId}/available-seats
- Returns list of available seats for a show

POST /api/booking/show/{showId}/book
- Book one or more seats for a show
- Request body: List of seat IDs

Booking Service (Allows end customers to browse the platform to get access to movies across different cities, languages, and genres, as well as book tickets in advance with a 	seamless experience.)
##########################################################################################################################################################################################

APIs:

1. Get Available Seats
--------------------------
GET API: http://localhost:8080/api/booking/show/1/available-seats

Request:
No body, as we are already passing show_id in path variable

Response:
[
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


2. Book Seat
----------------------------------
POST API: http://localhost:8080/api/booking/show/1/book

Request:
[1, 2]

Response:
Seats booked successfully!


Failure Scenario:

Request:
[2]

Response:
Seat 2 is already booked

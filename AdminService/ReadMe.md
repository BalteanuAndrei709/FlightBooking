<h2>Description</h2>
<b>Admin Service</b> is a microservice for registering new operators, along with their associated flights. <br />
<b>Technology Stack</b> <br />
-POSTGRES <br />
-Spring(Boot, WEB, Data) <br />
-Postman <br />
-Git (as you can see from this ReadME xD ) <br />

Admin can add flights in two ways:
1. Add a list of flights at the initial moment, when the operator is also created.
2. After operator was added(With operatorName, iban and API for searching internal flights),
   he can add flights containing the operator name and the routes.

<h2>Database Models</h2>
<img width="510" alt="image" src="https://github.com/BalteanuAndrei709/FlightBooking/assets/55703977/d4cf509b-6002-4085-a332-d3b8131f3cca">
<h3> Operator </h3>
id -> Unique identifier for the operator <br />
name -> Operator's name  <br />
iban -> Account number where funds will be transfered after booking a flight at that operator  <br />
api_search -> External API where flights details can be found  <br />

<h3> Flight </h3>
id -> Unique identifier for the flight <br />
operator_id -> Unique identifier for the operator which is a reference to the admin table <br />
leaving -> Represents the location from where the flight starts <br />
destination -> Represents the destination of the flight <br />
numberSeatsTotal -> Represents the flight's total seating capacity <br />  
numberSeatsAvailable -> Represents the flight's remaining available seats <br />
dateOfDeparture -> The date when the plane leaves <br />
timeOfDeparture -> the exact time of the flight's departure <br />


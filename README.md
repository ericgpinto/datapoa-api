# Data Poa API

## About the project

Project to consume an api of bus lines of the city of Porto Alegre(Brazil).

## Request examples
- http://localhost:8080/apidatapoa/callback/buslines -> endpoint that consumes the bus lines api, returning all the lines found and saving them to the application's database if they have not already been saved.

- http://localhost:8080/apidatapoa/callback/itineraries?p=6058 -> endpoint that consumes the bus lines api, returning the route of the line informed by parameter and saving it in the application's database if it hasn't been saved yet.

- http://localhost:8080/apidatapoa/buslines/radiussearch?lat=-30.01096627371853&lng=-51.1607744580939&radius=2924.99 -> in this endpoint, a point on the map (latitude and longitude) and a radius in kilometers are entered per parameter. Then it should return all lines that are within this radius.

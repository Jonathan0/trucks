# Trucks
Pick your favorite food trucks around you. 

1. pre-request: java11
2. build project `./gradlew clean build`
3. Run the TrucksApplication `./gradlew bootRun`
4. Test a GET request at localhost:8080 `curl -v localhost:8080`
5. Get All Trucks `curl -v localhost:8080/trucks`
6. Get one Truck with id `curl -v localhost:8080/trucks/2`
7. Update a Truck `curl -X PUT -v localhost:8080/trucks/2 -H 'Content-type:application/json' -d '{"dayOrder": 1, "dayOfWeekStr": "Monday", "starttime": "10AM", "endtime": "3PM"}'`
8. Add a new Truck `curl -X POST -v localhost:8080/trucks -H 'Content-type:application/json' -d '{"permitLocation": "70 Hope Ave", "latitude": 42.368697328144826, "longitude": -71.24739426023218}'`
9. Delete a Truck with id `curl -X DELETE -v localhost:8080/trucks/3`
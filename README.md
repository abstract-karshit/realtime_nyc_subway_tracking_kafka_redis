# Realtime nyc next subway tracking using kafka, redis and Springboot
##### Realtime tracking of next train for every stop and route for NYC

MTA, the agency operating NYC subways, publishes real time subway trip updates via a set of RESTful APIs that are available to the public.
MTA’s trip update API uses protobuf in gtfs-realtime feed specification so the data has a standard format

## Requirements:
* Kafka
* Redis
* Java 11
* Maven

## How to run
Clone the repo and execute the command inside main directory: 
<br>`mvn spring-boot:run`</br>

##### Note: <br>
Before running the project, you need to get your own `mta.api.key` and replace the value in `application.properties` file
<br>To get your own key: https://datamine.mta.info/user/register
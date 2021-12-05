# Getting Started
Reference Documentation for 'Listing Service' 

### Architecture and Technologies
Listing Service API is providing the rest end points Creating new Listings from 
different providers with different dealers 

All the requirements and validations from the assignment document has been satisfied.
Manual/Automatic input configuration has been made in the configuration files.

- Java 11
- Spring Boot 2.5.3
- Spring JPA
- Actuator
- Opencsv
- Maven
- H2 Database
- Mockito
- Swagger
- Docker

### How to Build and Run
Project is build using Maven and Run as jar

'java -jar listing-service-0.0.1-SNAPSHOT.jar'

### Documentation
Swagger has been configured with the service and API documentation can be viewed at below endpoints after starting the application.

`http://localhost:8081/swagger-ui.html#/`

Please check the 'listing-service.postman_collection.json' for sample rest end points

## Deployment
Deployed the Listing Service as Docker image. Please look Dockerfile

## Design decisions and Trade-offs
##### Executed tests and results
- Implemented test cases to test the required functionality

##### Ideas you would like to implement
- Implement Authentication and Authorization using JWT token Authentication 
- Sorting and Pagination for displaying the Listings (Using Spring JPA)
- Cache implementation for read operations to improve the application performance.(Using Redis or Spring Cache)
- Spring HATEOAS to easily create REST representations that follow the principle of HATEOAS

##### Decisions you had to take and why 
- Generalized the ListingDto across different sources i.e JSON & CSV. Because the target Object is same for all the sources
- Considered OpenCsv library for reading CSV file over the Apache Commons. Because it is one of the simplest and easiest CSV parser to understand using standard reader/writer beans.
- Implemented the search functionality with JPA Specification.Because JPA queries will generate dynamically(where clauses). In future new search param(available attribute) can be configured easily 

##### Architectures
- Followed the REST API Architecture.
- It is lightweight and easily maintainable and works over the HTTP protocal.
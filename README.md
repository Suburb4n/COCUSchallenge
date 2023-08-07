# COCUS Code Challenge

## Overview

The application is built using Java, Spring REST, Maven, Spring Web Security, Logback, and Pitest.

## Run

### Example for running the application
Using Maven follow these steps to launch your Spring Boot from the terminal:
- Clone or Download the Project from GitHub.
- Build the Project from a terminal/command prompt in the project root directory and execute the following command
```
 mvn clean install
```
- Run the Application: Once the project is built successfully, run the Spring Boot application.
```
  mvn spring-boot:run
```
If you choose to, run the application directly from your IDE. Locate the main class and run it as a Java Application.

With the Spring Boot application now running, open your web browser and navigate 
to http://localhost:8080 to access the application.

The defined Admin is "dluis1651@gmail.com", with password set as "password"

### Technologies Used
 #### Java 11
Java is the programming language used as the foundation for the application. 
It provides a robust and platform-independent environment for developing web applications.

#### Spring REST
Spring REST is a part of the Spring Framework that enables building web application with RESTful APIs. 
It provides various annotations and tools to simplify the creation of RESTful endpoints.

#### Maven
Maven is a build automation and dependency management tool used to manage project dependencies, 
build the application, test and manage its lifecycle.

#### Spring Web Security
Spring Web Security is a module of the Spring Framework that provides a robust and easy to use 
security features for web applications. It facilitates user authentication, authorization, a
nd protection against common web vulnerabilities.

#### Spring JPA
Spring JPA (Java Persistence API) is a part of the Spring Framework that simplifies working with databases by providing
a set of high-level abstractions over the Java Persistence API. It allows developers to interact with relational
databases using a set of annotations and interfaces, reducing the amount of boilerplate code required for data access.

#### H2 Database
H2 is an in-memory database that provides a fast and lightweight solution for development and testing. It can be used 
as a replacement for other relational databases during development, allowing for rapid prototyping and quick
data access without the need for external database setups.

#### Hibernate
Hibernate is an Object-Relational Mapping (ORM) framework that simplifies data access in Java applications. 
It acts as a bridge between the object-oriented domain model and the relational database, 
allowing developers to work with objects while Hibernate takes care of translating data to and from the database.

#### Logback
Logback is a logging framework provided by the Springframework dependency to log application events, errors, and warnings. 
It offers flexible configuration options and is widely used in Java applications.

#### Pitest
Pitest is a mutation testing tool for Java projects. It helps assess the quality of 
the test suite by introducing mutations to the code and checking if the tests detect those mutations.


## REST Controllers

### AuthorizationController 
####  Endpoint POST /authenticate
This endpoint handles user authentication using the provided email and password. 
The request body should include an AuthenticationRequest object containing the user's email and password. 
The controller then uses the AuthenticationManager to verify the user's credentials.

If the authentication is successful and a user with the provided email is found in the database, 
the controller logs the successful authentication event and returns a JWT token in the response body.

If the authentication fails (i.e., invalid email or password) or no user is found with the provided email, 
the controller logs a warning and returns a Bad Request response with an error message.

### Endpoint TripController /trips

The TripController is a Java class responsible for handling various operations related to trips 
in a web application. It provides endpoints to create new trips, add people to existing trips, 
delete trips, and list all available trips.
Endpoints
Create a New Trip

#### Endpoint POST /

This endpoint allows the creation of a new trip with the provided trip details.

Requires a JSON object representing the NewTripDTO with the following fields:

    tripId (Long): The unique identifier for the trip.
    origCity (String): The origin city of the trip.
    destCity (String): The destination city of the trip.
    departure (Date): The departure date of the trip.

Provides "HTTP Status Code: 200 (OK) - The trip was successfully created." if creation was a success,
and returns a JSON object representing the NewTripDTO of the created trip, including a link to the newly created trip.
    
Provides "HTTP Status Code: 400 (Bad Request) - Trip creation failed due to an error." if creation failed,
and returns an error message describing the reason for the failure.

####  Endpoint PATCH /{tripId}/people

This endpoint allows adding one person at a time to an existing trip.

Requires a:
- PathVariable tripId (Long): The unique identifier of the trip to which people will be added.

- Request Body:a JSON object representing the PersonDTO with the following fields:

    firstName (String): The first name of the person to be added.
    lastName (String): The last name of the person to be added.

Response

Provides an "HTTP Status Code: 200 (OK) - The person was successfully added to the trip." if a person if successfully
added to a trip, and returns a JSON object representing the PersonDTO of the added person, including a link 
to the trip to which the person was added.

Provides a "HTTP Status Code: 400 (Bad Request) - Adding people to the trip failed due to an error.", if adding a person
fails, and returns a response Body: An error message describing the reason for the failure.


#### Endpoint DELETE /{tripId}

This endpoint allows the deletion of a trip based on the provided tripId.

Requires a ath Variable tripId (Long): The unique identifier of the trip to be deleted.

Provides a "HTTP Status Code: 200 (OK) - The trip was successfully deleted." if deletion is successfull.

Provides a "HTTP Status Code: 400 (Bad Request) - Deleting the trip failed due to an error or the trip does not exist.",
    and returns a response Body: An error message describing the reason for the failure or "Trip not found!" if the 
trip does not exist.


#### Endpoint GET /

This endpoint retrieves a list of all trips available in the system.

Requires no input parameters.

Provides a "HTTP Status Code: 200 (OK) - Trips were found and listed.", if trips are found and returns a 
response Body: A JSON array representing a list of FullTripDTO objects, each containing the information stores in a trip,
along with a link to the individual trip.
    
Provides a "HTTP Status Code: 400 (Bad Request) - No trips were found!" if no trips are stored, and returns
a response Body: An error message describing the reason for the failure or an empty array if no trips were found.

Product Search Service

The Product Search Service is a Spring Boot microservice designed to manage products and provide powerful search capabilities based on multiple filters such as name, brand, category, price, and rating. It follows a clean layered architecture consisting of Controller, Service, and Repository layers. The application stores product data in an H2 in-memory database and uses Spring Data JPA for persistence. Swagger UI is integrated to explore and test all REST APIs directly from the browser. JUnit and Mockito are used to test the service layer and ensure functionality is reliable and bug-free.

Architecture Workflow:
The request begins from the Client, reaches the Controller, moves to the Service layer where business logic executes, then calls the Repository which interacts with the Database.

Architecture Diagram

flowchart LR
Client --> Controller --> Service --> Repository --> Database


Key Features:
The project supports adding new products, retrieving all products, searching products by filters, updating product details, and deleting products. It uses validation to ensure required fields are not empty. All SQL operations performed by Hibernate are logged for easy debugging. The application automatically creates or updates database tables using Hibernate’s ddl-auto property.

Technology Stack:
It is built using Java 17, Spring Boot, Spring Web, Spring Data JPA, H2 Database, Lombok, Swagger (Springdoc OpenAPI), JUnit, and Mockito.

Swagger URL:
http://localhost:8080/productservice

H2 Console:
http://localhost:8080/h2-console

Sample JSON for Adding a Product:

{"name":"Laptop","category":"Electronics","brand":"Dell","price":55000,"rating":4.5}


How to Run the Application:
Use the command mvn spring-boot:run to start the application. Once the server starts, open the Swagger URL to test APIs. Use the H2 Console to view database tables using JDBC URL jdbc:h2:mem:productsdb.

Running Unit Tests:
Use mvn test to execute all JUnit test cases. The service layer test class includes positive and negative test scenarios using Mockito to mock repository behavior.

Repository Structure:
The project is organized into Entity, Repository, Service, and Controller packages to maintain clear separation of concerns and make the code easy to understand and maintain.

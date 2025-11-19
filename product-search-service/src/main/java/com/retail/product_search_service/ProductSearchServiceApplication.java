package com.retail.product_search_service;

/*Forward Flow: Client → Controller → Service → Repository → Database
Client (Postman / Swagger UI) sends an HTTP request to your application.
Controller (@RestController) receives the request and maps it to the correct method (GET/POST etc.).
Service (@Service) executes business logic, validations, or rules as needed.
Repository (JpaRepository) interacts with the database, executing SQL queries (insert/select/update/delete).
Database (H2 in-memory) performs the actual data operation (store, retrieve, filter).
Data flows back as a response to the controller, converted to JSON.
Controller sends the JSON response back to the client.*/


/*Reverse Flow: Database → Repository → Service → Controller → Client
Database executes the SQL query and fetches the requested data.
Repository (JpaRepository) receives the database result and maps it to Java entity objects.
Service applies business logic, filters, or transformations if required.
Controller receives the processed entity/entities from the service.
Controller converts Java objects into JSON format.
Client (Postman / Swagger UI) receives the JSON response.*/

//Controller → Service → Repository → Database = request path
//Database → Repository → Service → Controller → Client = response path
//Client ↔ Controller ↔ Service ↔ Repository ↔ Database


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductSearchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductSearchServiceApplication.class, args);
		System.out.println("product service");
	}

}

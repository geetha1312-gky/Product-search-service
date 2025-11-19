

package com.retail.product_search_service.service;
import java.util.List;

import com.retail.product_search_service.Entity.Product;


//Why do we need methods in ProductService when ProductRepository already has them
//Think of a 3-layer architecture:Controller  →  Service  →  Repository  →  Database
//1. Repository Layer = Only talks to the DATABASE:findAll(),findById(),findByNameContainingIgnoreCase(),findByCategory() etc...
//2. Service Layer = Business Logic + Rules,Service is the “brain” of your application.
//Even if Service uses Repository methods inside it, service adds logic around those operations.
//“Even if repo has save(), service can add: Validate product name is not empty, Validate price is not negative, Set created date, Call another service, Apply discount rule, 
//and If error → throw exception.”//Repository CANNOT do any of this.
//“Why again write methods like saveProduct(), getProductById() in Service?”
//Because Controller should NOT directly call database.
//Controller should only call service, and service will call repository
//example-“In this flow, the controller handles the client request like POST /products, 
//the service performs the business logic such as validating the product and applying rules before calling repository.save(), 
//and the repository finally interacts with the database to INSERT the product into the table.”
//“The service layer should include the important methods from the repository so the controller never calls the repository directly, 
//and it can also contain additional methods for validation, business rules, calculations, or any extra logic that the repository itself cannot handle.”
//Because:Controller should never call repository directly,Service is the middle layer,So service needs to expose the same features

public interface ProductService {
	  Product saveProduct(Product product);

	    Product getProductById(Long id);

	    List<Product> getAllProducts();

	    List<Product> searchByName(String name);

	    List<Product> searchByCategory(String category);

	    List<Product> searchByBrand(String brand);

	    List<Product> searchByPriceLessThan(Double price);

	    List<Product> searchByRatingGreaterThanEqual(Double rating);
	

}

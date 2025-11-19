package com.retail.product_search_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.retail.product_search_service.Entity.Product;
import com.retail.product_search_service.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

//@RestController = @Controller + @ResponseBody
// this one handles http requests, Responses will be converted to JSON automatically.
//Why responses need to be converted to JSON-Clients don’t understand Java objects
//Your Spring Boot method returns a Product object (a Java class).
//If Spring sent the object as-is, the client (Postman, browser, or frontend) wouldn’t understand it because Java objects exist only in memory on the server.
//JSON is a universal data format(java script object notation)Almost all clients (web apps, mobile apps, Postman, etc.) understand JSON.
//Almost all clients (web apps, mobile apps, Postman, etc.) understand JSON.
//@ResponseBody tells Spring:“Take the return value (Java object), convert it to JSON, and send it as HTTP response”
//@RequestMapping("/products") → base path for all endpoints. Example: /products/search/name.
//@Tag(...) → Swagger grouping and description for this controller in Swagger UI.
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    //@Operation → Swagger summary for documentation.
    // @RequestBody Product product → Spring automatically converts JSON body from client into a Product object.
    //return productService.saveProduct(product) → calls service method to save product and returns the saved object.
    
    // 1. Add a new product
    // Maps to service: saveProduct(Product product)
    // POST /products
    // ================
    @PostMapping
    @Operation(summary = "Add a new product")
    public Product addProduct(@Valid @RequestBody Product product) {
        System.out.println("Received product: " + product);
        return productService.saveProduct(product);
    }

    // ===========================
    //@GetMapping("/{id}") → maps GET requests like /products/101
    //@PathVariable Long id → extracts id from URL and passes to method.Extracts data from the URL path itself
    // 2. Get product by ID
    // Maps to service: getProductById(Long id)
    // GET /products/{id}
    // ===========================
    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // ===========================
    // 3. Get all products
    // Maps to service: getAllProducts()
    // GET /products
    // ===========================
    @GetMapping
    @Operation(summary = "Get all products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // ===========================
    // 4. Search products by name
    // Maps to service: searchByName(String name)
    // GET /products/search/name?name=...
    // ===========================
    @GetMapping("/search/name")
    @Operation(summary = "Search products by name")
    public List<Product> searchByName(@RequestParam String name) {
        return productService.searchByName(name);
    }

    // ===========================
    // 5. Search products by category
    // Maps to service: searchByCategory(String category)
    // GET /products/search/category?category=...
    // ===========================
    @GetMapping("/search/category")
    @Operation(summary = "Search products by category")
    public List<Product> searchByCategory(@RequestParam String category) {
        return productService.searchByCategory(category);
    }

    // ===========================
    // 6. Search products by brand
    // Maps to service: searchByBrand(String brand)
    // GET /products/search/brand?brand=...
    // ===========================
    @GetMapping("/search/brand")
    @Operation(summary = "Search products by brand")
    public List<Product> searchByBrand(@RequestParam String brand) {
        return productService.searchByBrand(brand);
    }

    // ===========================
    // 7. Search products by price less than
    // Maps to service: searchByPriceLessThan(Double price)
    // GET /products/search/price?lessThan=...
    // ===========================
    @GetMapping("/search/price")
    @Operation(summary = "Search products by price less than")
    public List<Product> searchByPrice(@RequestParam Double lessThan) {
        return productService.searchByPriceLessThan(lessThan);
    }

    // ===========================
    // 8. Search products by minimum rating
    // Maps to service: searchByRatingGreaterThanEqual(Double rating)
    // GET /products/search/rating?min=...
    // ===========================
    @GetMapping("/search/rating")
    @Operation(summary = "Search products by minimum rating")
    public List<Product> searchByRating(@RequestParam Double min) {
        return productService.searchByRatingGreaterThanEqual(min);
    }
}

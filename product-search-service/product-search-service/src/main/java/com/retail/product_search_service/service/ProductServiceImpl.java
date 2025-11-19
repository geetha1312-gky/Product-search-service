package com.retail.product_search_service.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.product_search_service.Entity.Product;
import com.retail.product_search_service.Repository.Productrepository;
import com.retail.product_search_service.service.ProductService;

//Your Repository has 5 custom methods + built-in methods
//custom methods-findByNameContainingIgnoreCase(),findByCategoryIgnoreCase(),findByBrandIgnoreCase(),findByPriceLessThan(),findByRatingGreaterThanEqual()
//Built-in methods from JpaRepository (already available even if you don’t write them):save(),findById(),findAll(),deleteById(),count(),existsById() and so on.....
//You get all these for free because your repository extends:JpaRepository<Product, Long>
//So total methods available = 5 (custom) + 50+ (built-in)
//Service → Repository Mapping
//saveProduct()                  → repository.save()
//getProductById()               → repository.findById()
//getAllProducts()               → repository.findAll()
//searchByName()                 → repository.findByNameContainingIgnoreCase()
//searchByCategory()             → repository.findByCategoryIgnoreCase()
//searchByBrand()                → repository.findByBrandIgnoreCase()
//searchByPriceLessThan()        → repository.findByPriceLessThan()
//searchByRatingGreaterThanEqual() → repository.findByRatingGreaterThanEqual()

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private Productrepository productRepository;
    
 // ===========================
    // Save a product to the database
    // Receives a Product object as input (product)
    // Calls repository.save(product) which inserts or updates the record in the database
    // Returns the saved Product object with auto-generated fields like id
    // Used for POST /products
    // ===========================
    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // ===========================
    // Get a product by its ID
    // Receives the product id as input (Long id)
    // Calls repository.findById(id) which fetches the product if it exists
    // If not found, returns null
    // Used for GET /products/{id}
    // ===========================
    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // ===========================
    // Get all products
    // Calls repository.findAll() which fetches all products from the database
    // Returns a List of Product objects
    // Used for GET /products
    // ===========================
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // ===========================
    // Search products by name (case-insensitive, partial match)
    // Receives the search string as input
    // Calls repository.findByNameContainingIgnoreCase(name)
    // Returns a List of matching products
    // Used for GET /products/search/name?name=someName
    // ===========================
    @Override
    public List<Product> searchByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    // ===========================
    // Search products by category (case-insensitive, exact match)
    // Receives the category as input
    // Calls repository.findByCategoryIgnoreCase(category)
    // Returns a List of matching products
    // Used for GET /products/search/category?category=someCategory
    // ===========================
    @Override
    public List<Product> searchByCategory(String category) {
        return productRepository.findByCategoryIgnoreCase(category);
    }

    // ===========================
    // Search products by brand (case-insensitive, exact match)
    // Receives the brand as input
    // Calls repository.findByBrandIgnoreCase(brand)
    // Returns a List of matching products
    // Used for GET /products/search/brand?brand=someBrand
    // ===========================
    @Override
    public List<Product> searchByBrand(String brand) {
        return productRepository.findByBrandIgnoreCase(brand);
    }

    // ===========================
    // Search products with price less than a given value
    // Receives the maximum price as input
    // Calls repository.findByPriceLessThan(price)
    // Returns a List of matching products
    // Used for GET /products/search/price?lessThan=500
    // ===========================
    @Override
    public List<Product> searchByPriceLessThan(Double price) {
        return productRepository.findByPriceLessThan(price);
    }

    // ===========================
    // Search products with rating greater than or equal to a given value
    // Receives the minimum rating as input
    // Calls repository.findByRatingGreaterThanEqual(rating)
    // Returns a List of matching products
    // Used for GET /products/search/rating?min=4
    // ===========================
    @Override
    public List<Product> searchByRatingGreaterThanEqual(Double rating) {
        return productRepository.findByRatingGreaterThanEqual(rating);
    }
}

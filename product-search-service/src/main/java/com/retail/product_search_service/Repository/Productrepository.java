package com.retail.product_search_service.Repository;


import com.retail.product_search_service.Entity.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//If you write a method name in a specific pattern,Spring will automatically generate SQL for that method.
//If you write:findByName(String name),Spring says:findBy → SELECT,Name → field in the Product entity,String name → value to match
//So Spring internally builds this SQL:
//SELECT * FROM products WHERE name = ?;You never write SQL.You ONLY write the method name.
//this is for repository

@Repository
public interface Productrepository extends JpaRepository<Product, Long> {
	
	// Search by name (LIKE %name% search)
	// Explanation:
	// "findBy" → Spring Data knows this is a query method  
	// "Name" → field in Product entity  
	// "Containing" → performs a LIKE %name% match  
	// "IgnoreCase" → case-insensitive  
	// Final meaning: SELECT * FROM product WHERE LOWER(name) LIKE LOWER('%input%')
	List<Product> findByNameContainingIgnoreCase(String name);


	// Search by category (exact match but ignoring case)
	// Explanation:
	// "findBy" → query method  
	// "Category" → field name in Product  
	// "IgnoreCase" → ignore uppercase/lowercase  
	// Meaning: SELECT * FROM product WHERE LOWER(category) = LOWER(input)
	List<Product> findByCategoryIgnoreCase(String category);


	// Search by brand (exact match but case-insensitive)
	// Explanation:
	// Same logic as category  
	// Meaning: SELECT * FROM product WHERE LOWER(brand) = LOWER(input)
	List<Product> findByBrandIgnoreCase(String brand);


	// Price less than given number
	// Explanation:
	// "findBy" → query method  
	// "Price" → field in Product  
	// "LessThan" → SQL: price < input  
	// Meaning: SELECT * FROM product WHERE price < input
	List<Product> findByPriceLessThan(Double price);


	// Rating greater than or equal to given value
	// Explanation:
	// "findBy" → query method  
	// "Rating" → entity field  
	// "GreaterThanEqual" → SQL: rating >= input  
	// Meaning: SELECT * FROM product WHERE rating >= input
	List<Product> findByRatingGreaterThanEqual(Double rating);

	
	
	

}

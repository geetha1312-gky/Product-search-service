package com.retail.product_search_service.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor

//@Data is a Lombok shortcut that automatically generates:
/*public String getName() { ... }
public void setName(String name) { ... }
public String toString() { ... }
public boolean equals(Object o) { ... }
public int hashCode() { ... }*/
//@NoArgsConstructor — Generates a Default Constructor-Spring Boot & JPA require a no-argument constructor,JPA internally uses reflection to create objects.
//Without this constructor, you will get errors like:No default constructor for entity
//@AllArgsConstructor — Constructor With All Fields
//public Product(Long id, String name, String category, String brand, Double price, Double rating, Integer stock)
//For quickly creating product objects,For testing,For initializing sample data,For DTO mapping
//example-Product p = new Product(1L, "Laptop", "Electronics", "Dell", 55000.0, 4.5, 20);
//Without this annotation, you must manually write long constructors.

public class Product {
	public Product(Long id, String name, String category, String brand, Double price, Double rating) {
	    this.id = id;
	    this.name = name;
	    this.category = category;
	    this.brand = brand;
	    this.price = price;
	    this.rating = rating;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	@NotBlank(message = "Name is mandatory")
    @Column(nullable = false)
    private String name;        // Product name (eg: "Nike Shoes")

    private String category;    // Category (eg: "Footwear")
    private String brand;       // Brand name (eg: "Nike")
    private Double price;       // Product price
    private Double rating;      // Rating (eg: 4.5 out of 5)
}

package com.retail.product_search_service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.retail.product_search_service.Entity.Product;
import com.retail.product_search_service.Repository.Productrepository;
import com.retail.product_search_service.service.ProductServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private Productrepository productRepository;

    private Product product1;
    private Product product2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        product1 = new Product(1L, "Laptop", "Electronics", "Dell", 500.0, 4.5);
        product2 = new Product(2L, "Phone", "Electronics", "Samsung", 300.0, 4.0);
    }

    @Test
    public void testSaveProduct() {
        when(productRepository.save(product1)).thenReturn(product1);
        Product savedProduct = productService.saveProduct(product1);
        assertNotNull(savedProduct);
        assertEquals("Laptop", savedProduct.getName());
        verify(productRepository, times(1)).save(product1);
    }

    @Test
    public void testGetProductById_Found() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));
        Product foundProduct = productService.getProductById(1L);
        assertNotNull(foundProduct);
        assertEquals("Laptop", foundProduct.getName());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetProductById_NotFound() {
        when(productRepository.findById(3L)).thenReturn(Optional.empty());
        Product foundProduct = productService.getProductById(3L);
        assertNull(foundProduct);
        verify(productRepository, times(1)).findById(3L);
    }

    @Test
    public void testGetAllProducts() {
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));
        List<Product> products = productService.getAllProducts();
        assertEquals(2, products.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testSearchByName() {
        when(productRepository.findByNameContainingIgnoreCase("Laptop"))
                .thenReturn(Arrays.asList(product1));
        List<Product> products = productService.searchByName("Laptop");
        assertEquals(1, products.size());
        assertEquals("Laptop", products.get(0).getName());
        verify(productRepository, times(1)).findByNameContainingIgnoreCase("Laptop");
    }

    @Test
    public void testSearchByCategory() {
        when(productRepository.findByCategoryIgnoreCase("Electronics"))
                .thenReturn(Arrays.asList(product1, product2));
        List<Product> products = productService.searchByCategory("Electronics");
        assertEquals(2, products.size());
        verify(productRepository, times(1)).findByCategoryIgnoreCase("Electronics");
    }

    @Test
    public void testSearchByBrand() {
        when(productRepository.findByBrandIgnoreCase("Dell"))
                .thenReturn(Arrays.asList(product1));
        List<Product> products = productService.searchByBrand("Dell");
        assertEquals(1, products.size());
        assertEquals("Dell", products.get(0).getBrand());
        verify(productRepository, times(1)).findByBrandIgnoreCase("Dell");
    }

    @Test
    public void testSearchByPriceLessThan() {
        when(productRepository.findByPriceLessThan(400.0))
                .thenReturn(Arrays.asList(product2));
        List<Product> products = productService.searchByPriceLessThan(400.0);
        assertEquals(1, products.size());
        assertTrue(products.get(0).getPrice() < 400.0);
        verify(productRepository, times(1)).findByPriceLessThan(400.0);
    }

    @Test
    public void testSearchByRatingGreaterThanEqual() {
        when(productRepository.findByRatingGreaterThanEqual(4.5))
                .thenReturn(Arrays.asList(product1));
        List<Product> products = productService.searchByRatingGreaterThanEqual(4.5);
        assertEquals(1, products.size());
        assertTrue(products.get(0).getRating() >= 4.5);
        verify(productRepository, times(1)).findByRatingGreaterThanEqual(4.5);
    }
}

package com.retail.product_search_service;
/*
==========================================
UNIT TESTING — PART 1 & PART 2 (FULL EXPLANATION)
==========================================
WHAT IS UNIT TESTING?
Unit testing means testing ONE class at a time (usually a service class). The goal is to verify if the logic works correctly WITHOUT involving: database, API calls, email services, or external systems. So we isolate the class and test only its logic.
==========================================
JUNIT BASICS (Assertions)
==========================================
JUnit provides "assertions" to verify output.
MOST IMPORTANT ASSERTIONS:
1. assertEquals(expected, actual) -> Checks if both values are equal
2. assertNotNull(value) -> Checks the value is not null
3. assertNull(value) -> Checks the value is null
4. assertTrue(condition) -> Checks a condition is true
5. assertFalse(condition) -> Checks a condition is false
6. assertThrows(Exception.class, () -> { code }) -> Checks whether a method throws an exception
WHY ASSERTIONS? Because a unit test must automatically verify: Output is correct, Input is handled correctly, Code works for edge cases.
==========================================
MOCKITO — PART 2 (Mocking, when(), verify(), etc.)
==========================================
WHY DO WE NEED MOCKITO?
Mockito allows us to create FAKE objects for dependencies. Example: A ProductService uses ProductRepository. ProductRepository talks to a database. But in UNIT TESTING: we do NOT hit the real database, do NOT send real emails, do NOT call external APIs. Solution: Use Mockito to create a FAKE ProductRepository.
==========================================
IMPORTANT MOCKITO FEATURES
==========================================
1) @Mock — Creates a FAKE object of a dependency. Example: @Mock ProductRepository repo; This means repo does NOT talk to DB.
2) @InjectMocks — Creates the REAL object of the class you want to test and injects all @Mock objects into it. Example: @InjectMocks ProductService service; So: service = REAL, repo = FAKE, repo is injected inside service automatically.
3) MockitoAnnotations.openMocks(this) — Initializes @Mock and @InjectMocks before every test. Usually placed in @BeforeEach.
4) when(...).thenReturn(...) — Teaches mock how to behave. Example: when(repo.findById(1L)).thenReturn(Optional.of(product)); Meaning: If service calls repo.findById(1), Do NOT go to DB, Return product object immediately.
5) verify(mock, times(n)) — Checks whether a method was called. Example: verify(repo, times(1)).findById(1L); Ensures correct method is called and not called extra times.
6) any(), anyString(), anyLong() — Used when exact argument doesn't matter. Example: when(repo.findByName(anyString())).thenReturn(product);
7) doNothing().when(mock) — Used for mocking VOID methods. Example: doNothing().when(emailService).sendEmail(anyString());
8) thenThrow() — Used to mock exceptions. Example: when(repo.findById(10L)).thenThrow(new RuntimeException("DB error"));
==========================================
SUMMARY OF MOCKITO PURPOSE
==========================================
Mockito is used so we can test ONLY the service layer logic WITHOUT hitting DB or external APIs. Mockito replaces real dependencies with fakes. JUnit verifies output using ASSERTIONS. Together: JUnit + Mockito = PERFECT unit testing framework.


//General Unit Test Execution Flow (Arrange → Act → Assert → Verify)
*/import static org.junit.jupiter.api.Assertions.*;
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




/*
PART 3 — UNDERSTANDING YOUR UNIT TEST CLASS LINE BY LINE
1. @Mock: Creates a FAKE ProductRepository. It does NOT connect to database. Mockito controls its behavior.
2. @InjectMocks: Creates a REAL ProductServiceImpl object but injects the FAKE repository into it. So service is real, repository is fake.
3. @BeforeEach + MockitoAnnotations.openMocks(this): Runs BEFORE every test. It initializes @Mock and @InjectMocks. Every test starts fresh.
4. product1 & product2 objects: These are sample dummy data used for testing. They are NOT inserted into DB; they are only used inside the test.
---------------------------------------------
TEST METHOD BREAKDOWN:
Each test has 4 steps → Arrange → Act → Assert → Verify
---------------------------------------------
testSaveProduct:
ARRANGE: when(productRepository.save(product1)).thenReturn(product1);
Meaning: “If save() is called with product1, return product1.”
ACT: Product saved = productService.saveProduct(product1);
ASSERT: assertEquals("Laptop", saved.getName());
VERIFY: repository.save(product1) was called once.
---------------------------------------------
testGetProductById_Found:
ARRANGE: repository returns Optional.of(product1)
ACT: service.getProductById(1L)
ASSERT: product not null, name matches
VERIFY: findById called once
---------------------------------------------
testGetProductById_NotFound:
ARRANGE: repository returns Optional.empty()
ACT: service.getProductById(3L)
ASSERT: result is null (your service returns null for not found)
VERIFY: findById(3L) called once
---------------------------------------------
testGetAllProducts:
ARRANGE: findAll() returns [product1, product2]
ACT: service.getAllProducts()
ASSERT: size 2
VERIFY: findAll() called once
---------------------------------------------
testSearchByName:
ARRANGE: findByNameContainingIgnoreCase("Laptop") returns [product1]
ACT: service.searchByName("Laptop")
ASSERT: size 1, name is Laptop
VERIFY: method called
---------------------------------------------
testSearchByCategory:
ARRANGE: repository returns both products
ACT → service.searchByCategory("Electronics")
ASSERT → size 2
VERIFY → correct method called
---------------------------------------------
testSearchByBrand:
ARRANGE: brand "Dell" returns only product1
ASSERT: product1 brand matches
---------------------------------------------
testSearchByPriceLessThan:
ARRANGE: price < 400 returns product2
ASSERT: price < 400 true
---------------------------------------------
testSearchByRatingGreaterThanEqual:
ARRANGE: rating >= 4.5 returns product1
ASSERT: product1 rating >= 4.5
---------------------------------------------
SUMMARY:
Each test isolates exactly one method in service. Repository is mocked, so service logic alone is tested. Every test: (1) mocks repository output, (2) calls service, (3) checks output, (4) verifies repository usage. This is the standard pattern for all Spring Boot service layer tests.
*/


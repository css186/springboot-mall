package com.brian.springbootmall.controller;

import com.brian.springbootmall.constant.ProductCategory;
import com.brian.springbootmall.dto.ProductQueryParams;
import com.brian.springbootmall.dto.ProductRequest;
import com.brian.springbootmall.model.Product;
import com.brian.springbootmall.service.ProductService;
import com.brian.springbootmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Controller
@RestController
public class ProductController {

    // Inject product service interface
    @Autowired
    ProductService productService;

    // Return list of products
//    @GetMapping("/products")
//    public ResponseEntity<List<Product>>  getProducts(
//            // Filtering (filter by category & search by keyword)
//            @RequestParam(required = false) ProductCategory category,
//            @RequestParam(required = false) String search,
//
//            // Sorting
//            @RequestParam(defaultValue = "created_date") String orderBy,
//            @RequestParam(defaultValue = "desc") String sort,
//
//            // Pagination
//            @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
//            @RequestParam(defaultValue = "0") @Min(0) Integer offset
//    ) {
//        ProductQueryParams productQueryParams = new ProductQueryParams();
//        productQueryParams.setCategory(category);
//        productQueryParams.setSearch(search);
//        productQueryParams.setOrderBy(orderBy);
//        productQueryParams.setSort(sort);
//        productQueryParams.setLimit(limit);
//        productQueryParams.setOffset(offset);
//
//        List<Product> productList = productService.getProducts(productQueryParams);
//
//        return ResponseEntity.status(HttpStatus.OK).body(productList);
//    }

    // Enhanced way to return lists of product
    // return total number of data inside database for better pagination
    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getProducts(
            // Filtering (filter by category & search by keyword)
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) String search,

            // Sorting
            @RequestParam(defaultValue = "created_date") String orderBy,
            @RequestParam(defaultValue = "desc") String sort,

            // Pagination
            @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset
    ) {
        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setSort(sort);
        productQueryParams.setLimit(limit);
        productQueryParams.setOffset(offset);

        List<Product> productList = productService.getProducts(productQueryParams);

        // Count total number of data

        Integer total = productService.countProduct(productQueryParams);

        // Convert to page object
        Page<Product> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(productList);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    // Read
    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {
        // Get product from service
        Product product = productService.getProductById(productId);

        // Check null
        if (product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Create
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest) {

        // Created product and return its id
        Integer productId = productService.createProduct(productRequest);

        // Get product from the newly added id
        Product product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    // Update
    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Valid ProductRequest productRequest) {

        // Check if product exists
        Product product = productService.getProductById(productId);

        // if not, return NOT_FOUND
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // if exists, return product
        productService.updateProduct(productId, productRequest);

        Product updatedProduct = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }

    // Delete
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId) {

        productService.deleteProductById(productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

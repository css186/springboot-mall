package com.brian.springbootmall.service;

import com.brian.springbootmall.dto.ProductQueryParams;
import com.brian.springbootmall.dto.ProductRequest;
import com.brian.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);

    Integer countProduct(ProductQueryParams productQueryParams);
}

package com.brian.springbootmall.service;

import com.brian.springbootmall.constant.ProductCategory;
import com.brian.springbootmall.dto.ProductRequest;
import com.brian.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductCategory category, String search);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}

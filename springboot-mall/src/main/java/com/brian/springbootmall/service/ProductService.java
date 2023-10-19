package com.brian.springbootmall.service;

import com.brian.springbootmall.dto.ProductRequest;
import com.brian.springbootmall.model.Product;

public interface ProductService {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}

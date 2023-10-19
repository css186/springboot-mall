package com.brian.springbootmall.dao;

import com.brian.springbootmall.dto.ProductRequest;
import com.brian.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);
}

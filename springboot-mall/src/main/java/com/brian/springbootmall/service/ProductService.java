package com.brian.springbootmall.service;

import com.brian.springbootmall.model.Product;

public interface ProductService {
    Product getProductById(Integer productId);
}

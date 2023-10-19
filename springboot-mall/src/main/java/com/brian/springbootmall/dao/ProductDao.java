package com.brian.springbootmall.dao;

import com.brian.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);
}

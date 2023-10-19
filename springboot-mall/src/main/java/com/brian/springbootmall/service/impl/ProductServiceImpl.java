package com.brian.springbootmall.service.impl;

import com.brian.springbootmall.dao.ProductDao;
import com.brian.springbootmall.model.Product;
import com.brian.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {
    // Inject Dao interface
    @Autowired
    ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }
}

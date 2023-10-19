package com.brian.springbootmall.dao.impl;

import com.brian.springbootmall.dao.ProductDao;
import com.brian.springbootmall.model.Product;
import com.brian.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Product getProductById(Integer productId) {
        // SQL query
        String sql = "SELECT product_id, product_name, category, " +
                "image_url, price, stock, description, created_date, last_modified_date " +
                "FROM product WHERE product_id = :productId";

        // Create a map to match product_id variable
        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        // Get product list from querying db
        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        // Check return length
        if (productList.size() > 0) {
            return productList.get(0);
        } else {
            return null;
        }
    }
}

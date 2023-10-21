package com.brian.springbootmall.service.impl;

import com.brian.springbootmall.dao.OrderDao;
import com.brian.springbootmall.dao.ProductDao;
import com.brian.springbootmall.dto.BuyItem;
import com.brian.springbootmall.dto.CreateOrderRequest;
import com.brian.springbootmall.model.OrderItem;
import com.brian.springbootmall.model.Product;
import com.brian.springbootmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        // Calculate total amount
        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());

            int itemAmount = buyItem.getQuantity() * product.getPrice();
            totalAmount += itemAmount;

            // Convert BuyItem to OrderItem object
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(totalAmount);

            orderItemList.add(orderItem);
        }

        // Create order
        Integer orderId = orderDao.createOrder(userId, totalAmount);

        orderDao.createOrderItems(orderId, orderItemList);

        return orderId;
    }
}

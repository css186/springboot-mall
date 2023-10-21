package com.brian.springbootmall.service;

import com.brian.springbootmall.dto.CreateOrderRequest;
import com.brian.springbootmall.model.Order;

public interface OrderService {
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer orderId);
}

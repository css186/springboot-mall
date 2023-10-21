package com.brian.springbootmall.service;

import com.brian.springbootmall.dto.CreateOrderRequest;
import com.brian.springbootmall.dto.OrderQueryParams;
import com.brian.springbootmall.model.Order;

import java.util.List;

public interface OrderService {
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer orderId);

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);
}

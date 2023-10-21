package com.brian.springbootmall.dao;

import com.brian.springbootmall.dto.OrderQueryParams;
import com.brian.springbootmall.model.Order;
import com.brian.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {
    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, Integer totalAmount);

    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);

    List<OrderItem> getOrderItemsByOrderId(Integer orderId);

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);
}

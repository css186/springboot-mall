package com.brian.springbootmall.service.impl;

import com.brian.springbootmall.dao.OrderDao;
import com.brian.springbootmall.dao.ProductDao;
import com.brian.springbootmall.dao.UserDao;
import com.brian.springbootmall.dto.BuyItem;
import com.brian.springbootmall.dto.CreateOrderRequest;
import com.brian.springbootmall.dto.OrderQueryParams;
import com.brian.springbootmall.model.Order;
import com.brian.springbootmall.model.OrderItem;
import com.brian.springbootmall.model.Product;
import com.brian.springbootmall.model.User;
import com.brian.springbootmall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    // Add log
    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        // Check user's existence
        User user = userDao.getUserById(userId);

        if (user == null) {
            log.warn("User: {} doest not exist.", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());

            // check stock of product
            // no stock
            if (product == null) {
                log.warn("Product {} does not exist", buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            // not enough
            } else if (product.getStock() < buyItem.getQuantity()) {
                log.warn("Product {} is not enough. You ordered {}, and the remaining stock is {}.",
                        product.getProductId(), buyItem.getQuantity(), product.getStock());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            // Minus product stock
            productDao.updateStock(product.getProductId(), product.getStock() - buyItem.getQuantity());


            // Count total amount
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

    @Override
    public Order getOrderById(Integer orderId) {
        // 取得訂單
        Order order = orderDao.getOrderById(orderId);

        // 取得訂單明細
        List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(orderId);

        order.setOrderItemList(orderItemList);

        return order;
    }

    @Override
    public Integer countOrder(OrderQueryParams orderQueryParams) {
        return orderDao.countOrder(orderQueryParams);
    }

    @Override
    public List<Order> getOrders(OrderQueryParams orderQueryParams) {
        List<Order> orderList = orderDao.getOrders(orderQueryParams);

        for (Order order: orderList) {
            List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(order.getOrderId());

            order.setOrderItemList(orderItemList);
        }

        return orderList;
    }
}

package telran.org.scotlandyard.service;

import telran.org.scotlandyard.entity.Order;

import java.util.List;

public interface OrderService {


    List<Order> getAllOrders();

    Order findById(Long id);

    Order create(Order order);

    void deleteById(Long id);
}

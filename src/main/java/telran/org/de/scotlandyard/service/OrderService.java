package telran.org.de.scotlandyard.service;

import telran.org.de.scotlandyard.entity.Order;

import java.util.List;

public interface OrderService {



    List<Order> getAllOrders();

    Order findById(Long id);

    Order create(Order order);

    void deleteById(Long id);

    List<Order> getAllByCurrentUser();
}

package telran.org.de.scotlandyard.service;

import telran.org.de.scotlandyard.dto.orderdto.OrderStatusDto;
import telran.org.de.scotlandyard.entity.Order;
import telran.org.de.scotlandyard.model.Status;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();

    Order findById(Long id);

    Order create(Order order);

    void deleteById(Long id);

    List<Order> getAllByCurrentUser();

    OrderStatusDto getStatus(Long id);

    void changeStatus(Long orderId, Status status);
}

package telran.org.scotlandyard.service;

import telran.org.scotlandyard.entity.Order;

import java.util.List;

public interface OrderSevice {


    List<Order> getAll();

//    Order getById(Long id);

    Order create(Order order);
}

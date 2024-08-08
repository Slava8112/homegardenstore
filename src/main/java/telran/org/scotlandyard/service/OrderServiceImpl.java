package telran.org.scotlandyard.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telran.org.scotlandyard.entity.Order;
import telran.org.scotlandyard.repository.OrderReposit;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderSevice{

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    public OrderReposit orderReposit;

    @Override
    public List<Order> getAll(){
     return orderReposit.findAll();
    }

//    @Override
//    public Order getById(Long orderId) {
//             //   .orElseThrow(() -> new OrderNotFoundException("No Order with id " + id));;
//        Order order = (Order) orderReposit.getById(orderId);
//        log.debug("Order with id {}, Order {}", order.getId(), order);
//        return order;
//    }

    @Override
    public Order create(Order order) {
        log.debug("Order was sacsessfully added  Order {}", order);
        return (Order) orderReposit.save(order);
    }

}

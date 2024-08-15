package telran.org.scotlandyard.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telran.org.scotlandyard.entity.Order;
import telran.org.scotlandyard.repository.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderSevice{

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    public OrderRepository orderRepository;

    @Override
    public List<Order> getAll(){
     return orderRepository.findAll();
    }

//    @Override
//    public Order findById(Long orderId) {
//             //   .orElseThrow(() -> new OrderNotFoundException("No Order with id " + id));;
//        Order unit = orderRepositoryfindById(orderId).get();
//        //log.debug("Order with id {}, Order {}", order.getId(), order);
//        return unit;
//    }

    @Override
    public Order create(Order order) {
        log.debug("Order was sacsessfully added  Order {}", order);
        return (Order) orderRepository.save(order);
    }

}

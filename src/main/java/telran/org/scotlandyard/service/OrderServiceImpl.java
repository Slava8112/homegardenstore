package telran.org.scotlandyard.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import telran.org.scotlandyard.entity.Order;
import telran.org.scotlandyard.repository.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderSevice{

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    public final OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders(){
     return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
             //   .orElseThrow(() -> new OrderNotFoundException("No Order with id " + id));;
        return orderRepository.findById(id).get();
        //log.debug("Order with id {}, Order {}", order.getId(), order);
    }

    @Override
    public Order create(Order order) {
        log.debug("Order was sacsessfully added  Order {}", order);
        return (Order) orderRepository.save(order);
    }

    @Override
    public void deleteById(Long orderId) {
        orderRepository.deleteById(orderId);
    }

}

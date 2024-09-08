package telran.org.de.scotlandyard.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import telran.org.de.scotlandyard.dto.orderdto.OrderDTO;
import telran.org.de.scotlandyard.entity.CartItems;
import telran.org.de.scotlandyard.entity.Order;
import telran.org.de.scotlandyard.entity.OrderItem;
import telran.org.de.scotlandyard.entity.UserEntity;
import telran.org.de.scotlandyard.repository.OrderRepository;
import telran.org.de.scotlandyard.entity.Cart;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final CartService cartService;

    @Override
    public List<Order> getAllOrders(){
     return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
             //   .orElseThrow(() -> new OrderNotFoundException("No Order with id " + id));;
        return orderRepository.findById(id).get();

    }

    @Override
    public Order create(Order order) {
//        log.debug("Order was sacsessfully added  Order {}", order);
//        return (Order) orderRepository.save(order);
        Long userId = userService.getCurrentUser().getId();
        Cart cart = cartService.findByUserId(userId);

        // Переносим товары из корзины в заказ
        Set<CartItems> cartItems = cart.getCartItems();
        for (CartItems item : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(item.getProduct());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPricePurshause(item.getPricePurshause());
            order.addOrderItem(orderItem);
        }

        // Создаём заказ
        Order createdOrder = orderRepository.save(order);

        // Очищаем корзину после создания заказа
        cartService.clearCartForUser();

        return createdOrder;
    }

    @Override
    public void deleteById(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> getAllByCurrentUser() {
//        String userEmail = null;
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        UserEntity userEntity = userService.findByEmail(userEmail);
        List<Order> orders = orderRepository.findAllByUserEntity(userEntity);

        return orders;
    }


}

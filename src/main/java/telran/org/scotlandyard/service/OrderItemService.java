package telran.org.scotlandyard.service;

import telran.org.scotlandyard.entity.OrderItem;

import java.util.List;

public interface OrderItemService {

    List<OrderItem> getAllOrderItem();

    OrderItem getById(Long orderItemId);

    OrderItem addOrderItem(String productsId, OrderItem orderItem);

    void deleteById(Long orderItemId);
}

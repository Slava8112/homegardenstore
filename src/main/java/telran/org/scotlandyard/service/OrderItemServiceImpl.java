package telran.org.scotlandyard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import telran.org.scotlandyard.entity.OrderItem;
import telran.org.scotlandyard.entity.Product;
import telran.org.scotlandyard.repository.OrderItemsRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService{

    private final ProductService productService;
    private final OrderItemsRepository repository;

    @Override
    public List<OrderItem> getAllOrderItem() {
        return repository.findAll();
    }

    @Override
    public OrderItem getById(Long orderItemId) {
        return repository.getById(orderItemId);
    }

    @Override
    public OrderItem addOrderItem(String productsId, OrderItem orderItem) {
                Product product = productService.getById(Long.valueOf(productsId));
        orderItem.setProduct(product);
        return repository.save(orderItem);
    }

    @Override
    public void  deleteById(Long orderItemId) {
       repository.deleteById(orderItemId);
    }
}

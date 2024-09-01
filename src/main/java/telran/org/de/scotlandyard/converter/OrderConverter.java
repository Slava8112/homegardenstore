package telran.org.de.scotlandyard.converter;

import org.springframework.stereotype.Component;
import telran.org.de.scotlandyard.dto.orderdto.OrderCreateDto;
import telran.org.de.scotlandyard.dto.orderdto.OrderDTO;
import telran.org.de.scotlandyard.entity.Order;
import telran.org.de.scotlandyard.entity.OrderItem;
import telran.org.de.scotlandyard.dto.cartitemdto.CartItemDTO;
import telran.org.de.scotlandyard.dto.cartitemdto.CartItemCreateDto;
import telran.org.de.scotlandyard.entity.Product;
import telran.org.de.scotlandyard.exception.ProductNotFoundException;
import telran.org.de.scotlandyard.service.ProductService;

import java.util.stream.Collectors;

@Component
public class OrderConverter implements Converter<Order, OrderDTO, OrderCreateDto> {

    private final ProductService productService;

    public OrderConverter(ProductService productService) {
        this.productService = productService;
    }
    @Override
    public Order toEntity(OrderCreateDto dto) {
        Order order = new Order();
        order.setDeliveryAddress(dto.deliveryAddress());
        order.setDeliveryMethod(dto.deliveryMethod());
        order.setStatus(dto.status());  // Используем метод status()
        order.setOrderItems(dto.items().stream()
                .map(this::convertCartItemCreateDtoToEntity)
                .collect(Collectors.toSet()));
        return order;
    }
    @Override
    public OrderDTO toDto(Order entity) {
        return new OrderDTO(
                entity.getId(),
                entity.getOrderItems().stream()
                        .map(this::convertCartItemToDTO)
                        .collect(Collectors.toList()),
                entity.getDeliveryAddress(),
                entity.getDeliveryMethod(),
                entity.getStatus()
        );
    }
    private CartItemDTO convertCartItemToDTO(OrderItem item) {
        return new CartItemDTO(item.getProduct().getId(), item.getQuantity());
    }
    private OrderItem convertCartItemCreateDtoToEntity(CartItemCreateDto dto) {
        OrderItem item = new OrderItem();
        Product product = productService.getById(dto.productId());
        if (product == null) {
            throw new ProductNotFoundException("Product with ID " + dto.productId() + " not found");
        }
        item.setProduct(product);
        item.setQuantity(dto.quantity());
        return item;
    }
}


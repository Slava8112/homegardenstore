package telran.org.de.scotlandyard.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import telran.org.de.scotlandyard.converter.OrderConverter;
import telran.org.de.scotlandyard.dto.orderdto.OrderDTO;
import telran.org.de.scotlandyard.dto.orderdto.OrderStatusDto;
import telran.org.de.scotlandyard.entity.Cart;
import telran.org.de.scotlandyard.entity.Order;
import telran.org.de.scotlandyard.entity.OrderItem;
import telran.org.de.scotlandyard.model.Status;
import telran.org.de.scotlandyard.service.CartService;
import telran.org.de.scotlandyard.service.HistoryService;
import telran.org.de.scotlandyard.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderConverter orderConverter;
    private final CartService cartService;
    private final HistoryService historyService;

    @Operation(summary = "Получить список всех заказов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список заказов успешно получен"),
    })
    @GetMapping("/OrderAll")
    public ResponseEntity<List<OrderDTO>> list() {
        List<OrderDTO> orders = orderService.getAllOrders().stream()
                .map(orderConverter::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orders);
    }

    @Operation(summary = "Получить заказ по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Заказ успешно найден"),
            @ApiResponse(responseCode = "404", description = "Заказ не найден")
    })
    @GetMapping("/OrderById/{id}")
    public ResponseEntity<OrderDTO> getById(@PathVariable Long id) {
        Order order = orderService.findById(id);
        return ResponseEntity.ok(orderConverter.toDto(order));
    }

    @Operation(summary = "Создать новый заказ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Заказ успешно создан"),
            @ApiResponse(responseCode = "400", description = "Корзина пуста")
    })
    @PostMapping("/OrderCreate")
    public ResponseEntity<OrderDTO> create() {
        Cart currentCart = cartService.getCurrentUserCart();

        if (currentCart.getCartItems().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Order order = new Order();
        order.setUserEntity(currentCart.getUserEntity());
        order.setStatus(Status.AWAIT_PAYMENT);
        order.setCreatedAT(new Date());

        currentCart.getCartItems().forEach(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPricePurshause(cartItem.getPricePurshause());
            orderItem.setOrder(order);
            order.addOrderItem(orderItem);
        });

        Order createdOrder = orderService.create(order);

        cartService.clearCartForUser();

        return ResponseEntity.status(HttpStatus.CREATED).body(orderConverter.toDto(createdOrder));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить статус заказа по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Статус заказа успешно получен"),
            @ApiResponse(responseCode = "404", description = "Заказ не найден")
    })
    public ResponseEntity<OrderStatusDto> getStatus(@PathVariable Long id) {

        OrderStatusDto orderStatus = orderService.getStatus(id);
        return ResponseEntity.ok(orderStatus);
    }

    @Operation(summary = "Удалить заказ по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Заказ успешно удален"),
            @ApiResponse(responseCode = "404", description = "Заказ не найден")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        orderService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получить список заказов текущего пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Заказы успешно найдены"),
            @ApiResponse(responseCode = "404", description = "Заказы не найдены")
    })
    @GetMapping("/order_by_current_user")
    public ResponseEntity<List<OrderDTO>> listByCurrientUser() {

        List<OrderDTO> orders = orderService.getAllByCurrentUser().stream()
                .map(orderConverter::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(orders);
    }
    @Operation(summary = "Получить историю заказов пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "История заказов успешно получена"),
            @ApiResponse(responseCode = "404", description = "Заказы не найдены")
    })
    @GetMapping("/history/{userId}")
    public ResponseEntity<?> getUserOrderHistory(@PathVariable Long userId) {
        List<OrderDTO> orderHistory = historyService.getUserOrderHistory(userId);
        if (orderHistory.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Заказы не найдены для данного пользователя");
        }
        return ResponseEntity.ok(orderHistory);
    }

}

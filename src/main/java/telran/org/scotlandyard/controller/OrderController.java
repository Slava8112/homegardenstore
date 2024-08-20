package telran.org.scotlandyard.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import telran.org.scotlandyard.entity.Order;
import telran.org.scotlandyard.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderSevice;

    @Operation(summary = "Получить список всех заказов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список заказов успешно получен"),
    })
    @GetMapping("/OrderAll")
    public List<Order> list() {
        return orderSevice.getAllOrders();
    }

    @Operation(summary = "Получить заказ по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Заказ успешно найден"),
            @ApiResponse(responseCode = "404", description = "Заказ не найден")
    })
    @GetMapping("/OrderById/{id}")
    public Order getById(@PathVariable Long id) {
        return orderSevice.findById(id);
    }

    @Operation(summary = "Создать новый заказ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Заказ успешно создан"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос")
    })
    @PostMapping("/OrderCreate")
    public Order create(@RequestBody Order order) {
        return orderSevice.create(order);
    }

    @Operation(summary = "Удалить заказ по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Заказ успешно удален"),
            @ApiResponse(responseCode = "404", description = "Заказ не найден")
    })
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        orderSevice.deleteById(id);
    }
}

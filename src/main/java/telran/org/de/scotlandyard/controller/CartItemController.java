package telran.org.de.scotlandyard.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import telran.org.de.scotlandyard.entity.CartItems;
import telran.org.de.scotlandyard.repository.CartItemsRepository;
import telran.org.de.scotlandyard.service.CartItemsService;

import java.util.List;

@RestController
@RequestMapping("v1/cartItems")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemsService cartItemsService;

    private final CartItemsRepository cartItemsRepository;

    @Operation(summary = "Получить список всех элементов корзины")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение списка элементов корзины")
    })
    @GetMapping
    public List<CartItems> list() {
        return cartItemsService.getAll();
    }

    @Operation(summary = "Добавить элемент в корзину")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Элемент успешно добавлен в корзину"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос")
    })
    @PostMapping("/id")
    public CartItems add(@RequestParam Long productsId,
                         @RequestParam int quantity) {
        return cartItemsService.add(productsId, quantity);
    }

    @Operation(summary = "Удалить элемент корзины по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Элемент корзины успешно удален"),
            @ApiResponse(responseCode = "404", description = "Элемент корзины не найден")
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        cartItemsRepository.deleteById(id);
    }
}

package telran.org.de.scotlandyard.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import telran.org.de.scotlandyard.entity.Cart;
import telran.org.de.scotlandyard.service.CartService;

@RestController
@RequestMapping("v1/carts")
@RequiredArgsConstructor
public class CartController {

    public final CartService cartService;

    @Operation(summary = "Удаление корзины по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Корзина успешно удалена"),
            @ApiResponse(responseCode = "404", description = "Корзина не найдена")
    })
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        cartService.delete(id);
    }


}

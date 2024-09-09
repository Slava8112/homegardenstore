package telran.org.de.scotlandyard.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import telran.org.de.scotlandyard.converter.CartConverter;
import telran.org.de.scotlandyard.dto.cartdto.CartCreateDto;
import telran.org.de.scotlandyard.dto.cartdto.CartDto;
import telran.org.de.scotlandyard.entity.Cart;
import telran.org.de.scotlandyard.service.CartService;
import telran.org.de.scotlandyard.service.UserService;

@Slf4j
@RestController
@RequestMapping("v1/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartConverter converter;
    private final UserService userService;

    @Operation(summary = "Создание новой корзины")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Корзина успешно создана"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные")
    })
    @PostMapping
    public ResponseEntity<CartDto> createCart(@RequestBody CartCreateDto cartCreateDto){
//        Long userId = userService.getCurrentUser().getId();
//       Cart newCart = cartService.create(cartCreateDto);
        CartDto cartDto = cartService.create(cartCreateDto);
        return ResponseEntity.ok(cartDto);

    }

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

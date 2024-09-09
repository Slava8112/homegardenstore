package telran.org.de.scotlandyard.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import telran.org.de.scotlandyard.converter.CartConverter;
import telran.org.de.scotlandyard.dto.cartdto.CartCreateDto;
import telran.org.de.scotlandyard.dto.cartdto.CartDto;
import telran.org.de.scotlandyard.entity.Cart;
import telran.org.de.scotlandyard.service.CartService;

@RestController
@RequestMapping("v1/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartConverter converter;

    @Operation(summary = "Создание новой корзины")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Корзина успешно создана"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные")
    })
    @PostMapping
    public ResponseEntity<CartDto> createCart(@RequestBody CartCreateDto createDto) {
        Cart cart = converter.toEntity(createDto);
        Cart createdCart = cartService.create(cart);
        CartDto responseDto = converter.toDto(createdCart);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
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

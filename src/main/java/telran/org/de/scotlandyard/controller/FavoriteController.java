package telran.org.de.scotlandyard.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import telran.org.de.scotlandyard.converter.FavoriteConverter;
import telran.org.de.scotlandyard.dto.favoritedto.FavoriteCreateDto;
import telran.org.de.scotlandyard.dto.favoritedto.FavoriteDto;
import telran.org.de.scotlandyard.entity.Favorite;
import telran.org.de.scotlandyard.service.FavoriteService;
import telran.org.de.scotlandyard.service.ProductService;
import telran.org.de.scotlandyard.service.ProductServiceImpl;
import telran.org.de.scotlandyard.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;
    private final FavoriteConverter converter;
private final UserService userService;

    @Operation(summary = "Добавить товар в избранное")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Товар успешно добавлен в избранное"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос")
    })
    @PostMapping
    public ResponseEntity<FavoriteDto> create(@RequestBody FavoriteCreateDto favoriteCreateDto) {

        Long userId = userService.getCurrentUserId();
        Favorite favoritenew = favoriteService.createFavorite(userId, favoriteCreateDto.getProductId());
        //Favorite favorite = converter.toEntity(favoriteCreateDto);
        FavoriteDto responseDto = converter.toDto(favoritenew);
        return new  ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Получить избранный товар по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Избранный товар успешно найден"),
            @ApiResponse(responseCode = "404", description = "Избранный товар не найден")
    })
    @GetMapping("/{id}")
    public ResponseEntity<FavoriteDto> getById(@PathVariable Long userEntityId) {
        Favorite favorite = (Favorite) favoriteService.getUsersFavoritesByUserId(userEntityId);
        return ResponseEntity.ok(converter.toDto(favorite));
    }

    @Operation(summary = "Получить список всех избранных товаров")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список избранных товаров успешно получен")
    })
    @GetMapping
    public List<FavoriteDto> getAll() {
        return favoriteService.getAllFavorites().stream()
                .map(converter::toDto).collect(Collectors.toList());
    }

    @Operation(summary = "Получить список избранных товаров текущего пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список избранных товаров успешно получен"),
            @ApiResponse(responseCode = "401", description = "Неверный логин или пароль")
    })
    @GetMapping("/favorite_by_current_user")
    public ResponseEntity<List<FavoriteDto>> listFavoritesByCurrentUser(){

        List<FavoriteDto> favorites = favoriteService.getFavoritesByCurrentUser()
                .stream().map(converter::toDto).collect(Collectors.toList());

        return ResponseEntity.ok(favorites);
    }

    @Operation(summary = "Remove a product from favorites", description = "Removes a specified product from the current user's favorites.")
    @ApiResponse(responseCode = "204", description = "Product removed from favorites")
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> removeProductFromFavorites(@PathVariable Long productId) {
        //log.debug("Removing product {} from favorites for current user", productId);
        Long userId = userService.getCurrentUserId(); // Получаем ID текущего пользователя(,,)
        favoriteService.delete(userId, productId);
        return ResponseEntity.noContent().build();
    }
}

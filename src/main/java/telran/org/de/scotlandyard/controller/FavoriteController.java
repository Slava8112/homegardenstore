package telran.org.de.scotlandyard.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import telran.org.de.scotlandyard.service.FavoriteService;
import telran.org.de.scotlandyard.entity.Favorite;

import java.util.List;

@RestController
@RequestMapping("v1/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @Operation(summary = "Добавить товар в избранное")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Товар успешно добавлен в избранное"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос")
    })
    @PostMapping
    public Favorite create(@RequestParam Long userEntityId, @RequestBody Favorite favorite) {
        return favoriteService.createFavorite(userEntityId, favorite);
    }

    @Operation(summary = "Получить избранный товар по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Избранный товар успешно найден"),
            @ApiResponse(responseCode = "404", description = "Избранный товар не найден")
    })
    @GetMapping("/{id}")
    public Favorite getById(@PathVariable Long userEntityId) {
        return favoriteService.findById(userEntityId);
    }

    @Operation(summary = "Получить список всех избранных товаров")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список избранных товаров успешно получен")
    })
    @GetMapping
    public List<Favorite> getAll() {
        return favoriteService.getAllFavorites();
    }
}

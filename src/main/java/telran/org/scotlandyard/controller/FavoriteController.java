package telran.org.scotlandyard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telran.org.scotlandyard.entity.Favorite;
import telran.org.scotlandyard.service.FavoriteService;

import java.util.List;

@RestController
@RequestMapping("/v1/favorits")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

@PostMapping
    public Favorite create(@RequestParam Long userEntityId, @RequestBody Favorite favorite){
    return favoriteService.createFavorite(userEntityId,favorite);
}
@GetMapping("/{id}")
    public Favorite getById(@PathVariable Long userEntityId){
    return favoriteService.findById(userEntityId);
    }

    @GetMapping
    public List<Favorite> getAll(){
    return favoriteService.getAllFavorites();
    }
}

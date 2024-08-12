package telran.org.scotlandyard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telran.org.scotlandyard.entity.Favorite;
import telran.org.scotlandyard.service.FavoriteService;

import java.util.List;

@RestController
@RequestMapping("favorits")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

@PostMapping
    public Favorite create(@RequestParam Long user_out_id, @RequestBody Favorite favorite){
    return favoriteService.createFavorite(user_out_id,favorite);
}
@GetMapping("/{id}")
    public Favorite getById(@PathVariable Long user_out_id){
    return favoriteService.findById(user_out_id);
    }
    @GetMapping
    public List<Favorite> getAll(){
    return favoriteService.getAllFavorites();
    }
}

package telran.org.scotlandyard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import telran.org.scotlandyard.entity.Cart;
import telran.org.scotlandyard.entity.UserEntity;
import telran.org.scotlandyard.service.CartService;
import telran.org.scotlandyard.service.UserService;

@RestController
@RequestMapping("carts")
@RequiredArgsConstructor
public class CartController {

    public final CartService cartService;

//    @PostMapping
//    public Cart add(@RequestParam Long userEntityId) {
//        return cartService.create(userEntityId);
//    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        cartService.delete(id);
    }


}

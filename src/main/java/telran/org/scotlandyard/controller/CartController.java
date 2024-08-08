package telran.org.scotlandyard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import telran.org.scotlandyard.entity.Cart;
import telran.org.scotlandyard.service.CartService;

@RestController
@RequestMapping("carts")
@RequiredArgsConstructor
public class CartController {

    public final CartService cartService;

    @PostMapping
    public Cart add(@RequestBody Cart cart) {
        return cartService.create(cart);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        cartService.delete(id);
    }
}

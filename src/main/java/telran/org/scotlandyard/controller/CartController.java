package telran.org.scotlandyard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import telran.org.scotlandyard.service.CartService;

@RestController
@RequestMapping("/v1/carts")
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

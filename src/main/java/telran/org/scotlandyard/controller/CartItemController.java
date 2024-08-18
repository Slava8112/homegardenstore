package telran.org.scotlandyard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import telran.org.scotlandyard.entity.Cartitems;
import telran.org.scotlandyard.repository.CartItemsRepository;
import telran.org.scotlandyard.service.CartItemsService;
import telran.org.scotlandyard.service.CartService;
import telran.org.scotlandyard.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("cartItems")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemsService cartItemsService;
    private final ProductService productService;
    private final CartService cartService;
    private final CartItemsRepository cartItemsRepository;

    @GetMapping
    public List<Cartitems> list() {
        return cartItemsService.getAll();
    }

    @PostMapping("/id")
    public Cartitems add(@RequestParam Long productsId,
                         @RequestBody Cartitems cartitems) {
        return cartItemsService.add(productsId, cartitems);
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestParam Long cartItemsId) {
        cartItemsRepository.deleteById(cartItemsId);
    }
}
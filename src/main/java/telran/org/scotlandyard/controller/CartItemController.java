package telran.org.scotlandyard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import telran.org.scotlandyard.entity.Cartitems;
import telran.org.scotlandyard.service.CartItemsService;

import java.util.List;

@RestController
@RequestMapping("cartItems")
@RequiredArgsConstructor
public class CartItemController {
    private final CartItemsService cartItemsService;

    @GetMapping
    public List<Cartitems> list(){
        return cartItemsService.getAll();
    }


}

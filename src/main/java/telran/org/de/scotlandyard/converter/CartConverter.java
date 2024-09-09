package telran.org.de.scotlandyard.converter;

import org.springframework.stereotype.Component;
import telran.org.de.scotlandyard.dto.cartdto.CartCreateDto;
import telran.org.de.scotlandyard.dto.cartdto.CartDto;
import telran.org.de.scotlandyard.entity.Cart;
import telran.org.de.scotlandyard.entity.CartItems;
import telran.org.de.scotlandyard.service.UserService;

import java.util.List;

@Component
public class CartConverter implements Converter<Cart, CartDto, CartCreateDto> {

    private UserService userService;

    @Override
    public Cart toEntity(CartCreateDto cartCreateDto) {
       Cart cart = new Cart();
        return cart;
    }

    @Override
    public CartDto toDto(Cart cart) {
        CartDto dto = new CartDto(cart.getId(), cart.getUserEntity().getId());
        return dto;
    }
}

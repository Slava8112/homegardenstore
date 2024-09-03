package telran.org.de.scotlandyard.converter;

import telran.org.de.scotlandyard.dto.cartdto.CartCreateDto;
import telran.org.de.scotlandyard.dto.cartdto.CartDto;
import telran.org.de.scotlandyard.entity.Cart;

public class CartConverter implements Converter<Cart, CartDto, CartCreateDto> {
    @Override
    public Cart toEntity(CartCreateDto cartCreateDto) {
       Cart cart = new Cart();
        return cart;
    }

    @Override
    public CartDto toDto(Cart cart) {
        CartDto dto = new CartDto(cart.getId());
        return dto;
    }
}

package telran.org.de.scotlandyard.converter;

import org.springframework.stereotype.Component;
import telran.org.de.scotlandyard.dto.cartdto.CartCreateDto;
import telran.org.de.scotlandyard.dto.cartdto.CartDto;
import telran.org.de.scotlandyard.dto.cartitemdto.CartItemCreateDto;
import telran.org.de.scotlandyard.entity.Cart;
import telran.org.de.scotlandyard.entity.CartItems;
import telran.org.de.scotlandyard.service.ProductService;
import telran.org.de.scotlandyard.service.UserService;

@Component
public class CartConverter implements Converter<Cart, CartDto, CartCreateDto> {
    private final UserService userService;
    private final ProductService productService;

    public CartConverter(UserService userService,ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public Cart toEntity(CartCreateDto cartCreateDto) {
        Cart cart = new Cart();
        cart.setUserEntity(userService.getById(cartCreateDto.userId()));
        for (CartItemCreateDto itemDto : cartCreateDto.cartItems()) {
            CartItems cartItem = new CartItems();
            cartItem.setProduct(productService.getById(itemDto.productId()));
            cartItem.setQuantity(itemDto.quantity());
            cartItem.setCart(cart);
            cart.getCartItems().add(cartItem);
        }

        return cart;
    }

    @Override
    public CartDto toDto(Cart cart) {
        return new CartDto(cart.getId(), cart.getUserEntity().getId());
    }
}

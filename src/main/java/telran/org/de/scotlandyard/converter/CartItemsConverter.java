package telran.org.de.scotlandyard.converter;

import telran.org.de.scotlandyard.dto.cartitemdto.CartItemCreateDto;
import telran.org.de.scotlandyard.dto.cartitemdto.CartItemDTO;
import telran.org.de.scotlandyard.entity.Cart;
import telran.org.de.scotlandyard.entity.CartItems;
import telran.org.de.scotlandyard.entity.Product;

public class CartItemsConverter {
    public CartItemDTO toDto(CartItems cartItems) {
        return new CartItemDTO(
                cartItems.getProduct().getId(),
                cartItems.getQuantity()
        );
    }

    public CartItems toEntity(CartItemCreateDto cartItemCreateDto, Product product, Cart cart) {
        CartItems cartItems = new CartItems();
        cartItems.setProduct(product);
        cartItems.setCart(cart);
        cartItems.setQuantity(cartItemCreateDto.quantity());
        return cartItems;
    }

}

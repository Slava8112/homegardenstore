package telran.org.de.scotlandyard.service;

import telran.org.de.scotlandyard.dto.cartdto.CartCreateDto;
import telran.org.de.scotlandyard.dto.cartdto.CartDto;
import telran.org.de.scotlandyard.entity.Cart;

public interface CartService {

    void delete(Long cartId);

//    Cart findByUserId();

    Cart create(CartCreateDto cartCreateDto);

    void clearCartForUser();

    Cart findByUserId(Long userId);

    Cart getCurrentUserCart();

}

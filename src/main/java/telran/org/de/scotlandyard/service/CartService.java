package telran.org.de.scotlandyard.service;

import telran.org.de.scotlandyard.dto.cartdto.CartCreateDto;
import telran.org.de.scotlandyard.dto.cartdto.CartDto;
import telran.org.de.scotlandyard.entity.Cart;

public interface CartService {

    void delete(Long userEntity);

    //Cart findByUserId();

    CartDto create(CartCreateDto cart);

    void clearCartForUser();

    Cart findByUserId(Long userId);

}

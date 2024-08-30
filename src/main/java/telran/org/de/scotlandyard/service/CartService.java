package telran.org.de.scotlandyard.service;

import telran.org.de.scotlandyard.entity.Cart;

public interface CartService {

    Cart getById(Long cartId);

    void delete(Long userEntity);
}

package telran.org.scotlandyard.service;

import telran.org.scotlandyard.entity.Cart;

public interface CartService {

    Cart getById(Long cartId);

    void delete(Long userEntity);
}

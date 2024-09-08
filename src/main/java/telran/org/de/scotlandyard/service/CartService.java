package telran.org.de.scotlandyard.service;

import telran.org.de.scotlandyard.entity.Cart;

public interface CartService {

    void delete(Long userEntity);

    //Cart findByUserId();

    Cart create(Cart cart);

    void clearCartForUser();

    Cart findByUserId(Long userId);

}

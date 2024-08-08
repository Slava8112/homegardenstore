package telran.org.scotlandyard.service;

import telran.org.scotlandyard.entity.Cart;

public interface CartService {

    Cart create(Cart cart);

    void delete(Long cart_id);
}

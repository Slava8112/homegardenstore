package telran.org.scotlandyard.service;

import telran.org.scotlandyard.entity.Cart;
import telran.org.scotlandyard.entity.UserEntity;

public interface CartService {

//    Cart create(Long userEntityId);

    Cart getById(Long cartId);

    void delete(Long userEntity);
}

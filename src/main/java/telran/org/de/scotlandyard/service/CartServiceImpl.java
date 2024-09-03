package telran.org.de.scotlandyard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import telran.org.de.scotlandyard.entity.Cart;
import telran.org.de.scotlandyard.entity.UserEntity;
import telran.org.de.scotlandyard.exception.ProductNotFoundException;
import telran.org.de.scotlandyard.repository.CartRepository;
import telran.org.de.scotlandyard.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final UserService userService;
    private final UserRepository userRepository;
    private CartRepository cartRepository;

    @Override
    public void delete(Long cart_id) {
        Cart unit = cartRepository.getById(cart_id);
        cartRepository.delete(unit);
    }

    @Override
    public Cart create(Cart cart) {

        Long userId = userService.getCurrentUser().getId();
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ProductNotFoundException("User not found with id " + userId));


        Cart cart1 = cartRepository.findById(userId).orElseGet(() -> {

            Cart newCart = new Cart();
            newCart.setUserEntity(user);
            return newCart;
        });
    return cart1;
    }

}

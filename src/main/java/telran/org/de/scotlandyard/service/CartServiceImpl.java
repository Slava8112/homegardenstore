package telran.org.de.scotlandyard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import telran.org.de.scotlandyard.converter.CartConverter;
import telran.org.de.scotlandyard.dto.cartdto.CartCreateDto;
import telran.org.de.scotlandyard.dto.cartdto.CartDto;
import telran.org.de.scotlandyard.dto.cartitemdto.CartItemCreateDto;
import telran.org.de.scotlandyard.entity.Cart;
import telran.org.de.scotlandyard.entity.UserEntity;
import telran.org.de.scotlandyard.exception.CartNotFoundException;
import telran.org.de.scotlandyard.exception.UserNotFoundException;
import telran.org.de.scotlandyard.repository.CartItemsRepository;
import telran.org.de.scotlandyard.repository.CartRepository;
import telran.org.de.scotlandyard.repository.UserRepository;

import java.util.HashSet;


@Slf4j
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartConverter converter;
    private final UserService userService;
    private final UserRepository userRepository;
    private final CartItemsRepository cartItemsRepository;
    private final CartRepository cartRepository;
    private final CartConverter cartConverter;

    @Override
    public void delete(Long cart_id) {
        Cart unit = cartRepository.getById(cart_id);
        cartRepository.delete(unit);
    }

//    @Override
//    public Cart create(Cart cart) {
//        Long userId = userService.getCurrentUser().getId();
//        UserEntity user = userRepository.findById(userId)
//                .orElseThrow(() -> new ProductNotFoundException("User not found with id " + userId));
//
//
//        Cart cart1 = cartRepository.findById(userId).orElseGet(() -> {
//
//            Cart newCart = new Cart();
//            newCart.setUserEntity(user);
//            return newCart;
//        });
//    return cart1;
//    }

    @Override
    public CartDto create(CartCreateDto cartCreateDto) {


        //      .orElseThrow(() -> new CartNotFoundException("User not found with id " + userService.getCurrentUser().getId()));

        Long userId = userService.getCurrentUserId();
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));
        Cart cart = cartRepository.findByUserEntityId(userId).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUserEntity(user);
            cartRepository.save(newCart);
            return newCart;
        });

        updateLoadedCart(cart, cartCreateDto.cartItems());

        cart = cartRepository.save(cart);
        return cartConverter.toDto(cart);

}

    private void updateLoadedCart(Cart cart, HashSet<CartItemCreateDto> cartItemCreateDtos) {
        if(cart.getId() == null){
            cartRepository.save(cart);
        }
    }

    @Override
public void clearCartForUser() {
    Long userId = userService.getCurrentUserId();
    Cart cart = cartRepository.findByUserEntityId(userId)
            .orElseThrow(() -> new CartNotFoundException("Cart not found for user with id " + userId));

    // Удаляем все товары в корзине
    cartItemsRepository.deleteAllByCart(cart);
}

@Override
public Cart findByUserId(Long userId) {
    return cartRepository.findByUserEntityId(userId)
            .orElseThrow(() -> new CartNotFoundException("Cart not found for user with id " + userId));
}
}

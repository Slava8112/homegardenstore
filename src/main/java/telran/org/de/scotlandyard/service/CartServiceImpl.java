package telran.org.de.scotlandyard.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import telran.org.de.scotlandyard.dto.cartdto.CartCreateDto;
import telran.org.de.scotlandyard.dto.cartitemdto.CartItemCreateDto;
import telran.org.de.scotlandyard.entity.Cart;
import telran.org.de.scotlandyard.entity.CartItems;
import telran.org.de.scotlandyard.entity.Product;
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

    private final UserService userService;
    private final UserRepository userRepository;
    private final CartItemsRepository cartItemsRepository;
    private final CartRepository cartRepository;
    private final ProductService productService;

    @Override
    public void delete(Long cart_id) {
        Cart unit = cartRepository.getById(cart_id);
        cartRepository.delete(unit);
    }

    @Override
    public Cart create(CartCreateDto cartCreateDto) {
        Long userId = userService.getCurrentUserId();
        log.info("Создание корзины для пользователя с ID: {}", userId);
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));

        Cart cart = cartRepository.findByUserEntityId(userId).orElseGet(() -> {
            log.info("Корзина для пользователя с ID: {} не найдена. Создаем новую.", userId);
            Cart newCart = new Cart();
            newCart.setUserEntity(user);
            cartRepository.save(newCart);
            log.info("Создана новая корзина с ID: {} для пользователя с ID: {}", newCart.getId(), userId);
            return newCart;
        });

        updateLoadedCart(cart, cartCreateDto.cartItems());
        log.info("Корзина с ID: {} успешно обновлена и сохранена.", cart.getId());

        return cartRepository.save(cart);
    }

    private void updateLoadedCart(Cart cart, HashSet<CartItemCreateDto> cartItemCreateDtos) {
        if (cart.getId() == null) {
            cartRepository.save(cart);
        } else {
            cartItemCreateDtos.forEach(cartItemCreateDto -> {
                CartItems newItem = new CartItems();
                newItem.setQuantity(cartItemCreateDto.quantity());
                Product product = productService.getById(cartItemCreateDto.productId());
                newItem.setProduct(product);

                cart.getCartItems().add(newItem);
            });
        }
    }

    @Transactional
    @Override
    public void clearCartForUser() {
        Long userId = userService.getCurrentUserId();
        Cart cart = cartRepository.findByUserEntityId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found for user with id " + userId));

        cartItemsRepository.deleteAllByCart(cart);
    }

    @Override
    public Cart findByUserId(Long userId) {
        return cartRepository.findByUserEntityId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found for user with id " + userId));
    }

    @Override
    public Cart getCurrentUserCart() {
        Long userId = userService.getCurrentUserId();
        log.info("Получаем корзину для текущего пользователя с ID: {}", userId);
        return cartRepository.findByUserEntityId(userId)
                .orElseThrow(() -> {
                    Cart newCart = new Cart();
                    return new CartNotFoundException("Корзина не найдена для пользователя с id " + userId);
                });
    }
}

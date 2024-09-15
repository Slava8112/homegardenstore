package telran.org.de.scotlandyard.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import telran.org.de.scotlandyard.dto.cartdto.CartCreateDto;
import telran.org.de.scotlandyard.entity.Cart;
import telran.org.de.scotlandyard.entity.CartItems;
import telran.org.de.scotlandyard.entity.Product;
import telran.org.de.scotlandyard.repository.CartItemsRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartItemsServiceImpl implements CartItemsService {
    private final CartItemsRepository cartItemsRepository;
    private final ProductService productService;
    private final CartService cartService;
    private final UserService userService;


    @Override
    public List<CartItems> getAll() {
        return cartItemsRepository.findAll();
    }

    @Override
    @Transactional
    public CartItems add(Long productId, int quantity) {
        Product product = productService.getById(productId);
        log.info("Добавление продукта с ID: {} в корзину, количество: {}", productId, quantity);
        Cart cart = cartService.getCurrentUserCart();

        if (cart == null) {
            Long userId = userService.getCurrentUserId();
            log.info("Корзина не найдена для пользователя с ID: {}. Создаем новую корзину.", userId);
            CartCreateDto cartCreateDto = new CartCreateDto(userId, new HashSet<>());
            cart = cartService.create(cartCreateDto);
            log.info("Создана новая корзина с ID: {} для пользователя с ID: {}", cart.getId(), userId);
        } else {
            log.info("Корзина с ID: {} найдена для пользователя с ID: {}", cart.getId(), cart.getUserEntity().getId());
        }

        CartItems existingCartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (existingCartItem != null) {
            int newQuantity = existingCartItem.getQuantity() + quantity;
            existingCartItem.setQuantity(newQuantity);
            existingCartItem.setPricePurshause(existingCartItem.getProduct().getPrice() * newQuantity);
            return cartItemsRepository.save(existingCartItem);
        }

        CartItems cartItems = new CartItems();
        cartItems.setProduct(product);
        cartItems.setQuantity(quantity);
        cartItems.setPricePurshause(product.getPrice() * quantity);
        cartItems.setCart(cart);
//        double pricePurshause = product.getPrice() * quantity;
        log.info("Добавление товара в корзину. Продукт: {}, Количество: {}, Цена: {}", product.getName(), quantity, product.getPrice() * quantity);
        cart.getCartItems().add(cartItems);
        cartItemsRepository.save(cartItems);
        return cartItems;
    }
    @Override
    public void deleteById(Long cartitemsId) {
        cartItemsRepository.deleteById(cartitemsId);
    }
}

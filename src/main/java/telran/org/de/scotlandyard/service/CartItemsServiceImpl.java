package telran.org.de.scotlandyard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import telran.org.de.scotlandyard.entity.CartItems;
import telran.org.de.scotlandyard.entity.Product;
import telran.org.de.scotlandyard.repository.CartItemsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemsServiceImpl implements CartItemsService {
    private final CartItemsRepository cartItemsRepository;
    private final ProductService productService;
    private final CartService cartService;

    @Override
    public List<CartItems> getAll() {
        return cartItemsRepository.findAll();
    }


    @Override
    public CartItems add(Long productsId, CartItems cartitems) {
        Product product = productService.getById(productsId);
        cartitems.setProduct(product);
        return cartItemsRepository.save(cartitems);
    }

    @Override
    public void deleteById(Long cartitemsId) {
        cartItemsRepository.deleteById(cartitemsId);
    }
}

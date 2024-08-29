package telran.org.scotlandyard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import telran.org.scotlandyard.entity.Cartitems;
import telran.org.scotlandyard.entity.Product;
import telran.org.scotlandyard.entity.*;
import telran.org.scotlandyard.repository.CartItemsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemsServiceImpl implements CartItemsService{
    private final CartItemsRepository cartItemsRepository;
    private final ProductService productService;
    private final CartService cartService;

    @Override
    public List<Cartitems> getAll() {
        return cartItemsRepository.findAll();
    }

    @Override
    public Cartitems add(Long productsId, Cartitems cartitems) {
        Product product = productService.getById(productsId);
        cartitems.setProduct(product);
        return cartItemsRepository.save(cartitems);
    }

    @Override
    public void deleteById(Long cartitemsId) {
                        cartItemsRepository.deleteById(cartitemsId);
    }

    @Override
    public Cartitems update(Cartitems cartitems) {
        return null;
    }
}
